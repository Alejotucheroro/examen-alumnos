import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Alumno } from '../models/alumno';

// En Codespaces el frontend corre en "...-4200.app.github.dev"
// y el backend en "...-8080.app.github.dev": se reemplaza el puerto.
function construirUrlApi(): string {
  const host = window.location.hostname;
  if (host.endsWith('.app.github.dev')) {
    return `https://${host.replace('-4200.', '-8080.')}/api/alumnos`;
  }
  return 'http://localhost:8080/api/alumnos';
}

@Injectable({ providedIn: 'root' })
export class AlumnoService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = construirUrlApi();

  // GET /api/alumnos
  listar(): Observable<Alumno[]> {
    return this.http.get<Alumno[]>(this.apiUrl);
  }

  // DELETE /api/alumnos/{id}  (la API responde 204 No Content)
  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
