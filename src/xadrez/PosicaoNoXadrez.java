package xadrez;

import tabuleiroJogo.Posicao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PosicaoNoXadrez {

	// no chadrez se usa primeiro a coluna e depois a linha
	private char coluna;
	private int linha;

	public PosicaoNoXadrez(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ExcecaoDoXadrez("ERRO inicializando Posição do Xadrez. Valores validos são entre a1 até h8");

		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

	// LinhaDoChadrez = 8 - LinhaDaMatriz - 8 - 0 = 8 8 - 1 = 7
	// ColunaDoChadrez = colunaDaMatriz - 'a'; 'b' - 'a' = 1
	protected Posicao ParaPosicaoDoXadrez() {
		return new Posicao(8 - linha, coluna - 'a');
	}

	protected static PosicaoNoXadrez DaPosicaoDoXadre(Posicao posicao) {
		return new PosicaoNoXadrez((char) ('a' - posicao.getColuna()), 8 - posicao.getLinha());
	}

	@Override
	public String toString() {
		// "" - para conseguir concatenar duas String sem o compilador acusar erro
		return "" + coluna + linha;
	}

}
