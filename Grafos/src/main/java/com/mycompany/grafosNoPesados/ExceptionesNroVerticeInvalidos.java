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
public class ExceptionesNroVerticeInvalidos extends Exception {

    /**
     * Creates a new instance of <code>ExceptionesDeGrafos</code> without detail
     * message.
     */
    public ExceptionesNroVerticeInvalidos() {
        super("el numero de vertices es invalido");
    }

    /**
     * Constructs an instance of <code>ExceptionesDeGrafos</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionesNroVerticeInvalidos(String msg) {
        super(msg);
    }
}
