package com.hospital.register.client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.Socket;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.hospital.register.client.common.AdminClient;
import com.hospital.register.client.common.LoginClient;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LoginUI extends JFrame {

	private LoginPanel contentPane;
	private int screenX, screenY, frameX, frameY;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private String username, password, id;
	private JComboBox<String> comboBox;

	public LoginUI() {
		
//		try {
//			UIManager.setLookAndFeel(new NimbusLookAndFeel());
//		} catch (UnsupportedLookAndFeelException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
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
		
		JLabel lblTitle =new JLabel("在线挂号系统登录");
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(147, Short.MAX_VALUE)
							.addComponent(lblTitle)
							.addGap(104))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(lblTitle)
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(286, Short.MAX_VALUE))
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
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("请输入用户名");
		txtUsername.setBounds(177, 43, 170, 26);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setToolTipText("请输入密码");
		pwdPassword.setBounds(177, 85, 170, 26);
		panel.add(pwdPassword);
		
		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("请选择身份");
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"---选择身份---", "管理员", "挂号人员"}));
		comboBox.setBounds(177, 128, 170, 26);
		panel.add(comboBox);
		
		JButton btnLogin = new JButton("登录");
		btnLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				loginAction();
			}
		});
		btnLogin.setToolTipText("登录");
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnLogin.setBounds(162, 188, 117, 40);
		panel.add(btnLogin);
		contentPane.setLayout(gl_contentPane);
	}
	
	@SuppressWarnings("deprecation")
	public void loginAction() {
		username = txtUsername.getText();
		password = pwdPassword.getText();
		id = (String) comboBox.getSelectedItem();
		if (username == null || password == null || id.equals("---选择身份---")) System.out.println("you idiot!!!");
		else System.out.println("Username: " + username + " Password: " + password + " id: " + id);
		if (id.equals("管理员")) {
			// 进入管理员界面
			LoginClient lc = new LoginClient(username, password, id);
			lc.openConnection();
			lc.sendUserInfo();
			lc.getServerRequest();
			Socket socket = lc.getSocket();
			if (lc.getLoginResult()) {
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AdminMainUI adminGUI = new AdminMainUI(new AdminClient(socket));
							adminGUI.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} else {
				JOptionPane.showMessageDialog(null, "管理员登录失败", "提示", JOptionPane.ERROR_MESSAGE);
				txtUsername.setText("");
				pwdPassword.setText("");
			}
			
		} else if (id.equals("挂号人员")) {
			// 进入挂号人员界面
			LoginClient lc = new LoginClient(username, password, id);
			lc.openConnection();
			lc.sendUserInfo();
			lc.getServerRequest();
			String userID = lc.getUserID();
			System.out.println(userID);
			Socket socket = lc.getSocket();
			if (lc.getLoginResult()) {
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							RegisterMainUI register = new RegisterMainUI(userID, socket);
							register.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} else {
				JOptionPane.showMessageDialog(null, "挂号人员登录失败", "提示", JOptionPane.ERROR_MESSAGE);
				txtUsername.setText("");
				pwdPassword.setText("");
			}
		}
	}
	
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
	
}
