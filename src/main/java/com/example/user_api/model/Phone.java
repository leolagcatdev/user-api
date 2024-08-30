package com.example.user_api.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

/**
 * Entidad que representa un número de teléfono asociado a un usuario.
 * 
 * La anotación @Entity indica que esta clase es una entidad JPA que se
 * mapeará a una tabla en la base de datos.
 * 
 * La anotación @Data de Lombok genera automáticamente los métodos getter,
 * setter,
 * equals(), hashCode() y toString() para esta clase.
 */
@Entity
@Data
public class Phone {

    /**
     * Identificador único del teléfono.
     * Se genera automáticamente usando la estrategia AUTO, lo que permite que la
     * base de datos elija la estrategia de generación de ID adecuada.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    //Número de teléfono.
    private String number;

    //Código de área de la ciudad.
    private String citycode;

    //Código del país.
    private String contrycode;
}