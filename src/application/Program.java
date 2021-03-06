package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcecaoDoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoNoXadrez;
import java.util.ArrayList;
import java.util.List;

public class Program {

	public static void main(String[] args) {
		/*
		 * Posicao pos = new Posicao(3,6); System.out.println(pos); * Instanciar um
		 * tabuleiro de chadrez, setado as pecas tudo como null; Tabuleiro tabuleiro =
		 * new Tabuleiro(8, 8);
		 */

		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturada = new ArrayList<PecaDeXadrez>();

		while (partidaDeXadrez.getXequemate() == false) {
			try {

				InterfaceDoUsuario.limparTela();
				InterfaceDoUsuario.imprimirPartida(partidaDeXadrez, capturada);
				System.out.println();
				System.out.println("Origem: ");
				PosicaoNoXadrez origem = InterfaceDoUsuario.lerPosicaoNoXadrez(sc);

				boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(origem);
				InterfaceDoUsuario.limparTela();
				// sobrecarga do metodo de imprimir o tabuleiro, mas agora com todas as
				// possicoes possiveis e com o fundo colorido
				InterfaceDoUsuario.imprimirTabuleiro(partidaDeXadrez.getPecas(), movimentosPossiveis);
				System.out.println();
				System.out.println("Destino: ");
				PosicaoNoXadrez destino = InterfaceDoUsuario.lerPosicaoNoXadrez(sc);

				PecaDeXadrez pecaCapturada = partidaDeXadrez.realizarMovimentoDeXadrez(origem, destino);

				if (pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}

				if (partidaDeXadrez.getPromocao() != null) {
					System.out.println("Informe por qual tipo de peca tu quer subsituir o teu peao!");
					System.out.println("R/B/C/T");
					String pecaSubstituta = sc.nextLine().toUpperCase();
					while (!pecaSubstituta.equals("R") && !pecaSubstituta.equals("B") && !pecaSubstituta.equals("C")
							&& !pecaSubstituta.equals("T")) {
						System.out.print("Valor invalido! Informe R/B/C/T: ");
						pecaSubstituta = sc.nextLine().toUpperCase();
					}
					partidaDeXadrez.substituiPecaPromovida(pecaSubstituta);

				}

			} catch (ExcecaoDoXadrez erro) {

				System.out.println(erro.getMessage());
				System.out.println("ENTER para continuar!");
				sc.nextLine();
			} catch (InputMismatchException erro) {
				System.out.println(erro.getMessage());
				System.out.println("ENTER para continuar!");
				sc.nextLine();

			}

		}
		InterfaceDoUsuario.limparTela();
		InterfaceDoUsuario.imprimirPartida(partidaDeXadrez, capturada);

	}
}
