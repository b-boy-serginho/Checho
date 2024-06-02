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
public class ExcepcionesGrafos extends Exception{
    
    public ExcepcionesGrafos(){
        super("Cantidad de vertices debe ser positiva");
    }
    public ExcepcionesGrafos(String mensaje){
    
        super(mensaje);
    }
    
}
