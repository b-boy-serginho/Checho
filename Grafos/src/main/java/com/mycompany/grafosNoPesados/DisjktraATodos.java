/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.ArrayList;
import java.util.List;

public class DisjktraATodos {

		private double[] costos;
		private int[] predecesores;
		private boolean[] marcados;

		private DigrafoPesado miDigrafo;
		private static final double INFINITO = Double.MAX_VALUE;

		public DisjktraATodos(DigrafoPesado unGrafo) {
				this.miDigrafo = unGrafo;
				int n = this.miDigrafo.cantidadDeVertices();
				costos = new double[n];
				marcados = new boolean[n];
				predecesores = new int[n];
				for (int i = 0; i < n; i++) {
						costos[i] = INFINITO;
						marcados[i] = false;
						predecesores[i] = -1;

				}

		}

		public String caminosMasCortos(int verticeInicio) throws ExceptionAristaNoExiste {
				costos[verticeInicio] = 0;
				marcados[verticeInicio] = true;
				int enTurno = verticeInicio;

				while (!this.estanMarcadosTodos() && (costos[enTurno] != INFINITO)) {
						Iterable<Integer> lista = this.miDigrafo.adyacentesDelVertice(enTurno);
						for (Integer elemento : lista) {
								if (!this.marcados[elemento]) {
										double distancia = this.miDigrafo.peso(enTurno, elemento);
										if ((distancia + (costos[enTurno])) < (costos[elemento])) {
												costos[elemento] = distancia + (costos[enTurno]);
												predecesores[elemento] = enTurno;

										}
								}
						}
						marcados[enTurno] = true;
						enTurno = noMarcadoDeMenorCosto();
				}
				String s = "";
				List<Double> camino = new ArrayList<>();
				for (int i = 0; i < this.marcados.length; i++) {
						if (costos[i] != INFINITO) {
								s = s + i + "::";
								int elemento = predecesores[i];
								while (elemento != -1) {
										s = s + "<<-- " + elemento;
										elemento = predecesores[elemento];
								}
								s = s + "<<-- " + "COSTO: " + costos[i];
								s = s + "\n";
						}
				}
				return s;
		}

		private boolean estanMarcadosTodos() {
				for (int i = 0; i < this.marcados.length; i++) {
						if (!marcados[i]) {
								return false;
						}
				}
				return true;
		}

		private int noMarcadoDeMenorCosto() {
				int vertice = 0;
				double max = INFINITO;
				for (int i = 0; i < marcados.length; i++) {
						if ((!marcados[i]) && (costos[i] <= max)) {
								max = costos[i];
								vertice = i;
						}
				}
				return vertice;
		}

}
