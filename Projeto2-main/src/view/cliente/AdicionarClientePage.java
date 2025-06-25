package view.cliente;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Cliente;
import classes.ClienteEstrangeiro;
import classes.ClienteNacional;
import conexao_db.ClienteDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdicionarClientePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField email;
	private JTextField telefone;
	private JTextField nome;
	private JTextField documento;
	@SuppressWarnings("unused")
	private ClientePage TelaAnterior;


	
	private boolean validarCampos(String nome, String email, String telefone, String documento) {
	    if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || documento.isEmpty()) {
	        JOptionPane.showMessageDialog(this, 
	            "Todos os campos (Nome, E-mail, Telefone, Documento) são obrigatórios.", 
	            "Campos obrigatórios", 
	            JOptionPane.WARNING_MESSAGE);
	        return false;
	    }
	    return true;
	}
	
	public AdicionarClientePage(ClientePage TelaAnterior) {
		this.TelaAnterior = TelaAnterior;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow][][]", "[][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Adicionar Cliente");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblNewLabel, "cell 4 0");
		
		JLabel lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "cell 3 1,alignx trailing");
		
		nome = new JTextField();
		nome.setColumns(10);
		contentPane.add(nome, "cell 4 1,growx");
		
		JLabel lblTelefone = new JLabel("Telefone:");
		contentPane.add(lblTelefone, "cell 3 2,alignx trailing");
		
		telefone = new JTextField();
		telefone.setColumns(10);
		contentPane.add(telefone, "cell 4 2,growx");
		
		JLabel lblEmail = new JLabel("Email:");
		contentPane.add(lblEmail, "cell 3 3,alignx trailing");
		
		email = new JTextField();
		contentPane.add(email, "cell 4 3,growx");
		email.setColumns(10);
		JLabel lblDocumento = new JLabel("Documento:");
		JRadioButton rdbtnNacional = new JRadioButton("Nacional");
		JRadioButton rdbtnEstrangeiro = new JRadioButton("Estrangeiro");

		
		rdbtnNacional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNacional.isSelected()) {
					rdbtnEstrangeiro.setSelected(false);
					lblDocumento.setText("CPF:");
						}
					}
				});
				contentPane.add(rdbtnNacional, "cell 3 4");
		
		rdbtnEstrangeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnEstrangeiro.isSelected()) {
					rdbtnNacional.setSelected(false);
					lblDocumento.setText("Passaporte:");

				}
			}
		});
		contentPane.add(rdbtnEstrangeiro, "cell 4 4");
		
		contentPane.add(lblDocumento, "cell 3 5,alignx trailing");
		
		documento = new JTextField();
		contentPane.add(documento, "cell 4 5,growx");
		documento.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (TelaAnterior != null) {
	            	TelaAnterior.setVisible(true);
	            }
	            
			}
		});
		contentPane.add(btnCancelar, "cell 3 7");
		
		JButton btnNewButton = new JButton("Adicionar");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String nome1 = nome.getText().trim();
		            String email1 = email.getText().trim();
		            String telefone1 = telefone.getText().trim();
		            String documento1 = documento.getText().trim();

		            if (!validarCampos(nome1, email1, telefone1, documento1)) {
		                return;
		            }

		            Cliente cliente = null;
		            if (rdbtnNacional.isSelected()) {
		                cliente = new ClienteNacional();
		                ((ClienteNacional) cliente).setCpf(documento1);
		            } else if (rdbtnEstrangeiro.isSelected()) {
		                cliente = new ClienteEstrangeiro();
		                ((ClienteEstrangeiro) cliente).setPassaporte(documento1);
		            } else {
		                JOptionPane.showMessageDialog(null, "Selecione o tipo de cliente!");
		                return;
		            }

		            cliente.setNome(nome1);
		            cliente.setEmail(email1);
		            cliente.setTelefone(telefone1);

		            ClienteDAO.inserirCliente(cliente);

		            JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
		            dispose();
		            if (TelaAnterior != null) {
		                TelaAnterior.setVisible(true);
		            }

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, 
		                "Erro ao adicionar cliente: " + ex.getMessage(), 
		                "Erro", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		contentPane.add(btnNewButton, "cell 5 7");

	}
	
	
}
