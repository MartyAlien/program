package com.program.userUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserPaneSon1 extends JPanel{
	private static final long serialVersionUID = -1804666L;
	private JLabel[] labels=new JLabel[4];
	private JTextField[] fields=new JTextField[5];
	private JButton payBtn,codeBtn;
	private Font font=new Font("宋体",Font.PLAIN,22);
	private Font fontField=new Font("宋体",Font.BOLD,18);
	public UserPaneSon1() {
		super(null);
		setBounds(0, 0, 900, 590);
		setBackground(Color.WHITE);
		labels=initJLabels(labels, labels.length);
		for(JLabel jLabel:labels) {
			add(jLabel);
		}
		fields=iniTextField(fields, fields.length);
		for(JTextField f:fields) {
			add(f);
		}
		add(this.payBtnSetting());
		add(this.codeBtnSetting());
	}
	public JLabel[] initJLabels(JLabel[] L,int length) {
		String[] text= {"姓名:","手机号:","验证码:","费用:"};
		L=new JLabel[length];
		for (int i = 0; i < L.length; i++) {
			L[i]=new JLabel(text[i]);
			L[i].setFont(font);
			L[i].setHorizontalAlignment(JLabel.RIGHT);
			L[i].setBounds(280,100+60*i,80,40);
		}
		return L;
	}
	public JTextField[] iniTextField(JTextField[] vFields,int length) {
		vFields=new JTextField[length];
		for (int i = 0; i < vFields.length; i++) {
			vFields[i]=new JTextField();
			if(i!=2&i!=4)
				vFields[i].setBounds(380, 100+60*i, 200, 40);
			vFields[i].setFont(fontField);
			if(i!=2) {
				vFields[i].setEditable(false);
			}
		}
		vFields[2].setBounds(380, 220, 100, 40);
		vFields[4].setBounds(480, 340, 100, 40);
		vFields[4].setText("总计");
		vFields[4].setBorder(null);
		vFields[4].setBackground(null);
		return vFields;
	}
	public JButton payBtnSetting() {
		this.payBtn=new JButton("支   付");
		payBtn.setBounds(380, 420, 130, 50);
		payBtn.setBorder(null);
		payBtn.setFocusPainted(false);
		payBtn.setBackground(new Color(103,172,80));
		payBtn.setForeground(Color.white);
		payBtn.setFont(fontField);
		return payBtn;
	}
	public JButton codeBtnSetting() {
		codeBtn=new JButton("验证码");
		codeBtn.setBounds(490, 220, 90, 40);
		codeBtn.setBorder(null);
		codeBtn.setFocusPainted(false);
		codeBtn.setBackground(new Color(103,172,180));
		codeBtn.setForeground(Color.white);
		codeBtn.setFont(new Font("",Font.PLAIN,16));
		
		return codeBtn;
	}
}
