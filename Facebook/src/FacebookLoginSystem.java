import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Common.FacebookSystem;
import Common.User;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;

public class FacebookLoginSystem {

	private JFrame frame;
	private JTextField emailField;
	private JPasswordField passwordField;
	
	private FacebookSystem facebookSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacebookLoginSystem window = new FacebookLoginSystem();
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
	public FacebookLoginSystem() {
		this.facebookSystem = FacebookSystem.getFacebookSystem();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(215, 14, 64, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(67, 77, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(56, 119, 64, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		emailField = new JTextField();
		emailField.setBounds(171, 77, 185, 19);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(171, 116, 185, 22);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String email = emailField.getText();
				String password = passwordField.getText();
				
				User user = FacebookSystem.getFacebookSystem().logIn(email, password);
				if ( user == null ) {
					JOptionPane.showMessageDialog(null, "No such user exixts", "Login Error", JOptionPane.ERROR_MESSAGE);
					emailField.setText(null);
					passwordField.setText(null);
				}
				else {
					System.out.println("Welcome " + user.toString());
					
					frame.setVisible(false);
	                frame.dispose();
	                
					FacebookProfile profile = new FacebookProfile(user.getProfile());
					FacebookProfile.main(user.getProfile()); 

				}
			}
		});
		btnNewButton.setBounds(34, 183, 132, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
                frame.dispose();
				
				FacebookSignUpSystem signUp = new FacebookSignUpSystem();
				FacebookSignUpSystem.main(null);
				
			}
		});
		btnNewButton_1.setBounds(178, 183, 132, 44);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Confirm if you want to exit", "Login Systems",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(322, 183, 132, 44);
		frame.getContentPane().add(btnNewButton_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 151, 458, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 62, 458, 2);
		frame.getContentPane().add(separator_1);
	}
}