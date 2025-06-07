package modelos.dao.implementaciones;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import modelos.dao.contratos.ExportadorDAO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ExportadorPDF implements ExportadorDAO {

    private static final float MARGIN = 50;
    private static final float ROW_HEIGHT = 20;
    private static final float FONT_SIZE = 12;

    public ExportadorPDF() {}

    @Override
    public <T> void exportarColeccion(Collection<T> coleccion, String rutaArchivo) throws IOException {
        ExportadorCSV exportadorCSV = new ExportadorCSV();
        exportarDatos(exportadorCSV.convertirACadenas(coleccion), rutaArchivo);
    }

    @Override
    public <T> void exportarCamposSeleccionados(Collection<T> coleccion, String rutaArchivo, String... nombresCampos) throws IOException {
        ExportadorCSV exportadorCSV = new ExportadorCSV();
        exportarDatos(exportadorCSV.convertirCamposSeleccionados(coleccion, nombresCampos), rutaArchivo);
    }

    @Override
    public void exportarDatos(List<String[]> datos, String rutaArchivo) throws IOException {
        if (datos == null || datos.isEmpty()) {
            throw new IllegalArgumentException("Los datos no pueden estar vacíos.");
        }

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            float yPosition = PDRectangle.A4.getHeight() - MARGIN;
            float tableWidth = PDRectangle.A4.getWidth() - 2 * MARGIN;
            int columns = datos.get(0).length;
            float columnWidth = tableWidth / columns;

            // Configurar fuente para los encabezados
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, FONT_SIZE);

            for (String[] row : datos) {
                if (yPosition < MARGIN) {
                    // Cerrar el contentStream actual antes de crear uno nuevo
                    contentStream.close();

                    // Crear nueva página
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    yPosition = PDRectangle.A4.getHeight() - MARGIN;
                }

                float xPosition = MARGIN;
                for (String cell : row) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(xPosition, yPosition);
                    contentStream.showText(cell != null ? cell : "");
                    contentStream.endText();
                    xPosition += columnWidth;
                }
                yPosition -= ROW_HEIGHT;
            }

            // Cerrar el último contentStream
            contentStream.close();

            document.save(rutaArchivo);
        }
    }
}