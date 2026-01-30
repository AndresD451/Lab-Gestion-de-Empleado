/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_de_usuario_lab2_q126;
import java.util.Calendar;
/**
 *
 * @author sandr
 */

public class EmpleadoVentas extends Empleado{
    double[] ventasMensual;
    double tasaComision;
    public EmpleadoVentas(String code, String nombre, double salarioBase, String ruteFoto, double tasaComision ){
    super(code, nombre, salarioBase, ruteFoto);
    this.tasaComision=tasaComision;
    this.ventasMensual=new double[12];
}
    
    public void registrarVenta(double monto){
        Calendar diaHoy=Calendar.getInstance();
        int mes;
        mes=diaHoy.get(Calendar.MONTH);
        //suma el monto dependiendo del mess. import calander monnth empieza desde 0, compatible con arreglos
        this.ventasMensual[mes]= this.ventasMensual[mes]+monto;
       
        
    }
    public double calcComisonMes(){
        Calendar diaHoy=Calendar.getInstance();
        int mes;
        mes=diaHoy.get(Calendar.MONTH);
        return this.ventasMensual[mes]*this.tasaComision;
    }
    
    @Override
    public double calcularPago(){
       double pagoBase=(this.salarioBase/160)*this.horasTrabajadas;
       return pagoBase+calcComisonMes();
    }

    
}
