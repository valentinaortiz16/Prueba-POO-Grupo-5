import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Organizacion {
    private final Map<Integer, Empleado> empleados = new LinkedHashMap<>();
    private final Map<String, Departamento> departamentos = new LinkedHashMap<>();
    private final Map<String, Cargo> cargos = new LinkedHashMap<>();
    private Empleado director;

    public Empleado registrarEmpleado(int id, String nombre, String nombreDepartamento, String nombreCargo) {
        if (empleados.containsKey(id)) {
            throw new IllegalArgumentException("Ya existe un empleado con el identificador " + id + ".");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (nombreCargo == null || nombreCargo.isBlank()) {
            throw new IllegalArgumentException("El cargo no puede estar vacio.");
        }
        Departamento departamento = departamentos.computeIfAbsent(nombreDepartamento, Departamento::new);
        Cargo cargo = cargos.computeIfAbsent(nombreCargo, Cargo::new);
        Empleado empleado = new Empleado(id, nombre, departamento, cargo);
        departamento.agregarEmpleado(empleado);
        cargo.agregarEmpleado(empleado);
        empleados.put(id, empleado);
        return empleado;
    }

    public void designarDirector(int id) {
        Empleado candidato = buscar(id);
        if (director != null && director != candidato) {
            throw new IllegalArgumentException("Ya existe un director raiz: " + director.getNombre() + ".");
        }
        if (!candidato.esRaiz()) {
            throw new IllegalArgumentException("Un empleado con supervisor no puede ser director raiz.");
        }
        director = candidato;
    }

    public void asignarSupervisor(int idEmpleado, int idSupervisor) {
        Empleado empleado = buscar(idEmpleado);
        Empleado supervisor = buscar(idSupervisor);
        if (empleado == director) {
            throw new IllegalArgumentException("Al director raiz no se le puede asignar supervisor.");
        }
        empleado.asignarSupervisor(supervisor);
    }

    public List<Empleado> subordinadosDirectos(int id) {
        return buscar(id).getSubordinados();
    }

    public int nivel(int id) {
        return buscar(id).calcularNivel();
    }

    public List<Empleado> cadenaSupervisores(int id) {
        return buscar(id).cadenaSupervisores();
    }

    public Empleado ancestroComun(int idA, int idB) {
        return buscar(idA).ancestroComun(buscar(idB));
    }

    public List<Empleado> empleadosPorCargo(String nombreCargo) {
        Cargo cargo = cargos.get(nombreCargo);
        if (cargo == null) {
            throw new IllegalArgumentException("No existe el cargo " + nombreCargo + ".");
        }
        return cargo.getEmpleados();
    }

    public List<Empleado> listarEmpleados() {
        return new ArrayList<>(empleados.values());
    }

    public Empleado getDirector() {
        return director;
    }

    private Empleado buscar(int id) {
        Empleado empleado = empleados.get(id);
        if (empleado == null) {
            throw new IllegalArgumentException("No existe un empleado con el identificador " + id + ".");
        }
        return empleado;
    }
}
