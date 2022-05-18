package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private Graph<Country, DefaultEdge> grafo;
	private BordersDAO dao;
	private Map<Integer, Country> mappaPaesi;
	private List<Country> vertici;
	
	public Model() {
		dao=new BordersDAO();
		mappaPaesi=dao.loadAllCountries();
	}
	
    public void createGraph(int anno) {
    	
    	this.grafo=new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
     	
       	List<Border> listaConfini=dao.getCountryPairs(anno);
       	vertici=dao.getVertici();
    	
		Graphs.addAllVertices(this.grafo, dao.getVertici());
		
		for(Border b: listaConfini) {
			Country paeseUno=mappaPaesi.get(b.getStateUno());
			Country paeseDue=mappaPaesi.get(b.getStateDue());
				
			if(!grafo.containsEdge(paeseUno, paeseDue))
				this.grafo.addEdge(paeseUno, paeseDue);
									
			}
		
						
	}
    	
    
    public Map<Country, Integer> stampaStati(){
    	
    	Map<Country, Integer> mappa=new HashMap<Country, Integer>();
    	for(Country c: vertici) {
    		int grado=grafo.degreeOf(c);
			mappa.put(c, grado);
		}
    	
    	return mappa;
    }
    
    
    public int getComponenteConnessa() {
    	ConnectivityInspector<Country, DefaultEdge> c=new ConnectivityInspector<Country, DefaultEdge>(this.grafo);
		
    	return c.connectedSets().size();
	}
    
    
    public Set<Country> getConfini(Country paese) {
		Set<Country> raggiungibili=new HashSet<>();
		DepthFirstIterator<Country, DefaultEdge> it=new DepthFirstIterator<>(this.grafo, paese);
		
		while(it.hasNext()) {
			raggiungibili.add(it.next());
		}
		
		return raggiungibili;
	}

    
	public Map<Integer, Country> getMappaPaesi() {
		return mappaPaesi;
	}

	
    

	
    
}
