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
	 * 压缩一个文件或文件夹
	 * @param srcPath  源路径
	 * @param desPath  //目标路径
	 * @return
	 */
	public String zip(String srcPath,String desPath)
	{
		//创建File对象，指向源路径
		File file = new File(srcPath);
		sb.append("压缩文件如下：\n");
		try {
			FileOutputStream fos = new FileOutputStream(desPath);
			ZipOutputStream zos = new ZipOutputStream(fos);
			//判断是文件还是文件夹
			if(file.isDirectory()){//文件夹
				zipDirctory(zos,file,file.getName());
			}else{//文件
				zipFile(zos,file,file.getName());
			}
			sb.append("压缩完成！");
			zos.close();  //关闭流
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 压缩一个文件夹
	 * @param zos  压缩文件输出流
	 * @param file  文件对象
	 * @param zipName  当前文件夹条目
	 */
	private void zipDirctory(ZipOutputStream zos,File file,String zipName){
		//判断是不是文件夹
		if(file.isDirectory()){  //是文件夹
			File[] subFiles = file.listFiles();   //获取这个文件夹下的所有子文件，存放到文件数组中
			//判断文件夹是否为空
			if(subFiles.length==0){  //如果是空文件夹，需要添加到文件条目中
				try {
					zos.putNextEntry(new ZipEntry(zipName+"/"));
					sb.append(zipName+"\n");   //追加空文件信息
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//遍历这个文件夹
			for(File subFile:subFiles){
				if(subFile.isFile()){   //如果是文件，就直接压缩
					zipFile(zos,subFile,zipName+"/"+subFile.getName());   //调用方法
				}else{  //如果是文件夹，就按照文件夹的方式去压缩
					zipDirctory(zos,subFile,zipName+"/"+subFile.getName());   //递归调用，压缩文件夹
				}
			}
		}
	}
	
	/**
	 * 压缩一个文件
	 * @param zos  压缩文件输出流
	 * @param file  文件对象
	 * @param zipEntryName  压缩条目（文件相对路径）
	 */
	private void zipFile(ZipOutputStream zos,File file,String zipEntryName){
		sb.append(zipEntryName+"\n");
		//创建文件输入流
		try {
			FileInputStream fis = new FileInputStream(file);
			//把压缩文件写入压缩条目中
			zos.putNextEntry(new ZipEntry(zipEntryName));
			
			byte[] buf = new byte[1024];  //一次读1024个字节
			int length = -1; //代表实际读取多少个
			while((length = fis.read(buf))!= -1){  //每次读1024个
				zos.write(buf, 0, length);   //写进去，读多少写多少
			}
			zos.closeEntry();  //关闭
			fis.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
