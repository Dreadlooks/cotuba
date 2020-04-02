package cotuba.cli;

import java.nio.file.Path;

import cotuba.Cotuba;

public class Main {

	public static void main(String[] args) {
		LeitorDeOpcoes leitor = new LeitorDeOpcoes(args);

		Path diretorioDosMD = leitor.getDiretorioDosMD();
		String formato = leitor.getFormato();
		Path arquivoDeSaida = leitor.getArquivoDeSaida();
		boolean modoVerboso = leitor.isModoVerboso();

		try {

			Cotuba cotuba = new Cotuba();
			cotuba.executa(formato, diretorioDosMD, arquivoDeSaida);

			System.out.println("Arquivo gerado com sucesso: " + arquivoDeSaida);

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			if (modoVerboso) {
				ex.printStackTrace();
			}
			System.exit(1);
		}
	}

}
