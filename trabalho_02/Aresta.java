package trabalho_02;

public class Aresta implements Comparable<Aresta>{
	
	@Override
	public int compareTo(Aresta arg0) {
		if (this.valor < arg0.valor) {
			return -1;
		}else if (this.valor > arg0.valor) {
			return 1;
		}
		return 0;
	}
	
	int valor;
	Vertice origem, destino;
	
	//Construtor para grafo não valoravel
	public Aresta(Vertice origem, Vertice destino) {
		this.origem = origem;
		this.destino = destino;
	}
	
	//Contrutor para grafo valoravel
	public Aresta(Vertice origem, Vertice destino, int valor) {
		this.origem = origem;
		this.destino = destino;
		this.setValor(valor);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
}
