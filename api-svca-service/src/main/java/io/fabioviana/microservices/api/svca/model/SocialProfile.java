package io.fabioviana.microservices.api.svca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialProfile {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	public SocialProfile(String name) {
		this.name = name;
	}
}