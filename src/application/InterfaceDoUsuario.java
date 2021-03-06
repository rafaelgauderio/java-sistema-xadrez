package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoNoXadrez;
import xadrez.PartidaDeXadrez;

public class InterfaceDoUsuario {

	// https://stackoverflow.com/questions/2979383/java-clear-the-console

	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static PosicaoNoXadrez lerPosicaoNoXadrez(Scanner sc) {

		try {
			String string = sc.nextLine();
			char coluna = string.charAt(0);
			// recortando o segundo caracter da termo a1, h8
			int linha = Integer.parseInt(string.substring(1));
			return new PosicaoNoXadrez(coluna, linha);

		}

		catch (RuntimeException erro) {
			throw new InputMismatchException("ERRO lendo Posi��o do Xadrez");
		}

	}

	public static void imprimirPartida(PartidaDeXadrez partidaDeXadrez, List<PecaDeXadrez> capturada) {
		imprimirTabuleiro(partidaDeXadrez.getPecas());
		System.out.println();
		imprimirPecasCapturadas(capturada);
		System.out.println();

		System.out.println("Turno : " + partidaDeXadrez.getTurno());

		if (!partidaDeXadrez.getXequemate() == true) {
			System.out.println("Aguardando jogador: " + partidaDeXadrez.getJogadorDaVez());
			if (partidaDeXadrez.getXeque() == true) {
				System.out.println(ANSI_PURPLE_BACKGROUND + "XEQUE! PERIGO! proteja seu Rei");
			}

		} else {
			System.out.println(ANSI_PURPLE_BACKGROUND + "XEQUEMATE! GAME OVER");
			System.out.println("O vencedor da partida foi o jogador " + partidaDeXadrez.getJogadorDaVez());

		}

	}

	public static void imprimirTabuleiro(PecaDeXadrez[][] pecas) {
		System.out.println();

		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimirPeca(pecas[i][j], false);
			}
			System.out.println("\n");

		}

		System.out.println("   a   b   c   d   e   f   g   h  ");

	}

	public static void imprimirTabuleiro(PecaDeXadrez[][] pecas, boolean[][] movimentosPossiveis) {
		System.out.println();

		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println("\n");
		}

		System.out.println("   a   b   c   d   e   f   g   h  ");

	}

	private static void imprimirPeca(PecaDeXadrez peca, boolean corDeFundo) {
		// Se a variavel corDeFundo for verdadeira, entao muda o fundo da tela para
		// mostrar ser um movimento
		// possivel da peca
		if (corDeFundo) {
			System.out.print(ANSI_RED_BACKGROUND);
		}

		if (peca == null) {
			System.out.print("---" + ANSI_RESET);
		} else {

			if (peca.getCor() == Cor.BRANCO) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}

		System.out.print(" ");
	}

	private static void imprimirPecasCapturadas(List<PecaDeXadrez> capturada) {

		List<PecaDeXadrez> brancas = capturada.stream().filter(x -> x.getCor() == Cor.BRANCO)
				.collect(Collectors.toList());
		List<PecaDeXadrez> pretas = capturada.stream().filter(x -> x.getCor() == Cor.PRETO)
				.collect(Collectors.toList());
		System.out.println("Lista de pecas capturadas: ");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(brancas.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("Pretas: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(pretas.toArray()));
		System.out.print(ANSI_RESET);

	}

}
