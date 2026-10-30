import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private final String nombre;
    private final List<Empleado> empleados = new ArrayList<>();

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }
}
