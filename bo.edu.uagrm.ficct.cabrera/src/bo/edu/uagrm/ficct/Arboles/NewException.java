/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Arboles;


public class NewException extends Exception {

    public NewException() {
        super("Clave no existe en el arbol");
    }

    public NewException(String message) {
        super(message);
    }
}
