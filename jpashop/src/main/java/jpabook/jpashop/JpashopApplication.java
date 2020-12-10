package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@EntityScan("jpabook.jpashop.*")
@SpringBootApplication
public class JpashopApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

	

}
