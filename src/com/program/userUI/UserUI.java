package com.program.userUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.program.daoimp.UserDAOImp;
import com.program.masterUI.TabbedPaneDef;
import com.program.payment.Payment;
import com.program.user.User;

public class UserUI extends JFrame{
	private static final long serialVersionUID = -2001111L;
	Color color=new Color(191,230,240);
	private JTabbedPane uTabbedPane;
	private JPanel jPanel01,jPanel02,jPanel03;
	private JPanel headPanel;
	private List<Payment> lists;
	private JButton miniBtn,closeBtn;
	private User user;
	private UserPaneSon1 paneSon1;
	private UserPaneSon2 paneSon2;
	private UserPaneSon3 paneSon3;
	
	public UserUI(User user) throws HeadlessException {
		this.user = user;
		setSize(900, 700);
		setLayout(null);
		setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setBackground(color);
	    setUndecorated(true);
	    setResizable(false);
	    initHeadPane();
	    add(initJTabbedPane());
	    new UserListener(this);
	    
	    setVisible(true);
	}

	public void initHeadPane() {
		headPanel=new JPanel(null);
        headPanel.setBounds(0, 0, 900, 60);
        headPanel.setBackground(Color.white);
        
        miniBtn=new JButton();
        closeBtn=new JButton();
        miniBtn.setBounds(786,5,50,50);
        closeBtn.setBounds(845,5,50,50);
        miniBtn.setFocusPainted(false);
        closeBtn.setFocusPainted(false);
        miniBtn.setBorder(null);
        closeBtn.setBorder(null);
        miniBtn.setBackground(null);
        closeBtn.setBackground(null);
        
        miniBtn.setIcon(new ImageIcon("mstUI_img/mini2.png"));
        miniBtn.setRolloverIcon(new ImageIcon("mstUI_img/mini1.png"));
        miniBtn.setPressedIcon(new ImageIcon("mstUI_img/mini3.png"));
        closeBtn.setIcon(new ImageIcon("mstUI_img/close2.png"));
        closeBtn.setRolloverIcon(new ImageIcon("mstUI_img/close1.png"));
        closeBtn.setPressedIcon(new ImageIcon("mstUI_img/close3.png"));
        
        headPanel.add(miniBtn);
        headPanel.add(closeBtn);
        
        JLabel showWhoJLabel=new JLabel();
        showWhoJLabel.setBounds(20, 0, 600, 60);
        showWhoJLabel.setFont(new Font("微软雅黑",Font.BOLD,20));
        String textString="物业收费系统-居民模式   Hi! "+user.getName()+" ,今天是"+getDateStr();
        //String textString="物业收费系统-居民模式   Hi! "+" ,今天是"+getDateStr();
        showWhoJLabel.setText(textString);
        headPanel.add(showWhoJLabel);
        
        add(headPanel);
	}
	private static String getDateStr() {
		Calendar c=Calendar.getInstance();
		Date d=new Date();
		c.setTime(d);//设置指定时间
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH)+1;  //默认是0-11，我国是1-12
		int day=c.get(Calendar.DAY_OF_MONTH);
		return year+"/"+month+"/"+day;
	}
	public  JTabbedPane initJTabbedPane() {
		uTabbedPane=new JTabbedPane();
		jPanel01=new JPanel(null);
		jPanel02=new JPanel(null);
		jPanel03=new JPanel(null);
		
		jPanel01.setBackground(color);
		jPanel02.setBackground(color);
		jPanel03.setBackground(color);
		
		uTabbedPane.addTab("jPanel01", jPanel01); //添加选项卡容器，并且设置其中每个选项卡的标签以及其是否可启用
		uTabbedPane.setEnabledAt(0, true);
		uTabbedPane.setTitleAt(0, "查询缴费");
		uTabbedPane.addTab("jPanel02", jPanel02); //添加选项卡容器，并且设置其中每个选项卡的标签以及其是否可启用
		uTabbedPane.setEnabledAt(1, true);
		uTabbedPane.setTitleAt(1, "历史账单");
		uTabbedPane.addTab("jPanel03", jPanel03); //添加选项卡容器，并且设置其中每个选项卡的标签以及其是否可启用
		uTabbedPane.setEnabledAt(2, true);
		uTabbedPane.setTitleAt(2, "资料设置");
		
		uTabbedPane.setUI(new TabbedPaneDef());
		uTabbedPane.setBounds(0, 60, 900, 640);
		uTabbedPane.setFont(new Font("宋体", Font.BOLD, 18));
		uTabbedPane.setForeground(new Color(255,255,255));
		
		paneSon1=new UserPaneSon1(user);
		jPanel01.add(paneSon1);
		paneSon2=new UserPaneSon2(user);
		jPanel02.add(paneSon2);
		paneSon3=new UserPaneSon3(user);
		paneSon3.setOwner(this);
		jPanel03.add(paneSon3);
		return uTabbedPane;
	}

	public JPanel getjPanel01() {
		return jPanel01;
	}

	public void setjPanel01(JPanel jPanel01) {
		this.jPanel01 = jPanel01;
	}

	public JPanel getjPanel02() {
		return jPanel02;
	}

	public void setjPanel02(JPanel jPanel02) {
		this.jPanel02 = jPanel02;
	}

	public JPanel getjPanel03() {
		return jPanel03;
	}

	public void setjPanel03(JPanel jPanel03) {
		this.jPanel03 = jPanel03;
	}

	public JPanel getHeadPanel() {
		return headPanel;
	}

	public void setHeadPanel(JPanel headPanel) {
		this.headPanel = headPanel;
	}

	public List<Payment> getLists() {
		return lists;
	}

	public void setLists(List<Payment> lists) {
		this.lists = lists;
	}

	public JButton getMiniBtn() {
		return miniBtn;
	}

	public void setMiniBtn(JButton miniBtn) {
		this.miniBtn = miniBtn;
	}

	public JButton getCloseBtn() {
		return closeBtn;
	}

	public void setCloseBtn(JButton closeBtn) {
		this.closeBtn = closeBtn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserPaneSon1 getPaneSon1() {
		return paneSon1;
	}

	public void setPaneSon1(UserPaneSon1 paneSon1) {
		this.paneSon1 = paneSon1;
	}

	public UserPaneSon2 getPaneSon2() {
		return paneSon2;
	}

	public void setPaneSon2(UserPaneSon2 paneSon2) {
		this.paneSon2 = paneSon2;
	}

	public UserPaneSon3 getPaneSon3() {
		return paneSon3;
	}

	public void setPaneSon3(UserPaneSon3 paneSon3) {
		this.paneSon3 = paneSon3;
	}
	// getNewUser 方法用于用户资料数据更新操作
	public User getNewUser(User oldUser) {
		@SuppressWarnings("unchecked")
		List<User> selectOne = (List<User>)new UserDAOImp().selectOne(oldUser);
		if(selectOne.size()<=0) {
			return null;
		}
		return selectOne.get(0);
	}
}
