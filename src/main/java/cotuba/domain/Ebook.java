package cotuba.domain;

import java.nio.file.Path;
import java.util.List;

public class Ebook {

	private String formato;
	private Path arquivoDeSaida;
	private List<Capitulo> capitulos;

	public Ebook(String formato, Path arquivoDeSaida, List<Capitulo> capitulos) {
		super();
		this.formato = formato;
		this.arquivoDeSaida = arquivoDeSaida;
		this.capitulos = capitulos;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public Path getArquivoDeSaida() {
		return arquivoDeSaida;
	}

	public void setArquivoDeSaida(Path arquivoDeSaida) {
		this.arquivoDeSaida = arquivoDeSaida;
	}

	public List<Capitulo> getCapitulos() {
		return capitulos;
	}

	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
	}
}
