package com.program.userUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.program.dao.AdapterDAO;
import com.program.daoimp.PaymentDAOImp;
import com.program.daoimp.UserDAOImp;

public class UserListener implements ActionListener{
	private UserUI userUI;
	@SuppressWarnings("unused")
	private UserPaneSon1 sPanel01;
	private UserPaneSon2 sPanel02;
	@SuppressWarnings("unused")
	private UserPaneSon3 sPanel03;
	private int mouseAtX = 0;
	private int mouseAtY = 0;
	
	public UserListener(UserUI userUI) {
		this.userUI = userUI;
		this.sPanel01=this.userUI.getPaneSon1();
		this.sPanel02=this.userUI.getPaneSon2();
		this.sPanel03=this.userUI.getPaneSon3();
		canDraged(this.userUI.getHeadPanel());
		this.userUI.getCloseBtn().addActionListener(this);
		this.userUI.getMiniBtn().addActionListener(this);
		this.sPanel02.getDelBtn().addActionListener(this);
	}
	public void canDraged(JPanel p) {
		MouseAdapter frameDrageAdapter=new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
                mouseAtX = e.getPoint().x;
                mouseAtY = e.getPoint().y;
            }
		};
		MouseMotionAdapter motionAdapter=new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
	              userUI.setLocation((e.getXOnScreen()-mouseAtX),(e.getYOnScreen()-mouseAtY));//设置拖拽后，窗口的位置
	          }
		};
		p.addMouseListener(frameDrageAdapter);
		p.addMouseMotionListener(motionAdapter);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==userUI.getCloseBtn()) {
			System.exit(0);
		}
		if(e.getSource()==userUI.getMiniBtn()) {
			 userUI.setExtendedState(JFrame.ICONIFIED);
		}
		if(e.getSource()==this.sPanel02.getDelBtn()) {
			delRows(sPanel02.getDtm(), sPanel02.getPayedTable(), new PaymentDAOImp());
		}
	}
	public void delRows(DefaultTableModel tableModel,JTable table,AdapterDAO adapterDAO) {
		List<Integer> idList=new ArrayList<Integer>();
		int selectRows=table.getSelectedRows().length;// 取得用户所选行的行数
		int[] selectedRows = table.getSelectedRows();
		if(selectRows<=0) {
			JOptionPane.showMessageDialog(null, "删除前要选中行", "警告", JOptionPane.WARNING_MESSAGE);
		}else {
			// 确认是否删除
			int get = JOptionPane.showOptionDialog(null, "请确认是否删除？", "友情询问", JOptionPane.YES_NO_OPTION, 
					JOptionPane.WARNING_MESSAGE, null, new Object[]{"是","否"}, "否");
			if(get!=1) {
				// 视觉上：倒着删避免数组下标越界
				for(int i=selectRows-1;i>=0;i--) {
					idList.add((Integer.parseInt(tableModel.getValueAt(selectedRows[i], 0)+"")));
					tableModel.removeRow(selectedRows[i]);
				}
				// 数据库中：先获取选中行的第0列 -> 编号列，通过编号删除数据库中的数据
				// 调用删除方法
				if(adapterDAO instanceof PaymentDAOImp) {
					PaymentDAOImp p=(PaymentDAOImp)adapterDAO;
					p.delete(idList);
				}else if(adapterDAO instanceof UserDAOImp){
					UserDAOImp u=(UserDAOImp)adapterDAO;
					u.delete(idList);
				}
			}
		}
	}
	
}
