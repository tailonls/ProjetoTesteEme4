package br.com.automacao.core;

public class Propriedades {
	public static boolean FECHAR_BROWSER = true;

	public static Browsers BROWSER = Browsers.CHROME;
	public static boolean CHROME_HEADLESS = false;
	public static boolean GERAR_EVIDENCIA_PDF = false;
	public static TipoExecucao TIPO_EXECUCAO = TipoExecucao.LOCAL;

	public enum Browsers {
		CHROME, FIREFOX, IEXPLORER
	}
	
	public enum TipoExecucao {
		GRID, LOCAL
	}
}
