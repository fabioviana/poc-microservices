package io.fabioviana.microservices.api.svca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.fabioviana.microservices.api.svca.remote.ServiceBClient;

@RefreshScope
@RestController
@RequestMapping("/api")
public class ServiceAController {

	@Value("${name}")
	private String serviceName;

	@Autowired
	private ServiceBClient serviceBClient;

	@GetMapping("/v1/info/name")
	public String getInfo() {
		return this.serviceName;
	}

	@GetMapping("/v2/info/name")
	public String getInfoV2() {
		return this.serviceName.concat("\ncalls >> ").concat(this.serviceBClient.getInfo());
	}
}