package com.hospital.register.client.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.hospital.register.client.common.PatientCheckInfo;

import java.awt.GridLayout;
import javax.swing.JList;
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

public class AdminMainUI extends JFrame {

	private MainPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel_1, panel_2;
	private JTextField txtDate;
	private JTable table;
	private List<PatientCheckInfo> patients = new ArrayList<PatientCheckInfo>();
	
	public AdminMainUI() {
		
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
		txtDate.setText("2016-12-15");
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
		
		JButton btnCheck = new JButton("查询");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCheck.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnCheck.setBounds(881, 46, 117, 37);
		panel_1.add(btnCheck);
		
		
		
		Vector<String> colHeader = new Vector<String>();  
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
        
        PatientCheckInfo pci = new PatientCheckInfo("900657579795", "郭鑫", "男", "20", "18030647761", "泌尿科", "专家", "50", "2016-12-14", "初诊", "莫晗依");
        PatientCheckInfo pci2 = new PatientCheckInfo("900617494390", "陈炎", "男", "20", "15928924546", "耳鼻喉科" , "普通", "10", "2016-12-15", "复诊", "郭鑫");
        patients.add(pci);
        patients.add(pci2);
        
        Vector<Vector<String>> dataVec = new Vector<Vector<String>>();  
        for (int i = 0; i < patients.size(); i++) {
        	PatientCheckInfo patient = patients.get(i);
        	Vector<String> row = new Vector<String>();
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
        
        
//        Vector<String> row2 = new Vector<String>();  
//        row2.add("0002");  
//        row2.add("小强");  
//        row2.add("男");  
//        row2.add(new Date().toString());  
//        Vector<String> row3 = new Vector<String>();  
//        row3.add("0003");  
//        row3.add("韦小宝");  
//        row3.add("女");  
//        row3.add(new Date().toString());  
//        Vector<String> row4 = new Vector<String>();  
//        row4.add("0004");  
//        row4.add("零零七");  
//        row4.add("男");  
//        row4.add(new Date().toString());  
//            
//        dataVec.add(row2);  
//        dataVec.add(row3);  
//        dataVec.add(row4);  
        
        JTable table = new JTable(dataVec,colHeader);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setBackground(new Color(230, 230, 250));
        table.setFont(new Font("Arial", Font.PLAIN, 17));
        JScrollPane scrollPane = new JScrollPane(); 
        scrollPane.setBounds(69, 113, 929, 427);
        scrollPane.setViewportView(table);  
        panel_1.add(scrollPane);
		
      //设置列宽  
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
		
		JTextField txtPass = new JTextField();
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
		
		JTextField txtConfirm = new JTextField();
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
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 18));
		btnSubmit.setBounds(487, 304, 93, 40);
		panel_2.add(btnSubmit);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainUI frame = new AdminMainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
