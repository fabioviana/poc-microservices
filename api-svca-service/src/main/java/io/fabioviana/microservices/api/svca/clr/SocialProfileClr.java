package io.fabioviana.microservices.api.svca.clr;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.fabioviana.microservices.api.svca.model.SocialProfile;
import io.fabioviana.microservices.api.svca.repository.SocialProfileRepository;

@Component
public class SocialProfileClr implements CommandLineRunner {

	@Autowired
	private SocialProfileRepository socialProfileRepository;

	@Override
	public void run(String... arg0) throws Exception {
		Stream.<String>of("Katy Perry", "Justin Bieber", "Taylor Swift", "Barack Obama", "Youtube", "Rihanna",
				"Lady Gaga").forEach(name -> socialProfileRepository.save(new SocialProfile(name)));

	}
}