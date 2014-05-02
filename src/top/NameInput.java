package top;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

public class NameInput extends Form implements CommandListener {
	
	private TextField nameField;
	
	private Command ok;
	
	private GameManager manager;

	public NameInput(GameManager manager, int points) {
		super("Game Over");
		
		this.manager = manager;
		
		append("Congratulations, you have earned " + points + " points!");
		
		nameField = new TextField("Enter your name:", Common.lastName, 10, TextField.ANY);
		append(nameField);
		
		ok = new Command("Done", Command.OK, 0);
		addCommand(ok);
		
		setCommandListener(this);
	}

	public void commandAction(Command c, Displayable arg1) {
		if (c.equals(ok)) {
			Common.lastName = nameField.getString();
			manager.setName(nameField.getString());
		}
	}

}
