package com.hospital.register.client.gui;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.hospital.register.client.common.RegisterInfo;

public class RegisterTable extends JFrame {

	private JPanel contentPane;
	private Date date;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd    HH:mm:ss");
	private JLabel lblID;
	private JLabel lblName;
	private JLabel lblGender;
	private JLabel lblAge;
	private JLabel lblTel;
	private JLabel lblOffice;
	private JLabel lblClass;
	private JLabel lblPrice;
	private String number;
	private JPanel panel;

	public RegisterTable(RegisterInfo ri, String number) {
		
		this.number = number;
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "挂号单", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		
		JLabel label_0 = new JLabel("身份证号：");
		label_0.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblID = new JLabel("1002");
		lblID.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel label_1 = new JLabel("病人姓名：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblName = new JLabel("莫晗依");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel label_2 = new JLabel("性别：");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblGender = new JLabel("女");
		lblGender.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel label_3 = new JLabel("年龄：");
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblAge = new JLabel("21岁");
		lblAge.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel label_4 = new JLabel("联系方式：");
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblTel = new JLabel("18200531079");
		lblTel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel label_5 = new JLabel("挂号科室：");
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblOffice = new JLabel("泌尿科");
		lblOffice.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel label_6 = new JLabel("挂号类型：");
		label_6.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblClass = new JLabel("普通");
		lblClass.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel label_7 = new JLabel("价格：");
		label_7.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		lblPrice = new JLabel("5元");
		lblPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblTime = new JLabel();
		lblTime.setHorizontalAlignment(SwingConstants.TRAILING);
		date = new Date();
		String currentTime = sdf.format(date);
		lblTime.setText("时间：    " + currentTime);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_0, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblID, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblAge, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTel, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblOffice, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblClass, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addComponent(lblTime, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblID, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addComponent(label_0, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAge, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOffice, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClass, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(112)
					.addComponent(lblTime))
		);
		panel.setLayout(gl_panel);
		
		JButton btnPrint = new JButton("打印");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnPrint.gridx = 2;
		gbc_btnPrint.gridy = 1;
		contentPane.add(btnPrint, gbc_btnPrint);
		
		setInfo(ri);
	}
	
	public void setInfo(RegisterInfo ri) {
		panel.setBorder(new TitledBorder(null, "挂号单-" + number, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblID.setText(ri.getID());
		lblName.setText(ri.getName());
		lblGender.setText(ri.getGender());
		lblAge.setText(ri.getAge() + "岁");
		lblTel.setText(ri.getTelephone());
		lblOffice.setText(ri.getOffice());
		lblClass.setText(ri.getClassification());
		lblPrice.setText(ri.getPrice() + "元");
	}
}
