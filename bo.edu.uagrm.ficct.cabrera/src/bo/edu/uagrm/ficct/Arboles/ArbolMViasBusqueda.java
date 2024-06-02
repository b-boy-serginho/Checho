/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Arboles;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Asrock
 * @param <K>
 * @param <V>
 */
public class ArbolMViasBusqueda<K extends Comparable<K>, V> implements
		IArbolBusqueda<K, V> {

	protected NodoMVias<K, V> raiz;
	protected int orden;
	protected static final int POSICION_NO_VALIDA = -1;
	protected static final int ORDEN_MINIMO = 3;

	public ArbolMViasBusqueda() {
		this.orden = ArbolMViasBusqueda.ORDEN_MINIMO;
	}

	public ArbolMViasBusqueda(int orden) throws OrdenInvalidoExcepcion {
		if (orden < ArbolMViasBusqueda.ORDEN_MINIMO) {
			throw new OrdenInvalidoExcepcion();
		}
		this.orden = orden;
	}
        
@Override
        public String dibujarRecorrido(ArrayList<K> recorrido){return "";}
        
        
	@Override
	public void insertar(K claveAInsertar, V valorAsociado) {
		if (claveAInsertar == null) {
			throw new IllegalArgumentException("Clave invalida, no se aceptan claves vacias");
		}
		if (valorAsociado == null) {
			throw new IllegalArgumentException("Valor invalido, no se aceptan valores nulos");
		}
		if (this.esArbolVacio()) {
			this.raiz = new NodoMVias<>(this.orden, claveAInsertar, valorAsociado);
			return;
		}

		NodoMVias<K, V> nodoActual = this.raiz;

		do {
			int posicionDeClave = this.obtenerPosicionDeClave(nodoActual,
					claveAInsertar);
			if (posicionDeClave != ArbolMViasBusqueda.POSICION_NO_VALIDA) {
				nodoActual.setValor(posicionDeClave, valorAsociado);
				nodoActual = NodoMVias.nodoVacio();
			} else if (nodoActual.esHoja()) {
				if (nodoActual.estanClavesLlenas()) {
					int posicionPorDondeBajar = obtenerPosicionPorDondeBajar(
							nodoActual, claveAInsertar);
					NodoMVias<K, V> nuevoNodo = new NodoMVias<>(this.orden,
							claveAInsertar, valorAsociado);
					nodoActual.setHijo(posicionPorDondeBajar, nuevoNodo);

				} else {
					insertarClaveYValorOrdenadamenteEnNodo(nodoActual, claveAInsertar, valorAsociado);
				}
				nodoActual = NodoMVias.nodoVacio();
			} else {
				int posicionPorDondeBajar = obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
				if (nodoActual.esHijoVacio(posicionPorDondeBajar)) {
					NodoMVias<K, V> nuevoNodo = new NodoMVias<>(this.orden, claveAInsertar, valorAsociado);
					nodoActual.setHijo(posicionPorDondeBajar, nuevoNodo);
					nodoActual = NodoMVias.nodoVacio();
				} else {
					nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
				}
			}
		} while (!NodoMVias.esNodoVacio(nodoActual));

	}

	protected int obtenerPosicionDeClave(NodoMVias<K, V> nodoActual,
			K claveABuscar) {
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			K claveEnTurno = nodoActual.getClave(i);
			if (claveABuscar.compareTo(claveEnTurno) == 0) {
				return i;
			}
		}
		return ArbolMViasBusqueda.POSICION_NO_VALIDA;
	}

	protected int obtenerPosicionPorDondeBajar(NodoMVias<K, V> nodoActual, K claveAInsertar) {
		int i = 0;
		boolean llegoAlFinal = false;
		while (i < nodoActual.nroDeClavesNoVacias()) {
			K claveActual = nodoActual.getClave(i);
			if (claveActual.compareTo(claveAInsertar) < 0) {
				i++;
			} else {
				break;
			}
		}
		if (nodoActual.getClave(nodoActual.nroDeClavesNoVacias()
				- 1).compareTo(claveAInsertar) < 0) {
			return nodoActual.nroDeClavesNoVacias();
		}
		return i;
	}

	protected void insertarClaveYValorOrdenadamenteEnNodo(NodoMVias<K, V> nodoActual,
			K claveAInsertar, V valorAsociado) {
		boolean insertado = false;
		for (int i = nodoActual.nroDeClavesNoVacias() - 1; i >= 0 && !insertado; i--) {
			K claveActual = nodoActual.getClave(i);
			if (claveAInsertar.compareTo(claveActual) > 0) { // claveAInsertar > claveActual
				nodoActual.setClave(i + 1, claveAInsertar);
				nodoActual.setValor(i + 1, valorAsociado);
				insertado = true;
				//return;
			} else {
				nodoActual.setClave(i + 1, claveActual);
				nodoActual.setValor(i + 1, nodoActual.getValor(i));
			}
		}
		if (!insertado) {
			nodoActual.setClave(0, claveAInsertar);
			nodoActual.setValor(0, valorAsociado);
		}
	}
        //----------------------------------------------------------------------------
        //----------------------------------------------------------------------------
        @Override
	public void insertarRecursivo(K claveAInsertar, V valorAsociado) {
            this.CantDatosVacios1();
        }
        
        //----------------------------------------------------------------------------
        //----------------------------------------------------------------------------
	@Override
	public V eliminar(K claveAEliminar) throws NewException {
		if (claveAEliminar == null) {
			throw new IllegalArgumentException("Clave invalida,no se aceptan claves nulas");
		}
		V valorAsociado = buscar(claveAEliminar);
		if (valorAsociado == null) {
			throw new NewException();
		}
		this.raiz = eliminar(this.raiz, claveAEliminar);
		return valorAsociado;
	}

	private NodoMVias<K, V> eliminar(NodoMVias<K, V> nodoActual, K claveAEliminar) {
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			K claveEnTurno = nodoActual.getClave(i);
			if (claveAEliminar.compareTo(claveEnTurno) == 0) {
				if (nodoActual.esHoja()) {
					eliminarClaveDePosicion(nodoActual, i);
					if (!nodoActual.hayClavesNoVacias()) {
						return NodoMVias.nodoVacio();
					} else {
						return nodoActual;
					}
				} else {
					K claveDeReemplazo;
					if (hayHijosNoVaciosMasAdelante(nodoActual, i)) {
						claveDeReemplazo = obtenerSucesorInOrden(nodoActual, claveAEliminar);  //obtenerSucesorInOrden(nodoActual, i);
					} else {
						claveDeReemplazo = obtenerPredecesorInOrden(nodoActual, claveAEliminar);  //obtenerPredecesorInOrden(nodoActual, i);
					}

					V valorDeReemplazo = buscar(claveDeReemplazo);
					nodoActual = eliminar(nodoActual, claveDeReemplazo);
					nodoActual.setClave(i, claveDeReemplazo);
					nodoActual.setValor(i, valorDeReemplazo);
					return nodoActual;
				}
			} // Fin if (claveAEliminar.compareTo(claveEnTurno) == 0)

			if (claveAEliminar.compareTo(claveEnTurno) < 0) {
				NodoMVias<K, V> supuestoNuevoHijo = eliminar(nodoActual.getHijo(i), claveAEliminar);
				nodoActual.setHijo(i, supuestoNuevoHijo);
				return nodoActual;
			}
		} // Fin For
		NodoMVias<K, V> supuestoNuevoHijo = eliminar(nodoActual.getHijo(
				nodoActual.nroDeClavesNoVacias()), claveAEliminar);
		nodoActual.setHijo(nodoActual.nroDeClavesNoVacias(), supuestoNuevoHijo);
		return nodoActual;
	}

	protected void eliminarClaveDePosicion(NodoMVias<K, V> nodoActual, int posicion) {
		for (int i = posicion; i <= nodoActual.nroDeClavesNoVacias() - 1; i++) {
			nodoActual.setClave(i, nodoActual.getClave(i + 1));
			nodoActual.setValor(i, nodoActual.getValor(i + 1));
		}
	}

	protected boolean hayHijosNoVaciosMasAdelante(NodoMVias<K, V> nodoActual, int posicion) {
		boolean existe = false;
		for (int i = posicion + 1; i <= nodoActual.nroDeClavesNoVacias() && !existe; i++) {
			if (!nodoActual.esHijoVacio(i)) {
				existe = true;
			}
		}
		return existe;
	}

	private K obtenerPredecesorInOrden(NodoMVias<K, V> nodoActual, K claveAEliminar) {   //int i) {
		int posicion = obtenerPosicionDeClave(nodoActual, claveAEliminar);
		K claveDeRetorno = (K) NodoMVias.DatoVacio();
		if (!nodoActual.esHijoVacio(posicion)) {
			NodoMVias<K, V> nodoAuxiliar = nodoActual.getHijo(posicion);
			while (!NodoMVias.esNodoVacio(nodoAuxiliar)) {
				claveDeRetorno = nodoAuxiliar.getClave(nodoAuxiliar.nroDeClavesNoVacias() - 1);
				nodoAuxiliar = nodoAuxiliar.getHijo(0);
			}
			return claveDeRetorno;
		} else {
			return nodoActual.getClave(posicion-1);
		}
	}

	private K obtenerSucesorInOrden(NodoMVias<K, V> nodoActual, K claveAEliminar) {   //int i) {
		int posicion = obtenerPosicionDeClave(nodoActual, claveAEliminar);
		K claveDeRetorno = (K) NodoMVias.DatoVacio();
		if (!nodoActual.esHijoVacio(posicion + 1)) {
			//int posicion = obtenerPosicionPorDondeBajar(nodoActual, claveAEliminar);

			NodoMVias<K, V> nodoAuxiliar = nodoActual.getHijo(posicion + 1);

			while (!NodoMVias.esNodoVacio(nodoAuxiliar)) {
				claveDeRetorno = nodoAuxiliar.getClave(0);
				nodoAuxiliar = nodoAuxiliar.getHijo(0);
			}
			return claveDeRetorno;
		} else {
			return nodoActual.getClave(posicion + 1);
		}

	}
//----------------------------------------------------------------------------
	@Override
	public V buscar(K claveABuscar) {
		if (!this.esArbolVacio()) {
			NodoMVias<K, V> nodoActual = this.raiz;
			do {
				boolean cambiaElNodoActual = false;
				for (int i = 0; i < nodoActual.nroDeClavesNoVacias()
						&& !cambiaElNodoActual; i++) {
					K claveDelNodoActual = nodoActual.getClave(i);
					if (claveABuscar.compareTo(claveDelNodoActual) == 0) {
						return nodoActual.getValor(i);
					}
					if (claveABuscar.compareTo(claveDelNodoActual) < 0) {
						nodoActual = nodoActual.getHijo(i);
						cambiaElNodoActual = true;
					}
				}
				if (!cambiaElNodoActual) {
					nodoActual = nodoActual.getHijo(
							nodoActual.nroDeClavesNoVacias());
				}
			} while (!NodoMVias.esNodoVacio(nodoActual));
		}
		return null;
	}

	@Override
	public boolean contiene(K claveAVerificar) {
		return this.buscar(claveAVerificar) != null;
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public int sizeIterativo() {
		int cantidadDeNodos = 0;
		if (!this.esArbolVacio()) {
			Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
			colaDeNodos.offer(this.raiz);

			do {
				NodoMVias<K, V> nodoActual = colaDeNodos.poll();
				cantidadDeNodos++;
				for (int i = 0; i < orden; i++) {
					if (!nodoActual.esHijoVacio(i)) {
						colaDeNodos.offer(nodoActual.getHijo(i));
					}
				}
			} while (!colaDeNodos.isEmpty());
		}
		return cantidadDeNodos;
	}

	@Override
	public int altura() {
		return altura(this.raiz);
	}

	protected int altura(NodoMVias<K, V> nodoActual) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return 0;
		}
		int alturaMayor = 0;
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias() + 1; i++) {
			int alturaDeHijoActual = altura(nodoActual.getHijo(i));
			if (alturaDeHijoActual > alturaMayor) {
				alturaMayor = alturaDeHijoActual;
			}
		}
		return alturaMayor + 1;
	}

	@Override
	public int alturaIterativo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void vaciar() {
		this.raiz = NodoMVias.nodoVacio();
	}

	@Override
	public boolean esArbolVacio() {
		return NodoMVias.esNodoVacio(this.raiz);
	}

	@Override
	public K claveMinima() {
		if (esArbolVacio()) {
			return null;
		}
		NodoMVias<K, V> nodoActual = this.raiz;
		NodoMVias<K, V> nodoAnterior = NodoMVias.nodoVacio();
		while (!NodoMVias.esNodoVacio(nodoActual)) {
			nodoAnterior = nodoActual;
			nodoActual = nodoActual.getHijo(0);
		}
		return nodoAnterior.getClave(0);
	}

	@Override
	public K claveMaxima() {
		if (esArbolVacio()) {
			return null;
		}
		NodoMVias<K, V> nodoActual = this.raiz;
		K claveMayor = (K) NodoMVias.DatoVacio();
		while (!NodoMVias.esNodoVacio(nodoActual)) {
			claveMayor = nodoActual.getClave(nodoActual.nroDeClavesNoVacias() - 1);
			nodoActual = nodoActual.getHijo(nodoActual.nroDeClavesNoVacias());
		}
		return claveMayor;
	}

	@Override
	public int nivel() {
		return altura() - 1;
	}

	@Override
	public int cantidadDeHojas() {
		return cantidadDeHojas(this.raiz);
	}

	private int cantidadDeHojas(NodoMVias<K, V> nodoActual) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return 0;
		}
		if (nodoActual.esHoja()) {
			return 1;
		}
		int cantidad = 0;
		for (int i = 0; i < this.orden; i++) {
			cantidad += cantidadDeHojas(nodoActual.getHijo(i));
		}
		return cantidad;
	}

	public int cantidadDeHojasDesdeNivel(int nivelBase) {
		return cantidadDeHojasDesdeNivel(this.raiz, nivelBase, 0);
	}

	private int cantidadDeHojasDesdeNivel(NodoMVias<K, V> nodoActual, int nivelBase,
			int nivelActual) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return 0;
		}
		if (nivelActual >= nivelBase) {
			if (nodoActual.esHoja()) {
				return 1;
			}
		} else {
			if (nodoActual.esHoja()) {
				return 0;
			}
		}
		int cantidad = 0;
		for (int i = 0; i < this.orden; i++) {
			cantidad += cantidadDeHojasDesdeNivel(nodoActual.getHijo(i), nivelBase, nivelActual + 1);
		}
		return cantidad;
	}

	@Override
	public int cantidadDeHijosDerechos() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public int cantidadDatosVacios() {
		return cantidadDatosVacios(this.raiz);
	}

	private int cantidadDatosVacios(NodoMVias<K, V> nodoActual) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return 0;
		}
		int cantidad = 0;
		for (int i = 0; i < this.orden - 1; i++) {
			cantidad += cantidadDatosVacios(nodoActual.getHijo(i));
			if (nodoActual.esClaveVacia(i)) {
				cantidad++;
			}
		}
		cantidad += cantidadDatosVacios(nodoActual.getHijo(this.orden - 1));
		return cantidad;
	}

	@Override
	public List<K> recorridoEnInOrden() {
		List<K> recorrido = new ArrayList<>();
		recorridoEnInOrden(this.raiz, recorrido);
		return recorrido;
	}

	private void recorridoEnInOrden(NodoMVias<K, V> nodoActual,
			List<K> recorrido) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return;
		}
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			recorridoEnInOrden(nodoActual.getHijo(i), recorrido);
			recorrido.add(nodoActual.getClave(i));
		}
		recorridoEnInOrden(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()),
				recorrido);
	}

	@Override
	public List<K> recorridoEnInOrdenIterativo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<K> recorridoEnPreOrden() {
		List<K> recorrido = new ArrayList<>();
		recorridoEnPreOrden(this.raiz, recorrido);
		return recorrido;
	}

	private void recorridoEnPreOrden(NodoMVias<K, V> nodoActual,
			List<K> recorrido) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return;
		}
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			recorrido.add(nodoActual.getClave(i));
			recorridoEnPreOrden(nodoActual.getHijo(i), recorrido);
		}
		recorridoEnPreOrden(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()),
				recorrido);

	}

	@Override
	public List<K> recorridoEnPostOrden() {
		List<K> recorrido = new ArrayList<>();
		recorridoEnPostOrden(this.raiz, recorrido);
		return recorrido;
	}

	private void recorridoEnPostOrden(NodoMVias<K, V> nodoActual,
			List<K> recorrido) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return;
		}
		recorridoEnPostOrden(nodoActual.getHijo(0), recorrido);
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			recorridoEnPostOrden(nodoActual.getHijo(i + 1), recorrido);
			recorrido.add(nodoActual.getClave(i));
		}
	}

	@Override
	public List<K> recorridoEnPostOrdenIterativo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<K> recorridoPorNiveles() {
		List<K> recorrido = new ArrayList<>();
		if (!this.esArbolVacio()) {
			Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
			colaDeNodos.offer(this.raiz);
			do {
				NodoMVias<K, V> nodoActual = colaDeNodos.poll();
				for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
					recorrido.add(nodoActual.getClave(i));
					if (!nodoActual.esHijoVacio(i)) {
						colaDeNodos.offer(nodoActual.getHijo(i));
					}
				}
				if (!nodoActual.esHijoVacio(nodoActual.nroDeClavesNoVacias())) {
					colaDeNodos.offer(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()));
				}
			} while (!colaDeNodos.isEmpty());
		}
		return recorrido;
	}

	@Override
        public void mostrar() {
            mostrar(this.raiz, 0, 10);
	}

	private void mostrar(NodoMVias<K, V> nodoActual, int space, int height) {
		// Caso base
		if (NodoMVias.esNodoVacio(nodoActual)) {  //if (root == null) {
			return;
		}

		// aumentar la distancia entre niveles
		space += height;

		// imprime el hijo derecho primero
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			mostrar(nodoActual.getHijo(i), space, height);
			System.out.print("|" + nodoActual.getClave(i));  //(root.data);
		} ///(root.right, space, height);
		System.out.println("|");
		System.out.println("D");

		// imprime el nodo actual después de rellenar con espacios
		for (int i = height; i < space; i++) {
			System.out.print(' ');
		}
		// imprime el hijo izquierdo
		mostrar(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()), space, height);  //(root.left, space, height);

		/////////////////
		/*
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return;
		}
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			recorridoEnInOrden(nodoActual.getHijo(i), recorrido);
			recorrido.add(nodoActual.getClave(i));
		}
		recorridoEnInOrden(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()),
				recorrido);
		*/
	} 

	public int NroDeHijosVacios() {
		return NroDeHijosVacios(this.raiz);
	}

	private int NroDeHijosVacios(NodoMVias<K, V> nodoActual) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return 0;
		}
		for (int i = 0; i < this.orden; i++) {
			NroDeHijosVacios(nodoActual.getHijo(i));
			//recorrido.add(nodoActual.getClave(i));
		}
		NroDeHijosVacios(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()));
		return 0;
	}

	private int NroDeHijosVaciosDespuesDeNivelN(NodoBinario<K, V> nodoActual) {
		return 0;
	}

	public void eliminarHojas() {
		this.raiz = eliminarHojas(this.raiz);
	}

	private NodoMVias<K, V> eliminarHojas(NodoMVias<K, V> nodoActual) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return NodoMVias.nodoVacio();
		}
		if (nodoActual.esHoja()) {
			return NodoMVias.nodoVacio();
		}
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			NodoMVias<K, V> eliminados = eliminarHojas(nodoActual.getHijo(i));
			nodoActual.setHijo(i, eliminados);
		}
		NodoMVias<K, V> nodoEliminado = eliminarHojas(nodoActual.getHijo(nodoActual.nroDeClavesNoVacias()));
		nodoActual.setHijo(nodoActual.nroDeClavesNoVacias(), nodoEliminado);
		return nodoActual;
	}

	/*public static void printBinaryTree(NodoMVias<K,V> nodoActual, int space, int height)  {
        // Caso base
        if(NodoMVias.esNodoVacio(nodoActual)) {  //if (root == null) {
            return;
        }
 
        // aumentar la distancia entre niveles
        space += height;
 
        // imprime el hijo derecho primero
        printBinary  //printBinaryTree(root.right, space, height);
        System.out.println();
 
        // imprime el nodo actual después de rellenar con espacios
        for (int i = height; i < space; i++) {
            System.out.print(' ');
        }
 
        System.out.print(root.data);
 
        // imprime el hijo izquierdo
        System.out.println();
        printBinaryTree(root.left, space, height);
    }*/
 /*
		PRACTICO SOBRE ARBOLES MVIAS
	 */

 /*
		EJERCICIO # 3
		Metodo recursivo que retorne la cantidad de datos no vacios
		que hay en el nivel N de un arbol M-Vias de busqueda
	 */
	private int contarDatosNoVaciosDesdeNivelN(NodoMVias<K, V> nodoActual, int nivel, int nivelBasico) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return 0;
		}
		int cantidad = 0;
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			cantidad += contarDatosNoVaciosDesdeNivelN(nodoActual.getHijo(i),
					nivel, nivelBasico + 1);
			if (nivel == nivelBasico) {
				if (!nodoActual.esClaveVacia(i)) {
					cantidad++;
				}
			}
		}
		cantidad += contarDatosNoVaciosDesdeNivelN(nodoActual.getHijo(
				nodoActual.nroDeClavesNoVacias()), nivel, nivelBasico + 1);
		return cantidad;
	}

	/*
		EJERCICIO # 4
		Metodo recursivo que retorne la cantidad de nodos hojas en un arbol
		M-Vias de busqueda, pero solo despues del nivel N
	 */
	public int cantidadDeHojasDesdeNivelN(int nivel) {
		return cantidadDeHojasDesdeNivelN(this.raiz, nivel, 0);
	}

	private int cantidadDeHojasDesdeNivelN(NodoMVias<K, V> nodoActual, int nivel, int nivelBasico) {
		if (NodoMVias.esNodoVacio(nodoActual)) {
			return 0;
		}
		int hoja = 0;
		for (int i = 0; i < nodoActual.nroDeClavesNoVacias(); i++) {
			hoja += cantidadDeHojasDesdeNivelN(nodoActual.getHijo(i), nivel, nivelBasico + 1);

		}
		int ultimaHoja = cantidadDeHojasDesdeNivelN(nodoActual.getHijo(
				nodoActual.nroDeClavesNoVacias()), nivel, nivelBasico + 1);
		if (nivel == nivelBasico) {
			if (nodoActual.esHoja()) {
				return hoja + ultimaHoja + 1;
			}
		}
		return hoja + ultimaHoja;
	}

	/*
		EJERCICIO # 5
		Metodo iterativo que retorne la cantidad de datos vacios y no vacios en un arbol
		B, pero solo antes del nivel N
	 */

        
       
	@Override
	public List<K> recorridoEnPreOrdenIterativo() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
        //MEOTDO QUE RETORNE LA CANTIDAD DE DATOS VACIOS(CLAVES VACIAS)
        public int CantDatosVacios1(){
            return CantDatosVacios1(raiz);
        }
        
        private int CantDatosVacios1(NodoMVias<K, V> nodoActual) {
            if(NodoMVias.esNodoVacio(nodoActual)){
                return 0;
            }
            int cantidad = 0;
            for(int i=0; i<this.orden; i++){
                cantidad += CantDatosVacios1(nodoActual.getHijo(i));
                if(i<this.orden-1){
                   if(nodoActual.esClaveVacia(i)){
                    cantidad ++;
                    } 
                }  
            }
            return cantidad;
        }
        
        public int CantDatosVacios2(){
            return CantDatosVacios2(raiz);
        }
        
        private int CantDatosVacios2(NodoMVias<K, V> nodoActual) {
            if(NodoMVias.esNodoVacio(nodoActual)){
                return 0;
            }
            int cantidad = 0;
            for(int i=0; i<orden-1; i++){
                cantidad += CantDatosVacios2(nodoActual.getHijo(i));
                if(nodoActual.esClaveVacia(i)){
                    cantidad++;
                }
            }
            cantidad += CantDatosVacios2(nodoActual.getHijo(orden-1));
            return cantidad;
            /*RECORRIDO enInOrden*/
        }
        
        /*METODO QUE RETORNE CUANTOS NODOS SON HOJAS*/
        
        
        public static void main(String[] args){
            ArbolMViasBusqueda arbol = new ArbolMViasBusqueda<>();
            
                arbol.insertar(51, "a");
                arbol.insertar(47, "b");
                arbol.insertar(60, "c");
                arbol.insertar(30, "d");
//                arbol.insertar(50, "e");
//                arbol.insertar(55, "e");
//                arbol.insertar(70, "e");
//                arbol.insertar(75, "e");
//                arbol.insertar(57, "e");
                arbol.mostrar();
       System.out.println("CANTIDAD DE DATOS VACIOS: "+arbol.CantDatosVacios2());
//        System.out.println("RECORRIDO POR NIVELES: "+arbol.recorridoPorNiveles());
//        
//        System.out.println("CANTIDAD DE NODOS EN EL ARBOL: "+arbol.sizeIterativo());
//        System.out.println("ALTURA DEL ARBOL: "+arbol.altura());
//        
//        System.out.println("NIVEL DEL ARBOL: "+arbol.nivel());
//        
//        System.out.println("Se encuentra la clave??? "+arbol.contiene(51));
//              
//        arbol.vaciar();
        System.out.println("RECORRIDO EN PREORDEN: "+arbol.recorridoEnPreOrden());
            
        }

   
}
