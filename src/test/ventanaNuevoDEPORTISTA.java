package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;


@SuppressWarnings("serial")
public class ventanaNuevoDEPORTISTA extends JFrame {
	
	private Label lbl;
	private TextField tfNombre;
	private TextField tfApellido;
	private TextField tfEmail;
	private TextField tfTelefono;
	
	private Button btn;
	private Button btn1;
	private JComboBox<String> bx;
	private JComboBox<String> bx1;
	
	private TextField tfP;
	private TextField tfD;
	
	private final static String EmptyText = "                                                                     ";
	private Deportista dep;

	public ventanaNuevoDEPORTISTA() {
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setLayout(new FlowLayout());
		
		lbl= new Label("    NOMBRE");
		add(lbl);
		
		tfNombre = new TextField("", 30);
		tfNombre.setEditable(true);
		add(tfNombre);
		
		
		lbl = new Label("  APELLIDO");
		add(lbl);
		
		tfApellido = new TextField("", 30);
		tfApellido.setEditable(true);
		add(tfApellido);
		
		
		lbl = new Label("         E-MAIL");
		add(lbl);
		
		tfEmail = new TextField("", 30);
		tfEmail.setEditable(true);
		add(tfEmail);
		
		
		lbl = new Label(" TELEFONO");
		add(lbl);
		
		tfTelefono = new TextField("", 30);
		tfTelefono.setEditable(true);
		add(tfTelefono);
		
		lbl = new Label("             PAIS");
		add(lbl);
		
		tfP = new TextField(EmptyText);
		
		bx = new JComboBox<String>();
		addItemsBX(bx);
		
		bx.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				tfP.setText(bx.getSelectedItem().toString());
			}
		});
		
		add(bx);

		lbl = new Label("DISCIPLINA");
		add(lbl);
		
		tfD = new TextField(EmptyText);
		
		bx1 = new JComboBox<String>();
		addItemsBX1(bx1);
		
		bx1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfD.setText(bx1.getSelectedItem().toString());
			}
		});
		
		add(bx1);
		
		btn = new Button("GUARDAR");
		btn.addActionListener(new BTNListener());
		add(btn);
		
		btn1 = new Button("VOLVER");
		btn1.addActionListener(new BTNListenerBack());
		add(btn1);
		
		setTitle("Gestor De Olimpiadas - NUEVO DEPORTISTA");
		setSize(400,300);
		setVisible(true);
		setResizable(false);
	}
	
	
	class BTNListenerBack implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			ventanaDeportista.getInstancia().getFrame().setVisible(true);
			dispose();
		}
		
	}
	
	class BTNListener implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			boolean Continue = true;
			dep=new Deportista();
			
			
			try {
				dep.setNombre(tfNombre.getText());
				
				if (!soloLetra(dep.getNombre())) {
					throw new exception1();
					}
				if( dep.getNombre().isEmpty() || dep.getNombre().isBlank()) {
					throw new exception2();
				}
			}catch(exception1 e1) {
				Alerta nombre= new Alerta("El nombre contiene caracteres indebidos.");
				Continue = false;
			}catch(exception2 e1) {
				Alerta nombre= new Alerta("Ingrese un nombre.");
				Continue = false;
			}
			
			try {
				dep.setApellido(tfApellido.getText());
				
				if (!soloLetra(dep.getApellido())) {
					throw new exception1();
					}
				if( dep.getApellido().isEmpty() || dep.getApellido().isBlank()) {
					throw new exception2();
				}
			}catch(exception1 e1) {
				Alerta apellido= new Alerta("El apellido contiene caracteres indebidos.");
				Continue = false;
			}catch(exception2 e1) {
				Alerta apellido= new Alerta("Ingrese un apellido.");
				Continue = false;
			}
			
			try {
				dep.setEmail(tfEmail.getText());
				
				if (!(dep.getEmail().contains("@")) || dep.getEmail().isEmpty() || dep.getEmail().isBlank()){
					throw new exception1();
					}
			}catch(exception1 e1) {
				Alerta email= new Alerta("El email no contiene @ o no se ha ingresado un E mail.");
				Continue = false;
			}
			
			try {
				dep.setTelefono(tfTelefono.getText());
				if (  !(soloNumero(dep.getTelefono() ) )  ) {
					throw new exception1();
					}
				if( dep.getTelefono().isEmpty() || dep.getTelefono().isBlank()) {
					throw new exception2();
				}
			}catch(exception1 e1) {
				Alerta telefono= new Alerta("El telefono contiene caracteres indebidos.");
				Continue = false;
			}catch(exception2 e1) {
				Alerta telefono= new Alerta("Ingrese un telefono.");
				Continue = false;
			}
			
			try {
				if( !(bx.getSelectedItem().toString().isEmpty() || bx.getSelectedItem().toString().isBlank() ) ) {
					dep.setPais(bx.getSelectedItem().toString());
				}
				else {
					throw new exception1();
				}
			}catch(exception1 e1) {
				Alerta pais= new Alerta("Seleccione un Pais.");
				Continue = false;
			}
			
			try {
				if( !( bx1.getSelectedItem().toString().isEmpty() || bx1.getSelectedItem().toString().isBlank() ) ) {
					dep.setDisciplina(bx1.getSelectedItem().toString());
				}
				else {
					throw new exception1();
				}
			}catch(exception1 e1) {
				Alerta disciplina= new Alerta("Seleccione una Disciplina.");
				Continue = false;
			}
			
			if(Continue == true) {
				carga.deportista(dep);
				ventanaDeportista.getInstancia().actualizarTabla();
				ventanaDeportista.getInstancia().getFrame().setVisible(true);
				dispose();
			}
		}
	}

	@SuppressWarnings("static-access")
	public static void addItemsBX(JComboBox<String> bx) {
		
		try {
			
			bx.addItem(EmptyText);
			Statement sent = DBHelper.getServer().getConn().createStatement();
			ResultSet Datos = sent.executeQuery("SELECT * FROM tokyo2021_e3.pais");
			
			while(Datos.next()) {
				
				bx.addItem(Datos.getString("nombre"));
				
			}
			sent.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@SuppressWarnings("static-access")
	public static void addItemsBX1(JComboBox<String> bx1) {
		
		try {
			
			bx1.addItem(EmptyText);
			Statement sent = DBHelper.getServer().getConn().createStatement();
			ResultSet Datos = sent.executeQuery("SELECT * FROM tokyo2021_e3.disciplina");
			
			while(Datos.next()) {
				
				bx1.addItem(Datos.getString("nombre"));
				
			}
			sent.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static boolean soloLetra(String cadena) {
	    for (int i = 0; i < cadena.length(); i++) {
	        char c = cadena.charAt(i);
	        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static boolean soloNumero(String cadena) {
		
        for (int i = 0; i < cadena.length(); i++) {
            if (!Character.isDigit(cadena.charAt(i))) {
                return false;
            }
        }
        return true;
    }

	
}

