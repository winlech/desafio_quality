package br.com.mercadolivre.desafio_quality.dto;

import br.com.mercadolivre.desafio_quality.entities.Room;

import javax.validation.constraints.*;

public class RoomDTO {

    @NotBlank(message = "Campo room_name não pode estar vazio")
    @NotNull(message = "Campo room_name não pode estar vazio")
    @Size(max = 30, message = "Comprimento do room_name não pode exceder 30 caracters")
    @Pattern(regexp = "^[A-Z][A-Za-z0-9_ ]*$", message = "O room_name deve começar com uma letra maíuscula")
    private String room_name;

    @NotBlank(message = "Campo room_width não pode estar vazio")
    @NotNull(message = "Campo room_width não pode estar vazio")
    @Max(value = 25, message = "Largura máxima permitida por cômodo é de 25 metros")
    private double room_width;

    @NotBlank(message = "Campo room_length não pode estar vazio")
    @NotNull(message = "Campo room_length não pode estar vazio")
    @Max(value = 33, message = "Largura máxima permitida por cômodo é de 33 metros")
    private double room_length;

    public RoomDTO(String room_name, double room_width, double room_length) {
        this.room_name = room_name;
        this.room_width = room_width;
        this.room_length = room_length;
    }

    public RoomDTO(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public double getRoom_width() {
        return room_width;
    }

    public void setRoom_width(double room_width) {
        this.room_width = room_width;
    }

    public double getRoom_length() {
        return room_length;
    }

    public void setRoom_length(double room_length) {
        this.room_length = room_length;
    }

    public Room convert() {
        return new Room(this.getRoom_name(), this.getRoom_width(), this.getRoom_length());
    }
}
