package cotuba.application;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.md.RenderizadorMarkdownParaHtmlImpl;

public interface RenderizadorMarkdownParaHtml {

	List<Capitulo> render(Path diretorioMd);
	
	
	//DEFAULT METHOD
	static RenderizadorMarkdownParaHtml build() {
		return new RenderizadorMarkdownParaHtmlImpl();
	}
}
