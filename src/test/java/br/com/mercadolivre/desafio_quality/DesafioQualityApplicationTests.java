package br.com.mercadolivre.desafio_quality;

import br.com.mercadolivre.desafio_quality.entities.Place;
import br.com.mercadolivre.desafio_quality.entities.Room;
import br.com.mercadolivre.desafio_quality.entities.RoomWithTotal;
import br.com.mercadolivre.desafio_quality.services.CalculatePlaceValueService;
import br.com.mercadolivre.desafio_quality.services.CalculateRoomTotalSquareMeterService;
import br.com.mercadolivre.desafio_quality.services.CalculateTotalSquareMeterService;
import br.com.mercadolivre.desafio_quality.services.GetBiggestRoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DesafioQualityApplicationTests {

    private CalculateTotalSquareMeterService calculateTotalSquareMeterService;
    private CalculatePlaceValueService calculatePlaceValueService;
    private GetBiggestRoomService getBiggestRoomService;
    private CalculateRoomTotalSquareMeterService calculateRoomTotalSquareMeterService;
    private List<Room> rooms;
    private Place place;
    private Room biggestRoom;

    @BeforeEach
    void init() {
        calculateTotalSquareMeterService = new CalculateTotalSquareMeterService();
        calculatePlaceValueService = new CalculatePlaceValueService();
        getBiggestRoomService = new GetBiggestRoomService();
        calculateRoomTotalSquareMeterService = new CalculateRoomTotalSquareMeterService();
        biggestRoom = new Room("quarto", 2.5, 3.8);
        rooms = Stream.of(
                biggestRoom,
                new Room("cozinha", 1.2, 2.0)
        ).collect(Collectors.toList());
        place = new Place("Bellagio", "Lapa", new BigDecimal(5), rooms);

    }

    @Test
    @DisplayName("Must Return the Total Square Meter od the Property")
    void mustReturnTotalSquareMeter() {
        double expectedResult = 11.9;

        double result = calculateTotalSquareMeterService.execute(rooms);

        assertEquals(expectedResult, result);
    }

    @Test
    void mustReturnTotalPropertyValue() {
        double expectedValue = 5 * 11.9;
        BigDecimal expectedResult = new BigDecimal(expectedValue);

        double totalMeter = calculateTotalSquareMeterService.execute(rooms);
        BigDecimal result = calculatePlaceValueService.execute(place, totalMeter);

        assertEquals(expectedResult, result);
    }

    @Test
    void mustReturnBiggestRoom() {
        Room result = getBiggestRoomService.execute(rooms);

        assertEquals(biggestRoom, result);
    }

    @Test
    void mustReturnListTotalSquareMeterOfRoom() {
        List<RoomWithTotal> expectedResult = Stream.of(
                new RoomWithTotal(rooms.get(0), (rooms.get(0).getRoom_width() * rooms.get(0).getRoom_length())),
                new RoomWithTotal(rooms.get(1), (rooms.get(1).getRoom_width() * rooms.get(1).getRoom_length()))
        ).collect(Collectors.toList());

        List<RoomWithTotal> result = calculateRoomTotalSquareMeterService.execute(rooms);

        assertEquals(expectedResult, result);

    }

}
