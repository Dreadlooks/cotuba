package cotuba.application;

import cotuba.books.GeradorEPUBComEpublib;
import cotuba.domain.Ebook;

public interface GeradorEPUB {

	void gera(Ebook ebook);
	
	static GeradorEPUB build() {
		return new GeradorEPUBComEpublib();
	}
	
}
