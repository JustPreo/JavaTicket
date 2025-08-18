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

public class EventosRealizados extends JFrame {

    private ManejoDeEventos eventos;
    private ManejoDeUsuarios manejo;
    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JTextArea lblEstadisticas;
    private JButton Volver;

    public EventosRealizados(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.eventos = eventos;
        this.manejo = manejo;

        setSize(800, 650);
        setTitle("Eventos Realizados");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#394B66"));
        Font paraLetras = new Font("Roboto", Font.BOLD, 16);

        // Tabla
        String[] columnas = {"CODIGO", "TIPO", "TITULO", "FECHA", "MONTO"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEventos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaEventos);
        scroll.setBounds(50, 50, 700, 350);
        tablaEventos.getTableHeader().setReorderingAllowed(false); // para que no se muevan las celdas
        add(scroll);

        lblEstadisticas = new JTextArea();
        lblEstadisticas.setBounds(50, 420, 700, 150);
        lblEstadisticas.setFont(paraLetras);
        lblEstadisticas.setForeground(Color.decode("#EAE9D3"));
        lblEstadisticas.setEditable(false);
        lblEstadisticas.setBackground(getContentPane().getBackground()); 
        lblEstadisticas.setLineWrap(true);
        lblEstadisticas.setWrapStyleWord(true);
        add(lblEstadisticas);

        cargarEventosRealizados();

        Volver = new JButton("Volver");
        Volver.setBounds(325, 570, 150, 40);
        Volver.setFont(paraLetras);
        Volver.setBackground(Color.decode("#EAE9D3"));
        Volver.setForeground(Color.black);
        add(Volver);

        Volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministradorReportes menu = new AdministradorReportes(manejo,eventos);
                menu.setVisible(true);
                dispose();
            }
        });
    }

    private void cargarEventosRealizados() {
        modeloTabla.setRowCount(0); // Limpiar tabla

        ArrayList<categorias> realizados = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (categorias e : eventos.eventos) {
            if (!e.isCancelado() && e.isRealizado()) {
                try {
                    Date fechaEvento = sdf.parse(e.getFecha());
                    realizados.add(e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        // Ordenar del más reciente al más antiguo
        realizados.sort((a, b) -> {
            try {
                Date fechaA = sdf.parse(a.getFecha());
                Date fechaB = sdf.parse(b.getFecha());
                return fechaB.compareTo(fechaA);
            } catch (Exception ex) {
                return 0;
            }
        });

        int Deportivos = 0, Religiosos = 0, Musicales = 0;
        double DeportivosTC = 0, ReligiososTC = 0, MusicalesTC = 0;

        for (categorias evento : realizados) {
            String tipo = "";
            if (evento instanceof Deportivo) tipo = "DEPORTIVO";
            else if (evento instanceof Religioso) tipo = "RELIGIOSO";
            else if (evento instanceof Musical) tipo = "MUSICAL";
            double costo = evento.getCosto();
            

            switch (tipo) {
                case "DEPORTIVO":
                    Deportivos++;
                    DeportivosTC += evento.getCosto();
                    break;
                case "RELIGIOSO":
                    Religiosos++;
                    ReligiososTC += evento.getCosto() + 2000;
                    costo += (2000);
                    
                    break;
                case "MUSICAL":
                    Musicales++;
                    MusicalesTC += evento.getCosto() + (evento.getCosto() * 0.3);
                    costo += (evento.getCosto() * 0.3);
                    break;
            }
            
            modeloTabla.addRow(new Object[]{
                evento.getCodigo(), tipo, evento.getTitulo(), evento.getFecha(), String.format("%.2f",costo)
            });
        }

        String textoEstadisticas = "Cantidad Deportivos: " + Deportivos + " eventos | Total: Lps." + String.format("%.2f", DeportivosTC)
                + "\nCantidad Religiosos: " + Religiosos + " eventos | Total: Lps." + String.format("%.2f", ReligiososTC)
                + "\nCantidad Musicales: " + Musicales + " eventos | Total: Lps." + String.format("%.2f", MusicalesTC);

        lblEstadisticas.setText(textoEstadisticas);
    }
}
