/**
 * 
 */
package gui;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * @author Aurelien Fontaine
 * @version 1.0
 *
 */
public class PersonBoxEditable extends PersonBox {
	private GridPane personGrid;
	
	public PersonBoxEditable() {
		super();
		personGrid = this.getPersonGrid();
		
		TextField setPersonName = new TextField();
        setPersonName.setPromptText("Ex : John Smith");
        personGrid.add(setPersonName, 1, 0);

        TextArea setPersonAdress = new TextArea();
        setPersonAdress.setMaxSize(300, 60);
        setPersonAdress.setPromptText("Ex : 1, Road of Ireland");
        personGrid.add(setPersonAdress, 1, 1);

        TextField setPersonPhone = new TextField();
        setPersonPhone.setPromptText("Ex : (021) 000 0000 ");
        personGrid.add(setPersonPhone, 1, 2);
        
        TextField setPersonEmail = new TextField();
        setPersonEmail.setPromptText("Ex : john.smith@mail.com");
        personGrid.add(setPersonEmail, 1, 3);
	}
	
	public String getName(){
		Node node = getNodeByRowColumnIndex(0, 1, personGrid);
		TextField nameField = (TextField) node;
		nameField.getStyleClass().remove("error");
		String name = nameField.getText();
		if (name.equals("")) {
			errorInTheFields = true;
			nameField.getStyleClass().add("error");
		}
		return name;
	}
	
	public String getAdress(){
		Node node = getNodeByRowColumnIndex(1, 1, personGrid);
		TextArea adressField = (TextArea) node;
		adressField.getStyleClass().remove("error");
		String adress = adressField.getText();
		if (adress.equals("")) {
			errorInTheFields = true;
			adressField.getStyleClass().add("error");
		}
		return adress;
	}
	
	public String getTelephone(){
		Node node = getNodeByRowColumnIndex(2, 1, personGrid);
		TextField phoneField = (TextField) node;
		phoneField.getStyleClass().remove("error");
		String phone = phoneField.getText();
		if (phone.equals("")) {
			errorInTheFields = true;
			phoneField.getStyleClass().add("error");
		}
		return phone;
	}
	
	public String getEmail(){
		Node node = getNodeByRowColumnIndex(3, 1, personGrid);
		TextField emailField = (TextField) node;
		emailField.getStyleClass().remove("error");
		String email = emailField.getText();
		if (email.equals("")) {
			errorInTheFields = true;
			emailField.getStyleClass().add("error");
		}
		return email;
	}

	/* (non-Javadoc)
	 * @see gui.PersonBox#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see gui.PersonBox#setAdress(java.lang.String)
	 */
	@Override
	public void setAdress(String adress) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see gui.PersonBox#setTelephone(java.lang.String)
	 */
	@Override
	public void setTelephone(String phone) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see gui.PersonBox#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub

	}

}
