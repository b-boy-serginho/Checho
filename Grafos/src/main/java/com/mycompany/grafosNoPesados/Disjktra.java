/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.*;

public class Disjktra {

		private double[] costos;
		private int[] predecesores;
		private boolean[] marcados;
		private DigrafoPesado miDigrafo;
		private static final double INFINITO = Double.MAX_VALUE;

		private List<List<Integer>> rutas;

		public Disjktra(DigrafoPesado unGrafo) {
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

		public List<Double> caminosMasCortos(int verticeInicio, int verticeDestino) 
                                                            throws ExceptionAristaNoExiste {
				costos[verticeInicio] = 0;
				marcados[verticeInicio] = true;
				int enTurno = verticeInicio;

				while ((!marcados[verticeDestino]) && (costos[enTurno] != INFINITO)) {
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
				List<Double> camino = new ArrayList<>();

				if (costos[verticeDestino] != INFINITO) {
						int elemento = predecesores[verticeDestino];
						while (elemento != -1) {
								camino.add((double) elemento);
								elemento = predecesores[elemento];
						}
				}

				camino.add(costos[verticeDestino]);
				List<Double> salida = new ArrayList<>();
				for (int i = camino.size() - 1; i >= 0; i--) {
						salida.add(camino.get(i));
				}
				return salida;
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
