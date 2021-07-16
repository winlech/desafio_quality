package br.com.mercadolivre.desafio_quality.dto;

import br.com.mercadolivre.desafio_quality.entities.Property;
import br.com.mercadolivre.desafio_quality.entities.Room;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class PropertyDTO {

    @NotBlank(message = "Campo prop_name não pode estar vazio")
    @NotNull(message = "Campo prop_name não pode estar vazio")
    @Size(max = 30, message = "Comprimento do prop_name não pode exceder 30 caracters")
    @Pattern(regexp = "^[A-Z][A-Za-z0-9_ ]*$", message = "O prop_name deve começar com uma letra maíuscula")
    private String prop_name;

    @NotBlank(message = "Campo prop_district não pode estar vazio")
    @NotNull(message = "Campo prop_district não pode estar vazio")
    @Size(max = 45, message = "Comprimento do prop_district não pode exceder 30 caracters")
    private String prop_district;

    @Valid
    private List<Room> rooms;

    public PropertyDTO(String prop_name) {
        this.prop_name = prop_name;
    }

    public PropertyDTO(String prop_name, String prop_district, List<Room> rooms) {
        this.prop_name = prop_name;
        this.prop_district = prop_district;
        this.rooms = rooms;
    }

    public String getProp_name() {
        return prop_name;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }

    public String getProp_district() {
        return prop_district;
    }

    public void setProp_district(String prop_district) {
        this.prop_district = prop_district;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Property convert() {
        return new Property(this.getProp_name(), this.getProp_district(), this.getRooms());
    }
}
