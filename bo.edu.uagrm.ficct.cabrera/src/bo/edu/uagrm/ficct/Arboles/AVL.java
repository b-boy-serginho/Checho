/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Arboles;

/**
 *
 * @author Asrock
 * @param <K>
 * @param <V>
 */
public class AVL<K extends Comparable<K>, V>
		extends ArbolBinarioBusqueda<K, V> {

	private static final byte LIMITE_SUPERIOR = 1;
	private static final byte LIMITE_INFERIOR = -1;

	@Override
	public void insertar(K claveAInsertar, V valorAsociado) {
		if (claveAInsertar == null) {
			throw new IllegalArgumentException(
					"Clave invalida,no se aceptan valores nulos");
		}
		if (valorAsociado == null) {
			throw new IllegalArgumentException(
					"Valor Invalido,no se aceptan valores nulos");
		}

		this.raiz = insertar(this.raiz, claveAInsertar, valorAsociado);
	}

	private NodoBinario<K, V> insertar(NodoBinario<K, V> nodoActual,
			K claveAInsertar, V valorAsociado) {

		if (NodoBinario.esNodoVacio(nodoActual)) {
			NodoBinario<K, V> nuevoNodo = new NodoBinario<>(claveAInsertar, valorAsociado);
			return nuevoNodo;
		}

		K claveDelNodoActual = nodoActual.getClave();

		if (claveAInsertar.compareTo(claveDelNodoActual) < 0) {
			NodoBinario<K, V> supuestoNuevoHijoIzquierdo	= insertar(nodoActual.getHijoIzquierdo(),
							claveAInsertar, valorAsociado);
			nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
			return balancear(nodoActual);
		}
		if (claveAInsertar.compareTo(claveDelNodoActual) > 0) {
			NodoBinario<K, V> supuestoNuevoHijoDerecho = insertar(nodoActual.getHijoDerecho(),
							claveAInsertar, valorAsociado);
			nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
			return balancear(nodoActual);
		}

		nodoActual.setValor(valorAsociado);
		return nodoActual;
	}

	private NodoBinario<K, V> balancear(NodoBinario<K, V> nodoActual) {
		int alturaPorIzquierda = altura(nodoActual.getHijoIzquierdo());
		int alturaPorDerecha = altura(nodoActual.getHijoDerecho());
		int diferenciaDeAltura = alturaPorIzquierda - alturaPorDerecha;
		if (diferenciaDeAltura > AVL.LIMITE_SUPERIOR) {
			// HAY QUE HACER ROTACION A LA DERECHA
			NodoBinario<K, V> hijoIzquierdoDelProblematico = nodoActual.getHijoIzquierdo();
			alturaPorIzquierda = altura(hijoIzquierdoDelProblematico.getHijoIzquierdo());
			alturaPorDerecha = altura(hijoIzquierdoDelProblematico.getHijoDerecho());
			if (alturaPorIzquierda < alturaPorDerecha) { // alturaDer>alturaIzq
				return rotacionDoblePorDerecha(nodoActual);
			} else {
				return rotacionSimplePorDerecha(nodoActual);
			}
		} else if (diferenciaDeAltura < AVL.LIMITE_INFERIOR) {
			// HAY QUE HACER ROTACION A LA IZQUIERDA
			NodoBinario<K, V> hijoDerechoDelProblematico = nodoActual.getHijoDerecho();
			alturaPorIzquierda = altura(hijoDerechoDelProblematico.getHijoIzquierdo());
			alturaPorDerecha = altura(hijoDerechoDelProblematico.getHijoDerecho());
			if (alturaPorIzquierda > alturaPorDerecha) {
				return rotacionDoblePorIzquierda(nodoActual);
			} else {
				return rotacionSimplePorIzquierda(nodoActual);
			}
		} else {
			return nodoActual;
		}
		//return null;
	}

	private NodoBinario<K, V> rotacionSimplePorDerecha(NodoBinario<K, V> nodoActual) {
		NodoBinario<K, V> nodoQueRota = nodoActual.getHijoIzquierdo();
		nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
		nodoQueRota.setHijoDerecho(nodoActual);
		return nodoQueRota;
	}

	private NodoBinario<K, V> rotacionDoblePorDerecha(NodoBinario<K, V> nodoActual) {
		NodoBinario<K, V> nodoQueRotaAIzquierda = rotacionSimplePorIzquierda(nodoActual.getHijoIzquierdo());
		nodoActual.setHijoIzquierdo(nodoQueRotaAIzquierda);
		return rotacionSimplePorDerecha(nodoActual);
	}

	private NodoBinario<K, V> rotacionSimplePorIzquierda(NodoBinario<K, V> nodoActual) {
		NodoBinario<K, V> nodoQueRota = nodoActual.getHijoDerecho();
		nodoActual.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
		nodoQueRota.setHijoIzquierdo(nodoActual);
		return nodoQueRota;
	}

	private NodoBinario<K, V> rotacionDoblePorIzquierda(NodoBinario<K, V> nodoActual) {
		NodoBinario<K, V> nodoQueRotaADerecha = rotacionSimplePorDerecha(
				nodoActual.getHijoDerecho());
		nodoActual.setHijoDerecho(nodoQueRotaADerecha);
		return rotacionSimplePorIzquierda(nodoActual);
	}

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
			return balancear(nodoActual);
		}
		if (claveAEliminar.compareTo(claveActual) > 0) {
			NodoBinario<K, V> supuestoNuevoHijoDerecho
					= eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
			nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
			return balancear(nodoActual);
		}

		// CASO 1
		if (nodoActual.esHoja()) {
			return NodoBinario.nodoVacio();
		}

		// CASO2
		if (nodoActual.esVacioElHijoIzquierdo()
				&& !nodoActual.esVacioElHijoDerecho()) {
			return nodoActual.getHijoDerecho();
		}
		if (!nodoActual.esVacioElHijoIzquierdo()
				&& nodoActual.esVacioElHijoDerecho()) {
			return nodoActual.getHijoIzquierdo();
		}

		// CASO 3
		NodoBinario<K, V> nodoDelSucesor = obtenerNodoDelSucesor(
				nodoActual.getHijoDerecho());
		NodoBinario<K, V> posibleNuevoHijoDerecho
				= eliminar(nodoActual.getHijoDerecho(), nodoDelSucesor.getClave());

		nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);
		nodoActual.setClave(nodoDelSucesor.getClave());
		nodoActual.setValor(nodoDelSucesor.getValor());

		return nodoActual;
	}

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
    
    
    public static void main(String[] args) {
        AVL<Integer, String> arbol = new AVL<>();
        
        arbol.insertar(100, "a");
        arbol.insertar(90, "c");
        arbol.insertar(80, "d");
//        arbol.insertar(55, "d");
//        arbol.insertar(70, "e");
//        arbol.insertar(75, "e");
        arbol.mostrar();
        System.out.println("es Vacio: "+arbol.esArbolVacio());
        System.out.println("ALTURA: "+arbol.altura()); 
//        arbol.eliminar(51);
//        arbol.mostrar();
    }
	/*public void mostrar() {
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

		System.out.print("|" + nodoActual.getClave() + "|");  //(root.data);

		// imprime el hijo izquierdo
		System.out.println();
		mostrar(nodoActual.getHijoIzquierdo(), space, height);  //(root.left, space, height);
	}*/

}
