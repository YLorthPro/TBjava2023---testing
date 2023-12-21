package be.bstorm.formation.demotesting.demoApp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonneRepository extends JpaRepository<PersonneEntity, Long> {
}