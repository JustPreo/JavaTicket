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
import javaticket.Manejo.ManejoDeEventos;
import javaticket.Manejo.ManejoDeUsuarios;
import javax.swing.*;

public class CrearEvento extends JFrame {

    ManejoDeUsuarios manejo;
    ManejoDeEventos eventos;

    private JLabel codigoT, nombreT, descripcionT, fechaT, costoT;
    private JTextField codigo, nombre, descripcion, costo;
    private JButton crear,Volver;
    private JComboBox<String> c1, c2;
    private JDateChooser fechaS;

    public CrearEvento(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;

        setSize(500, 500);
        setTitle("Crear Evento");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.cyan);

        codigoT = new JLabel("Código");
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

        descripcionT = new JLabel("Descripción");
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
        fechaS.setMinSelectableDate(new java.util.Date());
        fechaS.setBounds(250, 55, 150, 30);
        add(fechaS);

        costoT = new JLabel("Costo");
        costoT.setBounds(250, 90, 150, 25);
        add(costoT);

        costo = new JTextField();
        costo.setBounds(250, 115, 150, 30);
        add(costo);

        String[] categorias = {"", "Deportivo", "Musical", "Religioso"};
        c1 = new JComboBox<>(categorias);
        c1.setBounds(250, 175, 150, 30);
        add(c1);
        
        Volver = new JButton("Volver");
        Volver.setBounds(0, 0, 50, 50);
        add(Volver);
        
        Volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministrarEventos menu = new AdministrarEventos(manejo,eventos);
                dispose();
            }
        });

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

        crear = new JButton("Crear Evento");
        crear.setBounds(180, 350, 130, 40);
        add(crear);

        crear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearEvento();
            }
        });

        setVisible(true);
    }

    private void crearCOMBODeportivo() {
        eliminarCombo();
        String[] deportivo = {"Futbol", "Tenis", "Rugby", "Baseball"};
        c2 = new JComboBox<>(deportivo);
        c2.setBounds(250, 220, 150, 30);
        add(c2);
        repaint();
    }

    private void crearCOMBOMusical() {
        eliminarCombo();
        String[] musical = {"Pop", "Rock", "Rap", "Clasica", "Reggaeton", "Otro"};
        c2 = new JComboBox<>(musical);
        c2.setBounds(250, 220, 150, 30);
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

            categorias evento = null;

            switch (tipoEvento) {
                case "Deportivo":
                    if (!validarDeporte(subtipo)) {//Por si acaso
                        JOptionPane.showMessageDialog(this, "Tipo de deporte no válido");
                        return false;
                    }
                    evento = new Deportivo(cod, nom, desc, fechaStr, costoD, subtipo, "Equipo A", "Equipo B");
                    break;
                case "Musical":
                    if (!validarMusica(subtipo)) {//Por si acaso
                        JOptionPane.showMessageDialog(this, "Tipo de música no válido");
                        return false;
                    }
                    evento = new Musical(cod, nom, desc, fechaStr, costoD, subtipo);
                    break;
                case "Religioso":
                    evento = new Religioso(cod, nom, desc, fechaStr, costoD);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Selecciona una categoría válida");
                    return false;
            }

            if (eventos.crearEvento(evento)) {
                JOptionPane.showMessageDialog(this, "Evento creado exitosamente");
                manejo.agregarArray(evento);
                limpiarCampos();
                return true;
            } else {
                //JOptionPane.showMessageDialog(this, "Error: codigo duplicado o evento no válido");
                return false;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crear evento: "+"\nParametros incorrectos");
            return false;
        }
    }

    private boolean validarDeporte(String deporte) {
        String[] validos = {"Futbol", "Tenis", "Rugby", "Baseball"};
        for (String v : validos) {
            if (v.equalsIgnoreCase(deporte)) return true;
        }
        return false;
    }

    private boolean validarMusica(String musica) {
        String[] validos = {"Pop", "Rock", "Rap", "Clasica", "Reggaeton", "Otro"};
        for (String v : validos) {
            if (v.equalsIgnoreCase(musica)) return true;
        }
        return false;
    }

    private boolean revisarCamposVacios() {
        return !codigo.getText().isEmpty()
                && !nombre.getText().isEmpty()
                && !descripcion.getText().isEmpty()
                && fechaS.getDate() != null
                && !costo.getText().isEmpty()
                && c1.getSelectedIndex() > 0; //Porque el 0 es vacio , osea el nulo
    }

    private void limpiarCampos() {
        codigo.setText("");
        nombre.setText("");
        descripcion.setText("");
        costo.setText("");
        fechaS.setDate(null);
        c1.setSelectedIndex(0);
        eliminarCombo();
    }
}
