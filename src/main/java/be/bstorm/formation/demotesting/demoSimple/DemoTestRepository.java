package be.bstorm.formation.demotesting.demoSimple;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemoTestRepository extends JpaRepository<DemoTest, Long> {
    List<DemoTest> findByPhrase(String phrase);
}