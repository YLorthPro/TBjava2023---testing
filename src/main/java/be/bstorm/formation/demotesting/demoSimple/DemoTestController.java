package be.bstorm.formation.demotesting.demoSimple;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoTestController {
    @GetMapping("demo/phrase")
    public ResponseEntity<String> getPhrase(){
        return ResponseEntity.ok("Coucou les javas");
    }
}
