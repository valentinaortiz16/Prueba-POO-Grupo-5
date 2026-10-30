# Analisis - Grupo 5: Jerarquia de empleados

## 1. Problema
Una empresa necesita representar las relaciones entre empleados y supervisores:
quien supervisa a quien, cuantos niveles hay desde el director y quienes son los
subordinados de cada persona, evitando datos inconsistentes (autosupervision y ciclos).

## 2. Entradas
| Dato | Tipo | Validacion |
|---|---|---|
| ID del empleado | Texto | No vacio, unico (se guarda en mayusculas) |
| Nombre | Texto | No vacio |
| Cargo | Texto | No vacio |
| Salario base | Decimal | Mayor que 0 |
| Departamento | Texto | Debe existir previamente |
| ID del supervisor | Texto | Debe existir |
| Opcion del menu | Entero | Numero valido del menu |

## 3. Procesos
1. **Registrar empleado:** valida datos, crea `Cargo` y `Empleado`, lo agrega a la
   organizacion y a su departamento. El primer empleado registrado es el director raiz.
2. **Asignar supervisor:** verifica existencia, que el empleado no sea el director,
   que no se supervise a si mismo y que no se forme un ciclo.
3. **Listar subordinados directos:** recorre los empleados y filtra los que tienen
   como supervisor al empleado consultado.
4. **Calcular nivel:** sube por la cadena de supervisores contando saltos hasta la raiz
   (director = 0).
5. **Consultar cadena de supervisores:** sube por la cadena guardando cada supervisor.
6. **Listar por departamento:** consulta la lista del departamento.

**Deteccion de ciclos:** antes de asignar que S supervise a E, se sube por la cadena de S.
Si aparece E en esa cadena, entonces S ya depende de E y asignarlo crearia un ciclo.
Esto tambien cubre la autosupervision (S == E).

## 4. Salidas
Mensajes de confirmacion, listas de empleados, el nivel (numero entero), la cadena de
supervisores y mensajes de error claros ante datos o operaciones invalidas.

## 5. Decisiones de diseno
- **Cuatro clases de dominio** (`Cargo`, `Empleado`, `Departamento`, `Organizacion`) mas `Main`.
  `Cargo` es la clase adicional: separa la informacion del puesto y se relaciona con
  `Empleado` por composicion.
- **Main no tiene logica de negocio:** solo lee datos, llama a `Organizacion` y muestra resultados.
- **Atributos privados** con getters; `Departamento` devuelve copias de su lista para
  proteger su estado interno.
- **Validaciones con excepciones** (`IllegalArgumentException` para datos invalidos,
  `IllegalStateException` para operaciones invalidas); `Main` las captura y muestra el mensaje.
- **Estructura de datos:** `ArrayList`, suficiente para el tamano del problema.
- **Empleado sin supervisor asignado:** puede existir, pero su nivel no se calcula
  hasta conectarlo con la raiz.
