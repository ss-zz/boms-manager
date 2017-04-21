package cn.com.sinosoft.tbf.common.util.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.sinosoft.tbf.domain.common.security.Audience;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * jwt工具类
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月18日
 */
@Component
public class JwtHelper {

	private final static Logger log = LoggerFactory.getLogger(JwtHelper.class);
	
	@Autowired
	HttpServletRequest request;

	@Autowired
	private Audience audienceEntity;

	/**
	 * 解析jwt
	 *
	 * @param jsonWebToken
	 * @param base64Security
	 * @return
	 */
	public Claims parseJWT(String jsonWebToken) {
		if (jsonWebToken == null || jsonWebToken.trim().equals("")) {
			return null;
		}
		Claims claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(
							audienceEntity.getBase64Secret()))
					.parseClaimsJws(jsonWebToken).getBody();
			if (claims != null) {
				Date expiration = claims.getExpiration();
				if (expiration == null
						|| expiration.getTime() < new Date().getTime()) {
					claims = null;
				}
			}

		} catch (Exception e) {
			log.warn("jwt token[{}]解析失败", jsonWebToken);
		}
		return claims;
	}
	
	/**
	 * 解析jwt-默认
	 *
	 * @return
	 */
	public Claims parseJWT() {
		return parseJWT(getJWTToken());
	}

	/**
	 * 生成jwt
	 *
	 * @param TTLMillis
	 *            过期时间
	 * @param base64Security
	 *            加密字符串
	 * @param userInfo
	 *            用户信息
	 * 
	 * @return
	 */
	public String createJWT(Map<String, Object> userInfo) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();

		// 生成签名密钥
		byte[] apiKeySecretBytes = DatatypeConverter
				.parseBase64Binary(audienceEntity.getBase64Secret());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes,
				signatureAlgorithm.getJcaName());

		// 添加构成JWT的参数
		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
				.setIssuedAt(new Date())
				.signWith(signatureAlgorithm, signingKey);

		// 用户信息
		builder.setClaims(userInfo);

		// 添加Token过期时间
		int TTLMillis = audienceEntity.getExpiresSecond() * 1000;
		if (TTLMillis >= 0) {
			long expMillis = nowMillis + TTLMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// 生成JWT
		return builder.compact();
	}
	
	/**
	 * 获取jwt token
	 *
	 * @return
	 */
	public String getJWTToken(){
		return request.getHeader(audienceEntity.getTokenName());
	}
	
}