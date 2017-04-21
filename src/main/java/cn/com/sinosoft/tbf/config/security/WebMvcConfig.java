package cn.com.sinosoft.tbf.config.security;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import cn.com.sinosoft.tbf.common.util.StringToDateConverter;
import cn.com.sinosoft.tbf.domain.common.security.Audience;

/**
 * web mvc 配置，不要添加@EnableWebMvc注解
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年9月1日
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;

	@Autowired
	private Audience audienceEntity;

	// api token 安全验证拦截器
	@Bean
	public ApiTokenAuthInterceptor apiTokenAuthInterceptor() {
		return new ApiTokenAuthInterceptor();
	}

	// 自定义拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// api 接口权限拦截器
		InterceptorRegistration apiTokenAuthInteceptor = registry.addInterceptor(apiTokenAuthInterceptor());
		for (String url : audienceEntity.getUrlPatterns()) {
			apiTokenAuthInteceptor.addPathPatterns(url);
		}

		super.addInterceptors(registry);
	}

	// 自定义http json转换
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		// 将null转换为空字符串
		objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

			@Override
			public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers)
					throws IOException, JsonProcessingException {
				gen.writeString("");
			}
		});

		// 忽略绑定失败属性
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}

	// 增加字符串转日期的功能
	@PostConstruct
	public void initEditableValidation() {
		ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter
				.getWebBindingInitializer();
		if (initializer.getConversionService() != null) {
			GenericConversionService genericConversionService = (GenericConversionService) initializer
					.getConversionService();
			genericConversionService.addConverter(new StringToDateConverter());
		}

	}

}
