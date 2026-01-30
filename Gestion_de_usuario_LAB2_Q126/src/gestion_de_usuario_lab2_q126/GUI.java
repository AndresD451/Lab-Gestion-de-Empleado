/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion_de_usuario_lab2_q126;
import javax.swing.*;
import java.awt.*; 
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author user
 */
public class GUI extends JFrame { 

    private Empresa empresa = new Empresa(50);

    private JTextField txtCodigo, txtNombre, txtSalario, txtHoras, txtVenta, txtComision;
    private JTextArea areaReporte;
    private JLabel lblFoto;
    private String rutaFoto = "";

    private JComboBox<String> comboTipo;
    private JDateChooser calContratacion, calFinContrato;

    public GUI() {
        setTitle("Gestión de Empleados");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        add(panelRegistro(), BorderLayout.NORTH);
        add(panelCentro(), BorderLayout.CENTER);
        add(panelReporte(), BorderLayout.SOUTH);
    }

    /* ================= PANEL REGISTRO ================= */
    private JPanel panelRegistro() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(new TitledBorder("Registro de Empleado"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        txtCodigo = new JTextField(10);
        txtNombre = new JTextField(15);
        txtSalario = new JTextField(10);
        txtComision = new JTextField(10);

        calContratacion = new JDateChooser();
        calFinContrato = new JDateChooser();

        comboTipo = new JComboBox<>(new String[]{
                "Empleado Estándar", "Empleado Temporal", "Empleado Ventas"
        });

        lblFoto = new JLabel("Sin foto", SwingConstants.CENTER);
        lblFoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JButton btnFoto = new JButton("Cargar Foto");
        btnFoto.addActionListener(e -> seleccionarFoto());

        JButton btnRegistrar = new JButton("Registrar Empleado");
        btnRegistrar.addActionListener(e -> registrarEmpleado());

        int y = 0;

        c.gridx = 0; c.gridy = y; p.add(new JLabel("Código"), c);
        c.gridx = 1; p.add(txtCodigo, c);
        c.gridx = 2; p.add(new JLabel("Nombre"), c);
        c.gridx = 3; p.add(txtNombre, c);

        y++;
        c.gridx = 0; c.gridy = y; p.add(new JLabel("Salario"), c);
        c.gridx = 1; p.add(txtSalario, c);
        c.gridx = 2; p.add(new JLabel("Tipo"), c);
        c.gridx = 3; p.add(comboTipo, c);

        y++;
        c.gridx = 0; c.gridy = y; p.add(new JLabel("Fecha Contratación"), c);
        c.gridx = 1; p.add(calContratacion, c);
        c.gridx = 2; p.add(new JLabel("Fin Contrato"), c);
        c.gridx = 3; p.add(calFinContrato, c);

        y++;
        c.gridx = 0; c.gridy = y; p.add(new JLabel("Comisión"), c);
        c.gridx = 1; p.add(txtComision, c);
        c.gridx = 2; p.add(btnFoto, c);
        c.gridx = 3; p.add(lblFoto, c);

        y++;
        c.gridx = 0; c.gridy = y; c.gridwidth = 4;
        p.add(btnRegistrar, c);

        return p;
    }

    /* ================= PANEL CENTRAL ================= */
    private JPanel panelCentro() {
        JPanel p = new JPanel(new GridLayout(1, 2, 10, 10));

        p.add(panelAcciones());
        p.add(panelPago());

        return p;
    }

    /* ================= PANEL ACCIONES ================= */
    private JPanel panelAcciones() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(new TitledBorder("Registro de Actividad"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        txtHoras = new JTextField(8);
        txtVenta = new JTextField(8);

        JButton btnHoras = new JButton("Registrar Horas");
        JButton btnVenta = new JButton("Registrar Venta");

        btnHoras.addActionListener(e -> {
            if (!txtCodigo.getText().isEmpty() && !txtHoras.getText().isEmpty()) {
                if (txtHoras.getText().matches("\\d+")) {
                    empresa.registrarHoras(txtCodigo.getText(), Integer.parseInt(txtHoras.getText()));
                } else {
                    JOptionPane.showMessageDialog(this, "Horas inválidas");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese código y horas");
            }
        });

        btnVenta.addActionListener(e -> {
            if (!txtCodigo.getText().isEmpty() && !txtVenta.getText().isEmpty()) {
                if (txtVenta.getText().matches("\\d+(\\.\\d+)?")) {
                    empresa.registrarVenta(txtCodigo.getText(), Double.parseDouble(txtVenta.getText()));
                } else {
                    JOptionPane.showMessageDialog(this, "Venta inválida");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese código y venta");
            }
        });

        c.gridx = 0; c.gridy = 0; p.add(new JLabel("Horas"), c);
        c.gridx = 1; p.add(txtHoras, c);
        c.gridx = 2; p.add(btnHoras, c);

        c.gridx = 0; c.gridy = 1; p.add(new JLabel("Venta"), c);
        c.gridx = 1; p.add(txtVenta, c);
        c.gridx = 2; p.add(btnVenta, c);

        return p;
    }

    /* ================= PANEL PAGO ================= */
    private JPanel panelPago() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new TitledBorder("Pago"));

        JButton btnPago = new JButton("Calcular Pago");
        btnPago.setFont(btnPago.getFont().deriveFont(Font.BOLD, 16f));

        btnPago.addActionListener(e -> {
            if (!txtCodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Pago: L. " + empresa.calcularPagoEmpleado(txtCodigo.getText()));
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese código");
            }
        });

        p.add(btnPago, BorderLayout.CENTER);
        return p;
    }

    /* ================= PANEL REPORTE ================= */
    private JScrollPane panelReporte() {
        areaReporte = new JTextArea(8, 80);
        areaReporte.setEditable(false);
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JButton btnReporte = new JButton("Generar Reporte");
        btnReporte.addActionListener(e ->
                areaReporte.setText(empresa.generarReporte())
        );

        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new TitledBorder("Reporte General"));
        p.add(new JScrollPane(areaReporte), BorderLayout.CENTER);
        p.add(btnReporte, BorderLayout.SOUTH);

        return new JScrollPane(p);
    }

    /* ================= UTILIDADES ================= */
    private void seleccionarFoto() {
        JFileChooser ch = new JFileChooser();
        if (ch.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            rutaFoto = ch.getSelectedFile().getAbsolutePath();
            lblFoto.setText("Foto cargada");
        }
    }

    private Calendar convertirCalendar(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c;
    }

    private void registrarEmpleado() {
        if (txtCodigo.getText().isEmpty() || txtNombre.getText().isEmpty() || txtSalario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese Código, Nombre y Salario");
            return;
        }

        if (!txtSalario.getText().matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "Salario inválido");
            return;
        }

        double salario = Double.parseDouble(txtSalario.getText());
        Calendar fContr = convertirCalendar(calContratacion.getDate());
        Empleado e = null;

        if (comboTipo.getSelectedIndex() == 0) { // Estándar
            e = new Empleado(txtCodigo.getText(), txtNombre.getText(), salario, rutaFoto, fContr);
        } else if (comboTipo.getSelectedIndex() == 1) { // Temporal
            if (calFinContrato.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Seleccione fecha fin de contrato");
                return;
            }
            Calendar fFin = convertirCalendar(calFinContrato.getDate());
            e = new EmpleadoTemporal(txtCodigo.getText(), txtNombre.getText(), salario, rutaFoto, fContr, fFin);
        } else { // Ventas
            if (txtComision.getText().isEmpty() || !txtComision.getText().matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(this, "Comisión inválida");
                return;
            }
            double comision = Double.parseDouble(txtComision.getText());
            e = new EmpleadoVentas(txtCodigo.getText(), txtNombre.getText(), salario, rutaFoto, fContr, comision);
        }

        if (!empresa.registrarEmpleado(e)) {
            JOptionPane.showMessageDialog(this, "Código duplicado");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI().setVisible(true));
    }
    
    
    
}

