package br.com.mercadolivre.desafio_quality.repositories;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class DistrictRepository {

    public static List<String> districts = Stream.of(
            "Lapa",
            "Centro",
            "Bairro2"
    ).collect(Collectors.toList());

    public String findByName(String name) {
        return districts.stream()
                .filter(r -> r.equals(name))
                .findFirst()
                .orElse(null);
    }
}
