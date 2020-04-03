package cotuba.application;

import java.nio.file.Path;

import cotuba.domain.FormatoEbook;

public interface ParametrosCotuba {
	Path getDiretoriosMd();
	FormatoEbook getFormato();
	Path getArquivosDeSaida();
}
