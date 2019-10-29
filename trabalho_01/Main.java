package trabalho_01;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/* 
 * Listas de arestas
 * Listas de adjacência
 * Matriz de adjacência
 * Matriz de incidência
*/

public class Main {
	
	private static String[] conjuntoV = {"a","b","c","d","e"};
	private static ArrayList<String> origem = new ArrayList<String>(); 
	private static ArrayList<String> destino = new ArrayList<String>();
	private static ArrayList<Integer> valor = new ArrayList<Integer>();	
	private static int orientado, valorado;	
	
	public static void main(String[] args) {		
		try {						
			orientado = JOptionPane.showConfirmDialog(null, "Grafo orientado?:");
			valorado = JOptionPane.showConfirmDialog(null, "Grafo Valorado?:");
			conjuntoV = JOptionPane.showInputDialog("Conjunto V (a,b,c,d,e...):").replaceAll(" ", "").split(",");			
			int maisUm;
			do {
				origem.add(JOptionPane.showInputDialog("Origem:"));				
				destino.add(JOptionPane.showInputDialog("Destino:"));
				maisUm = JOptionPane.showConfirmDialog(null, "Quer mais uma aresta:");
			} while (maisUm == 0);
			listaArestas();				
			listaAdjacencia();						
			matrizAdjacencia();
			matrizIncidencia();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro!");
		}
	}
	
	private static void listaArestas() {
		System.out.println("");
		System.out.println("---------LISTA DE ARESTAS---------");
		System.out.println("");
		if (valorado==1) {
			for (int i = 0; i < origem.size(); i++) {
				System.out.print(i+" - |"+""+origem.get(i)+"|"+destino.get(i)+"|");
				System.out.println("");
			}
		}else {
			for (int i = 0; i < origem.size(); i++) {
				System.out.print(i+" - |"+origem.get(i)+"|"+destino.get(i)+"|"+valor.get(i));
				System.out.println("");
			}
		} 
	}
	
	private static void listaAdjacencia() {	//FINALIZADO	
		System.out.println("");
		System.out.println("---------LISTA DE ADJACÊNCIA---------");		
		System.out.println("");
		if (orientado == 0) {
			for (int i = 0; i < conjuntoV.length; i++) {
				String retorno = conjuntoV[i];
				for (int j = 0; j < origem.size(); j++) {
					if (origem.get(j).equalsIgnoreCase(conjuntoV[i])) {
						retorno += " -> "+destino.get(j);
					}
				}
				System.out.println(retorno);
			}
		}else {
			for (int i = 0; i < conjuntoV.length; i++) {
				String ret = conjuntoV[i];
				for (int j = 0; j < origem.size(); j++) {
					if (conjuntoV[i].equalsIgnoreCase(destino.get(j))) {
						ret += " -> " + origem.get(j);
					}else if (conjuntoV[i].equalsIgnoreCase(origem.get(j))) {
						ret += " -> " + destino.get(j);
					}
				}
				System.out.println(ret);				
			}
		}
	}
	
	private static void matrizAdjacencia() {
		System.out.println("");
		System.out.println("---------MATRIZ DE ADJACÊNCIA---------");
		System.out.println("");
		int[][] matriz = new int[conjuntoV.length][conjuntoV.length];
		for (int i = 0; i < origem.size(); i++) {
			int x = - 1;
			int y = - 1;
			
			for (int j = 0; j < conjuntoV.length; j++) {
				if (conjuntoV[j].equalsIgnoreCase(origem.get(i))) {
					x = j;
				} 
				if (conjuntoV[j].equalsIgnoreCase(destino.get(i))) {
					y = j;
				}
				if (x != -1 && y != -1) {
					break;
				}
			}
			if (orientado == 1 && valorado == 1) {
				matriz[x][y] += 1;
				if (x!=y) {
					matriz[y][x] += 1;
				}
			}else if (orientado == 0 && valorado == 0) {
				matriz[x][y] += valor.get(i);
			}else if(valorado == 0 && orientado == 1){
				matriz[x][y] += valor.get(i);
				if (x!=y) {
					matriz[y][x] += valor.get(i);
				}
			}else {
				matriz[x][y] += 1;
			}				
		}
		System.out.print(""+"\t");
		for (int i = 0; i < conjuntoV.length; i++) {
			System.out.print(conjuntoV[i].toUpperCase()+"\t");
		}
		System.out.println("");
		for (int i = 0; i < conjuntoV.length; i++) {
			System.out.print(conjuntoV[i].toUpperCase()+"\t");
			for (int j = 0; j < conjuntoV.length; j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println("");
		}
	}
	
	private static void matrizIncidencia() {
		System.out.println("");
		System.out.println("---------MATRIZ DE INCIDÊNCIA---------");
		System.out.println("");
		int[][] matriz = new int[conjuntoV.length][origem.size()];		
		for (int i = 0; i < origem.size(); i++) {
			int x1 = -1, x2 = -1;
			for (int j = 0; j < conjuntoV.length; j++) {
				if (conjuntoV[j].equalsIgnoreCase(origem.get(i))) {
					x1 = j;
				} 
				if (conjuntoV[j].equalsIgnoreCase(destino.get(i))) {
					x2 = j;
				}
				if (x1 != -1 && x2 != -1) {				
					if (orientado == 1 && valorado == 1) {
						matriz[x1][i] += 1;
						matriz[x2][i] += 1;
					}else if (orientado == 0 && valorado == 0) {
						matriz[x1][i] += valor.get(i);
						if (x1!=x2) {
							matriz[x2][i] += valor.get(i) * -1;
						}
					}else if(valorado == 0 && orientado == 1){
						matriz[x1][i] = valor.get(i);
						matriz[x2][i] = valor.get(i);
					}else {
						matriz[x1][i] = 1;
						if (x1!=x2) {							
							matriz[x2][i] = -1;	
						}
					}
					break;
				}
			}						
		}
		System.out.print(""+"\t");		
		for (int i = 0; i < origem.size(); i++) {
			System.out.print("E"+i+"\t");
		}
		System.out.println("");
		for (int i = 0; i < conjuntoV.length; i++) {
			System.out.print(conjuntoV[i].toUpperCase()+"\t");
			for (int j = 0; j < origem.size(); j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println("");
		}
	}
}

 
