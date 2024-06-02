/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author hp
 */
public class BFS {
    private UtilsRecorridos controlMarcados;
    private List<Integer>recorrido;
    private Grafo grafo;
    
    public BFS(Grafo unGrafo,int verticeDePartida){
        this.grafo=unGrafo;
        this.grafo.validarVertice(verticeDePartida);
        controlMarcados =new UtilsRecorridos(grafo.cantidadDeVertices());
        recorrido=new ArrayList<>();
        controlMarcados.desmarcarTodos();
        ejecutarBFS(verticeDePartida);
    }
  
    public void ejecutarBFS(int verticeDePartida){                  // ASUMIR QUE HAY MINIMAMENTE UN NODO "VERTICE"
        Queue<Integer>cola=new LinkedList<>();
        cola.offer(verticeDePartida);
        controlMarcados.marcarVertice(verticeDePartida);
        do{
            int verticeEnTurno=cola.poll();
            recorrido.add(verticeEnTurno);
            Iterable<Integer>adyacentesEnTurno=grafo.adyacentesDelVertice(verticeEnTurno);
            for(Integer adyacente: adyacentesEnTurno){
                if(!controlMarcados.estaMarcado(adyacente)){
                    cola.offer(adyacente);
                    controlMarcados.marcarVertice(adyacente);
                }
            }   
                
        }while(!cola.isEmpty());   
    }
  /*  private boolean esConvexo(Grafo miGrafo){
        boolean sw=true;
        this.desmarcarTodos();
        Queue<Integer>cola=new LinkedList<>();
        for(int i=0;i<miGrafo.cantidadDeVertices()&& sw==true;i++){
            cola.offer(i);
                do{  
                    int verticeActual=cola.poll();
                    this.marcarVertice(i);
                   // Iterator<Integer>adyacentes=(Iterator<Integer>) grafo.adyacentesDelVertice(verticeActual);
                    Iterable<Integer>ad=miGrafo.adyacentesDelVertice(verticeActual);
                    for(Integer verticeEnTurno:ad){
                        if(!this.estaMarcado(verticeEnTurno)){
                            this.marcarVertice(verticeEnTurno);
                            cola.offer(verticeEnTurno);
                        }
                    }
                }while(!cola.isEmpty());
            if(this.estanTodosMarcados()){
                sw=false;
            }
           this.desmarcarTodos();
        }
        return sw;
    }*/
   
    public Iterable<Integer> mostrarRecorridoBFS(){
        return recorrido;
    }
    public boolean estanTodosMarcados(){
      
      return this.controlMarcados.estanTodosMarcados();
        
    }
    public boolean hayCaminoA(int vertice){
          grafo.validarVertice(vertice);
       return this.controlMarcados.estaMarcado(vertice);
    }
    
    
    
   /* #4  Para un grafo dirigido implementar un método o clase para determinar desde que vértices
        se puede llegar a un vértice, pero sin ejecutar más de una vez un recorrido.*/
    public List<Integer> desdeQueVerticesLlegoA(int verticeDestino){
        Queue<Integer>cola=new LinkedList<>();
        List<Integer>lista=new ArrayList<>();
        int vertices=this.grafo.cantidadDeVertices();
            for(int i=0;i<vertices;i++){
                Iterable<Integer>adyacentes=this.grafo.adyacentesDelVertice(i);
                for(Integer elemento:adyacentes){
                     if(verticeDestino==elemento){
                         cola.offer(i);
                     }
                }
            }
        while(!cola.isEmpty()){
            int i=cola.poll();
            lista.add(i);
            for(int j=0;j<vertices;j++){
                Iterable<Integer>adyacentes=this.grafo.adyacentesDelVertice(j);
                for(Integer elemento:adyacentes){
                     if(i==elemento){
                         if(!cola.contains(j))
                            cola.offer(j);
                     }
                }
            }
        }
        return lista;
    }
    /* 5 #ara un grafo dirigido solo usando como base la lógica de un recorrido (dfs o bfs) encuentre 
desde que vértices se puede llegar a un vértice a, sin importar las veces que ejecute el
recorrido elegido
*/
    public List<Integer>desdeDondePuedoLlegarAUsandoVariosRecorridos(Digrafo miDigrafo,int verticeDestino){
        BFS miBFS=new BFS(miDigrafo,0);
        Queue<Integer>cola=new LinkedList<>();
        List<Integer>lista=new ArrayList<>();
        if(miBFS.controlMarcados.estaMarcado(verticeDestino));
           lista.add(0);
       
       
          for(int i=1;i<miDigrafo.cantidadDeVertices();i++){
                miBFS=new BFS(miDigrafo,i);
                    if(miBFS.controlMarcados.estaMarcado(verticeDestino));
                            lista.add(i);
          }
         return lista;
    }
    
/* #6  Para un grafo dirigido implementar un algoritmo para encontrar si el grafo dirigido tiene 
       ciclos*/
   public boolean tieneCiclosElDigrafo(){
       int [][]matriz=new int[this.grafo.cantidadDeVertices()][this.grafo.cantidadDeVertices()];
       int n=this.grafo.cantidadDeVertices();
       for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               matriz[i][j]=0;
           }
       }
       for(int i=0;i<n;i++){
           Iterable<Integer>adyacentes=this.grafo.adyacentesDelVertice(i);
            for(Integer elemento:adyacentes){
                matriz[i][elemento]=1;
            }
       }
       for(int k=0;k<n;k++){
           for(int i=0;i<n;i++){
               for(int j=0;j<n;j++){
                   matriz[i][j]=Math.min(matriz[i][j]+matriz[i][k]*matriz[k][j], 1);
               }
           }
       }
       boolean sw=false;
        for(int i=0;i<n && sw==false;i++){
            if(matriz[i][i]!=0){
                sw=true;
            }
        }
        return sw;
   }
   
   public int cantidadDeIslasEnElGrafo(){
       if(this.controlMarcados.estanTodosMarcados()){
           return 1;
       }
       int cantidad=1;
        while(!this.controlMarcados.estanTodosMarcados()){
             int noMarcado=0;
             boolean sw=false;
                for(int i=0;i<this.grafo.cantidadDeVertices()&& sw==false;i++){
                    if(!this.controlMarcados.estaMarcado(i)){
                        noMarcado=i;
                        sw=true;
                    }
                }
              cantidad++;
              this.ejecutarBFS(noMarcado);
        }
      return cantidad;
   }
   
    
   
    
    
    
    
}
