/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.LinkedList;
import java.util.Queue;

public class CiclosEnDigrafo {

		private boolean[] marcados;
		private boolean[] visitados;
		private Digrafo miDigrafo;

		public CiclosEnDigrafo(Digrafo unDigrafo) {
				this.miDigrafo = unDigrafo;
				int n = this.miDigrafo.cantidadDeVertices();
				marcados = new boolean[n];
				visitados = new boolean[n];
				for (int i = 0; i < n; i++) {
						marcados[i] = false;
						visitados[i] = false;
				}
		}

		public boolean hayCiclos() {
				Queue<Integer> cola = new LinkedList<>();
				boolean sw = false;
				for (int i = 0; (i < this.miDigrafo.cantidadDeVertices()) && (sw == false); i++) {
						int enTurno = i;
						visitados[enTurno] = true;
						marcados[enTurno] = true;
						Iterable<Integer> adyacentes = this.miDigrafo.adyacentesDelVertice(enTurno);
						for (Integer elemento : adyacentes) { //GUARDAR LOS ADYACENTES DEL EN TURNO
								if (enTurno != elemento) {
										marcados[elemento] = true;
										cola.offer(elemento);
								}
								else {
										sw = true;
								}
						}
						while (!cola.isEmpty() && (sw == false)) {
								int elemento = cola.poll();
								this.marcados[elemento] = true;
								Iterable<Integer> lista = this.miDigrafo.adyacentesDelVertice(elemento);
								for (Integer x : lista) {
										if (x == enTurno) {
												sw = true;
										}
										else if (!marcados[x]) {
												cola.offer(x);
												marcados[x] = true;
										}
								}
						}
						for (int t = 0; t < marcados.length; t++) {
								marcados[t] = false;
						}
				}
				return sw;
		}
}
