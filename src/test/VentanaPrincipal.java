package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	
	private static VentanaPrincipal vPrincipal=new VentanaPrincipal();

	//private JPanel contentPane;
	private ventanaCONFIGURACION vConfig;
	//private static ventanaNuevoPAIS vNuevoPais;
	//private static ventanaNuevoDEPORTISTA vNuevoDepo;
	
	public VentanaPrincipal() {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		vConfig = new ventanaCONFIGURACION();
		
		//vConfig.setVisible(false);
		//vDepor = new ventanaDeportista();
		//vDepor.setVisible(false);
		
		
		
		//setLayout(new GridLayout(4,3));
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new GridLayout(3,3));
		
		JButton Boton;
		
		Boton = new JButton("DEPORTISTA");
		Boton.addActionListener(new btnDepoListener());
		panelPrincipal.add(Boton);
		
		Boton = new JButton("PAIS");
		Boton.addActionListener(new btnPaisListener());
		panelPrincipal.add(Boton);
		
		Boton = new JButton("DISCIPLINA");
		panelPrincipal.add(Boton);
		
		Boton = new JButton("SIN DEFINIR");
		panelPrincipal.add(Boton);
		Boton = new JButton("SIN DEFINIR");
		panelPrincipal.add(Boton);
		Boton = new JButton("SIN DEFINIR");
		panelPrincipal.add(Boton);
		Boton = new JButton("SIN DEFINIR");
		panelPrincipal.add(Boton);
		Boton = new JButton("SIN DEFINIR");
		panelPrincipal.add(Boton);
		Boton = new JButton("SIN DEFINIR");
		panelPrincipal.add(Boton);
		
		JPanel panelConf = new JPanel();
		getContentPane().add(panelConf, BorderLayout.NORTH);
		panelConf.setLayout(new BorderLayout());
		
		
		/*JLabel lbl = new JLabel("");
		lbl.setIcon(new ImageIcon("E:\\NIXU\\-2021\\-2do cuatrimestre\\Taller ii\\entregables\\3\\confi"));
		*/
		//Boton = new JButton(new ImageIcon("E:\\NIXU\\-2021\\-2do cuatrimestre\\Taller ii\\entregables\\3"));
		Boton = new JButton("CONFIGURACION");
		Boton.addActionListener(new btnConfListener());
		panelConf.add(Boton,BorderLayout.EAST);
		
		setTitle("Gestor De Olimpiadas");
		setSize(400,200);
		setVisible(true);
		setResizable(false);
		
	}
		
	class btnDepoListener implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				if(DBHelper.getConn() != null) {
					
					setVisible(false);
					ventanaDeportista.getInstancia();
					ventanaDeportista.getInstancia().getFrame().setVisible(true);
				
				}
				else {
					throw new exception1();
				}
			}catch(exception1 e1) {
				Alerta depo = new Alerta("No esta conectado a la base de datos.");
				//JOptionPane.showMessageDialog(null,"No esta conectado a una base de datos.");
			}
			
		}}
	
	class btnPaisListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				if(DBHelper.getConn() != null) {
					
					setVisible(false);
					ventanaPAIS.getInstancia();
					ventanaPAIS.getInstancia().getFrame().setVisible(true);
					
				}
				else {
					throw new exception1();
				}
			}catch(exception1 e1) {
				JOptionPane.showMessageDialog(null,"No esta conectado a una base de datos.");
				//Alerta depo = new Alerta("No esta conectado a la base de datos.");
			}
			
			
			//F.setVisible(true);
			
			
		}}
	
	class btnConfListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			setVisible(false);
			
			vConfig.setVisible(true);
			
			
		}}
	
	public static VentanaPrincipal getPrincipal() {
		return vPrincipal;
	}	
}
