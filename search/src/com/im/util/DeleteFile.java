package com.im.util;

import java.io.File;
import java.io.FileFilter;

public class DeleteFile {
	
	/** 
	 * 得到文件夹内文件数量 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static File[] directoryNum(String sPath) {
		int count = 0;
		 //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    File[] files = null;
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	    	  
	    }  else {
	    	files = dirFile.listFiles();
	    	count = files.length;
	    	System.out.println(count);
	    }
	    
		return files;
	}
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String sPath) {  
		boolean flag = false;
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    //删除最旧的一个文件,if条件顺序不能变
	    if (files.length > 0 && files[0].isFile()) {  
            flag = deleteFile(files[0].getAbsolutePath());  
        }/*
	    for (int i = 0; i < files.length; i++) {  
	    	System.out.println(files[i].getName());
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  */
	    return flag;
	}  
	public static boolean deleteDirectoryCount(File[] files) {  
		boolean flag = false;
		if(files != null)
			for (int i = 0; i < files.length; i++) {  
				//删除子文件  
				if (files[i].isFile()) {  
					flag = deleteFile(files[i].getAbsolutePath());  
					if (!flag) break;  
				}
			}  
	    return flag;
	}  
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	
	/**
	 * 删除指定目录下的指定文件
	 * @param path
	 * @param page
	 * @return
	 */
	public static File[] deleteBaiheFile(String sPath, final int page) {
		File[] res = null;
		
		 File file = new File(sPath);
		 if(file.isDirectory()) {
			 res = file.listFiles(new FileFilter() {
				
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					return pathname.getName().matches(page + "+//.html");
				}
			});
		 }
		
		return res;
	}
}
