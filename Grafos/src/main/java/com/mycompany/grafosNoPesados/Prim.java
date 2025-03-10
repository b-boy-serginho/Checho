/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.ArrayList;
import java.util.List;

public class Prim {
    private GrafoPesado miGrafo;
    private boolean[]marcados;
    public Prim(GrafoPesado unGrafo){
        this.miGrafo=unGrafo;
        marcados=new boolean[miGrafo.cantidadDeVertices()];
        for(int i=0;i<marcados.length;i++){
                marcados[i]=false;
            }
    }
    public GrafoPesado arbol() throws ExceptionesNroVerticeInvalidos, ExceptionAristaYaExiste{
        int n=this.miGrafo.cantidadDeVertices();
        GrafoPesado arbol=new GrafoPesado(n);
        marcados[0]=true;
        while(!estanTodosMarcados()){
            double infinito=Double.MAX_VALUE;
            int verticeOrigen=0;
            int destino=0;
            List<Integer>listaDeMarcados=listaDeElementosMarcados();
                for(Integer elemento:listaDeMarcados){
                    Iterable<AdyacentesConPeso>adyacentes=miGrafo.adyacentesDelVerticeConPeso(elemento);
                            for(AdyacentesConPeso adya:adyacentes){
                                    if(!marcados[adya.getIndiceVertice()] && adya.getPeso()<infinito){
                                       verticeOrigen=elemento;
                                       destino=adya.getIndiceVertice();
                                       infinito=adya.getPeso();
                                    }
                            } 
                }
               arbol.insertarArista(verticeOrigen, destino, infinito);
               marcados[destino]=true;
        }
        return arbol;
    }
    private List<Integer> listaDeElementosMarcados(){
        List<Integer>lista=new ArrayList<>();
            for(int i=0;i<marcados.length;i++){
                if(marcados[i]){
                    lista.add(i);
                }
            }
        return lista;
    }
    
    private boolean estanTodosMarcados(){
        for(int i=0;i<this.marcados.length;i++){
            if(!marcados[i]){
                return false;
            }
        }
        return true;
    }
    
}
