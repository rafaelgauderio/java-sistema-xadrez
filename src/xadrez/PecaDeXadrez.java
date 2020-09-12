package xadrez;

import tabuleiroJogo.Tabuleiro;
import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import xadrez.PosicaoNoXadrez;

public abstract class PecaDeXadrez extends Peca {

	private Cor cor;
	private int contarMovimentos;
	
	
	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;

	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContarMovimentos() {
		return contarMovimentos;
	}
	
	public void aumentarContagem() {
		contarMovimentos++;
	}
	
	public void diminuirContagem() {
		contarMovimentos--;
	}
	
	public int contarMovimentosBranco(int brancos) {

		if (contarMovimentos == 0) {
			brancos = 0;
		} else {
			if (contarMovimentos % 2 == 1) {
				brancos = (contarMovimentos / 2) + 1;
			} else {
				brancos = (contarMovimentos / 2);
			}

		}

		return brancos;
	}

	public int contarMovimentosPreto(int pretos) {
		if (contarMovimentos == 0 || contarMovimentos == 1) {
			pretos = 0;
		} else {
			pretos = contarMovimentos / 2;

		}

		return pretos;
	}
	
	
	//pegar a posicao(em matriz) converter para PosicaoNoXadrez ( letra e numero)
	public PosicaoNoXadrez getPosicaoDeXadrez() {
		return PosicaoNoXadrez.daPosicaoDoXadrez(posicao);
	}

	protected boolean existeAquiUmaPecaAdversaria(Posicao posicao) {
		PecaDeXadrez peca = (PecaDeXadrez) getTabuleiro().peca(posicao);
		// vai ser uma peça adversária se não for nula e ser de uma cor diferente
		return peca != null && peca.getCor() != cor;
	}
	

}
