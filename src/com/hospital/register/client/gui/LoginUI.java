package com.hospital.register.client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class LoginUI extends JFrame {

	private LoginPanel contentPane;
	private int screenX, screenY, frameX, frameY;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginUI() {
		
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(frameX + (e.getXOnScreen() - screenX), frameY + (e.getYOnScreen() - screenY));
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				screenX = e.getXOnScreen();
				screenY = e.getYOnScreen();
				frameX = getX();
				frameY = getY();
			}
		});
		
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(451, 328);
		setLocationRelativeTo(null);
		contentPane = new LoginPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTitle = new JLabel("在线挂号系统登录");
		lblTitle.setForeground(new Color(230, 230, 250));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
		
		JButton btnExit = new JButton("");
		btnExit.setToolTipText("EXIT");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnExit.setUI(new ExitButton());
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(153, Short.MAX_VALUE)
					.addComponent(lblTitle)
					.addGap(111)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(lblTitle)))
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("用户名：");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setBounds(72, 40, 80, 30);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("密码：");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setBounds(72, 82, 80, 30);
		panel.add(lblPassword);
		
		JLabel lblID = new JLabel("登录身份：");
		lblID.setForeground(new Color(255, 255, 255));
		lblID.setFont(new Font("Arial", Font.PLAIN, 16));
		lblID.setBounds(72, 124, 80, 30);
		panel.add(lblID);
		
		textField = new JTextField();
		textField.setToolTipText("请输入用户名");
		textField.setBounds(177, 43, 170, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("请输入密码");
		passwordField.setBounds(177, 85, 170, 26);
		panel.add(passwordField);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setToolTipText("请选择身份");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"---选择身份---", "管理员", "挂号人员"}));
		comboBox.setBounds(177, 128, 170, 26);
		panel.add(comboBox);
		
		JButton btnLogin = new JButton("登录");
		btnLogin.setToolTipText("登录");
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnLogin.setBounds(162, 188, 117, 40);
		panel.add(btnLogin);
		contentPane.setLayout(gl_contentPane);
	}
}
