package br.com.mercadolivre.desafio_quality.entities;

import br.com.mercadolivre.desafio_quality.entities.Room;

import java.math.BigDecimal;
import java.util.List;

public class Property {

    private String prop_name;
    private String prop_district;
    private List<Room> rooms;

    public Property(String prop_name) {
        this.prop_name = prop_name;
    }

    public Property(String prop_name, String prop_district, List<Room> rooms) {
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
}
