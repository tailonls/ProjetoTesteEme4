package br.com.automacao.core.relarorios;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import br.com.automacao.core.Propriedades;

import java.io.File;
import java.io.FileOutputStream;

public class GeradorReportPDF {

    private static Document documento = null;

    private static final String PATH = "target/evidencias_PDF";
    private static final String PATH_IMG = "target/report_HTML/";
    private static int contador = 0;
    private static int contadorNovaPagina = 0;
    private static Font font = new Font();

    public static void gerarDocumentoPDF(String nomeCenario) {

        if (contador == 0) {
            File documentos = new File(PATH);
            if (documentos.exists() && documentos.isDirectory()) {
                File[] files = documentos.listFiles();
                for (File fileToDelete : files) // Apagando pasta com PDFs de testes anteriores
                    fileToDelete.delete();
                documentos.delete();
            }
        }
        contador++;

        if (Propriedades.GERAR_EVIDENCIA_PDF) {
            new File(PATH).mkdir();
            documento = new Document();

            try {
                PdfWriter.getInstance(documento, new FileOutputStream(PATH + "/" + nomeCenario + ".pdf"));
                documento.open();
                documento.addTitle("Evidencia Teste Automatizado");

            } catch (Exception e) {
                System.out.println("Erro ao criar PDF para o cenario atual: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void addTextoDocumentoPDF(String texto) {
        if (documento != null) {
            try {
                if (documento.isOpen())

                    if (contadorNovaPagina > 1) {
                        documento.newPage();
                        contadorNovaPagina = 0;
                    }
                documento.add(new Paragraph(texto.replace("<b>", "").replace("</b>", "")));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addTextoDocumentoPDF(String texto, String destaque) {
        if (documento != null) {
            try {
                if (documento.isOpen()) {

                    if (destaque.equalsIgnoreCase("PASS"))
                        font.setColor(BaseColor.GREEN);
                    else
                        font.setColor(BaseColor.RED);

                    documento.add(new Paragraph(new Phrase(texto.replace("<b>", "").replace("</b>", ""), font)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addImagemDocumentoPDF(String path_imagem) {
        if (documento != null) {
            try {
                if (documento.isOpen()) {
                    Image image = Image.getInstance(PATH_IMG + path_imagem);
                    image.scaleAbsolute(500, 250);
                    documento.add(image);
                    contadorNovaPagina++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void encerraDocumentoPDF() {
        if (documento != null)
            documento.close();
    }
}