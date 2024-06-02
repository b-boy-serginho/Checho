/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Arboles;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asrock
 * @param <K>
 * @param <V>
 */
public interface IArbolBusqueda<K extends Comparable<K>, V> {

	void insertar(K claveAInsertar, V valorAsociado);
        void insertarRecursivo(K claveAInsertar, V valorAInsertar);
	V eliminar(K claveAEliminar) throws NewException;
	V buscar(K claveABuscar);
	boolean contiene(K claveAVerificar);
	int size();
	int sizeIterativo();	
	K claveMinima();	
	K claveMaxima();
	int altura();
	int alturaIterativo();
	void vaciar();
	boolean esArbolVacio();
	int nivel();
	int cantidadDeHojas();
	int cantidadDeHijosDerechos();
	List<K> recorridoEnInOrden();
	List<K> recorridoEnInOrdenIterativo();
	List<K> recorridoEnPreOrden();
	List<K> recorridoEnPreOrdenIterativo();
	List<K> recorridoEnPostOrden();
	List<K> recorridoEnPostOrdenIterativo();
	List<K> recorridoPorNiveles();
	void mostrar();
        String dibujarRecorrido(ArrayList<K> recorrido);

}
