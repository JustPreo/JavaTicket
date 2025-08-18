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

public class EventosCancelados extends JFrame {

    private ManejoDeEventos eventos;
    private ManejoDeUsuarios manejo;
    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JTextArea lblEstadisticas;
    private JButton Volver;

    public EventosCancelados(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.eventos = eventos;
        this.manejo = manejo;

        setSize(800, 650);
        setTitle("Eventos Cancelados");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#394B66"));
        Font paraLetras = new Font("Roboto", Font.BOLD, 16);

        // Tabla
        String[] columnas = {"CODIGO", "TIPO", "TITULO", "FECHA", "MULTA"};
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

        // Área de estadísticas
        lblEstadisticas = new JTextArea();
        lblEstadisticas.setBounds(50, 420, 700, 150);
        lblEstadisticas.setFont(paraLetras);
        lblEstadisticas.setForeground(Color.decode("#EAE9D3"));
        lblEstadisticas.setEditable(false);
        lblEstadisticas.setBackground(getContentPane().getBackground());
        lblEstadisticas.setLineWrap(true);
        lblEstadisticas.setWrapStyleWord(true);
        add(lblEstadisticas);

        cargarEventosCancelados();

        // Botón Volver
        Volver = new JButton("Volver");
        Volver.setBounds(325, 570, 150, 40);
        Volver.setFont(paraLetras);
        Volver.setBackground(Color.decode("#EAE9D3"));
        Volver.setForeground(Color.black);
        add(Volver);

        Volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministradorReportes menu = new AdministradorReportes(manejo, eventos);
                menu.setVisible(true);
                dispose();
            }
        });
    }

    private void cargarEventosCancelados() {
        modeloTabla.setRowCount(0); // Limpiar tabla

        ArrayList<categorias> cancelados = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (categorias e : eventos.eventos) {
            if (e.isCancelado()) {
                cancelados.add(e);
            }
        }

        // Ordenar del más reciente al más antiguo
        cancelados.sort((a, b) -> {
            try {
                Date fechaA = sdf.parse(a.getFecha());
                Date fechaB = sdf.parse(b.getFecha());
                return fechaB.compareTo(fechaA);
            } catch (Exception ex) {
                return 0;
            }
        });

        int Deportivos = 0, Religiosos = 0, Musicales = 0;
        double DeportivosMulta = 0, ReligiososMulta = 0, MusicalesMulta = 0;

        for (categorias evento : cancelados) {
            String tipo = "";
            if (evento instanceof Deportivo) tipo = "DEPORTIVO";
            else if (evento instanceof Religioso) tipo = "RELIGIOSO";
            else if (evento instanceof Musical) tipo = "MUSICAL";

            double multa = evento.getMulta();

            modeloTabla.addRow(new Object[]{
                evento.getCodigo(), tipo, evento.getTitulo(), evento.getFecha(), String.format("%.2f", multa)
            });

            switch (tipo) {
                case "DEPORTIVO":
                    Deportivos++;
                    DeportivosMulta += multa;
                    break;
                case "RELIGIOSO":
                    Religiosos++;
                    ReligiososMulta += multa;
                    break;
                case "MUSICAL":
                    Musicales++;
                    MusicalesMulta += multa;
                    break;
            }
        }

        String textoEstadisticas = "Cantidad Deportivos: " + Deportivos + " eventos | Multa(s): Lps." + String.format("%.2f", DeportivosMulta)
                + "\nCantidad Religiosos: " + Religiosos + " eventos | Multa(s): Lps." + String.format("%.2f", ReligiososMulta)
                + "\nCantidad Musicales: " + Musicales + " eventos | Multa(s): Lps." + String.format("%.2f", MusicalesMulta);

        lblEstadisticas.setText(textoEstadisticas);
    }
}