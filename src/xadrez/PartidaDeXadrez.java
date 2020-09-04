package xadrez;

import tabuleiroJogo.Tabuleiro;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Peca;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;
import java.util.ArrayList;
import java.util.List;

//Classe onde ficam as regras do jogo de Xadrez
public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorDaVez;
	private Tabuleiro tabuleiro;
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaDeXadrez() {

		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		// No Xadrez começa sempre as peças BRANCAS
		jogadorDaVez = Cor.BRANCO;
		FormacaoInicial();

	}

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorDaVez() {
		return jogadorDaVez;
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

	public boolean[][] movimentosPossiveis(PosicaoNoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.ParaPosicaoDoXadrez();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();

	}

	public PecaDeXadrez realizarMovimentoDeXadrez(PosicaoNoXadrez posicaoOrigem, PosicaoNoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.ParaPosicaoDoXadrez();
		Posicao destino = posicaoDestino.ParaPosicaoDoXadrez();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = realizarMovimento(origem, destino);
		proximoTurno();

		return (PecaDeXadrez) pecaCapturada;
	}

	private Peca realizarMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.lugarDaPeca(p, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		return pecaCapturada;
	}

	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.jaTemUmaPeca(posicao)) {
			throw new ExcecaoDoXadrez("Não há peça nessa posição!");

		}

		if (jogadorDaVez != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCor()) {

			throw new ExcecaoDoXadrez("A peça escolhida não é sua. Escolha uma peça "
					+ (jogadorDaVez == Cor.BRANCO ? Cor.BRANCO : Cor.PRETO));

		}

		if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
			throw new ExcecaoDoXadrez("Não exite nenhum movimento possível para essa peça!");
		}

	}

	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new ExcecaoDoXadrez("A peça escolhida não pode se movimentar para essa posição de destino");
		}
	}

	private void proximoTurno() {
		turno++;
		jogadorDaVez = (jogadorDaVez == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	private void lugarNovoPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoNoXadrez(coluna, linha).ParaPosicaoDoXadrez());
		pecasNoTabuleiro.add(peca);

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

		/*
		 * Para poder imprimir o console colorido abre um git bash na pasta bin do
		 * projeto e executa a classe principal do Programa # java application/Program
		 */

		/*
		 * tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.BRANCO), new Posicao(2, 2));
		 * tabuleiro.lugarDaPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(4, 3));
		 * tabuleiro.lugarDaPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(7, 4));
		 * tabuleiro.lugarDaPeca(new Torre(tabuleiro, Cor.PRETO), new Posicao(7, 3));
		 */

	}

}
