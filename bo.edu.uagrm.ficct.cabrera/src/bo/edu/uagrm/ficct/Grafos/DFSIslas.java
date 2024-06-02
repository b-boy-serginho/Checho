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
public class DFSIslas {
		private DFS recorrido;
		private Grafo grafo;

		public DFSIslas(Grafo unGrafo) {
				this.grafo = unGrafo;
				this.recorrido = new DFS(unGrafo, 0);
		}

		public int cantidadDeIslas() {
			int cantidad = 1;
			for (int i = 0; i < this.grafo.cantidadDeVertices() && !this.recorrido.hayCaminoATodos(); i++) {
				if (!this.recorrido.hayCaminoAlVertice(i)) {
					if (!hayAdyacentesMarcados(i)) {
						this.recorrido.realizarRecorridoDFS(i);
						cantidad++;
					}
                                        this.recorrido.realizarRecorridoDFS(i);	//--> AUMENTAR PARA DIGRAFOS
				}
			}
                    return cantidad;
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
