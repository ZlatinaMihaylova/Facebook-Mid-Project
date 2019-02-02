import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

import Common.FacebookSystem;
import Common.Profile;
import Common.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProfileWindow {

	private JFrame frame;
	
	
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileWindow window = new ProfileWindow(user);
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
	public ProfileWindow(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(601, 0, 21, 433);
		frame.getContentPane().add(scrollBar);
		
		JLabel lblNewLabel = new JLabel(this.user.getName());
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
		
		JLabel highSchoolInfo = new JLabel(this.user.getProfile().getHighSchool());
		highSchoolInfo.setBounds(127, 130, 138, 16);
		frame.getContentPane().add(highSchoolInfo);
		
		JLabel UniversityInfo = new JLabel(this.user.getProfile().getUniversity());
		UniversityInfo.setBounds(127, 159, 138, 16);
		frame.getContentPane().add(UniversityInfo);
		
		JLabel EmployeerInfo = new JLabel(this.user.getProfile().getEmployer());
		EmployeerInfo.setBounds(127, 188, 138, 16);
		frame.getContentPane().add(EmployeerInfo);
		
		JLabel CurrentCityInfo = new JLabel(this.user.getProfile().getCurrentCity());
		CurrentCityInfo.setBounds(127, 217, 138, 16);
		frame.getContentPane().add(CurrentCityInfo);
		
		JLabel HometownInfo = new JLabel(this.user.getProfile().getHometown());
		HometownInfo.setBounds(127, 246, 138, 16);
		frame.getContentPane().add(HometownInfo);
		
		JButton changeInformationButton = new JButton("Change Information");
		changeInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.setVisible(false);
                frame.dispose();
				
				ChangeUserInformationWindow changeInformation = new ChangeUserInformationWindow(user);
				ChangeUserInformationWindow.main(user);
				
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
	               
	                FacebookSystem.getFacebookSystem().logOut(user);
	                LoginSystemWindow newLogin = new LoginSystemWindow();
	                LoginSystemWindow.main(null);
				}			
			}
		});
		logoutButton.setBounds(492, 16, 97, 25);
		frame.getContentPane().add(logoutButton);
	}
}
