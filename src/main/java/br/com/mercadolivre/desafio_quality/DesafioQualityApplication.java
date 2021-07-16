package br.com.mercadolivre.desafio_quality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DesafioQualityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioQualityApplication.class, args);
    }

}
