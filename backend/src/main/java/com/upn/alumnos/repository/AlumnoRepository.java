package com.upn.alumnos.repository;

import com.upn.alumnos.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Al extender JpaRepository<Alumno, Long>, Spring Data genera automáticamente
 * findAll, findById, save, existsById y deleteById sin escribir SQL.
 */
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
}
