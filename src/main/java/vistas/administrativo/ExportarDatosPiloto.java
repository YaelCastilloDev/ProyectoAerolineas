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
import modelos.Piloto;

public class ExportarDatosPiloto extends JFrame {

    private JButton btnCSV;
    private JButton btnPDF;
    private JButton btnExcel;
    private JsonDBTemplate jsonDB;

    public ExportarDatosPiloto() {
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

        setTitle("Exportar Datos de Pilotos");
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
                JOptionPane.showMessageDialog(ExportarDatosPiloto.this,
                        "No se pudo establecer conexión con la base de datos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como " + formato);

            // Set default file name and extension
            String defaultFileName = "pilotos";
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

            int userSelection = fileChooser.showSaveDialog(ExportarDatosPiloto.this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();

                try {
                    switch (formato) {
                        case "CSV":
                            ExportadorCSVControlador csvControlador = new ExportadorCSVControlador(jsonDB);
                            csvControlador.exportarACSV(Piloto.class, filePath, true);
                            break;
                        case "PDF":
                            ExportadorPDFControlador pdfControlador = new ExportadorPDFControlador(jsonDB);
                            pdfControlador.exportarAPDF(Piloto.class, filePath);
                            break;
                        case "Excel":
                            ExportadorExcelControlador excelControlador = new ExportadorExcelControlador(jsonDB);
                            excelControlador.exportarAXLSX(Piloto.class, filePath);
                            break;
                    }
                    JOptionPane.showMessageDialog(ExportarDatosPiloto.this,
                            "Datos de pilotos exportados exitosamente a " + filePath,
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ExportarDatosPiloto.this,
                            "Error al exportar datos: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        }
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