package application;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoNoXadrez;

import java.util.Scanner;

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
			InterfaceDoUsuario.imprimirTabuleiro(partidaDeXadrez.getPecas());
			System.out.println();
			System.out.println("Origem: ");
			PosicaoNoXadrez origem = InterfaceDoUsuario.lerPosicaoNoXadrez(sc);
			
			System.out.println();
			System.out.println("Destino: ");
			
			PosicaoNoXadrez destino = InterfaceDoUsuario.lerPosicaoNoXadrez(sc);

			PecaDeXadrez pecaCapturada = partidaDeXadrez.realizarMovimentoDeXadrez(origem, destino);
		}

	}
}
