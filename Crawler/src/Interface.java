import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Interface extends JFrame {

	private static void visitaSite(String url) {
		try {
			System.out.println("Visitando: " + url);

//			conecta no site e pega o codigo html
			Document codigo = Jsoup.connect(url).userAgent("Chrome").timeout(10 * 60 * 1000).get();
			codigo = codigo.normalise();

//			obtem o texto do site visitado
			String pagina = codigo.text();

//			coloca tudo no txt
			Path caminho = Paths.get("C:\\TesteInterface\\TextoAtivo.txt");
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
	}

	private JPanel contentPane;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface() {
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Interface");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("PETR4");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String linha;
				String mensagem = "Criado com Sucesso! \nDiretório: C:\\TesteInterface\\PETR4.txt";

				visitaSite("https://br.tradingview.com/symbols/BMFBOVESPA-PETR4");

				try {

					BufferedReader arquivo = new BufferedReader(new FileReader("C:\\TesteInterface\\TextoAtivo.txt"));
//						printa no arquivo todos system.out
					PrintStream out = new PrintStream(new File("C:\\TesteInterface\\PETR4.txt"));
//						volta o System.out ao normal(sem printar dentro do arquivo)
					PrintStream stdout = System.out;
//						out.close();

					while (arquivo.ready()) {
//								pega a linha
						while ((linha = arquivo.readLine()) != null) {
							System.setOut(out);
							System.out.println("PETR4");
							System.out.println(linha.substring(linha.indexOf("— Perfil") + "— Perfil".length(), linha.indexOf("Notícias Comunidade")));
							System.out.println("");
							System.setOut(stdout);
							System.out.println(linha.substring(linha.indexOf("— Perfil") + "— Perfil".length(), linha.indexOf("Notícias Comunidade")));
						}
						JOptionPane.showMessageDialog(null, mensagem);
					}
					arquivo.close();
				} catch (Exception erro) {
					System.err.print("Exception err 2.0");
				}
				System.out.println("");
			}
		});
		btnNewButton.setBounds(36, 70, 89, 23);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("VALE3");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String linha;
				String mensagem = "Criado com Sucesso! \nDiretório: C:\\TesteInterface\\VALE3.txt";

				visitaSite("https://br.tradingview.com/symbols/BMFBOVESPA-VALE3");

				try {

					BufferedReader arquivo = new BufferedReader(new FileReader("C:\\TesteInterface\\TextoAtivo.txt"));
//					printa no arquivo todos system.out
					PrintStream out = new PrintStream(new File("C:\\TesteInterface\\VALE3.txt"));
//					volta o System.out ao normal(sem printar dentro do arquivo)
					PrintStream stdout = System.out;
//					out.close();

					while (arquivo.ready()) {
//						pega a linha
						while ((linha = arquivo.readLine()) != null) {
							System.setOut(out);
							System.out.println("VALE3");
							System.out.println(linha.substring(linha.indexOf("— Perfil") + "— Perfil".length(),
									linha.indexOf("Notícias Comunidade")));
							System.out.println("");
							System.setOut(stdout);
							System.out.println(linha.substring(linha.indexOf("— Perfil") + "— Perfil".length(),
									linha.indexOf("Notícias Comunidade")));
						}
						JOptionPane.showMessageDialog(null, mensagem);
					}
					arquivo.close();
				} catch (Exception erro) {
					System.err.print("Exception err 2.0");
				}
				System.out.println("");
			}
		});
		btnNewButton_1.setBounds(172, 70, 89, 23);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("ITUB4");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String linha;
				String mensagem = "Criado com Sucesso! \nDiretório: C:\\TesteInterface\\ITUB4.txt";

				visitaSite("https://br.tradingview.com/symbols/BMFBOVESPA-ITUB4");

				try {

					BufferedReader arquivo = new BufferedReader(new FileReader("C:\\TesteInterface\\TextoAtivo.txt"));
//						printa no arquivo todos system.out
					PrintStream out = new PrintStream(new File("C:\\TesteInterface\\ITUB4.txt"));
//						volta o System.out ao normal(sem printar dentro do arquivo)
					PrintStream stdout = System.out;
//						out.close();

					while (arquivo.ready()) {
//								pega a linha
						while ((linha = arquivo.readLine()) != null) {
							System.setOut(out);
							System.out.println("ITUB4");
							System.out.println(linha.substring(linha.indexOf("— Perfil") + "— Perfil".length(),
									linha.indexOf("Notícias Comunidade")));
							System.out.println("");
							System.setOut(stdout);
							System.out.println(linha.substring(linha.indexOf("— Perfil") + "— Perfil".length(),
									linha.indexOf("Notícias Comunidade")));
						}
						JOptionPane.showMessageDialog(null, mensagem);
					}
					arquivo.close();
				} catch (Exception erro) {
					System.err.print("Exception err 2.0");
				}
				System.out.println("");
			}
		});
		btnNewButton_2.setBounds(36, 117, 89, 23);
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JButton("USIM5");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String linha;
				String mensagem = "Criado com Sucesso! \nDiretório: C:\\TesteInterface\\USIM5.txt";

				visitaSite("https://br.tradingview.com/symbols/BMFBOVESPA-USIM5");

				try {

					BufferedReader arquivo = new BufferedReader(new FileReader("C:\\TesteInterface\\TextoAtivo.txt"));
//						printa no arquivo todos system.out
					PrintStream out = new PrintStream(new File("C:\\TesteInterface\\USIM5.txt"));
//						volta o System.out ao normal(sem printar dentro do arquivo)
					PrintStream stdout = System.out;
//						out.close();

					while (arquivo.ready()) {
//								pega a linha
						while ((linha = arquivo.readLine()) != null) {
							System.setOut(out);
							System.out.println("USIM5");
							System.out.println(linha.substring(linha.indexOf("— Perfil") + "— Perfil".length(),
									linha.indexOf("Notícias Comunidade")));
							System.out.println("");
							System.setOut(stdout);
							System.out.println(linha.substring(linha.indexOf("— Perfil") + "— Perfil".length(),
									linha.indexOf("Notícias Comunidade")));
						}
						JOptionPane.showMessageDialog(null, mensagem);
					}
					arquivo.close();
				} catch (Exception erro) {
					System.err.print("Exception err 2.0");
				}
				System.out.println("");
			}
		});
		btnNewButton_3.setBounds(172, 117, 89, 23);
		contentPane.add(btnNewButton_3);

		JLabel lblNewLabel = new JLabel("LISTA DE ATIVOS");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(10, 11, 176, 28);
		contentPane.add(lblNewLabel);
	}
}
