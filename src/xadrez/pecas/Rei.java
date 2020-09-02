package xadrez.pecas;

import tabuleiroJogo.Tabuleiro;
import xadrez.PecaDeXadrez;
import xadrez.Cor;

public class Rei extends PecaDeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro,cor);
	}
	
	@Override
	
	public String toString() {
		
		return "REI";
	}
	
}