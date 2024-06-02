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
public class GrafoPesado {

		protected List<List<AdyacenteConPeso>> listaDeAdyacencias;

		public GrafoPesado() {
				this.listaDeAdyacencias = new ArrayList<>();

		}

		public GrafoPesado(int nroDeVertices) {
				this();
				for (int i = 0; i < nroDeVertices; i++) {
						this.insertarVertice();
				}
		}

		public void insertarVertice() {
				List<AdyacenteConPeso> adyacenciaDelNuevoVertice = new ArrayList<>();
				this.listaDeAdyacencias.add(adyacenciaDelNuevoVertice);
		}

		public int cantidadDeAristas() {
		int cantAristas = 0;
		int cantLazos = 0;
		for (int i = 0; i < this.listaDeAdyacencias.size(); i++) {
			List<AdyacenteConPeso> adyacentesDeUnVertice = this.listaDeAdyacencias.get(i);
			for (AdyacenteConPeso Adyacente : adyacentesDeUnVertice) {
				if (i == Adyacente.getIndiceVertice()) {
					cantLazos++;
				} else {
					cantAristas++;
				}
			}
		}
		cantAristas = (cantAristas / 2) + cantLazos;
		return cantAristas;
				
	} 
		public int cantidadDeVertices() {
				return listaDeAdyacencias.size();
		}

		protected void validarVertice(int posicionDeVertice) {
				if (posicionDeVertice < 0
				 || posicionDeVertice >= cantidadDeVertices()) {
						throw new IllegalArgumentException("El vertice " + posicionDeVertice + "no pertenece al grafo");
				}
		}

		public void insertarArista(int posVerticeOrigen, int posVerticeDestino, double peso)
		 throws ExcepcionAristaYaExiste {
				if (this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
						throw new ExcepcionAristaYaExiste();
				}

				List<AdyacenteConPeso> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
				AdyacenteConPeso adyacenteDestino = new AdyacenteConPeso(posVerticeDestino, peso);
				adyacentesDelOrigen.add(adyacenteDestino);
				Collections.sort(adyacentesDelOrigen);
				if (posVerticeOrigen != posVerticeDestino) {
						List<AdyacenteConPeso> adyacentesDelDestino = this.listaDeAdyacencias.get(posVerticeDestino);
						AdyacenteConPeso adyacenteOrigen = new AdyacenteConPeso(posVerticeOrigen, peso);
						adyacentesDelDestino.add(adyacenteOrigen);
						Collections.sort(adyacentesDelDestino);
				}
		}

		public boolean existeAdyacencia(int posVerticeOrigen, int posVerticeDestino) {
				validarVertice(posVerticeOrigen);
				validarVertice(posVerticeDestino);
				List<AdyacenteConPeso> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
				
				for (int i = 0; i < adyacentesDelOrigen.size(); i++) {
						AdyacenteConPeso adyacente = adyacentesDelOrigen.get(i);
						if (adyacente.getIndiceVertice() == posVerticeDestino) {
								return true;
						}
				}

				return false;
				/*
				AdyacenteConPeso adyacenteDestino = new AdyacenteConPeso(posVerticeDestino);
				return adyacentesDelOrigen.contains(adyacenteDestino);  */
		}

		public void eliminarVertice(int posVertice) {
				validarVertice(posVertice);
				this.listaDeAdyacencias.remove(posVertice);
				for (int i = 0; i < this.cantidadDeVertices(); i++) {
						List<AdyacenteConPeso> adyacentesEnTurno = this.listaDeAdyacencias.get(i);
						AdyacenteConPeso unAdyacenteAuxiliar = new AdyacenteConPeso(i);
						adyacentesEnTurno.remove(unAdyacenteAuxiliar);
						for (int j = 0; j < adyacentesEnTurno.size(); j++) {
								AdyacenteConPeso adyacenteActual = adyacentesEnTurno.get(j);
								if (adyacenteActual.getIndiceVertice() > posVertice) {
										adyacenteActual.setIndiceVertice(adyacenteActual.getIndiceVertice() - 1);
								}
						}
				}
		}

		public int gradoDeVertice(int posDeVertice) {
				validarVertice(posDeVertice);
				List<AdyacenteConPeso> adyacenciasDelVertice = this.listaDeAdyacencias.get(posDeVertice);
				return adyacenciasDelVertice.size();
		}

		public Iterable<Integer> adyacentesDeVertice(int posDeVertice) {
				validarVertice(posDeVertice);
				List<AdyacenteConPeso> adyacentesDelVertice = this.listaDeAdyacencias.get(posDeVertice);
				List<Integer> listaDePosDeVertices = new ArrayList<>();
				for (AdyacenteConPeso unAdyacente : adyacentesDelVertice) {
						listaDePosDeVertices.add(unAdyacente.getIndiceVertice());
				}
				Iterable iterableDeAdyacentes = (Iterable) listaDePosDeVertices;
				return iterableDeAdyacentes;

		}

		public double peso(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
				if (!this.existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
						throw new ExcepcionAristaYaExiste();
				}
				List<AdyacenteConPeso> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen);
				AdyacenteConPeso adyacenteDestino = new AdyacenteConPeso(posVerticeDestino);
				int posDeDestino = adyacentesDelOrigen.indexOf(adyacenteDestino);
				adyacenteDestino = adyacentesDelOrigen.get(posDeDestino);
				return adyacenteDestino.getPeso();
		}

		// ---------------------------------------------TAREA ---------------------------------------
		public void eliminarArista(int posVerticeOrigen, int posVerticeDestino) throws ExcepcionAristaYaExiste {
				validarVertice(posVerticeOrigen);
				validarVertice(posVerticeDestino);
				if (!existeAdyacencia(posVerticeOrigen, posVerticeDestino)) {
						throw new ExcepcionAristaYaExiste("La arista no existe en el Grafo");
				}
				List<AdyacenteConPeso> adyacentesDelOrigen = this.listaDeAdyacencias.get(posVerticeOrigen); // creamos cajita para contener nodos del origen
				AdyacenteConPeso adyacenteDestino = new AdyacenteConPeso(posVerticeDestino); // creamos cajita para contener nodo con posicion del Destino
				int posicionVerticeDestino = adyacentesDelOrigen.indexOf(adyacenteDestino); // creamos variable para contener posicion donde se encuentra la cajita adyacenteDestino entre los nodos del origen
				adyacentesDelOrigen.remove(posicionVerticeDestino); // eliminamos de la lista adyacentesDelOrigen la posicionVerticeDestino
				if (posVerticeOrigen != posVerticeDestino) {
						List<AdyacenteConPeso> adyacentesDelDestino = this.listaDeAdyacencias.get(posVerticeDestino);
						AdyacenteConPeso adyacenteOrigen = new AdyacenteConPeso(posVerticeOrigen);
						int posicionVerticeOrigen = adyacentesDelDestino.indexOf(adyacenteOrigen);
						adyacentesDelDestino.remove(posicionVerticeOrigen);
				}
		}
		
		public String mostrarGrafoPesado() {
				String s = "|  ";

				Double[][] matriz = new Double[this.cantidadDeVertices()][this.cantidadDeVertices()];
				for (int i = 0; i < this.cantidadDeVertices(); i++) {
						s = s + i + " | ";
				}
				s = s + "\n";
				for (int i = 0; i < this.cantidadDeVertices(); i++) {
						for (int j = 0; j < this.cantidadDeVertices(); j++) {
								matriz[i][j] = (double) 0;
						}
				}

				for (int i = 0; i < this.listaDeAdyacencias.size(); i++) {
						List<AdyacenteConPeso> adyacentes = listaDeAdyacencias.get(i);
						for (AdyacenteConPeso elemento : adyacentes) {
								matriz[i][elemento.getIndiceVertice()] = elemento.getPeso();
						}
				}
				for (int i = 0; i < this.cantidadDeVertices(); i++) {
						s = s + i + "|";
						for (int j = 0; j < this.cantidadDeVertices(); j++) {
								s = s + matriz[i][j] + " ";
						}
						s = s + "\n";
				}
				return s;
		}
		
}
