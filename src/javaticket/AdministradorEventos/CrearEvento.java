package javaticket.AdministradorEventos;

import javaticket.Categorias.Musical;
import javaticket.Categorias.Religioso;
import javaticket.Categorias.Deportivo;
import javaticket.Categorias.categorias;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import javax.swing.*;

public class CrearEvento extends JFrame {

    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;

    private JLabel codigoT, nombreT, descripcionT, fechaT, costoT, cantidadT;
    private JTextField codigo, nombre, descripcion, costo, cantidad;
    private JButton crear, volver;
    private JComboBox<String> c1, c2;
    private JDateChooser fechaS;

    public CrearEvento(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;

        setSize(500, 500);
        setTitle("crear evento");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // etiquetas y campos
        codigoT = new JLabel("Codigo");
        codigoT.setBounds(30, 30, 150, 25);
        add(codigoT);
        codigo = new JTextField();
        codigo.setBounds(30, 55, 150, 30);
        add(codigo);

        nombreT = new JLabel("Nombre del evento");
        nombreT.setBounds(30, 90, 150, 25);
        add(nombreT);
        nombre = new JTextField();
        nombre.setBounds(30, 115, 150, 30);
        add(nombre);

        descripcionT = new JLabel("Descripcion");
        descripcionT.setBounds(30, 150, 150, 25);
        add(descripcionT);
        descripcion = new JTextField();
        descripcion.setBounds(30, 175, 150, 30);
        add(descripcion);

        fechaT = new JLabel("Fecha");
        fechaT.setBounds(250, 30, 150, 25);
        add(fechaT);
        fechaS = new JDateChooser();
        fechaS.setDateFormatString("yyyy-MM-dd");
        // fechaS.setMinSelectableDate(new java.util.Date()); //Esto es para lo de la fecha minima
        fechaS.setBounds(250, 55, 150, 30);
        add(fechaS);

        costoT = new JLabel("Costo");
        costoT.setBounds(250, 90, 150, 25);
        add(costoT);
        costo = new JTextField();
        costo.setBounds(250, 115, 150, 30);
        add(costo);

        cantidadT = new JLabel("Cantidad de personas");
        cantidadT.setBounds(250, 150, 150, 25);
        add(cantidadT);
        cantidad = new JTextField();
        cantidad.setBounds(250, 175, 150, 30);
        add(cantidad);

        String[] categorias = {"", "Deportivo", "Musical", "Religioso"};
        c1 = new JComboBox<>(categorias);
        c1.setBounds(30, 220, 150, 30);
        add(c1);

        volver = new JButton("Volver");
        volver.setBounds(160, 350, 150, 40);
        add(volver);

        crear = new JButton("Crear evento");
        crear.setBounds(160, 300, 150, 40);
        add(crear);

        c1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = (String) c1.getSelectedItem();
                switch (item) {
                    case "Deportivo":
                        crearCOMBODeportivo();
                        break;
                    case "Musical":
                        crearCOMBOMusical();
                        break;
                    case "Religioso":
                        eliminarCombo();
                        break;
                    default:
                        eliminarCombo();
                        break;
                }
            }
        });

        volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministrarEventos menu = new AdministrarEventos(manejo, eventos);
                menu.setVisible(true);
                dispose();
            }
        });

        crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearEvento();
            }
        });
    }

    private void crearCOMBODeportivo() {
        eliminarCombo();
        String[] deportivo = {"Futbol", "Tenis", "Rugby", "Baseball"};
        c2 = new JComboBox<>(deportivo);
        c2.setBounds(30, 260, 150, 30);
        add(c2);
        repaint();
    }

    private void crearCOMBOMusical() {
        eliminarCombo();
        String[] musical = {"Pop", "Rock", "Rap", "Clasica", "Reggaeton", "Otro"};
        c2 = new JComboBox<>(musical);
        c2.setBounds(30, 260, 150, 30);
        add(c2);
        repaint();
    }

    private void eliminarCombo() {
        if (c2 != null) {
            remove(c2);
            c2 = null;
            repaint();
        }
    }

    public boolean crearEvento() {
        if (!revisarCamposVacios()) {
            JOptionPane.showMessageDialog(this, "Por favor llena todos los campos");
            return false;
        }

        try {
            int cod = parseInt(codigo.getText());
            String nom = nombre.getText();
            String desc = descripcion.getText();
            String tipoEvento = (String) c1.getSelectedItem();
            String subtipo = (c2 != null) ? (String) c2.getSelectedItem() : "Otro";
            String fechaStr = new SimpleDateFormat("yyyy-MM-dd").format(fechaS.getDate());
            double costoD = parseDouble(costo.getText());
            int cantidadP = parseInt(cantidad.getText());

            categorias evento = null;

            switch (tipoEvento) {
                case "Deportivo":
                    if (cantidadP > 20000) {
                        JOptionPane.showMessageDialog(this, "La cantidad maxima permitida es 20000");
                        return false;
                    }
                    if (!validarDeporte(subtipo)) {
                        JOptionPane.showMessageDialog(this, "Tipo de deporte no valido");
                        return false;
                    }
                    evento = new Deportivo(cod, nom, desc, fechaStr, costoD, cantidadP, subtipo, "Equipo a", "Equipo b");

                    break;
                case "Musical":
                    if (cantidadP > 25000) {
                        JOptionPane.showMessageDialog(this, "La cantidad maxima permitida es 25000");
                        return false;
                    }
                    if (!validarMusica(subtipo)) {
                        JOptionPane.showMessageDialog(this, "Tipo de musica no valido");
                        return false;
                    }
                    evento = new Musical(cod, nom, desc, fechaStr, costoD, cantidadP, subtipo);
                    break;
                case "Religioso":
                    if (cantidadP > 30000) {
                        JOptionPane.showMessageDialog(this, "La cantidad maxima permitida es 30000");
                        return false;
                    }
                    evento = new Religioso(cod, nom, desc, fechaStr, costoD, cantidadP);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Selecciona una categoria valida");
                    return false;
            }
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

            if (fechaSeleccionada.before(calHoy.getTime())) {
                evento.setRealizado(true);
            }

            boolean esHoy = calHoy.get(Calendar.YEAR) == calSel.get(Calendar.YEAR)
                    && calHoy.get(Calendar.MONTH) == calSel.get(Calendar.MONTH)
                    && calHoy.get(Calendar.DAY_OF_MONTH) == calSel.get(Calendar.DAY_OF_MONTH);

            if (!esHoy && eventos.crearEvento(evento)) {    
                JOptionPane.showMessageDialog(this, "Evento creado exitosamente");
                manejo.agregarArray(evento);
                limpiarCampos();
                return true;
            } else if (esHoy) {
                JOptionPane.showMessageDialog(this, "No puedes elegir la fecha de hoy!");
                
                return false;
            }
            else
            {
            return false;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crear evento | parametros incorrectos");
            return false;
        }
    }

    private boolean validarDeporte(String deporte) {
        String[] validos = {"Futbol", "Tenis", "Rugby", "Baseball"};
        for (String v : validos) {
            if (v.equalsIgnoreCase(deporte)) {
                return true;
            }
        }
        return false;
    }

    private boolean validarMusica(String musica) {
        String[] validos = {"Pop", "Rock", "Rap", "Clasica", "Reggaeton", "Otro"};
        for (String v : validos) {
            if (v.equalsIgnoreCase(musica)) {
                return true;
            }
        }
        return false;
    }

    private boolean revisarCamposVacios() {
        return !codigo.getText().isEmpty()
                && !nombre.getText().isEmpty()
                && !descripcion.getText().isEmpty()
                && fechaS.getDate() != null
                && !costo.getText().isEmpty()
                && !cantidad.getText().isEmpty()
                && c1.getSelectedIndex() > 0;
    }

    private void limpiarCampos() {
        codigo.setText("");
        nombre.setText("");
        descripcion.setText("");
        costo.setText("");
        cantidad.setText("");
        fechaS.setDate(null);
        c1.setSelectedIndex(0);
        eliminarCombo();
    }
}
