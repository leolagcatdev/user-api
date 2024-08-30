package com.example.user_api.model;

import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * User es una entidad JPA que representa a un usuario en el sistema.
 * 
 * La anotación @Entity indica que esta clase es una entidad JPA que se mapeará
 * a una
 * tabla en la base de datos. La anotación @Table define el nombre de la tabla
 * en la base
 * de datos que se asociará con esta entidad.
 */

@Entity
@Data
@Table(name = "users")
public class User {
    // Definiciones de campos y métodos

    /**
     * Identificador único del usuario.
     * Se usa UUID como identificador para asegurar unicidad global.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Nombre del usuario.
    private String name;

    /*
     * Correo electrónico del usuario.
     * La anotación @Column(unique = true) asegura que el correo sea único en la
     * base de datos.
     */
    @Column(unique = true)
    private String email;

    // Contraseña del usuario.
    private String password;

    // Fecha y hora en la que se creó el usuario.
    private LocalDateTime created;

    // Fecha y hora de la última modificación del usuario.
    private LocalDateTime modified;

    // Fecha y hora del último inicio de sesión del usuario.
    private LocalDateTime lastLogin;

    // Token de acceso del usuario.
    private String token;

    // Indica si el usuario está activo en el sistema.
    private boolean isActive;

    /**
     * Lista de teléfonos asociados al usuario.
     * La relación se establece como @OneToMany, lo que significa que un usuario
     * puede tener
     * múltiples teléfonos.
     * La anotación @Cascade.ALL asegura que todas las operaciones de persistencia
     * (guardar,
     * actualizar, eliminar) se propagan a la lista de teléfonos.
     * La anotación @Fetch(FetchType.EAGER) carga inmediatamente la lista de
     * teléfonos al cargar
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Phone> phones;
}