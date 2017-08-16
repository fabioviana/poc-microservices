package io.fabioviana.microservices.api.svcb.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import io.fabioviana.microservices.api.svcb.model.SocialProfile;

@RepositoryRestResource(path = "/api/v1/socialprofile")
public interface SocialProfileRepository extends JpaRepository<SocialProfile, Long> {
	@RestResource(path = "by-name")
	Collection<SocialProfile> findByName(@Param("name") String name);
}