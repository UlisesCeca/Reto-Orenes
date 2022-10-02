package com.orenes.reto;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class RetoApplication {
    private static final String LOCATION_GROUP = "location-group";
	private static final String LOCATION_TOPIC = "location-topic";

	public static void main(String[] args) {
		SpringApplication.run(RetoApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	
	@KafkaListener(topics = LOCATION_TOPIC, groupId = LOCATION_GROUP)
    public void listener1(String location) {
        System.out.println("Location: " + location);
    }

}
