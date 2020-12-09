package Entity;

import java.time.LocalDate;

public class Horario {

	private long id;
	private String codigo;
	private LocalDate partida;
	private LocalDate chegada;
	private Aeroporto aeroporto;
	private Aviao aviao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	public Aeroporto getAeroporto() {
		return aeroporto;
	}
	public void setAeroporto(Aeroporto aeroporto) {
		this.aeroporto = aeroporto;
	}
	
	
	
}
