import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawler {
	private static void visitaSite(String url) {
		try {
			System.out.println("Visitando: " + url);

//			conecta no site e pega o codigo html
			Document codigo = Jsoup.connect(url).userAgent("Chrome").timeout(10 * 60 * 1000).get();
			codigo = codigo.normalise();

//			obtem o texto do site visitado
			String pagina = codigo.text();

//			coloca tudo no txt
			Path caminho = Paths.get("C:\\Teste\\TextoAtivo.txt");
			byte[] paginaEmByte = pagina.getBytes();

			try {

				Files.write(caminho, paginaEmByte);

			} catch (Exception erro) {
				System.err.print("Exception err");
			}

			System.out.println(" Site visitado ");

		} catch (Exception e) {
			visitaSite(url);
		}
	} // final do visitaSite

	public static void main(String args[]) {

		String linha;
		int numAtivos = 4;
		String ativo[] = new String[numAtivos];

		StringTokenizer lista = new StringTokenizer("PETR4,VALE3,ITUB4,USIM5");

		for (int j = 0; j < numAtivos; j++) {
			ativo[j] = lista.nextToken(",");
		}

		for (int i = 0; i < 4; i++) {
			visitaSite("https://br.tradingview.com/symbols/BMFBOVESPA-" + ativo[i] + "/");

			try {

				BufferedReader arquivo = new BufferedReader(new FileReader("C:\\Teste\\TextoAtivo.txt"));
//				printa no arquivo todos system.out
				PrintStream out = new PrintStream(new File("C:\\Teste\\" + ativo[i] + ".txt"));
//				volta o System.out ao normal(sem printar dentro do arquivo)
				PrintStream stdout = System.out;
//				out.close();

				while (arquivo.ready()) {
//						pega a linha
					while ((linha = arquivo.readLine()) != null) {
						System.setOut(out);
						System.out.println(ativo[i]);
						System.out.println(linha.substring(linha.indexOf("— Perfil") + "— Perfil".length(), linha.indexOf("Notícias Comunidade")));
						System.out.println("");
						System.setOut(stdout);
						System.out.println(linha.substring(linha.indexOf("— Perfil") + "— Perfil".length(), linha.indexOf("Notícias Comunidade")));
					}
				}
				arquivo.close();
			} catch (Exception erro) {
				System.err.print("Exception err 2.0");
			}
			System.out.println("");
		}
	}// main
}