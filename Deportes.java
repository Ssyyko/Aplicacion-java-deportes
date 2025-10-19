package com.mycompany.deportes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import javax.imageio.ImageIO;

public class Deportes extends JFrame {

    private int totalMinutos = 0;
    private JLabel etiquetaTotal;
    private JLabel labelImagen;
    private BufferedImage imagenOriginal;

    public Deportes() {
        setTitle("Menú Principal");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));

        JLabel etiqueta = new JLabel("Bienvenido a la app de deporte", SwingConstants.CENTER);
        panelSuperior.add(etiqueta);

        etiquetaTotal = new JLabel("Total minutos: " + totalMinutos, SwingConstants.CENTER);
        panelSuperior.add(etiquetaTotal);

        labelImagen = new JLabel();
        labelImagen.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());

        JButton btnAbrirRegistro = new JButton("Ir a registro diario");
        panelInferior.add(btnAbrirRegistro);

        btnAbrirRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Registro registro = new Registro(Deportes.this);
                registro.setVisible(true);
                setVisible(false);
            }
        });

        add(panelSuperior, BorderLayout.NORTH);
        add(labelImagen, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        try {
            imagenOriginal = ImageIO.read(new File("C:\\Users\\gpas9\\Downloads\\imagendeportes.jpg"));
            redimensionarImagen();
        } catch (Exception ex) {
            // Si falla, no pasa nada
        }

        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                redimensionarImagen();
            }
        });
    }

    private void redimensionarImagen() {
        if (imagenOriginal != null) {
            int ancho = labelImagen.getWidth();
            int alto = labelImagen.getHeight();
            if (ancho > 0 && alto > 0) {
                Image imagenEscalada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                labelImagen.setIcon(new ImageIcon(imagenEscalada));
            }
        }
    }

    public void agregarMinutos(int minutos) {
        totalMinutos += minutos;
        etiquetaTotal.setText("Total minutos: " + totalMinutos);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Deportes().setVisible(true);
        });
    }
}
