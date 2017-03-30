package com.hpe.settle.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 配置好后，可以直接通过 http://http://localhost:8080/swagger-ui.html 来访问首页进行服务的接口的验证、
 * */
@Configuration
@EnableSwagger2
public class Swagger2 {
	
	@Value("${swagger.basePackage}")
	private String basePackage;
	@Value("${swagger.version}")
	private String version;
	@Value("${swagger.serviceUrl}")
	private String serviceUrl;
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(basePackage))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Swagger2构建RESTful APIs")
				.description("")
				.termsOfServiceUrl(serviceUrl)
				.contact("LLAHN")
				.version(version)
				.build();
	}
}
