package test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

//import test.testBotonesEnTabla.ButtonEditor;
//import testGraficos.testBotonesEnTabla.ButtonRenderer;
//import testGraficos.ventanaNuevoDEPORTISTA.BTNListener;


@SuppressWarnings("serial")
class ventanaDeportista extends JFrame {
	
	private static ventanaDeportista ventanadeportista= new ventanaDeportista();
	
	private JFrame f = new JFrame();

	private String[] titulos = {"id","c1","c2","Deportistas","Accion 1","Accion 2"};
	private JTable tabla = new JTable();
	private JScrollPane scrollPane;
	//private DefaultTableModel modelo = new DefaultTableModel(titulos,0);
	
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	
	private Object [][] datos;//= {{"String Nombre","String Nacionalidad","String Disciplina"}};
	
	private JButton Nuevo = new JButton("+NUEVO");
	private JButton ExpoCSV = new JButton("EXPORTAR CSV");
	private JButton Back = new JButton("VOLVER");
	
	//private JButton borrar = new JButton("Borrar");
	//private JButton agregar = new JButton("Agregar");

	private JPanel panelBotones = new JPanel();

	public ventanaDeportista () {
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		datos=carga.tablaDatosDepor();
		
		Container contentPane = f.getContentPane();
	  contentPane.setLayout(new BorderLayout());

	  Nuevo.addActionListener(new NuevoListener());
	  ExpoCSV.addActionListener(new ExpoCSVListener());
	  Back.addActionListener(new BackListener());

	  panelBotones.add(Nuevo);
	  panelBotones.add(ExpoCSV);
	  panelBotones.add(Back);
	  
	  //tabla.setModel(modelo);
	  //tabla.setColumnModel(null);
	  //tabla.setTableHeader(new JTableHeader());
	  //tabla.isCellEditable(1,1);
	  
	  //tabla.setModel(new ModeloReservas(datos,titulos));
	  
	  //////////////
	  
	  //METODO PARA LEER LOS DATOS
	  
	  DefaultTableModel model = new DefaultTableModel(datos,titulos){
		  public boolean isCellEditable(int row, int column) {
			  if(column==4 || column==5) {
				  return true;
			  }
			  else return false;
			  }};

	  tabla = new JTable();
	  tabla.setModel(model);
	  
	  tabla.getColumn("Accion 1").setCellRenderer(new ButtonRenderer1());
	  tabla.getColumn("Accion 1").setCellEditor(new ButtonEditor1(new JCheckBox()));
	  tabla.getColumn("Accion 1").setResizable(false);
	  tabla.getColumn("Accion 1").setHeaderValue(null);
	  
	  tabla.getColumn("Accion 2").setCellRenderer(new ButtonRenderer2());
	  tabla.getColumn("Accion 2").setCellEditor(new ButtonEditor2(new JCheckBox()));
	  tabla.getColumn("Accion 2").setResizable(false);
	  tabla.getColumn("Accion 2").setHeaderValue(null);
	 
	  tabla.getTableHeader().setReorderingAllowed(false);
	  scrollPane = new JScrollPane(tabla);
	  
	  button1.addActionListener(new button1Listener());
	  button2.addActionListener(new button2Listener());
 
	  /////////////
	  
	  contentPane.add(scrollPane,BorderLayout.CENTER);
	  contentPane.add(panelBotones, BorderLayout.NORTH);

	  f.setTitle("Gestor De Olimpiadas - DEPORTISTAS");
	  f.setSize(600,300);
	  f.setResizable(false);
	  f.setVisible(true);

	  tabla.getColumn("id").setMinWidth(0);
	  tabla.getColumn("id").setMaxWidth(0);
	  
	  tabla.getColumn("c1").setResizable(false);
	  tabla.getColumn("c1").setHeaderValue(null);
	  
	  tabla.getColumn("c2").setResizable(false);
	  tabla.getColumn("c2").setHeaderValue(null);
	  tabla.getColumn("Deportistas").setResizable(false);
	  
	}
	//boton editar
	class button1Listener implements ActionListener {
		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			f.setVisible(false);
			System.out.println("Estoy en boton deportista editar");
			String id = tabla.getValueAt( tabla.getSelectedRow() , 0 ).toString();
			System.out.println("EL ID ES: "+ id);
			Deportista d = carga.traerDeportista(id);
			carga.imprimeDeportista(d);
			EditarDeportista ven = new EditarDeportista(d);
			

			System.out.println("EDITADO");
		}
	}
	//boton eliminar
	class button2Listener implements ActionListener {
		
        int lastElim = -1;
        @Override
        
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        	System.out.println("ELIMINAR");
        	
            int resp = JOptionPane.showConfirmDialog(null, "Eliminar Deportista?");

            if (resp == JOptionPane.YES_OPTION) {

                int rowElim = tabla.getSelectedRow();

                if (rowElim == -1 || rowElim >= tabla.getRowCount()) rowElim = lastElim;

                System.out.println("rowElim: "+rowElim);

                    carga.eliminarDeportista( tabla.getValueAt( rowElim , 0 ).toString() );

                    ((DefaultTableModel) tabla.getModel()).removeRow(rowElim);

                    lastElim = rowElim;

                    if (rowElim >= tabla.getRowCount()) lastElim--;

                    tabla.setEditingRow(lastElim);

            }
        }
    }

	class NuevoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			f.setVisible(false);
			@SuppressWarnings("unused")
			ventanaNuevoDEPORTISTA nuevo= new ventanaNuevoDEPORTISTA();
		}
	}
	
	class ExpoCSVListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			carga.exportarCSV();
			
		}
	}
	
	class BackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			f.setVisible(false);
			VentanaPrincipal.getPrincipal().setVisible(true);		
		}
	}
  
	class ButtonRenderer1 extends JButton implements TableCellRenderer {
	    public ButtonRenderer1() {
	      setOpaque(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    	setText((value == null) ? "EDITAR" : value.toString());
	    	return this;
	    }
	    
	  }
	
	class ButtonEditor1 extends DefaultCellEditor {
	    private String label;
	    
	    public ButtonEditor1(JCheckBox checkBox)
	    {
	      super(checkBox);
	    }

	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	    	label = (value == null) ? "EDITAR" : value.toString();
	        button1.setText(label);
	        return button1;
	      }

	    public Object getCellEditorValue() 
	    {
	      return new String(label);
	    }
	  }
  
	class ButtonRenderer2 extends JButton implements TableCellRenderer {
	    public ButtonRenderer2() {
	      setOpaque(true);
	    }

	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    	setText((value == null) ? "ELIMINAR" : value.toString());
	    	return this;
	    }
	    
	  }
  
	class ButtonEditor2 extends DefaultCellEditor {
	    private String label;
	    
	    public ButtonEditor2(JCheckBox checkBox)
	    {
	      super(checkBox);
	    }

	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	    	label = (value == null) ? "ELIMINAR" : value.toString();
	        button2.setText(label);
	        return button2;
	      }

	    public Object getCellEditorValue() 
	    {
	      return new String(label);
	    }
	  }

  	public static ventanaDeportista getInstancia() {
  		return ventanadeportista;
  	}

	public JFrame getFrame() {
		return f;
	}

	public void actualizarTabla() {

        //cargaDatosIni();
        //f.setVisible(true);
      datos=carga.tablaDatosDepor();
      tabla.setModel(new DefaultTableModel(datos,titulos){
          public boolean isCellEditable(int row, int column){
			  if(column==4 || column==5) {
				  return true;
			  }
			  else return false;
			  }
     });

      tabla.getColumn("Accion 1").setCellRenderer(new ButtonRenderer1());
      tabla.getColumn("Accion 1").setCellEditor(new ButtonEditor1(new JCheckBox()));
	  tabla.getColumn("Accion 1").setResizable(false);
      tabla.getColumn("Accion 1").setHeaderValue(null);

      tabla.getColumn("Accion 2").setCellRenderer(new ButtonRenderer2());
      tabla.getColumn("Accion 2").setCellEditor(new ButtonEditor2(new JCheckBox()));
	  tabla.getColumn("Accion 2").setResizable(false);
      tabla.getColumn("Accion 2").setHeaderValue(null);

	  tabla.getColumn("id").setMinWidth(0);
	  tabla.getColumn("id").setMaxWidth(0);
	  
	  tabla.getColumn("c1").setResizable(false);
	  tabla.getColumn("c1").setHeaderValue(null);
	  
	  tabla.getColumn("c2").setResizable(false);
	  tabla.getColumn("c2").setHeaderValue(null);
	  tabla.getColumn("Deportistas").setResizable(false);


    }
} 

//  public static void main(String[] args) {
//	    ventanaDeportista ven = new ventanaDeportista();
//	  }