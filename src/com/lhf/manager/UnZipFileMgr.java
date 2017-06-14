package com.lhf.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipFileMgr {
	
	/**
	 * ��ѹ��һ���ļ����ļ���
	 * @param srcPath Դ·��
	 * @param desPath  Ŀ��·��
	 * @return
	 */
	public String unZip(String srcPath,String desPath){
		StringBuffer sb = new StringBuffer();
		sb.append("��ѹ���ļ����£�\n");
		try {
			//�����ļ�������
			FileInputStream fis = new FileInputStream(srcPath);
			//��ѹ����
			ZipInputStream zis = new ZipInputStream(fis);
			//��ȡѹ�����е��ļ���Ŀ�������Ŀ�������ļ���Ҳ�������ļ���
			//������ļ�����Ҫ������ļ�д��Ŀ���ļ��¡������һ���ļ��У���Ҫ������Ӧ���ļ���
			ZipEntry zipEntry = null;//��Ŀ���ͱ���
			while((zipEntry=zis.getNextEntry())!= null){
				sb.append(zipEntry.getName()+"\n");
				//����Ŀ������File����
				// desPath+File.pathSeparator+zipEntry.getName();   �ļ���·��
				File targetFile = new File(desPath+File.pathSeparator+zipEntry.getName());
				//�жϸ��ļ����Ƿ���ڣ������ھʹ����ļ���
				if(!targetFile.getParentFile().exists()){
					targetFile.getParentFile().mkdirs();   //�����ھʹ���
				}
				if(zipEntry.isDirectory()){   //�����һ���ļ���
					if(!targetFile.exists()){   //�������ļ��У������ڣ��ʹ�����
						targetFile.mkdir();
					}
				}else{//�����һ���ļ�
					FileOutputStream fos = new FileOutputStream(targetFile);
					//�����������Ч��
					byte[] buf = new byte[1024];   
					int length = -1;
					//������-1���ͼ�����ȡ
					while((length=zis.read(buf))!= -1){
						fos.write(buf, 0, length);   //������д����
					}
					zis.closeEntry();  //�ر���
					fos.close();
				}
			}
			sb.append("��ѹ����ɣ�");
			fis.close();
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}

}
