package br.com.automacao.steps;

import br.com.automacao.pages.FuncionalidadePage;
import gherkin.ast.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;


public class FuncionalidadeStep {

    private FuncionalidadePage funcionalidadePage = new FuncionalidadePage();

    @E("realizo login com os dados de usuario")
    public void realizoLoginComOsDadosDeUsuario() {

    }

    @Dado("que acesso o site da eme4")
    public void queAcessoOSiteDaEme() {
        funcionalidadePage.acessarPaginaInicial();
    }

    @Entao("valido que a empresao padrao esta correta")
    public void validoQueAEmpresaoPadraoEstaCorreta() {
    }

    @Dado("que acesso o menu {string}")
    public void queAcessoOMenu(String Menu) {
    }

    @E("que acesso o submenu {string}")
    public void queAcessoOSubmenu(String descSubmenu) {
    }

    @Quando("clico na opcao Novo")
    public void clicoNaOpcaoNovo() {
    }

    @Entao("acesso a aba {string}")
    public void acessoAAba(String arg0) {
    }

    @Quando("preencho o cliente")
    public void preenchoOCliente() {
    }

    @Entao("deve carregar os dados do cliente")
    public void deveCarregarOsDadosDoCliente() {
    }

    @E("preencho os dados do produto conforme abaixo")
    public void preenchoOsDadosDoProdutoConformeAbaixo(DataTable table) {

    }

    @E("clico em Incluir")
    public void clicoEmIncluir() {
    }

    @Entao("valido que apareceu o alerta {string}")
    public void validoQueApareceuOAlerta(String arg0) {
    }

    @E("valido que a linha com o produto foi incluida")
    public void validoQueALinhaComOProdutoFoiIncluida() {
    }

    @Quando("clico em {string}")
    public void clicoEm(String arg0) {
    }

    @Quando("clico em excluir o ultimo produto")
    public void clicoEmExcluirOUltimoProduto() {
    }

    @Entao("valido que o ultimo item foi excluido")
    public void validoQueOUltimoItemFoiExcluido() {
    }

    @E("preencho os dados de condicoes de pagamento conforme os dados abaixo")
    public void preenchoOsDadosDeCondicoesDePagamentoConformeOsDadosAbaixo() {
    }

    @E("valido se {string} linhas com parcelas foram geradas com sucesso")
    public void validoSeLinhasComParcelasForamGeradasComSucesso(String arg0) {
    }

    @Quando("pesquiso pelo numero da cotacao gerada")
    public void pesquisoPeloNumeroDaCotacaoGerada() {
    }

    @Entao("valido que uma linha com os dados do pedido aparece")
    public void validoQueUmaLinhaComOsDadosDoPedidoAparece() {
    }
}
