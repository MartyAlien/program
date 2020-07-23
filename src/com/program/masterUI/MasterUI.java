package com.program.masterUI;

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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.program.daoimp.PaymentDAOImp;
import com.program.master.Master;
import com.program.payment.Payment;

public class MasterUI extends JFrame{
	private static final long serialVersionUID = -18044L;
	Color color=new Color(191,230,240);
	private Master master=null;
	private List<Payment> lists;
	private JPanel headPanel;
	private JPanel jPanel01,jPanel02,jPanel03;
	private JPanel floorPanel;
	private JTabbedPane allTabbedPane;
	private JScrollPane payScrollPane;
	private JTable payJTable=null;
	private JButton refleshBtn,addBtn,modBtn,dellBtn;
	private JButton miniBtn,closeBtn;
	private String[] columnNames = { "编号","用户名", "检修费", "清洁费", "停车费", "缴费日期","是否缴费" };
	private MasterListener masterListener;
	private MasterPaneSon1 masterPaneSon1=new MasterPaneSon1();
	private MasterPaneSon2 masterPaneSon2=new MasterPaneSon2();
	
	
	Object[][] data;

	public MasterUI() throws HeadlessException {
		setSize(900, 700);
		setLayout(null);
		setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setBackground(color);
	    setUndecorated(true);
	    setResizable(false);
	    initHeadPane();
	    add(initJTabbedPane());
	    this.masterListener = new MasterListener(this,allTabbedPane,payJTable,headPanel
	    		, refleshBtn, addBtn, modBtn, dellBtn,miniBtn,closeBtn,masterPaneSon1,masterPaneSon2);
	    setVisible(true);
	}
	
	public MasterUI(Master master) throws HeadlessException {
		this.master = master;
		setSize(900, 700);
		setLayout(null);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(color);
        setUndecorated(true);
        setResizable(false);
        initHeadPane();
        add(initJTabbedPane());
        this.masterListener = new MasterListener(this,allTabbedPane,payJTable,headPanel
        		, refleshBtn, addBtn, modBtn, dellBtn,miniBtn,closeBtn,masterPaneSon1,masterPaneSon2);
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
        String textString="物业收费系统-物管模式   Hi! "+master.getName()+" ,今天是"+getDateStr();
        //String textString="物业收费系统-物管模式   Hi! "+" ,今天是"+getDateStr();
        showWhoJLabel.setText(textString);
        headPanel.add(showWhoJLabel);
        
        add(headPanel);
	}

	private static String getDateStr() {
		Calendar c=Calendar.getInstance();
		Date d=new Date();
		c.setTime(d);//设置指定时间
		//System.out.println(c);
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH)+1;  //默认是0-11，我国是1-12
		int day=c.get(Calendar.DAY_OF_MONTH);
		return year+"/"+month+"/"+day;
	}
	public  JTabbedPane initJTabbedPane() {
		allTabbedPane=new JTabbedPane();
		jPanel01=new JPanel(null);
		jPanel02=new JPanel(null);
		jPanel03=new JPanel(null);
		
		jPanel01.setBackground(color);
		jPanel02.setBackground(color);
		jPanel03.setBackground(color);
		
		allTabbedPane.addTab("jPanel01", jPanel01); //添加选项卡容器，并且设置其中每个选项卡的标签以及其是否可启用
		allTabbedPane.setEnabledAt(0, true);
		allTabbedPane.setTitleAt(0, "收费记录");
		allTabbedPane.addTab("jPanel02", jPanel02); //添加选项卡容器，并且设置其中每个选项卡的标签以及其是否可启用
		allTabbedPane.setEnabledAt(1, true);
		allTabbedPane.setTitleAt(1, "生成业单");
		allTabbedPane.addTab("jPanel03", jPanel03); //添加选项卡容器，并且设置其中每个选项卡的标签以及其是否可启用
		allTabbedPane.setEnabledAt(2, true);
		allTabbedPane.setTitleAt(2, "用户管理");
		
		allTabbedPane.setUI(new TabbedPaneDef());
		allTabbedPane.setBounds(0, 60, 900, 640);
		allTabbedPane.setFont(new Font("宋体", Font.BOLD, 18));
		allTabbedPane.setForeground(new Color(255,255,255));
		
		jPanel01.add(initScrollPane());
		jPanel01.add(initBtnPane());
		jPanel02.add(masterPaneSon1);
		jPanel03.add(masterPaneSon2);
		return allTabbedPane;
	}
	private JScrollPane initScrollPane() {
		payScrollPane=new JScrollPane();
		payScrollPane.setBackground(Color.WHITE);
		payScrollPane.setBounds(0, 0, 896, 500);
		payScrollPane.setViewportView(getViewtable());
		return payScrollPane;
	}
	private JTable getViewtable() {
		/*if (payJTable == null) {
			payJTable = new JTable();
		}*/
		lists=getPayments();
		data=new Object[lists.size()][];
		int i=0;
		for(Payment p:lists) {
			data[i++]=p.toStrArray();
		}
		DefaultTableModel dtm=new DefaultTableModel(data,columnNames);
		payJTable=new JTable(dtm) {
			// 重写这个表格的方法：设置不可编辑，但可以选中
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		payJTable.getTableHeader().setFont(new Font("微软雅黑",Font.BOLD,16));
		payJTable.setFont(new Font("幼圆",Font.PLAIN,14));
		payJTable.setRowHeight(24);
		payJTable.getTableHeader().setReorderingAllowed(false);// 设置列不可拖动
		// 设置部分列宽度
		payJTable.getColumnModel().getColumn(0).setPreferredWidth(15);
		payJTable.getColumnModel().getColumn(2).setPreferredWidth(25);
		payJTable.getColumnModel().getColumn(3).setPreferredWidth(25);
		payJTable.getColumnModel().getColumn(4).setPreferredWidth(25);
		payJTable.getColumnModel().getColumn(0).setResizable(false);
		payJTable.getColumnModel().getColumn(2).setResizable(false);
		payJTable.getColumnModel().getColumn(3).setResizable(false);
		payJTable.getColumnModel().getColumn(4).setResizable(false);
		// 设置居中
		DefaultTableCellRenderer renderer=new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);
		payJTable.setDefaultRenderer(Object.class, renderer);
		return payJTable;
	}
	@SuppressWarnings("unchecked") //取消小黄标经过
	public List<Payment> getPayments() {
		PaymentDAOImp paymentDAOImp = new PaymentDAOImp();
		List<Payment> list = (List<Payment>)paymentDAOImp.selectAll();
		return list;
	}
	
	private JPanel initBtnPane() {
		this.floorPanel=new JPanel(null);
		this.refleshBtn=new JButton("刷新");
		this.addBtn=new JButton("添加");
		this.modBtn=new JButton("修改");
		this.dellBtn=new JButton("删除");
		
		refleshBtn.setBounds(40, 20, 100, 40);
		addBtn.setBounds(150, 20, 100, 40);
		modBtn.setBounds(260, 20, 100, 40);
		modBtn.setToolTipText("管理员是不能修改账单的！");
		modBtn.setEnabled(false);
		dellBtn.setBounds(370, 20, 100, 40);
		
		floorPanel.setBounds(0, 500, 900, 88);
		floorPanel.setBackground(Color.WHITE);
		
		floorPanel.add(refleshBtn);
		floorPanel.add(addBtn);
		floorPanel.add(modBtn);
		floorPanel.add(dellBtn);
		return floorPanel;
	}
	
	public JPanel initJpanel02() {
		JPanel jPanel=new JPanel();
		
		return jPanel;
	}

	public MasterListener getMasterListener() {
		return masterListener;
	}

	
	/*public static void main(String[] args) {
		new MasterUI();
	}*/
}
