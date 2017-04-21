package cn.com.sinosoft.tbf.common.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.SocketException;
import java.nio.charset.Charset;

/**
 * 文件操作工具类
 * 
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年12月15日
 */
public class FileUtil {

	/**
	 * 读取文件作为字符串
	 * 
	 * @param file
	 *            文件
	 * @param encode
	 *            编码
	 * @return
	 * @throws IOException
	 */
	public static String readFileAsString(File file, String encode) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encode));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = br.readLine()) != null) {
			buffer.append(line + System.getProperty("line.separator"));
		}
		br.close();
		return buffer.toString();
	}

	/**
	 * 读取文件作为字符串
	 * 
	 * @param filePath
	 *            文件路径
	 * @param encode
	 *            编码
	 * @return
	 * @throws IOException
	 */
	public static String readFileAsString(String filePath, String encode) throws IOException {
		return readFileAsString(new File(filePath), encode);
	}

	/**
	 * 将字符串保存为文件
	 * 
	 * @param str
	 *            字符串内容
	 * @param file
	 *            文件
	 * @throws IOException
	 */
	public static void saveStringAsFile(String str, File file, Charset charset) throws IOException {
		if (str == null || file == null)
			return;
		if (!file.exists() || file.isFile()) {
			file.deleteOnExit();
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), charset);
		fw.write(str);
		fw.flush();
		fw.close();

	}

	/**
	 * 将字符串保存为文件
	 * 
	 * @param str
	 *            字符串内容
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 */
	public static void saveStringAsFile(String str, String filePath, Charset charset) throws IOException {
		if (str == null || filePath == null)
			return;
		saveStringAsFile(str, new File(filePath), charset);
	}

	/**
	 * 同步本地目录
	 * 
	 * @param srcFolder
	 *            源目录
	 * @param targetFolder
	 *            目标目录
	 * @throws IOException
	 */
	public static void syncLocalFolder(String srcFolder, String targetFolder) throws IOException {
		File filepath = new File(targetFolder);
		// 判断目标文件夹是否存在，不存在则创建文件夹
		if (!filepath.exists()) {
			filepath.mkdirs();
		}
		// 获取源文件夹当前下的文件或目录
		File[] files = new File(srcFolder).listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				copyFile(files[i], new File(targetFolder + files[i].getName()));
			} else if (files[i].isDirectory()) {
				String sourceDir = srcFolder + File.separator + files[i].getName();
				String targetDir = targetFolder + File.separator + files[i].getName();
				copyDirectiory(sourceDir, targetDir);
			}
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param srcFile
	 *            源文件
	 * @param targetFile
	 *            目标文件
	 * @throws IOException
	 */
	public static void copyFile(File srcFile, File targetFile) throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// 新建文件输入流并对它进行缓冲
			inBuff = new BufferedInputStream(new FileInputStream(srcFile));

			// 新建文件输出流并对它进行缓冲
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// 缓冲数组
			byte[] b = new byte[1024 * 16];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			outBuff.flush();
		} finally {
			// 关闭流
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	/**
	 * 复制文件夹
	 * 
	 * @param srcDir
	 *            源文件夹目录
	 * @param targetDir
	 *            目标文件夹目录
	 * @throws IOException
	 */
	public static void copyDirectiory(String srcDir, String targetDir) throws IOException {
		// 新建目标目录
		new File(targetDir).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = new File(srcDir).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
				copyFile(sourceFile, targetFile);
			} else if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = srcDir + File.separator + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + File.separator + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * 同步本地和远程ftp目录
	 * 
	 * @param localFolder
	 *            本地目录
	 * @param remoteFtpFolder
	 *            远程ftp目录
	 * @param ftpConfig
	 *            ftp配置信息
	 * @throws IOException
	 * @throws SocketException
	 */
	public static void syncFtpFolder(String localFolder, String remoteFtpFolder, FtpConfig ftpConfig)
			throws SocketException, IOException {
		if (FtpConnect.connect(remoteFtpFolder, ftpConfig)) {
			File file = new File(localFolder);
			FtpConnect.upload(file);
			// 关闭连接
			FtpConnect.ftp.disconnect();
		}
	}

}
