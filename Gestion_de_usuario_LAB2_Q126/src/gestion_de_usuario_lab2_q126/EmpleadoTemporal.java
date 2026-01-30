package gestion_de_usuario_lab2_q126;

import java.util.Calendar;

public class EmpleadoTemporal extends Empleado {
    private Calendar fechaFinContrato;

    // el construtor
    public EmpleadoTemporal(String codigo, String nombre, double salarioBase, String rutaFoto, Calendar fechaContratacion, Calendar fechaFinContrato) {
        super(codigo, nombre, salarioBase, rutaFoto, fechaContratacion);
        this.fechaFinContrato = fechaFinContrato;
    }

    // fecha fin de contrato
    public void actualizarFechaFinContrato(Calendar nuevaFecha) {
        this.fechaFinContrato = nuevaFecha;
    }

    // pago condicionado
    @Override
    public double calcularPago() {
        Calendar hoy = Calendar.getInstance();
        if (hoy.compareTo(fechaFinContrato)<=0) {
            return super.calcularPago();
        } else {
            return 0.0;
        }
    }

    // informacion extendida
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() +
               " | Fin de contrato: " +
               fechaFinContrato.get(Calendar.DAY_OF_MONTH) + "/" +
               (fechaFinContrato.get(Calendar.MONTH) + 1) + "/" +
               fechaFinContrato.get(Calendar.YEAR);
    }
}