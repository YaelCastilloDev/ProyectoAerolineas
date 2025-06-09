package modelos.dao.implementaciones;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import modelos.dao.contratos.ExportadorDAO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
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

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth())); // Horizontal
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        PDRectangle pageSize = page.getMediaBox();

        float margin = MARGIN;
        float yStart = pageSize.getHeight() - margin;
        float tableWidth = pageSize.getWidth() - 2 * margin;
        int numColumns = datos.get(0).length;
        float columnWidth = tableWidth / numColumns;
        float fontSize = 10f; // Aumenté un poco el tamaño de fuente
        float leading = 1.5f * fontSize; // Más espacio entre líneas
        float rowSpacing = 8f; // Más espacio entre filas
        float cellPadding = 5f; // Padding interno de las celdas

        PDFont font = PDType1Font.HELVETICA;
        PDFont boldFont = PDType1Font.HELVETICA_BOLD;

        float yPosition = yStart;

        // Dibujar encabezados de columna
        String[] headers = datos.get(0);
        float headerHeight = leading + 2 * cellPadding;

        // Dibujar fila de encabezado
        drawRow(contentStream, margin, yPosition, columnWidth, headerHeight, headers, boldFont, fontSize, leading, cellPadding, true);
        yPosition -= headerHeight;

        // Dibujar filas de datos
        for (int rowIndex = 1; rowIndex < datos.size(); rowIndex++) {
            String[] row = datos.get(rowIndex);

            // Calcular altura de fila
            int maxLines = 1;
            for (String cell : row) {
                List<String> lines = splitTextIntoLines(cell != null ? cell : "", font, fontSize, columnWidth - 2 * cellPadding);
                if (lines.size() > maxLines) {
                    maxLines = lines.size();
                }
            }

            float rowHeight = leading * maxLines + 2 * cellPadding;

            // Nueva página si es necesario
            if (yPosition - rowHeight < margin) {
                contentStream.close();
                page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page);
                yPosition = yStart;
                // Redibujar encabezados en nueva página
                drawRow(contentStream, margin, yPosition, columnWidth, headerHeight, headers, boldFont, fontSize, leading, cellPadding, true);
                yPosition -= headerHeight;
            }

            drawRow(contentStream, margin, yPosition, columnWidth, rowHeight, row, font, fontSize, leading, cellPadding, false);
            yPosition -= rowHeight;
        }

        contentStream.close();
        document.save(rutaArchivo);
        document.close();
    }

    private void drawRow(PDPageContentStream contentStream, float xStart, float yStart, 
                        float columnWidth, float rowHeight, String[] row, 
                        PDFont font, float fontSize, float leading, 
                        float cellPadding, boolean isHeader) throws IOException {

        float xPosition = xStart;

        // Dibujar celdas y contenido
        for (int col = 0; col < row.length; col++) {
            String cellText = row[col] != null ? row[col] : "";

            // Dibujar borde de celda
            contentStream.setLineWidth(0.5f);
            contentStream.addRect(xPosition, yStart - rowHeight, columnWidth, rowHeight);
            contentStream.stroke();

            // Dividir texto en líneas
            List<String> lines = splitTextIntoLines(cellText, font, fontSize, columnWidth - 2 * cellPadding);

            // Escribir texto
            contentStream.beginText();
            contentStream.setFont(font, fontSize);

            // Calcular posición vertical inicial (centrado verticalmente)
            float textY = yStart - cellPadding - fontSize;
            if (lines.size() > 1) {
                textY = yStart - (rowHeight - (lines.size() * leading)) / 2 - fontSize;
            }

            contentStream.newLineAtOffset(xPosition + cellPadding, textY);

            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -leading);
            }

            contentStream.endText();

            xPosition += columnWidth;
        }
    }


    
    private List<String> splitTextIntoLines(String text, PDFont font, float fontSize, float maxWidth) throws IOException {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            lines.add("");
            return lines;
        }

        StringBuilder line = new StringBuilder();
        String[] words = text.split("\\s+"); // Mejor manejo de espacios múltiples

        for (String word : words) {
            String testLine = line.length() == 0 ? word : line.toString() + " " + word;
            float width = font.getStringWidth(testLine) / 1000 * fontSize;

            if (width <= maxWidth) {
                line.append(line.length() == 0 ? word : " " + word);
            } else {
                if (line.length() > 0) {
                    lines.add(line.toString());
                    line = new StringBuilder(word);
                } else {
                    // Palabra más ancha que la columna - dividir la palabra
                    lines.addAll(splitLongWord(word, font, fontSize, maxWidth));
                    line = new StringBuilder();
                }
            }
        }

        if (line.length() > 0) {
            lines.add(line.toString());
        }

        return lines.isEmpty() ? Arrays.asList("") : lines;
    }

    private List<String> splitLongWord(String word, PDFont font, float fontSize, float maxWidth) throws IOException {
        List<String> parts = new ArrayList<>();
        StringBuilder part = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            String testPart = part.toString() + c;
            float width = font.getStringWidth(testPart) / 1000 * fontSize;

            if (width <= maxWidth) {
                part.append(c);
            } else {
                if (part.length() > 0) {
                    parts.add(part.toString());
                    part = new StringBuilder(String.valueOf(c));
                } else {
                    // Carácter individual es más ancho que la columna - lo agregamos de todos modos
                    part.append(c);
                }
            }
        }

        if (part.length() > 0) {
            parts.add(part.toString());
        }

        return parts;
    }

}