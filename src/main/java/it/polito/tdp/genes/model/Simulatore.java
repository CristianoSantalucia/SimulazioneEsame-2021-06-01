package it.polito.tdp.genes.model;

import java.nio.charset.CodingErrorAction;
import java.time.Duration;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import com.sun.marlin.DMarlinRenderingEngine;

import it.polito.tdp.genes.model.Evento.tipoEvento;

public class Simulatore
{
	private Model model;
	Graph<Genes, DefaultWeightedEdge> grafo;

	private DefaultWeightedEdge precedente = null;

	private int numIng;
	private List<Ingegnere> ingegneri;
	private PriorityQueue<Evento> eventi;

	public void init(Graph<Genes, DefaultWeightedEdge> grafo, Genes partenza, int numIng)
	{
		this.model = new Model();
		this.grafo = grafo;

		this.eventi = new PriorityQueue<>();
		this.ingegneri = new ArrayList<>();
		this.numIng = numIng;

		Genes gPartenza = partenza;

		for (int i = 1; i <= numIng; i++)
		{
			Ingegnere ing = new Ingegnere(i, gPartenza);
			this.ingegneri.add(ing);

			for (int j = 2000; j <= 2003; j++)
			{
				for (int k = 1; k <= 12; k++)
				{
					Evento evento = new Evento(YearMonth.of(j, k), ing, tipoEvento.NUOVO_MESE);
					eventi.add(evento);
				}
			}
		}
	}

	public String simula()
	{
		while (!this.eventi.isEmpty())
		{
			Evento e = this.eventi.poll();
			System.out.println(e);
			processEvent(e);
		}
		//stampa
		Map<Genes, Integer> numeri = new HashMap<>();
		for (Genes g : this.grafo.vertexSet())
		{
			int num = 0;
			for (Ingegnere i : this.ingegneri)
			{
				if (g.equals(i.getLavoraA()))
				{
					num++;
				}
			}
			if (num > 0)
				numeri.put(g, num);
		}

		return "RESULT: " + numeri;
	}
	 
	private void processEvent(Evento evento)
	{
		Evento e = null;
		switch (evento.getTipo())
		{
			case NUOVO_MESE:
				int prob = (int) (Math.random() * 100);
				if (prob <= 30)
				{
					e = new Evento(evento.getAnno(), evento.getIng(), tipoEvento.CONTINUA);
					this.eventi.add(e);
				}
				else
				{
					evento.getIng().setLavoraA(nuovoLavoro(evento.getIng()));
					e = new Evento(evento.getAnno(), evento.getIng(), tipoEvento.CAMBIA);
					this.eventi.add(e);
				}
				break;
			default:
				break;
		}
	}

	private Genes nuovoLavoro(Ingegnere ing)
	{
		Set<DefaultWeightedEdge> archi = this.grafo.edgesOf(ing.getLavoraA());
		DefaultWeightedEdge bestEdge = null;
		Double terminator = 0.0;
		for (DefaultWeightedEdge e : archi)
		{
			terminator += this.grafo.getEdgeWeight(e);
		}
		Map<DefaultWeightedEdge, Double> mappa = new HashMap<>();
		for (DefaultWeightedEdge e : archi)
		{
			Double res = (this.grafo.getEdgeWeight(e) / terminator) * 100;
			mappa.put(e, res);
		}
		double bestDiff = 1000;
		Double random = Math.random();
		for (DefaultWeightedEdge e : mappa.keySet())
		{
			double num1 = mappa.get(e);
			double diff = Math.abs(num1 - random);
			if (diff < bestDiff)
			{
				bestDiff = diff;
				bestEdge = e;
			}
		}
		return Graphs.getOppositeVertex(this.grafo, bestEdge, ing.getLavoraA());
	}
}
