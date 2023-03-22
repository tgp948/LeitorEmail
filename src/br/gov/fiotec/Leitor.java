package br.gov.fiotec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import br.gov.fiotec.dao.EmailDAO;

public class Leitor {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
		
		String caminho;
		
		
		if (args.length == 0)
		{
			caminho = System.getProperty("user.dir");
			caminho += "\\email";
			
		}
		else
		{
			caminho = args[0];
		}
		System.out.println("O caminho usado é: " + caminho);
		
		try {
		
			File pasta = new File(caminho);
			File[] arquivos = pasta.listFiles();
			System.out.println("Arquivos :");
			if(arquivos != null)
			{
				for(int a = 0; a < arquivos.length; a++)
				{
					String nomeArquivo = arquivos[a].getName();
					int posicao = nomeArquivo.lastIndexOf(".");
					String flag = "";
					if(nomeArquivo.substring(posicao + 1).equals("txt"))
					{
						BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(arquivos[a]), "ANSI"));
						String linha = buffer.readLine();
						String remetente = null;
						String destinatario  = null;
						String dataEnvio = null;
						String assunto = null;
						while (linha != null) {
			                
			                String[] conteudoLinha = linha.split(":");
			                
			                
		                	if(conteudoLinha[0].trim().equals("De"))
		                	{
		                		remetente = conteudoLinha[1].trim();
		                	}
		                	if(conteudoLinha[0].trim().equals("Para"))
		                	{
		                		destinatario = conteudoLinha[1].trim();
		                	}
		                	if(conteudoLinha[0].trim().equals("Enviado em"))
		                	{
		                		dataEnvio  = conteudoLinha[1].trim();
		                	}
		                	if(conteudoLinha[0].trim().equals("Assunto"))
		                	{
		                		assunto = conteudoLinha[1];
		                		while(linha != null)
		                		{
		                			assunto += linha;
		                			linha = buffer.readLine();
		                		}
		                	}
		                	
		                	linha = buffer.readLine();
			                
			                
						}
						buffer.close();
						if((remetente != null || remetente !="") && (destinatario != null || destinatario !="") && (dataEnvio != null || dataEnvio !="") && (assunto != null || assunto !=""))
						{
							Email email = new Email(remetente, destinatario, dataEnvio, assunto, nomeArquivo);
							EmailDAO emaildao = new EmailDAO(email);
							if(emaildao.inserirEmail())
							{
								flag = "OK";
							}else
							{
								flag = "FALHOU";
							}
						}else {
							flag = "FALHOU";
						}
					}
					System.out.println(nomeArquivo + " --------> " + flag);
				}
			}else {
				System.out.println("Pasta " + caminho + " está vazia.");
			}
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
