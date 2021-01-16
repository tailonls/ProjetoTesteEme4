package br.com.automacao.core.relarorios;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.core.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;

import static br.com.automacao.core.DriverFactory.getDriver;

public class GeradorReportHTML extends GeradorReportPDF {

    private static ExtentReports extensao = null;
    private static ExtentTest logger = null;
    private static ExtentHtmlReporter relatorio = null;

    private static String PATH_REPORT = "target/ReportHTML/";
    private static String PATH_IMAGENS = "Screenshot/";

    protected static void inicializarReportHTML() {

        // Apagando pasta com html de testes anteriores
        File reportes = new File(PATH_REPORT);

        if (reportes.exists() && reportes.isDirectory()) {

            File[] files = reportes.listFiles();
            for (File fileToDelete : files) {
                fileToDelete.delete();
            }
            reportes.delete();
        }

        // Apagando imagens de testes anteriores
        File screenshots = new File(PATH_REPORT + PATH_IMAGENS);

        if (screenshots.exists() && screenshots.isDirectory()) {

            File[] files = screenshots.listFiles();
            for (File fileToDelete : files) {
                fileToDelete.delete();
            }
            screenshots.delete();
        }

        if (relatorio == null && extensao == null) {
            new File(PATH_REPORT).mkdir();
            new File(PATH_REPORT + PATH_IMAGENS).mkdir();

            relatorio = new ExtentHtmlReporter(PATH_REPORT + "RelatorioTestes.html");
            extensao = new ExtentReports();
            extensao.attachReporter(relatorio);
        }
    }

    protected static void addCenarioReportHTML(Scenario cenario) {

        if (relatorio != null && extensao != null) {

            logger = extensao.createTest(cenario.getName());
            logger.assignAuthor("Tailon Saraiva");
            addCategoriaReport(cenario.getName());

            atualizaReportHTML();

            gerarDocumentoPDF(cenario.getName());
        }
    }

    protected static void atualizaReportHTML() {
        extensao.flush();
    }

    public static void logPass(String log) {
        if (relatorio != null && extensao != null) {
            logger.pass(log);
            atualizaReportHTML();

            addTextoDocumentoPDF("PASSOU: " + log);
        }
    }

    public static void logPrintPass(String log) {

        try {
            String temp = getScreenshot();
            logger.pass(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
            atualizaReportHTML();

            addTextoDocumentoPDF("PASSOU: " + log);
            addImagemDocumentoPDF(PATH_REPORT + temp);

        } catch (Exception e) {
            logFail("Capture Failed " + e.getMessage());
        }
    }

    public static void logFail(String log) {
        if (relatorio != null && extensao != null) {
            logger.fail(log);
            atualizaReportHTML();

            addTextoDocumentoPDF("FALHOU: " + log);
        }
    }

    public static void logPrintFail(String log) {
        try {
            String temp = getScreenshot();
            logger.fail(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
            atualizaReportHTML();

            addTextoDocumentoPDF("FALHOU: " + log);
            addImagemDocumentoPDF(PATH_REPORT + temp);

        } catch (Exception e) {
            logFail("Capture Failed " + e.getMessage());
        }
    }

    public static void logInfo(String log) {
        if (relatorio != null && extensao != null) {
            logger.info(log);
            atualizaReportHTML();

            addTextoDocumentoPDF(log);
        }
    }

    public static void logAviso(String log) {
        if (relatorio != null && extensao != null) {
            logger.warning(log);
            atualizaReportHTML();

            addTextoDocumentoPDF("AVISO:" + log);
        }
    }

    public static void logErro(String log) {
        if (relatorio != null && extensao != null) {
            logger.error(log);
            atualizaReportHTML();

            addTextoDocumentoPDF("ERRO: " + log);
        }
    }

    public static void logPrintPaginaInteira(String log) {
        try {
            String temp = getScreenshotAllPage();
            logger.pass(log, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
            atualizaReportHTML();

            addTextoDocumentoPDF(log);
            addImagemDocumentoPDF(PATH_REPORT + temp);

        } catch (Exception e) {
            logFail("Capture Failed " + e.getMessage());
        }
    }

    private static String getScreenshot() {
        String PATH_TEMPORARIO = "";
        try {
            PATH_TEMPORARIO = PATH_IMAGENS + System.currentTimeMillis() + ".png";
            File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
            File destination = new File(PATH_REPORT + PATH_TEMPORARIO);
            FileUtils.copyFile(src, destination);

        } catch (Exception e) {
            System.out.println("Screenshot falhou! " + e.getMessage());
        }
        return PATH_TEMPORARIO;
    }

    private static String getScreenshotAllPage() {
        String PATH_TEMPORARIO = "";

        try {
            PATH_TEMPORARIO = PATH_IMAGENS + System.currentTimeMillis() + ".png";
            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                    .takeScreenshot(getDriver());
            ImageIO.write(screenshot.getImage(), "PNG", new File(PATH_REPORT + PATH_TEMPORARIO));

        } catch (Exception e) {
            System.out.println("Screenshot 'all page' falhou! " + e.getMessage());
        }

        return PATH_TEMPORARIO;
    }

    private static void addCategoriaReport(String cenario) {

        if (relatorio != null && extensao != null) {
            if (cenario.toUpperCase().contains("API") || cenario.toUpperCase().contains("SERVICO")
                    || cenario.toUpperCase().contains("REST"))
                logger.assignCategory("API");

            else
                logger.assignCategory("TELA");

            if (cenario.toUpperCase().contains("SMOKE"))
                logger.assignCategory("SMOKE");

            else
                logger.assignCategory("FUNCIONAL");
        }
    }
}