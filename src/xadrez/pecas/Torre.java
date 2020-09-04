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

		Posicao peca = new Posicao(0, 0);

		// Para Cimac3
		peca.setarValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(peca) && !getTabuleiro().jaTemUmaPeca(peca)) {
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setLinha(peca.getLinha() - 1);

		}
		if (getTabuleiro().posicaoExiste(peca) && existeAquiUmaPecaAdversaria(peca)) {
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}

		// Para Baixo
		peca.setarValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(peca) && !getTabuleiro().jaTemUmaPeca(peca)) {
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setLinha(peca.getLinha() + 1);
		}

		if (getTabuleiro().posicaoExiste(peca) && existeAquiUmaPecaAdversaria(peca)) {
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}

		// para esquerda

		peca.setarValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(peca) && !getTabuleiro().jaTemUmaPeca(peca)) {
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setColuna(peca.getColuna() - 1);
		}

		if (getTabuleiro().posicaoExiste(peca) && existeAquiUmaPecaAdversaria(peca)) {
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}

		// para direita

		peca.setarValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(peca) && !getTabuleiro().jaTemUmaPeca(peca)) {
			matriz[peca.getLinha()][peca.getColuna()] = true;
			peca.setColuna(peca.getColuna() + 1);
		}

		if (getTabuleiro().posicaoExiste(peca) && existeAquiUmaPecaAdversaria(peca)) {
			matriz[peca.getLinha()][peca.getColuna()] = true;
		}

		return matriz;
	}

}
