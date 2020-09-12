package xadrez.pecas;

import tabuleiroJogo.Tabuleiro;
import xadrez.PecaDeXadrez;
import xadrez.Cor;
import tabuleiroJogo.Posicao;
import xadrez.PartidaDeXadrez;

public class Rei extends PecaDeXadrez {

	private PartidaDeXadrez partidaDeXadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaDeXadrez partidaDeXadrez) {
		super(tabuleiro, cor);
		this.partidaDeXadrez = partidaDeXadrez;
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

	// verificar se a torre pode fazer Roque, tem que nao ter sido movida e nao ter
	// outra peça entre a Torre o Rei
	private boolean testeTorreRoque(Posicao posicao) {

		PecaDeXadrez pecaTorre = (PecaDeXadrez) getTabuleiro().peca(posicao);
		if (pecaTorre != null && pecaTorre instanceof Torre && pecaTorre.getCor() == getCor()
				&& pecaTorre.getContarMovimentos() == 0) {
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

		// MOVIMENTO ESPECIAL DE ROQUE

		if (getContarMovimentos() == 0 && !partidaDeXadrez.getXeque()) {

			// # movimento Especial Roque pequeno = Lado do rei
			Posicao posTorre1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testeTorreRoque(posTorre1) == true) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}

			// # movimento Especial Roque grande = Lado da rainha
			Posicao posTorre2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if (testeTorreRoque(posTorre2) == true) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null
						&& getTabuleiro().peca(p3) == null) {
					matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}

		}

		return matriz;
	}

}
