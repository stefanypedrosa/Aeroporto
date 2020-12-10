package Entity;

public class Bilhete {

	private long id;
	private int numero;
	private String assento;
	private double pesoBagagem;
	private SituacaoBilhete situacaoBilhete;
	private Horario horario;
	private Passageiro passageiro;
	
	
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
	public double getPesoBagagem() {
		return pesoBagagem;
	}
	public void setPesoBagagem(double pesoBagagem) {
		this.pesoBagagem = pesoBagagem;
	}
	public SituacaoBilhete getSituacaoBilhete() {
		return situacaoBilhete;
	}
	public void setSituacaoBilhete(SituacaoBilhete situacaoBilhete) {
		this.situacaoBilhete = situacaoBilhete;
	}
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	public Passageiro getPassageiro() {
		return passageiro;
	}
	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}
	
	
	void reservar(Passageiro passageiro){
		
	}
	
	void comprar() {
		
	}
	
	void cancelarReserva() {
		
	}
	
	void checkIn() {
		
	}
	
}
