package cn.com.sinosoft.tbf.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpConnect {

	static FTPClient ftp;

	/**
	 * ftp连接
	 * 
	 * @param path
	 *            远程ftp目录
	 * @param ftpConfig
	 *            ftp配置信息
	 * @return
	 * @throws SocketException
	 * @throws IOException
	 */
	public static boolean connect(String path, FtpConfig ftpConfig)
			throws SocketException, IOException {
		boolean result = false;
		ftp = new FTPClient();
		int reply;
		ftp.connect(ftpConfig.getAddr(), ftpConfig.getPort());
		ftp.login(ftpConfig.getUserName(), ftpConfig.getPassWord());
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			// ftp关闭连接
			ftp.disconnect();
			return result;
		} else {
			ftp.changeWorkingDirectory(path);
			result = true;
		}
		return result;
	}

	/**
	 * 上传本地目录到ftp目录
	 * 
	 * @param file
	 *            本地目录
	 * @throws IOException
	 */
	public static void upload(File file) throws IOException {
		if (file.isDirectory()) {
			ftp.makeDirectory(file.getName());
			ftp.changeWorkingDirectory(file.getName());
			String[] files = file.list();
			for (int i = 0; i < files.length; i++) {
				File file1 = new File(file.getPath() + File.separator
						+ files[i]);
				if (file1.isDirectory()) {
					upload(file1);
					ftp.changeToParentDirectory();
				} else {
					File file2 = new File(file.getPath() + File.separator
							+ files[i]);
					FileInputStream input = new FileInputStream(file2);
					ftp.storeFile(file2.getName(), input);
					input.close();
				}
			}
		} else {
			File file2 = new File(file.getPath());
			FileInputStream input = new FileInputStream(file2);
			ftp.storeFile(file2.getName(), input);
			input.close();
		}
	}
}
