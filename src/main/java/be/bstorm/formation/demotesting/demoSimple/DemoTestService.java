package be.bstorm.formation.demotesting.demoSimple;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DemoTestService {
    public String concatenation(String motA, String motB){
        return motA+" "+motB;
    }

    public int diviser(int a, int b){
        if(b==0)
            throw new ArithmeticException("Pas cool");

        return a/b;
    }

    public int addition(int a, int b){
        return a+b;
    }

    public List<String> split(String phrase){
        return Arrays.asList(phrase.split(" "));
    }

    public boolean isLowercase (String phrase){
        return phrase.equals(phrase.toLowerCase());
    }
}
