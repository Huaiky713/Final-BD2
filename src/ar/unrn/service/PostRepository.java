package ar.unrn.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.GenerateView;
import org.ektorp.support.View;

import ar.unrn.model.Post;
import ar.unrn.model.Counter;

import java.util.stream.Collectors;

public class PostRepository extends CouchDbRepositorySupport<Post> {

    public PostRepository(CouchDbInstance dbInstance) {
        super(Post.class, dbInstance.createConnector("post", true));
        initStandardDesignDocument();
    }
    
    @GenerateView
    public List<Post> findByAutor(String autor) {
        return queryView("by_autor", autor);
    }
    
    
    public List<Counter> countByAuthor() {
    	List<Counter> countByAuthor = new ArrayList<>();
    	List<Post> posts = this.getAll();
    	
    	if (posts == null)
			return null;
		
		Map<String, List<Post>> res = posts.stream()
				.collect(Collectors.groupingBy(Post::getAutor));
		
		res.forEach((author, list) -> {
			Counter counter = new Counter(author, list.size());
			
			countByAuthor.add(counter);	
		});
		
		return countByAuthor;
    }
    
    @View( name = "all_by_fecha", map = "function(doc) { if (doc.type == 'Post' ) emit( doc.fecha, doc._id )}")    
    public List<Post> findAllByOrderAndLimit(boolean desc, int limit) {
    
    	
    	ViewQuery query = new ViewQuery()
    			.designDocId("_design/Post")   
    			.viewName("all_by_fecha")
    			.includeDocs(true)
    			.descending(desc).startKey(new Date().getTime())
    			.limit(limit);
    	
    	List<Post> posts = this.db.queryView(query, Post.class);

    	
		return posts;
    }
    
    @View( name = "filter_by_text", map = "function(doc) {var prefix = doc.texto.match(/[A-Za-z0-9]+/g); if(prefix) for(var pre in prefix) emit(prefix[pre].toLowerCase(),null);}")    
    public List<Post> filterByText(String text) {
    	    	ViewQuery query = new ViewQuery()
    			.designDocId("_design/Post")
    			.viewName("filter_by_text")
    			.includeDocs(true).key(text.toLowerCase());
    	
    	List<Post> posts = new ArrayList<Post>();
    	
    	for(Post p : this.db.queryView(query, Post.class)) {
    		if(!posts.contains(p))
    			posts.add(p);
    	}

    	
		return posts;
    }

}