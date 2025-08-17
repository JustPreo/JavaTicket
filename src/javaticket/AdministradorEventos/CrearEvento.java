package javaticket.AdministradorEventos;

import javaticket.Categorias.Musical;
import javaticket.Categorias.Religioso;
import javaticket.Categorias.Deportivo;
import javaticket.Categorias.categorias;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
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
    Font paraLetras;
    private JDateChooser fechaS;

    // nuevos campos para equipos
    private JLabel equipo1T, equipo2T;
    private JTextField equipo1, equipo2;

    public CrearEvento(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        paraLetras = new Font("Roboto", Font.BOLD, 16);
        Font paraTitulos = new Font("Roboto", Font.BOLD, 25);

        setSize(500, 500);
        setTitle("crear evento");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#394B66"));//Azul)?

        // etiquetas y campos
        codigoT = new JLabel("Codigo");
        codigoT.setBounds(30, 30, 150, 25);
        codigoT.setFont(paraLetras);
        codigoT.setForeground(Color.decode("#EAE9D3"));
        add(codigoT);

        codigo = new JTextField();
        codigo.setBounds(30, 55, 150, 30);
        codigo.setFont(paraLetras);
        add(codigo);

        nombreT = new JLabel("Nombre del evento");
        nombreT.setBounds(30, 90, 150, 25);
        nombreT.setFont(paraLetras);
        nombreT.setForeground(Color.decode("#EAE9D3"));
        add(nombreT);

        nombre = new JTextField();
        nombre.setBounds(30, 115, 150, 30);
        nombre.setFont(paraLetras);
        add(nombre);

        descripcionT = new JLabel("Descripcion");
        descripcionT.setBounds(30, 150, 150, 25);
        descripcionT.setFont(paraLetras);
        descripcionT.setForeground(Color.decode("#EAE9D3"));
        add(descripcionT);

        descripcion = new JTextField();
        descripcion.setBounds(30, 175, 150, 30);
        descripcion.setFont(paraLetras);
        add(descripcion);

        fechaT = new JLabel("Fecha");
        fechaT.setBounds(250, 30, 150, 25);
        fechaT.setFont(paraLetras);
        fechaT.setForeground(Color.decode("#EAE9D3"));
        add(fechaT);

        fechaS = new JDateChooser();
        fechaS.setDateFormatString("yyyy-MM-dd");
        fechaS.setBounds(250, 55, 150, 30);
        add(fechaS);

        costoT = new JLabel("Costo");
        costoT.setBounds(250, 90, 150, 25);
        costoT.setFont(paraLetras);
        costoT.setForeground(Color.decode("#EAE9D3"));
        add(costoT);

        costo = new JTextField();
        costo.setBounds(250, 115, 150, 30);
        costo.setFont(paraLetras);
        add(costo);

        cantidadT = new JLabel("Cantidad de personas");
        cantidadT.setBounds(250, 150, 200, 25);
        cantidadT.setFont(paraLetras);
        cantidadT.setForeground(Color.decode("#EAE9D3"));
        add(cantidadT);

        cantidad = new JTextField();
        cantidad.setBounds(250, 175, 150, 30);
        cantidad.setFont(paraLetras);
        add(cantidad);

        String[] categorias = {"", "Deportivo", "Musical", "Religioso"};
        c1 = new JComboBox<>(categorias);
        c1.setBounds(30, 220, 150, 30);
        c1.setFont(paraLetras);
        add(c1);

        volver = new JButton("Volver");
        volver.setBounds(160, 415, 150, 40); //X , Y , L , W
        volver.setFont(paraLetras);
        volver.setBackground(Color.decode("#EAE9D3"));
        volver.setForeground(Color.black);
        add(volver);

        crear = new JButton("Crear evento");
        crear.setBounds(160, 365, 150, 40);
        crear.setFont(paraLetras);
        crear.setBackground(Color.decode("#EAE9D3"));
        crear.setForeground(Color.black);
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
        c2.setFont(paraLetras);
        add(c2);

        // campos para equipos
        equipo1T = new JLabel("Equipo 1");
        equipo1T.setBounds(250, 220, 150, 25);
        equipo1T.setFont(paraLetras);
        equipo1T.setForeground(Color.decode("#EAE9D3"));
        add(equipo1T);

        equipo1 = new JTextField();
        equipo1.setBounds(250, 245, 150, 30);
        equipo1.setFont(paraLetras);
        add(equipo1);

        equipo2T = new JLabel("Equipo 2");
        equipo2T.setBounds(250, 280, 150, 25);
        equipo2T.setFont(paraLetras);
        equipo2T.setForeground(Color.decode("#EAE9D3"));
        add(equipo2T);

        equipo2 = new JTextField();
        equipo2.setBounds(250, 305, 150, 30);
        equipo2.setFont(paraLetras);
        add(equipo2);

        repaint();
    }

    private void crearCOMBOMusical() {
        eliminarCombo();
        String[] musical = {"Pop", "Rock", "Rap", "Clasica", "Reggaeton", "Otro"};
        c2 = new JComboBox<>(musical);
        c2.setBounds(30, 260, 150, 30);
        c2.setFont(paraLetras);
        add(c2);
        repaint();
    }

    private void eliminarCombo() {
        if (c2 != null) {
            remove(c2);
            c2 = null;
        }
        if (equipo1 != null) {
            remove(equipo1);
            remove(equipo1T);
            equipo1 = null;
            equipo1T = null;
        }
        if (equipo2 != null) {
            remove(equipo2);
            remove(equipo2T);
            equipo2 = null;
            equipo2T = null;
        }
        repaint();
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
                    if (equipo1 == null || equipo2 == null || equipo1.getText().isEmpty() || equipo2.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Debes ingresar los nombres de ambos equipos");
                        return false;
                    }
                    evento = new Deportivo(cod, nom, desc, fechaStr, costoD, cantidadP, subtipo,
                                           equipo1.getText(), equipo2.getText());
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
            } else {
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
        if (equipo1 != null) equipo1.setText("");
        if (equipo2 != null) equipo2.setText("");
        eliminarCombo();
    }
}
