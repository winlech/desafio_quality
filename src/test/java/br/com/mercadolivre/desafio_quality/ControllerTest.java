package br.com.mercadolivre.desafio_quality;

import br.com.mercadolivre.desafio_quality.entities.Property;
import br.com.mercadolivre.desafio_quality.entities.Room;
import br.com.mercadolivre.desafio_quality.entities.RoomWithTotal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<RoomWithTotal> roomsWithTotal;
    private Property property;
    private Room biggestRoom;
    private Room smallestRoom;

    @BeforeEach
    void init() {
        biggestRoom = new Room("Quarto", 2.5, 3.8);
        smallestRoom = new Room("Cozinha", 1.2, 2.0);
        rooms = Stream.of(
                biggestRoom,
                smallestRoom
        ).collect(Collectors.toList());
        roomsWithTotal = new ArrayList<>(Arrays.asList(
                new RoomWithTotal(biggestRoom, biggestRoom.getRoom_width() * biggestRoom.getRoom_length()),
                new RoomWithTotal(smallestRoom, smallestRoom.getRoom_width() * smallestRoom.getRoom_length())
        ));
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

    @Test
    public void shouldReturnTotalPropertyValue() throws Exception {
        String payload = mapper.writeValueAsString(property);

        mockMvc.perform(post("/calculatePropertyValue")
                .contentType("application/json")
                .content(payload))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPropertyValue").value("R$ 59,5"));
    }

        @Test
        public void shouldReturnBiggerRoom() throws Exception {
            String payload = mapper.writeValueAsString(property);
            String response = mapper.writeValueAsString(biggestRoom);

            mockMvc.perform(post("/biggerRoom")
                    .contentType("application/json")
                    .content(payload))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(MockMvcResultMatchers.content().json(response));
        }

        @Test
        public void shouldReturnListRoomTotalSquareMeter() throws Exception {
            String payload = mapper.writeValueAsString(property);
            String response = mapper.writeValueAsString(roomsWithTotal);

            mockMvc.perform(post("/roomsWithTotal")
                    .contentType("application/json")
                    .content(payload))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(MockMvcResultMatchers.content().json(response));
        }

}
