package com.program.mainclass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Test extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 15151511L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable viewtable = null;
	private JButton jButton = null;
	DefaultTableModel dtm = null;

	String[] columnNames = { "First Name", "Last Name", "Sport", "# of Years", "Vegetarian" };

	Object[][] data = { { "Mary", "Campione", "Snowboarding", new Integer(5), new Boolean(false) },
			{ "Alison", "Huml", "Rowing", new Integer(3), new Boolean(true) },
			{ "Kathy", "Walrath", "Knitting", new Integer(2), new Boolean(false) },
			{ "Sharon", "Zakhour", "Speed reading", new Integer(20), new Boolean(true) },
			{ "Philip", "Milne", "Pool", new Integer(10), new Boolean(false) } };

	public Test() {
		super();
		initialize();
	}

	private JScrollPane getJScrollPane() {
		 jScrollPane = new JScrollPane();
		 jScrollPane.setBounds(new java.awt.Rectangle(40,40,650,200));
		 jScrollPane.setViewportView(getViewtable());
		 return jScrollPane;
	}

	private JTable getViewtable() {
		if (viewtable == null) {
			viewtable = new JTable();
			dtm=new DefaultTableModel(data,columnNames);
			viewtable=new JTable(dtm);
		}
		return viewtable;
	}

	private JButton getJButton() {
		 if (jButton == null) {
			jButton = new JButton();
			jButton.setText("del");
			jButton.setBounds(new java.awt.Rectangle(289,316,165,28));
			
			jButton.addActionListener(new ActionListener(){
			
				public void actionPerformed (ActionEvent e){
				
					/*int numrow=viewtable.getSelectedRows().length;
					System.out.println(numrow);
					for (int i=0;i<numrow;i++){
						dtm.removeRow(viewtable.getSelectedRow());
					}*/
					//dtm.addRow(new Object[] {"小明", "Milne", "Pool", new Integer(10), new Boolean(false) });
					Object valueAt = viewtable.getValueAt(viewtable.getSelectedRow(), viewtable.getSelectedColumn());
					System.out.println(valueAt);
				}
			});
		}
		 return jButton;
	}

	public static void main(String[] args) {
		new Test();
	}

	private void initialize() {
		this.setSize(772, 500);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

}
