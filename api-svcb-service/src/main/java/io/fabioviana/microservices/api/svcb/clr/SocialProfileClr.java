package io.fabioviana.microservices.api.svcb.clr;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.fabioviana.microservices.api.svcb.model.SocialProfile;
import io.fabioviana.microservices.api.svcb.repository.SocialProfileRepository;

@Component
public class SocialProfileClr implements CommandLineRunner {

	@Autowired
	private SocialProfileRepository socialProfileRepository;

	@Override
	public void run(String... arg0) throws Exception {
		Stream.<String>of("Kanye West", "Rihanna", "Imagine Dragons")
				.forEach(name -> socialProfileRepository.save(new SocialProfile(name)));

	}
}