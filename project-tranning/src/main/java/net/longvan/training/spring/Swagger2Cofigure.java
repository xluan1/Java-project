package net.longvan.training.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Cofigure {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("net.longvan.training.spring")).paths(PathSelectors.any())
				.build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Xuan Luan - Spring Boot Swagger Configuration")
				.description("\"Swagger configuration for application \"").version("1.1.0").license("Apache 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"").contact(new Contact("Xuan Luan",
						"https://github.com/xluan1", "ngoxuanluan2001@gmail.com"))
				.build();
	}
}
