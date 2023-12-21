package be.bstorm.formation.demotesting.demoApp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PersonneControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PersonneServiceImpl personneService;

    @InjectMocks
    private PersonneController personneController;

    @Test
    public void getOne() throws Exception{
        when(personneService.getById(anyLong())).thenReturn(Optional.of(new PersonneEntity()));
        mockMvc = MockMvcBuilders.standaloneSetup(personneController).build();
        mockMvc.perform(get("/personne/1")).andExpect(status().isOk());

        verify(personneService, times(1)).getById(anyLong());
    }

    @Test
    public void create() throws Exception{
        doNothing().when(personneService).create(any(Personne.class));

        mockMvc = MockMvcBuilders.standaloneSetup(personneController).build();

        String json = "{\"name\":\"Geoffrey\", \"birthdate\":\"2023-12-10\", \"height\":180}";
        mockMvc.perform(post("/personne/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

        verify(personneService, times(1)).create(any(Personne.class));
    }
}
