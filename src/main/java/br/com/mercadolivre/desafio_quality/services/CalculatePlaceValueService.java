package br.com.mercadolivre.desafio_quality.services;

import br.com.mercadolivre.desafio_quality.entities.District;
import br.com.mercadolivre.desafio_quality.entities.Property;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatePlaceValueService {

    public BigDecimal execute(District district, double totalSquareMeter) {
        return district.getValue_district_m2().multiply(BigDecimal.valueOf(totalSquareMeter));
    }
}
