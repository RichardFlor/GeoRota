package com.georota.georota;

import com.georota.georota.maps.services.Cidade;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GeorotaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeorotaApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(Cidade cidade) {
        return args -> {
            cidade.adicionarLocal("Praça da Sé", "Se, São Paulo - SP, 01001-000");
            cidade.adicionarLocal("MorumBIS", "Praça Roberto Gomes Pedrosa, 1 - Morumbi, São Paulo - SP, 05653-070");
            cidade.adicionarLocal("Parque Ibirapuera", "Av. Pedro Álvares Cabral - Vila Mariana, São Paulo - SP, 04094-050");
            cidade.conectarElos("Praça da Sé", "MorumBIS");
            cidade.conectarElos("Praça da Sé", "Parque Ibirapuera");
        };
    }
}