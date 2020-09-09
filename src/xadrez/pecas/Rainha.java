package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaDeXadrez;

public class Rainha extends PecaDeXadrez {

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "RAI";
	}

	// Rainha se move como a somatoria dos movimentos possives da Torre e do Bispo
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao pos = new Posicao(0, 0);

		// cima = linha-1 / esquerda = coluna-1 / linha=baixo +1 / direita=coluna+1
		// Para Cima
		pos.setarValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
			pos.setLinha(pos.getLinha() - 1);

		}
		if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// Para Baixo
		pos.setarValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
			pos.setLinha(pos.getLinha() + 1);
		}

		if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// para esquerda

		pos.setarValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
			pos.setColuna(pos.getColuna() - 1);
		}

		if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

		// para direita

		pos.setarValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
			pos.setColuna(pos.getColuna() + 1);
		}

		if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
			matriz[pos.getLinha()][pos.getColuna()] = true;
		}

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
