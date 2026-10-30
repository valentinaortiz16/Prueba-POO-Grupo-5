import java.util.ArrayList;

/**
 * Clase central del sistema: contiene empleados y departamentos y aplica
 * todas las reglas de negocio (registro, supervision, niveles, cadenas).
 * Main solo pide datos y muestra resultados; aqui esta la logica.
 */
public class Organizacion {

    private ArrayList<Empleado> empleados;
    private ArrayList<Departamento> departamentos;
    private Empleado director; // director raiz (nivel 0)

    public Organizacion() {
        this.empleados = new ArrayList<>();
        this.departamentos = new ArrayList<>();
        this.director = null;
    }

    // ---------------------------------------------------------------
    // Busquedas auxiliares
    // ---------------------------------------------------------------

    public Empleado buscarEmpleado(String id) {
        if (id == null) {
            return null;
        }
        String clave = id.trim().toUpperCase();
        for (Empleado e : empleados) {
            if (e.getId().equals(clave)) {
                return e;
            }
        }
        return null;
    }

    public Departamento buscarDepartamento(String nombre) {
        if (nombre == null) {
            return null;
        }
        for (Departamento d : departamentos) {
            if (d.getNombre().equalsIgnoreCase(nombre.trim())) {
                return d;
            }
        }
        return null;
    }

    private Empleado obtenerEmpleado(String id) {
        Empleado e = buscarEmpleado(id);
        if (e == null) {
            throw new IllegalArgumentException("No existe un empleado con ID '" + id.trim() + "'.");
        }
        return e;
    }

    public Empleado getDirector() {
        return director;
    }

    public ArrayList<Departamento> getDepartamentos() {
        return new ArrayList<>(departamentos);
    }

    public int cantidadEmpleados() {
        return empleados.size();
    }

    // ---------------------------------------------------------------
    // Metodos de negocio
    // ---------------------------------------------------------------

    /** Registra un departamento nuevo. Rechaza nombres vacios o repetidos. */
    public Departamento registrarDepartamento(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del departamento no puede estar vacio.");
        }
        if (buscarDepartamento(nombre) != null) {
            throw new IllegalArgumentException("Ya existe el departamento '" + nombre.trim() + "'.");
        }
        Departamento d = new Departamento(nombre);
        departamentos.add(d);
        return d;
    }

    /**
     * METODO 1. Registra un empleado con ID unico.
     * El primer empleado registrado se convierte en el director raiz.
     */
    public Empleado registrarEmpleado(String id, String nombre, String nombreCargo,
                                      double salario, String nombreDepartamento) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador no puede estar vacio.");
        }
        if (buscarEmpleado(id) != null) {
            throw new IllegalArgumentException("El ID '" + id.trim().toUpperCase() + "' ya esta registrado.");
        }
        Departamento depto = buscarDepartamento(nombreDepartamento);
        if (depto == null) {
            throw new IllegalArgumentException("El departamento '" + nombreDepartamento
                    + "' no existe. Registrelo primero.");
        }
        Cargo cargo = new Cargo(nombreCargo, salario);
        Empleado nuevo = new Empleado(id, nombre, cargo, depto);
        empleados.add(nuevo);
        depto.agregarEmpleado(nuevo);
        if (director == null) {
            director = nuevo;
        }
        return nuevo;
    }

    /**
     * METODO 2. Asigna (o reemplaza) el supervisor de un empleado.
     * Reglas: ambos deben existir, el director raiz no tiene supervisor,
     * nadie se supervisa a si mismo y no se permiten ciclos.
     */
    public void asignarSupervisor(String idEmpleado, String idSupervisor) {
        Empleado emp = obtenerEmpleado(idEmpleado);
        Empleado sup = obtenerEmpleado(idSupervisor);

        if (emp == director) {
            throw new IllegalStateException("El director raiz no puede tener supervisor.");
        }
        if (emp == sup) {
            throw new IllegalStateException("Un empleado no puede supervisarse a si mismo.");
        }
        if (generaCiclo(emp, sup)) {
            throw new IllegalStateException("Operacion rechazada: " + sup.getNombre() + " ya depende de "
                    + emp.getNombre() + " y se crearia un ciclo.");
        }
        emp.setSupervisor(sup);
    }

    /**
     * Revisa si asignar a 'nuevoSupervisor' sobre 'emp' crearia un ciclo:
     * se sube por la cadena de supervisores de 'nuevoSupervisor'; si aparece 'emp', hay ciclo.
     */
    private boolean generaCiclo(Empleado emp, Empleado nuevoSupervisor) {
        Empleado actual = nuevoSupervisor;
        while (actual != null) {
            if (actual == emp) {
                return true;
            }
            actual = actual.getSupervisor();
        }
        return false;
    }

    /** METODO 3. Lista los subordinados directos de un empleado. */
    public ArrayList<Empleado> listarSubordinadosDirectos(String id) {
        Empleado jefe = obtenerEmpleado(id);
        ArrayList<Empleado> resultado = new ArrayList<>();
        for (Empleado e : empleados) {
            if (e.getSupervisor() == jefe) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    /**
     * METODO 4. Calcula el nivel del empleado: el director raiz es 0 y cada
     * supervisor hacia abajo suma 1. Falla si el empleado no esta conectado a la raiz.
     */
    public int calcularNivel(String id) {
        Empleado actual = obtenerEmpleado(id);
        int nivel = 0;
        while (actual.getSupervisor() != null) {
            actual = actual.getSupervisor();
            nivel++;
        }
        if (actual != director) {
            throw new IllegalStateException("El empleado aun no esta conectado al director raiz "
                    + "(su cadena termina en " + actual.getNombre() + ").");
        }
        return nivel;
    }

    /** METODO 5. Devuelve la cadena de supervisores, del mas cercano al director. */
    public ArrayList<Empleado> consultarCadenaSupervisores(String id) {
        Empleado actual = obtenerEmpleado(id);
        ArrayList<Empleado> cadena = new ArrayList<>();
        while (actual.getSupervisor() != null) {
            actual = actual.getSupervisor();
            cadena.add(actual);
        }
        return cadena;
    }

    /** METODO 6. Lista los empleados de un departamento. */
    public ArrayList<Empleado> listarEmpleadosPorDepartamento(String nombreDepartamento) {
        Departamento d = buscarDepartamento(nombreDepartamento);
        if (d == null) {
            throw new IllegalArgumentException("El departamento '" + nombreDepartamento + "' no existe.");
        }
        return d.getEmpleados();
    }

    /** Lista todos los empleados registrados. */
    public ArrayList<Empleado> listarTodos() {
        return new ArrayList<>(empleados);
    }

    /** Carga el caso de referencia: A supervisa a B y B supervisa a C. */
    public void cargarEjemplo() {
        registrarDepartamento("Gerencia");
        registrarDepartamento("Sistemas");
        registrarEmpleado("A", "Ana Torres", "Directora General", 3000, "Gerencia");
        registrarEmpleado("B", "Bruno Salazar", "Jefe de Sistemas", 1800, "Sistemas");
        registrarEmpleado("C", "Carla Mena", "Desarrolladora", 1200, "Sistemas");
        asignarSupervisor("B", "A");
        asignarSupervisor("C", "B");
    }
}
