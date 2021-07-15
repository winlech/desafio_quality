package br.com.mercadolivre.desafio_quality;

import br.com.mercadolivre.desafio_quality.entities.Property;
import br.com.mercadolivre.desafio_quality.entities.Room;
import br.com.mercadolivre.desafio_quality.services.CalculatePlaceValueService;
import br.com.mercadolivre.desafio_quality.services.CalculateRoomTotalSquareMeterService;
import br.com.mercadolivre.desafio_quality.services.CalculateTotalSquareMeterService;
import br.com.mercadolivre.desafio_quality.services.GetBiggestRoomService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    private List<Room> rooms;
    private Property property;
    private Room biggestRoom;

    @BeforeEach
    void init() {
        biggestRoom = new Room("Quarto", 2.5, 3.8);
        rooms = Stream.of(
                biggestRoom,
                new Room("Cozinha", 1.2, 2.0)
        ).collect(Collectors.toList());
        property = new Property("Bellagio", "Lapa", rooms);

    }

    @Test
    public void shouldReturnTotalAreaFromProperty() throws Exception {
        String payload = mapper.writeValueAsString(property);

        mockMvc.perform(post("/calculateTotalArea")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalSquareMeter").value(11.9));
    }
}
