package tabuleiroJogo;

public class ExcecaoDoTabuleiro  extends RuntimeException{ 

	private static final long serialVersionUID = 1L;
	
	public ExcecaoDoTabuleiro (String msg) {
		super(msg);
	}

}
