package br.com.automacao.core;

import br.com.automacao.core.relarorios.GeradorReportHTML;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static br.com.automacao.core.DriverFactory.killDriver;

public class BaseTest extends GeradorReportHTML {

    @BeforeClass
    public static void iniciarTestes() {
        inicializarReportHTML();
    }

    @Before
    public void iniciarCenario(Scenario cenario) {
        addCenarioReportHTML(cenario);
    }

    @After
    public void finalizarCenario(Scenario cenario) {
        if (cenario.isFailed()) {
            logFail("O cenario falhou!");
        }

        if (Propriedades.FECHAR_BROWSER) {
            killDriver();
        }
        encerraDocumentoPDF();
    }

    @AfterClass
    public static void finalizarTestes() {
        atualizaReportHTML();
        killDriver();
    }
}