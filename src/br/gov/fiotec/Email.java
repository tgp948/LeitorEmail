package br.gov.fiotec;

public class Email {
	
	private String remetente;
	private String destinatario;
	private String dataEnvio;
	private String conteudo;
	private String nomeArquivo;
	
	public Email(String remetente, String destinatario, String dataEnvio, String conteudo, String nomeArquivo)
	{
		this.remetente = remetente;
		this.destinatario = destinatario;
		this.dataEnvio = dataEnvio;
		this.conteudo = conteudo;
		this.nomeArquivo = nomeArquivo;
	}


	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}


	public String getNomeArquivo() {
		return nomeArquivo;
	}


	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
}