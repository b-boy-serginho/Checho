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
public class DigrafoDebilmenteConexo {

		private DFS recorrido;
		private Digrafo grafo;

		public DigrafoDebilmenteConexo(Digrafo unGrafo) {
				this.grafo = unGrafo;
				this.recorrido = new DFS(unGrafo, 0);
		}

		public boolean esDebilmenteConexo() {
			for (int i = 0; i < this.grafo.cantidadDeVertices() && !this.recorrido.hayCaminoATodos(); i++) {
				if (!this.recorrido.hayCaminoAlVertice(i)) {
					if (hayAdyacentesMarcados(i)) {
						this.recorrido.realizarRecorridoDFS(i);
					}
				}
			}
                        return this.recorrido.hayCaminoATodos();
		}

		public boolean hayAdyacentesMarcados(int verticeNoMarcado) {
				Iterable<Integer> adyacentes = this.grafo.listaDeAdyacencias.get(verticeNoMarcado);
				for (Integer adyacente : adyacentes) {
						if (this.recorrido.hayCaminoAlVertice(adyacente)) {
								return true;
						}
				}
				return false;
		}
}
