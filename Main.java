import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static final Organizacion organizacion = new Organizacion();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Opcion: ");
            try {
                ejecutar(opcion);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("1. Registrar empleado");
        System.out.println("2. Designar director raiz");
        System.out.println("3. Asignar supervisor");
        System.out.println("4. Listar subordinados directos");
        System.out.println("5. Calcular nivel de un empleado");
        System.out.println("6. Consultar cadena de supervisores");
        System.out.println("7. Ancestro comun de dos empleados");
        System.out.println("8. Listar todos los empleados");
        System.out.println("9. Listar empleados por cargo");
        System.out.println("0. Salir");
    }

    private static void ejecutar(int opcion) {
        switch (opcion) {
            case 1 -> {
                int id = leerEntero("Id: ");
                String nombre = leerTexto("Nombre: ");
                String departamento = leerTexto("Departamento: ");
                String cargo = leerTexto("Cargo: ");
                Empleado e = organizacion.registrarEmpleado(id, nombre, departamento, cargo);
                System.out.println("Registrado: " + e);
            }
            case 2 -> {
                organizacion.designarDirector(leerEntero("Id del director: "));
                System.out.println("Director raiz: " + organizacion.getDirector());
            }
            case 3 -> {
                int id = leerEntero("Id del empleado: ");
                int idSup = leerEntero("Id del supervisor: ");
                organizacion.asignarSupervisor(id, idSup);
                System.out.println("Supervisor asignado.");
            }
            case 4 -> {
                List<Empleado> lista = organizacion.subordinadosDirectos(leerEntero("Id: "));
                if (lista.isEmpty()) {
                    System.out.println("Sin subordinados directos.");
                }
                lista.forEach(e -> System.out.println(e));
            }
            case 5 -> System.out.println("Nivel: " + organizacion.nivel(leerEntero("Id: ")));
            case 6 -> {
                List<Empleado> cadena = organizacion.cadenaSupervisores(leerEntero("Id: "));
                if (cadena.isEmpty()) {
                    System.out.println("Sin supervisores.");
                }
                cadena.forEach(e -> System.out.println(e));
            }
            case 7 -> {
                int a = leerEntero("Id del primer empleado: ");
                int b = leerEntero("Id del segundo empleado: ");
                Empleado comun = organizacion.ancestroComun(a, b);
                System.out.println(comun == null ? "Sin ancestro comun." : "Ancestro comun: " + comun);
            }
            case 8 -> organizacion.listarEmpleados().forEach(e ->
                    System.out.println(e + " | nivel " + e.calcularNivel()));
            case 9 -> {
                List<Empleado> lista = organizacion.empleadosPorCargo(leerTexto("Cargo: "));
                lista.forEach(e -> System.out.println(e));
            }
            case 0 -> System.out.println("Fin del programa.");
            default -> System.out.println("Opcion invalida.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = entrada.nextInt();
                entrada.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                entrada.nextLine();
                System.out.println("Ingrese un numero entero.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return entrada.nextLine().trim();
    }
}
