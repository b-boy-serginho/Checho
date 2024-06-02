/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.Grafos;

/**
 *
 * @author Asrock
 */
public class AdyacenteConPeso implements Comparable<AdyacenteConPeso> {

		private int indiceVertice;
		private double peso;

		public AdyacenteConPeso(int indice) {
				this.indiceVertice = indice;
		}

		public AdyacenteConPeso(int indice, double peso) {
				this.indiceVertice = indice;
				this.peso = peso;
		}

		public int getIndiceVertice() {
				return indiceVertice;
		}

		public void setIndiceVertice(int indice) {
				this.indiceVertice = indice;
		}

		public double getPeso() {
				return peso;
		}

		public void setPeso(double peso) {
				this.peso = peso;
		}

		public int compareTo(AdyacenteConPeso otroAdyacenteConPeso) {
				/*Integer esteVertice =this.indiceVertice;
		Integer elOtroVertice = otroAdyacenteConPeso.indiceVertice;
		return esteVertice.compareTo(elOtroVertice);
				 */

				if (otroAdyacenteConPeso == null) {
						return -1;
				}
				if (this.indiceVertice > otroAdyacenteConPeso.indiceVertice) {
						return 1;
				}
				if (this.indiceVertice < otroAdyacenteConPeso.indiceVertice) {
						return -1;
				}
				return 0;
		}

		@Override
		public int hashCode() {
				int hash = 5;
				hash = 89 * hash + this.indiceVertice;
				return hash;
		}

		@Override
		public boolean equals(Object otroObjeto) {
				if (this == otroObjeto) {
						return true;
				}
				if (otroObjeto == null) {
						return false;
				}
				if (getClass() != otroObjeto.getClass()) {
						return false;
				}
				final AdyacenteConPeso other = (AdyacenteConPeso) otroObjeto;
				if (this.indiceVertice != other.indiceVertice) {
						return false;
				}
				return true;
		}

}
