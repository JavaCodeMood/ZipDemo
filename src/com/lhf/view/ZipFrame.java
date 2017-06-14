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
 * ѹ�����ѹ������ͼ���ڽ���
 * @author lhf
 *
 */
public class ZipFrame extends JFrame{
	//ѹ�������
	private ZipFileMgr zipFileMgr = new ZipFileMgr();
	//��ѹ�������
	private UnZipFileMgr unZipFileMgr = new UnZipFileMgr();
	
	//����
	private JPanel panelNorth,panelCenter,panelSouth;
	//�ı��ؼ�
	private JLabel labelSrcPath,labelDesPath;
	//·�������
	private JTextField tfSrcPath,tfDesPath;
	//�����ı���ʾ��
	private JTextArea taDesc;
	//ѹ������ѹ����ť
	private JButton btnZip,btnUnZip;
	
	public ZipFrame(){
		initView();
		addListeners();
	}

	/**
	 * ��ʼ������
	 */
	private void initView() {
		this.setTitle("ѹ����ѹ������");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		panelNorth = new JPanel(new GridLayout(2,1));
		panelCenter = new JPanel();
		panelSouth = new JPanel();
		
		this.add(panelNorth,BorderLayout.NORTH);
		this.add(panelCenter,BorderLayout.CENTER);
		this.add(panelSouth,BorderLayout.SOUTH);
		
		//�ϲ����
		JPanel panelNorth1 = new JPanel();
		JPanel panelNorth2 = new JPanel();
		
		labelSrcPath = new JLabel("ԭʼ�ļ�·����");
		labelDesPath = new JLabel("Ŀ���ļ�·����");
		tfSrcPath = new JTextField(30);
		tfDesPath = new JTextField(30);
		
		panelNorth1.add(labelSrcPath);
		panelNorth1.add(tfSrcPath);
		panelNorth2.add(labelDesPath);
		panelNorth2.add(tfDesPath);
		panelNorth.add(panelNorth1);
		panelNorth.add(panelNorth2);
		
		//�в����
		taDesc = new JTextArea(10,35);
		taDesc.append("��ӭʹ��ѹ����ѹ��С����");
		JScrollPane scrollPane = new JScrollPane(taDesc);
		panelCenter.add(scrollPane);
		
		//�²����
		btnZip = new JButton("ѹ��");
		btnUnZip = new JButton("��ѹ��");
		panelSouth.add(btnZip);
		panelSouth.add(btnUnZip);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * ��Ӽ����¼�
	 */
	private void addListeners() {
		//ѹ��
		btnZip.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String srcPath = tfSrcPath.getText();
				String desPath = tfDesPath.getText();
				//����ѹ������
				zipFileMgr.zip(srcPath, desPath);
				String info = zipFileMgr.zip(srcPath, desPath);
				taDesc.setText(info);  //��ѹ����Ϣ��ʾ�ڴ�����
			}
		});
		//��ѹ��
		btnUnZip.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String srcPath = tfSrcPath.getText();
				String desPath = tfDesPath.getText();
				//���ý�ѹ������
				unZipFileMgr.unZip(srcPath, desPath);
				String info = unZipFileMgr.unZip(srcPath, desPath);
				taDesc.setText(info);//����ѹ����Ϣ��ʾ�ڴ�����
			}
		});
		
	}
	

}
