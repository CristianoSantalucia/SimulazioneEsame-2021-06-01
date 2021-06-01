/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.genes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController
{
	private Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="btnCreaGrafo"
	private Button btnCreaGrafo; // Value injected by FXMLLoader

	@FXML // fx:id="cmbGeni"
	private ComboBox<Genes> cmbGeni; // Value injected by FXMLLoader

	@FXML // fx:id="btnGeniAdiacenti"
	private Button btnGeniAdiacenti; // Value injected by FXMLLoader

	@FXML // fx:id="txtIng"
	private TextField txtIng; // Value injected by FXMLLoader

	@FXML // fx:id="btnSimula"
	private Button btnSimula; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML void doCreaGrafo(ActionEvent event)
	{
		try
		{
			this.model.creaGrafo();
			this.txtResult.appendText("Grafo creato con: \n#Vertici: " + this.model.getNumVertici() + "\n#Archi: "
					+ this.model.getNumArchi());

			this.cmbGeni.getItems().addAll(this.model.getVertici());
			
			btnGeniAdiacenti.setDisable(false);
			btnSimula.setDisable(false);
		}
		catch (Exception e)
		{
			this.txtResult.appendText("\nERRORE GENERAZIONE GRAFO");
			return;
		}
	}

	@FXML void doGeniAdiacenti(ActionEvent event)
	{
		Genes source = this.cmbGeni.getValue();
		if (source != null)
		{
			this.txtResult.appendText("\n\n");
			this.txtResult.appendText("VICINI di: " + source + "\n");
			this.txtResult.appendText(this.model.getVicini(source));
		}
		else
		{
			this.txtResult.appendText("\nERRORE SCELTA GENE");
			return;
		}
	}

	@FXML void doSimula(ActionEvent event)
	{
		Integer numIng = null; 
		try
		{
			numIng = Integer.parseInt(this.txtIng.getText()); 
		}
		catch (Exception e)
		{
			this.txtResult.appendText("\nERRORE");
			return; 
		}
		Genes base = this.cmbGeni.getValue();
		if (base != null && numIng != null && numIng > 0)
		{
			this.txtResult.appendText("\n" + this.model.sim(base, numIng));
		}
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize()
	{
		assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert cmbGeni != null : "fx:id=\"cmbGeni\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnGeniAdiacenti != null
				: "fx:id=\"btnGeniAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtIng != null : "fx:id=\"txtIng\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model)
	{
		this.model = model;
	}

}
