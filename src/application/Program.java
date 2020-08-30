package application;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;

public class Program {

	public static void main(String[] args) {

		System.out.println("Systema de xadrez inicial");
		Posicao pos = new Posicao(3, 6);
		System.out.println(pos);

		// Instanciar um tabuleiro de chadrez, setado as pecas tudo como null;
		Tabuleiro tabuleiro = new Tabuleiro(8, 8);

	}
}
