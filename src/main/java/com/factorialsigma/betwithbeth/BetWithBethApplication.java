package com.factorialsigma.betwithbeth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class BetWithBethApplication {

	public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1"); //TODO para wanabet
        System.setProperty("jdk.tls.client.protocols", "TLSv1"); //TODO para wanabet
		SpringApplication.run(BetWithBethApplication.class, args);
	}
}
