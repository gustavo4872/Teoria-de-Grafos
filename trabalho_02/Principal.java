package trabalho_02;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/*
 * Algoritmo de Dijkstra
 */

public class Principal {

	static Vertice[] vertices = {new Vertice("0", 0),
			new Vertice("1", 1),
			new Vertice("2", 2),
			new Vertice("3", 3),
			new Vertice("4", 4),
			new Vertice("5", 5),
			new Vertice("6", 6),
			new Vertice("7", 7),
			new Vertice("8", 8)};
	
	static ArrayList<Aresta> arestas = new ArrayList<>();
	
	public static void main(String[] args) {
		
		arestas.add(new Aresta(vertices[0], vertices[1], 4));
		arestas.add(new Aresta(vertices[0], vertices[7], 8));
		arestas.add(new Aresta(vertices[1], vertices[2], 8));
		arestas.add(new Aresta(vertices[1], vertices[7], 11));
		arestas.add(new Aresta(vertices[2], vertices[3], 7));
		arestas.add(new Aresta(vertices[2], vertices[5], 4));
		arestas.add(new Aresta(vertices[2], vertices[8], 2));
		arestas.add(new Aresta(vertices[3], vertices[4], 9));
		arestas.add(new Aresta(vertices[3], vertices[5], 14));
		arestas.add(new Aresta(vertices[4], vertices[5], 10));
		arestas.add(new Aresta(vertices[5], vertices[6], 2));
		arestas.add(new Aresta(vertices[6], vertices[8], 6));
		arestas.add(new Aresta(vertices[6], vertices[7], 1));
		
		Collections.sort(arestas);
		
		int conj = 0;
		
		int[] kruskal = new int[vertices.length]; 
		for (int i = 0; i < kruskal.length; i++) {
			kruskal[i] = 0;
		}
		
		ArrayList<Aresta> arestaMenorC = new ArrayList<>();
		int ar = 0;
		
		for (int i = 0; i < arestas.size(); i++) {
			
			System.out.print(kruskal[arestas.get(i).origem.id]);
			System.out.print("&&");
			System.out.print(kruskal[arestas.get(i).destino.id]);
			
			if (kruskal[arestas.get(i).origem.id]==0 && kruskal[arestas.get(i).destino.id]==0) {
				ar = ar +2;
				conj++;
				kruskal[arestas.get(i).origem.id]=conj;
				kruskal[arestas.get(i).destino.id]=conj;
				arestaMenorC.add(arestas.get(i));
			}else if (kruskal[arestas.get(i).origem.id]!=kruskal[arestas.get(i).destino.id]) {
				ar = ar + 2;
				arestaMenorC.add(arestas.get(i));
				if (kruskal[arestas.get(i).origem.id]!=0) {
					if (kruskal[arestas.get(i).destino.id]!=0) {
						for (int j = 0; j < kruskal.length; j++) {
							if (kruskal[j] == kruskal[arestas.get(i).destino.id]) {
								kruskal[j] = kruskal[arestas.get(i).origem.id];
							}
						}
					}else {
						kruskal[arestas.get(i).destino.id] = kruskal[arestas.get(i).origem.id]; 
					}
				}else if (kruskal[arestas.get(i).destino.id]!=kruskal[arestas.get(i).origem.id]) {
					if (kruskal[arestas.get(i).origem.id]!=0) {
						for (int j = 0; j < kruskal.length; j++) {
							if (kruskal[j] == kruskal[arestas.get(i).origem.id]) {
								kruskal[j] = kruskal[arestas.get(i).destino.id];
							}
						}
					}else {
						kruskal[arestas.get(i).origem.id] = kruskal[arestas.get(i).destino.id]; 
					}
				}
			}
			
			System.out.print(" -> ");
			System.out.print(kruskal[arestas.get(i).destino.id]);
			System.out.print("&&");
			System.out.println(kruskal[arestas.get(i).origem.id]);
			if (ar == vertices.length -1) {
				break;
			}
		}

		System.out.println("");
		System.out.println("");
		
		for (Aresta aresta : arestaMenorC) {
			System.out.print("Origem e destino: ");
			System.out.print(aresta.origem.nome);
			System.out.print(" - ");
			System.out.print(aresta.destino.nome);
			System.out.println("");
		}
		
	}
	
	

}
