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
public class GrafoConexo {
		private final DFS recorrido;
		
		public GrafoConexo(Grafo unGrafo) {
				this.recorrido = new DFS(unGrafo, 0);
		}
		
		public boolean esConexo() {
				return this.recorrido.hayCaminoATodos();
		}
		
		

		
}
