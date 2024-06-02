/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

public class Warshall {

		private Grafo miGrafo;

		public Warshall(Grafo unGrafo) {
				this.miGrafo = unGrafo;
		}

		public String matrizDeCaminos() {
				int n = miGrafo.cantidadDeVertices();
				int[][] p = new int[n][n];
				for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
								p[i][j] = 0;
						}
				}
				for (int i = 0; i < n; i++) {
						Iterable<Integer> lista = miGrafo.adyacentesDelVertice(i);
						for (Integer elemento : lista) {
								p[i][elemento] = 1;
						}
				}
				for (int k = 0; k < n; k++) {
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
								p[i][j] = Math.min(p[i][j] + p[i][k] * p[k][j], 1);

						}
					} 
				}
				String s = "";
				for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
								s = s + p[i][j];
						}
						s = s + "\n";
				}
				return s;
		}

		public boolean hayCiclos() {
				int n = miGrafo.cantidadDeVertices();
				int[][] p = new int[n][n];
				for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
								p[i][j] = 0;
						}
				}
				for (int i = 0; i < n; i++) {
						Iterable<Integer> lista = miGrafo.adyacentesDelVertice(i);
						for (Integer elemento : lista) {
								p[i][elemento] = 1;
						}
				}
				for (int k = 0; k < n; k++) {
						for (int i = 0; i < n; i++) {
								for (int j = 0; j < n; j++) {
										p[i][j] = Math.min(p[i][j] + p[i][k] * p[k][j], 1);

								}
						}
				}
				boolean sw = false;
				for (int i = 0; i < n; i++) {
						if (p[i][i] != 0) {
								return true;
						}
				}
				return false;
		}

}
