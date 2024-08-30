package com.example.user_api.service;

import com.example.user_api.model.User;
import com.example.user_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Servicio de usuario que maneja la lógica de negocio relacionada con los
 * usuarios.
 * 
 * La anotación @Service indica que esta clase es un servicio de Spring, lo que
 * permite
 * la inyección de dependencias y el manejo de transacciones.
 */
@Service
public class UserService {

    /**
     * Repositorio para realizar operaciones CRUD con la entidad User.
     * La anotación @Autowired permite la inyección automática de dependencias.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Expresiones regulares para validar el formato del correo electrónico y la
     * contraseña.
     * Estas se inyectan desde el archivo de configuración.
     */
    private final Pattern emailPattern;
    private final Pattern passwordPattern;

    /**
     * Constructor que inicializa los patrones de validación usando los valores
     * proporcionados en el archivo de configuración.
     * 
     * @param emailRegex    Expresión regular para validar el formato del correo
     *                      electrónico.
     * @param passwordRegex Expresión regular para validar el formato de la
     *                      contraseña.
     */
    public UserService(
            @Value("${user.email.regex}") String emailRegex,
            @Value("${user.password.regex}") String passwordRegex) {
        this.emailPattern = Pattern.compile(emailRegex);
        this.passwordPattern = Pattern.compile(passwordRegex);
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param user El usuario a registrar.
     * @return El usuario registrado.
     * @throws Exception Si el formato del correo o la contraseña es inválido, o si
     *                   el correo ya está registrado.
     */
    public User register(User user) throws Exception {
        // Validar el formato del correo electrónico
        if (!isValidEmail(user.getEmail())) {
            throw new Exception(
                    "{\"mensaje\": \"El formato del correo es inválido. Recuerda que debe ser \\\"tucorreo@dominio.abc\\\".\"}");
        }

        // Validar el formato de la contraseña
        if (!isValidPassword(user.getPassword())) {
            throw new Exception(
                    "{\"mensaje\": \"El formato de la contraseña es inválido. Recuerda que debe tener al menos una letra mayúscula, una minúscula, un dígito, un caracter especial y 8 caracteres como mínimo.\"}");
        }

        // Validar si el correo ya existe
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("{\"mensaje\": \"El correo ya registrado\"}");
        }

        // Configurar los campos del usuario
        user.setId(UUID.randomUUID()); // Genera un nuevo UUID para el usuario
        user.setCreated(LocalDateTime.now()); // Establece la fecha de creación
        user.setModified(LocalDateTime.now()); // Establece la fecha de la última modificación
        user.setLastLogin(LocalDateTime.now()); // Establece la fecha del último inicio de sesión
        user.setToken(UUID.randomUUID().toString()); // Genera un nuevo token para el usuario
        user.setActive(true); // Marca al usuario como activo

        // Guarda el usuario en la base de datos
        return userRepository.save(user);
    }

    /**
     * Valida el formato del correo electrónico usando una expresión regular.
     * 
     * @param email El correo electrónico a validar.
     * @return True si el correo es válido, false en caso contrario.
     */
    private boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Valida el formato de la contraseña usando una expresión regular.
     * 
     * @param password La contraseña a validar.
     * @return True si la contraseña es válida, false en caso contrario.
     */
    private boolean isValidPassword(String password) {
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }
}