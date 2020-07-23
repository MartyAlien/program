package com.program.userUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.program.daoimp.PaymentDAOImp;
import com.program.payment.Payment;
import com.program.user.User;

public class UserPaneSon1 extends JPanel{
	private static final long serialVersionUID = -1804666L;
	private JLabel[] labels=new JLabel[4];
	private JTextField[] fields=new JTextField[5];
	private JComboBox<String> willPayBox;
	private List<Payment> pList;
	private JButton payBtn,codeBtn;
	private Font font=new Font("宋体",Font.PLAIN,22);
	private Font fontField=new Font("宋体",Font.BOLD,18);
	private User user;
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
	public UserPaneSon1(User user) {
		this();
		this.user=user;
		fields[0].setText("*"+user.getName());
		fields[1].setText("*"+user.getPhone());
		add(this.initBox());
	}
	
	
	public JLabel[] initJLabels(JLabel[] L,int length) {
		String[] text= {"姓名:","手机号:","验证码:","待付费:"};
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
			if(i!=2&i!=3&i!=4)
				vFields[i].setBounds(380, 100+60*i, 200, 40);
			vFields[i].setFont(fontField);
			if(i!=2) {
				vFields[i].setEditable(false);
			}
		}
		vFields[2].setBounds(380, 220, 100, 40);
		vFields[3].setBounds(540, 280, 250, 40);
		vFields[4].setBounds(480, 340, 100, 40);
		vFields[3].setBorder(null);
		vFields[3].setBackground(null);
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
	public JComboBox<String> initBox() {
		this.willPayBox=new JComboBox<String>();
		willPayBox.setBounds(380, 280, 150, 40);
		willPayBox.setFont(new Font("",Font.PLAIN,16));
		willPayBox.addItemListener(getItemListener());
		fields[3].setFont(new Font("",Font.PLAIN,16));
		List<Payment> noPayList = getNoPayList();
		if(noPayList.size()<=0) {
			willPayBox.addItem("暂无欠费业单");
		}else {
			for(Payment p:noPayList) {
				willPayBox.addItem("账单编号: "+p.getID());
			}
		}
		return willPayBox;
	}
	public List<Payment> getNoPayList() {
		List<Payment> selectForNoPay = (List<Payment>)new PaymentDAOImp().selectForNoPay(user.getUserName());
		return selectForNoPay;
	}
	public ItemListener getItemListener() {
		ItemListener itemListener=new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==e.SELECTED) {
					if(willPayBox.getSelectedItem().toString().equals("暂无欠费业单")) {
						payBtn.setBackground(Color.DARK_GRAY);
						codeBtn.setBackground(Color.DARK_GRAY);
						payBtn.setEnabled(false);// 没有欠费单的话 按钮无法使用
						codeBtn.setEnabled(false);
						fields[3].setText("您的物业费用已缴过费了！");
						fields[4].setText("");
					}else {
						payBtn.setEnabled(true);
						codeBtn.setEnabled(true);
						String it=willPayBox.getSelectedItem().toString();
						int payID=Integer.parseInt(it.substring(6, it.length()));
						List<Payment> list = getNoPayList();
						for(Payment np:list) {
							if(np.getID()==payID) {
								fields[3].setText("设备检查费:"+np.getCheckEXP()+"元 清洁费:"+np.getCleanEXP()+"元 停车费:"+np.getParkEXP());
								fields[4].setText("总计："+(np.getCheckEXP()+np.getCleanEXP()+np.getParkEXP())+"元");
							}
						}
					}
				}
			}
		};
		return itemListener;
	}
	public JTextField[] getFields() {
		return fields;
	}
	public void setFields(JTextField[] fields) {
		this.fields = fields;
	}
	public JComboBox<String> getWillPayBox() {
		return willPayBox;
	}
	public void setWillPayBox(JComboBox<String> willPayBox) {
		this.willPayBox = willPayBox;
	}
	public List<Payment> getpList() {
		return pList;
	}
	public void setpList(List<Payment> pList) {
		this.pList = pList;
	}
	public JButton getPayBtn() {
		return payBtn;
	}
	public void setPayBtn(JButton payBtn) {
		this.payBtn = payBtn;
	}
	public JButton getCodeBtn() {
		return codeBtn;
	}
	public void setCodeBtn(JButton codeBtn) {
		this.codeBtn = codeBtn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
