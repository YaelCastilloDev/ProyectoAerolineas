package vistas.administrativo;

import javax.swing.*;
import java.awt.*;

public class ExportarDatosAzafata extends JFrame {

    private JButton btnCSV;
    private JButton btnPDF;
    private JButton btnExcel;

    public ExportarDatosAzafata() {
        setTitle("Exportar Datos de Azafatas");
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
            ExportarDatosAzafata ventana = new ExportarDatosAzafata();
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
