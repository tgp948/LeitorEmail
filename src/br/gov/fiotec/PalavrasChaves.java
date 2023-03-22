package br.gov.fiotec;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.gov.fiotec.dao.EmailDAO;

public class PalavrasChaves {

	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) {
		String caminho = System.getProperty("user.dir");
		caminho += "\\Apoio Analista I Externo_palavras.txt";
		HashMap<String, Integer> dicionario = new HashMap<String, Integer>();
		EmailDAO dao = new EmailDAO();
		
		try
		{
			File arquivo = new File(caminho);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), "UTF-16"));
			String linha = buffer.readLine();
			while (linha != null) {
				dicionario.put(linha, null) ;
				linha = buffer.readLine();
			}
			buffer.close();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		HashMap<String, Integer> palavraChave = dao.buscaTodosEmails();
		for(Map.Entry<String, Integer> map : palavraChave.entrySet())
		{
			if(map.getKey() != "" && map.getValue() > 2)
			{
				String tipo = null;
				if(dicionario.containsKey(map.getKey().toLowerCase()))
				{
					tipo = "Dicionário";
				}
				else if(map.getKey().matches("[A-Z][a-z]*"))
				{
					tipo = "Nome Próprio";
				}else 
				{
					tipo = "Outros";
				}
				dao.inserirPalavraChave(map.getKey(), map.getValue(), tipo);
			}
		}
	}

}
