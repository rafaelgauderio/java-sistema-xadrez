package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcecaoDoXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoNoXadrez;

public class Program {

	public static void main(String[] args) {
		/*
		 * Posicao pos = new Posicao(3,6); System.out.println(pos); * Instanciar um
		 * tabuleiro de chadrez, setado as pecas tudo como null; Tabuleiro tabuleiro =
		 * new Tabuleiro(8, 8);
		 */

		Scanner sc = new Scanner(System.in);

		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		while (true) {
			try {
				
				InterfaceDoUsuario.limparTela();
				InterfaceDoUsuario.imprimirTabuleiro(partidaDeXadrez.getPecas());
				System.out.println();
				System.out.println("Origem: ");
				PosicaoNoXadrez origem = InterfaceDoUsuario.lerPosicaoNoXadrez(sc);

				boolean [][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(origem);
				InterfaceDoUsuario.limparTela();
				//sobrecarga do do metodo de imprimir o tabuleiro, mas agora com todas as possicoes possiveis
				//e com o fundo colorido
				InterfaceDoUsuario.imprimirTabuleiro(partidaDeXadrez.getPecas(), movimentosPossiveis);
				System.out.println();
				System.out.println("Destino: ");
				PosicaoNoXadrez destino = InterfaceDoUsuario.lerPosicaoNoXadrez(sc);

				PecaDeXadrez pecaCapturada = partidaDeXadrez.realizarMovimentoDeXadrez(origem, destino);

			} catch (ExcecaoDoXadrez erro) {

				System.out.println(erro.getMessage());
				System.out.println("Pressione ENTER para continuar!");
				sc.nextLine();
			} catch (InputMismatchException erro) {
				System.out.println(erro.getMessage());
				System.out.println("Pressione ENTER para continuar!");
				sc.nextLine();

			}

		}

	}
}
