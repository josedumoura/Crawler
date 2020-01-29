import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class InterfaceExe extends JFrame {

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
			arquivo = new FileWriter("C:\\Ativos\\log.OUT", true);
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
			if(ativo.length() < 2){
				arquivo = new FileWriter(new File("C:\\Ativos\\" + tipoArq + "\\" + ativo.substring(0, 1) + ".TXT"));
			}else if (ativo.length() < 3) {
				arquivo = new FileWriter(new File("C:\\Ativos\\" + tipoArq + "\\" + ativo.substring(0, 2) + ".TXT"));
			} else if (ativo.length() < 4) {
				arquivo = new FileWriter(new File("C:\\Ativos\\" + tipoArq + "\\" + ativo.substring(0, 3) + ".TXT"));
			} else {
				arquivo = new FileWriter(new File("C:\\Ativos\\" + tipoArq + "\\" + ativo.substring(0, 4) + ".TXT"));
			}
			arquivo.write(resultado);
			arquivo.close();
		} catch (IOException e) {
			System.err.println("IO Exception err");
		} catch (Exception e) {
			System.err.println("Exception err");
		}
	}

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceExe frame = new InterfaceExe();
					frame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceExe() {
		setTitle("Interface para Executar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 235, 211);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Bovespa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> ListaAtivos = new ArrayList<String>();

//				Ativos Bovespa ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				try {
					BufferedReader listaTxt = new BufferedReader(
							new FileReader("C:\\Ativos\\ListaAtivos\\ListaAtivosBovespa.TXT"));

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
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(66, 36, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Nasdaq");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> ListaAtivos1 = new ArrayList<String>();

//				Ativos Nasdaq ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				try {
					BufferedReader listaTxt1 = new BufferedReader(
							new FileReader("C:\\Ativos\\ListaAtivos\\ListaAtivosNasdaq.TXT"));

					while (listaTxt1.ready()) {
						ListaAtivos1.add(listaTxt1.readLine());
					}

					listaTxt1.close();

				} catch (Exception err) {
					System.err.println("Exception 3.0");
				}
//				AtivosNasdaqBR && AtivosNasdaqUS
				for (int i = 0; i < ListaAtivos1.size(); i++) {
					visitaSite(ListaAtivos1.get(i), "NASDAQ-", "AtivosNasdaqBR", "br");
					visitaSite(ListaAtivos1.get(i), "NASDAQ-", "AtivosNasdaqUS", "in");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(66, 70, 89, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Nyse");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> ListaAtivos2 = new ArrayList<String>();

//				Ativos Nyse ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				try {
					BufferedReader listaTxt2 = new BufferedReader(
							new FileReader("C:\\Ativos\\ListaAtivos\\ListaAtivosNyse.TXT"));

					while (listaTxt2.ready()) {
						ListaAtivos2.add(listaTxt2.readLine());
					}

					listaTxt2.close();

				} catch (Exception err) {
					System.err.println("Exception 4.0");
				}
//				AtivosNyseBR && AtivosNyseUS
				for (int i = 0; i < ListaAtivos2.size(); i++) {
					visitaSite(ListaAtivos2.get(i), "NYSE-", "AtivosNyseBR", "br");
					visitaSite(ListaAtivos2.get(i), "NYSE-", "AtivosNyseUS", "in");
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(66, 104, 89, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Executar Todos");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> ListaAtivos = new ArrayList<String>();
				List<String> ListaAtivos1 = new ArrayList<String>();
				List<String> ListaAtivos2 = new ArrayList<String>();

//				Ativos Bovespa ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				try {
					BufferedReader listaTxt = new BufferedReader(
							new FileReader("C:\\Ativos\\ListaAtivos\\ListaAtivosBovespa.TXT"));

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

//				Ativos Nasdaq ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				try {
					BufferedReader listaTxt1 = new BufferedReader(
							new FileReader("C:\\Ativos\\ListaAtivos\\ListaAtivosNasdaq.TXT"));

					while (listaTxt1.ready()) {
						ListaAtivos1.add(listaTxt1.readLine());
					}

					listaTxt1.close();

				} catch (Exception err) {
					System.err.println("Exception 3.0");
				}
//				AtivosNasdaqBR && AtivosNasdaqUS
				for (int i = 0; i < ListaAtivos1.size(); i++) {
					visitaSite(ListaAtivos1.get(i), "NASDAQ-", "AtivosNasdaqBR", "br");
					visitaSite(ListaAtivos1.get(i), "NASDAQ-", "AtivosNasdaqUS", "in");
				}

//				Ativos Nyse ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				try {
					BufferedReader listaTxt2 = new BufferedReader(
							new FileReader("C:\\Ativos\\ListaAtivos\\ListaAtivosNyse.TXT"));

					while (listaTxt2.ready()) {
						ListaAtivos2.add(listaTxt2.readLine());
					}

					listaTxt2.close();

				} catch (Exception err) {
					System.err.println("Exception 4.0");
				}
//				AtivosNyseBR && AtivosNyseUS
				for (int i = 0; i < ListaAtivos2.size(); i++) {
					visitaSite(ListaAtivos2.get(i), "NYSE-", "AtivosNyseBR", "br");
					visitaSite(ListaAtivos2.get(i), "NYSE-", "AtivosNyseUS", "in");
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(48, 138, 131, 23);
		contentPane.add(btnNewButton_3);

		JLabel lblNewLabel = new JLabel("Escolha a lista para Executar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(23, 11, 193, 14);
		contentPane.add(lblNewLabel);
	}

}
