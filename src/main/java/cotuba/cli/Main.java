package cotuba.cli;

import java.nio.file.Path;

import cotuba.application.Cotuba;

public class Main {

	public static void main(String[] args) {
		LeitorDeOpcoes leitor = new LeitorDeOpcoes(args);

		Path arquivoDeSaida = leitor.getArquivosDeSaida();
		boolean modoVerboso = leitor.isModoVerboso();

		try {

			Cotuba cotuba = new Cotuba();
			cotuba.executa(leitor);

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
