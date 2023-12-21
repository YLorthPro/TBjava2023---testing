package be.bstorm.formation.demotesting.demoApp;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personne")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Personne>> getAllPersonne(){
        return ResponseEntity.ok(
                personneService.getAll().stream().map(Personne::fromBll).toList()
        );
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<Personne> getOne(@PathVariable Long id){
        return ResponseEntity.ok(Personne.fromBll(
                personneService.getById(id).orElseThrow(()->new EntityNotFoundException("Toujours pas trouvé"))
        ));
    }

    @PostMapping("/create")
    public void create(@RequestBody Personne form){
        personneService.create(form);
    }

    @PutMapping("/{id:[0-9]+}")
    public void update(@RequestBody Personne form, @PathVariable Long id){
        personneService.update(form, id);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public void delete(@PathVariable Long id){
        personneService.delete(id);
    }
}
