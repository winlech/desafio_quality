package br.com.mercadolivre.desafio_quality.entities;

import br.com.mercadolivre.desafio_quality.entities.Room;

import java.math.BigDecimal;
import java.util.List;

public class Place {

    private String prop_name;
    private String prop_district;
    private BigDecimal value_district_m2;
    private List<Room> rooms;

    public Place(String prop_name) {
        this.prop_name = prop_name;
    }

    public Place(String prop_name, String prop_district, BigDecimal value_district_m2, List<Room> rooms) {
        this.prop_name = prop_name;
        this.prop_district = prop_district;
        this.value_district_m2 = value_district_m2;
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

    public BigDecimal getValue_district_m2() {
        return value_district_m2;
    }

    public void setValue_district_m2(BigDecimal value_district_m2) {
        this.value_district_m2 = value_district_m2;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
