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

	private void lugarNovoPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoNoChadrez(coluna, linha).ParaPosicaoDoXadrez());
	}

	public void FormacaoInicial() {

		lugarNovoPeca('b', 6, new Torre(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('g', 1, new Torre(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('c', 3, new Torre(tabuleiro, Cor.BRANCO));

		lugarNovoPeca('e', 8, new Rei(tabuleiro, Cor.PRETO));
		lugarNovoPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		lugarNovoPeca('b', 8, new Torre(tabuleiro, Cor.PRETO));
		lugarNovoPeca('h', 3, new Torre(tabuleiro, Cor.PRETO));
		
		/* Para poder imprimir o console colorido
		 * abre um git bash na pasta bin do projeto e executa a classe principal do Programa
		 * # java application/Program 
		 */
		
		/*
		 * tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(2, 2));
		 * tabuleiro.lugarDaPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(4, 3));
		 * tabuleiro.lugarDaPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
		 * tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.PRETO), new Posicao(7, 3));
		 */

	}

}
