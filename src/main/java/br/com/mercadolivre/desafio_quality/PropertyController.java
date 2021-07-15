package br.com.mercadolivre.desafio_quality;

import br.com.mercadolivre.desafio_quality.dto.PropertyDTO;
import br.com.mercadolivre.desafio_quality.entities.Property;
import br.com.mercadolivre.desafio_quality.services.CalculatePlaceValueService;
import br.com.mercadolivre.desafio_quality.services.CalculateTotalSquareMeterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PropertyController {

    private final CalculatePlaceValueService calculatePlaceValueService;
    private final CalculateTotalSquareMeterService calculateTotalSquareMeterService;

    public PropertyController(CalculatePlaceValueService calculatePlaceValueService, CalculateTotalSquareMeterService calculateTotalSquareMeterService) {
        this.calculatePlaceValueService = calculatePlaceValueService;
        this.calculateTotalSquareMeterService = calculateTotalSquareMeterService;
    }

    @PostMapping("/calculateTotalArea")
    public ResponseEntity<Map> calculateTotalArea(@Valid @RequestBody PropertyDTO propertyDTO) {
        Property property = propertyDTO.convert();
        double totalSquareMeterProperty = calculateTotalSquareMeterService.execute(property.getRooms());
        Map<String, Double> returnValue= new HashMap<>();
        returnValue.put("totalSquareMeter", totalSquareMeterProperty);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }
}
