package com.example.cadastrocurriculo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
@Table(name = "curriculo")
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
//        allowGetters = true)
public class Curriculo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@NotBlank
	private String nome;
	
	@Column
	@NotBlank
	private String email;
	
	@Column
	@NotBlank
	private String cpf;
	
	@Column
	@NotBlank
	private String dataNascimento;
	
	@Column
	@NotBlank
	private String cep;
	
	@Column
	@NotBlank
	private String sexo;
	
	@Column
	@NotBlank
	private String escolaridade;
	
	@Column
	@NotBlank
	private String formacao;
	
	@Column
	@NotBlank
	private String experienciaProfissional;

	@Column
	@NotBlank
	private String password;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getExperienciaProfissional() {
		return experienciaProfissional;
	}

	public void setExperienciaProfissional(String experienciaProfissional) {
		this.experienciaProfissional = experienciaProfissional;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
