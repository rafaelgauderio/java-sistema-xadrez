package application;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.PartidaDeXadrez;

public class Program {

	public static void main(String[] args) {
		/*
		 * Posicao pos = new Posicao(3,6); 
		 * System.out.println(pos);		 *
		 * Instanciar um tabuleiro de chadrez, setado as pecas tudo como null;
		 * Tabuleiro tabuleiro = new Tabuleiro(8, 8);
		 */		
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		InterfaceDoUsuario.imprimirTabuleiro(partidaDeXadrez.getPecas());

	}
}
