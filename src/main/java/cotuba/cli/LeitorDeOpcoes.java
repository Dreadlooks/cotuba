package cotuba.cli;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import cotuba.application.ParametrosCotuba;
import cotuba.domain.FormatoEbook;

public class LeitorDeOpcoes implements ParametrosCotuba {

	private Path diretorioMd;
	private FormatoEbook formato;
	private Path arquivoDeSaida;
	private boolean modoVerboso = false;

	public LeitorDeOpcoes(String[] args) {
		Options options = new Options();

		Option opcaoDeDiretorioDosMD = new Option("d", "dir", true,
				"Diretório que contem os arquivos md. Default: diretório atual.");
		options.addOption(opcaoDeDiretorioDosMD);

		Option opcaoDeFormatoDoEbook = new Option("f", "format", true,
				"Formato de saída do ebook. Pode ser: pdf ou epub. Default: pdf");
		options.addOption(opcaoDeFormatoDoEbook);

		Option opcaoDeArquivoDeSaida = new Option("o", "output", true,
				"Arquivo de saída do ebook. Default: book.{formato}.");
		options.addOption(opcaoDeArquivoDeSaida);

		Option opcaoModoVerboso = new Option("v", "verbose", false, "Habilita modo verboso.");
		options.addOption(opcaoModoVerboso);

		CommandLineParser cmdParser = new DefaultParser();
		HelpFormatter ajuda = new HelpFormatter();
		CommandLine cmd;

		try {
			cmd = cmdParser.parse(options, args);
		} catch (ParseException e) {
			ajuda.printHelp("cotuba", options);
			throw new RuntimeException("Opção invalida!", e);
		}

		String nomeDoDiretorioDosMD = cmd.getOptionValue("dir");

		if (nomeDoDiretorioDosMD != null) {
			diretorioMd = Paths.get(nomeDoDiretorioDosMD);
			if (!Files.isDirectory(diretorioMd)) {
				throw new RuntimeException(nomeDoDiretorioDosMD + " não é um diretório.");
			}
		} else {
			Path diretorioAtual = Paths.get("");
			diretorioMd = diretorioAtual;
		}

		String nomeDoFormatoDoEbook = cmd.getOptionValue("format");

		if (nomeDoFormatoDoEbook != null) {
			formato = 	FormatoEbook.valueOf(nomeDoFormatoDoEbook.toUpperCase());
		} else {
			formato = FormatoEbook.PDF;
		}

		String nomeDoArquivoDeSaidaDoEbook = cmd.getOptionValue("output");
		if (nomeDoArquivoDeSaidaDoEbook != null) {
			arquivoDeSaida = Paths.get(nomeDoArquivoDeSaidaDoEbook);
			if (Files.exists(arquivoDeSaida) && Files.isDirectory(arquivoDeSaida)) {
				throw new RuntimeException(nomeDoArquivoDeSaidaDoEbook + " é um diretório.");
			}
		} else {
			arquivoDeSaida = Paths.get("book." + formato.name().toLowerCase());
		}

		modoVerboso = cmd.hasOption("verbose");

	}

	@Override
	public Path getDiretoriosMd() {
		// TODO Auto-generated method stub
		return diretorioMd;
	}

	@Override
	public Path getArquivosDeSaida() {
		// TODO Auto-generated method stub
		return arquivoDeSaida;
	}
	
	public boolean isModoVerboso() {
		return modoVerboso;
	}

	@Override
	public FormatoEbook getFormato() {
		// TODO Auto-generated method stub
		return formato;
	}
}
