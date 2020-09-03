package tabuleiroJogo;

public class Posicao {

	private int linha;
	private int coluna;

	public Posicao() {

	}

	public Posicao(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;

	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;

	}

	public int getColuna() {
		return coluna;

	}

	public void setColuna(int coluna) {
		this.coluna = coluna;

	}
	
	public void setarValores(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public static String avisaUsuario() {
		return "AVISO DE ERRO";
	}

	// Imprimir a posicao na tela
	@Override
	public String toString() {
		return "linha: " + linha + " | coluna: " + coluna;
	}

}
