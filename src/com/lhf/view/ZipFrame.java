package com.lhf.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.lhf.manager.UnZipFileMgr;
import com.lhf.manager.ZipFileMgr;

/**
 * 压缩与解压缩的视图窗口界面
 * @author lhf
 *
 */
public class ZipFrame extends JFrame{
	//压缩类对象
	private ZipFileMgr zipFileMgr = new ZipFileMgr();
	//解压缩类对象
	private UnZipFileMgr unZipFileMgr = new UnZipFileMgr();
	
	//容器
	private JPanel panelNorth,panelCenter,panelSouth;
	//文本控件
	private JLabel labelSrcPath,labelDesPath;
	//路径输入框
	private JTextField tfSrcPath,tfDesPath;
	//描述文本显示框
	private JTextArea taDesc;
	//压缩，解压缩按钮
	private JButton btnZip,btnUnZip;
	
	public ZipFrame(){
		initView();
		addListeners();
	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		this.setTitle("压缩解压缩工具");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		panelNorth = new JPanel(new GridLayout(2,1));
		panelCenter = new JPanel();
		panelSouth = new JPanel();
		
		this.add(panelNorth,BorderLayout.NORTH);
		this.add(panelCenter,BorderLayout.CENTER);
		this.add(panelSouth,BorderLayout.SOUTH);
		
		//上部面板
		JPanel panelNorth1 = new JPanel();
		JPanel panelNorth2 = new JPanel();
		
		labelSrcPath = new JLabel("原始文件路径：");
		labelDesPath = new JLabel("目标文件路径：");
		tfSrcPath = new JTextField(30);
		tfDesPath = new JTextField(30);
		
		panelNorth1.add(labelSrcPath);
		panelNorth1.add(tfSrcPath);
		panelNorth2.add(labelDesPath);
		panelNorth2.add(tfDesPath);
		panelNorth.add(panelNorth1);
		panelNorth.add(panelNorth2);
		
		//中部面板
		taDesc = new JTextArea(10,35);
		taDesc.append("欢迎使用压缩解压缩小工具");
		JScrollPane scrollPane = new JScrollPane(taDesc);
		panelCenter.add(scrollPane);
		
		//下部面板
		btnZip = new JButton("压缩");
		btnUnZip = new JButton("解压缩");
		panelSouth.add(btnZip);
		panelSouth.add(btnUnZip);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * 添加监听事件
	 */
	private void addListeners() {
		//压缩
		btnZip.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String srcPath = tfSrcPath.getText();
				String desPath = tfDesPath.getText();
				//调用压缩方法
				zipFileMgr.zip(srcPath, desPath);
				String info = zipFileMgr.zip(srcPath, desPath);
				taDesc.setText(info);  //将压缩信息显示在窗口中
			}
		});
		//解压缩
		btnUnZip.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String srcPath = tfSrcPath.getText();
				String desPath = tfDesPath.getText();
				//调用解压缩方法
				unZipFileMgr.unZip(srcPath, desPath);
				String info = unZipFileMgr.unZip(srcPath, desPath);
				taDesc.setText(info);//将解压缩信息显示在窗口中
			}
		});
		
	}
	

}
