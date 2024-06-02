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
public class ExcepcionAristaYaExiste extends Exception {
    
    public ExcepcionAristaYaExiste(){
        super("El vertico ya esta en el grafo");
    }
    public ExcepcionAristaYaExiste(String sms){
        super(sms);
    }
    
}
