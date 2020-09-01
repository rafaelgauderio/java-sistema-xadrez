package xadrez;

import tabuleiroJogo.Tabuleiro;
import tabuleiroJogo.Posicao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

//Classe onde ficam as regras do jogo de Xadrez
public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;

	public PartidaDeXadrez() {

		tabuleiro = new Tabuleiro(8, 8);
		FormacaoInicial();

	}

	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] matriz = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				matriz[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return matriz;
	}

	public void FormacaoInicial() {

		tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(2, 2));
		tabuleiro.lugarDaPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(4, 3));
		tabuleiro.lugarDaPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
		tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.PRETO), new Posicao(7, 3));

	}

}
