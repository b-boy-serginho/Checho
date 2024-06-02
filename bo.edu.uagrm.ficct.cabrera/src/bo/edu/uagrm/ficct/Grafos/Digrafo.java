/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Grafos;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Asrock
 */
public class Digrafo extends Grafo {

		public Digrafo() {
		}

		public Digrafo(int nroDeVerticesInicial) throws ExcepcionNroVerticesInvalido {
				super(nroDeVerticesInicial);
		}

		@Override
		public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
				super.validarVertice(posVerticeOrigen);
				super.validarVertice(posVerticeDestino);
				if (super.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
						throw new ExcepcionAristaYaExiste();
				}
				super.cantAristas++;
				List<Integer> adyacenciasDelOrigen = super.listaDeAdyacencias.get(posVerticeOrigen);
				adyacenciasDelOrigen.add(posVerticeDestino);
				Collections.sort(adyacenciasDelOrigen);
		}

		public int gradoVertice(int posDeVertice) {
				throw new UnsupportedOperationException("No soportado en grafos dirigidos");
		}

		public int gradoDeSalida(int posDeVertice) {
				return super.gradoDeVertice(posDeVertice);
		}

		public int gradoDeEntrada(int posDeVertice) {
				super.validarVertice(posDeVertice);
				int entradasDeVertice = 0;
				for (List<Integer> adyacentesDeUnVertice : super.listaDeAdyacencias) {
						for (Integer posAdyacente : adyacentesDeUnVertice) {
								if (posAdyacente == posDeVertice) {
										entradasDeVertice++;
								}
						}
				}
				return entradasDeVertice;
		}

		@Override
		public void eliminarVertice(int posVerticeAEliminar) {
				validarVertice(posVerticeAEliminar);
				this.listaDeAdyacencias.remove(posVerticeAEliminar);
				for (List<Integer> adyacentesDeUnVertice : this.listaDeAdyacencias) {
						int posicionDeVerticeEnAdy = adyacentesDeUnVertice.indexOf(posVerticeAEliminar);
						if (posicionDeVerticeEnAdy >= 0) {
								adyacentesDeUnVertice.remove(posicionDeVerticeEnAdy);
						}
						for (int i = 0; i < adyacentesDeUnVertice.size(); i++) {
								int posicionAdyacente = adyacentesDeUnVertice.get(i);
								if (posicionAdyacente > posVerticeAEliminar) {
										adyacentesDeUnVertice.set(i, posicionAdyacente - 1);
								}
						}
				}
		}

		@Override
		public void eliminarArista(int verticeOrigen, int verticeDestino) {

				validarVertice(verticeOrigen);
				validarVertice(verticeDestino);
				if (existeAdyacencia(verticeOrigen, verticeDestino)) {
						List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(verticeOrigen);
						int posicionVerticeDestino = adyacentesDelVerticeOrigen.indexOf(verticeDestino);
						adyacentesDelVerticeOrigen.remove(posicionVerticeDestino);
				}
		}

		public void mostrarDigrafo() {
				for (int i = 0; i < this.cantidadDeVertices(); i++) {
						System.out.print("Vertice [" + i + "] : |");
						for (Integer adyacente : this.listaDeAdyacencias.get(i)) {
								System.out.print(adyacente + "|");
						}
						System.out.println();
				}
		}

}
