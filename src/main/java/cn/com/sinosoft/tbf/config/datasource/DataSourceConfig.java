package cn.com.sinosoft.tbf.config.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;

/**
 * 数据源
 * 
 * springboot集成mybatis的基本入口<br/>
 * 1）创建数据源(如果采用的是默认的tomcat-jdbc数据源，则不需要)<br/>
 * 2）创建SqlSessionFactory<br/>
 * 3）配置事务管理器，除非需要使用事务，否则不用配置<br/>
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	// 数据源//
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(
			@Qualifier("dataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:mybatis/**/*.xml"));

		// 配置项
		bean.setConfiguration(MybatisConfigurationFactory.getConfiguration());

		// 分页插件
		OffsetLimitInterceptor interPage = new OffsetLimitInterceptor();
		String dialectClassPage = null;

		// 判断数据库类型
		String url = datasourceUrl;
		if (url != null) {
			if (url.contains("mysql")) {
				dialectClassPage = "com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect";
			} else if (url.contains("oracle")) {
				dialectClassPage = "com.github.miemiedev.mybatis.paginator.dialect.OracleDialect";
			}
		}

		interPage.setDialectClass(dialectClassPage);
		bean.setPlugins(new Interceptor[]{interPage});

		return bean.getObject();
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager(
			@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory)
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}