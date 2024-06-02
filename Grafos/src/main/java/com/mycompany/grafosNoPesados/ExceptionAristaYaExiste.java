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
public class ExceptionAristaYaExiste extends Exception {

    /**
     * Creates a new instance of <code>ExceptionAristaYaExiste</code> without
     * detail message.
     */
    public ExceptionAristaYaExiste() {
        super("La arista ya existe en el grafo");
    }

    /**
     * Constructs an instance of <code>ExceptionAristaYaExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionAristaYaExiste(String msg) {
        super(msg);
    }
}
