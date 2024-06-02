/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Asrock
 */
public class Grafo {
    protected int cantAristas;
    protected List<List<Integer>> listaDeAdyacencias;
		//linea 20: Lista de vertices, que en cada posicion contiene dentro una lista de todos los adyacentes de un vertice

		/*					  __
						 /    |
						 |   |0|->|0,1,2..|		cada posicion de la lista hace referencia a un vertice del grafo
	  [listaDeAdyacencias]  ->   |   |1|->|0,1,2..|		
						 |   |2|->|0,1,2..|
						 |   |3|->|0,1,2..|
						 \__ | 
		 */
    public Grafo() {
	this.listaDeAdyacencias = new ArrayList<>();
	this.cantAristas = 0;
    }

    public Grafo(int nroDeVerticesInicial) throws ExcepcionNroVerticesInvalido {
	if (nroDeVerticesInicial < 0) {
		throw new ExcepcionNroVerticesInvalido();
	}
	this.listaDeAdyacencias = new ArrayList<>();
	this.cantAristas = 0;
	for (int i = 0; i < nroDeVerticesInicial; i++) {
		this.listaDeAdyacencias.add(new ArrayList<>());
	}
    }

    public void insertarVertice() {
	this.listaDeAdyacencias.add(new ArrayList<>());
    }

    public int cantidadDeAristas() {
				/*int cantAristas = 0;
		int cantLazos = 0;
		for (int i = 0; i < this.listaDeAdyacencias.size(); i++) {
			List<Integer> adyacentesDeUnVertice = this.listaDeAdyacencias.get(i);
			for (Integer posAdyacente : adyacentesDeUnVertice) {
				if (i == posAdyacente) {
					cantLazos++;
				} else {
					cantAristas++;
				}
			}
		}
		cantAristas = (cantAristas / 2) + cantLazos;
		return cantAristas;*/

	return this.cantAristas;
    }

    public int cantidadDeVertices() {
	return listaDeAdyacencias.size();
    }

		protected void validarVertice(int posicionDeVertice) {
				if (posicionDeVertice < 0 || posicionDeVertice >= cantidadDeVertices()) {
						throw new IllegalArgumentException("El vertice " + posicionDeVertice
						 + "no pertenece al grafo");
				}
		}

		public void insertarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
				validarVertice(posVerticeOrigen);  // verificar si existe posVerticeOrigen
				validarVertice(posVerticeDestino);	// verificar si existe posVerticeDestino
				if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) { // existeAdyacencia entre verticeOrigen y verticeDestino?
						throw new ExcepcionAristaYaExiste();
				}
				this.cantAristas++;
				List<Integer> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
				//linea 80 -> sacamos la lista de adyacencias del verticeOrigen y la metemos dentro de la lista adyacentesDelOrigen
				// Lista<Integer> adyacentesDelOrigen -> esta lista guardara todas las adyacencias del verticeOrigen
				adyacentesDelOrigen.add(posVerticeDestino); //guardamos dentro de la lista que contiene las adyacencias Del verticeOrigen, el verticeDestino
				Collections.sort(adyacentesDelOrigen); // ordenamos todas los adyacenctes del vertice origen de menor a mayor
				if (posVerticeOrigen != posVerticeDestino) {
						// hacer lo mismo que hicimos con las adyacencias del verticeOrigen, pero ahora con el verticeDestino
						List<Integer> adyacenciasDelDestino = this.listaDeAdyacencias.get(posVerticeDestino);
						adyacenciasDelDestino.add(posVerticeOrigen);
						Collections.sort(adyacenciasDelDestino);
				}
		}

		public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
				validarVertice(posVerticeOrigen);
				validarVertice(posVerticeDestino);
				List<Integer> adyacenciasDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
				return adyacenciasDelOrigen.contains(posVerticeDestino);
		}

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

		public int gradoDeVertice(int posDeVertice) {
				validarVertice(posDeVertice);
				List<Integer> adyacenciasDelVertice = this.listaDeAdyacencias.get(posDeVertice);
				return adyacenciasDelVertice.size();
		}

		public Iterable<Integer> adyacentesDeVertice(int posDeVertice) {
				validarVertice(posDeVertice);
				List<Integer> adyacenciasDelVertice = this.listaDeAdyacencias.get(posDeVertice);
				Iterable<Integer> it = adyacenciasDelVertice;
				return it;
		}

		public void eliminarArista(int verticeOrigen, int verticeDestino) throws ExcepcionAristaYaExiste {
				/*/ TAREA ---------------------
		1ro  validar primero si existen ambos vertices en el grafo
		2do preguntar si existe adyacencia entre ambos vertices
			-si existe: eliminar adyacencia
				
			-si no, no hacer nada
				 */
				validarVertice(verticeOrigen);
				validarVertice(verticeDestino);
				if (!existeAdyacencia(verticeOrigen, verticeDestino)) {
						throw new ExcepcionAristaYaExiste("No existe la arista en el Grafo");
				}
				List<Integer> adyacentesDelVerticeOrigen = this.listaDeAdyacencias.get(verticeOrigen);
				int posicionVerticeDestino = adyacentesDelVerticeOrigen.indexOf(verticeDestino);
				adyacentesDelVerticeOrigen.remove(posicionVerticeDestino);
				if (verticeOrigen != verticeDestino) {
						List<Integer> adyacentesDelVerticeDestino = this.listaDeAdyacencias.get(verticeDestino);
						int posicionVerticeOrigen = adyacentesDelVerticeDestino.indexOf(verticeOrigen);
						adyacentesDelVerticeDestino.remove(posicionVerticeOrigen);
				}

		}

		public void mostrarGrafo() {
				for (int i = 0; i < this.cantidadDeVertices(); i++) {
						System.out.print("Vertice [" + i + "] : |");
						for (Integer adyacente : this.listaDeAdyacencias.get(i)) {
								System.out.print(adyacente + "|");
						}
						System.out.println();
				}
		}
		
}
