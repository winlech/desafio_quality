package br.com.mercadolivre.desafio_quality.services;

import br.com.mercadolivre.desafio_quality.entities.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateTotalSquareMeterService {

    public CalculateTotalSquareMeterService() {
    }

    public double execute(List<Room> rooms) {
        return rooms.stream()
                .mapToDouble(r -> r.getRoom_length() * r.getRoom_width())
                .sum();
    }
}
