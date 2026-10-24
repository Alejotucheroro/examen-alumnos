package com.upn.alumnos.service;

import com.upn.alumnos.model.Alumno;
import com.upn.alumnos.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Capa de lógica de negocio: el controller llama a estos métodos
 * y el service usa el repository para leer y escribir en MySQL.
 */
@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public List<Alumno> listarTodos() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    public Alumno registrar(Alumno alumno) {
        alumno.setId(null); // el id siempre lo genera MySQL
        return alumnoRepository.save(alumno);
    }

    public Optional<Alumno> actualizar(Long id, Alumno datos) {
        return alumnoRepository.findById(id)
                .map(alumno -> {
                    alumno.setNombres(datos.getNombres());
                    alumno.setCarrera(datos.getCarrera());
                    alumno.setCiclo(datos.getCiclo());
                    alumno.setPromedio(datos.getPromedio());
                    return alumnoRepository.save(alumno);
                });
    }

    public boolean eliminar(Long id) {
        if (!alumnoRepository.existsById(id)) {
            return false;
        }
        alumnoRepository.deleteById(id);
        return true;
    }
}
