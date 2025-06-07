package vistas.administrativo;

import javax.swing.*;
import java.awt.*;

public class ExportarDatosPiloto extends JFrame {

    private JButton btnCSV;
    private JButton btnPDF;
    private JButton btnExcel;

    public ExportarDatosPiloto() {
        setTitle("Exportar Datos de Pilotos");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        btnCSV = new JButton("CSV");
        btnPDF = new JButton("PDF");
        btnExcel = new JButton("Excel");

        add(btnCSV);
        add(btnPDF);
        add(btnExcel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExportarDatosPiloto ventana = new ExportarDatosPiloto();
            ventana.setVisible(true);
        });
    }

    public JButton getBtnCSV() {
        return btnCSV;
    }

    public JButton getBtnPDF() {
        return btnPDF;
    }

    public JButton getBtnExcel() {
        return btnExcel;
    }
}
