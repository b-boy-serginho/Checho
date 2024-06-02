/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grafosNoPesados;


public class AdyacentesConPeso implements Comparable<AdyacentesConPeso> {
    
    private int indiceVertice;
    private double peso;
    
    public AdyacentesConPeso(int vertice){
        this.indiceVertice=vertice;
    }
    public AdyacentesConPeso(int vertice,double peso){
        this.indiceVertice=vertice;
        this.peso=peso;
    }
    
    public int getIndiceVertice(){
        return this.indiceVertice;
    }
    public double getPeso(){
        return this.peso;
    }
    
    public void setIndiceVertice(int vertice){
        this.indiceVertice=vertice;
    }
    
    public void setPeso(double peso){
        this.peso=peso;
    }
    
    
    
    

    @Override
    public int compareTo(AdyacentesConPeso vert) {
        Integer esteVertice=this.indiceVertice;
        Integer otroVertice=vert.getIndiceVertice();
        return esteVertice.compareTo(otroVertice);
    }

    @Override
    public boolean equals(Object otro) {
        if(otro==null){
            return false;
        }
        if(getClass()!=otro.getClass()){
            return false;
        }
        AdyacentesConPeso other=(AdyacentesConPeso)otro;
        return this.indiceVertice==other.indiceVertice;
        
    }

    @Override
    public int hashCode() {
        int hash=3;
        hash=67*hash+this.indiceVertice;
        return hash;
    }
    
    
    
    
    
    
    
}
