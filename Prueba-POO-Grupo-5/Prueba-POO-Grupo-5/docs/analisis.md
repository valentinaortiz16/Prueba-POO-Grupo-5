# Analisis

## Problema

Una empresa requiere representar relaciones entre empleados y supervisores.

## Entradas

- Identificador unico, nombre, departamento y cargo de cada empleado.
- Identificador del director raiz.
- Pares (empleado, supervisor) para asignar supervisores.
- Identificadores para consultas de subordinados, nivel, cadena de supervisores y ancestro comun.

## Procesos

- Registro: se rechaza un identificador repetido, un nombre vacio o un cargo vacio.
- Asignacion de supervisor: se rechaza si el empleado es el director raiz, si se supervisa a si mismo o si el supervisor propuesto es subordinado del empleado (ciclo). Si el empleado ya tenia supervisor, se retira de la lista del anterior.
- Subordinados directos: lista almacenada en cada supervisor.
- Empleados por cargo: lista almacenada en cada Cargo.
- Nivel: cantidad de saltos de supervisor hasta llegar a quien no tiene supervisor.
- Cadena de supervisores: recorrido hacia la raiz.
- Ancestro comun: se igualan los niveles de ambos empleados y se sube en paralelo hasta coincidir.

## Salidas

- Mensajes de confirmacion o de error.
- Listas de empleados, nivel numerico y ancestro comun.

## Decisiones de diseno

- Cada Empleado conoce a su supervisor y a sus subordinados directos, lo que permite validar ciclos recorriendo la cadena.
- Organizacion administra el mapa de empleados, los departamentos, los cargos y el director raiz; Main solo muestra el menu.
- El nivel se calcula bajo demanda, evitando valores desactualizados al reasignar supervisores.
- Las reglas de negocio lanzan IllegalArgumentException y Main las muestra como mensajes.
