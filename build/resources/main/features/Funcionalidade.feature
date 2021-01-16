#language: pt
Funcionalidade: Cotacoes

  Contexto:
    Dado que acesso o site da eme4
    Entao valido que a empresao padrao esta correta
    E realizo login com os dados de usuario

  @teste
  Cenario: Realizar uma criacao de uma cotacao de venda
    Dado que acesso o menu "Vendas"
    E que acesso o submenu "Cotação de Vendas"
    Quando clico na opcao Novo
    Entao acesso a aba "Cabeçalho"
    Quando preencho o cliente
    Entao deve carregar os dados do cliente

#####################

    Quando acesso a aba "Produtos"
    E preencho os dados do produto conforme abaixo
      | COD_PRODUTO | UNIDADES | DESCONTO |
      | 200.1       | 10       | 15%      |
    E clico em Incluir
    #Validar alteração de valor com desconto
    Entao valido que apareceu o alerta "Iem incluído com sucesso."

#####################

    E valido que a linha com o produto foi incluida
    E preencho os dados do produto conforme abaixo
      | DESCRICAO_PRODUTO | UNIDADES | DESCONTO |
      | Campo             | 3        | 0%       |
    Quando clico em "Incluir"

#####################

    Entao valido que apareceu o alerta "Iem incluído com sucesso."
    E valido que a linha com o produto foi incluida

#####################

    Quando clico em excluir o ultimo produto
    Entao valido que o ultimo item foi excluido
    E valido que apareceu o alerta "Iem excluído com sucesso"

#####################

    Quando acesso a aba "Cond. Pagto"
    E preencho os dados de condicoes de pagamento conforme os dados abaixo
      | COND_PAGAMENTO | TIPO_COBRANCA       |
      | 30/60 dias     | Simples c/ Registro |
    Quando clico em "Gerar Parcelamento"
    Entao valido que apareceu o alerta "Parcelas geradas com sucesso"
    E valido se "2" linhas com parcelas foram geradas com sucesso

#####################

    Quando clico em "Confirmar"
    Entao valido que apareceu o alerta "Cotaçaõ Nº X gravada com sucesso!"

#####################

    Quando acesso a aba "Cotações"
    E pesquiso pelo numero da cotacao gerada
    Entao valido que uma linha com os dados do pedido aparece