package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



@SuppressWarnings("serial")
public class ventanaNuevoPAIS extends JFrame {
	
	private Label lbl;
	private TextField tf;
	private Button btn;
	private Button btn1;
	
	public ventanaNuevoPAIS() {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		//AGREGAR ESPACIO PARA POSIBLE MENSAJE
		
		lbl= new Label("NOMBRE");
		add(lbl);
		
		tf = new TextField("", 10);
		tf.setEditable(true);
		add(tf);
		
		btn = new Button("GUARDAR");
		btn.addActionListener(new BTNListenerGuardar());
		add(btn);
		
		btn1 = new Button("VOLVER");
		btn1.addActionListener(new BTNListenerBack());
		add(btn1);
		
		setTitle("Gestor De Olimpiadas - NUEVO PAIS");
		setSize(400,130);
		setVisible(true);
		setResizable(false);
	}
	
	class BTNListenerBack implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			ventanaPAIS.getInstancia().getFrame().setVisible(true);
			dispose();
		}
		
	}
	
	class BTNListenerGuardar implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// checkear q no este repetido
			try {
				
				System.out.println("Pais: "+tf.getText());
				if( !( tf.getText().isBlank() || tf.getText().isEmpty() ) ) {
					
					if( carga.paisID(tf.getText()) == 0 ) {
						
						setVisible(false);
						carga.pais(tf.getText());
						ventanaPAIS.getInstancia().actualizarDatosPais();
						ventanaPAIS.getInstancia().getFrame().setVisible(true);	
						dispose();
					}
					else {
						throw new exception1();
					}
				}
				else {
					throw new exception2();
				}
				
			}catch(exception1 e1) {
				Alerta pais= new Alerta("El Pais ya ha sido ingresado.");
			}catch(exception2 e1) {
				Alerta pais= new Alerta("Ingrese un Pais.");
			}
			
		}
	}
	
	public String getTFText() {
		
		String text = tf.getText();
		
		return text;
		
	}
		
}