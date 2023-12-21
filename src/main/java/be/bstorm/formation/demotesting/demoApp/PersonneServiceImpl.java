package be.bstorm.formation.demotesting.demoApp;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneServiceImpl implements PersonneService{

    private final PersonneRepository personneRepository;

    public PersonneServiceImpl(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }


    @Override
    public Optional<PersonneEntity> getById(Long id) {
        return personneRepository.findById(id);
    }

    @Override
    public List<PersonneEntity> getAll() {
        return personneRepository.findAll();
    }

    @Override
    public void create(Personne form) {

        if(form == null)
            throw new IllegalArgumentException("Pas cool");

        PersonneEntity entity = new PersonneEntity();
        entity.setId(form.id());
        entity.setName(form.name());
        entity.setBirthdate(form.birthdate());
        entity.setHeight(form.height());

        personneRepository.save(entity);

    }

    @Override
    public void update(Personne form, Long id) {

        if(form == null)
            throw new IllegalArgumentException("Pas cool");

        Optional<PersonneEntity> optionalEntity = personneRepository.findById(id);
        if(optionalEntity.isPresent()) {
            PersonneEntity entity = optionalEntity.get();
            entity.setId(form.id());
            entity.setName(form.name());
            entity.setBirthdate(form.birthdate());
            entity.setHeight(form.height());
            personneRepository.save(entity);
        } else
            throw new EntityNotFoundException("Toujours pas cool");

    }

    @Override
    public void delete(Long id) {
        if(personneRepository.existsById(id))
            personneRepository.deleteById(id);
    }
}
