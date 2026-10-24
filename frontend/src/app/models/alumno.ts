// Interface: define la forma del JSON que devuelve la API.
// Solo existe en tiempo de compilación (TypeScript), no genera código extra.
export interface Alumno {
  id: number;
  nombres: string;
  carrera: string;
  ciclo: number;
  promedio: number;
  estadoAcademico: 'REGULAR' | 'EN RIESGO';
}
