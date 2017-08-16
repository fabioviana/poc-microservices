package io.fabioviana.microservices.apigateway.socialprofile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialProfile {
	private String name;

	public String getName() {
		return name;
	}
}