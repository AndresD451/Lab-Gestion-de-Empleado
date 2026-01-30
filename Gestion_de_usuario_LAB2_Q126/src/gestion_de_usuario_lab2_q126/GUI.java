/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_de_usuario_lab2_q126;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author user
 */
public class GUI extends JFrame {

    private Empresa empresa;

    private JTabbedPane pestañas;

    // Registro Empleado
    private JTextField tfCodigo, tfNombre, tfSalario, tfTasaComision;
    private JButton btnSeleccionarFoto, btnRegistrar;
    private JComboBox<String> cbTipoEmpleado;
    private JDateChooser dcFechaContratacion, dcFechaFinContrato;
    private File archivoFoto;

    // Registro Horas
    private JTextField tfCodigoHoras, tfHoras;
    private JButton btnRegistrarHoras;

    // Registro Ventas
    private JTextField tfCodigoVentas, tfMontoVenta;
    private JButton btnRegistrarVenta;

    // Actualizar Contrato
    private JTextField tfCodigoContrato;
    private JDateChooser dcNuevaFechaFin;
    private JButton btnActualizarContrato;
    //Calculo pago
    private JComboBox<String> cbEmpleadosPago;
    private JTextField tfPagoMensual;
    private JButton btnCalcularPago;

    // Reporte
    private JTextArea taReporte;
    private JButton btnGenerarReporte;

    public GUI() {
        empresa = new Empresa(50);

        setTitle("Gestión de Empleados");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pestañas = new JTabbedPane();

        pestañas.addTab("Registrar Empleado", crearPanelRegistrarEmpleado());
        pestañas.addTab("Registrar Horas", crearPanelRegistrarHoras());
        pestañas.addTab("Registrar Ventas", crearPanelRegistrarVentas());
        pestañas.addTab("Actualizar Contrato", crearPanelActualizarContrato());
        pestañas.addTab("Calcular Pago Mensual", crearPanelCalcularPago());
        pestañas.addTab("Reporte", crearPanelReporte());

        add(pestañas);
        setVisible(true);
    }

    private JPanel crearPanelRegistrarEmpleado() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTipo = new JLabel("Tipo Empleado:");
        lblTipo.setBounds(20, 20, 120, 25);
        panel.add(lblTipo);

        cbTipoEmpleado = new JComboBox<>(new String[]{"Estándar", "Temporal", "Ventas"});
        cbTipoEmpleado.setBounds(150, 20, 120, 25);
        panel.add(cbTipoEmpleado);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 60, 100, 25);
        panel.add(lblCodigo);

        tfCodigo = new JTextField();
        tfCodigo.setBounds(150, 60, 150, 25);
        panel.add(tfCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 100, 100, 25);
        panel.add(lblNombre);

        tfNombre = new JTextField();
        tfNombre.setBounds(150, 100, 200, 25);
        panel.add(tfNombre);

        JLabel lblSalario = new JLabel("Salario Base:");
        lblSalario.setBounds(20, 140, 100, 25);
        panel.add(lblSalario);

        tfSalario = new JTextField();
        tfSalario.setBounds(150, 140, 100, 25);
        panel.add(tfSalario);

        JLabel lblFoto = new JLabel("Foto:");
        lblFoto.setBounds(20, 180, 100, 25);
        panel.add(lblFoto);

        btnSeleccionarFoto = new JButton("Seleccionar Archivo...");
        btnSeleccionarFoto.setBounds(150, 180, 180, 25);
        panel.add(btnSeleccionarFoto);

        btnSeleccionarFoto.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int opcion = chooser.showOpenDialog(this);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                archivoFoto = chooser.getSelectedFile();
                btnSeleccionarFoto.setText(archivoFoto.getName());
            }
        });

        JLabel lblFecha = new JLabel("Fecha Contratación:");
        lblFecha.setBounds(20, 220, 150, 25);
        panel.add(lblFecha);

        dcFechaContratacion = new JDateChooser();
        dcFechaContratacion.setBounds(150, 220, 150, 25);
        panel.add(dcFechaContratacion);

        JLabel lblFinContrato = new JLabel("Fecha Fin Contrato (temporal):");
        lblFinContrato.setBounds(20, 260, 200, 25);
        panel.add(lblFinContrato);

        dcFechaFinContrato = new JDateChooser();
        dcFechaFinContrato.setBounds(220, 260, 150, 25);
        panel.add(dcFechaFinContrato);

        JLabel lblTasa = new JLabel("Tasa Comisión (ventas):");
        lblTasa.setBounds(20, 300, 180, 25);
        panel.add(lblTasa);

        tfTasaComision = new JTextField();
        tfTasaComision.setBounds(200, 300, 80, 25);
        panel.add(tfTasaComision);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(150, 350, 120, 35);
        panel.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {

            String codigo = tfCodigo.getText();
            String nombre = tfNombre.getText();
            double salario = Double.parseDouble(tfSalario.getText());

            Calendar fechaContratacion = Calendar.getInstance();
            fechaContratacion.setTime(dcFechaContratacion.getDate());

            String tipo = (String) cbTipoEmpleado.getSelectedItem();
            if (empresa.existeEmpleado(codigo)) {
                JOptionPane.showMessageDialog(this,
                        "Ya existe un empleado con ese código",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (tipo.equals("Estándar")) {
                empresa.registrarEmpleado(new Empleado(codigo, nombre, salario,
                        archivoFoto != null ? archivoFoto.getAbsolutePath() : "",
                        fechaContratacion));
            } else if (tipo.equals("Temporal")) {
                Calendar fechaFin = Calendar.getInstance();
                fechaFin.setTime(dcFechaFinContrato.getDate());
                empresa.registrarEmpleado(new EmpleadoTemporal(codigo, nombre, salario,
                        archivoFoto != null ? archivoFoto.getAbsolutePath() : "",
                        fechaContratacion, fechaFin));
            } else if (tipo.equals("Ventas")) {
                double tasa = Double.parseDouble(tfTasaComision.getText());
                empresa.registrarEmpleado(new EmpleadoVentas(codigo, nombre, salario,
                        archivoFoto != null ? archivoFoto.getAbsolutePath() : "",
                        fechaContratacion, tasa));
            }

            JOptionPane.showMessageDialog(this, "Empleado registrado con éxito");
            limpiarCamposRegistroEmpleado();
            actualizarComboEmpleadosPago();

        });

        return panel;
    }

    private void limpiarCamposRegistroEmpleado() {
        tfCodigo.setText("");
        tfNombre.setText("");
        tfSalario.setText("");
        tfTasaComision.setText("");
        btnSeleccionarFoto.setText("Seleccionar Archivo...");
        archivoFoto = null;
        dcFechaContratacion.setDate(null);
        dcFechaFinContrato.setDate(null);
    }

    private JPanel crearPanelRegistrarHoras() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblCodigo = new JLabel("Código Empleado:");
        lblCodigo.setBounds(20, 30, 120, 25);
        panel.add(lblCodigo);

        tfCodigoHoras = new JTextField();
        tfCodigoHoras.setBounds(150, 30, 150, 25);
        panel.add(tfCodigoHoras);

        JLabel lblHoras = new JLabel("Horas trabajadas:");
        lblHoras.setBounds(20, 70, 120, 25);
        panel.add(lblHoras);

        tfHoras = new JTextField();
        tfHoras.setBounds(150, 70, 100, 25);
        panel.add(tfHoras);

        btnRegistrarHoras = new JButton("Registrar Horas");
        btnRegistrarHoras.setBounds(150, 110, 150, 30);
        panel.add(btnRegistrarHoras);

        btnRegistrarHoras.addActionListener(e -> {

            String codigo = tfCodigoHoras.getText();

            if (!empresa.existeEmpleado(codigo)) {
                JOptionPane.showMessageDialog(this,
                        "Empleado no encontrado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int horas = Integer.parseInt(tfHoras.getText());
            empresa.registrarHoras(codigo, horas);

            JOptionPane.showMessageDialog(this, "Horas registradas correctamente");

            tfCodigoHoras.setText("");
            tfHoras.setText("");
        });

        return panel;
    }

    private JPanel crearPanelRegistrarVentas() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblCodigo = new JLabel("Código Empleado:");
        lblCodigo.setBounds(20, 30, 120, 25);
        panel.add(lblCodigo);

        tfCodigoVentas = new JTextField();
        tfCodigoVentas.setBounds(150, 30, 150, 25);
        panel.add(tfCodigoVentas);

        JLabel lblMonto = new JLabel("Monto Venta:");
        lblMonto.setBounds(20, 70, 120, 25);
        panel.add(lblMonto);

        tfMontoVenta = new JTextField();
        tfMontoVenta.setBounds(150, 70, 100, 25);
        panel.add(tfMontoVenta);

        btnRegistrarVenta = new JButton("Registrar Venta");
        btnRegistrarVenta.setBounds(150, 110, 150, 30);
        panel.add(btnRegistrarVenta);

        btnRegistrarVenta.addActionListener(e -> {

            String codigo = tfCodigoVentas.getText();

            if (!empresa.existeEmpleado(codigo)) {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double monto = Double.parseDouble(tfMontoVenta.getText());
            empresa.registrarVenta(codigo, monto);

            JOptionPane.showMessageDialog(this, "Venta registrada correctamente");

            tfCodigoVentas.setText("");
            tfMontoVenta.setText("");
        });

        return panel;
    }

    private JPanel crearPanelActualizarContrato() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblCodigo = new JLabel("Código Empleado Temporal:");
        lblCodigo.setBounds(20, 30, 180, 25);
        panel.add(lblCodigo);

        tfCodigoContrato = new JTextField();
        tfCodigoContrato.setBounds(210, 30, 150, 25);
        panel.add(tfCodigoContrato);

        JLabel lblFecha = new JLabel("Nueva Fecha Fin:");
        lblFecha.setBounds(20, 70, 150, 25);
        panel.add(lblFecha);

        dcNuevaFechaFin = new JDateChooser();
        dcNuevaFechaFin.setBounds(210, 70, 150, 25);
        panel.add(dcNuevaFechaFin);

        btnActualizarContrato = new JButton("Actualizar Contrato");
        btnActualizarContrato.setBounds(210, 110, 150, 30);
        panel.add(btnActualizarContrato);

        btnActualizarContrato.addActionListener(e -> {

            String codigo = tfCodigoContrato.getText();

            if (!empresa.existeEmpleado(codigo)) {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Calendar nuevaFecha = Calendar.getInstance();
            nuevaFecha.setTime(dcNuevaFechaFin.getDate());

            empresa.actualizarContrato(codigo, nuevaFecha);

            JOptionPane.showMessageDialog(this, "Contrato actualizado correctamente");

            tfCodigoContrato.setText("");
        });

        return panel;
    }

    private JPanel crearPanelCalcularPago() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblEmpleado = new JLabel("Seleccione Empleado:");
        lblEmpleado.setBounds(20, 30, 150, 25);
        panel.add(lblEmpleado);

        cbEmpleadosPago = new JComboBox<>();
        cbEmpleadosPago.setBounds(180, 30, 200, 25);
        panel.add(cbEmpleadosPago);

        JLabel lblPago = new JLabel("Pago Mensual:");
        lblPago.setBounds(20, 70, 150, 25);
        panel.add(lblPago);

        tfPagoMensual = new JTextField();
        tfPagoMensual.setBounds(180, 70, 200, 25);
        tfPagoMensual.setEditable(false);
        panel.add(tfPagoMensual);

        btnCalcularPago = new JButton("Calcular");
        btnCalcularPago.setBounds(180, 110, 120, 30);
        panel.add(btnCalcularPago);

        btnCalcularPago.addActionListener(e -> {

            if (cbEmpleadosPago.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this,
                        "No hay empleados seleccionados",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String codigo = cbEmpleadosPago.getSelectedItem().toString();

            double pago = empresa.calcularPagoEmpleado(codigo);

            tfPagoMensual.setText(String.format("%.2f", pago));
        });

        return panel;
    }

    private void actualizarComboEmpleadosPago() {

        cbEmpleadosPago.removeAllItems();

        for (Empleado emp : empresa.getEmpleados()) {

            if (emp != null) {
                cbEmpleadosPago.addItem(emp.getCodigo());
            }
        }
    }

    private JPanel crearPanelReporte() {

        JPanel panel = new JPanel(new BorderLayout());

        JPanel panelEmpleados = new JPanel();
        panelEmpleados.setLayout(new BoxLayout(panelEmpleados, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(panelEmpleados);
        panel.add(scroll, BorderLayout.CENTER);

        btnGenerarReporte = new JButton("Generar Reporte");
        panel.add(btnGenerarReporte, BorderLayout.SOUTH);

        btnGenerarReporte.addActionListener(e -> {

            panelEmpleados.removeAll();

            for (Empleado emp : empresa.getEmpleados()) {

                if (emp == null) {
                    continue;
                }

                JPanel panelEmpleado = new JPanel(new BorderLayout());
                panelEmpleado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                // FOTO
                JLabel lblFoto;

                if (emp.getArchivoFoto() != null && !emp.getArchivoFoto().isEmpty()) {
                    ImageIcon icon = new ImageIcon(emp.getArchivoFoto());
                    Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    lblFoto = new JLabel(new ImageIcon(img));
                } else {
                    lblFoto = new JLabel("Sin Foto");
                    lblFoto.setPreferredSize(new Dimension(100, 100));
                    lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
                }

                panelEmpleado.add(lblFoto, BorderLayout.WEST);

                // TEXTO
                JTextArea info = new JTextArea(emp.mostrarInformacion());
                info.setEditable(false);
                info.setFont(new Font("Monospaced", Font.PLAIN, 12));
                info.setOpaque(false);

                panelEmpleado.add(info, BorderLayout.CENTER);

                panelEmpleado.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

                panelEmpleados.add(panelEmpleado);
                panelEmpleados.add(Box.createVerticalStrut(10));
                panelEmpleados.add(new JSeparator());
            }

            panelEmpleados.revalidate();
            panelEmpleados.repaint();
        });

        return panel;
    }

}
