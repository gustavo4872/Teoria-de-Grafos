package trabalho_03;

public class Aresta implements Comparable<Aresta>{
	
	@Override
	public int compareTo(Aresta outraAresta) {
		if(this.valor < outraAresta.valor) {
			return -1;
		}else if (this.valor > outraAresta.valor) {
			return 1;
		}
		return 0;
	}
	
	public Aresta(Vertice origem, Vertice destino ) {
		this.origem = origem;
		this.destino = destino;
	}
	
	public Aresta(Vertice origem, Vertice destino, int valor) {
		this.origem = origem;
		this.destino = destino;
		this.valor = valor;
	}
	
	private Vertice origem;
	private Vertice destino;
	private int valor;
	
	public Vertice getOrigem() {
		return origem;
	}

	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
