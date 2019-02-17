package Views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Common.Profile;
import Common.User;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeUserInformationWindow {

	private JFrame frame;
	private JTextField updateHighSchoolField;
	private JTextField updateUniversityField;
	private JTextField updateEmployerField;
	private JTextField updateCurrentCityField;
	private JTextField updateHometownField;
	
	private Profile profile;
	private static User loggedInUser;

	/**
	 * Launch the application.
	 */
	public static void main(Profile profile,User loggedInUser) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeUserInformationWindow window = new ChangeUserInformationWindow(profile,loggedInUser);
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
	public ChangeUserInformationWindow(Profile profile, User loggedInUser) {
		this.profile = profile;
		ChangeUserInformationWindow.loggedInUser = loggedInUser;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Change Information:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(149, 13, 183, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("High school:");
		lblNewLabel_1.setBounds(54, 52, 102, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("University:");
		lblNewLabel_2.setBounds(54, 81, 102, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Employer:");
		lblNewLabel_3.setBounds(54, 110, 102, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Current city:");
		lblNewLabel_4.setBounds(54, 139, 102, 16);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Hometown:");
		lblNewLabel_5.setBounds(54, 168, 102, 16);
		frame.getContentPane().add(lblNewLabel_5);
		
		updateHighSchoolField = new JTextField(this.profile.getHighSchool());
		updateHighSchoolField.setBounds(168, 49, 164, 22);
		frame.getContentPane().add(updateHighSchoolField);
		updateHighSchoolField.setColumns(10);
		
		updateUniversityField = new JTextField(this.profile.getUniversity());
		updateUniversityField.setBounds(168, 78, 164, 22);
		frame.getContentPane().add(updateUniversityField);
		updateUniversityField.setColumns(10);
		
		updateEmployerField = new JTextField(this.profile.getEmployer());
		updateEmployerField.setBounds(168, 107, 164, 22);
		frame.getContentPane().add(updateEmployerField);
		updateEmployerField.setColumns(10);
		
		updateCurrentCityField = new JTextField(this.profile.getCurrentCity());
		updateCurrentCityField.setBounds(168, 136, 164, 22);
		frame.getContentPane().add(updateCurrentCityField);
		updateCurrentCityField.setColumns(10);
		
		updateHometownField = new JTextField(this.profile.getBirthPlace());
		updateHometownField.setBounds(168, 165, 164, 22);
		frame.getContentPane().add(updateHometownField);
		updateHometownField.setColumns(10);
		
		JButton updateInformationButton = new JButton("Update Information");
		updateInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					profile.updateInformation(updateHighSchoolField.getText(), updateUniversityField.getText(),
							updateEmployerField.getText(), updateCurrentCityField.getText(), updateHometownField.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				frame.setVisible(false);
                frame.dispose();
                
                ProfileWindow changeInformation = new ProfileWindow(profile, true,loggedInUser);
                ProfileWindow.main(profile,true,loggedInUser);
			}
		});
		updateInformationButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateInformationButton.setBounds(114, 200, 240, 40);
		frame.getContentPane().add(updateInformationButton);
	}
}