package xadrez.pecas;

import tabuleiroJogo.Posicao;
import tabuleiroJogo.Tabuleiro;
import xadrez.PecaDeXadrez;
import xadrez.Cor;

public class Peao extends PecaDeXadrez {

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public boolean[][] movimentosPossiveis() {

		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Posicao pos = new Posicao(0, 0);

		// Peao se move para cima (-1), se branco; bara baixo (+1) se preto.
		if (getCor() == Cor.BRANCO) {
			pos.setarValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}
			// Caso for o primeiro movimento, pode movimentar duas casas
			pos.setarValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao pos2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)
					&& getTabuleiro().posicaoExiste(pos2) && !getTabuleiro().jaTemUmaPeca(pos2)
					&& getContarMovimentos() == 0) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}

			// Pecao captura peças pela diagonal para esquerda (-1) e direita (+1)
			pos.setarValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}
			pos.setarValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}
		}

		// Se for um peça preta
		else {
			pos.setarValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}
			// Caso for o primeiro movimento, pode movimentar duas casas
			pos.setarValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao pos2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(pos) && !getTabuleiro().jaTemUmaPeca(pos)
					&& getTabuleiro().posicaoExiste(pos2) && !getTabuleiro().jaTemUmaPeca(pos2)
					&& getContarMovimentos() == 0) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}

			// Pecao captura peças pela diagonal para esquerda (-1) e direita (+1)
			pos.setarValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}
			pos.setarValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(pos) && existeAquiUmaPecaAdversaria(pos)) {
				matriz[pos.getLinha()][pos.getColuna()] = true;
			}
		}

		return matriz;
	}

	@Override
	public String toString() {
		return "PEA";
	}

}
