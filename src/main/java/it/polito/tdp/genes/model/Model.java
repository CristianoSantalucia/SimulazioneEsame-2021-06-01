package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model
{
	private GenesDao dao;
	private Map<String, Genes> geni;
	private Graph<Genes, DefaultWeightedEdge> grafo;

	// sim
	Simulatore theSims;

	public Model()
	{
		this.dao = new GenesDao();
		this.theSims = new Simulatore();
	}

	public void creaGrafo()
	{
		this.geni = new TreeMap<>();
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.dao.getVertici(this.geni);
		// Vertici
		Graphs.addAllVertices(this.grafo, this.geni.values());
		// Archi
		this.creaArchi(this.dao.getAdiacenze());
	}

	private void creaArchi(List<Adiacenza> adiacenze)
	{
		for (Adiacenza a : adiacenze)
		{
			Genes g1 = this.geni.get(a.getId1());
			Genes g2 = this.geni.get(a.getId2());
			Double peso = a.getPeso();
			if (g1 != null && g2 != null)
			{
				if (g1.getChromosome() == g2.getChromosome())
					Graphs.addEdgeWithVertices(this.grafo, g1, g2, 2 * Math.abs(peso));
				else Graphs.addEdgeWithVertices(this.grafo, g1, g2, Math.abs(peso));
			}
		}
	}

	public int getNumVertici()
	{
		return this.grafo.vertexSet().size();
	}

	public int getNumArchi()
	{
		return this.grafo.edgeSet().size();
	}

	public Set<Genes> getVertici()
	{
		return this.grafo.vertexSet();
	}

	public Graph<Genes, DefaultWeightedEdge> getGrafo()
	{
		return this.grafo;
	}

	public List<Genes> getAdiacenti(Genes g)
	{
		return Graphs.neighborListOf(this.grafo, g);
	}

	public String getVicini(Genes source)
	{
		List<Genes> geniVicini = Graphs.neighborListOf(this.grafo, source);
		List<Vicino> vicini = new ArrayList<>();
		for (Genes g : geniVicini)
		{
			Vicino v = new Vicino(g, this.grafo.getEdgeWeight(this.grafo.getEdge(source, g)));
			vicini.add(v);
		}
		Collections.sort(vicini);
		String s = "";
		for (Vicino v : vicini)
			s += "\n" + v.getGeni() + " PESO : " + v.getPeso();
		return s;
	}

	// simulazione
	public void init(Genes base, int numIng)
	{
		this.theSims.init(grafo, base, numIng);
		this.theSims.simula();
	}
}
