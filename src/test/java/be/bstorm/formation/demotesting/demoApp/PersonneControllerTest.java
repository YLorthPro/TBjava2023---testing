package be.bstorm.formation.demotesting.demoApp;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
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
    public void create_whenOK() throws Exception{
        doNothing().when(personneService).create(any(Personne.class));

        mockMvc = MockMvcBuilders.standaloneSetup(personneController).build();

        String json = "{\"name\":\"Geoffrey\", \"birthdate\":\"2023-12-10\", \"height\":180}";
        mockMvc.perform(post("/personne/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

        verify(personneService, times(1)).create(any(Personne.class));
    }

    @Test
    public void create_when_nameTooShort() throws Exception{
        doNothing().when(personneService).create(any(Personne.class));

        mockMvc = MockMvcBuilders.standaloneSetup(personneController).build();

        String json = "{\"name\":\"Ly\", \"birthdate\":\"2023-12-10\", \"height\":180}";
        mockMvc.perform(post("/personne/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isBadRequest());

        verify(personneService, times(0)).create(any(Personne.class));
    }

    @Test
    public void getAllPersonnesTest() throws Exception {
        when(personneService.getAll()).thenReturn(Arrays.asList(new PersonneEntity(), new PersonneEntity()));
        mockMvc = MockMvcBuilders.standaloneSetup(personneController).build();
        mockMvc.perform(get("/personne/all")).andExpect(status().isOk());
        verify(personneService, times(1)).getAll();
    }

    @Test
    public void updatePersonneTest() throws Exception {
        doNothing().when(personneService).update(any(Personne.class),anyLong());

        mockMvc = MockMvcBuilders.standaloneSetup(personneController).build();

        mockMvc.perform(put("/personne/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\", \"name\":\"Updated Name\", \"age\":30, \"city\":\"Updated City\"}"))
                .andExpect(status().isOk());

        verify(personneService, times(1)).update(any(Personne.class), anyLong());
    }

    // Cas de test pour la méthode delete(anyLong()) de PersonneService lors de la visite de l'endpoint /personne/delete/{id}.
    // On crée une configuration autonome, on appelle l'endpoint et on attend un status OK
    @Test
    public void deletePersonneTest() throws Exception {
        doNothing().when(personneService).delete(anyLong());
        mockMvc = MockMvcBuilders.standaloneSetup(personneController).build();
        mockMvc.perform(delete("/personne/1")).andExpect(status().isOk());
        verify(personneService, times(1)).delete(anyLong());
    }
}
