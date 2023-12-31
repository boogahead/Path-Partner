package com.ssafy.pathpartner.config;

import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = {"com.ssafy.pathpartner.**.repository"})
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

  private final List<String> patterns = Arrays.asList("/board/*", "/admin", "/user/*", "/area/*",
      "attraction/*", "/comment/*", "/notice_article/*");

  @Autowired
  private DataSource dataSource;

//	private final String uploadFilePath;

//	public WebMvcConfiguration(@Value("${file.path.upload-files}") String uploadFilePath) {
//		this.uploadFilePath = uploadFilePath;
//	}

//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry.addMapping("/**")
//        .allowedOrigins("*")
////			.allowedOrigins("http://localhost")
//        .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
//            HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
//            HttpMethod.PATCH.name(), HttpMethod.OPTIONS.name())
////				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
////				.allowedMethods("*")
//        .allowCredentials(true)
//        .allowedHeaders("Authorization")
//        .maxAge(1800); // 1800초 동안 preflight 결과를 캐시에 저장\
//  }

  @Bean
  public DataSourceTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource);
  }
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
//	}

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/upload/file/**").addResourceLocations("file:///" + uploadFilePath + "/")
//				.setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
//	}
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("index2");
//	}


}
