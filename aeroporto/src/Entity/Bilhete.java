package Entity;

public class Bilhete {

	private long id;
	private int numero;
	private String assento;
	private SituacaoBilhete situacaoBilhete;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getAssento() {
		return assento;
	}
	public void setAssento(String assento) {
		this.assento = assento;
	}
	public SituacaoBilhete getSituacaoBilhete() {
		return situacaoBilhete;
	}
	public void setSituacaoBilhete(SituacaoBilhete situacaoBilhete) {
		this.situacaoBilhete = situacaoBilhete;
	}
	
	void reservar(Passageiro passageiro){
		
	}
	
	void comprar() {
		
	}
	
	void cancelarReserva() {
		
	}
	
	void checkIn(Bagagem bagagem) {
		
	}
	
}
