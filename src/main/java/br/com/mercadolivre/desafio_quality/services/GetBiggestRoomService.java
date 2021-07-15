package br.com.mercadolivre.desafio_quality.services;

import br.com.mercadolivre.desafio_quality.entities.Room;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class GetBiggestRoomService {

    public Room execute(List<Room> rooms) {
        return rooms.stream()
                .max(Comparator.comparingDouble(r -> r.getRoom_length() * r.getRoom_width()))
                .orElse(new Room("", 0, 0));
    }
}
