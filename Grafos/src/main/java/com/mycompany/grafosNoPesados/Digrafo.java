/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.List;

/**
 *
 * @author hp
 */
public class Digrafo extends Grafo {

              public Digrafo(int nroVertices) throws ExcepcionesGrafos {
                            super(nroVertices);
              }

              public Digrafo() {

              }

              @Override
              public void insertarArista(int verticeInicial, int verticeFinal) throws ExcepcionAristaYaExiste {
                            super.validarVertice(verticeFinal);
                            super.validarVertice(verticeInicial);
                            if (super.existeAdyacencia(verticeInicial, verticeFinal)) {
                                          throw new ExcepcionAristaYaExiste();
                            }
                            List<Integer> listaAdya = super.listaDeAdyacencia.get(verticeInicial);
                            listaAdya.add(verticeFinal);
              }

              @Override
              public int gradoDelVertice(int vertice) {
                            throw new UnsupportedOperationException("metodo no soportado"); //To change body of generated methods, choose Tools | Templates.
              }

              public int gradoDeSalida(int vertice) {
                            return super.gradoDelVertice(vertice);
              }

              public int gradoDeEntrada(int vertice) {
                            super.validarVertice(vertice);
                            int cantidadLlegada = 0;
                            for (int i = 0; i < super.listaDeAdyacencia.size(); i++) {
                                          if (super.listaDeAdyacencia.get(i).contains(vertice)) {
                                                        cantidadLlegada++;
                                          }
                            }
                            return cantidadLlegada;
              }

              @Override
              public int cantidadDeAristas() {
                            int cantidad = 0;
                            for (int i = 0; i < super.listaDeAdyacencia.size(); i++) {
                                          List<Integer> listaAdya = super.listaDeAdyacencia.get(i);
                                          cantidad = cantidad + listaAdya.size();
                            }
                            return cantidad;
              }

              @Override
              public void eliminarArista(int posicionInicial, int posicionFinal) throws ExcepcionAristaYaExiste {
                            super.validarVertice(posicionFinal);
                            super.validarVertice(posicionInicial);
                            if (!super.existeAdyacencia(posicionInicial, posicionFinal)) {
                                          throw new ExcepcionAristaYaExiste("El vertice " + posicionFinal + " no es adyacente a " + posicionInicial);
                            }
                            List<Integer> listaAdya = super.listaDeAdyacencia.get(posicionInicial);
                            int posicionAEliminar = listaAdya.indexOf(posicionFinal);
                            listaAdya.remove(posicionAEliminar);
              }

}
