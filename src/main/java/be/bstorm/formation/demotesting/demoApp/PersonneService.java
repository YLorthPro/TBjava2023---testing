package be.bstorm.formation.demotesting.demoApp;

import java.util.List;
import java.util.Optional;

public interface PersonneService {
    Optional<PersonneEntity> getById(Long id);
    List<PersonneEntity> getAll();
    void create(Personne form);
    void update(Personne form, Long id);
    void delete(Long id);
}
