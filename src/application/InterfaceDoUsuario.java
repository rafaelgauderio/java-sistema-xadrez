package application;

import xadrez.PecaDeXadrez;

public class InterfaceDoUsuario {

	public static void imprimirTabuleiro(PecaDeXadrez[][] pecas) {

		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimirPeca(pecas[i][j]);
			}
			System.out.println();
		}

		System.out.println("   a   b   c   d   e   f   g   h  ");

	}

	private static void imprimirPeca(PecaDeXadrez peca) {
		if (peca == null) {
			System.out.print("---");
		} else {
			System.out.print(peca);
		}

		System.out.print(" ");
	}

}
