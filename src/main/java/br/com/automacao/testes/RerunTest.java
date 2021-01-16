package br.com.automacao.testes;

import br.com.automacao.core.BaseTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
				features = "@target/rerun.txt", 
				glue = {"br/com/automacao/steps", "br/com/automacao/core"}, tags = "@teste",
				plugin = { "pretty","rerun:target/rerun.txt" }, 
				snippets = SnippetType.CAMELCASE,
				strict = false,
				dryRun = false
				 
)
public class RerunTest extends BaseTest {

	// Executa os testes falhados que foram para o arquivo 'rerun.txt'
}