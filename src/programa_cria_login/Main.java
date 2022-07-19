package programa_cria_login;

import entities.Login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		ArrayList<String> listagemDeLogins = new ArrayList<String>();
		ArrayList<String> nomeCompletoFragmentado = new ArrayList<String>();
		ArrayList<String> nomesParaLogin = new ArrayList<String>();
		String nomeCompletoInformado;
		int numeroDeLoginsCadastrados = 0;
		char continuar = 's';
		
		do {
			
			nomeCompletoFragmentado = null;
			nomesParaLogin = null;
			
			do {
				
				if (nomeCompletoFragmentado != null) {
					if (nomeCompletoFragmentado.size() == 1 || nomeCompletoFragmentado.size() == 0) {
						System.out.println("O nome informado precisa estar completo!");
					}
				}
				
				System.out.println("Insira o nome completo do funcionário:");
				nomeCompletoInformado = sc.nextLine();

				nomeCompletoInformado = nomeCompletoInformado.trim();
				nomeCompletoInformado = nomeCompletoInformado.toLowerCase();
				nomeCompletoFragmentado = nomeCompletoInformado.split(" "); 
				nomeCompletoFragmentado = Arrays.asList(nomesSeparados);

			} while (nomeCompletoFragmentado.length < 2);
			
			String nomeCompletoReconstruido = "";
			
			for (int x = 0 ; x < nomeCompletoFragmentado.length ; x++) {
					nomeCompletoFragmentado[x] = nomeCompletoFragmentado[x].substring(0,1).toUpperCase() + nomeCompletoFragmentado[x].substring(1);
					nomeCompletoReconstruido += nomeCompletoFragmentado[x] + " ";
			}
			
			nomeCompletoReconstruido = nomeCompletoReconstruido.trim();
			nomeCompletoReconstruido = nomeCompletoReconstruido.replace(" De ", " de ");
			nomeCompletoReconstruido = nomeCompletoReconstruido.replace(" Do ", " do ");
			nomeCompletoReconstruido = nomeCompletoReconstruido.replace(" Dos ", " dos ");
			nomeCompletoReconstruido = nomeCompletoReconstruido.replace(" Da ", " da ");
			nomeCompletoReconstruido = nomeCompletoReconstruido.replace(" Das ", " das ");
			
			String nomeCompletoSemAcentos = nomeCompletoInformado.replaceAll("[ãáâ]", "a");
			nomeCompletoSemAcentos = nomeCompletoSemAcentos.replaceAll("[éê]", "e");
			nomeCompletoSemAcentos = nomeCompletoSemAcentos.replaceAll("[óôõ]", "o");
			nomeCompletoSemAcentos = nomeCompletoSemAcentos.replace("í", "i");
			nomeCompletoSemAcentos = nomeCompletoSemAcentos.replace("ú", "u");
			nomeCompletoSemAcentos = nomeCompletoSemAcentos.replace("ç", "c");
			
			nomesParaLogin = nomeCompletoSemAcentos.split(" ");
			String primeiroNome = nomesParaLogin[0];
			String sobrenome = nomesParaLogin[nomesParaLogin.length-1];
			
			boolean temAgnome = false;
			
			if (sobrenome == "filho" || sobrenome == "neto" || sobrenome == "junior" || sobrenome == "sobrinho") {
				sobrenome = nomesParaLogin[nomesParaLogin.length-2];
				temAgnome = true;
			}
			
			String loginGerado = primeiroNome + "." + sobrenome;
			
			if (numeroDeLoginsCadastrados > 0) {
				for (int x = numeroDeLoginsCadastrados , quantidadeDeLoginsIguais = 0 ; x > 0 ; x--) {
					if (loginGerado == listagemDeLogins.get(x)) {
						if ((nomesParaLogin.length > 2 && temAgnome == false) || (nomesParaLogin.length > 3 && temAgnome == true)) {
							for (int y = numeroDeLoginsCadastrados ; y > 0 ; y--) {
								loginGerado = primeiroNome + "." + nomesParaLogin[1].charAt(quantidadeDeLoginsIguais) + sobrenome;
								quantidadeDeLoginsIguais++;
								if (quantidadeDeLoginsIguais == nomesParaLogin[1].length() && ((nomesParaLogin.length > 3 && temAgnome == false) || (nomesParaLogin.length > 4 && temAgnome == true))) {
									quantidadeDeLoginsIguais = 0;
									for (int z = numeroDeLoginsCadastrados ; z > 0 ; z--) {
										loginGerado = primeiroNome + "." + nomesParaLogin[2].charAt(quantidadeDeLoginsIguais) + sobrenome;
										quantidadeDeLoginsIguais++;
									}
									if (quantidadeDeLoginsIguais == nomesParaLogin[2].length()) {
										for (int a = numeroDeLoginsCadastrados, b = 2 ; a > 0 ; a--) {
											if (loginGerado == listagemDeLogins.get(x)) {
												loginGerado += b;
												b++;
											}
										}
									}
								}
								else {
									for (int a = numeroDeLoginsCadastrados, b = 2 ; a > 0 ; a--) {
										if (loginGerado == listagemDeLogins.get(x)) {
											loginGerado += b;
											b++;
										}
									}
								}
							}
						}
						else {
							for (int a = numeroDeLoginsCadastrados, b = 2 ; a > 0 ; a--) {
								if (loginGerado == listagemDeLogins.get(x)) {
									loginGerado += b;
									b++;
								}
							}
						}
					}
				}
			}
			
			listagemDeLogins.add(loginGerado);
			numeroDeLoginsCadastrados++;
			
			System.out.println("");
			System.out.println("Cadastro realizado com sucesso!");
			System.out.println("Nome do Usuário: " + nomeCompletoReconstruido);
			System.out.println("Login: " + loginGerado);
			System.out.println("");
			
			do {
				if (continuar != 's' && continuar != 'S' && continuar != 'n' && continuar != 'N') {
					System.out.println("Resposta inválida! Por favor, responda apenas com \"S\" ou \"N\".");
				}
				
				System.out.println("Deseja inserir um novo registro? (S/N)");
				continuar = sc.next().charAt(0);
				sc.nextLine();
				
			} while (continuar != 's' && continuar != 'S' && continuar != 'n' && continuar != 'N');
			
		} while (continuar == 's' || continuar == 'S');
		
		sc.close();
		
		System.out.print("Obrigado por utilizar o serviço de criação de login FoscoviusGenerator 1.0!");
	}

}
