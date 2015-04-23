/*
 * Copyright (C), 2002-2014, 苏宁易购电子商务有限公司
 * FileName: Beantet.java
 * Author:   admin
 * Date:     2014年7月8日 上午11:48:48
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.suning.layout;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.suning.create.Create;
import com.suning.db.DbUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author admin
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LayoutTable  extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JTable jtable;
	private MyTableModel tableModel;
	String[] titles = { "选择", "表名称" };
	JButton crTableButton ;
	
	// 配置文件信息
	Map<String, String> dbMap;	

	public LayoutTable(Map<String, String> map) {
		
		setTitle("代码生成器");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);


        // 增加关闭事件监听，关闭相关操作
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }

        });
        
        
		this.dbMap = map;
 
		// 设置表格
		Object[][] tableData = {};
		tableModel = new MyTableModel(tableData, titles);
		jtable = new JTable(this.tableModel);
		
		TableColumn column = jtable.getColumnModel().getColumn(0);
		column.setPreferredWidth(2);

		JScrollPane scr = new JScrollPane(this.jtable);
		scr.setBounds(0, 0, 300, 300);
		panel.add(scr);
		
	    //查询数据库按钮
		JButton querybutton = new JButton("查询数据库");
		// 按钮增加动作执行queryDataBase()方法
		querybutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryDataBase(dbMap);
			}
		});
		querybutton.setBounds(300, 200, 100,25);
		panel.add(querybutton);

		// 按照表格生成按钮
		crTableButton = new JButton("生成");
		// 按钮增加动作执行create()方法
		crTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createByTables();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		crTableButton.setBounds(new Rectangle(300,100,100, 25));
		crTableButton.setEnabled(false);
		panel.add(crTableButton);
 
		
	}

	// 查询数据库 获取所有表名
	public void queryDataBase(Map<String, String> querymap) {
		try {
			DbUtil db = new DbUtil(querymap);
			List<String> tableList = db
					.getTableNames(querymap.get("showTable"));
			if (tableList == null) {
				int rowCount = this.getTableModel().getRowCount();
				int delInd = 0;
				while (delInd < rowCount) {
					this.getTableModel().removeRow(0);
					delInd++;
				}
			} else {
				int rowCount = this.getTableModel().getRowCount();

				int delInd = 0;
				while (delInd < rowCount) {
					this.getTableModel().removeRow(0);
					delInd++;
				}
				for (String tName : tableList) {
					Object[] rowData = { new Boolean(false), tName };
					this.getTableModel().addRow(rowData);
				}
				crTableButton.setEnabled(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createByTables()  {
		// 判断勾选
		int rowCount = this.jtable.getRowCount();
		try {
			Create c = new Create();
			String tabName = null;
			for (int i = 0; i < rowCount; i++) {
				if (this.jtable.getValueAt(i, 0).toString().equals("true")) {
					tabName = this.jtable.getValueAt(i, 1).toString();
					c.createByTable(tabName);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public JTable getJtable() {
		return jtable;
	}

	public void setJtable(JTable jtable) {
		this.jtable = jtable;
	}

	public MyTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(MyTableModel tableModel) {
		this.tableModel = tableModel;
	}


	class MyTableModel extends DefaultTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyTableModel(Object[][] data, String[] columns) {
			super(data, columns);
		}

		public boolean isCellEditable(int row, int column) { // 设置Table单元格是否可编辑
			if (column == 0)
				return true;
			return false;
		}

		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex == 0) {
				return Boolean.class;
			}
			return Object.class;
		}

	}
}
