package it.polito.tdp.genes.model;

import java.time.YearMonth;

public class Evento implements Comparable<Evento>
{
	public enum tipoEvento {NUOVO_MESE, CONTINUA, CAMBIA}; 
	
	private YearMonth anno; 
	private Ingegnere ing; 
	private tipoEvento tipo;
	public Evento(YearMonth anno, Ingegnere ing, tipoEvento tipo)
	{
		super();
		this.anno = anno;
		this.ing = ing;
		this.tipo = tipo;
	}
	public YearMonth getAnno()
	{
		return anno;
	}
	public void setAnno(YearMonth anno)
	{
		this.anno = anno;
	}
	public Ingegnere getIng()
	{
		return ing;
	}
	public void setIng(Ingegnere ing)
	{
		this.ing = ing;
	}
	public tipoEvento getTipo()
	{
		return tipo;
	}
	public void setTipo(tipoEvento tipo)
	{
		this.tipo = tipo;
	}
	@Override public String toString()
	{
		return "Evento [anno=" + anno + ", ing=" + ing + ", tipo=" + tipo + "]";
	}
	@Override public int compareTo(Evento o)
	{
		return this.anno.compareTo(o.anno);
	}
	 
}
