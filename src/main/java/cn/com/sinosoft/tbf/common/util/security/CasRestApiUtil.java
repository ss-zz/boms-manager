package cn.com.sinosoft.tbf.common.util.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * cas集成rest api工具类
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月19日
 */
public class CasRestApiUtil {

	static final Logger LOG = Logger.getLogger(CasRestApiUtil.class.getName());
	
	/**
	 * 获取http client
	 */
	private static CloseableHttpClient getHttpClient() {
		return HttpClients.createDefault();
	}

	/**
	 * 根据用户名、密码获取cas tgt票据（根据cas server端的配置自动过期）
	 */
	public static String getTicketGrantingTicket(String server, String username,
			String password) {
		HttpClient client = getHttpClient();
		HttpPost post = new HttpPost(server);

		// 创建参数列表
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("username", username));
		list.add(new BasicNameValuePair("password", password));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefEntity);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			client.execute(post);
			CloseableHttpResponse response = getHttpClient().execute(post);
			String responseEntity = EntityUtils.toString(response.getEntity());
			switch (response.getStatusLine().getStatusCode()) {
				case 201 :
					Matcher matcher = Pattern.compile(".*action=\".*/(.*?)\".*")
							.matcher(responseEntity);
					if (matcher.matches())
						return matcher.group(1);
					LOG.warning("成功请求，但是没有发现票据！");
					break;
				default :
					LOG.warning(
							"状态码 (" + response.getStatusLine().getStatusCode()
									+ ") cas服务端!");
					break;
			}
		} catch (final IOException e) {
			LOG.warning(e.getMessage());
		} finally {
			post.releaseConnection();
		}
		return null;
	}
	
	/**
	 * 获取cas st票据（使用一次就会过期）
	 */
	public static String getServiceTicket(String server, String ticketGrantingTicket,
			String service) {
		if (ticketGrantingTicket == null)
			return null;
		HttpPost post = new HttpPost(server + "/" + ticketGrantingTicket);
		// 创建参数列表
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("service", service));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefEntity);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			CloseableHttpResponse response = getHttpClient().execute(post);
			String responseEntity = EntityUtils.toString(response.getEntity());
			switch (response.getStatusLine().getStatusCode()) {
				case 200 :
					return responseEntity;
				default :
					LOG.warning("请求失败["
							+ response.getStatusLine().getStatusCode() + "]");
					break;
			}
		} catch (final IOException e) {
			LOG.warning(e.getMessage());
		} finally {
			post.releaseConnection();
		}
		return null;
	}

	/**
	 * 登出
	 *
	 * @param server
	 * @param ticketGrantingTicket
	 */
	public static void logout(String server, String ticketGrantingTicket) {
		HttpDelete method = new HttpDelete(server + "/" + ticketGrantingTicket);
		try {
			CloseableHttpResponse response = getHttpClient().execute(method);
			//String responseEntity = EntityUtils.toString(response.getEntity());
			switch (response.getStatusLine().getStatusCode()) {
				case 200 :
					break;
				default :
					LOG.warning(
							"错误码 (" + response.getStatusLine().getStatusCode()
									+ ") cas 服务端 !");
					break;
			}
		} catch (final IOException e) {
			LOG.warning(e.getMessage());
		} finally {
			method.releaseConnection();
		}
	}

	/*public static void main(String[] args) {
		// cas server
		String server = "http://localhost:8080/cas/v1/tickets";
		// 认证-用户名
		String username = "jt";
		// 认证-密码
		String password = MD5Util.digestMD5("00000000");
		// 服务-注意：需要cas server端能够验证过去
		String service = "http://cas01.example.org";
		
		//获取tgt票据，用来返回客户端
		String tgt = CasRestApiUtil.getTicketGrantingTicket(server,
				username, password);
		System.out.println("授权票据[TGT]是 " + tgt);
		
		//获取st票据，用于访问服务，移动端的话需要自己访问cas获取此票据
		String st = CasRestApiUtil.getServiceTicket(server,
				tgt, service);
		System.out.println("服务票据[ST]是 " + st);
		
		//登出
		CasRestApiUtil.logout(server, tgt);
	}*/

}
