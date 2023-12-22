package be.bstorm.formation.demotesting.demoApp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record Personne(
        Long id,
        @Size(min = 3)
        String name,
        int height,
        LocalDate birthdate
) {
    public static Personne fromBll(PersonneEntity entity){
        return new Personne(
                entity.getId(),
                entity.getName(),
                entity.getHeight(),
                entity.getBirthdate()
        );
    }
}
