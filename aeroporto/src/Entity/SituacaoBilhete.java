package Entity;

public enum SituacaoBilhete {
	
	DISPONIVEL("1"),
	RESERVADO("2"),
	VENDIDO("3");
	
	private String id;
	
	private SituacaoBilhete(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public static SituacaoBilhete getById(String id) {
		for(SituacaoBilhete situacaoBilhete : SituacaoBilhete.values()) {
			if(situacaoBilhete.getId().equals(id)) {
				return situacaoBilhete;
			}
		}
		return null;
	}

//	private long id;
//	private String nome;
//	
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public String getNome() {
//		return nome;
//	}
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
	
}
