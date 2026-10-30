# Casos de prueba - Grupo 5

Datos base (opcion 8 del menu): A (director) supervisa a B; B supervisa a C.

| # | Descripcion | Entrada | Resultado esperado | Resultado obtenido |
|---|---|---|---|---|
| 1 | Nivel en cadena A -> B -> C (caso de referencia) | Opcion 8, luego opcion 5 con ID `C` | Nivel de C: 2 | `Nivel de C: 2` |
| 2 | ID de empleado duplicado | Registrar empleado `A` dos veces | Error por ID repetido | `ERROR: El ID 'A' ya esta registrado.` |
| 3 | Autosupervision | Opcion 3: empleado `B`, supervisor `B` | Rechazada | `ERROR: Un empleado no puede supervisarse a si mismo.` |
| 4 | Ciclo en la jerarquia | Opcion 3: empleado `B`, supervisor `C` | Rechazada por ciclo | `ERROR: Operacion rechazada: Carla Mena ya depende de Bruno Salazar y se crearia un ciclo.` |
| 5 | Subordinados directos | Opcion 4 con ID `A` | Solo B | `Subordinados directos: - B - Bruno Salazar [Jefe de Sistemas, Sistemas]` |
| 6 | Asignar supervisor al director raiz | Opcion 3: empleado `A`, supervisor `C` | Rechazada | `ERROR: El director raiz no puede tener supervisor.` |
| 7 | Salario invalido | Registrar empleado con salario `-5` | Rechazado | `ERROR: El salario base debe ser mayor que cero.` |
| 8 | Opcion de menu invalida | Escribir `zz` | Mensaje de opcion invalida | `Opcion invalida. Elija un numero del menu.` |
| 9 | Nivel de empleado sin conexion a la raiz | Registrar `D` sin supervisor, opcion 5 con ID `D` | Error | `ERROR: El empleado aun no esta conectado al director raiz (su cadena termina en Diego).` |
| 10 | Cadena de supervisores de C | Opcion 6 con ID `C` | B y luego A | `-> B - Bruno Salazar ... -> A - Ana Torres ...` |

Los resultados obtenidos corresponden a una ejecucion real del programa. Vuelve a ejecutarlos
tu en VS Code y anota una captura para el README.
