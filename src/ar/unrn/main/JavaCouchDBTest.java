package ar.unrn.main;

import java.net.MalformedURLException;
import java.util.Date;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import ar.unrn.model.Page;
import ar.unrn.model.Post;
import ar.unrn.service.PageRepository;
import ar.unrn.service.PostRepository;

public class JavaCouchDBTest {

	public static void main(String[] args) throws MalformedURLException {
//--------------- Creating Connection--------------------------//  
		HttpClient httpClient = new StdHttpClient.Builder().url("http://localhost:5984").username("admin")
				.password("1234").build();

		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);

		createPages(dbInstance);
		createPosts(dbInstance);
	}

	private static void createPages(CouchDbInstance dbInstance) {
		CouchDbConnector dbPage = new StdCouchDbConnector("page", dbInstance);
		dbPage.createDatabaseIfNotExists();
		
		PageRepository pageRepository = new PageRepository(dbInstance);
		
		Page page = new Page();
		
		//Pagina inicial
		
		page.setTitulo("Infusiones");
		page.setTexto("Una infusión es una bebida obtenida de las hojas, las flores, las raíces, las cortezas, los frutos o las semillas de ciertas hierbas y plantas, que pueden ser aromáticas o no. En concreto, a dichas hojas, flores, frutos o semillas se les vierte agua caliente —o se introducen en agua caliente—, sin que esta agua llegue al punto de ebullición.");
		page.setAutor("Armando Paredes");
		page.setFecha(new Date(120, 7, 10).getTime());
		page.setId("5f7b94460da67d816fe65169");
		
		pageRepository.add(page);
	}

	private static void createPosts(CouchDbInstance dbInstance) {
		
	
		CouchDbConnector dbPost = new StdCouchDbConnector("post", dbInstance);
		dbPost.createDatabaseIfNotExists();
		
		PostRepository postRepository = new PostRepository(dbInstance);
		
		Post post = new Post();

		post.setTitulo("Café");
		post.setTexto("El café es la bebida que se obtiene a partir de los granos tostados y molidos de los frutos de la planta del café (cafeto); es altamente estimulante por su contenido de cafeína  una sustancia psicoactiva. Este producto es uno de los más comercializados del mundo y una de las tres bebidas1​ más consumidas del mundo (junto con el agua y el té). Suele tomarse durante el desayuno, después de éste o incluso como único desayuno, aunque también se suele tomar en la merienda, o después del almuerzo o cena para entablar conversaciones o solo por costumbre. Es una de las bebidas sin alcohol más socializadoras en muchos países. El gusto por el café no es espontáneo, sino que debe cultivarse, puesto que su sabor es fuerte y amargo.");
		post.setResumen("El café es la bebida que se obtiene a partir de los granos tostados y molidos de los frutos de la planta del café.");
		post.setAutor("Esteban Quito");
		post.setFecha(new Date(121, 5, 3).getTime());
		post.addTag("Café");
		post.addTag("Infusiones");
		post.addLink("https://cafecom.ec");
		post.addLink("https://es.wikipedia.org/wiki/Café");
		
		postRepository.add(post);
		
		post = new Post();

		post.setTitulo("Té");
		post.setTexto("El té es la infusión de las hojas y brotes de la planta del té (Camellia sinensis).La popularidad de esta bebida es solamente sobrepasada por el agua. Su sabor es fresco, ligeramente amargo y astringente; este gusto es agradable para mucha gente.");
		post.setResumen("El té es la infusión de las hojas y brotes de la planta del té (Camellia sinensis).");
		post.setAutor("Susana Horia");
		post.setFecha(new Date(115, 6, 3).getTime());
		post.addTag("Té");
		post.addTag("Infusiones");
		post.addLink("https://es.wikipedia.org/wiki/Té");
		
		postRepository.add(post);
		
		post = new Post();

		post.setTitulo("Té de cascarilla");
		post.setTexto("La infusión puede prepararse hirviendo la cascarilla en agua o leche y, opcionalmente, añadiendo azúcar.\r\n"
				+ "Sin embargo, su uso más frecuente es en el mate con cascarilla. Se prepara mezclando cascarilla con yerba mate y armando (preparando) el mate con esta mezcla. También se puede prescindir de esta mezcla y utilizarla sola en el mate de cascarilla, que no incluye yerba mate.Posee un sabor más frutal que el del chocolate y más ácido y astringente que la cocoa.");
		post.setResumen("El té de cascarilla es una bebida típica de la gastronomía de Uruguay que se realiza mediante la infusión de la cascarilla de cacao en agua o leche.");
		post.setAutor("Susana Horia");
		post.setFecha(new Date(113,10,7).getTime());
		post.addTag("Té");
		post.addTag("Cascarilla");
		post.addTag("Infusiones");
		post.addLink("https://es.wikipedia.org/wiki/T%C3%A9_de_cascarilla");
		post.addLink("https://es.wikipedia.org/wiki/Croton_eluteria");
		
		postRepository.add(post);
		
		post = new Post();

		post.setTitulo("Mate");
		post.setTexto("El mate es una infusión hecha con hojas de yerba mate (Ilex paraguariensis), planta originaria de las cuencas de los ríos Paraná, Paraguay y Uruguay. Estas plantas previamente secadas, cortadas y molidas forman la yerba mate, la cual tiene sabor amargo debido a los taninos de sus hojas. Por esto, hay quienes gustan de endulzar un poco el mate con azúcar, stevia o endulzante no calórico, pero comúnmente se toma amargo. La espuma que se genera al «cebar» se debe a los glucósidos que la yerba mate contiene.");
		post.setResumen("El mate es una infusión hecha con hojas de yerba mate (Ilex paraguariensis).");
		post.setAutor("Susana Horia");
		post.setFecha(new Date().getTime());
		post.addTag("Mate");
		post.addTag("Infusiones");
		post.addLink("https://es.wikipedia.org/wiki/Mate_(infusión)");
		
		postRepository.add(post);
		
		
		post = new Post();

		post.setTitulo("Té de cannabis");
		post.setTexto("El té, infusión o leche de cannabis (o de marihuana) es una bebida de cannabis preparada al infusionar varias partes de la planta de cannabis en leche o agua con mantequilla caliente. Se puede encontrar ya preparado para la venta en formato de sobres en las grow shops. La infusión de marihuana se aprecia en los tratamientos fitoterapéuticos de cannabis como analgésico, desinflamatorio y paliativo de varios síntomas en enfermedades específicas.");
		post.setResumen("El té, infusión o leche de cannabis (o de marihuana) es una bebida de cannabis preparada al infusionar varias partes de la planta de cannabis en leche o agua con mantequilla caliente. ");
		post.setAutor("Armando Paredes");
		post.setFecha(new Date(121,4,5).getTime());
		post.addTag("Té");
		post.addTag("Cannabis");
		post.addTag("Infusiones");
		post.addLink("https://es.wikipedia.org/wiki/Té_de_cannabis");
		
		postRepository.add(post);

	}
}