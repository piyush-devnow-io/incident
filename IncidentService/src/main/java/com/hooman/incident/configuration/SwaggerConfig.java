package com.hooman.incident.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Incidet API").description("Incident API for reference")
				.termsOfServiceUrl("http://devnow.io").contact("piyush@devnow.io").license("DevNow")
				.licenseUrl("piyush@devnow.io").version("1.0").build();
	}

//    private Predicate<String> postPaths() {
//		return or(regex("/api/posts.*"), regex("/api/javainuse.*"));
//	}

}