package be.bstorm.formation.demotesting.demoApp;

import java.time.LocalDate;

public record Personne(
        Long id,
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
