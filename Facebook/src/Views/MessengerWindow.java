package Views;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Container;

import javax.swing.SwingConstants;

import Common.Chat;
import Common.Chat.Message;
import Common.Profile;
import Common.User;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class MessengerWindow {

	private JFrame frame;
	private JTextField textField;
	private String personName = "";
	private Profile otherPerson;
	/**
	 * Launch the application.
	 */
	public static void main(Profile otherPerson) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessengerWindow window = new MessengerWindow(otherPerson);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	
	public MessengerWindow(Profile otherPerson) throws Exception {
		this.otherPerson = otherPerson;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton homePageButton = new JButton("Home Page");
		homePageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
                frame.dispose();
				
				HomePageWindow homePageWindow = new HomePageWindow();
				HomePageWindow.main(null);
			}
		});
		homePageButton.setBounds(590, 13, 168, 25);
		frame.getContentPane().add(homePageButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 51, 758, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(216, 66, 8, 674);
		frame.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(216, 678, 554, 2);
		frame.getContentPane().add(separator_2);
		
		textField = new JTextField();
		textField.setBounds(226, 688, 443, 52);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		DefaultListModel listModel2   = new DefaultListModel();
		for (Chat.Message message: ProfileWindow.getLoggedInUser().findChat(otherPerson).getMessages()) {
			listModel2.addElement(message);
		}
		
		JList list2 = new JList();
		list2.setModel(listModel2);
		
		
		JScrollPane scrollPane = new JScrollPane();////tova sa suobshteniqta s nqkoi
		scrollPane.setForeground(SystemColor.menu);
		scrollPane.setBounds(228, 128, 542, 537);
		scrollPane.setViewportView(list2);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String textMessage = textField.getText();
				textField.setText(null);
				try {
					ProfileWindow.getLoggedInUser().sendMessage(textMessage,otherPerson);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				DefaultListModel listModel2   = new DefaultListModel();
				try {
					for (Chat.Message message: ProfileWindow.getLoggedInUser().findChat(otherPerson).getMessages()) {
						listModel2.addElement(message);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JList list2 = new JList();
				list2.setModel(listModel2);
				
				
				////tova sa suobshteniqta s nqkoi
				scrollPane.setForeground(SystemColor.menu);
				scrollPane.setBounds(228, 128, 542, 537);
				scrollPane.setViewportView(list2);
				frame.getContentPane().add(scrollPane);
				frame.getContentPane().revalidate();
				frame.getContentPane().repaint();
				
				
			}
			
			
		});
		sendButton.setBounds(673, 702, 97, 25);
		frame.getContentPane().add(sendButton);
		

		JLabel lblNewLabel = new JLabel(otherPerson.getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(247, 66, 300, 37);
		frame.getContentPane().add(lblNewLabel);
		

		DefaultListModel listModel   = new DefaultListModel();
		for (Chat chat: ProfileWindow.getLoggedInUser().getChats()) {
			listModel.addElement(chat);
		}
		
		JList list = new JList();
		list.setModel(listModel);
		
		JScrollPane scrollPane_1 = new JScrollPane();// tova sa vsichki suobshteniq
		scrollPane_1.setForeground(SystemColor.menu);
		scrollPane_1.setBounds(12, 66, 195, 674);
		scrollPane_1.setViewportView(list);
		frame.getContentPane().add(scrollPane_1);
//		frame.getContentPane().revalidate();
//		frame.getContentPane().repaint();
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		    	 JList list  = (JList)evt.getSource();
		         if (evt.getClickCount() == 2) {
		        	 
		        	 Container parent = lblNewLabel.getParent();
		        	 parent.remove(lblNewLabel);
		        	 parent.validate();
		        	 parent.repaint();
		        	 
		             int index = list.locationToIndex(evt.getPoint());
		             otherPerson = ((Chat) list.getModel().getElementAt(index)).getOtherPerson();
		             JLabel lblNewLabel = new JLabel(otherPerson.getName());
		     		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		     		lblNewLabel.setBounds(247, 66, 300, 37);
		     		frame.getContentPane().add(lblNewLabel);
		     		
		             if ( index >= 0) {
		            	 DefaultListModel listModel2   = new DefaultListModel();
		         		try {
							for (Chat.Message message: ((Chat) list.getModel().getElementAt(index)).getMessages()) {
								listModel2.addElement(message);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		         		
		         		JList list2 = new JList();
		         		list2.setModel(listModel2);

		         	//	JScrollPane scrollPane = new JScrollPane();////tova sa suobshteniqta s nqkoi
		         		scrollPane.setForeground(SystemColor.menu);
		         		scrollPane.setBounds(228, 128, 542, 537);
		         		scrollPane.setViewportView(list2);
		         		frame.getContentPane().add(scrollPane);
		         		frame.getContentPane().revalidate();
		         		frame.getContentPane().repaint();
		             }
		        
		         }
		    }
		}); 
		
		
	}
}
