package cotuba;

import java.nio.file.Path;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		LeitorDeOpcoes leitor = new LeitorDeOpcoes(args);

		Path diretorioDosMD = leitor.getDiretorioDosMD();
		String formato = leitor.getFormato();
		Path arquivoDeSaida = leitor.getArquivoDeSaida();
		boolean modoVerboso = leitor.isModoVerboso();
		
		RenderizadorMarkdownParaHtml renderizador = new RenderizadorMarkdownParaHtml(); // inserido
		
		List<Capitulo> capitulos = renderizador.render(diretorioDosMD);
		Ebook ebook = new Ebook(formato, arquivoDeSaida, capitulos);
		
		try {

			if ("pdf".equals(formato)) {
				GeradorPdf geraPdf = new GeradorPdf();
				geraPdf.gera(ebook);

			} else if ("epub".equals(formato)) {
				GeradorEPUB geradorEPUB = new GeradorEPUB();
				geradorEPUB.gera(ebook);
				
			} else {
				throw new RuntimeException("Formato do ebook inválido: " + formato);
			}

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
