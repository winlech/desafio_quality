package br.com.mercadolivre.desafio_quality.dto;

import br.com.mercadolivre.desafio_quality.entities.District;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class DistrictDTO {

    @NotBlank(message = "Campo prop_district não pode estar vazio")
    @NotNull(message = "Campo prop_district não pode estar vazio")
    @Size(max = 45, message = "Comprimento do prop_district não pode exceder 45 caracters")
    private String prop_district;

    @NotNull(message = "Campo value_district_m2 não pode estar vazio")
    @Min(value = 1, message = "Valor não pode ser inferior a 1")
    @Digits(integer = 13, fraction = 2, message = "Até 13 casas")
    private BigDecimal value_district_m2;

    public District convert() {
        return new District(this.prop_district, this.value_district_m2);
    }

    public DistrictDTO() {
    }

    public DistrictDTO(String prop_district, BigDecimal value_district_m2) {
        this.prop_district = prop_district;
        this.value_district_m2 = value_district_m2;
    }

    public String getProp_district() {
        return prop_district;
    }

    public void setProp_district(String prop_district) {
        this.prop_district = prop_district;
    }

    public BigDecimal getValue_district_m2() {
        return value_district_m2;
    }

    public void setValue_district_m2(BigDecimal value_district_m2) {
        this.value_district_m2 = value_district_m2;
    }
}
