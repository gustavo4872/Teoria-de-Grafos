package trabalho_03;

public class Vertice implements Comparable<Vertice>{
	
	@Override
	public int compareTo(Vertice o) {
		if (this.valor < o.getValor()) {
			return -1;
		}else if (this.valor > o.getValor()) {
			return 1;
		}
		return 0;
	}
	
	public Vertice() {
		
	}
	
	public Vertice(String nome, int posicao) {
		this.nome = nome;
		this.posicao = posicao;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
	
	private String nome;
	private boolean flag;
	private int valor;
	private int posicao;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

}
