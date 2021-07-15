package br.com.mercadolivre.desafio_quality.services;

import br.com.mercadolivre.desafio_quality.entities.Place;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatePlaceValueService {

    public BigDecimal execute(Place place, double totalSquareMeter) {
        return place.getValue_district_m2().multiply(BigDecimal.valueOf(totalSquareMeter));
    }
}
