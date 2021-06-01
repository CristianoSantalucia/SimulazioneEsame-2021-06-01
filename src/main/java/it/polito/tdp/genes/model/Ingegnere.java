package it.polito.tdp.genes.model;

public class Ingegnere
{
	private int codIng; 
	private Genes lavoraA;
	
	public Ingegnere(int codIng, Genes lavoraA)
	{
		this.codIng = codIng; 
		this.lavoraA = lavoraA;
	}
	public int getCodIng()
	{
		return codIng;
	}
	public void setCodIng(int codIng)
	{
		this.codIng = codIng;
	}
	public Genes getLavoraA()
	{
		return lavoraA;
	}
	public void setLavoraA(Genes lavoraA)
	{
		this.lavoraA = lavoraA;
	}
	@Override public String toString()
	{
		return "Ingegniere " + codIng + ", lavoraA=" + lavoraA + "]";
	}
	@Override public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + codIng;
		return result;
	}
	@Override public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Ingegnere other = (Ingegnere) obj;
		if (codIng != other.codIng) return false;
		return true;
	} 
}
