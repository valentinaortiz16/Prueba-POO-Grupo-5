import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal: solo muestra el menu, lee datos y delega todo a Organizacion.
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Organizacion org = new Organizacion();
        int opcion;

        System.out.println("=== SISTEMA DE JERARQUIA DE EMPLEADOS ===");
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            try {
                switch (opcion) {
                    case 1:
                        registrarDepartamento(org);
                        break;
                    case 2:
                        registrarEmpleado(org);
                        break;
                    case 3:
                        asignarSupervisor(org);
                        break;
                    case 4:
                        listarSubordinados(org);
                        break;
                    case 5:
                        calcularNivel(org);
                        break;
                    case 6:
                        consultarCadena(org);
                        break;
                    case 7:
                        listarPorDepartamento(org);
                        break;
                    case 8:
                        org.cargarEjemplo();
                        System.out.println("Datos de ejemplo cargados: A supervisa a B, B supervisa a C.");
                        break;
                    case 9:
                        listarTodos(org);
                        break;
                    case 0:
                        System.out.println("Hasta luego.");
                        break;
                    default:
                        System.out.println("Opcion invalida. Elija un numero del menu.");
                }
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        } while (opcion != 0);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("1. Registrar departamento");
        System.out.println("2. Registrar empleado");
        System.out.println("3. Asignar supervisor");
        System.out.println("4. Listar subordinados directos");
        System.out.println("5. Calcular nivel de un empleado");
        System.out.println("6. Consultar cadena de supervisores");
        System.out.println("7. Listar empleados por departamento");
        System.out.println("8. Cargar datos de ejemplo (A -> B -> C)");
        System.out.println("9. Listar todos los empleados");
        System.out.println("0. Salir");
    }

    // ------------------ Opciones del menu ------------------

    private static void registrarDepartamento(Organizacion org) {
        String nombre = leerTexto("Nombre del departamento: ");
        Departamento d = org.registrarDepartamento(nombre);
        System.out.println("Departamento registrado: " + d.getNombre());
    }

    private static void registrarEmpleado(Organizacion org) {
        String id = leerTexto("ID del empleado: ");
        String nombre = leerTexto("Nombre: ");
        String cargo = leerTexto("Cargo: ");
        double salario = leerDecimal("Salario base: ");
        String depto = leerTexto("Departamento: ");
        Empleado e = org.registrarEmpleado(id, nombre, cargo, salario, depto);
        System.out.println("Empleado registrado: " + e);
        if (e == org.getDirector()) {
            System.out.println("(Es el primer empleado: queda como director raiz, nivel 0)");
        }
    }

    private static void asignarSupervisor(Organizacion org) {
        String idEmp = leerTexto("ID del empleado: ");
        String idSup = leerTexto("ID del supervisor: ");
        org.asignarSupervisor(idEmp, idSup);
        System.out.println("Supervisor asignado correctamente.");
    }

    private static void listarSubordinados(Organizacion org) {
        String id = leerTexto("ID del supervisor: ");
        ArrayList<Empleado> lista = org.listarSubordinadosDirectos(id);
        if (lista.isEmpty()) {
            System.out.println("Ese empleado no tiene subordinados directos.");
        } else {
            System.out.println("Subordinados directos:");
            for (Empleado e : lista) {
                System.out.println(" - " + e);
            }
        }
    }

    private static void calcularNivel(Organizacion org) {
        String id = leerTexto("ID del empleado: ");
        int nivel = org.calcularNivel(id);
        System.out.println("Nivel de " + id.trim().toUpperCase() + ": " + nivel);
    }

    private static void consultarCadena(Organizacion org) {
        String id = leerTexto("ID del empleado: ");
        ArrayList<Empleado> cadena = org.consultarCadenaSupervisores(id);
        if (cadena.isEmpty()) {
            System.out.println("Ese empleado no tiene supervisores.");
        } else {
            System.out.println("Cadena de supervisores (del mas cercano al director):");
            for (Empleado e : cadena) {
                System.out.println(" -> " + e);
            }
        }
    }

    private static void listarPorDepartamento(Organizacion org) {
        String depto = leerTexto("Nombre del departamento: ");
        ArrayList<Empleado> lista = org.listarEmpleadosPorDepartamento(depto);
        if (lista.isEmpty()) {
            System.out.println("El departamento no tiene empleados.");
        } else {
            for (Empleado e : lista) {
                System.out.println(" - " + e);
            }
        }
    }

    private static void listarTodos(Organizacion org) {
        ArrayList<Empleado> lista = org.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado e : lista) {
                System.out.println(" - " + e);
            }
        }
    }

    // ------------------ Lectura segura de datos ------------------

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        String linea = sc.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException ex) {
            return -1; // cae en "opcion invalida" del switch
        }
    }

    private static double leerDecimal(String mensaje) {
        System.out.print(mensaje);
        String linea = sc.nextLine().trim().replace(',', '.');
        try {
            return Double.parseDouble(linea);
        } catch (NumberFormatException ex) {
            return -1; // el constructor de Cargo lo rechaza (salario <= 0)
        }
    }
}
