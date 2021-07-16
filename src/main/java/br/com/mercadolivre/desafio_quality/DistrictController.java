package br.com.mercadolivre.desafio_quality;

import br.com.mercadolivre.desafio_quality.entities.District;
import br.com.mercadolivre.desafio_quality.dto.DistrictDTO;
import br.com.mercadolivre.desafio_quality.repositories.DistrictRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/district")
@Api(value = "API REST District")
@CrossOrigin(origins = "*")
public class DistrictController {

    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictController(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @PostMapping
    @ApiOperation(value = "Cria um novo bairro")
    public ResponseEntity<District> create(@Valid @RequestBody DistrictDTO districtDTO) {
        return new ResponseEntity<>(districtRepository.save(districtDTO.convert()), HttpStatus.OK);
    }
}
