package com.lhf.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipFileMgr {
	
	/**
	 * 解压缩一个文件或文件夹
	 * @param srcPath 源路径
	 * @param desPath  目标路径
	 * @return
	 */
	public String unZip(String srcPath,String desPath){
		StringBuffer sb = new StringBuffer();
		sb.append("解压缩文件如下：\n");
		try {
			//创建文件输入流
			FileInputStream fis = new FileInputStream(srcPath);
			//解压缩流
			ZipInputStream zis = new ZipInputStream(fis);
			//读取压缩包中的文件条目，这个条目可能是文件，也可能是文件夹
			//如果是文件，需要把这个文件写到目标文件下。如果是一个文件夹，需要创建对应的文件夹
			ZipEntry zipEntry = null;//条目类型变量
			while((zipEntry=zis.getNextEntry())!= null){
				sb.append(zipEntry.getName()+"\n");
				//创建目标对象的File对象
				// desPath+File.pathSeparator+zipEntry.getName();   文件的路径
				File targetFile = new File(desPath+File.pathSeparator+zipEntry.getName());
				//判断父文件夹是否存在，不存在就创建文件夹
				if(!targetFile.getParentFile().exists()){
					targetFile.getParentFile().mkdirs();   //不存在就创建
				}
				if(zipEntry.isDirectory()){   //如果是一个文件夹
					if(!targetFile.exists()){   //如果这个文件夹，不存在，就创建它
						targetFile.mkdir();
					}
				}else{//如果是一个文件
					FileOutputStream fos = new FileOutputStream(targetFile);
					//缓冲区，提高效率
					byte[] buf = new byte[1024];   
					int length = -1;
					//不等于-1，就继续读取
					while((length=zis.read(buf))!= -1){
						fos.write(buf, 0, length);   //读多少写多少
					}
					zis.closeEntry();  //关闭流
					fos.close();
				}
			}
			sb.append("解压缩完成！");
			fis.close();
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}

}
