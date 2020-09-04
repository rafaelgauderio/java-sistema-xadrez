package xadrez.pecas;

import tabuleiroJogo.Tabuleiro;
import tabuleiroJogo.Posicao;
import xadrez.PecaDeXadrez;
import xadrez.Cor;


public class Torre extends xadrez.PecaDeXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "TOR";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao pos = new Posicao(0, 0);

		// Para Cimac3
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

		return matriz;
	}

}
