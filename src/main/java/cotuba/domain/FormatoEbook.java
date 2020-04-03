package cotuba.domain;

import cotuba.application.GeradorEbook;
import cotuba.books.GeradorEPUB;
import cotuba.books.GeradorPDF;

public enum FormatoEbook {
	
	PDF(new GeradorPDF()),
	EPUB(new GeradorEPUB());

	private GeradorEbook geradorEbook;
	
	FormatoEbook(GeradorEbook geradorEbook) {
		// TODO Auto-generated constructor stub
		this.geradorEbook = geradorEbook;
	}

	public GeradorEbook getGeradorEbook() {
		return geradorEbook;
	}
}

