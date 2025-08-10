package javaticket.AdministradorEventos;

import javaticket.Categorias.categorias;
import javaticket.Categorias.Deportivo;
import javaticket.Categorias.Musical;
import javaticket.Categorias.Religioso;
import javaticket.Manejo.ManejoDeEventos;

import javax.swing.*;
import java.awt.Color;
import javaticket.Manejo.ManejoDeUsuarios;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class VerEvento extends JFrame {

    private ManejoDeEventos eventos;
    private ManejoDeUsuarios manejo;

    private JLabel codigoL;
    private JTextField codigoTF;
    private JButton buscarBtn, volverBtn;
    private JTextArea areaTexto;

    public VerEvento(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.eventos = eventos;
        this.manejo = manejo;

        setSize(600, 500);
        setTitle("Ver Evento");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.lightGray);

        codigoL = new JLabel("Ingrese codigo del evento:");
        codigoL.setBounds(20, 20, 180, 30);
        add(codigoL);

        codigoTF = new JTextField();
        codigoTF.setBounds(200, 20, 100, 30);
        add(codigoTF);

        buscarBtn = new JButton("Buscar");
        buscarBtn.setBounds(320, 20, 100, 30);
        add(buscarBtn);

        areaTexto = new JTextArea();
        areaTexto.setBounds(20, 70, 550, 350);
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        add(areaTexto);

        volverBtn = new JButton("Volver");
        volverBtn.setBounds(230, 430, 100, 30);
        add(volverBtn);

        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                buscarEvento();
            }
        });

        volverBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                AdministrarEventos admin = new AdministrarEventos(manejo,eventos);
                dispose();
            }
        });

        setVisible(true);
    }

    private void buscarEvento() {
        try {
            int codigo = Integer.parseInt(codigoTF.getText());
            categorias evento = eventos.buscarEvento(codigo);

            if (evento == null) {
                JOptionPane.showMessageDialog(this, "No se encontro evento con ese codigo.", "Error", JOptionPane.ERROR_MESSAGE);
                areaTexto.setText("");
                return;
            }

            areaTexto.setText("");

            areaTexto.append("Codigo: " + evento.getCodigo() + "\n");
            areaTexto.append("Titulo: " + evento.getTitulo() + "\n");
            areaTexto.append("Descripcion: " + evento.getDescripcion() + "\n");
            areaTexto.append("Fecha: " + evento.getFecha() + "\n");
            areaTexto.append("Costo: $" + evento.getCosto() + "\n");
            areaTexto.append("Tipo: " + evento.getClass().getSimpleName() + "\n");

            areaTexto.append("\nEstado del Evento:\n");
            if (evento.isCancelado()) {
                areaTexto.append("- Evento cancelado\n");
                if (evento.getMulta() > 0) {
                    areaTexto.append(String.format("- Multa cobrada: Lps %.2f\n", evento.getMulta()));
                }
            } else if (evento.isRealizado()) {
                areaTexto.append("- Evento realizado\n");
            } else {
                areaTexto.append("- Evento activo\n");
            }

            // Datos especificos segun tipo
            if (evento instanceof Deportivo) {
                Deportivo dep = (Deportivo) evento;
                areaTexto.append("\n-- Datos Deportivos --\n");
                areaTexto.append("Tipo de Deporte: " + dep.getTipoDeporte() + "\n");
                areaTexto.append("Equipo 1:\n");
                for (String jugador : dep.getJugadoresEquipo1()) {
                    areaTexto.append("  - " + jugador + "\n");
                }
                areaTexto.append("Equipo 2:\n");
                for (String jugador : dep.getJugadoresEquipo2()) {
                    areaTexto.append("  - " + jugador + "\n");
                }
            } else if (evento instanceof Musical) {
                Musical mus = (Musical) evento;
                areaTexto.append("\n-- Datos Musicales --\n");
                areaTexto.append("Tipo de Musica: " + mus.getTipoMusica() + "\n");
                areaTexto.append("Equipo de Montaje:\n");
                for (String persona : mus.getEquipoMontaje()) {
                    areaTexto.append("  - " + persona + "\n");
                }
            } else if (evento instanceof Religioso) {
                Religioso rel = (Religioso) evento;
                areaTexto.append("\n-- Datos Religiosos --\n");
                areaTexto.append("Personas Convertidas: " + rel.getConvertidos() + "\n");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un codigo valido.", "Error", JOptionPane.ERROR_MESSAGE);
            areaTexto.setText("");
        }
    }

    
}
