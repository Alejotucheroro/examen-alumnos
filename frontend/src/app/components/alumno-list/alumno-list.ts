import { Component, OnInit, inject, signal } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import { Alumno } from '../../models/alumno';
import { AlumnoService } from '../../services/alumno';

@Component({
  selector: 'app-alumno-list',
  imports: [DecimalPipe],
  templateUrl: './alumno-list.html',
  styleUrl: './alumno-list.scss',
})
export class AlumnoList implements OnInit {
  // Inyección del servicio que se comunica con la API
  private readonly alumnoService = inject(AlumnoService);

  // Estado de la pantalla (signals: Angular actualiza la vista al cambiar)
  alumnos = signal<Alumno[]>([]);
  cargando = signal(false);
  error = signal('');

  // Al abrir el componente se cargan los alumnos
  ngOnInit(): void {
    this.cargarAlumnos();
  }

  cargarAlumnos(): void {
    this.cargando.set(true);
    this.error.set('');
    this.alumnoService.listar().subscribe({
      next: (datos) => {
        this.alumnos.set(datos);
        this.cargando.set(false);
      },
      error: () => {
        this.error.set('No se pudo conectar con el backend. Verifique que la API esté en ejecución.');
        this.cargando.set(false);
      },
    });
  }

  eliminar(alumno: Alumno): void {
    if (!confirm(`¿Desea eliminar al alumno ${alumno.nombres}?`)) {
      return;
    }
    this.alumnoService.eliminar(alumno.id).subscribe({
      // 204 No Content: se quita el alumno del listado sin recargar la página
      next: () => this.alumnos.update((lista) => lista.filter((a) => a.id !== alumno.id)),
      error: () => this.error.set(`No se pudo eliminar al alumno ${alumno.nombres}.`),
    });
  }
}
