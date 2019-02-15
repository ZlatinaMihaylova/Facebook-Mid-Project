package Views;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Common.FacebookSystem;
import Common.Profile;

public class HomePageWindow {

	private JFrame frame;

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
		searchFiled.setBounds(194, 13, 188, 22);
		frame.getContentPane().add(searchFiled);
		searchFiled.setColumns(10);
		
		JList list = new JList();
		list.setBounds(194, 30, 188, 100);

		
		JButton searchButton = new JButton("Search");
		frame.getContentPane().add(list);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String searched = searchFiled.getText();
				HashSet<Profile> results = new HashSet<Profile>();
				
				results = FacebookSystem.getFacebookSystem().searchByName(searched);
				
				DefaultListModel listModel   = new DefaultListModel();
				for (Profile profile: results) {
					listModel.addElement(profile);
				}
				
				list.setModel(listModel);
				
				
				JButton viewProfile = new JButton("View profile");
				frame.getContentPane().add(viewProfile);
				viewProfile.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (list.getSelectedValue() != null ) {
							frame.setVisible(false);
			                frame.dispose();
			                
							ProfileWindow profile = new ProfileWindow((Profile)list.getSelectedValue(), false);
							ProfileWindow.main((Profile)list.getSelectedValue(),false); 
						}
						
					}
				});
				viewProfile.setBounds(394, 40, 97, 25);
				
				
				
				
				
			}
		});
		searchButton.setBounds(394, 12, 97, 25);
		frame.getContentPane().add(searchButton);
		
		JButton myProfile = new JButton("My Profile");
		myProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.setVisible(false);
                frame.dispose();
				
				ProfileWindow profile = new ProfileWindow(ProfileWindow.getLoggedInUser(), true);
				ProfileWindow.main(ProfileWindow.getLoggedInUser(), true); 
				
				
			}
		});
		myProfile.setBounds(559, 12, 97, 25);
		frame.getContentPane().add(myProfile);
	}
}
