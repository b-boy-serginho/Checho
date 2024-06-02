/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Arboles;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Asrock
 * @param <K>
 * @param <V>
 */
public class NodoMVias<K,V> {
    
    private List<K> listaDeClaves;
    private List<V> listaDeValores;
    private List<NodoMVias<K,V>> listaDeHijos;
    
    public static NodoMVias nodoVacio(){
        return null;
    }
    
    public static Object DatoVacio(){
        return null;
    }
    public static boolean esNodoVacio(NodoMVias nodoMVias){
        return nodoMVias == NodoMVias.nodoVacio();
    }
    
    public NodoMVias(int orden){
        this.listaDeClaves = new ArrayList<>();
        this.listaDeValores = new ArrayList<>();
        this.listaDeHijos = new LinkedList<>();
        
        for(int i=0; i < orden-1; i++){
            this.listaDeClaves.add((K) NodoMVias.DatoVacio());
            this.listaDeValores.add((V) NodoMVias.DatoVacio());
            this.listaDeHijos.add(NodoMVias.nodoVacio());
        }
        this.listaDeHijos.add(NodoMVias.nodoVacio());
    }
    
    public NodoMVias(int orden, K clave, V valor){
        this(orden);
        
        this.listaDeClaves.set(0, clave);
        this.listaDeValores.set(0, valor);
    }

    public K getClave(int posicion) {
        return this.listaDeClaves.get(posicion);
    }

    public void setClave(int posicion,K clave) {
        this.listaDeClaves.set(posicion, clave);
    }

    public V getValor(int posicion) {
        return this.listaDeValores.get(posicion);
    }

    public void setValor(int posicion,V valor) {
        this.listaDeValores.set(posicion, valor);
    }

    public NodoMVias<K, V> getHijo(int posicion) {
        return this.listaDeHijos.get(posicion);
    }

    public void setHijo(int posicion,NodoMVias<K,V> Hijo) {
        this.listaDeHijos.set(posicion, Hijo);
    }
    
    public boolean esHijoVacio(int posicion){
        return NodoMVias.esNodoVacio(this.getHijo(posicion));
    }
    
    public boolean esClaveVacia(int posicion){
        return NodoMVias.DatoVacio() == this.getClave(posicion);
    }
    
    public boolean esHoja(){
        /*  for(int i=0; i < this.listaDeHijos.size();i++){
                if(!this.esHijoVacio(i)){
                    return false;
                }    
            }*/
        for(NodoMVias<K,V> unHijo:this.listaDeHijos){
            if(!NodoMVias.esNodoVacio(unHijo)){
                return false;
            }
        }
        return true;
    }
    
    public boolean estanClavesLlenas(){
        for(int i = this.listaDeClaves.size()-1; i >= 0; i--){
            if(this.esClaveVacia(i)){
                return false;
            }
        }
        return true;
    }
    
    public int nroDeClavesNoVacias(){
        int cantidad = 0;
        for(K unaClave:this.listaDeClaves){
            if(unaClave != NodoMVias.DatoVacio()){
                cantidad++;
            }
        }
        return cantidad;
    }
    
     public int cantidadDeClavesVacias(){
        int cantidad = 0;
        //recorremos en el nodo
        for(int i=0; i<this.listaDeClaves.size(); i++){
            if(this.esClaveVacia(i)){
                cantidad++;
            }
        }
        return cantidad;
    }
    
     public int cantidadDeHijosVacios(){
        int cantidad = 0;
        //recorremos en todo el arbol
        for(int i=0; i<this.listaDeHijos.size(); i++){
            if(this.esHijoVacio(i)){
                cantidad++;
            }
        }
        return cantidad;
    }
    
    
    
    
    public boolean hayClavesNoVacias(){
        return this.nroDeClavesNoVacias() != 0;
    }
}
