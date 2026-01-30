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

//Clase base 
public class Empleado {

    protected String codigo;
    protected String nombre;
    protected Calendar fechaContratacion;
    protected double salarioBase;
    protected int horasTrabajadas;
    protected String rutaFoto;

    public Empleado(String codigo, String nombre, double salarioBase,
                    String rutaFoto, Calendar fechaContratacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
        this.rutaFoto = rutaFoto;
        this.fechaContratacion = fechaContratacion;
        this.horasTrabajadas = 0;
    }

    public void registrarHoras(int horas) {
        if (horas > 0) {
            horasTrabajadas += horas;
        }
    }

    //Pago proporcional con deducción
    public double calcularPago() {
        double pago = (salarioBase / 160.0) * horasTrabajadas;
        return pago - (pago * 0.035);
    }

    public String mostrarInformacion() {
        return "Código: " + codigo +
               " | Nombre: " + nombre +
               " | Fecha Contratación: " +
               fechaContratacion.get(Calendar.DAY_OF_MONTH) + "/" +
               (fechaContratacion.get(Calendar.MONTH) + 1) + "/" +
               fechaContratacion.get(Calendar.YEAR);
    }

    public String getCodigo() {
        return codigo;
    }
    public String getArchivoFoto() {
    return rutaFoto;
}
    
}

