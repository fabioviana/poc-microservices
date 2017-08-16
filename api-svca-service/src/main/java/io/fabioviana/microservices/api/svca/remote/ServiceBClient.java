package io.fabioviana.microservices.api.svca.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "api-svcb-service", fallback = ServiceBClientFallback.class)
public interface ServiceBClient {
	@GetMapping("/api/v1/info/name")
	String getInfo();
}