package cotuba.application;

import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;

public interface GeradorEbook {

	void gera(Ebook ebook);

	static GeradorEbook build(FormatoEbook format) {
		return format.getGeradorEbook();
	}
}
