package io.fabioviana.microservices.api.svca.remote;

import org.springframework.stereotype.Component;

@Component
public class ServiceBClientFallback implements ServiceBClient {
	@Override
	public String getInfo() {
		return "FALLBACK";
	}
}