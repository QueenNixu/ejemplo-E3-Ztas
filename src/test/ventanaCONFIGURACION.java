package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;


@SuppressWarnings("serial")
public class ventanaCONFIGURACION extends JFrame{ // Anda 
	
	private Label lbl;
	private TextField tf;
	private TextField tf1;
	private Button btn;
	private Button btn1;
	
	public ventanaCONFIGURACION() {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		lbl= new Label("USUARIO BD     ");
		add(lbl);
		
		tf = new TextField("",10);
		tf.setEditable(true);
		add(tf);
		
		
		lbl = new Label("PASSWORD BD");
		add(lbl);
		
		tf1 = new TextField("",10);
		tf1.setEditable(true);
		add(tf1);
		
		btn = new Button("CONECTAR");
		add(btn);
		btn.addActionListener(new btnConectarListener());
		
		btn1 = new Button("VOLVER");
		add(btn1);
		btn1.addActionListener(new btnVolverListener());
		
		setTitle("Gestor De Olimpiadas - CONFIGURACION");
		setSize(250,130);
		setVisible(false);
		setResizable(false);
	}
	class btnConectarListener implements ActionListener {

		@SuppressWarnings({ "static-access", "unused" })
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				
				DBHelper.getServer().setDbPassword(tf1.getText());
				DBHelper.getServer().setDbUser(tf.getText());
				Connection Aux = DBHelper.getServer().getConn();
				
				if(Aux == null) {
					throw new exception1();
				}
				
				setVisible(false);
				VentanaPrincipal.getPrincipal().setVisible(true);
				
			}catch(exception1 e1) {
				// TODO Auto-generated catch block
				Alerta conf = new Alerta("Error al conectarse a la base de datos.");
			}
			
			
		}}
	
	
	
	class btnVolverListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
			VentanaPrincipal.getPrincipal().setVisible(true);
			
		}}
	
}
