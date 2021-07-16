package br.com.mercadolivre.desafio_quality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.mercadolivre.desafio_quality"))
                .paths(PathSelectors.any())
                .build();
//                .apiInfo(metaInfo());
    }

//    private ApiInfo metaInfo() {
//        ApiInfo apiInfo = new ApiInfo(
//                "Desafio Quality API REST",
//                "Desafio da DH",
//                "1.0",
//                "Termos de Uso",
//                new Contact("Wincenty Lech", "https://www.github.com/winlech", "wincenty.lech@mercadolivre.com.br"),
//                "Licença",
//                "site da licença",
//                new ArrayList<VendorExtension>()
//        );
//
//        return apiInfo;
//    }

}
