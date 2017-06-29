/**
 * 
 */
package br.com.processo.campanha.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Vagner
 *
 */
@Entity
@Table(name="campanha")
public class Campanha implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4007682420598389152L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	@Column(name="DTINICIO")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value="dtInicio")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dtInicio;
	
	@Column(name="DTFIM")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value="dtFim")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dtFim;
	
	@Column(name="IDCLUBE")
	private Long idClube;
	
	

	/**
	 * 
	 */
	public Campanha() {
	}

	
	/**
	 * @param id
	 * @param nome
	 * @param dtInicio
	 * @param dtFim
	 * @param idClube
	 */
	public Campanha(Long id, String nome, LocalDate dtInicio, LocalDate dtFim, Long idClube) {
		this.id = id;
		this.nome = nome;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.idClube = idClube;
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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the dtInicio
	 */
	public LocalDate getDtInicio() {
		return dtInicio;
	}

	/**
	 * @param dtInicio the dtInicio to set
	 */
	public void setDtInicio(LocalDate dtInicio) {
		this.dtInicio = dtInicio;
	}

	/**
	 * @return the dtFim
	 */
	public LocalDate getDtFim() {
		return dtFim;
	}

	/**
	 * @param dtFim the dtFim to set
	 */
	public void setDtFim(LocalDate dtFim) {
		this.dtFim = dtFim;
	}

	/**
	 * @return the idClube
	 */
	public Long getIdClube() {
		return idClube;
	}

	/**
	 * @param idClube the idClube to set
	 */
	public void setIdClube(Long idClube) {
		this.idClube = idClube;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtFim == null) ? 0 : dtFim.hashCode());
		result = prime * result + ((dtInicio == null) ? 0 : dtInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idClube == null) ? 0 : idClube.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		if (!(obj instanceof Campanha)) {
			return false;
		}
		Campanha other = (Campanha) obj;
		if (dtFim == null) {
			if (other.dtFim != null) {
				return false;
			}
		} else if (!dtFim.equals(other.dtFim)) {
			return false;
		}
		if (dtInicio == null) {
			if (other.dtInicio != null) {
				return false;
			}
		} else if (!dtInicio.equals(other.dtInicio)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (idClube == null) {
			if (other.idClube != null) {
				return false;
			}
		} else if (!idClube.equals(other.idClube)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Campanha [id=" + id + ", nome=" + nome + ", dtInicio=" + dtInicio + ", dtFim=" + dtFim + ", idClube="
				+ idClube + "]";
	}
	
	
	

}
