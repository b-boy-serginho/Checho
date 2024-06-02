/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.ArrayList;
import java.util.List;

public class DebilmenteConexo {

		private Grafo miGrafo;
		private BFS bfs;

		public DebilmenteConexo(Grafo unGrafo) {
				this.miGrafo = unGrafo;
				bfs = new BFS(miGrafo, 0);
		}

		public int cantidadDeIslasEnDigrafo() {
				if (bfs.estanTodosMarcados()) {
						return 1;
				}
				int cantidad = 1;
				while (!bfs.estanTodosMarcados()) {
						List<Integer> noMarcados = new ArrayList<>();
						for (int i = 0; i < this.miGrafo.cantidadDeVertices(); i++) {
								if (!bfs.hayCaminoA(i)) {
										noMarcados.add(i);
								}
						}
						int inicial = noMarcados.get(0);
						boolean hay = false;
						for (int i = 0; i < noMarcados.size() && hay == false; i++) {
								inicial = noMarcados.get(i);
								if (this.hayAdyacentesMarcados(inicial)) {
										hay = true;
										inicial = noMarcados.get(i);
								}
						}
						if (hay) {
								bfs.ejecutarBFS(inicial);
						}
						else {
								cantidad++;
								bfs.ejecutarBFS(inicial);
						}
				}
				return cantidad;
		}

		private boolean hayAdyacentesMarcados(int vertice) {
				Iterable<Integer> lista = this.miGrafo.adyacentesDelVertice(vertice);
				for (Integer elemento : lista) {
						if (bfs.hayCaminoA(elemento)) {
								return true;
						}
				}
				return false;
		}

		public boolean esDebilmenteConexo() {
				boolean sw = false;
				if (this.cantidadDeIslasEnDigrafo() == 1) {
						for (int i = 0; i < (this.miGrafo.cantidadDeVertices()) && (sw == false); i++) {
								bfs = new BFS(this.miGrafo, i);
								if (!bfs.estanTodosMarcados()) {
										sw = true;
								}
						}
				}
				return sw;
		}

}
