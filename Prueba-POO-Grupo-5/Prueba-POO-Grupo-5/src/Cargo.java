public class Pruebas {
    static int ok = 0, total = 0;
    static void check(String nombre, Object esperado, Object obtenido) {
        total++;
        boolean b = String.valueOf(esperado).equals(String.valueOf(obtenido));
        if (b) ok++;
        System.out.println("P" + total + " " + nombre + " | esperado: " + esperado + " | obtenido: " + obtenido + " | " + (b ? "OK" : "FALLA"));
    }
    static String intento(Runnable r) {
        try { r.run(); return "sin error"; } catch (RuntimeException e) { return "error: " + e.getMessage(); }
    }
    public static void main(String[] a) {
        Organizacion o = new Organizacion();
        o.registrarDirector(1, "A", "Director", "Dirección");
        o.registrarEmpleado(2, "B", "Gerente", "Ventas");
        o.registrarEmpleado(3, "C", "Analista", "Ventas");
        o.asignarSupervisor(2, 1);
        o.asignarSupervisor(3, 2);
        check("Caso referencia: nivel de C", 2, o.calcularNivel(3));
        check("ID duplicado", "error: Ya existe un empleado con el ID 2.", intento(() -> o.registrarEmpleado(2, "X", "", "")));
        check("Subordinados directos de A", "[[2] B (Gerente)]", o.listarSubordinados(1));
        check("Autosupervisión", "error: Un empleado no puede supervisarse a sí mismo.", intento(() -> o.asignarSupervisor(2, 2)));
        check("Supervisor al director raíz", "error: El director raíz no puede tener supervisor.", intento(() -> o.asignarSupervisor(1, 3)));
        o.registrarEmpleado(4, "D", "Jefe", "TI");
        o.registrarEmpleado(5, "E", "Dev", "TI");
        o.asignarSupervisor(5, 4);
        check("Ciclo (D bajo E)", "error: Se generaría un ciclo: D ya es superior de E.", intento(() -> o.asignarSupervisor(4, 5)));
        check("Límite: ID no positivo", "error: El ID debe ser un entero positivo.", intento(() -> o.registrarEmpleado(0, "Z", "", "")));
        check("Cadena de C", "[[2] B (Gerente), [1] A (Director)]", o.cadenaSupervisores(3));
        System.out.println(ok + "/" + total + " pruebas OK");
    }
}
