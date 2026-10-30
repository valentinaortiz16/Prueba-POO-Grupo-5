/**
 * Representa el cargo de un empleado (nombre y salario base).
 * Es la clase adicional del Grupo 5: Empleado la usa por composicion.
 */
public class Cargo {

    private String nombre;
    private double salarioBase;

    public Cargo(String nombre, double salarioBase) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cargo no puede estar vacio.");
        }
        if (salarioBase <= 0) {
            throw new IllegalArgumentException("El salario base debe ser mayor que cero.");
        }
        this.nombre = nombre.trim();
        this.salarioBase = salarioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    @Override
    public String toString() {
        return nombre + " ($" + String.format("%.2f", salarioBase) + ")";
    }
}
