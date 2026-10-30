# Prueba-POO-Grupo-5: Jerarquia de empleados

## Integrantes

- Integrante 1
- Integrante 2
- Integrante 3

## Objetivo

Representar las relaciones entre empleados y supervisores de una empresa: registrar empleados con identificador unico, asignar supervisores, listar subordinados directos, calcular el nivel de cada empleado desde el director raiz, evitar autosupervision y ciclos, y consultar la cadena de supervisores.

## Clases y metodos

Empleado
- calcularNivel(): cantidad de supervisores hasta la raiz.
- cadenaSupervisores(): lista de supervisores desde el directo hasta el director raiz.
- esSubordinadoDe(otro): verifica si otro esta en su cadena.
- asignarSupervisor(nuevo): valida autosupervision y ciclos, y actualiza ambas listas.
- ancestroComun(otro): supervisor comun mas cercano, equiparando niveles y subiendo en paralelo (idea tomada de LCA.java).

Departamento
- agregarEmpleado(empleado), getEmpleados().

Cargo
- agregarEmpleado(empleado), getEmpleados(): puesto que ocupa cada empleado (Director, Gerente, Analista, etc.).

Organizacion
- registrarEmpleado(id, nombre, departamento, cargo), designarDirector(id), asignarSupervisor(idEmpleado, idSupervisor), subordinadosDirectos(id), nivel(id), cadenaSupervisores(id), ancestroComun(idA, idB), empleadosPorCargo(cargo), listarEmpleados().

Main
- Menu por consola. Solo coordina la entrada y salida; los calculos y validaciones estan en las clases anteriores.

## Compilacion y ejecucion

    cd src
    javac -d ../out *.java
    java -cp ../out Main

## Capturas

Agregar capturas de pantalla de la ejecucion en la carpeta docs/ y enlazarlas aqui.

## Distribucion de responsabilidades

- Integrante 1: clase Empleado y validaciones.
- Integrante 2: clases Departamento, Cargo y Organizacion.
- Integrante 3: Main, pruebas y documentacion.

## Referencia

Idea del ancestro comun mas cercano inspirada en LCA.java de Steven Halim, Felix Halim y Suhendry Effendy (https://github.com/stevenhalim/cpbook-code).
