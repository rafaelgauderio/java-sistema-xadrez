package xadrez.pecas;

import tabuleiroJogo.Tabuleiro;
import xadrez.PecaDeXadrez;
import xadrez.Cor;
import tabuleiroJogo.Posicao;

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

		Posicao posicaoInicial = new Posicao(0, 0);

		// Para Cima
		posicaoInicial.setarValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(posicaoInicial) && !getTabuleiro().jaTemUmaPeca(posicaoInicial)) {
			matriz[posicaoInicial.getLinha()][posicaoInicial.getColuna()] = true;
			posicaoInicial.setLinha(posicaoInicial.getLinha() - 1);

		}
		if (getTabuleiro().posicaoExiste(posicaoInicial) && existeAquiUmaPecaAdversaria(posicaoInicial)) {
			matriz[posicaoInicial.getLinha()][posicaoInicial.getColuna()] = true;
		}

		// Para Baixo
		posicaoInicial.setarValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(posicaoInicial) && !getTabuleiro().jaTemUmaPeca(posicaoInicial)) {
			matriz[posicaoInicial.getLinha()][posicaoInicial.getColuna()] = true;
			posicaoInicial.setLinha(posicaoInicial.getLinha() + 1);
		}

		if (getTabuleiro().posicaoExiste(posicaoInicial) && existeAquiUmaPecaAdversaria(posicaoInicial) ) {
			matriz[posicaoInicial.getLinha()][posicaoInicial.getColuna()] = true;
		}

		// para esquerda

		posicao.setarValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(posicaoInicial) && !getTabuleiro().jaTemUmaPeca(posicaoInicial)) {
			matriz[posicaoInicial.getLinha()][posicaoInicial.getColuna()] = true;
			posicaoInicial.setColuna(posicaoInicial.getColuna() - 1);
		}

		if (getTabuleiro().posicaoExiste(posicaoInicial) && existeAquiUmaPecaAdversaria(posicaoInicial)) {
			matriz[posicaoInicial.getLinha()][posicaoInicial.getColuna()] = true;
		}

		// para direita

		posicao.setarValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(posicaoInicial) && !getTabuleiro().jaTemUmaPeca(posicaoInicial) ) {
			matriz[posicaoInicial.getLinha()][posicaoInicial.getColuna()] = true;
			posicaoInicial.setColuna(posicaoInicial.getColuna() + 1);
		}

		if (getTabuleiro().posicaoExiste(posicaoInicial) && existeAquiUmaPecaAdversaria(posicaoInicial) ) {
			matriz[posicaoInicial.getLinha()][posicaoInicial.getColuna()] = true;
		}

		return matriz;
	}

}
