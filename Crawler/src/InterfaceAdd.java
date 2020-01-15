import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Color;

public class InterfaceAdd extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceAdd frame = new InterfaceAdd();
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
	public InterfaceAdd() {
		setTitle("Interface para Adicionar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 235, 211);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Bovespa");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeAtivo = JOptionPane.showInputDialog("Digite o nome do Ativo: ");

				if (nomeAtivo != null) {
					BufferedWriter add;
					try {
						add = new BufferedWriter(
								new FileWriter("C:\\Ativos\\ListaAtivos\\ListaAtivosBovespa.txt", true));
						add.newLine();
						add.write(nomeAtivo);
						add.close();
					} catch (Exception err) {
						System.err.println("Erro ADD");
					}
				}
			}
		});
		btnNewButton.setBounds(72, 36, 87, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Nasdaq");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeAtivo = JOptionPane.showInputDialog("Digite o nome do Ativo: ");

				if (nomeAtivo != null) {
					BufferedWriter add;
					try {
						add = new BufferedWriter(
								new FileWriter("C:\\Ativos\\ListaAtivos\\ListaAtivosNasdaq.txt", true));
						add.newLine();
						add.write(nomeAtivo);
						add.close();
					} catch (Exception err) {
						System.err.println("Erro ADD 1.0");
					}
				}
			}
		});
		btnNewButton_1.setBounds(70, 134, 89, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Nyse");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeAtivo = JOptionPane.showInputDialog("Digite o nome do Ativo: ");

				if (nomeAtivo != null) {
					BufferedWriter add;
					try {
						add = new BufferedWriter(new FileWriter("C:\\Ativos\\ListaAtivos\\ListaAtivosNyse.txt", true));
						add.newLine();
						add.write(nomeAtivo);
						add.close();
					} catch (Exception err) {
						System.err.println("Erro ADD 2.0");
					}
				}
			}
		});
		btnNewButton_2.setBounds(70, 85, 89, 23);
		contentPane.add(btnNewButton_2);

		JLabel lblNewLabel = new JLabel("Escolha a lista para adicionar");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(24, 11, 204, 14);
		contentPane.add(lblNewLabel);

	}
}
