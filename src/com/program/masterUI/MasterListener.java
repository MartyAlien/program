package com.program.masterUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.program.daoimp.PaymentDAOImp;
import com.program.payment.Payment;

public class MasterListener implements ActionListener{
	private MasterUI masterUI;
	private JTabbedPane allTabbedPane;
	private JTable payJTable;
	private JPanel headPanel;
	private JButton refleshBtn,addBtn,modBtn,dellBtn;
	private int mouseAtX = 0;
	private int mouseAtY = 0;
	
	
	
	public MasterListener(MasterUI masterUI,JTabbedPane allTabbedPane,JTable payJTable,JPanel headPanel, JButton refleshBtn, JButton addBtn, JButton modBtn, JButton dellBtn) {
		super();
		
		this.masterUI=masterUI;
		this.allTabbedPane=allTabbedPane;
		this.payJTable=payJTable;
		this.headPanel = headPanel;
		this.refleshBtn = refleshBtn;
		this.addBtn = addBtn;
		this.modBtn = modBtn;
		this.dellBtn = dellBtn;
		canDraged(headPanel);
		refleshBtn.addActionListener(this);
		addBtn.addActionListener(this);
		modBtn.addActionListener(this);
		dellBtn.addActionListener(this);
	}

	// 窗体可拖拽
		public void canDraged(JPanel p) {
			MouseAdapter frameDrageAdapter=new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
	                mouseAtX = e.getPoint().x;
	                mouseAtY = e.getPoint().y;
	            }
			};
			MouseMotionAdapter motionAdapter=new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
		              masterUI.setLocation((e.getXOnScreen()-mouseAtX),(e.getYOnScreen()-mouseAtY));//设置拖拽后，窗口的位置
		          }
			};
			p.addMouseListener(frameDrageAdapter);
			p.addMouseMotionListener(motionAdapter);
		}


	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel tableModel = (DefaultTableModel) payJTable.getModel();
		if(e.getSource()==refleshBtn) {
			for(int i=tableModel.getRowCount()-1;i>=0;i--) {
				tableModel.removeRow(i); // 先移除原来的数据
			}
			List<Payment> payments = masterUI.getPayments();
			for(Payment p:payments) {
				tableModel.addRow(p.toStrArray());
			}
		}
		if(e.getSource()==addBtn) {
			allTabbedPane.setSelectedIndex(1);	// 跳转选项卡：1 为下发业单
		}
		if(e.getSource()==dellBtn) {
			List<Integer> idList=new ArrayList<Integer>();
			int selectRows=payJTable.getSelectedRows().length;// 取得用户所选行的行数
			int[] selectedRows = payJTable.getSelectedRows();
			if(selectRows<=0) {
				JOptionPane.showMessageDialog(null, "删除前要选中行", "警告", JOptionPane.WARNING_MESSAGE);
			}else {
				// 视觉上：倒着删避免数组下标越界
				for(int i=selectRows-1;i>=0;i--) {
					idList.add(((Integer)tableModel.getValueAt(selectedRows[i], 0)));
					tableModel.removeRow(selectedRows[i]);
				}
				// 数据库中：先获取选中行的第0列 -> 编号列，通过编号删除数据库中的数据
				// 调用删除方法
				new PaymentDAOImp().delete(idList);
			}
		}
		if(e.getSource()==modBtn) {
			//doNothing
		}
	}

}
