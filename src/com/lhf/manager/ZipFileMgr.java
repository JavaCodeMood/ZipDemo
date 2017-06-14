package com.lhf.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileMgr {
	private StringBuffer sb = new StringBuffer();
	
	/**
	 * ѹ��һ���ļ����ļ���
	 * @param srcPath  Դ·��
	 * @param desPath  //Ŀ��·��
	 * @return
	 */
	public String zip(String srcPath,String desPath)
	{
		//����File����ָ��Դ·��
		File file = new File(srcPath);
		sb.append("ѹ���ļ����£�\n");
		try {
			FileOutputStream fos = new FileOutputStream(desPath);
			ZipOutputStream zos = new ZipOutputStream(fos);
			//�ж����ļ������ļ���
			if(file.isDirectory()){//�ļ���
				zipDirctory(zos,file,file.getName());
			}else{//�ļ�
				zipFile(zos,file,file.getName());
			}
			sb.append("ѹ����ɣ�");
			zos.close();  //�ر���
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * ѹ��һ���ļ���
	 * @param zos  ѹ���ļ������
	 * @param file  �ļ�����
	 * @param zipName  ��ǰ�ļ�����Ŀ
	 */
	private void zipDirctory(ZipOutputStream zos,File file,String zipName){
		//�ж��ǲ����ļ���
		if(file.isDirectory()){  //���ļ���
			File[] subFiles = file.listFiles();   //��ȡ����ļ����µ��������ļ�����ŵ��ļ�������
			//�ж��ļ����Ƿ�Ϊ��
			if(subFiles.length==0){  //����ǿ��ļ��У���Ҫ��ӵ��ļ���Ŀ��
				try {
					zos.putNextEntry(new ZipEntry(zipName+"/"));
					sb.append(zipName+"\n");   //׷�ӿ��ļ���Ϣ
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//��������ļ���
			for(File subFile:subFiles){
				if(subFile.isFile()){   //������ļ�����ֱ��ѹ��
					zipFile(zos,subFile,zipName+"/"+subFile.getName());   //���÷���
				}else{  //������ļ��У��Ͱ����ļ��еķ�ʽȥѹ��
					zipDirctory(zos,subFile,zipName+"/"+subFile.getName());   //�ݹ���ã�ѹ���ļ���
				}
			}
		}
	}
	
	/**
	 * ѹ��һ���ļ�
	 * @param zos  ѹ���ļ������
	 * @param file  �ļ�����
	 * @param zipEntryName  ѹ����Ŀ���ļ����·����
	 */
	private void zipFile(ZipOutputStream zos,File file,String zipEntryName){
		sb.append(zipEntryName+"\n");
		//�����ļ�������
		try {
			FileInputStream fis = new FileInputStream(file);
			//��ѹ���ļ�д��ѹ����Ŀ��
			zos.putNextEntry(new ZipEntry(zipEntryName));
			
			byte[] buf = new byte[1024];  //һ�ζ�1024���ֽ�
			int length = -1; //����ʵ�ʶ�ȡ���ٸ�
			while((length = fis.read(buf))!= -1){  //ÿ�ζ�1024��
				zos.write(buf, 0, length);   //д��ȥ��������д����
			}
			zos.closeEntry();  //�ر�
			fis.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
