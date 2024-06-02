/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.LinkedList;
import java.util.Queue;


public class MostrarIslasEnDigrafo {
    private boolean []marcados;
    private Digrafo miDigrafo;
        public MostrarIslasEnDigrafo(Digrafo unDigrafo){
            this.miDigrafo=unDigrafo;
            marcados=new boolean[this.miDigrafo.cantidadDeVertices()];
                for(int i=0;i<this.miDigrafo.cantidadDeVertices();i++){
                    marcados[i]=false;
                }
        }
     private boolean estanTodosMarcados(){
         for(int i =0;i<this.marcados.length;i++){
             if(!marcados[i]){
                 return false;
             }
         }
         
        return true; 
     }   
     public String mostrarIslas(){
         String cadena="";
         Queue<Integer>cola=new LinkedList<>();
         cola.offer(0);
         marcados[0]=true;
         while(!estanTodosMarcados()){
                while(!cola.isEmpty()){
                    int elemento=cola.poll();
                    cadena+=elemento+";";
                    marcados[elemento]=true;
                    Iterable<Integer>adyacentes=miDigrafo.adyacentesDelVertice(elemento);
                        for(Integer ad:adyacentes){
                            if(!marcados[ad]){
                                cola.offer(ad);
                                marcados[ad]=true;
                            }
                        }
                }
                if(hayNoMarcadosConAdyacentesMarcados()!=-1){
                    cola.offer(hayNoMarcadosConAdyacentesMarcados());   
                }else{
                    if(!this.estanTodosMarcados()){
                        for(int i=0;i<marcados.length;i++){
                                if(!this.marcados[i]){
                                    cola.offer(i);
                                   break;
                                }
                        }
                         cadena+="---";
                    }
                }
         }
      return cadena;
     }
     
    private int hayNoMarcadosConAdyacentesMarcados(){
            for(int i=0;i<marcados.length;i++){
                    if(!marcados[i]){
                        Iterable<Integer>lista=this.miDigrafo.adyacentesDelVertice(i);
                            for(Integer ele:lista){
                                if(marcados[ele]){
                                    return i;
                                }
                            }
                    }
            }
           return -1;
    }
        
}
