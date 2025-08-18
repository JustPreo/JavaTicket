package javaticket.AdministradorEventos;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.*;
import javaticket.Categorias.*;
import javaticket.Manejo.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class EditarEvento extends JFrame {

    private ManejoDeUsuarios manejo;
    private ManejoDeEventos eventos;
    private JTextField codigoTF, nombre, descripcion, costo;
    private JDateChooser fechaS;
    private JButton buscarBtn, volver, guardarCambios;
    private JComboBox<String> c2;
    private JPanel panelExtra;
    private JTable tablaEquipo1, tablaEquipo2, tablaMontaje;
    private DefaultTableModel modeloEquipo1, modeloEquipo2, modeloMontaje;
    private JSpinner spinnerConvertidos;
    private JSpinner spinnerCapacidad;
    private categorias eventoActual;
    private Font paraLetras;

    // NUEVO: campos para nombre de equipos
    private JTextField nombreEquipo1TF, nombreEquipo2TF;

    public EditarEvento(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        paraLetras = new Font("Roboto", Font.BOLD, 16);
        Font paraTitulos = new Font("Roboto", Font.BOLD, 25);

        setSize(650, 700);
        setTitle("Editar evento");
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
        add(nombre);

        JLabel descripcionL = new JLabel("Descripcion");
        descripcionL.setBounds(20, 110, 100, 25);
        descripcionL.setFont(paraLetras);
        descripcionL.setForeground(Color.decode("#EAE9D3"));
        add(descripcionL);

        descripcion = new JTextField();
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
        add(fechaS);

        JLabel costoL = new JLabel("Costo");
        costoL.setBounds(250, 150, 100, 25);
        costoL.setFont(paraLetras);
        costoL.setForeground(Color.decode("#EAE9D3"));
        add(costoL);

        costo = new JTextField();
        costo.setBounds(300, 150, 100, 30);
        costo.setFont(paraLetras);
        add(costo);

        JLabel capacidadL = new JLabel("Capacidad");
        capacidadL.setBounds(420, 150, 100, 25);
        capacidadL.setFont(paraLetras);
        capacidadL.setForeground(Color.decode("#EAE9D3"));
        add(capacidadL);

        spinnerCapacidad = new JSpinner(new SpinnerNumberModel(0, 0, 30000, 1));
        spinnerCapacidad.setBounds(500, 150, 80, 25);
        spinnerCapacidad.setFont(paraLetras);
        add(spinnerCapacidad);

        panelExtra = new JPanel();
        panelExtra.setLayout(null);
        panelExtra.setBounds(20, 200, 600, 300);
        panelExtra.setBackground(Color.LIGHT_GRAY);
        add(panelExtra);

        guardarCambios = new JButton("Guardar cambios");
        guardarCambios.setBounds(120, 520, 180, 40);
        guardarCambios.setFont(paraLetras);
        guardarCambios.setBackground(Color.decode("#EAE9D3"));
        add(guardarCambios);

        volver = new JButton("Volver");
        volver.setBounds(320, 520, 150, 40);
        volver.setFont(paraLetras);
        volver.setBackground(Color.decode("#EAE9D3"));
        add(volver);

        buscarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarEvento();
            }
        });

        guardarCambios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editar();
                AdministrarEventos menu = new AdministrarEventos(manejo, eventos);
                menu.setVisible(true);
                dispose();
            }
        });

        volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministrarEventos menu = new AdministrarEventos(manejo, eventos);
                menu.setVisible(true);
                dispose();
            }
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
            
            if (evento.isCancelado())
            {
            JOptionPane.showMessageDialog(this, "No se puede editar un evento cancelado!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            }

            this.eventoActual = evento;

            nombre.setText(evento.getTitulo());
            descripcion.setText(evento.getDescripcion());
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            fechaS.setDate(format.parse(evento.getFecha()));
            fechaS.setEnabled(true);
            costo.setText(Double.toString(evento.getCosto()));

            int maxCapacidad = 30000;
            if (evento instanceof Deportivo) {
                maxCapacidad = 20000;
            } else if (evento instanceof Musical) {
                maxCapacidad = 25000;
            }

            spinnerCapacidad.setModel(new SpinnerNumberModel(evento.getCapacidad(), 0, maxCapacidad, 1));
            
            if(evento.isRealizado())
            {
            fechaS.setEnabled(false);
            }
            mostrarPanelExtra(evento);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar el evento.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarPanelExtra(categorias evento) {
        panelExtra.removeAll();

        if (evento instanceof Deportivo) {
            String[] deportes = {"Futbol", "Tenis", "Rugby", "Baseball"};
            c2 = new JComboBox<>(deportes);
            c2.setBounds(20, 10, 150, 25);
            c2.setSelectedItem(((Deportivo) evento).getTipoDeporte());
            c2.setEnabled(false);
            panelExtra.add(c2);

            // Nombres de equipos
            JLabel eq1L = new JLabel("Equipo 1:");
            eq1L.setBounds(200, 10, 120, 25);
            eq1L.setFont(paraLetras);
            panelExtra.add(eq1L);

            nombreEquipo1TF = new JTextField(((Deportivo) evento).getNombreEquipo1());
            nombreEquipo1TF.setBounds(320, 10, 120, 25);
            nombreEquipo1TF.setFont(paraLetras);
            panelExtra.add(nombreEquipo1TF);

            JLabel eq2L = new JLabel("Equipo 2:");
            eq2L.setBounds(200, 40, 120, 25);
            eq2L.setFont(paraLetras);
            panelExtra.add(eq2L);

            nombreEquipo2TF = new JTextField(((Deportivo) evento).getNombreEquipo2());
            nombreEquipo2TF.setBounds(320, 40, 120, 25);
            nombreEquipo2TF.setFont(paraLetras);
            panelExtra.add(nombreEquipo2TF);

            modeloEquipo1 = new DefaultTableModel(new Object[]{"Jugadores Equipo 1"}, 0);
            for (String j : ((Deportivo) evento).getJugadoresEquipo1()) {
                modeloEquipo1.addRow(new Object[]{j});
            }
            tablaEquipo1 = new JTable(modeloEquipo1);
            JScrollPane scroll1 = new JScrollPane(tablaEquipo1);
            tablaEquipo1.setFont(paraLetras);
            scroll1.setBounds(60, 80, 200, 150);
            tablaEquipo1.getTableHeader().setReorderingAllowed(false); // para que no se muevan las celdas
            panelExtra.add(scroll1);

            JButton addE1 = new JButton("Añadir");
            addE1.setBounds(20, 240, 150, 25);
            addE1.setFont(paraLetras);
            addE1.setBackground(Color.decode("#EAE9D3"));
            addE1.addActionListener(e -> modeloEquipo1.addRow(new Object[]{""}));
            panelExtra.add(addE1);

            JButton delE1 = new JButton("Eliminar");
            delE1.setBounds(170, 240, 130, 25);
            delE1.setFont(paraLetras);
            delE1.setBackground(Color.decode("#EAE9D3"));
            delE1.addActionListener(e -> {
                int sel = tablaEquipo1.getSelectedRow();
                if (sel != -1) {
                    modeloEquipo1.removeRow(sel);
                }
            });
            panelExtra.add(delE1);

            modeloEquipo2 = new DefaultTableModel(new Object[]{"Jugadores Equipo 2"}, 0);
            for (String j : ((Deportivo) evento).getJugadoresEquipo2()) {
                modeloEquipo2.addRow(new Object[]{j});
            }
            tablaEquipo2 = new JTable(modeloEquipo2);
            tablaEquipo2.getTableHeader().setReorderingAllowed(false); // para que no se muevan las celdas
            tablaEquipo2.setFont(paraLetras);
            JScrollPane scroll2 = new JScrollPane(tablaEquipo2);
            scroll2.setBounds(340, 80, 200, 150);
            panelExtra.add(scroll2);

            JButton addE2 = new JButton("Añadir");
            addE2.setBounds(300, 240, 150, 25);
            addE2.setFont(paraLetras);
            addE2.setBackground(Color.decode("#EAE9D3"));
            addE2.addActionListener(e -> modeloEquipo2.addRow(new Object[]{""}));
            panelExtra.add(addE2);

            JButton delE2 = new JButton("Eliminar");
            delE2.setBounds(450, 240, 130, 25);
            delE2.setFont(paraLetras);
            delE2.setBackground(Color.decode("#EAE9D3"));
            delE2.addActionListener(e -> {
                int sel = tablaEquipo2.getSelectedRow();
                if (sel != -1) {
                    modeloEquipo2.removeRow(sel);
                }
            });
            panelExtra.add(delE2);

        } else if (evento instanceof Musical) {
            String[] tipos = {"Pop", "Rock", "Rap", "Clasica", "Reggaeton", "Otro"};
            c2 = new JComboBox<>(tipos);
            c2.setBounds(20, 10, 150, 25);
            c2.setSelectedItem(((Musical) evento).getTipoMusica());
            c2.setEnabled(false);
            panelExtra.add(c2);

            modeloMontaje = new DefaultTableModel(new Object[]{"Equipo de Montaje"}, 0);
            for (String p : ((Musical) evento).getEquipoMontaje()) {
                modeloMontaje.addRow(new Object[]{p});
            }
            tablaMontaje = new JTable(modeloMontaje);
            JScrollPane scroll = new JScrollPane(tablaMontaje);
            tablaMontaje.setFont(paraLetras);
            scroll.setBounds(20, 50, 300, 150);
            tablaMontaje.getTableHeader().setReorderingAllowed(false); // para que no se muevan las celdas
            panelExtra.add(scroll);

            JButton add = new JButton("Añadir persona");
            add.setBounds(20, 210, 150, 25);
            add.setFont(paraLetras);
            add.setBackground(Color.decode("#EAE9D3"));
            add.addActionListener(e -> modeloMontaje.addRow(new Object[]{""}));
            panelExtra.add(add);

            JButton del = new JButton("Eliminar persona");
            del.setBounds(180, 210, 150, 25);
            del.setFont(paraLetras);
            del.setBackground(Color.decode("#EAE9D3"));
            del.addActionListener(e -> {
                int sel = tablaMontaje.getSelectedRow();
                if (sel != -1) {
                    modeloMontaje.removeRow(sel);
                }
            });
            panelExtra.add(del);

        } else if (evento instanceof Religioso) {
            JLabel l = new JLabel("Convertidos:");
            l.setBounds(20, 10, 100, 25);
            panelExtra.add(l);

            spinnerConvertidos = new JSpinner(new SpinnerNumberModel(((Religioso) evento).getConvertidos(), 0, 30000, 1));
            spinnerConvertidos.setBounds(120, 10, 80, 25);
            spinnerConvertidos.setFont(paraLetras);
            panelExtra.add(spinnerConvertidos);
        }

        panelExtra.revalidate();
        panelExtra.repaint();
    }

    private void editar() {
        if (eventoActual == null) {
            JOptionPane.showMessageDialog(this, "Primero busca un evento.");
            return;
        }
        try {
            Date fechaSeleccionada = fechaS.getDate();

            Calendar calHoy = Calendar.getInstance();
            calHoy.set(Calendar.HOUR_OF_DAY, 0);
            calHoy.set(Calendar.MINUTE, 0);
            calHoy.set(Calendar.SECOND, 0);
            calHoy.set(Calendar.MILLISECOND, 0);

            Calendar calSel = Calendar.getInstance();
            calSel.setTime(fechaSeleccionada);
            calSel.set(Calendar.HOUR_OF_DAY, 0);
            calSel.set(Calendar.MINUTE, 0);
            calSel.set(Calendar.SECOND, 0);
            calSel.set(Calendar.MILLISECOND, 0);

            boolean esHoy = calHoy.get(Calendar.YEAR) == calSel.get(Calendar.YEAR)
                    && calHoy.get(Calendar.MONTH) == calSel.get(Calendar.MONTH)
                    && calHoy.get(Calendar.DAY_OF_MONTH) == calSel.get(Calendar.DAY_OF_MONTH);

            if (esHoy) {
                JOptionPane.showMessageDialog(this, "No puedes elegir la fecha de hoy!");
                return;
            }

            if (fechaSeleccionada.before(calHoy.getTime())) {
                eventoActual.setRealizado(true);
            }

            eventoActual.setTitulo(nombre.getText());
            eventoActual.setDescripcion(descripcion.getText());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            eventoActual.setFecha(format.format(fechaS.getDate()));
            eventoActual.setCosto(Double.parseDouble(costo.getText()));

            int capacidadValor = (Integer) spinnerCapacidad.getValue();
            eventoActual.setCapacidad(capacidadValor);

            if (eventoActual instanceof Deportivo) {
                Deportivo dep = (Deportivo) eventoActual;
                dep.setTipoDeporte((String) c2.getSelectedItem());

                // actualizar nombres equipos
                dep.setNombreEquipo1(nombreEquipo1TF.getText().trim());
                dep.setNombreEquipo2(nombreEquipo2TF.getText().trim());

                ArrayList<String> eq1 = new ArrayList<>();
                for (int i = 0; i < modeloEquipo1.getRowCount(); i++) {
                    String nombre = (String) modeloEquipo1.getValueAt(i, 0);
                    if (nombre != null && !nombre.trim().isEmpty()) {
                        eq1.add(nombre.trim());
                    }
                }
                dep.setJugadoresEquipo1(eq1);

                ArrayList<String> eq2 = new ArrayList<>();
                for (int i = 0; i < modeloEquipo2.getRowCount(); i++) {
                    String nombre = (String) modeloEquipo2.getValueAt(i, 0);
                    if (nombre != null && !nombre.trim().isEmpty()) {
                        eq2.add(nombre.trim());
                    }
                }
                dep.setJugadoresEquipo2(eq2);

            } else if (eventoActual instanceof Musical) {
                Musical mus = (Musical) eventoActual;
                mus.setTipoMusica((String) c2.getSelectedItem());

                ArrayList<String> equipo = new ArrayList<>();
                for (int i = 0; i < modeloMontaje.getRowCount(); i++) {
                    String nombre = (String) modeloMontaje.getValueAt(i, 0);
                    if (nombre != null && !nombre.trim().isEmpty()) {
                        equipo.add(nombre.trim());
                    }
                }
                mus.setEquipoMontaje(equipo);

            } else if (eventoActual instanceof Religioso) {
                Religioso rel = (Religioso) eventoActual;
                rel.setConvertidos((Integer) spinnerConvertidos.getValue());
            }

            JOptionPane.showMessageDialog(this, "Evento actualizado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los cambios.");
        }
    }
}
