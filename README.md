# Examen práctico – Gestión académica de alumnos

Soluciones Web y Aplicaciones Distribuidas – UPN

| Carpeta | Contenido |
| --- | --- |
| [`backend/`](backend) | API REST Spring Boot + JPA + MySQL 8 (Docker) |
| [`frontend/`](frontend) | Frontend Angular que lista y elimina alumnos |

## Ejecutar en GitHub Codespaces

```bash
bash iniciar.sh
```

El script crea o inicia MySQL en Docker, levanta el backend en el puerto **8080** (Public)
y el frontend en el puerto **4200**. El puerto **3306** (MySQL) debe quedar **Private**.

## Endpoints del backend

| Método | Endpoint | Respuesta |
| --- | --- | --- |
| GET | `/api/alumnos` | 200 – lista de alumnos |
| GET | `/api/alumnos/{id}` | 200 o 404 |
| POST | `/api/alumnos` | 201 Created |
| PUT | `/api/alumnos/{id}` | 200 o 404 |
| DELETE | `/api/alumnos/{id}` | 204 No Content o 404 |

`estadoAcademico` es un atributo calculado: **REGULAR** si `promedio >= 13`, **EN RIESGO** si `promedio < 13`.

## Datos de prueba (Thunder Client / curl)

```json
{"nombres":"Ana Torres","carrera":"Ingeniería de Sistemas","ciclo":5,"promedio":16.5}
{"nombres":"Luis Pérez","carrera":"Ingeniería Industrial","ciclo":4,"promedio":11.8}
{"nombres":"María Rojas","carrera":"Ingeniería de Sistemas","ciclo":6,"promedio":14.2}
```
