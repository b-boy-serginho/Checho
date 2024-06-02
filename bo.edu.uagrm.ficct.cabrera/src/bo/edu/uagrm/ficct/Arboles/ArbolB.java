/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Arboles;

import java.util.Stack;

/**
 *
 * @author Asrock
 */
public class ArbolB<K extends Comparable<K>, V> extends ArbolMViasBusqueda<K, V> {

		private final int nroMaxDeDatos;
		private final int nroMinDeDatos;
		private final int nroMinDeHijos;

		public ArbolB() {
				super();
				this.nroMaxDeDatos = 2;
				this.nroMinDeDatos = 1;
				this.nroMinDeHijos = 2;
		}

		public ArbolB(int orden) throws OrdenInvalidoExcepcion {
				super(orden);
				this.nroMaxDeDatos = super.orden - 1;
				this.nroMinDeDatos = this.nroMaxDeDatos / 2;
				this.nroMinDeHijos = this.nroMinDeDatos + 1;
		}

		@Override
		public void insertar(K claveAInsertar, V valorAsociado) {
				if (claveAInsertar == null) {
						throw new IllegalArgumentException("Clave invalida, no se aceptan claves vacias");
				}
				if (valorAsociado == null) {
						throw new IllegalArgumentException("Valor invalido, no se aceptan valores nulos");
				}
				if (this.esArbolVacio()) {
						this.raiz = new NodoMVias<>(this.orden + 1, claveAInsertar, valorAsociado);
						return;
				}

				NodoMVias<K, V> nodoActual = this.raiz;
				Stack<NodoMVias<K, V>> pilaDeAncestros = new Stack<>();

				do {
						int posicionDeClave = super.obtenerPosicionDeClave(nodoActual,
						 claveAInsertar);
						if (posicionDeClave != ArbolMViasBusqueda.POSICION_NO_VALIDA) {
								nodoActual.setValor(posicionDeClave, valorAsociado);
								nodoActual = NodoMVias.nodoVacio();
						}
						else if (nodoActual.esHoja()) {
								super.insertarClaveYValorOrdenadamenteEnNodo(nodoActual, claveAInsertar,
								 valorAsociado);
								if (nodoActual.nroDeClavesNoVacias() > this.nroMaxDeDatos) {
										this.dividir(nodoActual, pilaDeAncestros);

								}
								nodoActual = NodoMVias.nodoVacio();
						}
						else {
								int posicionPorDondeBajar = obtenerPosicionPorDondeBajar(nodoActual, claveAInsertar);
								pilaDeAncestros.push(nodoActual);
								nodoActual = nodoActual.getHijo(posicionPorDondeBajar);
						}
				}
				while (!NodoMVias.esNodoVacio(nodoActual));

		}

		/*private void dividir(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDeAncestros) {

		NodoMVias<K, V> nodoPadre = new NodoMVias(this.orden + 1);
		//NodoMVias<K, V> nodoPrimeraDivision = new NodoMVias(this.orden + 1);
		NodoMVias<K, V> nodoSegundaDivision = new NodoMVias(this.orden + 1);

		while (!NodoMVias.esNodoVacio(nodoPadre)) {
			int posicionDelMedio = this.orden / 2;
			K clavePosicionDelMedio = nodoActual.getClave(posicionDelMedio - 1);
			V valorPosicionDelMedio = nodoActual.getValor(posicionDelMedio - 1);
			for (int i = posicionDelMedio; i < this.orden; i++) { // DIVIDIENDO CLAVES
				nodoSegundaDivision.setClave(i - posicionDelMedio, nodoActual.getClave(i));
				nodoActual.setClave(i, (K) NodoMVias.DatoVacio());
			}
			nodoActual.setClave(posicionDelMedio - 1, (K) NodoMVias.DatoVacio());
			if (!pilaDeAncestros.isEmpty()) {
				nodoPadre = pilaDeAncestros.pop();    // padre: [70,100,400] actual: [75,90,91,99]

				super.insertarClaveYValorOrdenadamenteEnNodo(nodoPadre, clavePosicionDelMedio,
						valorPosicionDelMedio);
				int posicionClaveMedia = this.obtenerPosicionDeClave(nodoPadre, clavePosicionDelMedio);
				for (int i = nodoPadre.nroDeClavesNoVacias() - 1; i > posicionClaveMedia; i--) {  // ORDENANDO HIJOS
					nodoPadre.setHijo(i + 1, nodoPadre.getHijo(i));
				}

				nodoPadre.setHijo(posicionClaveMedia, nodoActual);
				nodoPadre.setHijo(posicionClaveMedia + 1, nodoSegundaDivision);
				nodoActual = nodoPadre;
				if (nodoPadre.nroDeClavesNoVacias() <= this.nroMaxDeDatos) {
					nodoPadre = NodoMVias.nodoVacio();
				}
			} else {
				super.insertarClaveYValorOrdenadamenteEnNodo(nodoPadre, clavePosicionDelMedio,
						valorPosicionDelMedio);
				int posicionClaveMedia = this.obtenerPosicionDeClave(nodoPadre, clavePosicionDelMedio);
				for (int i = nodoPadre.nroDeClavesNoVacias() - 1; i > posicionClaveMedia; i--) {  // ORDENANDO HIJOS
					nodoPadre.setHijo(i + 1, nodoPadre.getHijo(i));
				}

				nodoPadre.setHijo(posicionClaveMedia, nodoActual);
				nodoPadre.setHijo(posicionClaveMedia + 1, nodoSegundaDivision);
				this.raiz = nodoPadre;
				nodoPadre = NodoMVias.nodoVacio();

			}

		}

	}*/
		private void dividir(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDeAncestros) {

				NodoMVias<K, V> nodoPadre = new NodoMVias(this.orden + 1);
				NodoMVias<K, V> nodoPrimeraDivision = new NodoMVias(this.orden + 1);
				NodoMVias<K, V> nodoSegundaDivision = new NodoMVias(this.orden + 1);
				int posicionDelMedio = this.orden / 2;
				K clavePosicionDelMedio = nodoActual.getClave(posicionDelMedio - 1);
				V valorPosicionDelMedio = nodoActual.getValor(posicionDelMedio - 1);
				if (pilaDeAncestros.isEmpty()) {
						super.insertarClaveYValorOrdenadamenteEnNodo(nodoPadre, clavePosicionDelMedio,
								 valorPosicionDelMedio);
								int posicionClaveMedia = this.obtenerPosicionDeClave(nodoPadre, clavePosicionDelMedio);
								for (int i = nodoPadre.nroDeClavesNoVacias() - 1; i > posicionClaveMedia; i--) {  // ORDENANDO HIJOS
										nodoPadre.setHijo(i + 1, nodoPadre.getHijo(i));
								}

								nodoPadre.setHijo(posicionClaveMedia, nodoActual);
								nodoPadre.setHijo(posicionClaveMedia + 1, nodoSegundaDivision);
								this.raiz = nodoPadre;
								nodoPadre = NodoMVias.nodoVacio();
				}

				while (!NodoMVias.esNodoVacio(nodoPadre)) {

						for (int i = posicionDelMedio; i < this.orden; i++) { // DIVIDIENDO CLAVES
								nodoSegundaDivision.setClave(i - posicionDelMedio, nodoActual.getClave(i));
								nodoActual.setClave(i, (K) NodoMVias.DatoVacio());
						}
						nodoActual.setClave(posicionDelMedio - 1, (K) NodoMVias.DatoVacio());
						if (!pilaDeAncestros.isEmpty()) {
								nodoPadre = pilaDeAncestros.pop();    // padre: [70,100,400] actual: [75,90,91,99]

								super.insertarClaveYValorOrdenadamenteEnNodo(nodoPadre, clavePosicionDelMedio,
								 valorPosicionDelMedio);
								int posicionClaveMedia = this.obtenerPosicionDeClave(nodoPadre, clavePosicionDelMedio);
								for (int i = nodoPadre.nroDeClavesNoVacias() - 1; i > posicionClaveMedia; i--) {  // ORDENANDO HIJOS
										nodoPadre.setHijo(i + 1, nodoPadre.getHijo(i));
								}

								nodoPadre.setHijo(posicionClaveMedia, nodoActual);
								nodoPadre.setHijo(posicionClaveMedia + 1, nodoSegundaDivision);
								nodoActual = nodoPadre;
								if (nodoPadre.nroDeClavesNoVacias() <= this.nroMaxDeDatos) {
										nodoPadre = NodoMVias.nodoVacio();
								}
						}
						else {
								super.insertarClaveYValorOrdenadamenteEnNodo(nodoPadre, clavePosicionDelMedio,
								 valorPosicionDelMedio);
								int posicionClaveMedia = this.obtenerPosicionDeClave(nodoPadre, clavePosicionDelMedio);
								for (int i = nodoPadre.nroDeClavesNoVacias() - 1; i > posicionClaveMedia; i--) {  // ORDENANDO HIJOS
										nodoPadre.setHijo(i + 1, nodoPadre.getHijo(i));
								}

								nodoPadre.setHijo(posicionClaveMedia, nodoActual);
								nodoPadre.setHijo(posicionClaveMedia + 1, nodoSegundaDivision);
								this.raiz = nodoPadre;
								nodoPadre = NodoMVias.nodoVacio();

						}

				}

		}

		@Override
		public V eliminar(K claveAEliminar) throws NewException {
				if (claveAEliminar == null) {
						throw new IllegalArgumentException("Clave invalida,no se aceptan claves nulas");
				}
				Stack<NodoMVias<K, V>> pilaDeAncestros = new Stack<>();
				NodoMVias<K, V> nodoDeLaClave = this.buscarNodoDeLaClave(pilaDeAncestros,
				 claveAEliminar);
				if (NodoMVias.esNodoVacio(nodoDeLaClave)) {
						return null;
				}
				int posicionDeClave = super.obtenerPosicionDeClave(nodoDeLaClave, claveAEliminar);
				V valorAsociado = nodoDeLaClave.getValor(posicionDeClave);
				if (nodoDeLaClave.esHoja()) {
						// la Clave esta en una hoja
						super.eliminarClaveDePosicion(nodoDeLaClave, posicionDeClave);
						if (nodoDeLaClave.nroDeClavesNoVacias() < this.nroMinDeDatos) {
								if (pilaDeAncestros.isEmpty()) {
										if (!nodoDeLaClave.hayClavesNoVacias()) {
												super.vaciar();
										}
										else {
												prestarseOFusionarse(nodoDeLaClave, pilaDeAncestros);
										}
								}
						}
				}
				else {
						// Clave esta en un nodo hoja
						NodoMVias<K, V> nodoDelPredecesor = this.obtenerNodoDelPredecesor(
						 nodoDeLaClave.getHijo(posicionDeClave), pilaDeAncestros);
						K clavePredecesora = nodoDelPredecesor.getClave(
						 nodoDelPredecesor.nroDeClavesNoVacias() - 1);
						V valorDelPredecesor = nodoDelPredecesor.getValor(
						 nodoDelPredecesor.nroDeClavesNoVacias() - 1);
						super.eliminarClaveDePosicion(nodoDelPredecesor,
						 nodoDelPredecesor.nroDeClavesNoVacias() - 1);
						nodoDeLaClave.setClave(posicionDeClave, clavePredecesora);
						nodoDeLaClave.setValor(posicionDeClave, valorDelPredecesor);
						if (nodoDelPredecesor.nroDeClavesNoVacias() < this.nroMinDeDatos) {
								prestarseOFusionarse(nodoDelPredecesor, pilaDeAncestros);
						}
				}

				return valorAsociado;
		}

		private void prestarseOFusionarse(NodoMVias<K, V> nodoDeLaClave,
		 Stack<NodoMVias<K, V>> pilaDeAncestros) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		private NodoMVias<K, V> buscarNodoDeLaClave(Stack<NodoMVias<K, V>> pilaDeAncestros,
		 K claveAEliminar) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		private NodoMVias<K, V> obtenerNodoDelPredecesor(NodoMVias<K, V> nodoEnTurno,
		 Stack<NodoMVias<K, V>> pilaDeAncestros) {
				while (!nodoEnTurno.esHoja()) {
						pilaDeAncestros.push(nodoEnTurno);
						int ultimaPosicion = nodoEnTurno.nroDeClavesNoVacias();
						nodoEnTurno = nodoEnTurno.getHijo(ultimaPosicion);
				}
				return nodoEnTurno;
		}

}
