package io.fabioviana.microservices.apigateway;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.UriComponentsBuilder;

import feign.Feign;
import feign.hystrix.HystrixFeign;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.Paths;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableZuulProxy
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableCircuitBreaker
@EnableSwagger2
@SpringBootApplication
public class ApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServiceApplication.class, args);
	}

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setIncludeHeaders(true);
		return loggingFilter;
	}

	@LoadBalanced
	@Bean
	public RestTemplate defaultRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Feign.Builder feignBuilder() {
		return HystrixFeign.builder();
	}

	@Bean
	public Docket v10DocumentationPlugin(@Value("${name}") String serviceName) {
		return new VersionedDocket(serviceName, "v1");
	}

	class VersionedDocket extends Docket {
		@SuppressWarnings("rawtypes")
		public VersionedDocket(String serviceName, String version) {
			super(DocumentationType.SWAGGER_2);
			super.groupName(version).select()
					.apis(RequestHandlerSelectors.basePackage("io.fabioviana.microservices.apigateway"))
					.paths(PathSelectors.any()).build()
					.apiInfo(new ApiInfo(serviceName, "Base-URL: /api/".concat(version), version, "",
							new Contact("me@me.com", "", ""), "", "", new ArrayList<VendorExtension>()))
					.pathProvider(new BasePathAwareRelativePathProvider("/api/" + version))
					.directModelSubstitute(Date.class, String.class).useDefaultResponseMessages(false)
					.enableUrlTemplating(true);
		}
	}

	class BasePathAwareRelativePathProvider extends AbstractPathProvider {
		private String basePath;

		public BasePathAwareRelativePathProvider(String basePath) {
			this.basePath = basePath;
		}

		@Override
		protected String applicationPath() {
			return basePath;
		}

		@Override
		protected String getDocumentationPath() {
			return "/";
		}

		@Override
		public String getOperationPath(String operationPath) {
			UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath("/");
			return Paths.removeAdjacentForwardSlashes(
					uriComponentsBuilder.path(operationPath.replaceFirst(basePath, "")).build().toString());
		}
	}
}