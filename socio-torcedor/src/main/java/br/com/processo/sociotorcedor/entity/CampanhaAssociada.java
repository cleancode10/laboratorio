/**
 * 
 */
package br.com.processo.sociotorcedor.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Vagner
 *
 */
@Entity
@Table(name="campanhaassociada")
public class CampanhaAssociada implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -9070993883646410630L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	//@Column(name="IDSOCIO")
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="SOCIO", referencedColumnName="id")
	private Socio socio;
	
	@Column(name="CAMPANHA")
	private Long campanha;
	
	
	

	/**
	 * 
	 */
	public CampanhaAssociada() {
	}

	/**
	 * @param socio
	 * @param campanha
	 */
	public CampanhaAssociada(Socio socio, Long campanha) {
		this.socio = socio;
		this.campanha = campanha;
	}

	/**
	 * @param id
	 * @param socio
	 * @param campanha
	 */
	public CampanhaAssociada(Long id, Socio socio, Long campanha) {
		this.id = id;
		this.socio = socio;
		this.campanha = campanha;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the socio
	 */
	public Socio getSocio() {
		return socio;
	}

	/**
	 * @param socio the socio to set
	 */
	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	/**
	 * @return the campanha
	 */
	public Long getCampanha() {
		return campanha;
	}

	/**
	 * @param campanha the campanha to set
	 */
	public void setCampanha(Long campanha) {
		this.campanha = campanha;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campanha == null) ? 0 : campanha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((socio == null) ? 0 : socio.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CampanhaAssociada)) {
			return false;
		}
		CampanhaAssociada other = (CampanhaAssociada) obj;
		if (campanha == null) {
			if (other.campanha != null) {
				return false;
			}
		} else if (!campanha.equals(other.campanha)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (socio == null) {
			if (other.socio != null) {
				return false;
			}
		} else if (!socio.equals(other.socio)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CampanhaAssociada [id=" + id + ", socio=" + socio + ", campanha=" + campanha + "]";
	}
	
	
	


	


}
