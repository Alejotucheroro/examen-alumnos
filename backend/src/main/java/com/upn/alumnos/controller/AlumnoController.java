package com.upn.alumnos.controller;

import com.upn.alumnos.model.Alumno;
import com.upn.alumnos.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Expone la API REST en /api/alumnos y devuelve
 * el código HTTP adecuado en cada operación.
 */
@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // GET /api/alumnos -> 200 OK con la lista
    @GetMapping
    public List<Alumno> listar() {
        return alumnoService.listarTodos();
    }

    // GET /api/alumnos/{id} -> 200 OK o 404 Not Found
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> buscarPorId(@PathVariable Long id) {
        return alumnoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/alumnos -> 201 Created
    @PostMapping
    public ResponseEntity<Alumno> registrar(@RequestBody Alumno alumno) {
        Alumno guardado = alumnoService.registrar(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    // PUT /api/alumnos/{id} -> 200 OK o 404 Not Found
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizar(@PathVariable Long id, @RequestBody Alumno alumno) {
        return alumnoService.actualizar(id, alumno)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/alumnos/{id} -> 204 No Content o 404 Not Found
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!alumnoService.eliminar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
