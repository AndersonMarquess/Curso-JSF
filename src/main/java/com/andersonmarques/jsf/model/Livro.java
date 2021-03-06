package com.andersonmarques.jsf.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String titulo;
	private String isbn;
	private double preco;
	@Temporal(TemporalType.DATE)
	private Calendar dataLancamento = Calendar.getInstance();
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Autor> autores = new ArrayList<>();

	public Livro() {}
	
	public Integer getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public void adicionarAutor(Autor autor) {
		this.autores.add(autor);
	}

	public void removerAutor(Autor autor) {
		autores.remove(autor);
	}

	@Override
	public String toString() {
		return String.format("Livro [id=%s, titulo=%s, isbn=%s, preco=%s, dataLancamento=%s, autores=%s]", id, titulo,
				isbn, preco, dataLancamento, autores);
	}
}