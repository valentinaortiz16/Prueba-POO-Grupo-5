import java.util.ArrayList;

/**
 * Agrupa a los empleados que pertenecen a una misma area de la organizacion.
 */
public class Departamento {

    private String nombre;
    private ArrayList<Empleado> empleados;

    public Departamento(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del departamento no puede estar vacio.");
        }
        this.nombre = nombre.trim();
        this.empleados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    /** Devuelve una copia para que nadie modifique la lista interna desde fuera. */
    public ArrayList<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }

    public void agregarEmpleado(Empleado e) {
        if (e == null) {
            throw new IllegalArgumentException("No se puede agregar un empleado nulo.");
        }
        if (!empleados.contains(e)) {
            empleados.add(e);
        }
    }

    public int cantidadEmpleados() {
        return empleados.size();
    }

    @Override
    public String toString() {
        return nombre + " (" + empleados.size() + " empleados)";
    }
}
