package be.bstorm.formation.demotesting.demoApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonneIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PersonneRepository personneRepository;

    @Test
    void whenGetById_thenReturns200() throws Exception {
        PersonneEntity personne = new PersonneEntity(1L, "Bibou", LocalDate.parse("2022-03-14"), 180);
        personneRepository.save(personne);

        mockMvc.perform(get("/personne/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Bibou")))
                .andExpect(jsonPath("$.height", is(180)))
                .andExpect(jsonPath("$.birthdate", is("2022-03-14")));
    }

    @Test
    void whenCreate_thenReturns201() throws Exception {
        String json = "{\"name\":\"Geoffrey\", \"birthdate\":\"2023-12-10\", \"height\":180}";

        mockMvc.perform(post("/personne/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isOk());
    }

    @Test
    void testNameValidation() throws Exception {
        String invalidNameJson = "{\"name\":\"\", \"birthdate\":\"2023-12-10\", \"height\":180}";

        mockMvc.perform(post("/personne/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidNameJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdate_whenOK() throws Exception {
        PersonneEntity personne = new PersonneEntity(1L, "Bibou", LocalDate.parse("2022-03-14"), 180);
        personneRepository.save(personne);
        String update = """
                {"id":1,
                "name":"Bébou",
                "birthdate":"2023-10-01",
                "height":19
                }
                """;


        mockMvc.perform(put("/personne/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(update))
                .andExpect(status().isOk());

        mockMvc.perform(get("/personne/{id}", 1L))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Bébou")))
                .andExpect(jsonPath("$.height", is(19)))
                .andExpect(jsonPath("$.birthdate", is("2023-10-01")));
    }
}