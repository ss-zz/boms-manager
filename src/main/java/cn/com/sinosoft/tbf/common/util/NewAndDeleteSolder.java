package cn.com.sinosoft.tbf.common.util;

import java.io.File;

/**
 * 新建或删除文件夹
 * 
 * @author <a href="mainto:lichuang@sinosoft.com.cn">lichuang</a>
 * @since 2016-12-15
 */
public class NewAndDeleteSolder {

	// 新建一个文件夹
	public static void newFolder(String folderPath) {
		try {
			File myFilePath = new File(folderPath);
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除文件
	public static void delFile(String fileName){      
		try{   
			File myFileName =new File(fileName);  
			myFileName.delete();       
		}catch (Exception e) {              
			e.printStackTrace();      
		}  
	}
}
