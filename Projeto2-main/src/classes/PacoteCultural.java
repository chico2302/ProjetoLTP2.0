package classes;

public class PacoteCultural extends PacoteViagem {
	
	String tipo;
	
	public PacoteCultural(Long pacote_id, String nome, String destino, int duracao, double preco) {
		super(pacote_id, nome, destino, duracao, preco);
	}
	
	public PacoteCultural(Long pacote_id, String nome, String destino, int duracao, double preco, String tipo) {
		super(pacote_id, nome, destino, duracao, preco);
		this.tipo = "Cultural";
		pacotes.add(this);
	}
	
	public PacoteCultural(Long pacote_id, String nome, String destino, int duracao, double preco, String tipo, Cliente cliente) {
		super(pacote_id, nome, destino, duracao, preco, cliente);
		this.tipo = "Cultural";
		//String detalhes = "Inclui visitas a museus, centros históricos e experiências gastronômicas";
		pacotes.add(this);
	}

	public PacoteCultural() {
	}
}