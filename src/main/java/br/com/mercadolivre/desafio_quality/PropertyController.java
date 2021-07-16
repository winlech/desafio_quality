package br.com.mercadolivre.desafio_quality;

import br.com.mercadolivre.desafio_quality.dto.PropertyDTO;
import br.com.mercadolivre.desafio_quality.entities.District;
import br.com.mercadolivre.desafio_quality.entities.Property;
import br.com.mercadolivre.desafio_quality.entities.Room;
import br.com.mercadolivre.desafio_quality.entities.RoomWithTotal;
import br.com.mercadolivre.desafio_quality.repositories.DistrictRepository;
import br.com.mercadolivre.desafio_quality.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PropertyController {

    private final CalculatePlaceValueService calculatePlaceValueService;
    private final CalculateTotalSquareMeterService calculateTotalSquareMeterService;
    private final VerifyDistrictService verifyDistrictService;
    private final DistrictRepository districtRepository;
    private final GetBiggestRoomService getBiggestRoomService;
    private final CalculateRoomTotalSquareMeterService calculateRoomTotalSquareMeterService;

    public PropertyController(CalculatePlaceValueService calculatePlaceValueService, CalculateTotalSquareMeterService calculateTotalSquareMeterService, VerifyDistrictService verifyDistrictService, DistrictRepository districtRepository, GetBiggestRoomService getBiggestRoomService, CalculateRoomTotalSquareMeterService calculateRoomTotalSquareMeterService) {
        this.calculatePlaceValueService = calculatePlaceValueService;
        this.calculateTotalSquareMeterService = calculateTotalSquareMeterService;
        this.verifyDistrictService = verifyDistrictService;
        this.districtRepository = districtRepository;
        this.getBiggestRoomService = getBiggestRoomService;
        this.calculateRoomTotalSquareMeterService = calculateRoomTotalSquareMeterService;
    }

    @PostMapping("/calculateTotalArea")
    public ResponseEntity<Map> calculateTotalArea(@Valid @RequestBody PropertyDTO propertyDTO) {
        Property property = propertyDTO.convert();
        double totalSquareMeterProperty = calculateTotalSquareMeterService.execute(property.getRooms());
        Map<String, Double> returnValue= new HashMap<>();
        returnValue.put("totalSquareMeter", totalSquareMeterProperty);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PostMapping("/calculatePropertyValue")
    public ResponseEntity<Map> calculatePropertyValue(@Valid @RequestBody PropertyDTO propertyDTO) {
        Property property = propertyDTO.convert();
        District district;
        verifyDistrictService.execute(property.getProp_district());
        district = districtRepository.findByName(property.getProp_district());

        double totalSquareMeterProperty = calculateTotalSquareMeterService.execute(property.getRooms());
        BigDecimal totalValue = calculatePlaceValueService.execute(district, totalSquareMeterProperty);

        DecimalFormat formatter = new DecimalFormat("'R$' 0.##");
        Map<String, String> totalValueMap = new HashMap<>();
        totalValueMap.put("totalPropertyValue", formatter.format(totalValue));
        return new ResponseEntity<>(totalValueMap, HttpStatus.OK);
    }

    @PostMapping("/biggerRoom")
    public ResponseEntity<Room> biggerRoom(@Valid @RequestBody PropertyDTO propertyDTO) {
        Property property = propertyDTO.convert();
        Room rooms = getBiggestRoomService.execute(property.getRooms());

        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @PostMapping("/roomsWithTotal")
    public ResponseEntity<List<RoomWithTotal>> roomsWithTotal(@Valid @RequestBody PropertyDTO propertyDTO) {
        Property property = propertyDTO.convert();
        List<RoomWithTotal> rooms = calculateRoomTotalSquareMeterService.execute(property.getRooms());

        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
}
