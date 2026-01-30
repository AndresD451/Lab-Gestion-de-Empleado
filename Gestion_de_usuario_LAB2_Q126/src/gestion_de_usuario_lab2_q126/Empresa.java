/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_de_usuario_lab2_q126;
import java.util.Calendar;

/**
 *
 * @author Nathan
 */

//Gestión general
public class Empresa {

    private Empleado[] empleados;
    private int contador;

    public Empresa(int capacidad) {
        empleados = new Empleado[capacidad];
        contador = 0;
    }

    public boolean registrarEmpleado(Empleado e) {
        if (buscarEmpleado(e.getCodigo()) != null) return false;
        empleados[contador++] = e;
        return true;
    }

    public Empleado buscarEmpleado(String codigo) {
        for (int i = 0; i < contador; i++) {
            if (empleados[i].getCodigo().equals(codigo)) {
                return empleados[i];
            }
        }
        return null;
    }

    public void registrarHoras(String codigo, int horas) {
        Empleado e = buscarEmpleado(codigo);
        if (e != null) e.registrarHoras(horas);
    }

    public void registrarVenta(String codigo, double monto) {
        Empleado e = buscarEmpleado(codigo);
        if (e instanceof EmpleadoVentas) {
            ((EmpleadoVentas) e).registrarVenta(monto);
        }
    }

    public void actualizarContrato(String codigo, Calendar fecha) {
        Empleado e = buscarEmpleado(codigo);
        if (e instanceof EmpleadoTemporal) {
            ((EmpleadoTemporal) e).actualizarFechaFinContrato(fecha);
        }
    }

    public double calcularPagoEmpleado(String codigo) {
        Empleado e = buscarEmpleado(codigo);
        return (e != null) ? e.calcularPago() : 0;
    }

    public String generarReporte() {
        String r = "";
        int est = 0, temp = 0, ven = 0;

        for (int i = 0; i < contador; i++) {
            r += empleados[i].mostrarInformacion() + "\n";
            if (empleados[i] instanceof EmpleadoVentas) ven++;
            else if (empleados[i] instanceof EmpleadoTemporal) temp++;
            else est++;
        }

        r += "\nEstándar: " + est +
             "\nTemporales: " + temp +
             "\nVentas: " + ven;

        return r;
    }
}

