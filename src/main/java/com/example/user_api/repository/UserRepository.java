package com.example.user_api.repository;

import com.example.user_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * UserRepository es una interfaz que extiende JpaRepository para manejar
 * operaciones de persistencia relacionadas con la entidad User.
 * 
 * Al extender JpaRepository, obtienes métodos CRUD
 * ya implementados por Spring Data JPA, lo que facilita mucho la interacción
 * con la base de datos.
 */

public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Método para buscar un usuario por su correo electrónico.
     * 
     * @param email el correo electrónico del usuario.
     * @return un Optional que contiene el usuario si se encuentra, o está vacío si
     *         no.
     * 
     *         - Al retornar un Optional, se evita trabajar con valores nulos, lo
     *         que puede
     *         reducir posibles errores de NullPointerException.
     *         - El uso de métodos descriptivos como findByEmail hace que el código
     *         sea más legible y
     *         expresivo.
     */
    Optional<User> findByEmail(String email);
}