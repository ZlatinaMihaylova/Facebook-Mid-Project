package Views;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Common.FacebookSystem;
import Common.Profile;
import Common.User;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.awt.event.ActionEvent;

public class ProfileWindow {

	private JFrame frame;
	
	
	private Profile profile;
	private static User loggedInUser;
	private boolean isItMyProfile;

	/**
	 * Launch the application.
	 */
	public static void main(Profile profile,boolean isItMyProfile) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileWindow window = new ProfileWindow(profile, isItMyProfile);
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
	public ProfileWindow(Profile profile,boolean isItMyProfile) {
		this.profile = profile;
		this.isItMyProfile = isItMyProfile;
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
		frame.setBounds(100, 100, 640, 480);
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
		
		if ( this.isItMyProfile) {
			JButton changeInformationButton = new JButton("Change Information");
			changeInformationButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					frame.setVisible(false);
	                frame.dispose();
					
					ChangeUserInformationWindow changeInformation = new ChangeUserInformationWindow(profile);
					ChangeUserInformationWindow.main(profile);
					
				}
			});
			changeInformationButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			changeInformationButton.setBounds(12, 275, 171, 25);
			frame.getContentPane().add(changeInformationButton);
			
			
			JButton logoutButton = new JButton("Log out");
			logoutButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Log out",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						
						frame.setVisible(false);
		                frame.dispose();
		                
		                setLoggedInUser(null);
		               
		                FacebookSystem.getFacebookSystem().logOut(loggedInUser);
		                LoginSystemWindow newLogin = new LoginSystemWindow();
		                LoginSystemWindow.main(null);
					}			
				}
			});
			logoutButton.setBounds(492, 16, 97, 25);
			frame.getContentPane().add(logoutButton);
		}

		
		
		
	}

	
}