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
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.suning.create.Create;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author admin
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LayoutSql  extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int width = 900;
	int height = 800;

	JTextArea  Sqltext;
	 JTextField RemarkFiled;
	 JTextField VOFiled;
	// 配置文件信息
	Map<String, String> dbMap;	

	public LayoutSql(Map<String, String> map) {
		
		setTitle("代码生成器");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(width, height);

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
 
  
		  JLabel lbVO = new JLabel("VO名称");
		  lbVO.setBounds(0, 0, 50, 50);
	        panel.add(lbVO);
	        
	        VOFiled = new JTextField();
	        VOFiled.setText("");
	        VOFiled.setBounds(100, 0, 200,50);
	        panel.add(VOFiled);
	        VOFiled.setColumns(10);
	        
	        
	        JLabel lbRemark = new JLabel("功能作用");
	        lbRemark.setBounds(0, 100, 70, 50);
	        panel.add(lbRemark);
	        
	        
	        
	        RemarkFiled = new JTextField();
	        RemarkFiled.setText("");
	        RemarkFiled.setBounds(100, 100, 200,50);
	        panel.add(RemarkFiled);
	        RemarkFiled.setColumns(10);
	        
	        
	        JLabel lbSQL = new JLabel("SQL语句");
	        lbSQL.setBounds(0, 300, 50, 50);
		    panel.add(lbSQL);
			        
        //按照SQL生成按钮		 
		JButton buttonBySql = new JButton("生成");
		// 按钮增加动作执行queryDataBase()方法
		buttonBySql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createBySql();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		buttonBySql.setBounds(new Rectangle(500, 200,100, 25));
		panel.add(buttonBySql);
		
		
		//设置输入文本框
	    Sqltext = new JTextArea();
		Sqltext.setAutoscrolls(true); 
		
		JScrollPane textareaScroller = new JScrollPane(Sqltext);
		textareaScroller.setBounds(100,200, 300, 400);
		panel.add(textareaScroller);
		
	}

	public void createBySql() throws Exception  {
		Create c = new Create();
		String selectSql =this.Sqltext.getText();
		String  reMark = this.RemarkFiled.getText();
		String  voName = this.VOFiled.getText();
		c.createBySql(selectSql, voName, reMark);
	}
}
