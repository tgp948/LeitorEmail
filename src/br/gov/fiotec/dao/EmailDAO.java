package br.gov.fiotec.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import br.gov.fiotec.Email;

public class EmailDAO extends Banco  {
	
	Email email;
	
	public EmailDAO(Email email) 
	{
		this.email = email;
	}
	
	public EmailDAO()
	{
	
	}
	
	
	public Boolean inserirEmail() throws SQLException 
	{
		if(validaArquivo(email.getNomeArquivo()))
		{
			conectar();
			
			String sql = "INSERT INTO email (nomearquivo, remetente, destinatario, conteudo, dataemail) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = getConexao().prepareStatement(sql);
			stmt.setString(1, email.getNomeArquivo());
			stmt.setString(2, email.getRemetente());
			stmt.setString(3, email.getDestinatario());
			stmt.setString(4, email.getConteudo());
			stmt.setString(5, email.getDataEnvio());
			stmt.executeUpdate();
			stmt.close();
			fechar();
			
			return true;
		}else 
		{
			System.out.println("O arquivo " + email.getNomeArquivo() + " já existe no banco.");
			return false;
		}
		
	}
	
	public boolean validaArquivo(String arquivo) throws SQLException
	{
		conectar();
		String sql = "SELECT nomearquivo from email where nomearquivo = ?";
		PreparedStatement stmt = getConexao().prepareStatement(sql);
		stmt.setString(1, arquivo);
		ResultSet result = stmt.executeQuery();
		Boolean resultado = !result.next();
		stmt.close();
		fechar();
		return resultado;
	}
	
	public HashMap<String, Integer> buscaTodosEmails()
	{
		PreparedStatement stmt = null;
		HashMap<String, Integer> palavrasChaves = new HashMap<String, Integer>();
		try {
			conectar();
			String sql = "SELECT conteudo from email";
			stmt = getConexao().prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while(result.next())
			{
				String linha = result.getString("conteudo");
				if(linha != null)
				{
					System.out.println(linha);
					String[] strV = linha.split(" ");
					for(int a=0; a < strV.length; a++) 
					{
						if(palavrasChaves.containsKey(strV[a].replaceAll("[^\\p{L}]", "")))
						{
							palavrasChaves.put(strV[a].replaceAll("[^\\p{L}]", ""), palavrasChaves.get(strV[a].replaceAll("[^\\p{L}]", "")) + 1);
						}else
						{
							palavrasChaves.put(strV[a].replaceAll("[^\\p{L}]", ""), 1);
						}
					}
					
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				fechar();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return palavrasChaves;

	}
	
	public void inserirPalavraChave(String palavra, Integer vezes, String tipo) 
	{
		PreparedStatement stmt = null;
		try {
			conectar();
			String sql = "INSERT INTO palavraschaves (palavra, numvezes, categoria) VALUES (?, ?, ?)";
			stmt = getConexao().prepareStatement(sql);
			stmt.setString(1, palavra);
			stmt.setInt(2, vezes);
			stmt.setString(3, tipo);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				fechar();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
