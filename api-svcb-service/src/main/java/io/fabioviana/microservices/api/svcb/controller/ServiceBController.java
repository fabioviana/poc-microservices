package io.fabioviana.microservices.api.svcb.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/api/v1/info")
public class ServiceBController {

	@Value("${name}")
	private String serviceName;

	@GetMapping("/name")
	public String getInfo() {
		return this.serviceName;
	}
}