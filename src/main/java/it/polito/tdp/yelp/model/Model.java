package it.polito.tdp.yelp.model;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	private YelpDao dao;
	private Graph<Review, DefaultWeightedEdge> grafo;
	private Map<String, Review> idMap;
	private List<Review> vertici;
	
	public Model() {
		dao= new YelpDao();
		idMap=new HashMap<String, Review>();
		//riempio mappa
		dao.getAllReviewsMap(idMap);
	}
	
	public void creaGrafo(String b) {
		grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		//Nodi= tutte le recensioni(Reviews) del locale commerciale b.
		vertici=new ArrayList<>(dao.getVertici(b, idMap));
		Graphs.addAllVertices(this.grafo, vertici);
		/*Ogni recensione r va collegata con tutte le sue recensioni consecutive, ovvero con le recensioni che hanno una
         data di pubblicazione (colonna review_date, Reviews) PIù RECENTE.Archi orientati da recensione r VERSO recensioni più recenti
         Il PESO (positivo) è dato dalla differenza,in giorni, tra la data di pubblicazione di r e la data di pubblicazione della recensione più recente; 
         se  0, l’arco non deve essere inserito. */
		List<Adiacenza> adiacenze=dao.getAdiacenze(b, idMap);
		double peso=0;
		for(Adiacenza a: adiacenze) {
			if(this.grafo.containsVertex(a.getR1()) && this.grafo.containsVertex(a.getR2())){
				peso=Math.abs(ChronoUnit.DAYS.between(a.getData1(),a.getData2()));
				if(peso!=0) {
				if(a.getData1().isBefore(a.getData2())) {
					
					Graphs.addEdge(this.grafo, a.getR1(), a.getR2(), peso);
				}
				else {
					Graphs.addEdge(this.grafo, a.getR2(), a.getR1(), peso);
				}
			}
			}
		}
	}
	/*Stampa recensione per cui il numero di archi USCENTI sia MAX. Stampa l’identificativo della recensione (review_id,Reviews) 
	 *e il relativo numero di archi uscenti. Nel caso in cui ci sia più di una recensione che abbia lo stesso numero di archi uscenti, 
	 *il programma deve stamparle tutte */
	public List<Rmax> rMax(){
		List<Rmax> result= new ArrayList<>();
		int max=0; int sommau=0;
		//scorro vertici, calcolo numero di archi
		for(Review r: vertici) {
			if (this.grafo.outDegreeOf(r)> max){
				max=this.grafo.outDegreeOf(r);
			}		
		}
		
		for(Review r: vertici) {
			if (this.grafo.outDegreeOf(r) == max){
				result.add(new Rmax(r.getReviewId(), max));
			}		
		}
		return result;
	}
	
	public int Nvertici() {
		return vertici.size();
	}
	
	public int Narchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<String> getCitta(){
		return dao.getAllCities();
	}
	
	public List<Business> getLocali(String c){
		return dao.getAllLocali(c);
	}
	
}
