package xadrez;

import tabuleiroJogo.Tabuleiro;
import tabuleiroJogo.Peca;
import tabuleiroJogo.Posicao;
import xadrez.PosicaoNoXadrez;

public abstract class PecaDeXadrez extends Peca {

	private Cor cor;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;

	}

	public Cor getCor() {
		return cor;
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
