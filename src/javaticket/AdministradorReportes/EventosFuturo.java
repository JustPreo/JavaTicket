/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaticket.AdministradorReportes;

import javaticket.Categorias.*;
import javaticket.Manejo.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import javaticket.MenuPrincipal;

public class EventosFuturo extends JFrame {

    private ManejoDeEventos eventos;
    private ManejoDeUsuarios manejo;
    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JTextArea lblEstadisticas;
    private JButton Volver;

    public EventosFuturo(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.eventos = eventos;
        this.manejo = manejo;

        setSize(800, 650);
        setTitle("Eventos Futuros");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Tabla
        String[] columnas = {"CODIGO", "TIPO", "TITULO", "FECHA", "MONTO"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCeldaEditable(int row, int column) {
                return false;
            }
        };//Casi que copypaste de lo que hice en editarEvento
        tablaEventos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaEventos);
        scroll.setBounds(50, 50, 700, 350);
        add(scroll);
        lblEstadisticas = new JTextArea();
        lblEstadisticas.setBounds(50, 420, 700, 150);
        lblEstadisticas.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEstadisticas.setForeground(Color.BLACK);
        lblEstadisticas.setEditable(false);
        lblEstadisticas.setBackground(getContentPane().getBackground()); 
        lblEstadisticas.setLineWrap(true);
        lblEstadisticas.setWrapStyleWord(true);
        add(lblEstadisticas);

        cargarEventosFuturos();
        
        Volver = new JButton("Volver");
        Volver.setBounds(165, 425, 175, 50);
        add(Volver);
        
        Volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministradorReportes menu = new AdministradorReportes(manejo,eventos);
                menu.setVisible(true);
                dispose();
            }
        });
    }

    private void cargarEventosFuturos() {
        modeloTabla.setRowCount(0); // Limpiar tabla

        ArrayList<categorias> futurama = new ArrayList<>();
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.HOUR_OF_DAY, 0);
        hoy.set(Calendar.MINUTE, 0);
        hoy.set(Calendar.SECOND, 0);
        hoy.set(Calendar.MILLISECOND, 0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (categorias e : eventos.eventos) {
            if (!e.isCancelado()) {
                try {
                    Date fechaEvento = sdf.parse(e.getFecha());
                    if (fechaEvento.after(hoy.getTime()) && !e.isRealizado()) {
                        futurama.add(e);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        int Deportivos = 0, Religiosos = 0, Musicales = 0;
        double DeportivosTC = 0, ReligiososTC = 0, MusicalesTC = 0;//TotalCosto

        for (categorias evento : futurama) {
            String tipo = "";
            if (evento instanceof Deportivo) {
                tipo = "DEPORTIVO";
            } else if (evento instanceof Religioso) {
                tipo = "RELIGIOSO";
            } else if (evento instanceof Musical) {
                tipo = "MUSICAL";
            }

            modeloTabla.addRow(new Object[]{
                evento.getCodigo(), tipo, evento.getTitulo(), evento.getFecha(), String.format("%.2f", evento.getCosto())
            });

            switch (tipo) {
                case "DEPORTIVO":
                    Deportivos++;
                    DeportivosTC += evento.getCosto();
                    break;
                case "RELIGIOSO":
                    Religiosos++;
                    ReligiososTC += evento.getCosto();
                    break;
                case "MUSICAL":
                    Musicales++;
                    MusicalesTC += evento.getCosto();
                    break;
            }
        }

        String textoEstadisticas = "Deportivos: " + Deportivos + " eventos | Monto total: Lps." + String.format("%.2f", DeportivosTC)
                + "\nReligiosos: " + Religiosos + " eventos | Monto total: Lps." + String.format("%.2f", ReligiososTC)
                + " \nMusicales: " + Musicales + " eventos | Monto total: Lps." + String.format("%.2f", MusicalesTC);

        lblEstadisticas.setText(textoEstadisticas);
        
        if (lblEstadisticas.getText().equals(""))
        {
        lblEstadisticas.setText("No hay estadisticas que mostrar");
        }
    }

}
