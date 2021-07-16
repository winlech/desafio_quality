package br.com.mercadolivre.desafio_quality.repositories;

import br.com.mercadolivre.desafio_quality.entities.District;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class DistrictRepository {

    public static List<District> districts = Stream.of(
            new District("Lapa", new BigDecimal(5.0)),
            new District("Centro", new BigDecimal(5.0)),
            new District("Bairro2", new BigDecimal(5.0))
    ).collect(Collectors.toList());

    public District findByName(String name) {
        return districts.stream()
                .filter(r -> r.getProp_district().equals(name))
                .findFirst()
                .orElse(null);
    }
}
