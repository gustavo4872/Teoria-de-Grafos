package trabalho_03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * Algoritmo de Kruskal e Prim-Jarnik
 */

public class Main {
	
	static class PrimJarnik {
		
		public PrimJarnik(Vertice vertice, int distancia) {
			this.vertice = vertice;
			this.distancia = distancia;
		}
		
		private Vertice vertice;
		private int distancia;
	}
	
	private static Vertice[] vertices;
		
	private static ArrayList<Aresta> arestas = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		try {
			
			System.out.println("->Digite o Conjunto V.\n->Ex.: a,b,c,d,e");
			String aux[] = scanner.nextLine().replace(" ", "").split(",");
			vertices = new Vertice[aux.length];
			
			for (int i = 0; i < aux.length; i++) {
				
				if (aux[i].equals("")) 	{
					scanner.close();
					//throw new Exception();
				}
				
				vertices[i] = new Vertice(aux[i], i);
			}
			
			System.out.println("->Conjunto V criado!");
			System.out.println("->Digite o Conjunto E.\n->Ex.: a,b,1,c,d,2,e,a,3");
			String aux2[] = scanner.nextLine().replace(" ", "").split(",");
			
			Vertice origem = new Vertice();
			Vertice destino = new Vertice();
			for (int i = 0; i < aux2.length; i = i + 3) {
				System.out.println(i);
				for (int j = 0; j < vertices.length; j++) {
					if (aux2[i].equals(vertices[j].getNome())) {						
						origem = vertices[j];
					}
					if (aux2[i+1].equals(vertices[j].getNome())) {
						destino = vertices[j];
					}
				}
				arestas.add(new Aresta(origem, destino, Integer.parseInt(aux2[i+2])));
			}
		
		} catch (Exception e) {
			System.out.println("Erro na entrada de dados!");
			System.out.println(e.toString());
			scanner.close();
			System.exit(0);
		}
		
		scanner.close();
		System.out.println("");
		System.out.println("Prim-Jarnik");
		System.out.println("");
		prim_jarnik();
		System.out.println("");
		System.out.println("Kruskal");
		System.out.println("");
		kruskal();
		
	}	
	
	public static void prim_jarnik() {
		
		Vertice u;
		String[] pai = new String[vertices.length];
		ArrayList<Vertice> adjs = new ArrayList<Vertice>();
		ArrayList<PrimJarnik> adjsU = new ArrayList<Main.PrimJarnik>();
		
		for (int i = 0; i < vertices.length; i++) {
			vertices[i].setValor(Integer.MAX_VALUE);
		}
		
		pai[0] = vertices[0].getNome();
		adjs.add(vertices[0]);
		vertices[0].setValor(0);
		
		Vertice vertice;
		int distancia = 0;
		
		for (int i = 0; i < vertices.length; i++) {
			
			if (adjs.size()!=0) {
				
				u = adjs.remove(0);
				u.setFlag(true);
				
				if (retorna_adjacentes(u, adjs, adjsU)) {

					for (int j = 0; j < adjsU.size(); j++) {
						
						vertice = adjsU.get(j).vertice;
						distancia = adjsU.get(j).distancia;
								
						if (!vertice.isFlag() && distancia < vertice.getValor()) {
							
							pai[vertice.getPosicao()] = u.getNome();
							vertice.setValor(distancia);
						}
						
					}

				}
				
				Collections.sort(adjs);
				
			}
			adjsU = new ArrayList<Main.PrimJarnik>();
		}
		
		distancia = 0;
		for (int i = 1; i < vertices.length; i++) {
			if (pai[i]!=null) {
				vertice = vertices[i];
				distancia += vertice.getValor(); 
				System.out.print(pai[i]);
				System.out.print("|");
				System.out.print(vertice.getNome() );
				System.out.print(":");
				System.out.print(vertice.getValor());
				System.out.println("");
			}
		}
		System.out.println("Distância Total: "+distancia);
	}
	
		
	public static void kruskal() {
		int conjOrigem; 	
		int posOrigem; 
		
		int conjDestino; 
		int posDestino; 
		
		int[] conjunto = new int[vertices.length];  
		
		ArrayList<Aresta> menorCaminho = new ArrayList<Aresta>(); 		
		Collections.sort(arestas); 
			
		for (int i = 0; i < arestas.size(); i++) {
			
			posOrigem = arestas.get(i).getOrigem().getPosicao();
			conjOrigem = conjunto[posOrigem];
			
			posDestino = arestas.get(i).getDestino().getPosicao();			
			conjDestino = conjunto[posDestino];
			
			if (conjOrigem == 0 && conjDestino == 0) { 

				conjunto[posOrigem] = i+1; 
				conjunto[posDestino] = i+1; 
				menorCaminho.add(arestas.get(i));
				
			}else { 
				
				if (conjOrigem == 0 || conjDestino == 0 ) { 

					conjunto[posOrigem] = conjOrigem + conjDestino; 
					conjunto[posDestino] = conjOrigem + conjDestino; 
					menorCaminho.add(arestas.get(i));
					
				}else if (conjOrigem != conjDestino) { 

					menorCaminho.add(arestas.get(i));
					int novoConj = conjOrigem; 
					int antigoConj = conjDestino; 
					
					for (int j = 0; j < conjunto.length; j++) { 
					
						if (conjunto[j] == antigoConj) conjunto[j] = novoConj;
						
					}
					
				}
				
			}
			
			if (menorCaminho.size() == vertices.length - 1) { 
				break;
			}
		}
		
		
		Aresta aresta;
		int distancia = 0;
		for (int i = 0; i < menorCaminho.size(); i++) {
			aresta = menorCaminho.get(i);
			distancia += aresta.getValor();
			System.out.print(aresta.getOrigem().getNome());
			System.out.print("|");
			System.out.print(aresta.getDestino().getNome());
			System.out.print(":");
			System.out.print(aresta.getValor());
			System.out.println();
		}
		System.out.println("Distância Total: "+distancia);
	}
	
	
	public static boolean retorna_adjacentes(Vertice u, ArrayList<Vertice> adjs, ArrayList<PrimJarnik> adjsU) {
				
		Vertice origem;
		Vertice destino;
		int valor;
		
		for (int i = 0; i < arestas.size(); i++) {
						
			origem = arestas.get(i).getOrigem();
			destino = arestas.get(i).getDestino();
			valor = arestas.get(i).getValor();
			
			if (origem.equals(u) && !destino.isFlag()) {
				adjsU.add(new PrimJarnik(destino, valor));
				if (!adjs.contains(destino)) {
					adjs.add(destino);
				}
			}else if (destino.equals(u) && !origem.isFlag()) {
				adjsU.add(new PrimJarnik(origem, valor));
				if (!adjs.contains(origem)) {
					adjs.add(origem);
				}
			}
		}
		
		return adjsU.size() > 0 ? true : false;
	}
	
}