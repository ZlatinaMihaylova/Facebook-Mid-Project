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
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JTextField searchFiled = new JTextField();
		searchFiled.setBounds(12, 13, 188, 22);
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
				scrollPane.setBounds(12, 35, 188, 100);
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
		searchButton.setBounds(212, 12, 97, 25);
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
		myProfile.setBounds(578, 12, 97, 25);
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
		logoutButton.setBounds(687, 12, 83, 25);
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
				scrollPane.setBounds(469, 37, 97, 100);
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
		showFriendRequest.setBounds(469, 12, 97, 25);
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
		scrollPane.setBounds(12, 270, 758, 470);
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
				
				scrollPane.setBounds(299, 240, 311, 349);
				scrollPane.setViewportView(list);
				frame.getContentPane().add(scrollPane);
				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
			}
		});
		postButton.setBounds(341, 232, 97, 25);
		frame.getContentPane().add(postButton);
	}
}
