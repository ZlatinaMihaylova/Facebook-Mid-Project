package Views;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Common.FacebookSystem;
import Common.Post;
import Common.Profile;
import Common.User;
import javax.swing.JSeparator;

public class HomePageWindow {

	private JFrame frame;
	private JTextField postText;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePageWindow window = new HomePageWindow();
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
	public HomePageWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JTextField searchFiled = new JTextField();
		searchFiled.setBounds(310, 13, 142, 22);
		frame.getContentPane().add(searchFiled);
		searchFiled.setColumns(10);
		
		
		
		
		JButton searchButton = new JButton("Search");
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String searched = searchFiled.getText();
				HashSet<Profile> results = new HashSet<Profile>();
				
				results = FacebookSystem.getFacebookSystem().searchByName(searched);

				DefaultListModel listModel   = new DefaultListModel();
				for (Profile profile: results) {
					listModel.addElement(profile);
				}
				
				JList list = new JList();
				list.setModel(listModel);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(310, 35, 142, 65);
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
						                
						             if ( ProfileWindow.getLoggedInUser().equals(profile)) {
						            	 ProfileWindow profileView = new ProfileWindow(profile, true,ProfileWindow.getLoggedInUser());
						            	 ProfileWindow.main(profile,true,ProfileWindow.getLoggedInUser()); 
						             }
						             else {
						            	 ProfileWindow profileView = new ProfileWindow(profile, false,ProfileWindow.getLoggedInUser());
						            	 ProfileWindow.main(profile,false,ProfileWindow.getLoggedInUser()); 
						             }
										
				            	 }
				             }
				        
				         }
				    }
				}); 
			}
		});
		searchButton.setBounds(464, 12, 84, 25);
		frame.getContentPane().add(searchButton);
		
		JButton myProfile = new JButton("My Profile");
		myProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.setVisible(false);
                frame.dispose();
				
				ProfileWindow profile = new ProfileWindow(ProfileWindow.getLoggedInUser(), true,ProfileWindow.getLoggedInUser());
				ProfileWindow.main(ProfileWindow.getLoggedInUser(), true,ProfileWindow.getLoggedInUser()); 
				
				
			}
		});
		myProfile.setBounds(573, 12, 113, 25);
		frame.getContentPane().add(myProfile);
		
		JButton logoutButton = new JButton("Log out");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log out",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					
					frame.setVisible(false);

	                FacebookSystem.getFacebookSystem().logOut(ProfileWindow.getLoggedInUser());
	                LoginSystemWindow newLogin = new LoginSystemWindow();
	                LoginSystemWindow.main(null);
	                
	                ProfileWindow.setLoggedInUser(null);
				}			
			}
		});
		logoutButton.setBounds(686, 12, 84, 25);
		frame.getContentPane().add(logoutButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 103, 731, 2);
		frame.getContentPane().add(separator);
		
		JButton showFriendRequest = new JButton("Friend Requests");
		showFriendRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Set<Profile> results = new HashSet<Profile>();
				
				results = ProfileWindow.getLoggedInUser().getFriendRequest();

				DefaultListModel listModel   = new DefaultListModel();
				for (Profile profile: results) {
					listModel.addElement(profile);
				}
			
				JList list = new JList();
				list.setModel(listModel);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 37, 130, 65);
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
		showFriendRequest.setBounds(12, 12, 130, 25);
		frame.getContentPane().add(showFriendRequest);
		
		
		DefaultListModel listModel   = new DefaultListModel();
		for (Post post: ProfileWindow.getLoggedInUser().getNewsFeed().getPosts()) {
			listModel.addElement(post);
		}
		
		JList list = new JList();
		list.setBorder(null);
		list.setBackground(SystemColor.menu);
		list.setModel(listModel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 270, 758, 270);
		scrollPane.setViewportView(list);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();

		postText = new JTextField();
		postText.setBounds(212, 118, 354, 104);
		frame.getContentPane().add(postText);
		postText.setColumns(10);
		
		JButton postButton = new JButton("Post");
		postButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String text = postText.getText();
				postText.setText(null);
				
				try {
					ProfileWindow.getLoggedInUser().writeNewStatus(text);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DefaultListModel listModel   = new DefaultListModel();
				for (Post post: ProfileWindow.getLoggedInUser().getNewsFeed().getPosts()) {
					listModel.addElement(post);
				}
				
				JList list = new JList();
				list.setBorder(null);
				list.setBackground(SystemColor.menu);
				list.setModel(listModel);
				
				scrollPane.setBounds(12, 270, 758, 270);
				scrollPane.setViewportView(list);
				frame.getContentPane().add(scrollPane);
				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
			}
		});
		postButton.setBounds(341, 232, 97, 25);
		frame.getContentPane().add(postButton);
		
		
		
		if (ProfileWindow.getLoggedInUser().getLastChat() != null ) {
			
			JButton messengerButton = new JButton("Messenger");
			messengerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setVisible(false);
	                frame.dispose();
					
					try {
						MessengerWindow messa = new MessengerWindow(ProfileWindow.getLoggedInUser().getLastChat().getOtherPerson());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					MessengerWindow.main(ProfileWindow.getLoggedInUser().getLastChat().getOtherPerson()); 
				}
			});
			messengerButton.setBounds(147, 12, 122, 23);
			frame.getContentPane().add(messengerButton);
		}

	}
}
