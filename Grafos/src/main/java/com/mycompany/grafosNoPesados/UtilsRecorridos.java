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
public class UtilsRecorridos {
   
       private List<Boolean>marcados;
       private int nroVertices;
       
   public UtilsRecorridos(int nVertices){
   
       this.nroVertices=nVertices;
      // this.desmarcarTodos();
   
   }
   public int cantidadVertices(){
       return nroVertices;
   }
   public void desmarcarTodos(){
        marcados=new ArrayList<>();
    
        for(int i=0;i<this.nroVertices;i++){
            marcados.add(Boolean.FALSE);
        }
    }  
    
     public void marcarVertice(int posAMarcar){
        marcados.set(posAMarcar, Boolean.TRUE);
    }
     
     
     public boolean estaMarcado(int posVertice){
        return this.marcados.get(posVertice);
    }
     
     public boolean estanTodosMarcados(){
        for(Boolean marcado:this.marcados){
            if(!marcado){
                return false;
            }
        }
        return true;
    }
    public ArrayList<Integer> listaDeNoMarcados(){
        ArrayList<Integer>noMarcados=new ArrayList<>();
        for(int i=0;i<marcados.size();i++){
            if(marcados.get(i)==false){
                noMarcados.add(i);
            
            }
        }
        return noMarcados;
    }
}
