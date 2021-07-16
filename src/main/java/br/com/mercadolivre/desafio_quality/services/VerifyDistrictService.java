package br.com.mercadolivre.desafio_quality.services;

import br.com.mercadolivre.desafio_quality.entities.District;
import br.com.mercadolivre.desafio_quality.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyDistrictService {

    private final DistrictRepository districtRepository;

    @Autowired
    public VerifyDistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public boolean execute(String district_name) {
        District district = this.districtRepository.findByName(district_name);
        if(district != null)
            return true;
        throw new RuntimeException("Bairro não existe");
    }
}
