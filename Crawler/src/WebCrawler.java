import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebCrawler {

	private static void visitaSite(String ativo) {
		String pagina;
		String url;

		url = "https://br.tradingview.com/symbols/BMFBOVESPA-" + ativo;

		try {

			System.out.println("Visitando: " + url);

//			conecta no site e pega o codigo html
			Document codigo = Jsoup.connect(url).userAgent("Chrome").timeout(10 * 60 * 1000).get();
			System.out.println("Site visitado!");

//			todo o html da pagina
			pagina = codigo.html();

//			faz o "parse"
			parse(ativo, pagina);

		} catch (Exception err) {
			System.err.println("Exception err 1.0, A URL não existe.");
		}
	}

	private static void WriteLog(String log) {
		FileWriter arquivo;

		try {
			arquivo = new FileWriter("C:\\Ativos\\log.out", true);
			PrintWriter escreveLog = new PrintWriter(arquivo);
			escreveLog.println(log);
			arquivo.close();
		} catch (IOException e) {
			System.err.println("IO Exception err");
		} catch (Exception e) {
			System.err.println("Exception err");
		}

	}

	private static void parse(String ativo, String pagina) {
		int POS_INI = pagina.indexOf("<div class=\"tv-widget-description__text\">");
		int POS_FIN = pagina.indexOf("</div>", pagina.indexOf("<div class=\"tv-widget-description__text\">"));

		WriteLog("Iniciando ativo: " + ativo);

		String resultado = pagina.substring(POS_INI + 58, POS_FIN);

		if (pagina.contains("<div class=\"tv-widget-description__text\">")) {
			createFile(resultado, ativo);
		} else {
			System.out.println("Não existe, arquivo não criado.");
		}

		WriteLog("Ativo finalizado: " + ativo);
	}

	public static void createFile(String resultado, String ativo) {
		FileWriter arquivo;

		try {
			arquivo = new FileWriter(new File("C:\\Ativos\\" + ativo.substring(0,4) + ".txt"));
			arquivo.write(resultado);
			arquivo.close();

		} catch (IOException e) {
			System.err.println("IO Exception err");
		} catch (Exception e) {
			System.err.println("Exception err");
		}

	}

	public static void main(String args[]) {

		List<String> ListaAtivos = new ArrayList<String>();
		long tempoInicio = System.currentTimeMillis();

		try {
			BufferedReader listaTxt = new BufferedReader(new FileReader("C:\\Ativos\\ListaAtivos.txt"));

			while (listaTxt.ready()) {
				ListaAtivos.add(listaTxt.readLine());
			}

			listaTxt.close();

		} catch (Exception err) {
			System.err.println("Exception 2.0");
		}

		for (int i = 0; i < ListaAtivos.size(); i++) {
			visitaSite(ListaAtivos.get(i));
		}

		long tempoFinal = System.currentTimeMillis();

		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
		System.out.println("Tempo de execução: " + sdf.format(new java.util.Date(tempoFinal - tempoInicio)));
	}
}
