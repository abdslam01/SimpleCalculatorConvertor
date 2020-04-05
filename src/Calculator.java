import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPanel principalePanel, textPanel, radioPanel, buttonPanel;
	private ButtonGroup radioGroup;
	private HashMap<String,JButton> Btns;
	private HashMap<String,JRadioButton> radioBtns;
	private HashMap<String, String> InputOutput;
	/*
	 	le HashMap 'Btns': contient 25 buttons, qui ont comme noms, la liste ci-apres
	 		 -{"A","On/Off","←","c","/","B","7","8","9","*","C","4","5","6","-","D","1","2","3","+","E","F","0",".","="}
	 	le HashMap 'radioBtns': contient les 3 radio Buttons:
	 		 -Hex Radio Button, Dec Radio Button, Bin Radio Button
	 	le HashMap 'InputOutput' à:
		 	 -BeforeLastRadioButtonClicked: sert a enregister le nom de la radio button qui ete cliquer [def: Hex]
		 	 -LastRadioButtonClicked: sert a enregister le nom de la radio button qui ete cliquer actuellement [def: Hex]
		 	 -FirstInputOutput: sert a enregister la 1ere input de l'utilisateur [def: null]
		 	 -SecondInputOutput: sert a enregister la 2eme input de l'utilisateur [def: null]
			 -State: sert a determiner si utilisateur definit le type d'opration ou non [def: false]
	 */
	
	Calculator(){
		/*
		 * Creation et Initialisation de HashMap 'InputOutPut'
		 */
		InputOutput = new HashMap<String, String>();
		String InOut[] = {"FirstInputOutput", "SecondInputOutput" ,"OpdType", "OprType"};
		for(String e : InOut)
			InputOutput.put(e,null);
		InputOutput.put("BeforeLastRadioClicked", "Hex");
		InputOutput.put("LastRadioClicked", "Hex");
		InputOutput.put("State", "false");
		
		/*
		 *  Creation et Initialisation de Panel Principale, l'ajout au Frame
		 */
		principalePanel = new JPanel();
		principalePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(principalePanel);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		/*
		 * Creation et Initialisation de TextField et Son Panel,  l'ajout de ce dernier au Frame
		 */
		textPanel=new JPanel();
		textField=new JTextField("");
		textField.setPreferredSize(new Dimension(400, 40));
		textField.setFont(new Font("SansSerif", Font.BOLD, 20));
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setEditable(false);
		textPanel.add(textField);
		getContentPane().add(textPanel);
		
		/*
		 * Creation et Initialisation des 3 radio Button, et leur Propres groupe
		 * Les ajouts au HashMap, les nommées, et les a créés des ecouteurs
		 * Les ajout au panel
		 */
		radioPanel=new JPanel();
		radioGroup=new ButtonGroup();
		radioBtns=new HashMap<String,JRadioButton>();
		String RadioBtnsName[]= {"Hex","Déc","Bin"};
		for(String Name: RadioBtnsName) {
			radioBtns.put(Name,new JRadioButton(Name));
			radioGroup.add(radioBtns.get(Name));
			radioPanel.add(radioBtns.get(Name));
			radioBtns.get(Name).setActionCommand(Name);
			radioBtns.get(Name).addActionListener((ActionListener)this);
		}
		getContentPane().add(radioPanel);
		
		/*
		 * Creation et Initialisation des 25 Button
		 * Les ajouts au HashMap, les nommées, et les a créés des ecouteurs
		 * Les ajout au panel
		 */
		buttonPanel=new JPanel();
		buttonPanel.setLayout(new GridLayout(5,5,5,5));
		Btns=new HashMap<String,JButton>();
		String BtnsName[]= {"A","On/Off","←","c","/","B","7","8","9","*","C","4","5","6","-","D","1","2","3","+","E","F","0",".","="};
		for(String Name: BtnsName) {
			Btns.put(Name,new JButton(Name));
			Btns.get(Name).setPreferredSize(new Dimension(0,35));
			Btns.get(Name).setFont(new Font("SansSerif", Font.BOLD, 13));
			Btns.get(Name).setActionCommand(Name);
			Btns.get(Name).addActionListener((ActionListener)this);
			buttonPanel.add(Btns.get(Name));
		}
		getContentPane().add(buttonPanel);
		
		/*
		 * Définir l'action par défaut lors de l'exécution de l'application
		 */
		radioBtns.get("Hex").setSelected(true);
		radioBtns.get("Hex").doClick();
		
		/*
		 * Initialisation de Frame
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setTitle("Calculatrice - By: Abdessalam BAHAFID");
	}
	public static void main(String [] args) {
		new Calculator();
	}
	public void actionPerformed(ActionEvent e) {
		/*
		 * Creation de l'objet qui contient les methode a utilise
		 */
		Methods methods=new Methods();
		String HexBtns[]= {"A","B","C","D","E","F"};
		String DecBtns[]= {"2","3","4","5","6","7","8","9","."};

		/*
		 * En cas de clique sur l'une des radio button, enregister l'evenement actueal, ainsi que le precedent 
		 */
		if(methods.isContains(new String[]{"Hex","Déc","Bin"}, e.getActionCommand())) {
			InputOutput.put("BeforeLastRadioClicked", InputOutput.get("LastRadioClicked"));
			InputOutput.put("LastRadioClicked",e.getActionCommand());
		}
		
		switch(e.getActionCommand()) {
			case "Hex":
				InputOutput.put("FirstInputOutput",null);
				InputOutput.put("SecondInputOutput",null);
				for(String Name : methods.concat(HexBtns, DecBtns))
					Btns.get(Name).setEnabled(true);
				Btns.get(".").setEnabled(false);
				if(!textField.getText().equals("") && InputOutput.get("BeforeLastRadioClicked") != InputOutput.get("LastRadioClicked"))
					textField.setText(methods.toHex(textField.getText(),InputOutput.get("BeforeLastRadioClicked")));
				break;
				
			case "Déc":
				InputOutput.put("FirstInputOutput",null);
				InputOutput.put("SecondInputOutput",null);
				for(String Name : DecBtns)
					Btns.get(Name).setEnabled(true);
				for(String Name : HexBtns)
					Btns.get(Name).setEnabled(false);
				if(!textField.getText().equals("") && InputOutput.get("BeforeLastRadioClicked") != InputOutput.get("LastRadioClicked"))
					textField.setText(methods.toDec(textField.getText(),InputOutput.get("BeforeLastRadioClicked")));
				break;
				
			case "Bin":
				InputOutput.put("FirstInputOutput",null);
				InputOutput.put("SecondInputOutput",null);
				for(String Name : methods.concat(HexBtns, DecBtns))
					Btns.get(Name).setEnabled(false);
				if(!textField.getText().equals("") && InputOutput.get("BeforeLastRadioClicked") != InputOutput.get("LastRadioClicked"))
					textField.setText(methods.toBin(textField.getText(),InputOutput.get("BeforeLastRadioClicked")));
				break;
				
			case "On/Off":
				if(Btns.get("c").isEnabled()) {
					for(JButton btn : Btns.values())
						btn.setEnabled(false);
					for(JRadioButton btn : radioBtns.values())
						btn.setEnabled(false);
					textField.setText("");
					InputOutput.put("FirstInputOutput", null);
					InputOutput.put("SecondInputOutput", null);
				}else {
					for(JButton btn : Btns.values())
						btn.setEnabled(true);
					for(JRadioButton btn : radioBtns.values())
						btn.setEnabled(true);
					InputOutput.put("BeforeLastRadioClicked", "none");
					radioBtns.get("Hex").setSelected(true);
					radioBtns.get("Hex").doClick();
				}
				Btns.get("On/Off").setEnabled(true);
				break;
				
			case "←":
				textField.setText(methods.cancelLastChar(textField.getText()));
				break;
				
			case "c":
				textField.setText("");
				InputOutput.put("FirstInputOutput", null);
				InputOutput.put("SecondInputOutput", null);
				break;
				
			case "/":
			case "*":
			case "-":
			case "+":
				if(!textField.getText().equals("")) {
					if(InputOutput.get("FirstInputOutput") == null)
						InputOutput.put("FirstInputOutput",textField.getText());
					else
						InputOutput.put("SecondInputOutput",textField.getText());
				}
				InputOutput.put("OprType", e.getActionCommand());
				InputOutput.put("State", "true");
				break;
				
			case "=":
				InputOutput.put("OpdType", InputOutput.get("LastRadioClicked"));
				InputOutput.put("SecondInputOutput", textField.getText());
				if(!InputOutput.containsValue(null)) {
					switch(InputOutput.get("OprType")) {
					case "/":
						InputOutput.put("FirstInputOutput", methods.division(InputOutput.get("FirstInputOutput"), InputOutput.get("SecondInputOutput"), InputOutput.get("OpdType")));
						break;
					case "*":
						InputOutput.put("FirstInputOutput", methods.multiplication(InputOutput.get("FirstInputOutput"), InputOutput.get("SecondInputOutput"), InputOutput.get("OpdType")));
						break;
					case "-":
						InputOutput.put("FirstInputOutput", methods.subtraction(InputOutput.get("FirstInputOutput"), InputOutput.get("SecondInputOutput"), InputOutput.get("OpdType")));
						break;
					case "+":
						InputOutput.put("FirstInputOutput", methods.addition(InputOutput.get("FirstInputOutput"), InputOutput.get("SecondInputOutput"), InputOutput.get("OpdType")));
						break;
					}
					textField.setText(InputOutput.get("FirstInputOutput"));
					InputOutput.put("FirstInputOutput", null);
					InputOutput.put("SecondInputOutput", null);
				}
				break;
			default:
				if(InputOutput.get("State").equals("true")) {
					InputOutput.put("State", "false");
					textField.setText("");
				}
				if(textField.getText().isEmpty()) {
					if(!e.getActionCommand().equals("."))
						textField.setText(e.getActionCommand());
				}else {
					if(!e.getActionCommand().equals(".")) {
						textField.setText(textField.getText() + e.getActionCommand());
					}else {
						if(!methods.isContains(textField.getText(), '.')) {
							textField.setText(textField.getText() + ".");
						}
					}
				}
		}
	}
}
