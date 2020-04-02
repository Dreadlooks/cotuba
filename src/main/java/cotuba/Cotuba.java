package cotuba;

import java.nio.file.Path;
import java.util.List;

import cotuba.books.GeradorEPUB;
import cotuba.books.GeradorEPUBImpl;
import cotuba.books.GeradorPdf;
import cotuba.books.GeradorPdfImpl;
import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.md.RenderizadorMarkdownParaHtml;
import cotuba.md.RenderizadorMarkdownParaHtmlImpl;

public class Cotuba {

	public void executa(String formato, Path diretorioDosMd, Path arquivoDeSaida) {
		RenderizadorMarkdownParaHtml renderizador = new RenderizadorMarkdownParaHtmlImpl();

		List<Capitulo> capitulos = renderizador.render(diretorioDosMd);
		Ebook ebook = new Ebook(formato, arquivoDeSaida, capitulos);

		if ("pdf".equals(formato)) {
			GeradorPdf geraPdf = new GeradorPdfImpl();
			geraPdf.gera(ebook);

		} else if ("epub".equals(formato)) {
			GeradorEPUB geradorEPUB = new GeradorEPUBImpl();
			geradorEPUB.gera(ebook);

		} else {
			throw new RuntimeException("Formato do ebook inválido: " + formato);
		}
	}

}
