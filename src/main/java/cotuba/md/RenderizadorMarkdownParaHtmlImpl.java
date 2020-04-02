package cotuba.md;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import cotuba.application.RenderizadorMarkdownParaHtml;
import cotuba.domain.Capitulo;

public class RenderizadorMarkdownParaHtmlImpl implements RenderizadorMarkdownParaHtml {

	@Override
	public List<Capitulo> render(Path diretorioMd) {
		List<Capitulo> capitulos = new ArrayList<>();

		PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**/*.md");
		try (Stream<Path> arquivosMD = Files.list(diretorioMd)) {
			arquivosMD.filter(matcher::matches).sorted().forEach(arquivoMD -> {
				
				Capitulo capitulo = new Capitulo();
				Parser parser = Parser.builder().build();
				Node document = null;
				
				try {
					document = parser.parseReader(Files.newBufferedReader(arquivoMD));
					document.accept(new AbstractVisitor() {
						@Override
						public void visit(Heading heading) {
							if (heading.getLevel() == 1) {
								// capítulo
								String tituloDoCapitulo = ((Text) heading.getFirstChild()).getLiteral();
								capitulo.setTitulo(tituloDoCapitulo);	
								// TODO: usar título do capítulo
							} else if (heading.getLevel() == 2) {
								// seção
							} else if (heading.getLevel() == 3) {
								// título
							}
						}
					});
				} catch (Exception ex) {
					throw new RuntimeException("Erro ao fazer parse do arquivo " + arquivoMD, ex);
				}

				try {
					HtmlRenderer renderer = HtmlRenderer.builder().build();
					String html = renderer.render(document);
					capitulo.setConteudoHtml(html);	//	inserido
					capitulos.add(capitulo);	

				} catch (Exception ex) {
					throw new RuntimeException("Erro ao renderizar para HTML o arquivo " + arquivoMD, ex);
				}

			});
		} catch (IOException ex) {
			throw new RuntimeException("Erro tentando encontrar arquivos .md em " + diretorioMd.toAbsolutePath(), ex);
		}

		return capitulos;
	}
}
