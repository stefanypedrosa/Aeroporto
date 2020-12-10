package Entity;

import java.time.LocalDate;

public class Bilhete {

	private long id;
	private int numero;
	private String assento;
	private double pesoBagagem;
	private String situacaoBilhete;
	private LocalDate partida;
	private LocalDate chegada;
	private String codigoAeroporto;
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
	public String getSituacaoBilhete() {
		if(situacaoBilhete == null || situacaoBilhete == "") {
			situacaoBilhete = "DISPONIVEL";
		}
		return situacaoBilhete;
	}
	public void setSituacaoBilhete(String situacaoBilhete) {
		if(situacaoBilhete == null || situacaoBilhete == "") {
			situacaoBilhete = "DISPONIVEL";
		}
		this.situacaoBilhete = situacaoBilhete;
	}
	public LocalDate getPartida() {
		return partida;
	}
	public void setPartida(LocalDate partida) {
		this.partida = partida;
	}
	public LocalDate getChegada() {
		return chegada;
	}
	public void setChegada(LocalDate chegada) {
		this.chegada = chegada;
	}
	public String getCodigoAeroporto() {
		return codigoAeroporto;
	}
	public void setCodigoAeroporto(String codigoAeroporto) {
		this.codigoAeroporto = codigoAeroporto;
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
