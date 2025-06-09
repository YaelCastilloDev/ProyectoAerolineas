package vistas.administrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import controladores.ExportadorCSVControlador;
import controladores.ExportadorExcelControlador;
import controladores.ExportadorPDFControlador;
import io.jsondb.JsonDBTemplate;
import modelos.Boleto;

public class ExportarDatosBoleto extends JFrame {

    private JButton btnCSV;
    private JButton btnPDF;
    private JButton btnExcel;
    private JsonDBTemplate jsonDB;

    public ExportarDatosBoleto() {
        // Initialize JsonDBTemplate with your values
        String dbFilesLocation = "D:/proyectos/Aerolinea/ProyectoAerolineas/basededatosJSON";
        String baseScanPackage = "modelos";

        try {
            File dbDir = new File(dbFilesLocation);
            if (!dbDir.exists()) {
                dbDir.mkdirs();
            }
            this.jsonDB = new JsonDBTemplate(dbFilesLocation, baseScanPackage);
            System.out.println("Conexión a JSONDB establecida correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al conectar con la base de datos: " + e.getMessage(),
                    "Error de conexión", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        setTitle("Exportar Datos de Boletos");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        btnCSV = new JButton("CSV");
        btnPDF = new JButton("PDF");
        btnExcel = new JButton("Excel");

        // Add action listeners
        btnCSV.addActionListener(new ExportarListener("CSV"));
        btnPDF.addActionListener(new ExportarListener("PDF"));
        btnExcel.addActionListener(new ExportarListener("Excel"));

        add(btnCSV);
        add(btnPDF);
        add(btnExcel);
    }

    private class ExportarListener implements ActionListener {
        private String formato;

        public ExportarListener(String formato) {
            this.formato = formato;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (jsonDB == null) {
                JOptionPane.showMessageDialog(ExportarDatosBoleto.this,
                        "No se pudo establecer conexión con la base de datos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como " + formato);

            // Set default file name and extension
            String defaultFileName = "boletos";
            switch (formato) {
                case "CSV":
                    fileChooser.setSelectedFile(new File(defaultFileName + ".csv"));
                    break;
                case "PDF":
                    fileChooser.setSelectedFile(new File(defaultFileName + ".pdf"));
                    break;
                case "Excel":
                    fileChooser.setSelectedFile(new File(defaultFileName + ".xlsx"));
                    break;
            }

            int userSelection = fileChooser.showSaveDialog(ExportarDatosBoleto.this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                try {
                    switch (formato) {
                        case "CSV":
                            ExportadorCSVControlador csvControlador = new ExportadorCSVControlador(jsonDB);
                            csvControlador.exportarACSV(Boleto.class, filePath, true);
                            break;
                        case "PDF":
                            ExportadorPDFControlador pdfControlador = new ExportadorPDFControlador(jsonDB);
                            pdfControlador.exportarAPDF(Boleto.class, filePath);
                            break;
                        case "Excel":
                            ExportadorExcelControlador excelControlador = new ExportadorExcelControlador(jsonDB);
                            excelControlador.exportarAXLSX(Boleto.class, filePath);
                            break;
                    }
                    JOptionPane.showMessageDialog(ExportarDatosBoleto.this,
                            "Datos exportados exitosamente a " + filePath,
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ExportarDatosBoleto.this,
                            "Error al exportar datos: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExportarDatosBoleto ventana = new ExportarDatosBoleto();
            ventana.setVisible(true);
        });
    }

    // Getters para los botones si los necesitas después
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