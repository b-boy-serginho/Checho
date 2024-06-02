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
import java.util.Stack;

/**
 *
 * @author Asrock
 * @param <K>
 * @param <V>
 */
public class ArbolBinarioBusqueda<K extends Comparable<K>, V> implements
		IArbolBusqueda<K, V> {

	protected NodoBinario<K, V> raiz;

	public ArbolBinarioBusqueda() {
	}

	/**
	 *
	 * @param clavesInOrden
	 * @param valoresInOrden
	 * @param clavesNoInOrden
	 * @param valoresNoInOrden
	 * @param conPreOrden
	 */
	public ArbolBinarioBusqueda(List<K> clavesInOrden,
			List<V> valoresInOrden,
			List<K> clavesNoInOrden,
			List<V> valoresNoInOrden,
			boolean conPreOrden) {
		if (clavesInOrden.isEmpty() || valoresInOrden.isEmpty()
				|| clavesNoInOrden.isEmpty() || valoresNoInOrden.isEmpty()) {
			throw new IllegalArgumentException("Los parametros no deben ser vacios");
		}
		if (conPreOrden) {
			this.raiz = reconstruirConPreOrden(clavesInOrden,
					valoresInOrden, clavesNoInOrden,
					valoresNoInOrden);
		} else {
			this.raiz = reconstruirConPostOrden(clavesInOrden,
					valoresInOrden, clavesNoInOrden,
					valoresNoInOrden);
		}
	}
        
        @Override
 public String dibujarRecorrido(ArrayList<K> recorrido){
         String R="";
         
         for (int i = 0; i < recorrido.size(); i++) {
             R= R + recorrido.get(i).toString()+" ";
         }
         
         return R;
     }
	@Override
	public void insertar(K claveAInsertar, V valorAsociado) {
		if (claveAInsertar == null) {
			throw new IllegalArgumentException("Clave inválida, no se aceptan claves nulas");
		}
		if (valorAsociado == null) {
			throw new IllegalArgumentException("Valor inválido, no se aceptan valores nulos");
		}
		if (this.esArbolVacio()) {
			this.raiz = new NodoBinario<>(claveAInsertar, valorAsociado);
			return;
		}

		NodoBinario<K, V> nodoAnterior = NodoBinario.nodoVacio();
		NodoBinario<K, V> nodoActual = this.raiz;
		boolean insertarAIzquierda = true;

		while (!NodoBinario.esNodoVacio(nodoActual)) {
			K claveDelNodoActual = nodoActual.getClave();
			nodoAnterior = nodoActual;
			if (claveAInsertar.compareTo(claveDelNodoActual) < 0) {
				nodoActual = nodoActual.getHijoIzquierdo();
				insertarAIzquierda = true;
			} else if (claveAInsertar.compareTo(claveDelNodoActual) > 0) {
				nodoActual = nodoActual.getHijoDerecho();
				insertarAIzquierda = false;
			} else {
				nodoActual.setValor(valorAsociado);
				return;
			}
		}
		NodoBinario<K, V> nodoNuevo = new NodoBinario<>(
				claveAInsertar, valorAsociado);
		if (insertarAIzquierda) {
			nodoAnterior.setHijoIzquierdo(nodoNuevo);
		} else {
			nodoAnterior.setHijoDerecho(nodoNuevo);
		}
	}
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
        @Override
        public void insertarRecursivo(K claveAInsertar, V valorAInsertar){
            if (claveAInsertar == null){
                throw new IllegalArgumentException("LA CLAVE NO PUEDE SER NULA");
            }
            if (valorAInsertar == null){
                throw new IllegalArgumentException("EL VALOR NO PUEDE SER NULO");
            }
            
            raiz = this.insertarRecursivo(raiz, claveAInsertar, valorAInsertar);
        }
        
        
        public NodoBinario<K,V> insertarRecursivo(NodoBinario<K,V>nodoActual,K claveAinsertar,V valorAinsertar){
            if(NodoBinario.esNodoVacio(nodoActual)){
                NodoBinario<K,V>nuevoNodo = new NodoBinario<K,V>(claveAinsertar, valorAinsertar);
                return nuevoNodo;
            }
            
            K claveDelNodoActual = nodoActual.getClave();
            if(claveAinsertar.compareTo(claveDelNodoActual) < 0){
                NodoBinario<K,V>nuevoNodo = insertarRecursivo(nodoActual.getHijoIzquierdo(), claveAinsertar, valorAinsertar);
                nodoActual.setHijoIzquierdo(nuevoNodo);
                return nodoActual;
            }
            else if (claveAinsertar.compareTo(claveDelNodoActual) > 0){
                NodoBinario<K,V>nuevoNodo = insertarRecursivo(nodoActual.getHijoDerecho(), claveAinsertar, valorAinsertar);
                nodoActual.setHijoDerecho(nuevoNodo);
                return nodoActual;
            }
            else{
                nodoActual.setValor(valorAinsertar);
                return nodoActual;
            }
        }
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
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

	private NodoBinario<K, V> eliminar(NodoBinario<K, V> nodoActual,
			K claveAEliminar) {
		K claveActual = nodoActual.getClave();
		if (claveAEliminar.compareTo(claveActual) < 0) {
			NodoBinario<K, V> supuestoNuevoHijoIzquierdo
					= eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
			nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
			return nodoActual;
		}
		if (claveAEliminar.compareTo(claveActual) > 0) {
			NodoBinario<K, V> supuestoNuevoHijoDerecho
					= eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
			nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
			return nodoActual;
		}

		// CASO 1 si es hoja
		if (nodoActual.esHoja()) {
			return NodoBinario.nodoVacio();
		}

		// CASO2
		// Caso2.1 solo tiene hijo derecho
		if (nodoActual.esVacioElHijoIzquierdo()
				&& !nodoActual.esVacioElHijoDerecho()) {
			return nodoActual.getHijoDerecho();
		}
		// Caso2.2 solo tiene hijo izquierdo
		if (!nodoActual.esVacioElHijoIzquierdo()
				&& nodoActual.esVacioElHijoDerecho()) {
			return nodoActual.getHijoIzquierdo();
		}

		// CASO 3 nodo con ambos hijos
		NodoBinario<K, V> nodoDelSucesor = obtenerNodoDelSucesor(
				nodoActual.getHijoDerecho());
		NodoBinario<K, V> posibleNuevoHijoDerecho
				= eliminar(nodoActual.getHijoDerecho(), nodoDelSucesor.getClave());

		nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);
		nodoActual.setClave(nodoDelSucesor.getClave());
		nodoActual.setValor(nodoDelSucesor.getValor());

		return nodoActual;

	}

	protected NodoBinario<K, V> obtenerNodoDelSucesor(
			NodoBinario<K, V> elNodo) {
		NodoBinario<K, V> nodoAnterior;
		do {
			nodoAnterior = elNodo;
			elNodo = elNodo.getHijoIzquierdo();
		} while (!NodoBinario.esNodoVacio(elNodo));

		return nodoAnterior;
	}

//----------------------------------------------------------------------------------------------------
////----------------------------------------------------------------------------------------------------
        public V eliminarIterativo(K claveAElinar){
            return null;
        }
//----------------------------------------------------------------------------------------------------
////----------------------------------------------------------------------------------------------------
//
	@Override
	public V buscar(K claveABuscar) {
		if (!this.esArbolVacio()) {
			NodoBinario<K, V> nodoActual = this.raiz;
			do {
				K claveDelNodoActual = nodoActual.getClave();
				if (claveABuscar.compareTo(claveDelNodoActual) < 0) {
					nodoActual = nodoActual.getHijoIzquierdo();
				} else if (claveABuscar.compareTo(claveDelNodoActual) > 0) {
					nodoActual = nodoActual.getHijoDerecho();
				} else {
					return nodoActual.getValor();
				}
			} while (!NodoBinario.esNodoVacio(nodoActual)); 
		}
		return null;
	}

	@Override
	public boolean contiene(K claveAVerificar) {
		return this.buscar(claveAVerificar) != null;
	}

	@Override
	public int sizeIterativo() {
		int cantidadDeNodos = 0;
		if (!this.esArbolVacio()) {
			Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
			colaDeNodos.offer(this.raiz);
			do {
				NodoBinario<K, V> nodoActual = colaDeNodos.poll();
				cantidadDeNodos++;
				if (!nodoActual.esVacioElHijoIzquierdo()) {
					colaDeNodos.offer(nodoActual.getHijoIzquierdo());
				}
				if (!nodoActual.esVacioElHijoDerecho()) {
					colaDeNodos.offer(nodoActual.getHijoDerecho());
				}
			} while (!colaDeNodos.isEmpty());
		}
		return cantidadDeNodos;
	}

	@Override
	// sizeV2 retorna cantidad de nodos en modo RECURSIVO
	public int size() {
		return size(this.raiz);
	}

	private int size(NodoBinario<K, V> nodoActual) {
		if (NodoBinario.esNodoVacio(nodoActual)) {
			return 0;
		}
		int sizePorIzquierda = size(nodoActual.getHijoIzquierdo());
		int sizePorDerecha = size(nodoActual.getHijoDerecho());
		return sizePorIzquierda + sizePorDerecha + 1;
	}

	@Override
	public K claveMinima() {
		if (this.esArbolVacio()) {
			return null;
		}

		NodoBinario<K, V> nodoActual = this.raiz;
		NodoBinario<K, V> nodoAnterior = NodoBinario.nodoVacio();
		while (!NodoBinario.esNodoVacio(nodoActual)) {
			nodoAnterior = nodoActual;
			nodoActual = nodoActual.getHijoIzquierdo();
		}
		return nodoAnterior.getClave();
	}

	@Override
	public K claveMaxima() {
		if (this.esArbolVacio()) {
			return null;
		}

		NodoBinario<K, V> nodoActual = this.raiz;
		NodoBinario<K, V> nodoAnterior = NodoBinario.nodoVacio();
		while (!NodoBinario.esNodoVacio(nodoActual)) {
			nodoAnterior = nodoActual;
			nodoActual = nodoActual.getHijoDerecho();
		}
		return nodoAnterior.getClave();
	}

	@Override
	public int alturaIterativo() {
		int altura = 0;
		if (!this.esArbolVacio()) {
			Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
			colaDeNodos.offer(this.raiz);
			do {
				int nroDeNodosDelNivel = colaDeNodos.size();
				while (nroDeNodosDelNivel > 0) {
					NodoBinario<K, V> nodoActual = colaDeNodos.poll();
					if (!nodoActual.esVacioElHijoIzquierdo()) {
						colaDeNodos.offer(nodoActual.getHijoIzquierdo());
					}
					if (!nodoActual.esVacioElHijoDerecho()) {
						colaDeNodos.offer(nodoActual.getHijoDerecho());
					}
					nroDeNodosDelNivel--;
				}
				altura++;
			} while (!colaDeNodos.isEmpty());
		}
		return altura;
	}

	@Override
	// alturaV2 de forma RECURSIVA
	public int altura() {
		return altura(this.raiz);
	}

	protected int altura(NodoBinario<K, V> nodoActual) {
		if (NodoBinario.esNodoVacio(nodoActual)) {
			return 0;
		}
		int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
		int alturaPorDerecha = altura(nodoActual.getHijoDerecho());
		return alturaPorIzquierda > alturaPorDerecha ? alturaPorIzquierda + 1
				: alturaPorDerecha + 1;
	}

	@Override
	public void vaciar() {
		this.raiz = NodoBinario.nodoVacio();
	}

	@Override
	public boolean esArbolVacio() {
		return NodoBinario.esNodoVacio(this.raiz);
	}

	@Override
	public int nivel() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public int cantidadDeHojas() {
		return cantidadDeHojas(this.raiz);
	}

	private int cantidadDeHojas(NodoBinario<K, V> nodoActual) {
		if (NodoBinario.esNodoVacio(nodoActual)) {
			return 0;
		}
		if (nodoActual.esHoja()) {
			return 1;
		}
		int cantidadHojasPorIzquierda = cantidadDeHojas(
				nodoActual.getHijoIzquierdo());
		int cantidadDeHojasPorDerecha = cantidadDeHojas(
				nodoActual.getHijoDerecho());
		return cantidadHojasPorIzquierda + cantidadDeHojasPorDerecha;
	}

	@Override
	public int cantidadDeHijosDerechos() {
		return cantidadDeHijosDerechos(this.raiz);
	}

	private int cantidadDeHijosDerechos(NodoBinario<K, V> nodoActual) {
		if (NodoBinario.esNodoVacio(nodoActual)) {
			return 0;
		}
		int hdPorRamaIzquierda = cantidadDeHijosDerechos(nodoActual.getHijoIzquierdo());
		int hdPorRamaDerecha = cantidadDeHijosDerechos(nodoActual.getHijoDerecho());
		if (!nodoActual.esVacioElHijoDerecho()) {
			return hdPorRamaIzquierda + hdPorRamaDerecha + 1;
		}

		return hdPorRamaIzquierda + hdPorRamaDerecha;
	}

	public boolean tieneNodosCompletosEnNivel(int nivelObjetivo) {
		return tieneNodosCompletosEnNivel(this.raiz, nivelObjetivo, 0);
	}

	private boolean tieneNodosCompletosEnNivel(NodoBinario<K, V> nodoActual,
			int nivelObjetivo, int nivelActual) {
		if (NodoBinario.esNodoVacio(nodoActual)) {
			return false;
		}

		if (nivelActual == nivelObjetivo) {
			return !nodoActual.esVacioElHijoIzquierdo()
					&& !nodoActual.esVacioElHijoDerecho();
		}
		boolean completoPorIzquierda = this.tieneNodosCompletosEnNivel(
				nodoActual.getHijoIzquierdo(), nivelObjetivo, nivelActual + 1);
		boolean completoPorDerecha = this.tieneNodosCompletosEnNivel(
				nodoActual.getHijoDerecho(), nivelObjetivo, nivelActual + 1);
		return completoPorIzquierda && completoPorDerecha;
	}

	public int NroNodosCompletosFueraDelNivel(int nivelObjetivo) {
		return NroNodosCompletosFueraDelNivel(this.raiz, nivelObjetivo, 0);
	}

	private int NroNodosCompletosFueraDelNivel(NodoBinario<K, V> nodoActual,
			int nivelObjetivo, int nivelActual) {
		if (NodoBinario.esNodoVacio(nodoActual)) {
			return 0;
		}

		int NroNodosPorIzquierda = this.NroNodosCompletosFueraDelNivel(
				nodoActual.getHijoIzquierdo(), nivelObjetivo, nivelActual + 1);
		int NroNodosPorDerecha = this.NroNodosCompletosFueraDelNivel(
				nodoActual.getHijoDerecho(), nivelObjetivo, nivelActual + 1);
		if (nivelActual != nivelObjetivo) {
			if (!nodoActual.esVacioElHijoIzquierdo()
					&& !nodoActual.esVacioElHijoDerecho()) {
				return NroNodosPorIzquierda + NroNodosPorDerecha + 1;
			}
		}
		return NroNodosPorIzquierda + NroNodosPorDerecha;
	}

	public int NroNodosCompletosFueraDelNivelIterativo(int nivelObjetivo) {
		int cantidad = 0;
		if (!this.esArbolVacio()) {
			Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
			colaDeNodos.offer(this.raiz);
			int nivelActual = 0;
			do {
				NodoBinario<K, V> nodoActual = colaDeNodos.poll();
				//recorrido.add(nodoActual.getClave());

				if (!nodoActual.esVacioElHijoIzquierdo()) {
					colaDeNodos.offer(nodoActual.getHijoIzquierdo());
				}
				if (!nodoActual.esVacioElHijoDerecho()) {
					colaDeNodos.offer(nodoActual.getHijoDerecho());
				}
				if (nivelActual != nivelObjetivo) {
					if (!nodoActual.esVacioElHijoIzquierdo() && !nodoActual.esVacioElHijoDerecho()) {
						cantidad++;
					}
				}
				nivelActual++;
			} while (!colaDeNodos.isEmpty());
		}
		return cantidad;
	}

	@Override
	public List<K> recorridoEnInOrdenIterativo() {
		List<K> recorrido = new ArrayList<>();
		if (!this.esArbolVacio()) {
			Stack<NodoBinario<K, V>> pilaDeNodo = new Stack<>();
			NodoBinario<K, V> nodoActual = this.raiz;
			apilarParaInOrden(nodoActual, pilaDeNodo);
			while (!pilaDeNodo.empty()) {
				nodoActual = pilaDeNodo.pop();
				recorrido.add(nodoActual.getClave());
				if (!nodoActual.esVacioElHijoDerecho()) {
					apilarParaInOrden(nodoActual.getHijoDerecho(),pilaDeNodo);
				}
			}
		}
		return recorrido;
	}

	private void apilarParaInOrden(NodoBinario<K, V> nodoActual,
			Stack<NodoBinario<K, V>> pilaDeNodo) {
		while (!NodoBinario.esNodoVacio(nodoActual)) {
			pilaDeNodo.push(nodoActual);
			nodoActual = nodoActual.getHijoIzquierdo();
		}
	}

	@Override
	public List<K> recorridoEnInOrden() {
		List<K> recorrido = new ArrayList<>();
		recorridoEnInOrden(this.raiz, recorrido);
		return recorrido;
	}

	private void recorridoEnInOrden(NodoBinario<K, V> nodoActual,
			List<K> recorrido) {
		if (NodoBinario.esNodoVacio(nodoActual)) {
			return;
		}
		recorridoEnInOrden(nodoActual.getHijoIzquierdo(), recorrido);
		recorrido.add(nodoActual.getClave());
		recorridoEnInOrden(nodoActual.getHijoDerecho(), recorrido);

	}

	@Override
	public List<K> recorridoEnPreOrdenIterativo() {
		List<K> recorrido = new ArrayList<>();
		if (this.esArbolVacio()) {
			return recorrido;
		}
		Stack<NodoBinario<K, V>> pilaDeNodo = new Stack<>();
		pilaDeNodo.push(this.raiz);
		while (!pilaDeNodo.empty()) {
			NodoBinario<K, V> nodoActual = pilaDeNodo.pop();
			recorrido.add(nodoActual.getClave());
			if (!nodoActual.esVacioElHijoDerecho()) {
				pilaDeNodo.push(nodoActual.getHijoDerecho());
			}
			if (!nodoActual.esVacioElHijoIzquierdo()) {
				pilaDeNodo.push(nodoActual.getHijoIzquierdo());
			}
		}
		return recorrido;
	}

	@Override
	public List<K> recorridoEnPreOrden() {
		List<K> recorrido = new ArrayList<>();
		recorridoEnPreOrden(this.raiz, recorrido);
		return recorrido;
	}

	private void recorridoEnPreOrden(NodoBinario<K, V> nodoActual,
			List<K> recorrido) {
		if (NodoBinario.esNodoVacio(nodoActual)) {
			return;
		}
		recorrido.add(nodoActual.getClave());
		recorridoEnPreOrden(nodoActual.getHijoIzquierdo(), recorrido);
		recorridoEnPreOrden(nodoActual.getHijoDerecho(), recorrido);
	}
	
	@Override
	public List<K> recorridoEnPostOrdenIterativo() {
		List<K> recorrido = new ArrayList<>();
		if (!this.esArbolVacio()) {

			Stack<NodoBinario<K, V>> pilaDeNodo = new Stack<>();
			NodoBinario<K, V> nodoActual = this.raiz;
			//iterar en una función para apilar 
			apilarParaPostOrden(nodoActual, pilaDeNodo);
			//empezams a iterar sobre la pila
			while (!pilaDeNodo.empty()) {
				nodoActual = pilaDeNodo.pop();
				recorrido.add(nodoActual.getClave());
				if (!pilaDeNodo.empty()) {
					NodoBinario<K, V> nodoDelTope = pilaDeNodo.peek();
					if (!nodoDelTope.esVacioElHijoDerecho()
							&& nodoDelTope.getHijoDerecho() != nodoActual) {
						//volver a iterar en la funcion para apilar
						apilarParaPostOrden(nodoDelTope.getHijoDerecho(),
								pilaDeNodo);
					}
				}
			}

		}

		return recorrido;
	}

	private void apilarParaPostOrden(NodoBinario<K, V> nodoActual,
			Stack<NodoBinario<K, V>> pilaDeNodo) {
		//proceso inicial antes de iterar en la pila
		while (!NodoBinario.esNodoVacio(nodoActual)) {
			pilaDeNodo.push(nodoActual);
			if (!nodoActual.esVacioElHijoIzquierdo()) {
				nodoActual = nodoActual.getHijoIzquierdo();
			} else {
				nodoActual = nodoActual.getHijoDerecho();
			}
		}
	}

	@Override
	public List<K> recorridoEnPostOrden() {
		List<K> recorrido = new ArrayList<>();
		recorridoEnPostOrden(this.raiz, recorrido);
		return recorrido;
	}

	private void recorridoEnPostOrden(NodoBinario<K, V> nodoActual,
			List<K> recorrido) {
		if (NodoBinario.esNodoVacio(nodoActual)) {
			return;
		}
		recorridoEnPostOrden(nodoActual.getHijoIzquierdo(), recorrido);
		recorridoEnPostOrden(nodoActual.getHijoDerecho(), recorrido);
		recorrido.add(nodoActual.getClave());
	}

	@Override
	public List<K> recorridoPorNiveles() {
		List<K> recorrido = new ArrayList<>();
		if (!this.esArbolVacio()) {
			Queue<NodoBinario<K, V>> colaDeNodos = new LinkedList<>();
			colaDeNodos.offer(this.raiz);

			do {
				NodoBinario<K, V> nodoActual = colaDeNodos.poll();
				recorrido.add(nodoActual.getClave());
				if (!nodoActual.esVacioElHijoIzquierdo()) {
					colaDeNodos.offer(nodoActual.getHijoIzquierdo());
				}
				if (!nodoActual.esVacioElHijoDerecho()) {
					colaDeNodos.offer(nodoActual.getHijoDerecho());
				}

			} while (!colaDeNodos.isEmpty());
		}
		return recorrido;
	}

	private NodoBinario<K, V> reconstruirConPreOrden(List<K> clavesInOrden,
			List<V> valoresInOrden, List<K> clavesPreOrden,
			List<V> valoresPreOrden) {
		if (clavesInOrden.isEmpty()) {
			return NodoBinario.nodoVacio();
		}
		int posicionDeClaveEnPreOrden = 0;
		K claveDelNodoActual = clavesPreOrden.get(posicionDeClaveEnPreOrden);
		V valorAsociadoActual = valoresPreOrden.get(posicionDeClaveEnPreOrden);

		int posicionDeClaveEnInOrden = this.buscarPosicionDeClave(clavesInOrden,
				claveDelNodoActual);
		// ARMANDO RAMA IZQUIERDA
		List<K> clavesInOrdenPorIzquierda = clavesInOrden.subList(0,
				posicionDeClaveEnInOrden);
		List<V> valoresInOrdenPorIzquierda = valoresPreOrden.subList(0,
				posicionDeClaveEnInOrden);

		List<K> clavesPreOrdenPorIzquierda = clavesPreOrden.subList(
				1, posicionDeClaveEnInOrden + 1);
		List<V> valoresPreOrdenPorIzquierda = valoresPreOrden.subList(
				1, posicionDeClaveEnInOrden + 1);

		NodoBinario<K, V> hijoIzquierdo = reconstruirConPreOrden(
				clavesInOrdenPorIzquierda, valoresInOrdenPorIzquierda,
				clavesPreOrdenPorIzquierda, valoresPreOrdenPorIzquierda);
		// ARMANDO RAMA DERECHA
		List<K> clavesInOrdenPorDerecha = clavesInOrden.subList(
				posicionDeClaveEnInOrden + 1, clavesInOrden.size());
		List<V> valoresInOrdenPorDerecha = valoresPreOrden.subList(
				posicionDeClaveEnInOrden + 1, clavesInOrden.size());

		List<K> clavesPreOrdenPorDerecha = clavesPreOrden.subList(
				posicionDeClaveEnInOrden + 1, clavesPreOrden.size());
		List<V> valoresPreOrdenPorDerecha = valoresPreOrden.subList(
				posicionDeClaveEnInOrden + 1, clavesPreOrden.size());

		NodoBinario<K, V> hijoDerecho = reconstruirConPreOrden(
				clavesInOrdenPorDerecha, valoresInOrdenPorDerecha,
				clavesPreOrdenPorDerecha, valoresPreOrdenPorDerecha);
		// ARMANDO EL NODO ACTUAL
		NodoBinario<K, V> nodoActual = new NodoBinario<>(claveDelNodoActual,
				valorAsociadoActual);
		nodoActual.setHijoIzquierdo(hijoIzquierdo);
		nodoActual.setHijoDerecho(hijoDerecho);
		return nodoActual;	
	}

	private int buscarPosicionDeClave(List<K> claves, K claveABuscar) {
		for (int i = 0; i < claves.size(); i++) {
			K claveActual = claves.get(i);
			if (claveABuscar.compareTo(claveActual) == 0) {
				return i;
			}
		}
		return -1;
	}

	private NodoBinario<K, V> reconstruirConPostOrden(List<K> clavesInOrden,
			List<V> valoresInOrden, List<K> clavesPostOrden,
			List<V> valoresPostOrden) {
		if (clavesInOrden.isEmpty()) {
			return NodoBinario.nodoVacio();
		}

		int posicionDeClaveEnPostOrden = clavesPostOrden.size() - 1;
		K claveDelNodoActual = clavesPostOrden.get(posicionDeClaveEnPostOrden);
		V valorAsociadoActual = valoresPostOrden.get(posicionDeClaveEnPostOrden);

		int posicionDeClaveEnInOrden = this.buscarPosicionDeClave(clavesInOrden,
				claveDelNodoActual);
		// ARMANDO RAMA IZQUIERDA
		List<K> clavesInOrdenPorIzquierda = clavesInOrden.subList(0,
				posicionDeClaveEnInOrden);
		List<V> valoresInOrdenPorIzquierda = valoresPostOrden.subList(0,
				posicionDeClaveEnInOrden);

		List<K> clavesPostOrdenPorIzquierda = clavesPostOrden.subList(
				0, posicionDeClaveEnInOrden);
		List<V> valoresPostOrdenPorIzquierda = valoresPostOrden.subList(
				0, posicionDeClaveEnInOrden);

		NodoBinario<K, V> hijoIzquierdo = reconstruirConPreOrden(
				clavesInOrdenPorIzquierda, valoresInOrdenPorIzquierda,
				clavesPostOrdenPorIzquierda, valoresPostOrdenPorIzquierda);
		// ARMANDO RAMA DERECHA  a la derecha a la derecha        
		List<K> clavesInOrdenPorDerecha = clavesInOrden.subList(
				posicionDeClaveEnInOrden + 1, clavesInOrden.size());
		List<V> valoresInOrdenPorDerecha = valoresPostOrden.subList(
				posicionDeClaveEnInOrden + 1, clavesInOrden.size());

		List<K> clavesPreOrdenPorDerecha = clavesPostOrden.subList(
				posicionDeClaveEnInOrden, clavesPostOrden.size() - 1);
		List<V> valoresPreOrdenPorDerecha = valoresPostOrden.subList(
				posicionDeClaveEnInOrden, clavesPostOrden.size() - 1);

		NodoBinario<K, V> hijoDerecho = reconstruirConPreOrden(
				clavesInOrdenPorDerecha, valoresInOrdenPorDerecha,
				clavesPreOrdenPorDerecha, valoresPreOrdenPorDerecha);
		// ARMANDO EL NODO ACTUAL
		NodoBinario<K, V> nodoActual = new NodoBinario<>(claveDelNodoActual,
				valorAsociadoActual);
		nodoActual.setHijoIzquierdo(hijoIzquierdo);
		nodoActual.setHijoDerecho(hijoDerecho);

		return nodoActual;

	}

	/*//@Override
	private void mostrar(NodoBinario<K, V> nodo) {

		int profundidad = altura(this.raiz);

		for (int i = 0; i < profundidad; i++) {
			System.out.print(" ");
		}
		System.out.println("- " + nodo.getClave().toString());

		if (!nodo.esVacioElHijoIzquierdo()) {
			mostrar(nodo.getHijoIzquierdo());
		}
		if (!nodo.esVacioElHijoDerecho()) {
			mostrar(nodo.getHijoDerecho());
		}
	}*/
	@Override
	public void mostrar() {
		mostrar(this.raiz, 0, 10);
	}

	private void mostrar(NodoBinario<K, V> nodoActual, int space, int height) {
		// Caso base
		if (NodoBinario.esNodoVacio(nodoActual)) {  //if (root == null) {
			return;
		}

		// aumentar la distancia entre niveles
		space += height;

		// imprime el hijo derecho primero
		mostrar(nodoActual.getHijoDerecho(), space, height);  ///(root.right, space, height);
		System.out.println("D");

		// imprime el nodo actual después de rellenar con espacios
		for (int i = height; i < space; i++) {
			System.out.print(' ');
		}

		System.out.print("-|" + nodoActual.getClave() + "|");  //(root.data);

		// imprime el hijo izquierdo
		System.out.println();
		mostrar(nodoActual.getHijoIzquierdo(), space, height);  //(root.left, space, height);
	}

        
        public static void main(String[] args) throws OrdenInvalidoExcepcion, NewException {
            ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
                arbol.insertarRecursivo(81, "Azul");
                arbol.insertarRecursivo(60, "Azul");
		arbol.insertarRecursivo(85, "Azul");
                arbol.insertarRecursivo(96, "Azul");
                arbol.insertarRecursivo(99, "Azul");
                arbol.insertarRecursivo(65, "Azul");
		
                System.out.println("Recorrido en InOrden:   "+ arbol.recorridoEnInOrden());
		System.out.println("Recorrido en PreOrden:  "+ arbol.recorridoEnPreOrden());
		System.out.println("Recorrido en PostOrden: "+ arbol.recorridoEnPostOrden());
		System.out.println("Recorrido en Por Niveles: "+ arbol.recorridoPorNiveles());
                System.out.println("Cantidad de Hojas: "+ arbol.cantidadDeHojas());
                System.out.println("NRO: "+ arbol.NroNodosCompletosFueraDelNivel(2));
		System.out.println();
		arbol.mostrar();
        }
        
}
