package br.com.mercadolivre.desafio_quality;

import br.com.mercadolivre.desafio_quality.entities.District;
import br.com.mercadolivre.desafio_quality.entities.Property;
import br.com.mercadolivre.desafio_quality.entities.Room;
import br.com.mercadolivre.desafio_quality.entities.RoomWithTotal;
import br.com.mercadolivre.desafio_quality.repositories.DistrictRepository;
import br.com.mercadolivre.desafio_quality.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
class DesafioQualityApplicationTests {

    private CalculateTotalSquareMeterService calculateTotalSquareMeterService;
    private CalculatePlaceValueService calculatePlaceValueService;
    private GetBiggestRoomService getBiggestRoomService;
    private CalculateRoomTotalSquareMeterService calculateRoomTotalSquareMeterService;
    private List<Room> rooms;
    private Property property;
    private Room biggestRoom;

    @Mock
    private DistrictRepository districtRepository;

    private VerifyDistrictService verifyDistrictService;

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
        property = new Property("Bellagio", "Lapa", rooms);
        verifyDistrictService = new VerifyDistrictService(districtRepository);

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
        District district = new District("Lapa", new BigDecimal(5));
        BigDecimal result = calculatePlaceValueService.execute(district, totalMeter);

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

    @Test
    @DisplayName("District exists so return true")
    void shouldVerifyIfDistrictExists() {
        District district = new District("Lapa", new BigDecimal(5.0));

        when(districtRepository.findByName("Lapa")).thenReturn(district);

        boolean result = verifyDistrictService.execute(property.getProp_district());

        verify(districtRepository).findByName(eq("Lapa"));
        assertTrue(result);
    }

    @Test
    @DisplayName("District do not exists so throw exception")
    void shouldThrowException() {

        when(districtRepository.findByName(anyString())).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            verifyDistrictService.execute("Não existe");
        });

        String expecteMessage = "Bairro não existe";
        String actualMessage = exception.getMessage();

        assertEquals(expecteMessage, actualMessage);
    }

}
