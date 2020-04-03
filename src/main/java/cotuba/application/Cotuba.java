package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;

public class Cotuba {

	public void executa(ParametrosCotuba parametros) {
		RenderizadorMarkdownParaHtml renderizador = RenderizadorMarkdownParaHtml.build();

		FormatoEbook formato = parametros.getFormato();
		Path diretorioDosMD = parametros.getDiretoriosMd();
		Path arquivoDeSaida = parametros.getArquivosDeSaida();

		List<Capitulo> capitulos = renderizador.render(diretorioDosMD);
		Ebook ebook = new Ebook(formato, arquivoDeSaida, capitulos);

		GeradorEbook gerador = GeradorEbook.build(formato);
		gerador.gera(ebook);
	}
}
