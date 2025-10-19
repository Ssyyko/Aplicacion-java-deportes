package com.mycompany.deportes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Registro extends JFrame {

    private JComboBox<String> comboDeporte;
    private JTextField campoDuracion;
    private Deportes ventanaPrincipal;

    public Registro(Deportes ventana) {
        ventanaPrincipal = ventana;
        setTitle("Registro Diario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,10,10,10);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelDeporte = new JLabel("Selecciona un deporte:");
        comboDeporte = new JComboBox<>(new String[]{"Correr", "Ciclismo", "Yoga", "Natación"});

        JLabel labelDuracion = new JLabel("Duración (minutos):");
        campoDuracion = new JTextField(10);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String deporte = (String) comboDeporte.getSelectedItem();
                String duracion = campoDuracion.getText().trim();
                if (duracion.isEmpty()) {
                    JOptionPane.showMessageDialog(Registro.this, "Por favor, pon la duración.");
                    return;
                }
                int duracionInt = Integer.parseInt(duracion);
                ventanaPrincipal.agregarMinutos(duracionInt);
                JOptionPane.showMessageDialog(Registro.this, "Datos guardados:\nDeporte: " + deporte + "\nDuración: " + duracion + " min");
                campoDuracion.setText("");
            }
        });

        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.setVisible(true);
                dispose();
            }
        });

        g.gridx = 0; g.gridy = 0;
        add(labelDeporte, g);
        g.gridx = 1;
        add(comboDeporte, g);

        g.gridx = 0; g.gridy = 1;
        add(labelDuracion, g);
        g.gridx = 1;
        add(campoDuracion, g);

        g.gridx = 0; g.gridy = 2;
        add(btnGuardar, g);
        g.gridx = 1;
        add(btnVolver, g);
    }
}