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
public class DFSCiclo {

		private RecorridoUtil controlDeMarcado;
		private Grafo unGrafo;
		private Grafo grafoAuxiliar;

		public DFSCiclo(Grafo unGrafo) throws ExcepcionNroVerticesInvalido {
				this.unGrafo = unGrafo;
				controlDeMarcado = new RecorridoUtil(unGrafo.cantidadDeVertices());
				grafoAuxiliar = new Grafo(unGrafo.cantidadDeVertices());
		}

		public boolean encontrarCiclo() throws ExcepcionNroVerticesInvalido, ExcepcionAristaYaExiste {

				boolean cicloEncontrado = recorrer(0);
				for (int i = 1; i < unGrafo.listaDeAdyacencias.size() && !cicloEncontrado; i++) {
						if (!this.controlDeMarcado.estanTodosMarcados()) {
								if (!this.controlDeMarcado.estaMarcado(i)) {
										cicloEncontrado = recorrer(i);
								}
						}
						else {
								return false;
						}
						/*if (!this.controlDeMarcado.estaMarcado(i)) {
								if (recorrer(i)) {
										return true;
								}
								if (this.controlDeMarcado.estanTodosMarcados()) {
										return false;
								}
						}*/
				}
				return cicloEncontrado;
		}

		public boolean recorrer(int posVertice) throws ExcepcionAristaYaExiste {
				controlDeMarcado.marcar(posVertice);
				Iterable<Integer> itAdyacentesEnTurno = unGrafo.adyacentesDeVertice(posVertice);
				for (Integer adyacenteEnTurno : itAdyacentesEnTurno) {
						if (!this.controlDeMarcado.estaMarcado(adyacenteEnTurno)) {
								grafoAuxiliar.insertarArista(posVertice, adyacenteEnTurno);
								boolean recorrido = recorrer(adyacenteEnTurno);
						}
						else {
								if (!grafoAuxiliar.existeAdyacencia(posVertice, adyacenteEnTurno)) {
										return true;
								}
						}
				}
				return false;
		}

		

}
