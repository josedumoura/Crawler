import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebCrawler {

	private static void visitaSite(String ativo, String tipo, String tipoArq, String tipoUrl) {
		String pagina;
		String url;

		url = "https://" + tipoUrl + ".tradingview.com/symbols/" + tipo + ativo;

		try {

			System.out.println("Visitando: " + url);

//			conecta no site e pega o codigo html
			Document codigo = Jsoup.connect(url).userAgent("Chrome").timeout(10 * 60 * 1000).get();
			System.out.println("Site visitado!");

//			todo o html da pagina
			pagina = codigo.html();

//			faz o "parse"
			parse(ativo, pagina, tipoArq);

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

	private static void parse(String ativo, String pagina, String tipoArq) {
		int POS_INI = pagina.indexOf("<div class=\"tv-widget-description__text\">");
		int POS_FIN = pagina.indexOf("</div>", pagina.indexOf("<div class=\"tv-widget-description__text\">"));

		WriteLog("Iniciando ativo: " + ativo);

		String resultado = pagina.substring(POS_INI + 58, POS_FIN);

		if (pagina.contains("<div class=\"tv-widget-description__text\">")) {
			createFile(resultado, ativo, tipoArq);
		} else {
			System.out.println("Não existe, arquivo não criado.");
		}
		WriteLog("Ativo finalizado: " + ativo);
	}

	public static void createFile(String resultado, String ativo, String tipoArq) {
		FileWriter arquivo;

		try {
//			tratamento para ativos com apenas 3 letras EX: IBM
			if (ativo.length() < 3) {
				arquivo = new FileWriter(new File("C:\\Ativos\\" + tipoArq + "\\" + ativo.substring(0, 2) + ".txt"));
			} else if (ativo.length() < 4) {
				arquivo = new FileWriter(new File("C:\\Ativos\\" + tipoArq + "\\" + ativo.substring(0, 3) + ".txt"));
			} else {
				arquivo = new FileWriter(new File("C:\\Ativos\\" + tipoArq + "\\" + ativo.substring(0, 4) + ".txt"));
			}
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
		List<String> ListaAtivos1 = new ArrayList<String>();
		List<String> ListaAtivos2 = new ArrayList<String>();

		long tempoInicio = System.currentTimeMillis();

//		Ativos Bovespa ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		try {
			BufferedReader listaTxt = new BufferedReader(new FileReader("C:\\Ativos\\ListaAtivos\\ListaAtivosBovespa.txt"));

			while (listaTxt.ready()) {
				ListaAtivos.add(listaTxt.readLine());
			}

			listaTxt.close();

		} catch (Exception err) {
			System.err.println("Exception 2.0");
		}

		for (int i = 0; i < ListaAtivos.size(); i++) {
			visitaSite(ListaAtivos.get(i), "BMFBOVESPA-", "AtivosBovespa", "br");
		}

//		Ativos Nasdaq ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		try {
			BufferedReader listaTxt1 = new BufferedReader(new FileReader("C:\\Ativos\\ListaAtivos\\ListaAtivosNasdaq.txt"));

			while (listaTxt1.ready()) {
				ListaAtivos1.add(listaTxt1.readLine());
			}

			listaTxt1.close();

		} catch (Exception err) {
			System.err.println("Exception 3.0");
		}
//		AtivosNasdaqBR && AtivosNasdaqUS
		for (int i = 0; i < ListaAtivos1.size(); i++) {
			visitaSite(ListaAtivos1.get(i), "NASDAQ-", "AtivosNasdaqBR", "br");
			visitaSite(ListaAtivos1.get(i), "NASDAQ-", "AtivosNasdaqUS", "in");
		}

//		Ativos Nyse ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		try {
			BufferedReader listaTxt2 = new BufferedReader(new FileReader("C:\\Ativos\\ListaAtivos\\ListaAtivosNyse.txt"));

			while (listaTxt2.ready()) {
				ListaAtivos2.add(listaTxt2.readLine());
			}

			listaTxt2.close();

		} catch (Exception err) {
			System.err.println("Exception 4.0");
		}
//		AtivosNyseBR && AtivosNyseUS
		for (int i = 0; i < ListaAtivos2.size(); i++) {
			visitaSite(ListaAtivos2.get(i), "NYSE-", "AtivosNyseBR", "br");
			visitaSite(ListaAtivos2.get(i), "NYSE-", "AtivosNyseUS", "in");
		}

		long tempoFinal = System.currentTimeMillis();

		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
		System.out.println("Tempo de execução: " + sdf.format(new java.util.Date(tempoFinal - tempoInicio)));
	}
}
