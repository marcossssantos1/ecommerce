package com.marcossantos.ecommerce.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria", indexes = { @Index(name = "idx_slug", columnList = "slug"),
		@Index(name = "idx_ativa", columnList = "ativa") })
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(columnDefinition = "TEXT")
	private String descricao;

	@Column(nullable = false, length = 100, unique = true)
	private String slug;

	@Column(nullable = false)
	private Boolean ativa = true;

	@PrePersist
	@PreUpdate
	private void normalizarSlug() {
		if (slug != null) {
			slug = slug.toLowerCase().trim().replace(" ", "-");
		}
	}

	public Categoria() {
	}

	public Categoria(Long id, String nome, String descricao, String slug, Boolean ativa) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.slug = slug;
		this.ativa = ativa;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Boolean getAtiva() {
		return ativa;
	}

	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

}
