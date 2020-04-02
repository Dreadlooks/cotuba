package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

public class Cotuba {

	public void executa(ParametrosCotuba parametros) {
		RenderizadorMarkdownParaHtml renderizador = RenderizadorMarkdownParaHtml.build();

		String formato = parametros.getFormato();
		Path diretorioDosMD = parametros.getDiretoriosMd();
		Path arquivoDeSaida = parametros.getArquivosDeSaida();
		
		List<Capitulo> capitulos = renderizador.render(diretorioDosMD);
		Ebook ebook = new Ebook(formato, arquivoDeSaida, capitulos);

		if ("pdf".equals(formato)) {
			GeradorPdf geraPdf = GeradorPdf.build();
			geraPdf.gera(ebook);

		} else if ("epub".equals(formato)) {
			GeradorEPUB geradorEPUB = GeradorEPUB.build();
			geradorEPUB.gera(ebook);

		} else {
			throw new RuntimeException("Formato do ebook inválido: " + formato);
		}
	}

}
