package xadrez.pecas;

import tabuleiroJogo.Tabuleiro;
import xadrez.PecaDeXadrez;
import xadrez.Cor;
import tabuleiroJogo.Posicao;

public class Rei extends PecaDeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override

	public String toString() {

		return "REI";
	}

	private boolean podeMover(Posicao posicao) {
		PecaDeXadrez peca = (PecaDeXadrez) getTabuleiro().peca(posicao);

		if (peca == null || peca.getCor() != getCor()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao pos = new Posicao(0, 0);

		// acima

		pos.setarValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(pos) && podeMover(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// abaixo

		pos.setarValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(pos) && podeMover(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// esquerda

		pos.setarValores(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(pos) && podeMover(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// direita

		pos.setarValores(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(pos) && podeMover(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// DIAGONAIS

		// noroeste = linha - 1 && coluna -1

		pos.setarValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(pos) && podeMover(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// nordeste = linha -1 && coluna +1

		pos.setarValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(pos) && podeMover(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// sudoeste = linha +1 && coluna -1

		pos.setarValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posicaoExiste(pos) && podeMover(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// sudeste = linha +1 && coluna +1

		pos.setarValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posicaoExiste(pos) && podeMover(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		return matriz;
	}

}
