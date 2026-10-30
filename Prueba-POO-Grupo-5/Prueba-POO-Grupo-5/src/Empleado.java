/**
 * Representa a un empleado de la organizacion.
 * Guarda su identificador unico, su cargo, su departamento y su supervisor.
 */
public class Empleado {

    private String id;
    private String nombre;
    private Cargo cargo;
    private Departamento departamento;
    private Empleado supervisor; // null si es el director raiz o aun no tiene supervisor

    public Empleado(String id, String nombre, Cargo cargo, Departamento departamento) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador no puede estar vacio.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        if (cargo == null) {
            throw new IllegalArgumentException("El empleado debe tener un cargo.");
        }
        if (departamento == null) {
            throw new IllegalArgumentException("El empleado debe pertenecer a un departamento.");
        }
        this.id = id.trim().toUpperCase();
        this.nombre = nombre.trim();
        this.cargo = cargo;
        this.departamento = departamento;
        this.supervisor = null;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Empleado getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Empleado supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " [" + cargo.getNombre() + ", " + departamento.getNombre() + "]";
    }
}
