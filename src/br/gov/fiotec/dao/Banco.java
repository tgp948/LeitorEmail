package br.gov.fiotec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	
	private String usuario = "sa";
	private String password = "123456789";
	private String db = "leitoremail";
	private Connection conexao = null;
	
	
	
	public void conectar() throws SQLException
	{
		if(conexao == null || conexao.isClosed())
		{
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, usuario, password);
		}
	}
	
	public void fechar() throws SQLException 
	{
		conexao.close();
	}
	
	public Connection getConexao()
	{
		return this.conexao;
	}

}
