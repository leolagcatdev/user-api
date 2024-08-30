Este archivo `README.md` explica cómo clonar el repositorio, ejecutar la aplicación, y probar la API RESTful con Postman:

```markdown
# User API

Esta es una API RESTful para la gestión de usuarios. Permite registrar usuarios y valida tanto el correo electrónico como la contraseña con expresiones regulares configurables.

## Requisitos

- Java 8 o superior
- Maven 3.6+ (opcional, si prefieres usar Maven en lugar de Gradle)

## Clonar el Repositorio

1. Clona el repositorio utilizando Git:
    ```sh
    git clone https://github.com/leolagcatdev/user-api.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd user-api
    ```

## Ejecutar la Aplicación

### Usando Maven

1. Asegúrate de estar en el directorio raíz del proyecto.
2. Ejecuta la aplicación con Maven:
    ```sh
    mvn spring-boot:run
    ```

### Usando Gradle

1. Asegúrate de estar en el directorio raíz del proyecto.
2. Ejecuta la aplicación con Gradle:
    ```sh
    ./gradlew bootRun
    ```

La aplicación se ejecutará en `http://localhost:8080`.

## Probar la Aplicación

Puedes usar [Postman](https://www.postman.com/) para probar los endpoints de la API. Aquí te mostramos cómo probar el endpoint de registro de usuarios.

### Registro de Usuario

- **URL**: `http://localhost:8080/api/users/register`
- **Método**: `POST`
- **Headers**:
  - `Content-Type: application/json`
- **Body** (Ejemplo de JSON válido para el cuerpo de la solicitud):
    ```json
    {
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.org",
        "password": "Hunter2@password",
        "phones": [
            {
                "number": "1234567",
                "citycode": "1",
                "contrycode": "57"
            }
        ]
    }
    ```
- **Respuesta Esperada** (En caso de éxito):
    ```json
    {
        "id": "UUID del usuario",
        "name": "Juan Rodriguez",
        "email": "juan@rodriguez.org",
        "password": "Hashed password",
        "created": "Fecha de creación",
        "modified": "Fecha de modificación",
        "lastLogin": "Fecha del último ingreso",
        "token": "Token generado",
        "isActive": true,
        "phones": [
            {
                "id": "UUID del teléfono",
                "number": "1234567",
                "citycode": "1",
                "contrycode": "57"
            }
        ]
    }
    ```
- **Errores Comunes**:
  - **Formato de Correo Inválido**:
    ```json
    {
        "mensaje": "El formato del correo es inválido. Recuerda que debe ser \"tucorreo@dominio.abc\"."
    }
    ```
  - **Formato de Contraseña Inválido**:
    ```json
    {
        "mensaje": "El formato de la contraseña es inválido. Recuerda que debe tener al menos una letra mayúscula, una minúscula, un dígito, un carácter especial y 8 caracteres como mínimo."
    }
    ```
  - **Correo Ya Registrado**:
    ```json
    {
        "mensaje": "El correo ya registrado"
    }
    ```

## Acceder a la Consola H2

- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Usuario**: `sa`
- **Contraseña**: `password`

## Notas

- Asegúrate de que el archivo `application.properties` esté correctamente configurado para tu entorno de desarrollo.
- Las expresiones regulares para la validación de correos y contraseñas están configuradas en el archivo `application.properties` y pueden ser modificadas allí.


## Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).

```