package cotuba.md;

import java.nio.file.Path;
import java.util.List;

import cotuba.domain.Capitulo;

public interface RenderizadorMarkdownParaHtml {

	List<Capitulo> render(Path diretorioMd);
}
