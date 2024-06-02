/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Arboles;

/**
 *
 * @author Asrock
 */
public  class NodoBinario<K, V> {

	private K clave;
	private V valor;
	private NodoBinario<K, V> hijoIzquierdo;
	private NodoBinario<K, V> hijoDerecho;

	public NodoBinario() {
	}

	public NodoBinario(K clave, V valor) {
		this.clave = clave;
		this.valor = valor;
	}

	public K getClave() {
		return clave;
	}

	public void setClave(K clave) {
		this.clave = clave;
	}

	public V getValor() {
		return valor;
	}

	public void setValor(V valor) {
		this.valor = valor;
	}

	public NodoBinario<K, V> getHijoIzquierdo() {
		return hijoIzquierdo;
	}

	public void setHijoIzquierdo(NodoBinario<K, V> hijoIzquierdo) {
		this.hijoIzquierdo = hijoIzquierdo;
	}

	public NodoBinario<K, V> getHijoDerecho() {
		return hijoDerecho;
	}

	public void setHijoDerecho(NodoBinario<K, V> hijoDerecho) {
		this.hijoDerecho = hijoDerecho;
	}

	public static NodoBinario nodoVacio() {
		return null;
	}

	public static boolean esNodoVacio(NodoBinario unNodo) {
		return unNodo == NodoBinario.nodoVacio();
	}

	public boolean esVacioElHijoIzquierdo() {
		return this.esNodoVacio(this.getHijoIzquierdo());
	}

	public boolean esVacioElHijoDerecho() {
		return this.esNodoVacio(this.getHijoDerecho());
	}

	public boolean esHoja() {
		return this.esVacioElHijoIzquierdo()
				&& this.esVacioElHijoDerecho();
	}

	public boolean esNodoCompleto() {
		return !this.esVacioElHijoIzquierdo()
				&& !this.esVacioElHijoDerecho();
	}

}
