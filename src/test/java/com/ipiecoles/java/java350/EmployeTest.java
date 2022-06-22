package com.ipiecoles.java.java350;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EmployeTest {

    @Test
    public void testCheckBadYear(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now());
        
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbauchePassee(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().minusYears(2));
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(2);
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheFuture(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(LocalDate.now().plusYears(2));
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheNull(){
        //Given
        Employe employe = new Employe();
        employe.setDateEmbauche(null);
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isZero();
    }

    @Test
    public void testGetPrimeAnnuelle(){
        //Given
        Employe employe = new Employe("Doe", "John", "M12345", LocalDate.now(), 2500d, Entreprise.PERFORMANCE_BASE, 1d);

        //When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(1700d);
    }

    @ParameterizedTest
    @CsvSource({
            "'M12345',0,1,1.0,1700.0",
            "'T12345',0,1,1,0,1000.0",
            "'T12345',0,2,1,0,2500.0"
    })
    public void testGetPrimeAnnuelle(String matricule,
                                     Integer nbAnneesAnciennete,
                                     Integer performance,
                                     Double tauxTravail,
                                     Double primeCalculee){
        //Given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(nbAnneesAnciennete),
        2500d, performance, tauxTravail);
        //When
        Double prime = employe.getPrimeAnnuelle();
        //Then
        Assertions.assertThat(prime).isEqualTo(primeCalculee);
    }
}
