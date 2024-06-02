/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author hp
 */
public class DFS {
		// private List<Boolean>marcados;

		private List<Integer> recorrido;
		private Grafo grafo;
		private UtilsRecorridos controlMarcados;

		public DFS(Grafo unGrafo, int verticeDePartida) {
				this.grafo = unGrafo;
				grafo.validarVertice(verticeDePartida);
				recorrido = new ArrayList<>();
				controlMarcados = new UtilsRecorridos(grafo.cantidadDeVertices());
				controlMarcados.desmarcarTodos();
				continuarDFS(verticeDePartida);

		}

		public void continuarDFS(int verticeDePartida) {
				controlMarcados.marcarVertice(verticeDePartida);
				recorrido.add(verticeDePartida);
				Iterable<Integer> listaAdya = grafo.adyacentesDelVertice(verticeDePartida);
				for (Integer enTurno : listaAdya) {
						if (!controlMarcados.estaMarcado(enTurno)) {
								continuarDFS(enTurno);
						}
				}

		}

		public boolean BuscarCamino(int verticeDePartida, int verticeDestino) {
				controlMarcados.marcarVertice(verticeDePartida);
				Iterable<Integer> listaAdya = grafo.adyacentesDelVertice(verticeDePartida);
				for (Integer enTurno : listaAdya) {
						while (!controlMarcados.estanTodosMarcados()) {
								ArrayList<Integer> nuevaLista = (ArrayList) listaAdya;
								if (nuevaLista.contains(verticeDestino)) {
										return true;
								}
								break;
						}
						if (!controlMarcados.estaMarcado(enTurno)) {
								continuarDFS(enTurno);
						}
				}

				return false;
		}

		public int cantidadDeIslasEnDigrafo(Digrafo unDigrafo) {
				DFS primerDfs = new DFS(unDigrafo, 0);
				int cantidadDeIslas = 0;
				if (primerDfs.hayCaminoATodos()) {
						cantidadDeIslas++;
						return cantidadDeIslas;
				}
				else {
						while (!primerDfs.hayCaminoATodos()) {
								ArrayList<Integer> listaDeNoMarcados = primerDfs.listaDeNoMarcados();
								boolean encontro = false;
								int primerNoMarcado = listaDeNoMarcados.get(0);
								for (int i = 0; i < (listaDeNoMarcados.size()) && (encontro == false); i++) {
										if (primerDfs.hayAdyacentesMarcado(unDigrafo, listaDeNoMarcados.get(i))) {
												primerNoMarcado = listaDeNoMarcados.get(i);
												encontro = true;
										}
								}
								if (encontro == true) {
										primerDfs.continuarDFS(primerNoMarcado);
								}
								else {
										cantidadDeIslas++;
										primerDfs.continuarDFS(primerNoMarcado);
								}
						}
						cantidadDeIslas++;
				}
				return cantidadDeIslas;
		}

		public boolean hayAdyacentesMarcado(Digrafo unDigrafo, int vertice) {
				Iterable<Integer> listaDeAdyacentes = unDigrafo.adyacentesDelVertice(vertice);
				for (Integer b : listaDeAdyacentes) {
						if (controlMarcados.estaMarcado(b)) {
								return true;
						}

				}
				return false;
		}

		public String matrizDeCaminos(Digrafo unDigrafo) {
				byte[][] matriz = new byte[unDigrafo.cantidadDeVertices()][unDigrafo.cantidadDeVertices()];
				for (int i = 0; i < unDigrafo.cantidadDeVertices(); i++) {
						for (int j = 0; j < unDigrafo.cantidadDeVertices(); j++) {

								matriz[i][j] = 0;

						}

				}
				for (int i = 0; i < unDigrafo.cantidadDeVertices(); i++) {
						Iterable<Integer> ad = unDigrafo.adyacentesDelVertice(i);
						for (Integer a : ad) {
								matriz[i][a] = 1;
						}
				}

				String s = "";
				for (int i = 0; i < unDigrafo.cantidadDeVertices(); i++) {
						for (int j = 0; j < unDigrafo.cantidadDeVertices(); j++) {
								s = s + matriz[i][j];
						}
						s = s + "\n";

				}

				return s;
		}

		public int ciclosEnDigrafo(Digrafo unDigrafo) {
				return this.ciclosEnDigrafo(unDigrafo, 0);
		}

		private int ciclosEnDigrafo(Digrafo unDigrafo, int verticiIncial) {
				int ciclos = 0;
				controlMarcados.desmarcarTodos();
				Queue<Integer> cola = new LinkedList<>();
				cola.offer(verticiIncial);
				controlMarcados.marcarVertice(verticiIncial);

				do {
						int actual = cola.poll();
						Iterable<Integer> listaDeAdyacentes = unDigrafo.adyacentesDelVertice(actual);
						for (Integer a : listaDeAdyacentes) {
								if (!controlMarcados.estaMarcado(a)) {
										cola.offer(a);
										controlMarcados.marcarVertice(a);
								}
								else {
										ciclos++;
								}
						}
				}
				while (!cola.isEmpty());
				return ciclos;
		}

		public boolean unDigrafoEsDebilmenteConexo(Digrafo unDigrafo) {
				DFS primerDfs = new DFS(unDigrafo, 0);
				if (primerDfs.hayCaminoATodos()) {
						return false;
				}
				else {
						if (this.cantidadDeIslasEnDigrafo(unDigrafo) > 1) {
								return false;
						}

				}
				return true;
		}

		public boolean estanMarcadosLosAdyacentes(Iterable<Integer> adyacentes, ArrayList<Integer> Marcados) {

				for (Integer a : adyacentes) {
						if (Marcados.contains(a)) {
								return true;
						}
				}
				return false;
		}

		public void marcar(int vertice) {
				this.controlMarcados.marcarVertice(vertice);
		}

		public int cantidadDeIslasEnUnGrafo(Grafo unGrafo) {
				int cantidad = 0;
				DFS recor = new DFS(unGrafo, 0);
				if (recor.hayCaminoATodos()) {
						cantidad++;
						return cantidad;
				}
				else {
						while (!recor.hayCaminoATodos()) {

								ArrayList<Integer> noMarcados = recor.listaDeNoMarcados();
								int inicial = noMarcados.get(0);
								recor.continuarDFS(inicial);
								cantidad++;
						}
						cantidad++;
				}
				return cantidad;
		}

		public int cantidadDeIslasEnUnDigrafo2() {
				DFS miDfs = new DFS(this.grafo, 0);
				if (miDfs.hayCaminoATodos()) {
						return 1;
				}
				int cantidad = 1;
				while (!miDfs.hayCaminoATodos()) {
						List<Integer> noMarcados = new ArrayList<>();
						for (int i = 0; i < this.grafo.cantidadDeVertices(); i++) {
								if (!miDfs.hayCaminoA(i)) {
										noMarcados.add(i);
								}
						}
						int inicial = noMarcados.get(0);
						boolean hay = false;
						for (int i = 0; i < noMarcados.size() && (hay == false); i++) {
								inicial = noMarcados.get(i);
								Iterable<Integer> adyacentes = this.grafo.adyacentesDelVertice(inicial);
								for (Integer elemento : adyacentes) {
										if (miDfs.hayCaminoA(elemento)) {
												hay = true;
												inicial = noMarcados.get(i);
										}
								}
						}
						if (hay) {
								miDfs.continuarDFS(inicial);
						}
						else {
								cantidad++;
								miDfs.continuarDFS(inicial);
						}
				}
				return cantidad;
		}

		public Iterable<Integer> mostrarRecorridoBFS() {
				return recorrido;

		}

		private boolean hayCaminoA(int vertice) {
				grafo.validarVertice(vertice);
				return controlMarcados.estaMarcado(vertice);
		}

		public boolean hayCaminoATodos() {
				return controlMarcados.estanTodosMarcados();
		}

		public ArrayList<Integer> listaDeNoMarcados() {
				ArrayList<Integer> listaNoMarcados = new ArrayList<>();
				for (int i = 0; i < controlMarcados.cantidadVertices(); i++) {
						if (!hayCaminoA(i)) {
								listaNoMarcados.add(i);
						}
				}
				return listaNoMarcados;
		}

		public ArrayList<Integer> listaDeMarcados() {
				ArrayList<Integer> noMarcados = new ArrayList<>();
				for (int i = 0; i < controlMarcados.cantidadVertices(); i++) {
						if (this.hayCaminoA(i)) {
								noMarcados.add(i);

						}
				}
				return noMarcados;
		}

}
