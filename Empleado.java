import java.util.ArrayList;
import java.util.List;

public class Empleado {
    private final int id;
    private final String nombre;
    private final Departamento departamento;
    private final Cargo cargo;
    private Empleado supervisor;
    private final List<Empleado> subordinados = new ArrayList<>();

    public Empleado(int id, String nombre, Departamento departamento, Cargo cargo) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Empleado getSupervisor() {
        return supervisor;
    }

    public List<Empleado> getSubordinados() {
        return new ArrayList<>(subordinados);
    }

    public boolean esRaiz() {
        return supervisor == null;
    }

    public int calcularNivel() {
        int nivel = 0;
        Empleado actual = this;
        while (actual.supervisor != null) {
            nivel++;
            actual = actual.supervisor;
        }
        return nivel;
    }

    public List<Empleado> cadenaSupervisores() {
        List<Empleado> cadena = new ArrayList<>();
        Empleado actual = supervisor;
        while (actual != null) {
            cadena.add(actual);
            actual = actual.supervisor;
        }
        return cadena;
    }

    public boolean esSubordinadoDe(Empleado otro) {
        Empleado actual = supervisor;
        while (actual != null) {
            if (actual == otro) {
                return true;
            }
            actual = actual.supervisor;
        }
        return false;
    }

    public void asignarSupervisor(Empleado nuevo) {
        if (nuevo == null) {
            throw new IllegalArgumentException("El supervisor no puede ser nulo.");
        }
        if (nuevo == this) {
            throw new IllegalArgumentException("Un empleado no puede supervisarse a si mismo.");
        }
        if (nuevo.esSubordinadoDe(this)) {
            throw new IllegalArgumentException("La asignacion crearia un ciclo.");
        }
        if (supervisor != null) {
            supervisor.subordinados.remove(this);
        }
        supervisor = nuevo;
        nuevo.subordinados.add(this);
    }

    public Empleado ancestroComun(Empleado otro) {
        Empleado a = this;
        Empleado b = otro;
        int na = a.calcularNivel();
        int nb = b.calcularNivel();
        while (na > nb) {
            a = a.supervisor;
            na--;
        }
        while (nb > na) {
            b = b.supervisor;
            nb--;
        }
        while (a != b) {
            if (a.supervisor == null || b.supervisor == null) {
                return null;
            }
            a = a.supervisor;
            b = b.supervisor;
        }
        return a;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + departamento.getNombre() + ", " + cargo.getNombre() + ")";
    }
}
