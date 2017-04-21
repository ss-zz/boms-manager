package cn.com.sinosoft.tbf.common.util.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * http client 工具类
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月19日
 */
public class HttpClientUtil {

	/**
	 * 获取http client
	 */
	private static CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}
	
	
	/**
	 * 发送post请求
	 */
	public static String postData(String url, Map<String, String> params,
			String encoding) {
		HttpClient client = getHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (String key : params.keySet()) {
			list.add(new BasicNameValuePair(key, params.get(key)));
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(list, encoding);
			post.setEntity(uefEntity);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			client.execute(post);
			CloseableHttpResponse response = getHttpClient().execute(post);
			String responseEntity = EntityUtils.toString(response.getEntity());
			switch (response.getStatusLine().getStatusCode()) {
				case 200 :
					return responseEntity;
				default :
					break;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		return null;
	}
	
	/*public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", "jt");
		params.put("password", MD5Util.digestMD5("00000000"));
		String res = HttpClientUtil.postData("http://192.168.1.252:9301/wjwmb/api/user/login", params, "utf-8");
		System.out.println(res);
	}*/

}
