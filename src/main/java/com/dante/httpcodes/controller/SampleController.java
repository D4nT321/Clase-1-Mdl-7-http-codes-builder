// Define el paquete donde está ubicado este controlador.
// Sirve para organizar el proyecto.
package com.dante.httpcodes.controller;

// Importa la anotación RequestMapping de Spring.
// Se usa para definir la ruta base de un controlador o método.
import org.springframework.web.bind.annotation.RequestMapping;

// Importa la anotación RestController.
// Indica que esta clase es un controlador REST.
import org.springframework.web.bind.annotation.RestController;

// Importa la clase House del paquete model.
// Se usa más adelante para crear objetos House.
import com.dante.httpcodes.model.House;

// Importa la clase URI.
// Se usa para construir la URL de respuesta cuando se crea un recurso.
import java.net.URI;

// Importa la clase ArrayList.
// Se usa para crear listas dinámicas.
import java.util.ArrayList;

// Importa la interfaz List.
// Se usa para declarar listas de forma genérica.
import java.util.List;

// Importa la clase HttpStatus.
// Contiene códigos de estado HTTP (200, 204, 400, etc.).
import org.springframework.http.HttpStatus;

// Importa HttpStatusCode.
// Representa códigos de estado HTTP (no se usa directamente aquí).


// Importa ResponseEntity.
// Se usa para construir respuestas HTTP completas (estado + cuerpo).
import org.springframework.http.ResponseEntity;

// Importa la anotación GetMapping.
// Se usa para manejar peticiones HTTP GET.
import org.springframework.web.bind.annotation.GetMapping;

// Importa la anotación PathVariable.
// Se usa para capturar valores de la URL.
import org.springframework.web.bind.annotation.PathVariable;

// Importa la anotación PostMapping.
// Se usa para manejar peticiones HTTP POST.
import org.springframework.web.bind.annotation.PostMapping;

// Importa la anotación RequestBody.
// Se usa para recibir datos enviados en el cuerpo de la petición.
import org.springframework.web.bind.annotation.RequestBody;

// Indica que esta clase es un controlador REST.
@RestController

// Define la ruta base para todos los endpoints de esta clase.
@RequestMapping("/api/sample")
public class SampleController {

    // Declara una lista de String.
    // Se inicializa con los valores "Hola" y "Mundo".
    List<String> list = new ArrayList<>(List.of("Hola", "Mundo"));

    // Maneja peticiones GET a /api/sample
    @GetMapping
    public ResponseEntity<List<String>> getAll() {

        // Si la lista está vacía
        if (list.isEmpty()) {

            // Devuelve una respuesta HTTP 204 (NO CONTENT) sin cuerpo
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Si la lista tiene elementos,
        // devuelve HTTP 200 (OK) con la lista como cuerpo
        return ResponseEntity.ok(list);
    }

    // Maneja peticiones GET a /api/sample/{index}
    @GetMapping("/{index}")
    public ResponseEntity<String> getByIndex(@PathVariable String index) {

        try {
            // Convierte el índice recibido como String a entero
            var indexInList = Integer.parseInt(index);

            // Obtiene el elemento de la lista según el índice
            var element = list.get(indexInList);

            // Devuelve HTTP 200 (OK) con el elemento encontrado
            return ResponseEntity.ok(element);

        } catch (IndexOutOfBoundsException e) {
            // Si el índice está fuera del rango de la lista,
            // devuelve HTTP 204 (NO CONTENT)
            return ResponseEntity.noContent().build();

        } catch (NumberFormatException e) {
            // Si el índice no se puede convertir a número,
            // devuelve HTTP 400 (BAD REQUEST) con un mensaje
            return ResponseEntity.badRequest()
                    .body("El indice no pueder ser convertido a numero");
        }
    }

    // Maneja peticiones POST a /api/sample
    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody String entity) {

        // Se crea un objeto House usando el patrón Builder.
        // Solo se asigna la dirección.
     /*
      */
      var h1 = House.builder()
                .address("Cra 1 # 2 -03")
                .build();

        // Ejemplo de cómo sería usando el constructor directamente
        // h1 = new House(null, null, "Cra 1 # 2 -03", false);

        // Se crea otro objeto House usando el Builder.
        // Se asignan varios atributos.
        var h2 = House.builder()
                .numWindows(4)
                .color("Azul")
                .haveGarage(true)
              .build();

        // Ejemplo usando el constructor
        // h2 = new House("AZUL", 4, null, true);

        // Guarda el tamaño actual de la lista
        // Este valor se usará como índice
        var index = list.size();

        // Agrega el valor recibido en la petición a la lista
        list.add(entity);

        
        // Devuelve una respuesta HTTP 201 (CREATED)
        // con la ubicación del nuevo recurso y el índice como cuerpo
        return ResponseEntity.created(URI.create("/api/sample/" + index))
                .body(index);
    }
}