package br.com.mercadolivre.desafio_quality.entities;

public class RoomWithTotal {

    private Room room;
    private double totalSquareMeter;

    public RoomWithTotal(Room room) {
        this.room = room;
    }

    public RoomWithTotal(Room room, double totalSquareMeter) {
        this.room = room;
        this.totalSquareMeter = totalSquareMeter;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getTotalSquareMeter() {
        return totalSquareMeter;
    }

    public void setTotalSquareMeter(double totalSquareMeter) {
        this.totalSquareMeter = totalSquareMeter;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof RoomWithTotal)) return false;
        RoomWithTotal other = (RoomWithTotal)obj;
        if(!(this.room.getRoom_name().equalsIgnoreCase(other.room.getRoom_name())))
            return false;
        if(this.room.getRoom_length() != other.room.getRoom_length() || this.room.getRoom_width() != other.room.getRoom_width())
            return false;
        return this.totalSquareMeter == other.totalSquareMeter;
    }
}
