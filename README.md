# Prueba-POO-Grupo-5: Jerarquia de empleados

## Integrantes

- Alulema Chato Sandra Jazmin  
- Armas Jaya Anthony Daniel
- Guaman Sagñay Joselyn Maribel
- Ortiz Mosquera Valeria Valentina
- Vayas Masache Andres Gabriel 

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

<img width="1910" height="991" alt="{4DDB458F-7D6C-4C08-9737-FBA79D58E656}" src="https://github.com/user-attachments/assets/301db001-1db7-4eea-b538-76a49cc77cda" />
<img width="1916" height="995" alt="{DBF4BCB2-5AF3-4D7D-9356-1766200B84D5}" src="https://github.com/user-attachments/assets/ca998142-67a1-4473-9342-979d3a6332e7" />



## Distribucion de responsabilidades

- Alulema Chato Sandra Jazmin : diagrama_clases / Empleado.java  
- Armas Jaya Anthony Daniel : Departamento.java
- Guaman Sagñay Joselyn Maribel : análisis / Organización.java
- Ortiz Mosquera Valeria Valentina : casos_prueba / Main.java
- Vayas Masache Andres Gabriel : Cargo.java

## Referencia

Idea del ancestro comun mas cercano inspirada en LCA.java de Steven Halim, Felix Halim y Suhendry Effendy (https://github.com/stevenhalim/cpbook-code).
