import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import java.awt.Font;

public class FacebookProfile {

	private JFrame frame;
	
	
	private Profile profile;

	/**
	 * Launch the application.
	 */
	public static void main(Profile profile2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacebookProfile window = new FacebookProfile(profile2);
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
	public FacebookProfile(Profile profile) {
		this.profile = profile;
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
		
		JLabel lblNewLabel = new JLabel(this.profile.getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 13, 332, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Information:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(44, 79, 170, 16);
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
		
		JLabel highSchoolInfo = new JLabel(this.profile.getInformation().getHighSchool());
		highSchoolInfo.setBounds(127, 130, 56, 16);
		frame.getContentPane().add(highSchoolInfo);
		
		JLabel UniversityInfo = new JLabel(this.profile.getInformation().getUniversity());
		UniversityInfo.setBounds(127, 159, 56, 16);
		frame.getContentPane().add(UniversityInfo);
		
		JLabel EmployeerInfo = new JLabel(this.profile.getInformation().getEmployer());
		EmployeerInfo.setBounds(127, 188, 56, 16);
		frame.getContentPane().add(EmployeerInfo);
		
		JLabel CurrentCityInfo = new JLabel(this.profile.getInformation().getCurrentCity());
		CurrentCityInfo.setBounds(127, 217, 56, 16);
		frame.getContentPane().add(CurrentCityInfo);
		
		JLabel HometownInfo = new JLabel(this.profile.getInformation().getHighSchool());
		HometownInfo.setBounds(127, 246, 56, 16);
		frame.getContentPane().add(HometownInfo);
	}
}
