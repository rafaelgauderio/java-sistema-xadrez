package xadrez;

import tabuleiroJogo.Tabuleiro;
import tabuleiroJogo.Posicao;
import tabuleiroJogo.Peca;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Classe onde ficam as regras do jogo de Xadrez
public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorDaVez;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequeMate;
	// variavel se a peça tem chance de tomar o en Passant
	// A captura en passant deve ser feita IMEDIATAMENTE apos o peai ter movido por duas casas, caso contratio o jogador
	// adversario perde o direito de fazer o en passant. Consiste no unico movimento no Xadrez em que a peca que captura nao
	// sera movida ara a casa ocupada pela peca capturada
	private PecaDeXadrez vunerabilidadeEnPassant;

	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();

	public PartidaDeXadrez() {

		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		// No Xadrez começa sempre as peças BRANCAS
		jogadorDaVez = Cor.BRANCO;
		// por padrao uma propriedade boleana recebe falso
		xeque = false;
		xequeMate = false;
		FormacaoInicial();

	}

	public int getTurno() {
		return turno;
	}

	public Cor getJogadorDaVez() {
		return jogadorDaVez;
	}

	public boolean getXeque() {
		return xeque;
	}

	public boolean getXequemate() {
		return xequeMate;
	}

	public PecaDeXadrez getVunerabilidadeEnPassant() {
		return vunerabilidadeEnPassant;
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
		Posicao posicao = posicaoOrigem.paraPosicaoDoXadrez();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();

	}

	public PecaDeXadrez realizarMovimentoDeXadrez(PosicaoNoXadrez posicaoOrigem, PosicaoNoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.paraPosicaoDoXadrez();
		Posicao destino = posicaoDestino.paraPosicaoDoXadrez();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = realizarMovimento(origem, destino);

		// Jogador nao pode se colocar em Xeque e nem deixar de se defender de um xeque

		if (TesteXeque(jogadorDaVez) == true) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new ExcecaoDoXadrez("Tu nao podes se colocar em XEQUE e nem deixar de se defender de um XEQUE!");
		}

		PecaDeXadrez pecasMovimentadas = (PecaDeXadrez) tabuleiro.peca(destino);
		// se a cor do oponente do jogador da vez estiver em XEQUE, a partidade estara//
		// em xeque
		xeque = (TesteXeque(corDoOponente(jogadorDaVez)) ? true : false);

		// testar se teve um xequemate, antes de chamar a proxima jogada
		if (testeDeXequeMate(corDoOponente(jogadorDaVez)) == true) {
			xequeMate = true;
		}

		else {
			proximoTurno();
		}

		// # movimento especial en passant

		if (pecasMovimentadas instanceof Peao
				&& (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			vunerabilidadeEnPassant = pecasMovimentadas;
		} else {
			vunerabilidadeEnPassant = null;
		}

		return (PecaDeXadrez) pecaCapturada;
	}

	private Peca realizarMovimento(Posicao origem, Posicao destino) {
		PecaDeXadrez peca = (PecaDeXadrez) tabuleiro.removerPeca(origem);
		peca.aumentarContagem();

		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.lugarDaPeca(peca, destino);

		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}

		// # movimento Especial Roque pequeno = Lado do rei
		if (peca instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {

			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.lugarDaPeca(torre, destinoTorre);
			torre.aumentarContagem();

		}

		// # movimento Especial Roque grande = Lado da Rainha
		if (peca instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {

			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(origemTorre);
			tabuleiro.lugarDaPeca(torre, destinoTorre);
			torre.aumentarContagem();

		}

		// # movimento especial el passant
		if (peca instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
				Posicao posicaoPeao;
				if (peca.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				} else {
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}

				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasNoTabuleiro.remove(pecaCapturada);

			}
		}

		return pecaCapturada;
	}

	// metodo de desfazer o movimento, caso a pessoa tentar se colocar em Xeque, ou
	// nao se defender de um ataque
	// que a coloque em Xeque. Tem que desfazer toda o metodo de realziar moviment
	// 1 - remover da posicao destino, 2 - colocar na posicao origem 3 - se tiver
	// captudaro alguma peca; tirar da lista
	// de pecas capturadas e inserir de volta na lista das pecas do tabuleiro

	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {

		PecaDeXadrez peca = (PecaDeXadrez) tabuleiro.removerPeca(destino);
		peca.diminuirContagem();
		tabuleiro.lugarDaPeca(peca, origem);

		if (pecaCapturada != null) {
			tabuleiro.lugarDaPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);

		}

		// # movimento Especial Roque pequeno = Lado do rei
		if (peca instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {

			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(destinoTorre);
			tabuleiro.lugarDaPeca(torre, origemTorre);
			torre.diminuirContagem();

		}

		// # movimento Especial Roque grande = Lado da Rainha
		if (peca instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {

			Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			PecaDeXadrez torre = (PecaDeXadrez) tabuleiro.removerPeca(destinoTorre);
			tabuleiro.lugarDaPeca(torre, origemTorre);
			torre.diminuirContagem();

		}

		// # movimento especial el passant
		if (peca instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == vunerabilidadeEnPassant) {
				PecaDeXadrez peao = (PecaDeXadrez)tabuleiro.removerPeca(destino);
				Posicao posicaoPeao;
				if (peca.getCor() == Cor.BRANCO) {
					posicaoPeao = new Posicao(3, destino.getColuna());
				} else {
					posicaoPeao = new Posicao(4, destino.getColuna());

				}

				tabuleiro.lugarDaPeca(peao, posicaoPeao);

			}
		}

	}

	private void validarPosicaoOrigem(Posicao posicao) {
		if (!tabuleiro.jaTemUmaPeca(posicao)) {
			throw new ExcecaoDoXadrez("Nao existe peca nessa posicao!");

		}

		if (jogadorDaVez != ((PecaDeXadrez) tabuleiro.peca(posicao)).getCor()) {

			throw new ExcecaoDoXadrez("A peca escolhida nao lhe pertence! escolha um peca "
					+ (jogadorDaVez == Cor.BRANCO ? Cor.BRANCO : Cor.PRETO));

		}

		if (!tabuleiro.peca(posicao).existeAlgumMovimentoPossivel()) {
			throw new ExcecaoDoXadrez("Nao exite nenhum movimento possivel para essa peca!");
		}

	}

	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new ExcecaoDoXadrez("A peca escolhida nao pode se movimentar para essa posicao de destino");
		}
	}

	private void proximoTurno() {
		turno++;
		jogadorDaVez = (jogadorDaVez == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}

	// metodo que retorna a cor da peca do oponente, para caso for um rei do
	// adversario, declarar o xeque

	private Cor corDoOponente(Cor cor) {
		return (cor == Cor.BRANCO) ? Cor.PRETO : cor.BRANCO;
	}

	// Peca nao tem cor, o que tem cor é a peça de Xadrez, tem que fazer downcasting
	// no filtro da expressao lambda
	private PecaDeXadrez CorDoRei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca peca : lista) {
			if (peca instanceof Rei) {
				return (PecaDeXadrez) peca;
			}
		}
		// Nem tratar esse excacao, pois se acontecer significa que o programa está
		// quebrando
		throw new IllegalStateException("Nao existe Rei dessa " + cor + " no tabuleiro!");
	}

	// Se o teste de Xeque der verdadeiro significa que o Rei está em Xeque, o
	// jogador em xeque
	// é obrigad a defender esse Rei

	private boolean TesteXeque(Cor cor) {
		Posicao PosicaoDoRei = CorDoRei(cor).getPosicaoDeXadrez().paraPosicaoDoXadrez();
		List<Peca> pecasDoOponente = pecasNoTabuleiro.stream()
				.filter(x -> ((PecaDeXadrez) x).getCor() == corDoOponente(cor)).collect(Collectors.toList());
		for (Peca peca : pecasDoOponente) {
			boolean[][] matriz = peca.movimentosPossiveis();
			if (matriz[PosicaoDoRei.getLinha()][PosicaoDoRei.getColuna()]) {
				return true;
			}

		}

		return false;
	}

	private boolean testeDeXequeMate(Cor cor) {
		// Senao está nem em xeque, muito menos está em xequemate
		if (!TesteXeque(cor)) {
			return false;
		}
		// fazer uma lista de todas as pecas e depois percorrer toda essa lista testando
		// todos os movimentos possiveis dessas pestas,
		// testando se nenhum desses movimentos tira o rei do xeque. Ao testar esses
		// movimentos que nao tirararam do xeque, desfazer
		// toda essa movimentocao para nao bagunca o tabuleiro. Se algum movimento
		// possivel tirar do xequemate, é apenas um xeque;
		// se nenhum movimento tirar, declarar o xeque mate.
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez) x).getCor() == cor)
				.collect(Collectors.toList());
		for (Peca peca : lista) {
			boolean[][] matriz = peca.movimentosPossiveis();
			for (int i = 0; i < tabuleiro.getLinhas(); i++) {
				for (int j = 0; j < tabuleiro.getColunas(); j++) {
					if (matriz[i][j]) {
						Posicao origem = ((PecaDeXadrez) peca).getPosicaoDeXadrez().paraPosicaoDoXadrez();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = realizarMovimento(origem, destino);
						boolean testeXeque = TesteXeque(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testeXeque) {
							return false;
						}

					}
				}
			}
		}
		return true;

	}

	private void lugarNovoPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.lugarDaPeca(peca, new PosicaoNoXadrez(coluna, linha).paraPosicaoDoXadrez());
		pecasNoTabuleiro.add(peca);

	}

	public void FormacaoInicial() {

		lugarNovoPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		lugarNovoPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		lugarNovoPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		lugarNovoPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		lugarNovoPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		lugarNovoPeca('d', 5, new Peao(tabuleiro, Cor.BRANCO, this));
		lugarNovoPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		lugarNovoPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		lugarNovoPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		lugarNovoPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));

		lugarNovoPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
		lugarNovoPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
		lugarNovoPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		lugarNovoPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		lugarNovoPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
		lugarNovoPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		lugarNovoPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
		lugarNovoPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
		lugarNovoPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
		lugarNovoPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
		lugarNovoPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
		lugarNovoPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
		lugarNovoPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
		lugarNovoPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
		lugarNovoPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
		lugarNovoPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));

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
