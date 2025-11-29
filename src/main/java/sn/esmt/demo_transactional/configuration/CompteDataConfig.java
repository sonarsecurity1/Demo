package sn.esmt.demo_transactional.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sn.esmt.demo_transactional.model.Compte;
import sn.esmt.demo_transactional.repository.CompteRepository;

import java.util.Arrays;

@Configuration
public class CompteDataConfig {

    @Bean
    public CommandLineRunner initComptes(CompteRepository compteRepository) {
        return (args) -> {
            // Données constantes pour les comptes
            compteRepository.save(new Compte("192983783","Pis", 650000.0));
            compteRepository.save(new Compte("137832937","Louise", 4350000.0));
            compteRepository.save(new Compte("397491749","Imana", 2750000.0));
        };
    }
}