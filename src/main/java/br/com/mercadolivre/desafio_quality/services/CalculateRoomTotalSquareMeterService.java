package br.com.mercadolivre.desafio_quality.services;

import br.com.mercadolivre.desafio_quality.entities.Room;
import br.com.mercadolivre.desafio_quality.entities.RoomWithTotal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalculateRoomTotalSquareMeterService {

    public List<RoomWithTotal> execute(List<Room> rooms) {
        return rooms.stream()
                .map(r -> new RoomWithTotal(r, r.getRoom_width() * r.getRoom_length()))
                .collect(Collectors.toList());

    }
}
