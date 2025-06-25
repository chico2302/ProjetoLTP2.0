package view.cliente;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexao_db.ClienteDAO;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PesquisarClientePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField documento;
	@SuppressWarnings("unused")
	private ClientePage TelaAnterior;


	public PesquisarClientePage(ClientePage TelaAnterior) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][][][][][][grow][][][][]", "[][][]"));
		
		JLabel lblNewLabel = new JLabel("Pesquisar cliente");
		JLabel lblNewLabel_1 = new JLabel("Documento:");
		documento = new JTextField();
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String documento1 = documento.getText();
				if (documento1.trim().isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Documento obrigatório!");
				} else {
					ClienteDAO.buscarCliente(documento1);
					}
				} catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, 
		                "Erro ao pesquisar cliente: " + ex.getMessage(), 
		                "Erro", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TelaAnterior != null) {
					TelaAnterior.setVisible(true);
				}
				dispose();
			}
		});


		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 6 1");
		contentPane.add(lblNewLabel_1, "cell 5 3,alignx trailing");
		contentPane.add(documento, "cell 6 3 2 1,growx");
		documento.setColumns(10);
		contentPane.add(btnVoltar, "cell 5 7");
		contentPane.add(btnPesquisar, "cell 7 7");

	}

}
