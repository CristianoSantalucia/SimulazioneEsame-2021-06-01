package it.polito.tdp.genes.model;

public class Adiacenza
{
	private String id1;
	private String id2;
	private Double peso;
	public Adiacenza(String id1, String id2, Double peso)
	{
		this.id1 = id1;
		this.id2 = id2;
		this.peso = peso;
	}
	public String getId1()
	{
		return id1;
	}
	public void setId1(String id1)
	{
		this.id1 = id1;
	}
	public String getId2()
	{
		return id2;
	}
	public void setId2(String id2)
	{
		this.id2 = id2;
	}
	public Double getPeso()
	{
		return peso;
	}
	public void setPeso(Double peso)
	{
		this.peso = peso;
	}
	@Override public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id1 == null) ? 0 : id1.hashCode());
		result = prime * result + ((id2 == null) ? 0 : id2.hashCode());
		return result;
	}
	@Override public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Adiacenza other = (Adiacenza) obj;
		if (id1 == null)
		{
			if (other.id1 != null) return false;
		}
		else if (!id1.equals(other.id1)) return false;
		if (id2 == null)
		{
			if (other.id2 != null) return false;
		}
		else if (!id2.equals(other.id2)) return false;
		return true;
	}
	@Override public String toString()
	{
		return "Adiacenza [id1=" + id1 + ", id2=" + id2 + ", peso=" + peso + "]";
	} 
}
