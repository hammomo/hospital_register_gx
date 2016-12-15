package com.hospital.register.client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;

import com.hospital.register.client.common.RegisterInfo;
import com.hospital.register.client.common.UserClient;

import javax.swing.JPasswordField;

public class RegisterMainUI extends JFrame {

	private MainPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel_1, panel_2, panel_3;
	
	private ButtonGroup bgGender;
	private ButtonGroup bgClass;
	
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtTel;
	private JTextField txtPrice_1;
	private JTextField txtPrice_2;
	private JComboBox<String> comboBox_1, comboBox_2;
	private JRadioButton rClass, rClass_1, rClass2, rClass_2, rClass_12, rClass_22;
	private JRadioButton rMale;
	private JTextField txtID;
	
	private JRadioButton rFemale;
	private JTextField textField;
	private JLabel lblTitle;
	private JLabel lblIDName_1, lblID;
	private JLabel lblName_1;
	private JLabel lblGender_1,lblGender_2;
	private JLabel lblAge_1, lblAge_2;
	private JLabel lblTel_1, lblTel_2;
	private JLabel lblOffice_1;
	private JLabel lblOffice_2;
	private JLabel lblClass_1;
	private JLabel lblClass_2;
	private JLabel lblPrice_1;
	private JLabel lblPrice_2;
	private JLabel lblName_2;
	
	private int screenX, screenY, frameX, frameY;
	public String name, gender, number, age, price,  telephone, office, classification;
	private JPasswordField pwdOldpassword, pwdNewpassword, pwdConfirm;
	
	private Socket socket;
	private UserClient uc;
	private RegisterInfo ri;
	private RegisterInfo secondRegister = null;
	private String name_1, name_2, name_3, name_4, name_5;
	private String oldPass, newPass_1, newPass_2;
	
	public RegisterMainUI(String id, Socket socket) {
//		try {
//			UIManager.setLookAndFeel(new NimbusLookAndFeel());
//		} catch (UnsupportedLookAndFeelException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		this.socket = socket;
		System.out.println(socket.getInetAddress().toString() + socket.getLocalPort());
		uc = new UserClient(socket, id);
		
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
		setBackground(new Color(255, 255, 255, 0));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 700);
		setLocationRelativeTo(null);
		contentPane = new MainPanel();
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("在线挂号系统");
		label.setForeground(new Color(106, 90, 205));
		label.setFont(new Font("Arial", Font.BOLD, 20));
		label.setBounds(490, 16, 120, 24);
		contentPane.add(label);
		
		JButton btnExit = new JButton();
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				uc.closeResources();
				System.out.println("I've closed resources and sent close information to server...");
				System.exit(0);
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblID = new JLabel("挂号员ID：");
		lblID.setText(lblID.getText() + id);
		lblID.setFont(new Font("Arial", Font.PLAIN, 15));
		lblID.setBounds(21, 16, 158, 24);
		contentPane.add(lblID);
		btnExit.setUI(new ExitButton());
		btnExit.setToolTipText("EXIT");
		btnExit.setBounds(1060, 6, 35, 35);
		contentPane.add(btnExit);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 49, 1088, 645);
		contentPane.add(tabbedPane);
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(null, "\u521D\u8BCA\u6302\u53F7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("初诊挂号", null, panel_1, null);
		panel_1.setLayout(null);
		
		lblTitle = new JLabel("挂号单填写");
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(221, 160, 221));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 19));
		lblTitle.setBounds(292, 61, 482, 53);
		panel_1.add(lblTitle);
		
		createFirstUI();
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u590D\u8BCA\u6302\u53F7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setOpaque(false);
		tabbedPane.addTab("复诊挂号", null, panel_2, null);
		panel_2.setLayout(null);
		
		createSecondUI();
	
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u4FEE\u6539\u5BC6\u7801", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setOpaque(false);
		tabbedPane.addTab("修改密码", null, panel_3, null);
		panel_3.setLayout(null);
		
		createThirdUI();
	}
	
	public void createThirdUI() {
		pwdOldpassword = new JPasswordField();
		pwdOldpassword.setText("");
		pwdOldpassword.setBounds(469, 114, 270, 40);
		panel_3.add(pwdOldpassword);
		
		JLabel lblOldPassword = new JLabel("    请输入旧密码：");
		lblOldPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		lblOldPassword.setOpaque(true);
		lblOldPassword.setBackground(new Color(221, 160, 221));
		lblOldPassword.setBounds(292, 114, 482, 40);
		panel_3.add(lblOldPassword);
		
		pwdNewpassword= new JPasswordField();
		pwdNewpassword.setText("");
		pwdNewpassword.setBounds(469, 154, 270, 40);
		panel_3.add(pwdNewpassword);
		
		JLabel lblNewPassword = new JLabel("    请输入新密码：");
		lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewPassword.setOpaque(true);
		lblNewPassword.setBackground(new Color(255, 240, 245));
		lblNewPassword.setBounds(292, 154, 482, 40);
		panel_3.add(lblNewPassword);
		
		pwdConfirm= new JPasswordField();
		pwdConfirm.setText("");
		pwdConfirm.setBounds(469, 194, 270, 40);
		panel_3.add(pwdConfirm);
		
		JLabel lblConfirm = new JLabel("    请确认新密码：");
		lblConfirm.setFont(new Font("Arial", Font.PLAIN, 18));
		lblConfirm.setOpaque(true);
		lblConfirm.setBackground(new Color(221, 160, 221));
		lblConfirm.setBounds(292, 194, 482, 40);
		panel_3.add(lblConfirm);
		
		JButton btnSubmit_3 = new JButton("确认修改");
		btnSubmit_3.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				oldPass = pwdOldpassword.getText();
				newPass_1 = pwdNewpassword.getText();
				newPass_2 = pwdConfirm.getText();
				if (!newPass_1.equals(newPass_2)) {
					System.out.println("两个密码不相同！");
					JOptionPane.showMessageDialog(null, "两个密码不相同！", "提示", JOptionPane.ERROR_MESSAGE);
				} else {
					boolean result = uc.updatePassword(oldPass, newPass_1);
					if (result) {
						JOptionPane.showMessageDialog(null, "密码修改成功！", "提示", JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(null, "输入的旧密码错误！", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnSubmit_3.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit_3.setBounds(487, 254, 93, 50);
		panel_3.add(btnSubmit_3);
	}
	
	public void createFirstUI() {
		
		txtID = new JTextField();
		txtID.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtID.setColumns(10);
		txtID.setBounds(469, 114, 270, 40);
		panel_1.add(txtID);

		lblIDName_1 = new JLabel("               身份证号：");
		lblIDName_1.setBounds(292, 114, 482, 40);
		panel_1.add(lblIDName_1);
		lblIDName_1.setBackground(new Color(255, 240, 245));
		lblIDName_1.setOpaque(true);
		lblIDName_1.setFont(new Font("Arial", Font.PLAIN, 18));
		
		txtName = new JTextField();
		txtName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtName.setBounds(469, 155, 214, 40);
		panel_1.add(txtName);
		txtName.setColumns(10);
		
		lblName_1 = new JLabel("               病人姓名：");
		lblName_1.setOpaque(true);
		lblName_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblName_1.setBackground(new Color(221, 160, 221));
		lblName_1.setBounds(292, 154, 482, 40);
		panel_1.add(lblName_1);
		
		rMale = new JRadioButton("男");
		rMale.setSelected(true);
		rMale.setFont(new Font("Arial", Font.PLAIN, 16));
		rMale.setBounds(469, 200, 68, 32);
		panel_1.add(rMale);
		
		rFemale = new JRadioButton("女");
		rFemale.setFont(new Font("Arial", Font.PLAIN, 16));
		rFemale.setBounds(527, 200, 68, 32);
		panel_1.add(rFemale);
		
		bgGender = new ButtonGroup();
		bgGender.add(rMale);
		bgGender.add(rFemale);
		
		lblGender_1 = new JLabel("                      性别：");
		lblGender_1.setOpaque(true);
		lblGender_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblGender_1.setBackground(new Color(255, 240, 245));
		lblGender_1.setBounds(292, 194, 482, 40);
		panel_1.add(lblGender_1);
		
		txtAge = new JTextField();
		txtAge.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtAge.setColumns(10);
		txtAge.setBounds(469, 235, 214, 40);
		panel_1.add(txtAge);
		
		lblAge_1 = new JLabel("                     年龄：                                               岁");
		lblAge_1.setOpaque(true);
		lblAge_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAge_1.setBackground(new Color(221, 160, 221));
		lblAge_1.setBounds(292, 234, 482, 40);
		panel_1.add(lblAge_1);
		
		txtTel = new JTextField();
		txtTel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtTel.setColumns(10);
		txtTel.setBounds(469, 274, 270, 40);
		panel_1.add(txtTel);
		
		lblTel_1 = new JLabel("              联系方式：");
		lblTel_1.setOpaque(true);
		lblTel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTel_1.setBackground(new Color(255, 240, 245));
		lblTel_1.setBounds(292, 274, 482, 40);
		panel_1.add(lblTel_1);
		

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		comboBox_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"---请选择---", "妇科", "儿科", "耳鼻喉科", "肠胃科", "泌尿科", "生殖科", "皮肤科"}));
		comboBox_1.setBounds(469, 314, 174, 40);
		panel_1.add(comboBox_1);
		
		lblOffice_1 = new JLabel("              挂号科室：");
		lblOffice_1.setOpaque(true);
		lblOffice_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblOffice_1.setBackground(new Color(221, 160, 221));
		lblOffice_1.setBounds(292, 314, 482, 40);
		panel_1.add(lblOffice_1);
		
		rClass = new JRadioButton("普通");
		rClass.setSelected(true);
		rClass.setFont(new Font("Arial", Font.PLAIN, 16));
		rClass.setBounds(469, 363, 79, 23);
		panel_1.add(rClass);
		
		rClass_1 = new JRadioButton("急诊");
		rClass_1.setFont(new Font("Arial", Font.PLAIN, 16));
		rClass_1.setBounds(548, 363, 79, 23);
		panel_1.add(rClass_1);
		
		rClass_2 = new JRadioButton("专家");
		rClass_2.setFont(new Font("Arial", Font.PLAIN, 16));
		rClass_2.setBounds(627, 363, 79, 23);
		panel_1.add(rClass_2);
		
		bgClass = new ButtonGroup();
		bgClass.add(rClass);
		bgClass.add(rClass_1);
		bgClass.add(rClass_2);
		
		lblClass_1 = new JLabel("              挂号类别：");
		lblClass_1.setOpaque(true);
		lblClass_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblClass_1.setBackground(new Color(255, 240, 245));
		lblClass_1.setBounds(292, 354, 482, 40);
		panel_1.add(lblClass_1);

		txtPrice_1 = new JTextField();
		txtPrice_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtPrice_1.setColumns(10);
		txtPrice_1.setBounds(469, 395, 214, 40);
		panel_1.add(txtPrice_1);
		
		lblPrice_1 = new JLabel("                     价格：                                               元");
		lblPrice_1.setOpaque(true);
		lblPrice_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPrice_1.setBackground(new Color(221, 160, 221));
		lblPrice_1.setBounds(292, 394, 482, 40);
		panel_1.add(lblPrice_1);
		
		JButton btnSubmit_1 = new JButton("提交");
		btnSubmit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitInfo();
				reset();
			}
		});
		btnSubmit_1.setBackground(new Color(244, 164, 96));
		btnSubmit_1.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit_1.setBounds(478, 455, 117, 46);
		panel_1.add(btnSubmit_1);
		
		
		
	}
	
	public void createSecondUI() {
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textField.setBounds(420, 61, 343, 40);
		panel_2.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) secondRegister = secondRegister();
			}
		});
		
		JLabel lblIDSearch = new JLabel("      挂号单号：");
		lblIDSearch.setFont(new Font("Arial", Font.PLAIN, 18));
		lblIDSearch.setOpaque(true);
		lblIDSearch.setBackground(new Color(221, 160, 221));
		lblIDSearch.setBounds(292, 61, 482, 40);
		panel_2.add(lblIDSearch);
		
		JButton button = new JButton("搜索");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondRegister = secondRegister();
			}
		});
		button.setFont(new Font("Arial", Font.PLAIN, 18));
		button.setBackground(new Color(244, 164, 96));
		button.setBounds(487, 113, 93, 40);
		panel_2.add(button);
		
		lblID = new JLabel("               身份证号：");
		lblID.setOpaque(true);
		lblID.setFont(new Font("Arial", Font.PLAIN, 18));
		lblID.setBackground(new Color(221, 160, 221));
		lblID.setBounds(292, 165, 482, 40);
		panel_2.add(lblID);
		name_1 = lblID.getText();
		
		lblName_2 = new JLabel("               病人姓名：");
		lblName_2.setOpaque(true);
		lblName_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblName_2.setBackground(new Color(255, 240, 245));
		lblName_2.setBounds(292, 205, 482, 40);
		panel_2.add(lblName_2);
		name_2 = lblName_2.getText();

		lblGender_2 = new JLabel("                      性别：");
		lblGender_2.setOpaque(true);
		lblGender_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblGender_2.setBackground(new Color(221, 160, 221));
		lblGender_2.setBounds(292, 245, 482, 40);
		panel_2.add(lblGender_2);
		name_3 = lblGender_2.getText();
		
		lblAge_2 = new JLabel("                     年龄：");
		lblAge_2.setOpaque(true);
		lblAge_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAge_2.setBackground(new Color(255, 240, 245));
		lblAge_2.setBounds(292, 285, 482, 40);
		panel_2.add(lblAge_2);
		name_4 = lblAge_2.getText();
		
		lblTel_2 = new JLabel("              联系方式：");
		lblTel_2.setOpaque(true);
		lblTel_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTel_2.setBackground(new Color(221, 160, 221));
		lblTel_2.setBounds(292, 325, 482, 40);
		panel_2.add(lblTel_2);
		name_5 = lblTel_2.getText();

		comboBox_2 = new JComboBox<String>();
		comboBox_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		comboBox_2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"---请选择---", "妇科", "儿科", "耳鼻喉科", "肠胃科", "泌尿科", "生殖科", "皮肤科"}));
		comboBox_2.setBounds(469, 365, 174, 40);
		panel_2.add(comboBox_2);
		
		lblOffice_2 = new JLabel("              挂号科室：");
		lblOffice_2.setOpaque(true);
		lblOffice_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblOffice_2.setBackground(new Color(255, 240, 245));
		lblOffice_2.setBounds(292, 365, 482, 40);
		panel_2.add(lblOffice_2);
		
		rClass2 = new JRadioButton("普通");
		rClass2.setSelected(true);
		rClass2.setFont(new Font("Arial", Font.PLAIN, 16));
		rClass2.setBounds(469, 415, 79, 23);
		panel_2.add(rClass2);
		
		rClass_12 = new JRadioButton("急诊");
		rClass_12.setFont(new Font("Arial", Font.PLAIN, 16));
		rClass_12.setBounds(548, 415, 79, 23);
		panel_2.add(rClass_12);
		
		rClass_22 = new JRadioButton("专家");
		rClass_22.setFont(new Font("Arial", Font.PLAIN, 16));
		rClass_22.setBounds(627, 415, 79, 23);
		panel_2.add(rClass_22);
		
		bgClass = new ButtonGroup();
		bgClass.add(rClass2);
		bgClass.add(rClass_12);
		bgClass.add(rClass_22);
		
		lblClass_2 = new JLabel("              挂号类别：");
		lblClass_2.setOpaque(true);
		lblClass_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblClass_2.setBackground(new Color(221, 160, 221));
		lblClass_2.setBounds(292, 405, 482, 40);
		panel_2.add(lblClass_2);

		txtPrice_2 = new JTextField();
		txtPrice_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtPrice_2.setColumns(10);
		txtPrice_2.setBounds(469, 445, 214, 40);
		panel_2.add(txtPrice_2);
		
		lblPrice_2 = new JLabel("                     价格：                                               元");
		lblPrice_2.setOpaque(true);
		lblPrice_2.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPrice_2.setBackground(new Color(255, 240, 245));
		lblPrice_2.setBounds(292, 445, 482, 40);
		panel_2.add(lblPrice_2);
		
		JButton btnSubmit_2 = new JButton("提交");
		btnSubmit_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 复诊挂号
				secondRegisterAction(secondRegister);
				reset_2();
			}
		});
		btnSubmit_2.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit_2.setBounds(487, 509, 93, 40);
		panel_2.add(btnSubmit_2);
	}
	
	public void submitInfo() {
		number = txtID.getText();
		name = txtName.getText();
		if (rMale.isSelected()) gender = rMale.getText();
		else gender = rFemale.getText();
		age = txtAge.getText();
		telephone = txtTel.getText();
		office = (String) comboBox_1.getSelectedItem();
		if (rClass.isSelected()) classification = rClass.getText();
		else if (rClass_1.isSelected()) classification = rClass_1.getText();
		else if (rClass_2.isSelected()) classification = rClass_2.getText();
		price = txtPrice_1.getText();
		System.out.println("------所有病人信息------");
		System.out.println("病志号： " + number);
		System.out.println("病人姓名： " + name);
		System.out.println("性别： " + gender);
		System.out.println("年龄： " + age);
		System.out.println("联系方式： " + telephone);
		System.out.println("科室： " + office);
		System.out.println("类型： " + classification);
		System.out.println("价格： " + price);
		ri = new RegisterInfo(number, name, gender, age, telephone, office, classification, price);
		String number = uc.sendPatientInfo_1(ri);
		System.out.println(ri.getName() + "的挂号单号为" + number);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterTable frame = new RegisterTable(ri, number);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void reset() {
		txtID.setText("");
		txtName.setText("");
		rMale.setSelected(true);
		txtAge.setText("");
		txtTel.setText("");
		comboBox_1.setSelectedIndex(0);
		rClass.setSelected(true);
		txtPrice_1.setText("");
	}
	
	public void reset_2() {
		textField.setText("");
		lblID.setText(name_1);
		lblName_2.setText(name_2);
		lblGender_2.setText(name_3);
		lblAge_2.setText(name_4);
		lblTel_2.setText(name_5);
		comboBox_2.setSelectedIndex(0);
		rClass2.setSelected(true);
		txtPrice_2.setText("");
	}
	
	public RegisterInfo secondRegister() {
		String number = textField.getText();
		System.out.println(number);
		RegisterInfo secondRegister = uc.secondRegister(number);
		lblID.setText(name_1 + " " + secondRegister.getID());
		lblName_2.setText(name_2 + " " + secondRegister.getName());
		lblGender_2.setText(name_3 + " " + secondRegister.getGender());
		lblAge_2.setText(name_4 + "  " + secondRegister.getAge());
		lblTel_2.setText(name_5 + " " + secondRegister.getTelephone());
		return secondRegister;
	}
	
	public void secondRegisterAction(RegisterInfo ri) {
		String office = (String) comboBox_2.getSelectedItem();
		String classification = null;
		if (rClass2.isSelected()) classification = rClass.getText();
		else if (rClass_12.isSelected()) classification = rClass_1.getText();
		else if (rClass_22.isSelected()) classification = rClass_2.getText();
		String price = txtPrice_2.getText();
		ri.setOffice(office);
		ri.setClassification(classification);
		ri.setPrice(price);
		String number = uc.sendPatientInfo_2(ri);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterTable frame = new RegisterTable(ri, number);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}