package com.hospital.register.client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.hospital.register.client.common.AdminClient;
import com.hospital.register.client.common.PatientCheckInfo;

import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.AbstractListModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class AdminMainUI extends JFrame {

	private MainPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel_1, panel_2;
	private JTextField txtDate;
	private JTable table;
//	private List<PatientCheckInfo> patients = new ArrayList<PatientCheckInfo>();

	private AdminClient ac;
	private Vector<String> colHeader;
	private Vector<Vector<String>> dataVec;
	private List<PatientCheckInfo> patients;
	
	private int screenX, screenY, frameX, frameY;
	
	public AdminMainUI(AdminClient ac) {
		
		this.ac = ac;
		
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
				// 客户端退出逻辑
				ac.closeResources();
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
		
		JLabel lblID = new JLabel("管理员身份");
		lblID.setFont(new Font("Arial", Font.PLAIN, 15));
		lblID.setBounds(21, 16, 158, 24);
		contentPane.add(lblID);
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 49, 1088, 645);
		contentPane.add(tabbedPane);
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBorder(new TitledBorder(null, "信息统计", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("信息统计", null, panel_1, null);
		
		JLabel lblDate = new JLabel("日期(yyyy-MM-dd格式)：");
		lblDate.setBounds(69, 53, 200, 22);
		lblDate.setFont(new Font("Arial", Font.PLAIN, 18));
		
		txtDate = new JTextField();
		txtDate.setBounds(275, 47, 181, 32);
		txtDate.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtDate.setText("2016-12-17");
		txtDate.setColumns(10);
		
		JLabel lblOffice = new JLabel("科室：");
		lblOffice.setBounds(474, 53, 54, 22);
		lblOffice.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JComboBox<String> cbOffice = new JComboBox<String>();
		cbOffice.setBounds(535, 47, 158, 32);
		cbOffice.setPreferredSize(new Dimension(52, 40));
		cbOffice.setModel(new DefaultComboBoxModel<String>(new String[] {"所有科室", "妇科", "儿科", "耳鼻喉科", "肠胃科", "泌尿科", "生殖科", "皮肤科", "妇产科", "普通内科", "普通外科"}));
		cbOffice.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel_1.setLayout(null);
		panel_1.add(lblDate);
		panel_1.add(txtDate);
		panel_1.add(lblOffice);
		panel_1.add(cbOffice);
		
		JCheckBox c1 = new JCheckBox("初诊");
		c1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		c1.setBounds(722, 47, 72, 32);
		panel_1.add(c1);
		
		JCheckBox c2 = new JCheckBox("复诊");
		c2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		c2.setBounds(797, 47, 72, 32);
		panel_1.add(c2);
		
		// 设置表格列名
		colHeader = new Vector<String>();  
        colHeader.add("病志号");  
        colHeader.add("病人姓名");  
        colHeader.add("性别");  
        colHeader.add("年龄");
        colHeader.add("联系方式");
        colHeader.add("挂号科室");
        colHeader.add("挂号类别");
        colHeader.add("价格");
        colHeader.add("初复诊");
        colHeader.add("挂号人员");
		        
        
        
		JButton btnCheck = new JButton("查询");
		btnCheck.setFocusable(false);
		btnCheck.setFocusTraversalKeysEnabled(false);
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 查询
				// 设置表格行数据
		        dataVec = new Vector<Vector<String>>();
				patients = new ArrayList<PatientCheckInfo>();
				String date = txtDate.getText();
				String office = cbOffice.getSelectedItem().toString();
				int select = 0;
				if (c1.isSelected() && !c2.isSelected()) select = 1;
				if (!c1.isSelected() && c2.isSelected()) select = 2;
				if (c1.isSelected() && c2.isSelected() || !c1.isSelected() && !c2.isSelected()) select = 3;
				if (select == 1) {
					System.out.println("查询日期：" + date + " 查询科室：" + office + " 初／复诊：初诊");
				} else if (select == 2) {
					System.out.println("查询日期：" + date + " 查询科室：" + office + " 初／复诊：复诊");
				} else if (select == 3) {
					System.out.println("查询日期：" + date + " 查询科室：" + office + " 初／复诊：全部");
				} else {
					System.out.println("错误");
				}
				List<PatientCheckInfo> list = ac.sendCheckInfo(date, office, select);
				for (int i = 0; i < list.size(); i++) {
					patients.add(list.get(i));
				}
				createTable();
			}
		});
		btnCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnCheck.setBounds(881, 46, 117, 37);
		panel_1.add(btnCheck);
		
		table = new JTable(dataVec,colHeader);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setBackground(new Color(230, 230, 250));
        table.setFont(new Font("Arial", Font.PLAIN, 17));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(69, 113, 929, 427);
        scrollPane.setViewportView(table);  
        panel_1.add(scrollPane);
		
        // 设置表格格式
        table.getColumnModel().getColumn(0).setPreferredWidth(140);  
        table.getColumnModel().getColumn(1).setPreferredWidth(65);  
        table.getColumnModel().getColumn(2).setPreferredWidth(50);  
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(110);
        table.getColumnModel().getColumn(5).setPreferredWidth(65);
        table.getColumnModel().getColumn(6).setPreferredWidth(50);
        table.getColumnModel().getColumn(7).setPreferredWidth(50);
        table.getColumnModel().getColumn(8).setPreferredWidth(50);
        table.getColumnModel().getColumn(9).setPreferredWidth(50);
        table.setRowHeight(24);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "增添用户", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setOpaque(false);
		tabbedPane.addTab("增添用户", null, panel_2, null);
		panel_2.setLayout(null);
		
		createSecondUI();
	}
	
	public void createSecondUI() {
		JTextField txtUsername = new JTextField();
		txtUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtUsername.setColumns(10);
		txtUsername.setBounds(450, 114, 270, 40);
		panel_2.add(txtUsername);
		
		JLabel lblUsername = new JLabel("            用户名：");
		lblUsername.setBounds(292, 114, 482, 40);
		lblUsername.setBackground(new Color(221, 160, 221));
		lblUsername.setOpaque(true);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_2.add(lblUsername);
		
		JPasswordField txtPass = new JPasswordField();
		txtPass.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtPass.setColumns(10);
		txtPass.setBounds(450, 154, 270, 40);
		panel_2.add(txtPass);
		
		JLabel lblPass = new JLabel("               密码：");
		lblPass.setBounds(292, 154, 482, 40);
		lblPass.setBackground(new Color(255, 240, 245));
		lblPass.setOpaque(true);
		lblPass.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_2.add(lblPass);
		
		JPasswordField txtConfirm = new JPasswordField();
		txtConfirm.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtConfirm.setColumns(10);
		txtConfirm.setBounds(450, 194, 270, 40);
		panel_2.add(txtConfirm);
		
		JLabel lblConfirm = new JLabel("        确认密码：");
		lblConfirm.setBounds(292, 194, 482, 40);
		lblConfirm.setBackground(new Color(221, 160, 221));
		lblConfirm.setOpaque(true);
		lblConfirm.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_2.add(lblConfirm);
		
		JTextField txtTruename = new JTextField();
		txtTruename.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtTruename.setColumns(10);
		txtTruename.setBounds(450, 234, 270, 40);
		panel_2.add(txtTruename);
		
		JLabel lblTruename = new JLabel("        真实姓名：");
		lblTruename.setBounds(292, 234, 482, 40);
		lblTruename.setBackground(new Color(255, 240, 245));
		lblTruename.setOpaque(true);
		lblTruename.setFont(new Font("Arial", Font.PLAIN, 18));
		panel_2.add(lblTruename);
		
		JButton btnSubmit = new JButton("提交");
		btnSubmit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// 增加用户
				String username = txtUsername.getText();
				String password = txtPass.getText();
				String confirm = txtConfirm.getText();
				String truename = txtTruename.getText();
				if (!password.equals(confirm)) {
					JOptionPane.showMessageDialog(null, "两个密码不相同！", "提示", JOptionPane.ERROR_MESSAGE);
					txtPass.setText("");
					txtConfirm.setText("");
				} else {
					boolean result = ac.createNewUser(username, password, truename);
					if (result) {
						JOptionPane.showMessageDialog(null, "创建成功！", "提示", JOptionPane.DEFAULT_OPTION);
					} else {
						JOptionPane.showMessageDialog(null, "创建失败！", "提示", JOptionPane.ERROR_MESSAGE);
					}
				}
				txtUsername.setText("");
				txtPass.setText("");
				txtConfirm.setText("");
				txtTruename.setText("");
			}
		});
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit.setBounds(487, 304, 93, 40);
		panel_2.add(btnSubmit);
	}
	
	public void createTable() {
		for (int i = 0; i < patients.size(); i++) {
			System.out.println(i);
        	PatientCheckInfo patient = patients.get(i);
        	Vector<String> row = new Vector<String>();
        	System.out.println(patient.getNumber() + patient.getName() + patient.getGender() + patient.getAge() + patient.getTelephone() + patient.getOffice() + patient.getClassification() + patient.getPrice() + patient.getType() + patient.getUsername());
        	row.add(patient.getNumber());  
            row.add(patient.getName());  
            row.add(patient.getGender());
            row.add(patient.getAge());
            row.add(patient.getTelephone());
            row.add(patient.getOffice());
            row.add(patient.getClassification());
            row.add(patient.getPrice());
            row.add(patient.getType());
            row.add(patient.getUsername());
            dataVec.add(row);
        }
		
		table = new JTable(dataVec,colHeader);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setBackground(new Color(230, 230, 250));
        table.setFont(new Font("Arial", Font.PLAIN, 17));
        JScrollPane scrollPane = new JScrollPane(); 
        scrollPane.setBounds(69, 113, 929, 427);
        scrollPane.setViewportView(table);  
        panel_1.add(scrollPane);
		
        // 设置表格格式
        table.getColumnModel().getColumn(0).setPreferredWidth(140);  
        table.getColumnModel().getColumn(1).setPreferredWidth(65);  
        table.getColumnModel().getColumn(2).setPreferredWidth(50);  
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(110);
        table.getColumnModel().getColumn(5).setPreferredWidth(65);
        table.getColumnModel().getColumn(6).setPreferredWidth(50);
        table.getColumnModel().getColumn(7).setPreferredWidth(50);
        table.getColumnModel().getColumn(8).setPreferredWidth(50);
        table.getColumnModel().getColumn(9).setPreferredWidth(50);
        table.setRowHeight(24);
	}
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AdminMainUI frame = new AdminMainUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
