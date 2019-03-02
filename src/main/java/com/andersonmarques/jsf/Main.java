package com.andersonmarques.jsf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.andersonmarques.jsf.dao.DAO;
import com.andersonmarques.jsf.model.Autor;
import com.andersonmarques.jsf.model.Livro;

public class Main {

	public static void main(String[] args) {
		popularBanco();
		System.out.println("Banco populado com sucesso.");
	}

	private static void popularBanco() {
		DAO<Autor> em = new DAO<Autor>(Autor.class);
		DAO<Livro> livroDAO = new DAO<Livro>(Livro.class);

		Autor assis = geraAutor("Machado de Assis");
		Autor amado = geraAutor("Jorge Amado");
		Autor coelho = geraAutor("Paulo Coelho");
		
		em.gravar(assis);
		em.gravar(amado);
		em.gravar(coelho);

		Livro casmurro = geraLivro("978-8-52-504464-8", "Dom Casmurro", "10/01/1899", 24.90, assis);
		Livro memorias = geraLivro("978-8-50-815415-9", "Memorias Postumas de Bras Cubas", "01/01/1881", 19.90, assis);
		Livro quincas = geraLivro("978-8-50-804084-1", "Quincas Borba", "01/01/1891", 16.90, assis);

		livroDAO.gravar(casmurro);
		livroDAO.gravar(memorias);
		livroDAO.gravar(quincas);

		Livro alquemista = geraLivro("978-8-57-542758-3", "O Alquimista", "01/01/1988", 19.90, coelho);
		Livro brida = geraLivro("978-8-50-567258-7", "Brida", "01/01/1990", 12.90, coelho);
		Livro valkirias = geraLivro("978-8-52-812458-8", "As Valkirias", "01/01/1992", 29.90, coelho);

		livroDAO.gravar(alquemista);
		livroDAO.gravar(brida);
		livroDAO.gravar(valkirias);

		Livro capitaes = geraLivro("978-8-50-831169-1", "Capitaes da Areia", "01/01/1937", 6.90, amado);
		Livro flor = geraLivro("978-8-53-592569-9", "Dona Flor e Seus Dois Maridos", "01/01/1966", 18.90, amado);

		livroDAO.gravar(capitaes);
		livroDAO.gravar(flor);

	}

	private static Autor geraAutor(String nome) {
		Autor autor = new Autor();
		autor.setNome(nome);
		return autor;
	}

	private static Livro geraLivro(String isbn, String titulo, String data, double preco, Autor autor) {
		Livro livro = new Livro();
		livro.setIsbn(isbn);
		livro.setTitulo(titulo);
		livro.setPreco(preco);
		livro.adicionarAutor(autor);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		livro.setDataLancamento(calendar);
		return livro;
	}
}
