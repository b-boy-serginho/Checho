/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.ArrayList;
import java.util.List;

public class OrdenamientoTopologico {
    private Digrafo miDigrafo;
    public OrdenamientoTopologico(Digrafo unDigrafo){
        this.miDigrafo=unDigrafo;
    }
    public List<Integer> ordenamiento(){
       
       Warshall verifica=new Warshall(miDigrafo);
       DebilmenteConexo debil=new DebilmenteConexo(miDigrafo);
      if(verifica.hayCiclos() || !debil.esDebilmenteConexo()){
        throw new IllegalArgumentException("no se puede realizar el ordenamiento sobre este digrafo");
      }
      List<Integer>lista=new ArrayList<>();
        int nVertices=miDigrafo.cantidadDeVertices();
        List<Boolean>marcados=new ArrayList<>();
        List<Integer>entradas=new ArrayList<>();
        List<Integer>elementos=new ArrayList<>();
         for(int i=0;i<miDigrafo.cantidadDeVertices();i++){
             marcados.add(Boolean.FALSE);
             int nEntrada=miDigrafo.gradoDeEntrada(i);
             entradas.add(nEntrada);
             elementos.add(i);
             
         }
         while(!estanTodosMarcados(marcados)){
             int vertice=0;
            for(int i=0;i<entradas.size();i++){
             if((entradas.get(i)==0) && (!marcados.get(i))){
                 vertice=i;
                 break;
             }
            }
         lista.add(vertice);
         marcados.set(vertice, Boolean.TRUE);
         for(int i=0;i<elementos.size();i++){
             int destino=elementos.get(i);
                if(miDigrafo.existeAdyacencia(vertice, destino)){
                    entradas.set(i, entradas.get(i)-1);
                }
            }        
        }
      return lista;
    }  
    
   private boolean estanTodosMarcados(List<Boolean>lista){
       for(int i=0;i<lista.size();i++){
           if(lista.get(i)==false){
               return false;
           }
       }
      return true;
   }
}
