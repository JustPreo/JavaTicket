package javaticket.AdministradorReportes;

import com.toedter.calendar.JDateChooser;
import javaticket.Categorias.*;
import javaticket.Manejo.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class IngresoPorFecha extends JFrame {
    
    private ManejoDeEventos eventos;
    private ManejoDeUsuarios manejo;
    private JButton generarReporteBtn, volver;
    private JTextArea resultadoTA;
    private JDateChooser fechaInicioDC;
    private JDateChooser fechaFinDC;
    
    public IngresoPorFecha(ManejoDeUsuarios manejo, ManejoDeEventos eventos) {
        this.manejo = manejo;
        this.eventos = eventos;
        
        setSize(600, 450);
        setTitle("Ingreso por Fecha");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#394B66"));
        Font fuente = new Font("Roboto", Font.BOLD, 16);
        
        JLabel lblInicio = new JLabel("Fecha Inicial:");
        lblInicio.setBounds(50, 30, 220, 30);
        lblInicio.setForeground(Color.WHITE);
        lblInicio.setFont(fuente);
        add(lblInicio);
        
        fechaInicioDC = new JDateChooser();
        fechaInicioDC.setBounds(270, 30, 150, 30);
        fechaInicioDC.setFont(fuente);
        fechaInicioDC.setDateFormatString("yyyy-MM-dd");
        add(fechaInicioDC);
        
        fechaFinDC = new JDateChooser();
        fechaFinDC.setBounds(270, 80, 150, 30);
        fechaFinDC.setFont(fuente);
        fechaFinDC.setDateFormatString("yyyy-MM-dd");
        add(fechaFinDC);
        
        JLabel lblFin = new JLabel("Fecha Final:");
        lblFin.setBounds(50, 80, 220, 30);
        lblFin.setForeground(Color.WHITE);
        lblFin.setFont(fuente);
        add(lblFin);
        
        generarReporteBtn = new JButton("Generar Reporte");
        generarReporteBtn.setBounds(180, 130, 200, 40);
        generarReporteBtn.setFont(fuente);
        generarReporteBtn.setBackground(Color.decode("#EAE9D3"));
        generarReporteBtn.setForeground(Color.BLACK);
        add(generarReporteBtn);
        
        volver = new JButton("Volver");
        volver.setBounds(180, 360, 200, 40);
        volver.setFont(fuente);
        volver.setBackground(Color.decode("#EAE9D3"));
        volver.setForeground(Color.BLACK);
        add(volver);
        
        resultadoTA = new JTextArea();
        resultadoTA.setBounds(50, 190, 500, 160);
        resultadoTA.setFont(fuente);
        resultadoTA.setEditable(false);
        resultadoTA.setLineWrap(true);
        resultadoTA.setWrapStyleWord(true);
        resultadoTA.setBackground(getContentPane().getBackground());
        resultadoTA.setForeground(Color.WHITE);
        add(resultadoTA);
        
        generarReporteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarReporte();
            }
        });
        
        volver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                new AdministradorReportes(manejo, eventos).setVisible(true);
                dispose();
            }
        });
    }
    
    private void generarReporte() {
        Date fechaInicio = fechaInicioDC.getDate();
        Date fechaFin = fechaFinDC.getDate();
        
        if (fechaInicio == null || fechaFin == null) {
            JOptionPane.showMessageDialog(this, "Seleccione ambas fechas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (fechaFin.before(fechaInicio)) {
            JOptionPane.showMessageDialog(this, "La fecha final no puede ser anterior a la inicial", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            String inicioStr = sdf.format(fechaInicio);
            String finStr = sdf.format(fechaFin);
            
            int Deportivos = 0, Religiosos = 0, Musicales = 0;
            double totalIngresos = 0;
            
            for (categorias evento : eventos.eventos) {
                Date fechaEvento = sdf.parse(evento.getFecha());
                
                if (!fechaEvento.before(fechaInicio) && !fechaEvento.after(fechaFin)) {
                    String tipo = "";
                    if (evento instanceof Deportivo) {
                        tipo = "DEPORTIVO";
                    } else if (evento instanceof Religioso) {
                        tipo = "RELIGIOSO";
                    } else if (evento instanceof Musical) {
                        tipo = "MUSICAL";
                    }
                    
                    double ingresoEvento = 0;
                    
                    if (evento.isCancelado()) {
                        ingresoEvento = evento.getMulta();
                    } else {
                        ingresoEvento = evento.getCosto();
                        if (tipo.equals("RELIGIOSO")) {
                            ingresoEvento += 2000;
                        }
                        if (tipo.equals("MUSICAL")) {
                            ingresoEvento += evento.getCosto() * 0.3;
                        }
                    }
                    
                    totalIngresos += ingresoEvento;
                    
                    switch (tipo) {
                        case "DEPORTIVO" ->
                            Deportivos++;
                        case "RELIGIOSO" ->
                            Religiosos++;
                        case "MUSICAL" ->
                            Musicales++;
                    }
                }
            }
            
            String resultado = "Reporte de Ingresos del " + inicioStr + " al " + finStr + ":\n\n"
                    + "Total de ingresos (incluyendo multas): Lps." + String.format("%.2f", totalIngresos) + "\n"
                    + "Deportivos: " + Deportivos + "\n"
                    + "Religiosos: " + Religiosos + "\n"
                    + "Musicales: " + Musicales;
            
            resultadoTA.setText(resultado);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al generar el reporte", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
