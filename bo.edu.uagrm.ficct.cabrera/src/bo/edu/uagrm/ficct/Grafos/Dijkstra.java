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
public class Dijkstra {

		private double[] costos;
		private int[] predecesores;
		//private boolean[] marcados;
		private RecorridoUtil listaMarcados;
		private DigrafoPesado miDigrafo;
		private static final double INFINITO = Double.MAX_VALUE;

		public Dijkstra(DigrafoPesado unGrafo) {
				this.miDigrafo = unGrafo;
				int n = this.miDigrafo.cantidadDeVertices();
				costos = new double[n];
				//marcados = new boolean[n];
				listaMarcados = new RecorridoUtil(n);
				predecesores = new int[n];
				for (int i = 0; i < n; i++) {
						costos[i] = INFINITO;
						//marcados[i] = false;
						predecesores[i] = -1;

				}

		}

		public String costoMinimo(int verticeOrigen, int verticeDestino) throws ExcepcionAristaYaExiste {
				costos[verticeOrigen] = 0;
				int verticeNoMarcado;
				do {
						verticeNoMarcado = this.noMarcadoDeMenorCosto();
						listaMarcados.marcar(verticeNoMarcado);
						Iterable<Integer> adyacentesNoMarcados = miDigrafo.adyacentesDeVertice(verticeNoMarcado);
						for (Integer adyacente : adyacentesNoMarcados) {
								if (!listaMarcados.estaMarcado(adyacente)) {
										double pesoAdyacente = this.miDigrafo.peso(verticeNoMarcado, adyacente);
										if (costos[adyacente] > (costos[verticeNoMarcado] + pesoAdyacente)) {
												costos[adyacente] = costos[verticeNoMarcado] + pesoAdyacente;
												predecesores[adyacente] = verticeNoMarcado;
										}
								}
						}
				}
				while (!listaMarcados.estaMarcado(verticeDestino));
				
				String s = "|";
				for (int i = 0; i < costos.length; i++) {
						s = s + costos[i] + "|";
				}
				return s;
		}

		public int noMarcadoDeMenorCosto() {
				int vertice = 0;
				double maximo = INFINITO;
				for (int i = 0; i < miDigrafo.cantidadDeVertices(); i++) {
						if ((!listaMarcados.estaMarcado(i)) && (costos[i] <= maximo)) {
								maximo = costos[i];
								vertice = i;
						}
				}
				return vertice;
		}

}
