/**
 * 
 */
package gui;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class PersonBoxShow extends PersonBox {
	private GridPane personGrid;
	
	public PersonBoxShow() {
		super();
		personGrid = this.getPersonGrid();
		
		Text nameText = new Text();
		personGrid.add(nameText, 1, 0);
		
		Text adressText = new Text();
		personGrid.add(adressText, 1, 1);
		
		Text phoneText = new Text();
		personGrid.add(phoneText, 1, 2);
		
		Text emailText = new Text();
		personGrid.add(emailText, 1, 3);
		
	}

	/* (non-Javadoc)
	 * @see gui.PersonBox#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		Node node = this.getNodeByRowColumnIndex(0, 1, personGrid);
		Text nameText = (Text) node;
		nameText.setText(name);
	}

	/* (non-Javadoc)
	 * @see gui.PersonBox#setAdress(java.lang.String)
	 */
	@Override
	public void setAdress(String adress) {
		Node node = this.getNodeByRowColumnIndex(1, 1, personGrid);
		Text adressText = (Text) node;
		adressText.setText(adress);
	}

	/* (non-Javadoc)
	 * @see gui.PersonBox#setTelephone(java.lang.String)
	 */
	@Override
	public void setTelephone(String phone) {
		Node node = this.getNodeByRowColumnIndex(2, 1, personGrid);
		Text phoneText = (Text) node;
		phoneText.setText(phone);
	}

	/* (non-Javadoc)
	 * @see gui.PersonBox#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		Node node = this.getNodeByRowColumnIndex(3, 1, personGrid);
		Text emailText = (Text) node;
		emailText.setText(email);
	}

}
