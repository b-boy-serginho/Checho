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
public class ExceptionAristaNoExiste extends Exception {

    /**
     * Creates a new instance of <code>ExceptionAristaNoExiste</code> without
     * detail message.
     */
    public ExceptionAristaNoExiste() {
        super("Arista no Existe , No hay adyacencia para eliminar");
    }

    /**
     * Constructs an instance of <code>ExceptionAristaNoExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionAristaNoExiste(String msg) {
        super(msg);
    }
}
