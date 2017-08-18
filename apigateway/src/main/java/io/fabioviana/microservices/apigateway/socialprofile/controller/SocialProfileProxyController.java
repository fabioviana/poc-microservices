package io.fabioviana.microservices.apigateway.socialprofile.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.fabioviana.microservices.apigateway.socialprofile.model.SocialProfile;

@RefreshScope
@RestController
@RequestMapping("/api/v1/socialProfiles")
public class SocialProfileProxyController {
	private static final String SERVICE_A = "api-svca-service";
	private static final String SERVICE_B = "api-svcb-service";

	private SocialProfileProxyController self;

    @Autowired
    private ApplicationContext applicationContext;

	@Autowired
	private RestTemplate restTemplate;

	@PostConstruct
    private void init() {
        self = applicationContext.getBean(SocialProfileProxyController.class);
    }

	@HystrixCommand(fallbackMethod = "getProfileNamesByServiceFallback")
	@RequestMapping(method = RequestMethod.GET, value = "/names/{service}")
	public Collection<String> getProfileNamesByService(@PathVariable("service") String service) {
		ParameterizedTypeReference<Resources<SocialProfile>> typeReference = new ParameterizedTypeReference<Resources<SocialProfile>>() {
		};

		ResponseEntity<Resources<SocialProfile>> profiles = restTemplate.exchange(
				"http://" + service + "/api/v1/socialProfiles", HttpMethod.GET, null, typeReference);

		return profiles.getBody().getContent().stream().map(SocialProfile::getName).collect(Collectors.toList());
	}

	@HystrixCommand(fallbackMethod = "getProfileNamesFallback")
	@RequestMapping(method = RequestMethod.GET, value = "/names")
	public Collection<String> getProfileNames() {
		Collection<String> results = self.getProfileNamesByService(SERVICE_A);
		Collection<String> svcbResults = self.getProfileNamesByService(SERVICE_B);
		results.addAll(svcbResults);
		return results;
	}

	private Collection<String> getProfileNamesFallback() {
		return new ArrayList<String>();
	}

	private Collection<String> getProfileNamesByServiceFallback(String service) {
		return new ArrayList<String>();
	}
}