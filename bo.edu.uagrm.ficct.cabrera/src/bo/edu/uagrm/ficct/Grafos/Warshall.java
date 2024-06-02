/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Grafos;

/**
 *
 * @author Asrock
 */
public class Warshall {
		
		private Grafo unGrafo;
		
		
		public Warshall(Grafo otroGrafo) {
				this.unGrafo = otroGrafo;
		}
		
		public String matrizDeCaminos() {
				int n = this.unGrafo.cantidadDeVertices();
				int [][] P = new int[n][n];
				for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
								P[i][j] = 0;
						}
				}
				
				for (int i = 0; i < n; i++) {
						Iterable<Integer> listaDeAdyacentes = this.unGrafo.adyacentesDeVertice(i);
						for (Integer adyacente : listaDeAdyacentes) {
								P[i][adyacente] = 1;
						}
				}
				
				for (int k = 0; k < n; k++) {
						for (int j = 0; j < n; j++) {
								for (int i = 0; i < n; i++) {
										P[i][j] = P[i][j] | (P[i][k] & P[k][j]);
								}
						}
				}
				
				String s = "";
				for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
								s = s + P[i][j] + " ";
						}
						s = s + "\n";
				}
				return s;
		}
		
}		
