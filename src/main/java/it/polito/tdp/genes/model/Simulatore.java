package it.polito.tdp.genes.model;

import java.security.SignatureException;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.genes.model.Evento.tipoEvento;

public class Simulatore
{
	private int numIng;
	private List<Ingegnere> ingegneri;
	private PriorityQueue<Evento> eventi;

	public void init(Genes partenza, int numIng)
	{
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

	public void simula()
	{
		while (!this.eventi.isEmpty())
		{
			Evento e = this.eventi.poll();
			System.out.println(e);
			processEvent(e);
		}
	}

	private void processEvent(Evento evento)
	{
		switch (evento.getTipo())
		{
			case NUOVO_MESE:
				int prob = (int) (Math.random() * 100);
				if (prob <= 30)
				{
					Evento e = new Evento(evento.getAnno(),evento.getIng(),tipoEvento.CONTINUA);
					this.eventi.add(e); 
				}
				else
				{
//					Evento e = new Evento(evento.getMese(),tipoEvento.CAMBIA);
//					this.eventi.add(e); 
				}
				break;

			default:
				break;
		} 
	}
}
