package com.example.user_api.controller;

import com.example.user_api.model.User;
import com.example.user_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * UserController es el controlador REST que maneja las solicitudes HTTP
 * relacionadas
 * con la entidad User.
 * 
 * La anotación @RestController indica que esta clase es un controlador donde
 * cada método devuelve un objeto que se serializa automáticamente en JSON.
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Inyección de dependencia del servicio UserService
    @Autowired
    private UserService userService;

    /**
     * Constructor para inyectar UserService.
     * 
     * @param userService el servicio de usuarios que maneja la lógica de negocio.
     * 
     *                    - Utilizar la inyección de dependencias a través del
     *                    constructor en lugar de la
     *                    inyección de campos. Esto facilita la creación de pruebas
     *                    unitarias y asegura que
     *                    las dependencias sean inyectadas adecuadamente.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para registrar un nuevo usuario.
     * 
     * @param user el objeto User recibido en el cuerpo de la solicitud.
     * @return ResponseEntity con el usuario creado y el código de estado HTTP.
     * 
     *         - @Valid asegura que el objeto User sea validado según las
     *         restricciones definidas
     *         en la clase User.
     *         - @RequestBody indica que el cuerpo de la solicitud HTTP debe ser
     *         convertido a un
     *         objeto User.
     * 
     *         - Manejar excepciones específicas en lugar de capturar una excepción
     *         general.
     *         - Utilizar ResponseEntity para controlar el contenido de la respuesta
     *         y el estado HTTP.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            // Registrar el usuario usando el servicio
            User newUser = userService.register(user);
            // Retornar el usuario creado con el estado HTTP 201 (CREATED)
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            // Retornar el mensaje de error con el estado HTTP 400 (BAD REQUEST)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}