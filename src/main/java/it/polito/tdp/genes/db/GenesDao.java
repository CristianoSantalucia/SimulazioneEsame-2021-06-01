package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;

public class GenesDao
{
	public List<Genes> getAllGenes()
	{
		String sql = "SELECT GeneID, Essential, Chromosome FROM Genes GROUP BY GeneID";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try
		{
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next())
			{

				Genes genes = new Genes(res.getString("GeneID"), res.getString("Essential"), res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void getVertici(Map<String, Genes> geni)
	{
		String sql = "SELECT GeneID, Essential, Chromosome " 
					+ "FROM genes AS g " 
					+ "WHERE g.Essential = 'Essential' "
					+ "GROUP BY GeneID, Essential, Chromosome";

		Connection conn = DBConnect.getConnection();
		try
		{
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next())
			{
				if (!geni.containsKey(res.getString("GeneID")))
				{
					Genes g = new Genes(res.getString("GeneID"), res.getString("Essential"),
							res.getInt("Chromosome"));
					geni.put(g.getGeneId(), g);
				}
			}
			res.close();
			st.close();
			conn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Adiacenza> getAdiacenze()
	{
		String sql = "SELECT i.GeneID1 AS id1, i.GeneID2 AS id2, i.Expression_Corr AS peso "
					+ "FROM interactions AS i "
					+ "WHERE i.GeneID1 <> i.GeneID2 "
					+ "		AND i.GeneID1 IN ( SELECT GeneID "
					+ "									FROM genes AS g "
					+ "									WHERE g.Essential = 'Essential' "
					+ "									GROUP BY GeneID ) "
					+ "		AND i.GeneID2 IN ( SELECT GeneID\n"
					+ "									FROM genes AS g \n"
					+ "									WHERE g.Essential = 'Essential' "
					+ "									GROUP BY GeneID ) "; 

		ArrayList<Adiacenza> result = new ArrayList<>(); 
		Connection conn = DBConnect.getConnection();
		try
		{
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next())
			{
				 Adiacenza a = new Adiacenza(res.getString("id1"), res.getString("id2"), res.getDouble("peso")); 
				 result.add(a); 
			}
			res.close();
			st.close();
			conn.close();
			return result;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null; 
		}
	}
}
