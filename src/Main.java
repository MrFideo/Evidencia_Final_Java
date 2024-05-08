import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Doctor> doctores = new ArrayList<>();
    private static ArrayList<Paciente> pacientes = new ArrayList<>();
    private static ArrayList<Cita> citas = new ArrayList<>();
    private static ArrayList<Administrador> administradores = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Configurar administradores
        configurarAdministradores();

        // Proceso de inicio de sesión
        if (!iniciarSesion()) {
            System.out.println("Acceso denegado.");
            return;
        }

        while (true) {
            System.out.println("1. Registrar Doctor");
            System.out.println("2. Registrar Paciente");
            System.out.println("3. Eliminar Doctor");
            System.out.println("4. Eliminar Paciente");
            System.out.println("5. Crear Cita");
            System.out.println("6. Mostrar Citas");
            System.out.println("7. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (opcion) {
                case 1:
                    registrarDoctor();
                    break;
                case 2:
                    registrarPaciente();
                    break;
                case 3:
                    eliminarDoctor();
                    break;
                case 4:
                    eliminarPaciente();
                    break;
                case 5:
                    crearCita();
                    break;
                case 6:
                    mostrarCitas();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void configurarAdministradores() {
        administradores.add(new Administrador("admin", "admin123"));
    }

    private static boolean iniciarSesion() {
        System.out.print("Ingrese usuario: ");
        String usuario = scanner.nextLine();
        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.nextLine();

        for (Administrador admin : administradores) {
            if (admin.autenticar(usuario, contraseña)) {
                System.out.println("Inicio de sesión exitoso.");
                return true;
            }
        }
        return false;
    }

    private static void registrarDoctor() {
        System.out.print("Ingrese nombre del doctor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese especialidad del doctor: ");
        String especialidad = scanner.nextLine();
        Doctor doctor = new Doctor(nombre, especialidad);
        doctores.add(doctor);
        System.out.println("Doctor registrado con éxito.");
    }

    private static void registrarPaciente() {
        System.out.print("Ingrese nombre del paciente: ");
        String nombre = scanner.nextLine();
        Paciente paciente = new Paciente(nombre);
        pacientes.add(paciente);
        System.out.println("Paciente registrado con éxito.");
    }

    private static void eliminarDoctor() {
        if (doctores.isEmpty()) {
            System.out.println("No hay doctores registrados.");
            return;
        }
        System.out.println("Seleccione el doctor a eliminar:");
        mostrarDoctores();
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < doctores.size()) {
            doctores.remove(index);
            System.out.println("Doctor eliminado con éxito.");
        } else {
            System.out.println("Índice inválido.");
        }
        scanner.nextLine(); // Consume newline
    }

    private static void eliminarPaciente() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        System.out.println("Seleccione el paciente a eliminar:");
        mostrarPacientes();
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < pacientes.size()) {
            pacientes.remove(index);
            System.out.println("Paciente eliminado con éxito.");
        } else {
            System.out.println("Índice inválido.");
        }
        scanner.nextLine(); // Consume newline
    }

    private static void crearCita() {
        if (doctores.isEmpty() || pacientes.isEmpty()) {
            System.out.println("Debe haber al menos un doctor y un paciente registrado para crear una cita.");
            return;
        }
        System.out.println("Seleccione un doctor para la cita:");
        mostrarDoctores();
        int docIndex = scanner.nextInt() - 1;

        System.out.println("Seleccione un paciente para la cita:");
        mostrarPacientes();
        int pacIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        System.out.print("Ingrese fecha y hora de la cita (formato YYYY-MM-DD HH:MM): ");
        String fechaHora = scanner.nextLine();

        if (docIndex >= 0 && docIndex < doctores.size() && pacIndex >= 0 && pacIndex < pacientes.size()) {
            Doctor doctor = doctores.get(docIndex);
            Paciente paciente = pacientes.get(pacIndex);
            Cita cita = new Cita(paciente, doctor, fechaHora);
            citas.add(cita);
            System.out.println("Cita creada con éxito.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private static void mostrarCitas() {
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }
        for (Cita cita : citas) {
            System.out.println("Cita entre el Dr. " + cita.getDoctor().getNombre() +
                               " y el paciente " + cita.getPaciente().getNombre() +
                               " programada para " + cita.getFechaHora() + ".");
        }
    }

    private static void mostrarDoctores() {
        for (int i = 0; i < doctores.size(); i++) {
            System.out.println((i + 1) + ". " + doctores.get(i).getNombre() + " - " + doctores.get(i).getEspecialidad());
        }
    }

    private static void mostrarPacientes() {
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + ". " + pacientes.get(i).getNombre());
        }
    }
}