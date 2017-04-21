package cn.com.sinosoft.tbf.config.datasource;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;

/**
 * mybatis配置
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年9月1日
 */
public class MybatisConfigurationFactory{
	
	public static Configuration getConfiguration(){
		Configuration conf = new Configuration();
		conf.setMapUnderscoreToCamelCase(true);// 下划线转驼峰
		conf.setCacheEnabled(false);// 缓存
		conf.setLazyLoadingEnabled(false);
		conf.setAggressiveLazyLoading(true);
		conf.setJdbcTypeForNull(JdbcType.NULL);
		return conf;
	}

}
