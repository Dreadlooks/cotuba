package cotuba.application;

import cotuba.books.GeradorPDFComIText;
import cotuba.domain.Ebook;

public interface GeradorPdf {

	void gera(Ebook ebook);
	
	static GeradorPdf build() {
		return new GeradorPDFComIText();
	}
}
