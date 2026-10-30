# Casos de prueba

Datos base: empleados 1-A (Direccion, Director), 2-B (Ventas, Gerente), 3-C (Ventas, Analista). Director raiz: 1. Supervisores: B supervisado por A, C supervisado por B.

| N | Entrada | Resultado esperado | Resultado obtenido |
|---|---------|--------------------|--------------------|
| 1 | Opcion 5, id 3 | Nivel: 2 | Nivel: 2 |
| 2 | Registrar id 1 por segunda vez | Error: Ya existe un empleado con el identificador 1. | Error: Ya existe un empleado con el identificador 1. |
| 3 | Asignar supervisor: empleado 2, supervisor 2 | Error: Un empleado no puede supervisarse a si mismo. | Error: Un empleado no puede supervisarse a si mismo. |
| 4 | Asignar supervisor: empleado 2, supervisor 3 | Error: La asignacion crearia un ciclo. | Error: La asignacion crearia un ciclo. |
| 5 | Asignar supervisor: empleado 1, supervisor 3 | Error: Al director raiz no se le puede asignar supervisor. | Error: Al director raiz no se le puede asignar supervisor. |
| 6 | Subordinados directos de 1; cadena de 3; ancestro comun de 2 y 3; empleados con cargo Analista | 2 - B (Ventas, Gerente); 2 - B (Ventas, Gerente) y 1 - A (Direccion, Director); 2 - B (Ventas, Gerente); 3 - C (Ventas, Analista) | 2 - B (Ventas, Gerente); 2 - B (Ventas, Gerente) y 1 - A (Direccion, Director); 2 - B (Ventas, Gerente); 3 - C (Ventas, Analista) |
