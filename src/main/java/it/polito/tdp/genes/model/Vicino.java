package it.polito.tdp.genes.model;

public class Vicino implements Comparable<Vicino>
{
	private Genes geni; 
	private Double peso;
	public Vicino(Genes geni, Double peso)
	{
		super();
		this.geni = geni;
		this.peso = peso;
	}
	public Genes getGeni()
	{
		return geni;
	}
	public void setGeni(Genes geni)
	{
		this.geni = geni;
	}
	public Double getPeso()
	{
		return peso;
	}
	public void setPeso(Double peso)
	{
		this.peso = peso;
	}
	@Override public String toString()
	{
		return "Vicino [geni=" + geni + ", peso=" + peso + "]";
	}
	
	@Override public int compareTo(Vicino v2)
	{
		return - (int) (this.peso - v2.getPeso());
	} 
}
