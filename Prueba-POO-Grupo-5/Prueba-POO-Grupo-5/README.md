# Prueba-POO-Grupo-5: Jerarquia de empleados

**Universidad Tecnica de Ambato - FISEI - Ingenieria en Software**
**Asignatura:** Algoritmos y Logica de Programacion | **Docente:** Ing. Jose Ruben Caiza Caizabuano

## Integrantes
- Sandra Jazmin Alulema Chato
- (agregar nombres de los demas integrantes)

## Objetivo
Aplicacion de consola en Java que representa la jerarquia de una empresa mediante clases y
objetos: registra empleados con ID unico, asigna supervisores, lista subordinados, calcula el
nivel desde el director raiz y consulta la cadena de supervisores, evitando autosupervision y ciclos.

## Clases y metodos
| Clase | Responsabilidad | Metodos principales |
|---|---|---|
| `Cargo` | Nombre y salario base del puesto (clase adicional) | `getNombre()`, `getSalarioBase()` |
| `Empleado` | ID, nombre, cargo, departamento y supervisor | `getSupervisor()`, `setSupervisor()` |
| `Departamento` | Agrupa empleados | `agregarEmpleado()`, `getEmpleados()` |
| `Organizacion` | Reglas de negocio | `registrarEmpleado()`, `asignarSupervisor()`, `listarSubordinadosDirectos()`, `calcularNivel()`, `consultarCadenaSupervisores()`, `listarEmpleadosPorDepartamento()` |
| `Main` | Menu con `Scanner`, `switch` y ciclo `do-while` | solo interaccion con el usuario |

## Compilacion y ejecucion
```bash
cd src
javac *.java
java Main
```
En VS Code: abrir la carpeta, abrir `Main.java` y pulsar **Run**.

## Menu
```
1. Registrar departamento      6. Consultar cadena de supervisores
2. Registrar empleado          7. Listar empleados por departamento
3. Asignar supervisor          8. Cargar datos de ejemplo (A -> B -> C)
4. Listar subordinados         9. Listar todos los empleados
5. Calcular nivel              0. Salir
```
Para una prueba rapida: opcion **8** y luego opcion **5** con ID `C` (resultado: nivel 2).

## Validaciones
ID duplicado, campos vacios, salario <= 0, departamento o empleado inexistente,
autosupervision, ciclos, supervisor para el director raiz y opciones invalidas.

## Capturas
(Insertar aqui capturas de la ejecucion en VS Code.)

## Distribucion de responsabilidades
| Integrante | Responsabilidad |
|---|---|
| (nombre 1) | `Cargo` y `Empleado` |
| (nombre 2) | `Departamento` y registro en `Organizacion` |
| (nombre 3) | Supervision, niveles, cadena y validaciones |
| (nombre 4) | `Main`, documentacion y diagrama |

## Documentacion
- [Analisis](docs/analisis.md)
- [Casos de prueba](docs/casos_prueba.md)
- [Diagrama de clases](docs/diagrama_clases.png)
