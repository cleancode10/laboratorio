/**
 * 
 */
package br.com.processo.sociotorcedor.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Vagner
 *
 */
@JsonAutoDetect
@Entity
@Table(name="socio")
public class Socio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3020310686942571023L;

	@Id
	@GeneratedValue
	@ApiModelProperty(value="id")
	private Long id;
	
	@ApiModelProperty(value="nome")
	@Column(name="nome")
	private String nome;
	
	@ApiModelProperty(value="email")
	private String email;
	
	@ApiModelProperty(value="dtNascimento")
	@Column(name="DTNACIMENTO")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty(value="dtNascimento")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dtNascimento;
	
	@ApiModelProperty(value="idClube")
	@Column(name="IDCLUBE")
	private Long idClube;
	
	@OneToMany(mappedBy="socio", fetch=FetchType.LAZY)
	private List<CampanhaAssociada> campanhas;
	
	public Socio() {
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the dtNascimento
	 */
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	/**
	 * @param dtNascimento the dtNascimento to set
	 */
	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
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



	/**
	 * @return the campanhas
	 */
	public List<CampanhaAssociada> getCampanhas() {
		return campanhas;
	}


	/**
	 * @param campanhas the campanhas to set
	 */
	public void setCampanhas(List<CampanhaAssociada> campanhas) {
		this.campanhas = campanhas;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campanhas == null) ? 0 : campanhas.hashCode());
		result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (!(obj instanceof Socio)) {
			return false;
		}
		Socio other = (Socio) obj;
		if (campanhas == null) {
			if (other.campanhas != null) {
				return false;
			}
		} else if (!campanhas.equals(other.campanhas)) {
			return false;
		}
		if (dtNascimento == null) {
			if (other.dtNascimento != null) {
				return false;
			}
		} else if (!dtNascimento.equals(other.dtNascimento)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
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
		final int maxLen = 10;
		return "Socio [id=" + id + ", nome=" + nome + ", email=" + email + ", dtNascimento=" + dtNascimento
				+ ", idClube=" + idClube + ", campanhas="
				+ (campanhas != null ? campanhas.subList(0, Math.min(campanhas.size(), maxLen)) : null) + "]";
	}


	

}
