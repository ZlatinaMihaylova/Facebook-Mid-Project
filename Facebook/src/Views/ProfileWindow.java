package Views;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Common.Chat;
import Common.FacebookSystem;
import Common.Post;
import Common.Profile;
import Common.User;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class ProfileWindow {

	private JFrame frame;
	
	
	private Profile profile;
	private static User loggedInUser;
	private boolean isItMyProfile;
	private JTextField postText;

	/**
	 * Launch the application.
	 */
	public static void main(Profile profile,boolean isItMyProfile,User loggedInUser) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileWindow window = new ProfileWindow(profile, isItMyProfile,loggedInUser);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProfileWindow(Profile profile,boolean isItMyProfile, User loggedInUser) {
		this.profile = profile;
		this.isItMyProfile = isItMyProfile;
		this.loggedInUser = loggedInUser;
		initialize();
	}
	
	public static void setLoggedInUser(User loggedInUser) {
		ProfileWindow.loggedInUser = loggedInUser;
	}
	
	public static User getLoggedInUser() {
		return loggedInUser;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel(this.profile.toString());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 13, 332, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Information:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(34, 101, 170, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("High School:");
		lblNewLabel_2.setBounds(12, 130, 103, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("University:");
		lblNewLabel_3.setBounds(12, 159, 103, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Employer:");
		lblNewLabel_4.setBounds(12, 188, 103, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Current City:");
		lblNewLabel_5.setBounds(12, 217, 103, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Hometown:");
		lblNewLabel_6.setBounds(12, 246, 103, 16);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel highSchoolInfo = new JLabel(this.profile.getHighSchool());
		highSchoolInfo.setBounds(127, 130, 138, 16);
		frame.getContentPane().add(highSchoolInfo);
		
		JLabel UniversityInfo = new JLabel(this.profile.getUniversity());
		UniversityInfo.setBounds(127, 159, 138, 16);
		frame.getContentPane().add(UniversityInfo);
		
		JLabel EmployeerInfo = new JLabel(this.profile.getEmployer());
		EmployeerInfo.setBounds(127, 188, 138, 16);
		frame.getContentPane().add(EmployeerInfo);
		
		JLabel CurrentCityInfo = new JLabel(this.profile.getCurrentCity());
		CurrentCityInfo.setBounds(127, 217, 138, 16);
		frame.getContentPane().add(CurrentCityInfo);
		
		JLabel HometownInfo = new JLabel(this.profile.getBirthPlace());
		HometownInfo.setBounds(127, 246, 138, 16);
		frame.getContentPane().add(HometownInfo);
		
		JButton homePageButton = new JButton("Home page");
		homePageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
                frame.dispose();
				
				HomePageWindow homePageWindow = new HomePageWindow();
				HomePageWindow.main(null);
			}
		});
		homePageButton.setBounds(356, 16, 145, 25);
		frame.getContentPane().add(homePageButton);
		
		
		DefaultListModel listModel   = new DefaultListModel();
		for (Post post: profile.getPosts()) {
			listModel.addElement(post);
		}
		
		JList list = new JList();
		list.setBorder(null);
		list.setBackground(SystemColor.menu);
		list.setModel(listModel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(299, 240, 311, 349);
		scrollPane.setViewportView(list);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		
		if ( this.isItMyProfile) {
			JButton changeInformationButton = new JButton("Change Information");
			changeInformationButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					frame.setVisible(false);
	                frame.dispose();
					
					ChangeUserInformationWindow changeInformation = new ChangeUserInformationWindow(profile,loggedInUser);
					ChangeUserInformationWindow.main(profile,loggedInUser);
					
				}
			});
			changeInformationButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			changeInformationButton.setBounds(12, 275, 171, 25);
			frame.getContentPane().add(changeInformationButton);
			
			postText = new JTextField();
			postText.setBounds(299, 95, 311, 109);
			frame.getContentPane().add(postText);
			postText.setColumns(10);
			
			JButton postButton = new JButton("Post");
			postButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String text = postText.getText();
					postText.setText(null);
					
					try {
						loggedInUser.writeNewStatus(text);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DefaultListModel listModel   = new DefaultListModel();
					for (Post post: profile.getPosts()) {
						listModel.addElement(post);
					}
					
					JList list = new JList();
					list.setBorder(null);
					list.setBackground(SystemColor.menu);
					list.setModel(listModel);
					
					scrollPane.setBounds(299, 240, 311, 349);
					scrollPane.setViewportView(list);
					frame.getContentPane().add(scrollPane);
					frame.getContentPane().revalidate();
					frame.getContentPane().repaint();
					
					
				}
			});
			postButton.setBounds(392, 213, 97, 25);
			frame.getContentPane().add(postButton);
			
		}
		
		else { 
			if (loggedInUser.containsFriend(profile) ) {
				JButton friendsButton = new JButton("You are friends");
				friendsButton.setBounds(22, 51, 182, 25);
				frame.getContentPane().add(friendsButton);
			}
			else if (loggedInUser.containsFriendRequest(profile)) {
				JButton friendRequestButton = new JButton("Accept friend request");
				friendRequestButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							loggedInUser.acceptFriendRequest(profile);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						frame.remove(friendRequestButton);
						
						JButton friendsButton = new JButton("You are friends");
						friendsButton.setBounds(22, 51, 182, 25);
						frame.getContentPane().add(friendsButton);
					}
				});
				friendRequestButton.setBounds(22, 51, 182, 25);
				frame.getContentPane().add(friendRequestButton);
			}
			else {
				JButton friendRequestButton = new JButton("Send Friend Request");
				friendRequestButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						loggedInUser.sendFriendRequest((User) profile);
						
						frame.remove(friendRequestButton);
						
						JButton friendsButton = new JButton("Friend request is sent");
						friendsButton.setBounds(22, 51, 182, 25);
						frame.getContentPane().add(friendsButton);
					}
				});
				friendRequestButton.setBounds(22, 51, 182, 25);
				frame.getContentPane().add(friendRequestButton);
			}
			
			
			JButton sendMessageButton = new JButton("Send Message");
			sendMessageButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					frame.setVisible(false);
	                frame.dispose();
	                
	                try {
						ProfileWindow.getLoggedInUser().findChat(profile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						MessengerWindow profileView = new MessengerWindow(profile);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					MessengerWindow.main(profile); 
				}
			});
			sendMessageButton.setBounds(189, 51, 145, 25);
			frame.getContentPane().add(sendMessageButton);
		}
		
		JButton logoutButton = new JButton("Log out");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log out",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					
					frame.setVisible(false);
	                frame.dispose();

	                FacebookSystem.getFacebookSystem().logOut(loggedInUser);
	                LoginSystemWindow newLogin = new LoginSystemWindow();
	                LoginSystemWindow.main(null);
	                
	                setLoggedInUser(null);
				}			
			}
		});
		logoutButton.setBounds(492, 16, 97, 25);
		frame.getContentPane().add(logoutButton);
		
		JButton showFriendsButton = new JButton("Friends");
		showFriendsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultListModel listModel   = new DefaultListModel();
				for (Profile user: ((User)profile).getFriends()) {
					listModel.addElement(user);
				}
				
				JList list = new JList();
				list.setModel(listModel);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 340, 251, 185);
				scrollPane.setViewportView(list);
				frame.getContentPane().add(scrollPane);
				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
				
				list.addMouseListener(new MouseAdapter() {
				    public void mouseClicked(MouseEvent evt) {
				    	JList list  = (JList)evt.getSource();
				        if (evt.getClickCount() == 2) {
				        	int index = list.locationToIndex(evt.getPoint());
				            if ( index >= 0) {
				            	Profile profile = (Profile) list.getModel().getElementAt(index);
				            	if ( profile != null) {
				            		frame.setVisible(false);
						            frame.dispose();
						                
						            ProfileWindow profileView = new ProfileWindow(profile, false,ProfileWindow.getLoggedInUser());
						            ProfileWindow.main(profile,false,ProfileWindow.getLoggedInUser()); 
										
				            	 }
				             }
				         }
				    }
				}); 
			}
		});
		showFriendsButton.setBounds(56, 310, 97, 25);
		frame.getContentPane().add(showFriendsButton);
		
		
	}
}