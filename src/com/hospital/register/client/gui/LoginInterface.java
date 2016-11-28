package com.hospital.register.client.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class LoginInterface extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private String username;
	private String password;
	private String identifier;
	

	/**
	 * Create the frame.
	 */
	public LoginInterface() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		setTitle("挂号系统登录");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 380);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("用户名：");
		lblUsername.setBounds(32, 58, 70, 28);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setText("username");
		txtUsername.setBounds(112, 59, 130, 26);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("密码：");
		lblPassword.setBounds(32, 134, 70, 28);
		contentPane.add(lblPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("password");
		pwdPassword.setBounds(112, 135, 130, 26);
		contentPane.add(pwdPassword);
		
		JButton btnLogin = new JButton("登录");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = getUsername();
				password = getPassword();
				System.out.println("用户名： " + username + "\t密码： " + password
						+ "\t登录身份： " + identifier);
				
			}
		});
		btnLogin.setBounds(91, 273, 117, 29);
		contentPane.add(btnLogin);
		
		JLabel lblIdentifier = new JLabel("登录身份：");
		lblIdentifier.setBounds(32, 201, 70, 26);
		contentPane.add(lblIdentifier);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("管理员");
		comboBox.addItem("挂号人员");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch (e.getStateChange()) {
				case ItemEvent.SELECTED:
					identifier = (String) e.getItem();
					break;
				case ItemEvent.DESELECTED:
					break;
				}
			}
		});
		comboBox.setBounds(112, 202, 130, 27);
		contentPane.add(comboBox);
	}
	
	public String getUsername() {
		return txtUsername.getText();
	}
	
	@SuppressWarnings("deprecation")
	public String getPassword() {
		return pwdPassword.getText();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginInterface frame = new LoginInterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
