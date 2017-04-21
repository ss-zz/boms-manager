package cn.com.sinosoft.tbf.domain.common;

/**
 * 文件上传返回的信息
 * 
 * @author <a href="mainto:lichuang@sinosoft.com.cn">lichuang</a>
 * @since 2016-12-20
 */
public class FileUploadInfo {

	/**
	 * 文件id
	 */
	private Integer fileId;

	/**
	 * 路径
	 */
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

}
