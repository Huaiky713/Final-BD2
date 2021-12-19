package ar.unrn.api;

import static spark.Spark.get;

import java.net.MalformedURLException;
import java.util.List;

import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;
import org.json.simple.JSONArray;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import ar.unrn.model.Page;
import ar.unrn.model.Post;
import ar.unrn.service.PageRepository;
import ar.unrn.service.PostRepository;
import spark.Spark;

public class BlogAPI {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws MalformedURLException {

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		HttpClient httpClient = new StdHttpClient.Builder()
				.url("http://localhost:5984")
				.username("admin")
				.password("1234").build();
		
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);

		PageRepository pageRepository = new PageRepository(dbInstance);
		PostRepository postRepository = new PostRepository(dbInstance);

		get("/search/:text", (req, res) -> {
			res.header("Access-Control-Allow-Origin", "*");

			// Recupero la palabra/frase ingresada por el usuario
			String text = req.params("text");
			
			List<Post> posts = postRepository.filterByText(text);

			if (posts == null)
				return "";
			
			JSONArray jsonPages = new JSONArray();
			posts.forEach(p -> jsonPages.add(p.toJsonForFilterByText()));

			return ow.writeValueAsString(jsonPages);
			
		});
		
		get("/pagina-id/:id", (req, res) -> {
			res.header("Access-Control-Allow-Origin", "*");
			// Recupero el id que viene por parámetro
			String paginaId = req.params("id");
			Page page = pageRepository.get(paginaId);

			if (page == null)
				return "";

			JSONArray jsonPages = new JSONArray();
			jsonPages.add(page.toJson());
			return ow.writeValueAsString(jsonPages);
		});

		get("/byautor", (req, res) -> {
			res.header("Access-Control-Allow-Origin", "*");

			return ow.writeValueAsString(postRepository.countByAuthor());
		});

		get("/ultimos4posts", (req, res) -> {
			res.header("Access-Control-Allow-Origin", "*");

			List<Post> posts = postRepository.findAllByOrderAndLimit(true, 4);

			if (posts == null)
				return "";

			JSONArray jsonPages = new JSONArray();
			posts.forEach(p -> jsonPages.add(p.toJson()));

			return ow.writeValueAsString(jsonPages);
		});

		get("/posts-autor/:nombreautor", (req, res) -> {
			res.header("Access-Control-Allow-Origin", "*");

			// Recupero el nombre del autor que viene como parámetro
			String nombreAutor = req.params("nombreautor");

			List<Post> posts = postRepository.findByAutor(nombreAutor);

			if (posts == null)
				return "";

			JSONArray jsonPages = new JSONArray();
			posts.forEach(p -> jsonPages.add(p.toJson()));

			return ow.writeValueAsString(jsonPages);
		});

		get("/post-id/:id", (req, res) -> {
			res.header("Access-Control-Allow-Origin", "*");

			// Recupero el id del post que viene por parámetro
			String postId = req.params("id");

			Post post = postRepository.get(postId);

			if (post == null)
				return "";

			JSONArray jsonPages = new JSONArray();
			jsonPages.add(post.toJson());
			return ow.writeValueAsString(jsonPages);
		});

	

		Spark.exception(Exception.class, (exception, request, response) -> {
			exception.printStackTrace();
		});

	}
}