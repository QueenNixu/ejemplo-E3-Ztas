package test;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class EditarPais extends JFrame {
	
	private Label lbl;
	private TextField tf;
	private Button btn1;
	private Button btn2;
	private String Aux1;
	private int Aux2;
	
	public EditarPais(String Str ,int I) {
		
		Aux1 = Str;
		Aux2 = I;
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		lbl= new Label("Nombre del Pais:");
		add(lbl);
		
		tf = new TextField(Str,25);
		tf.setEditable(true);
		add(tf);
		
		btn1 = new Button("Listo");
		add(btn1);
		btn1.addActionListener(new btnListoListener());
		
		btn2 = new Button("Volver");
		add(btn2);
		btn2.addActionListener(new btnVolverListener());
		
		setTitle("Editando nombre del Pais");
		setSize(250,130);
		setVisible(true);
		setResizable(false);
		
	}
	
	class btnListoListener implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if( !( tf.getText().isBlank() || tf.getText().isEmpty() ) ) {
				
				if( carga.paisID(tf.getText()) == 0 ) {
					
					carga.EditarPais( Aux1 , Aux2 , tf.getText() );
					
					ventanaPAIS.getInstancia().actualizarDatosPais();
					ventanaPAIS.getInstancia().getFrame().setVisible(true);
					dispose();
				}
				else {
					Alerta pais= new Alerta("El Pais ya ha sido ingresado.");
				}
			}
			else {
				@SuppressWarnings("unused")
				Alerta pais= new Alerta("Ingrese un Pais.");
			}
			
			
			
		}}
	
	class btnVolverListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
			ventanaPAIS.getInstancia().getFrame().setVisible(true);
			
		}}

}
