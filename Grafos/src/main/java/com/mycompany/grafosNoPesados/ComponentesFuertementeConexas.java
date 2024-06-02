/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.ArrayList;
import java.util.List;

public class ComponentesFuertementeConexas {

		private DFS dfs;
		private Grafo miGrafo;

		public ComponentesFuertementeConexas(Grafo unGrafo) {
				this.miGrafo = unGrafo;
		}

		public List<Integer> listaComponentesConexas() {
				List<Integer> lista = new ArrayList<>();
				for (int i = 0; i < miGrafo.cantidadDeVertices(); i++) {
						dfs = new DFS(miGrafo, i);
						if (dfs.hayCaminoATodos()) {
								lista.add(i);
						}

				}
				return lista;
		}
}
