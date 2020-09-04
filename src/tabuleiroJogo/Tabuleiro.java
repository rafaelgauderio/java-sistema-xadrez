package tabuleiroJogo;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Peca[][] pecas;

	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new ExcecaoDoTabuleiro("ERRO ao criar Tabuleiro: tem que ter pelo menos 1 linha e 1 coluna!");
		}

		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];

	}

	public int getLinhas() {
		return linhas;
	}

	/*
	 * public void setLinhas(int linhas) { this.linhas = linhas; }
	 */

	public int getColunas() {
		return colunas;

	}

	/*
	 * public void setColunas(int colunas) { this.colunas = colunas; }
	 */

	public Peca peca(int linha, int coluna) {
		if (!posicaoExiste(linha, coluna)) {
			throw new ExcecaoDoTabuleiro("Posição não existe no tabuleiro");
		}
		return pecas[linha][coluna];
	}

	public Peca peca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new ExcecaoDoTabuleiro("Posição não existe no tabuleiro");
		}

		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	public void lugarDaPeca(Peca peca, Posicao posicao) {
		if (jaTemUmaPeca(posicao)) {
			throw new ExcecaoDoTabuleiro("Já existe uma peça na posicao " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}

	public Peca removerPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new ExcecaoDoTabuleiro("Posição não existe no tabuleiro!");
		}
		if (peca(posicao) == null) {
			return null;
		}

		Peca auxiliar = peca(posicao);
		auxiliar.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return auxiliar;
	}

	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;

	}

	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}

	public boolean jaTemUmaPeca(Posicao posicao) {
		if (!posicaoExiste(posicao)) {
			throw new ExcecaoDoTabuleiro("ERRO!! Não existe essa posição no tabuleiro!!");
		}

		return peca(posicao) != null;
	}

}
