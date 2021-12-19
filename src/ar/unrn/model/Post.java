package ar.unrn.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

	@JsonProperty("_id")
	private String id;
	@JsonProperty("_rev")
	private String revision;
	
	private String type = "Post";
	
	private String titulo;
	private String resumen;
	private String texto;
	private String autor;
	private Long fecha;

	private List<String> tags = new ArrayList<>();
	private List<String> links = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public void addTag(String tag) {
		this.tags.add(tag);
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}
	
	public void addLink(String link) {
		this.links.add(link);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@SuppressWarnings({ "unchecked" })
	public JSONObject toJson() {
		JSONObject id = new JSONObject();
		id.put("$oid", getId());

		JSONObject postJson = new JSONObject();
		postJson.put("_id", id);
		postJson.put("titulo", getTitulo());
		postJson.put("resumen", getResumen());
		postJson.put("texto", getTexto());
		postJson.put("autor", getAutor());
		postJson.put("links-relacionados", getLinks());

		postJson.put("tags", getTags());

		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		JSONObject date = new JSONObject();
		date.put("$date", formater.format(new Date(this.fecha)));

		postJson.put("fecha", date);

		return postJson;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJsonForFilterByText() {
		JSONObject id = new JSONObject();
		id.put("$oid", getId());

		JSONObject postJson = new JSONObject();
		postJson.put("_id", id);
		postJson.put("titulo", getTitulo());
		postJson.put("resumen", getResumen());
		postJson.put("autor", getAutor());
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		JSONObject date = new JSONObject();
		date.put("$date", formater.format(new Date(this.fecha)));

		postJson.put("fecha", date);

		return postJson;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
