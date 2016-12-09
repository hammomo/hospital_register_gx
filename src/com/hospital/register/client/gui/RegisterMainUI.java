package com.hospital.register.client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

public class RegisterMainUI extends JFrame {

	private MainPanel contentPane;
	private JPanel panel_1;
	private ButtonGroup bgGender;
	private ButtonGroup bgClass;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtAddress;
	private JTextField txtPrice;
	private int screenX, screenY, frameX, frameY;
	private String name, gender, id, age, price,  address, office, classification;
	private JLabel lblNumber;
	private JComboBox<String> comboBox;
	private JRadioButton rClass;
	private JRadioButton rClass_1;
	private JRadioButton rClass_2;
	private JRadioButton rMale;
	private JRadioButton rFemale;
	
	public RegisterMainUI() {
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
				System.exit(0);
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.setUI(new ExitButton());
		btnExit.setToolTipText("EXIT");
		btnExit.setBounds(1060, 6, 35, 35);
		contentPane.add(btnExit);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 49, 1088, 645);
		contentPane.add(tabbedPane);
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(null, "\u521D\u8BCA\u6302\u53F7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("初诊挂号", null, panel_1, null);
		panel_1.setLayout(null);
		createFirstRegisterUI();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u590D\u8BCA\u6302\u53F7", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setOpaque(false);
		tabbedPane.addTab("复诊挂号", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u4FEE\u6539\u5BC6\u7801", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setOpaque(false);
		tabbedPane.addTab("修改密码", null, panel_3, null);
		
		JLabel lblID = new JLabel("挂号员ID：");
		lblID.setFont(new Font("Arial", Font.PLAIN, 15));
		lblID.setBounds(21, 16, 158, 24);
		contentPane.add(lblID);
	}
	
	public void createFirstRegisterUI() {
		JLabel lblTitle = new JLabel("挂号单填写");
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(221, 160, 221));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 19));
		lblTitle.setBounds(292, 61, 482, 54);
		panel_1.add(lblTitle);
		
		JLabel lblNumberName = new JLabel("病志号：");
		lblNumberName.setBackground(new Color(255, 240, 245));
		lblNumberName.setOpaque(true);
		lblNumberName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNumberName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNumberName.setBounds(292, 114, 248, 40);
		panel_1.add(lblNumberName);
		
		lblNumber = new JLabel("1002");
		lblNumber.setOpaque(true);
		lblNumber.setBackground(new Color(255, 240, 245));
		lblNumber.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNumber.setBounds(539, 114, 235, 40);
		panel_1.add(lblNumber);
		
		txtName = new JTextField();
		txtName.setBounds(469, 155, 214, 40);
		panel_1.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblName = new JLabel("               病人姓名：");
		lblName.setOpaque(true);
		lblName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblName.setBackground(new Color(221, 160, 221));
		lblName.setBounds(292, 155, 482, 40);
		panel_1.add(lblName);
		
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
		
		JLabel lblGender = new JLabel("                      性别：");
		lblGender.setOpaque(true);
		lblGender.setFont(new Font("Arial", Font.PLAIN, 18));
		lblGender.setBackground(new Color(255, 240, 245));
		lblGender.setBounds(292, 195, 482, 40);
		panel_1.add(lblGender);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(469, 235, 214, 40);
		panel_1.add(txtAge);
		
		JLabel lblAge = new JLabel("                     年龄：                                               岁");
		lblAge.setOpaque(true);
		lblAge.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAge.setBackground(new Color(221, 160, 221));
		lblAge.setBounds(292, 235, 482, 40);
		panel_1.add(lblAge);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(469, 274, 270, 40);
		panel_1.add(txtAddress);
		
		JLabel lblAddress = new JLabel("              家庭住址：");
		lblAddress.setOpaque(true);
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAddress.setBackground(new Color(255, 240, 245));
		lblAddress.setBounds(292, 274, 482, 40);
		panel_1.add(lblAddress);
		

		comboBox = new JComboBox<String>();
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"-----请选择-----", "妇科", "儿科", "耳鼻喉科", "肠胃科", "泌尿科", "生殖科", "皮肤科"}));
		comboBox.setBounds(469, 314, 174, 40);
		panel_1.add(comboBox);
		
		JLabel lblOffice = new JLabel("              挂号科室：");
		lblOffice.setOpaque(true);
		lblOffice.setFont(new Font("Arial", Font.PLAIN, 18));
		lblOffice.setBackground(new Color(221, 160, 221));
		lblOffice.setBounds(292, 314, 482, 40);
		panel_1.add(lblOffice);
		
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
		
		JLabel lblClass = new JLabel("              挂号类别：");
		lblClass.setOpaque(true);
		lblClass.setFont(new Font("Arial", Font.PLAIN, 18));
		lblClass.setBackground(new Color(255, 240, 245));
		lblClass.setBounds(292, 354, 482, 40);
		panel_1.add(lblClass);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(469, 395, 214, 40);
		panel_1.add(txtPrice);
		
		JLabel label_1 = new JLabel("                     价格：                                               元");
		label_1.setOpaque(true);
		label_1.setFont(new Font("Arial", Font.PLAIN, 18));
		label_1.setBackground(new Color(221, 160, 221));
		label_1.setBounds(292, 395, 482, 40);
		panel_1.add(label_1);
		
		JButton btnNewButton = new JButton("提交");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitInfo();
			}
		});
		btnNewButton.setBackground(new Color(244, 164, 96));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 18));
		btnNewButton.setBounds(478, 455, 117, 46);
		panel_1.add(btnNewButton);
	}
	
	public void submitInfo() {
		id = lblNumber.getText();
		name = txtName.getText();
		if (rMale.isSelected()) gender = rMale.getText();
		else gender = rFemale.getText();
		age = txtAge.getText();
		address = txtAddress.getText();
		office = (String) comboBox.getSelectedItem();
		if (rClass.isSelected()) classification = rClass.getText();
		else if (rClass_1.isSelected()) classification = rClass_1.getText();
		else classification = rClass_2.getText();
		price = txtPrice.getText();
		System.out.println("------所有病人信息------");
		System.out.println("病志号： " + id);
		System.out.println("病人姓名： " + name);
		System.out.println("性别： " + gender);
		System.out.println("年龄： " + age);
		System.out.println("家庭住址： " + address);
		System.out.println("科室： " + office);
		System.out.println("类型： " + classification);
		System.out.println("价格： " + price);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterMainUI frame = new RegisterMainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
