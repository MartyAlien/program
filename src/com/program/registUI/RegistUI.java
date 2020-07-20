package com.program.registUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegistUI extends JFrame{
	private static final long serialVersionUID = -18044620L;
	private JPanel jPanel01,jPanel02,jPanel03,jPanel04,jPanel05,jPanel06,jPanel07,jPanel08;
	private JTextField field;
	private JPasswordField passwordField;
	private JCheckBox checkBox[]=new JCheckBox[3];
	private JTextArea textArea;
	private JScrollPane jScrollPane;
	private JRadioButton radioButton01,radioButton02;
	private JButton button;
	private JComboBox< String > box;
	private Font font=new Font("微软雅黑",Font.BOLD,15);
	private Font fontField=new Font("微软雅黑",Font.PLAIN,20);
	
	
	public RegistUI() throws HeadlessException {
		super("注冊表单");
		setSize(500, 535);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		getContentPane().setBackground(new Color(4,178,156));
		this.setRigistStyle();
		button.addMouseListener(new RegistListener(this,field, passwordField, checkBox, textArea, radioButton01, radioButton02,box));
		setVisible(true);
	}
	
	private void setRigistStyle() {
		// 用户名+文本框
		jPanel01=new JPanel();
		jPanel01.setLayout(null);
		jPanel01.setBackground(new Color(4,178,156));
		JLabel jLabel01=new JLabel("姓名");
		jLabel01.setBounds(100, 20, 60, 40);
		jLabel01.setFont(font);
		field=new JTextField(20);
		field.setBounds(170, 20, 200, 40);
		field.setFont(fontField);
		field.setBorder(null);
		jPanel01.add(jLabel01);
		jPanel01.add(field);
		jPanel01.setBounds(0, 0, this.getWidth(), 50);
		this.add(jPanel01);
		
		// 密码+密码框
		jPanel02=new JPanel();
		jPanel02.setLayout(null);
		jPanel02.setBackground(new Color(4,178,156));
		JLabel jLabel02=new JLabel("密码");
		jLabel02.setBounds(100, 20, 60, 40);
		jLabel02.setFont(font);
		passwordField=new JPasswordField(20);
		passwordField.setBounds(170, 20, 200, 40);
		passwordField.setFont(fontField);
		passwordField.setBorder(null);
		jPanel02.add(jLabel02); 
		jPanel02.add(passwordField);
		jPanel02.setBounds(0, 50, this.getWidth(), 50);
		this.add(jPanel02);
		
		// 性别+单选按钮
		jPanel03=new JPanel();
		jPanel03.setLayout(null);
		jPanel03.setBackground(new Color(4,178,156));
		JLabel jLabel03=new JLabel("性别");
		jLabel03.setBounds(100, 10, 60, 40);
		jLabel03.setFont(font);
		ButtonGroup buttonGroup=new ButtonGroup();
		radioButton01=new JRadioButton("男");
		radioButton01.setFocusPainted(false);
		radioButton01.setFont(font);
		radioButton01.setBackground(new Color(4,178,156));
		radioButton01.setBounds(170,20,60,20);
		radioButton02=new JRadioButton("女");
		radioButton02.setFocusPainted(false);
		radioButton02.setFont(font);
		radioButton02.setBackground(new Color(4,178,156));
		radioButton02.setBounds(240,20,60,20);
		buttonGroup.add(radioButton01);
		buttonGroup.add(radioButton02);
		radioButton01.setSelected(true);
		jPanel03.add(jLabel03);
		jPanel03.add(radioButton01);
		jPanel03.add(radioButton02);
		jPanel03.setBounds(0, 100, this.getWidth(), 50);
		this.add(jPanel03);
		
		// 爱好+复选框
		jPanel04=new JPanel();
		jPanel04.setLayout(null);
		jPanel04.setBackground(new Color(4,178,156));
		JLabel jLabel04=new JLabel("爱好");
		jLabel04.setBounds(100, 15, 60, 40);
		jLabel04.setFont(font);
		jPanel05=new JPanel();
		jPanel05.setBackground(new Color(4,178,156));
		String[] item={"编程","煲剧","游戏"};
		for(int i=0;i<checkBox.length;i++) {
			checkBox[i]=new JCheckBox(item[i]);
			checkBox[i].setFont(font);
			checkBox[i].setBackground(new Color(4,178,156));
			checkBox[i].setFocusPainted(false);
			checkBox[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			jPanel05.add(checkBox[i]);
		}
		jPanel05.setBounds(170, 15, 200, 30);
		jPanel04.add(jLabel04);
		jPanel04.add(jPanel05);
		jPanel04.setBounds(0,150,this.getWidth(),50);
		this.add(jPanel04);
		
		// 学历+下拉列表
		jPanel08=new JPanel(null);
		jPanel08.setBackground(new Color(4,178,156));
		JLabel jLabel=new JLabel("学历");
		jLabel.setBounds(100, 15, 60, 40);
		jLabel.setFont(font);
		box=new JComboBox<String>();
		box.addItem("研究生");
		box.addItem("大学本科");
		box.addItem("大学专科");
		box.addItem("高中");
		box.setSelectedIndex(1);
		box.setFont(new Font("幼圆", Font.PLAIN, 17));
		box.setBounds(170, 25, 200, 30);
		jPanel08.add(jLabel);
		jPanel08.add(box);
		jPanel08.setBounds(0, 200, this.getWidth(), 50);
		this.add(jPanel08);
		
		// 自我介绍+文本域
		jPanel06=new JPanel();
		jPanel06.setLayout(null);
		jPanel06.setBackground(new Color(4,178,156));
		JLabel jLabel05=new JLabel("简介");
		jLabel05.setBounds(100, 15, 60, 40);
		jLabel05.setFont(font);
		textArea=new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(fontField);
		textArea.setBorder(null);
		jScrollPane=new JScrollPane(textArea);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(170, 25, 200, 130);
		jPanel06.add(jLabel05);
		jPanel06.add(jScrollPane);
		jPanel06.setBounds(0, 250, this.getWidth(), 150);
		this.add(jPanel06);	
		
		// 提交按钮
		jPanel07=new JPanel();
		jPanel07.setLayout(null);
		jPanel07.setBackground(new Color(4,178,156));
		button=new JButton("注 册");
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setBackground(new Color(67,162,244));
		button.setFont(new Font("幼圆",Font.PLAIN,17));
		button.setForeground(Color.white);
		button.setBounds(220, 0, 78, 35);
		jPanel07.add(button);
		jPanel07.setBounds(0, 450, this.getWidth(), 50);
		this.add(jPanel07);
	}
}
