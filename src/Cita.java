class Cita {
    private Paciente paciente;
    private Doctor doctor;
    private String fechaHora;

    public Cita(Paciente paciente, Doctor doctor, String fechaHora) {
        this.paciente = paciente;
        this.doctor = doctor;
        this.fechaHora = fechaHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getFechaHora() {
        return fechaHora;
    }
}
