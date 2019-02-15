package Views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Common.FacebookSystem;
import Common.User;

import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class SignUpSystemWindow {

	private JFrame frame;
	private JTextField newNameField;
	private JTextField newEmailField;
	private JPasswordField newPasswordField;
	
	
	private static boolean verifyEmail(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
	private static boolean verifyPassword(String password) {
		boolean isLower = false;
		boolean isUpper = false;
		boolean isDigit = false;
		
		if ( password.length() < 5) {
			return false;
		}
		for ( int index = 0; index < password.length(); index++) {
			if (Character.isLowerCase(password.charAt(index))) {
				isLower = true;
			}
			if (Character.isUpperCase(password.charAt(index))) {
				isUpper = true;
			}
			if (Character.isDigit(password.charAt(index))) {
				isDigit = true;
			}
		}
		if (isLower && isUpper && isDigit ) {
			return true;
		}
		return false;
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpSystemWindow window = new SignUpSystemWindow();
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
	public SignUpSystemWindow() {
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
		
		JLabel lblSignUp = new JLabel("Sign Up:");
		lblSignUp.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSignUp.setBounds(180, 13, 82, 31);
		frame.getContentPane().add(lblSignUp);
		
		JLabel newName = new JLabel("Name:");
		newName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newName.setBounds(78, 61, 56, 16);
		frame.getContentPane().add(newName);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(78, 101, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(72, 141, 92, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		newNameField = new JTextField();
		newNameField.setBounds(202, 57, 186, 27);
		frame.getContentPane().add(newNameField);
		newNameField.setColumns(10);
		
		newEmailField = new JTextField();
		newEmailField.setBounds(202, 97, 186, 27);
		frame.getContentPane().add(newEmailField);
		newEmailField.setColumns(10);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(202, 137, 186, 27);
		frame.getContentPane().add(newPasswordField);
		

		JLabel sameEmailError = new JLabel();
		sameEmailError.setHorizontalAlignment(SwingConstants.LEFT);
		sameEmailError.setForeground(Color.RED);
		sameEmailError.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sameEmailError.setBounds(212, 119, 170, 22);
		frame.getContentPane().add(sameEmailError);
		
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = newNameField.getText();
				String email = newEmailField.getText();
				String password = newPasswordField.getText();
				
				if (FacebookSystem.getFacebookSystem().containsEmail(email)) {
					sameEmailError.setText("Email Address is Already Registered.");
				}
				
				else if (SignUpSystemWindow.verifyEmail(email) && SignUpSystemWindow.verifyPassword(password)) {
					sameEmailError.setText(null);
					User user;
					try {
						user = new User(name, email, password);
						FacebookSystem.getFacebookSystem().register(user);
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					JOptionPane.showMessageDialog(null, "Welcome to Facebook!", "Sign Up Completed", JOptionPane.PLAIN_MESSAGE);
					
					frame.setVisible(false);
	                frame.dispose();
	                
	                LoginSystemWindow login = new LoginSystemWindow();
					LoginSystemWindow.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid e-mail or password", "Sign Up Error", JOptionPane.ERROR_MESSAGE);
					sameEmailError.setText(null);
					newEmailField.setText(null);
					newPasswordField.setText(null);
				}
			}
		});
		signUpButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		signUpButton.setBounds(12, 193, 122, 46);
		frame.getContentPane().add(signUpButton);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
                frame.dispose();
                
				LoginSystemWindow login = new LoginSystemWindow();
				LoginSystemWindow.main(null);
				
			}
		});
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginButton.setBounds(148, 193, 130, 46);
		frame.getContentPane().add(loginButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Confirm if you want to exit", "Login Systems",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		exitButton.setBounds(290, 193, 130, 46);
		frame.getContentPane().add(exitButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 49, 408, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 178, 408, 2);
		frame.getContentPane().add(separator_1);
		
		
		
	}
}