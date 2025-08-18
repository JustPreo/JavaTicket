package javaticket.AdministradorEventos;

import javaticket.Categorias.*;
import javaticket.Manejo.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.*;
import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;

public class VerEvento extends JFrame {

    private ManejoDeUsuarios manejo;
    private ManejoDeEventos eventos;
    private JTextField codigoTF, nombre, descripcion, costo;
    private JDateChooser fechaS;
    private JButton buscarBtn, volver;
    private JComboBox<String> c2;
    private JTable tablaEquipo1, tablaEquipo2, tablaMontaje;
    private DefaultTableModel modeloEquipo1, modeloEquipo2, modeloMontaje;
    private JSpinner spinnerConvertidos;
    private JSpinner spinnerCapacidad;
    private JLabel estadoLbl;
    private categorias eventoActual;
    private Font paraLetras;
    private java.util.List<Component> componentesExtra = new ArrayList<>();

    public VerEvento(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        paraLetras = new Font("Roboto", Font.BOLD, 16);
        Font paraTitulos = new Font("Roboto", Font.BOLD, 25);

        setSize(700, 700);
        setTitle("Ver evento");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#394B66"));//Azul)?

        JLabel codigoL = new JLabel("Codigo");
        codigoL.setBounds(20, 20, 100, 30);
        codigoL.setFont(paraLetras);
        codigoL.setForeground(Color.decode("#EAE9D3"));
        add(codigoL);

        codigoTF = new JTextField();
        codigoTF.setBounds(80, 20, 80, 30);
        codigoTF.setFont(paraLetras);
        add(codigoTF);

        buscarBtn = new JButton("Buscar");
        buscarBtn.setBounds(180, 20, 100, 30);
        buscarBtn.setFont(paraLetras);
        buscarBtn.setBackground(Color.decode("#EAE9D3"));
        add(buscarBtn);

        JLabel nombreL = new JLabel("Nombre");
        nombreL.setBounds(20, 70, 100, 25);
        nombreL.setFont(paraLetras);
        nombreL.setForeground(Color.decode("#EAE9D3"));
        add(nombreL);

        nombre = new JTextField();
        nombre.setBounds(80, 70, 200, 30);
        nombre.setFont(paraLetras);
        nombre.setEditable(false);
        add(nombre);

        JLabel descripcionL = new JLabel("Descripcion");
        descripcionL.setBounds(20, 110, 100, 25);
        descripcionL.setFont(paraLetras);
        descripcionL.setForeground(Color.decode("#EAE9D3"));
        add(descripcionL);

        descripcion = new JTextField();
        descripcion.setBounds(110, 110, 300, 30);
        descripcion.setEditable(false);
        descripcion.setBounds(110, 110, 300, 30);
        descripcion.setFont(paraLetras);
        add(descripcion);

        JLabel fechaL = new JLabel("Fecha");
        fechaL.setBounds(20, 150, 100, 25);
        fechaL.setFont(paraLetras);
        fechaL.setForeground(Color.decode("#EAE9D3"));
        add(fechaL);

        fechaS = new JDateChooser();
        fechaS.setDateFormatString("yyyy-MM-dd");
        fechaS.setBounds(80, 150, 150, 30);
        fechaS.setEnabled(false);
        add(fechaS);

        JLabel costoL = new JLabel("Costo");
        costoL.setBounds(250, 150, 100, 25);
        costoL.setFont(paraLetras);
        costoL.setForeground(Color.decode("#EAE9D3"));
        add(costoL);

        costo = new JTextField();
        costo.setBounds(300, 150, 100, 30);
        costo.setEditable(false);
        costo.setFont(paraLetras);
        add(costo);

        JLabel capacidadL = new JLabel("Capacidad");
        capacidadL.setBounds(420, 150, 100, 25);
        capacidadL.setFont(paraLetras);
        capacidadL.setForeground(Color.decode("#EAE9D3"));
        add(capacidadL);

        spinnerCapacidad = new JSpinner(new SpinnerNumberModel(0, 0, 30000, 1));
        spinnerCapacidad.setBounds(500, 150, 80, 25);
        spinnerCapacidad.setEnabled(false);
        spinnerCapacidad.setFont(paraLetras);
        add(spinnerCapacidad);

        volver = new JButton("Volver");
        volver.setBounds(300, 600, 150, 40);
        volver.setFont(paraLetras);
        volver.setBackground(Color.decode("#EAE9D3"));
        add(volver);

        estadoLbl = new JLabel("");

        estadoLbl.setBounds(290, 65, 600, 50);
        estadoLbl.setFont(paraLetras);
        estadoLbl.setForeground(Color.decode("#EAE9D3"));
        add(estadoLbl);

        buscarBtn.addActionListener(e -> buscarEvento());
        volver.addActionListener(e -> {
            AdministrarEventos menu = new AdministrarEventos(manejo, eventos);
            menu.setVisible(true);
            dispose();
        });
    }

    private void buscarEvento() {
        try {
            int codigo = Integer.parseInt(codigoTF.getText());
            categorias evento = eventos.buscarEvento(codigo);

            if (evento == null) {
                JOptionPane.showMessageDialog(this, "No se encontro evento con ese codigo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.eventoActual = evento;

            nombre.setText(evento.getTitulo());
            descripcion.setText(evento.getDescripcion());
            fechaS.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(evento.getFecha()));
            costo.setText(Double.toString(evento.getCosto()));
            spinnerCapacidad.setValue(evento.getCapacidad());

            mostrarDetalle(evento);

            StringBuilder mensajeEstado = new StringBuilder();
            if (evento.isCancelado()) {
                mensajeEstado.append("- Evento cancelado ");
                if (evento.getMulta() > 0) {
                    mensajeEstado.append(" Multa cobrada: Lps ").append(String.format("%.2f", evento.getMulta()));
                }
            } else if (evento.isRealizado()) {
                mensajeEstado.append("- Evento realizado");
            } else {
                mensajeEstado.append("- Evento activo");
            }

            estadoLbl.setText(mensajeEstado.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar el evento.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDetalle(categorias evento) {
        if (c2 != null) {
            remove(c2);
        }
        if (tablaEquipo1 != null) {
            remove(tablaEquipo1.getParent());
        }
        if (tablaEquipo2 != null) {
            remove(tablaEquipo2.getParent());
        }
        if (tablaMontaje != null) {
            remove(tablaMontaje.getParent());
        }
        if (spinnerConvertidos != null) {
            remove(spinnerConvertidos);
        }
        for (Component c : componentesExtra) {
            remove(c);
        }
        componentesExtra.clear();
        
        if (evento instanceof Deportivo) {
            costo.setText(Double.toString(evento.getCosto()));
            String[] deportes = {"Futbol", "Tenis", "Rugby", "Baseball"};
            c2 = new JComboBox<>(deportes);
            c2.setBounds(20, 200, 150, 25);
            c2.setSelectedItem(((Deportivo) evento).getTipoDeporte());
            c2.setEnabled(false);
            add(c2);
            componentesExtra.add(c2);

            modeloEquipo1 = new DefaultTableModel(new Object[]{"Jugadores Equipo 1"}, 0) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            for (String j : ((Deportivo) evento).getJugadoresEquipo1()) {
                modeloEquipo1.addRow(new Object[]{j});
            }
            tablaEquipo1 = new JTable(modeloEquipo1);
            JScrollPane scroll1 = new JScrollPane(tablaEquipo1);
            scroll1.setBounds(20, 240, 200, 150);
            tablaEquipo1.setFont(paraLetras);
            add(scroll1);
            componentesExtra.add(scroll1);

            modeloEquipo2 = new DefaultTableModel(new Object[]{"Jugadores Equipo 1"}, 0) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            for (String j : ((Deportivo) evento).getJugadoresEquipo2()) {
                modeloEquipo2.addRow(new Object[]{j});
            }
            tablaEquipo2 = new JTable(modeloEquipo2);
            JScrollPane scroll2 = new JScrollPane(tablaEquipo2);
            scroll2.setBounds(300, 240, 200, 150);
            tablaEquipo2.setFont(paraLetras);
            add(scroll2);
            componentesExtra.add(scroll2);

        } else if (evento instanceof Musical) {
            costo.setText(Double.toString((evento.getCosto())+(((Musical) evento).getSeguroGrama())));
            String[] tipos = {"Pop", "Rock", "Rap", "Clasica", "Reggaeton", "Otro"};
            c2 = new JComboBox<>(tipos);
            c2.setBounds(20, 200, 150, 25);
            c2.setSelectedItem(((Musical) evento).getTipoMusica());
            c2.setEnabled(false);
            add(c2);
            componentesExtra.add(c2);

            modeloMontaje = new DefaultTableModel(new Object[]{"Equipo de Montaje"}, 0) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            for (String p : ((Musical) evento).getEquipoMontaje()) {
                modeloMontaje.addRow(new Object[]{p});
            }
            tablaMontaje = new JTable(modeloMontaje);
            JScrollPane scroll = new JScrollPane(tablaMontaje);
            scroll.setBounds(20, 240, 300, 150);
            add(scroll);
            componentesExtra.add(scroll);

        } else if (evento instanceof Religioso) {
            costo.setText(Double.toString((evento.getCosto())+(((Religioso) evento).getSeguroFijo())));
            JLabel l = new JLabel("Convertidos:");
            l.setFont(paraLetras);
            l.setForeground(Color.decode("#EAE9D3"));
            l.setBounds(20, 200, 100, 25);
            add(l);
            componentesExtra.add(l);

            spinnerConvertidos = new JSpinner(new SpinnerNumberModel(((Religioso) evento).getConvertidos(), 0, 30000, 1));
            spinnerConvertidos.setBounds(120, 200, 80, 25);
            spinnerConvertidos.setEnabled(false);
            spinnerConvertidos.setFont(paraLetras);
            add(spinnerConvertidos);
            componentesExtra.add(spinnerConvertidos);

        }

        revalidate();
        repaint();
    }
}
