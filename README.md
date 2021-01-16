# Framework Selenium Gradle

Framework Selenium para automação de testes (Gradle + Java + Spring + JUnit + Cucumber)

Para executar os testes basta rodar as classes dentro do diretório \src\test\java\br.com.automacao.testes, essa classe irá executar os testes escritos em BDD nos arquivos de Features contidos no diretório src\test\resources\features.

Ao final do teste será gerado um relatório contendo os prints da imagem de cada passo executado do teste, abra ele utilizando o browser.

**Obs.:** Para retirar a dependencia do BaseTest do FuncionalidadePage é necessário extender o BaseTest no PageElementPage, mas para isso será preciso retirar os Hooks do BaseTest (@Before e @ After do cucumber.api.java).

## Requisitos para rodar testes em uma máquina

- Java JDK (De preferência versão mais atualizada).
- Variáveis de ambiente JAVA_HOME e PATH configuradas.
- Gradle (Para gerenciar dependências).
- Alguma IDE (Eclipse, IntelliJ, etc) que facilite nos testes.

## Estrutura do projeto:

Caixa-Test:

src/main/java:

- **core:** Classes base do projeto para configurações de driver e dos cenários.
- **pages:** Cada tela deve possuir a classe com métodos únicos.
- **servicos:** Classes com métodos que interagem com os serviços **Rest**.
- **repositorios:** Classes com métodos que interagem com a **Base de Dados**.
- **steps:**  Classes com métodos que implementam o que está no arquivo de feature.
- **tests:** Classes que iniciam os cenários no arquivo de feature.
- **utils:** Classes reutilizáveis como conversão de datas ou textos.

src/main/resources:

- **features:** Arquivos com os cenários escritos em BDD.
- **application.properties:** Arquivo com dados de login, nome de servidores e endereços das bases.

## Ordem dos arquivos durante um teste:
	1) [Classe Runner] Aciona...
	2) [Arquivos de feature] Implementados pelas...
	3) [Classes Step] Que utilizam...
	4) [Classes Pages] Que utilizam...
	5) [Classes ElementMap]

## Para rodar um teste:

1 - Após ter instalado os programas e configurado as variáveis de ambiente;
2 - Baixe o projeto e vá até a pasta */src/main/java/test/* e verifique as classes de Teste;
3 - Essas são as classes que iniciam os testes, para saber qual teste ela irá rodar verifique a propriedade **tags** 	  
> Ex.: tags = "@teste"

4 - Busque dentre os arquivos de feature na pasta */src/main/resources/features/* qual delas tem essa tag;
5 - Rode a classe de teste com o botão direito do mouse -> Run As -> JUnit Test;
6 - Após terminado o teste vá até a pasta *target/Reports/* e abra o arquivo relatorio_testes.html no navegador, esse relatório tem o passo a passo com imagens do que aconteceu no teste.

Para criar um novo teste (Necessário ter o mínimo de conhecimento em Selenium, Cucumber, Junit e Java):

- Crie um novo arquivo **.feature** com o nome da funcionalidade que deseja testar:

	Ex: 

		EditarCadastro.feature

- Escreva os br.com.automacao.steps do teste no padrão BDD utilizando a linguagem Gherkin:

	Ex: 
		
		Cenario: Testar pesquisa no google
			Dado que acesso o site google
			E pesquiso pela termo "Assistir filme online"
			E clico no botao "Pesquisar"
			Entao deve carregar a pagina com os resultados da pesqusia
		
- Execute esse arquivo clicando no botão direito -> run as... -> arquivo feature 
- Ao executar deve aparecer na tela do console as implementações desses br.com.automacao.steps

	Ex:  
	
		@Dado("^que acesso o site google$")
		public void que_acesso_o_site_google() throws Throwable {
	    	// Write code here that turns the phrase above into concrete actions
	    	throw new PendingException();
		}

- Crie uma classe de Step na pasta Step do projeto com o nome da sua funcionalidade, nessa classe serão colocados os métodos que implementam os stesp escritos no arquivo .feature:
	
	Ex: 
		
		PesquisaGoogleStep.java
	
- Cole as implementações dos br.com.automacao.steps que apareceram no console
- Apague as linhas  que começam com **"//Write code here..."** e **"throw new..."** elas não serão necessarias
- Apague também os **"throws Throwable"** que aparecem ao lado dos métodos, também não serão necessárias

- Crie uma classe de Page na pasta Page do projeto com o nome da sua funcionalidade, nessa classe serão colocados os métodos que são usados somente na funcionalidade  ser testada:

	Ex: 

		PesquisaGooglePage.java
	
- Crie uma classe de ElementMap na pasta Page do projeto com o nome da sua funcionalidade, nessa classe serão mapeados os elementos presentes na tela que deseja testar:

	Ex: 
	
		PesquisaGooglePageElementMap.java
	
- Extenda a classe BasePage à essa classe
- Extenda a classe ElementMap à classe Page
- Estancie a classe Page na classe Step

- Agora basta implementar cada método que fara o teste na classe page utilizando os elementos mapeados na classe **ElementMap** e utilizar esses métodos na classe **Step**.

**Base de dados:**

 - MySQL (XAMPP)
 - Endereço: http://localhost/phpmyadmin/
    
Criar tabela:

    CREATE TABLE tbCliente (
      idCliente BIGINT NOT NULL AUTO_INCREMENT,
      nome VARCHAR(60) NOT NULL,
      endereco VARCHAR(80) NULL,
      telefone VARCHAR(15) NULL,
      PRIMARY KEY(idCliente)
    );

Buscar registro:

    SELECT * FROM tbCliente;
    
Inserir registro:

    INSERT INTO tbCliente SET nome = 'Marcus Vinicius', endereco = 'Av. Presidente Vargas, 10 – sl: 3001';

Alterar registro:

    UPDATE tbCliente SET nome = 'Marcus Vinicius Leandro' WHERE idCliente = 1 LIMIT 1;

Deletar registro:

    DELETE FROM tbCliente WHERE idCliente = 1 LIMIT 1;
