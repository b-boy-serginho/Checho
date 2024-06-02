/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;

/**
 *
 * @author hp
 */
public class FuertementeConexo {
    private BFS miBfs;
    private Grafo miGrafo;
    
    public FuertementeConexo(Grafo unGrafo){
        this.miGrafo=unGrafo;
    }
    public boolean esFuertementoConexo(){
        int n=this.miGrafo.cantidadDeVertices();
        boolean sw=true;
            for(int i=0;i<n && sw==true;i++){
                miBfs=new BFS(miGrafo,i);
                    if(!miBfs.estanTodosMarcados()){
                        sw=false;
                    }
            }
           return sw;
    }
    
}
