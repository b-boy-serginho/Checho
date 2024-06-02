/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class Grafo {
    //ATRIBUTOS
    protected List<List<Integer>> listaDeAdyacencia;

    //CONSTRUCTOR
    public Grafo() {
	listaDeAdyacencia = new ArrayList<>();
    }

    //CONSTRUCTOR SOBRECARGADO
    public Grafo(int nroVertices) throws ExcepcionesGrafos {
	if (nroVertices < 0) {
		throw new ExcepcionesGrafos("nro de vertices invalidos");
	}
	this.listaDeAdyacencia = new ArrayList<>();

	for (int i = 0; i < nroVertices; i++) {
            this.listaDeAdyacencia.add(new ArrayList<>());
	}

    }

    public void insertarVertice() {
	this.listaDeAdyacencia.add(new ArrayList<>());
    }

    public int cantidadDeAristas() {
	int cantidadAristas = 0;
	int cantidadDeLazos = 0;
	for (int i = 0; i < this.listaDeAdyacencia.size(); i++) {
	List<Integer> listaAdya = this.listaDeAdyacencia.get(i);
            for (Integer ad : listaAdya) {
                if (ad == i) {
                    cantidadDeLazos++;
                }
                else {
                    cantidadAristas++;
                }
            }
	}
	cantidadAristas = (cantidadAristas / 2) + cantidadDeLazos;
	return cantidadAristas;

    }

    public int cantidadDeVertices() {
	return this.listaDeAdyacencia.size();
    }

    public void validarVertice(int posicion) {
	if (posicion < 0 || posicion >= this.cantidadDeVertices()) {
            throw new IllegalArgumentException("El vertice" + posicion + "no pertenece al grafo");
        }   
    }

    public void insertarArista(int verticeInicial, int verticeFinal) throws ExcepcionAristaYaExiste {
	this.validarVertice(verticeInicial);
	this.validarVertice(verticeFinal);
	if (this.existeAdyacencia(verticeInicial, verticeFinal)) {
		throw new ExcepcionAristaYaExiste("La arista ya existe en el grafo");
	}

	List<Integer> listaAdyacente = this.listaDeAdyacencia.get(verticeInicial);
	listaAdyacente.add(verticeFinal);

	if (verticeInicial != verticeFinal) {
            listaAdyacente = this.listaDeAdyacencia.get(verticeFinal);
            listaAdyacente.add(verticeInicial);
	}
    }

    public boolean existeAdyacencia(int origen, int destino) {
	this.validarVertice(origen);
	this.validarVertice(destino);
	List<Integer> listaAdyacente = this.listaDeAdyacencia.get(origen);
	return listaAdyacente.contains(destino);
    }

    public void eliminarVertice(int posVerticeAEliminar) {
	this.validarVertice(posVerticeAEliminar);
	this.listaDeAdyacencia.remove(posVerticeAEliminar);
	for (List<Integer> listaAdya : this.listaDeAdyacencia) {
            int posicionAEliminarDeAdyacencia = listaAdya.indexOf(posVerticeAEliminar);
            if (posicionAEliminarDeAdyacencia >= 0) {
		listaAdya.remove(posicionAEliminarDeAdyacencia);

            }
	for (int i = 0; i < listaAdya.size(); i++) {    //por que el for este si el remove hace todo el trabajo
	int posicionAdyacente = listaAdya.get(i);
            if (posicionAdyacente > posVerticeAEliminar) {
		listaAdya.set(i, posicionAdyacente - 1);
            }
						}
	}
    }

    public void eliminarArista(int posicionInicial, int posicionFinal) throws ExcepcionAristaYaExiste {
	this.validarVertice(posicionInicial);
	this.validarVertice(posicionFinal);
	if (!this.existeAdyacencia(posicionInicial, posicionFinal)) {
            throw new ExcepcionAristaYaExiste("El vertice " + posicionFinal + " no es adyacente a " + posicionInicial);
	}
	List<Integer> listaAdya = this.listaDeAdyacencia.get(posicionInicial);
	int posicionAEliminar = listaAdya.indexOf(posicionFinal);
	listaAdya.remove(posicionAEliminar);
	listaAdya = this.listaDeAdyacencia.get(posicionFinal);
	posicionAEliminar = listaAdya.indexOf(posicionInicial);
	listaAdya.remove(posicionAEliminar);
    }

    public int gradoDelVertice(int vertice) {
	this.validarVertice(vertice);
	List<Integer> adyacentes = this.listaDeAdyacencia.get(vertice);
	return adyacentes.size();
    }

    public Iterable<Integer> adyacentesDelVertice(int vertice) {
	this.validarVertice(vertice);
	List<Integer> listaAdya = this.listaDeAdyacencia.get(vertice);
	Iterable<Integer> it = listaAdya;
	return it;
    }

    public String mostraElGrafo() {
	String s = "|";
	int[][] matriz = new int[this.cantidadDeVertices()][this.cantidadDeVertices()];
	for (int i = 0; i < this.cantidadDeVertices(); i++) {
            s = s + i + "|";
	}
	s = s + "\n";
	for (int i = 0; i < this.cantidadDeVertices(); i++) {
            for (int j = 0; j < this.cantidadDeVertices(); j++) {
                matriz[i][j] = 0;
            }
	}

	for (int i = 0; i < this.listaDeAdyacencia.size(); i++) {
            List<Integer> adyacentes = listaDeAdyacencia.get(i);
            for (Integer elemento : adyacentes) {
		matriz[i][elemento] = 1;
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
