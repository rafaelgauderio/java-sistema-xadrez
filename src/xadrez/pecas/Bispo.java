package xadrez.pecas;

import tabuleiroJogo.Tabuleiro;
import tabuleiroJogo.Posicao;
import xadrez.PecaDeXadrez;
import xadrez.Cor;

public class Bispo extends xadrez.PecaDeXadrez {

	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "BIS";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao pos = new Posicao(0, 0);

		// cima = linha-1 / esquerda = coluna-1 / linha=baixo +1 / direita=coluna+1

		// NOROESTE
		// Para Cima e para esquerda
		pos.setarValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
			pos.setarValores(pos.getLinha() - 1, pos.getColuna() - 1);
			;

		}
		if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// NORDESTE
		// Para cima e para direita
		pos.setarValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
			pos.setarValores(pos.getLinha() - 1, pos.getColuna() + 1);
		}

		if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// SUDOESTE
		// para baixo e para esquerda

		pos.setarValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
			pos.setarValores(pos.getLinha() + 1, pos.getColuna() - 1);
			;
		}

		if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// SUDESTE
		// para baixo e para direita

		pos.setarValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
			pos.setarValores(pos.getLinha() + 1, pos.getColuna() + 1);
			;
		}

		if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		return matriz;
	}

}
