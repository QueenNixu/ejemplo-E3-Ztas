package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;


@SuppressWarnings("serial")
public class ventanaPAIS extends JFrame {
	
	
	
	private static JFrame f = new JFrame();
	private static ventanaPAIS ventanapais= new ventanaPAIS();
	
	private String[] titulos = {"","Paises","Accion 1","Accion 2"};//
	//private DefaultTableModel modelo = new DefaultTableModel(titulos,0);
	private JTable tabla = new JTable();
	private JScrollPane scrollPane;
	
	private Object [][] datos;
	
	
	
	
	private JButton Nuevo = new JButton("+NUEVO");
	private JButton Back = new JButton("VOLVER");
	
	private TableModel model;
	
	JButton button1 = new JButton();
	JButton button2 = new JButton();
	
	//private JButton borrar = new JButton("Borrar");
	//private JButton agregar = new JButton("Agregar");
	
	
	private JPanel panelBotones = new JPanel();

	public ventanaPAIS () {
		
		//button1.addActionListener(new button1Listener());
		//button2.addActionListener(new button2Listener());
		  
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Container contentPane = f.getContentPane();
		contentPane.setLayout(new BorderLayout());

		Nuevo.addActionListener(new NuevoListener());
		Back.addActionListener(new BackListener());
		
		panelBotones.add(Nuevo);
		panelBotones.add(Back);
		  
		  
		  /// ACA CARGAR DATOS EN DATOS COMO Object[][]
		cargaDatosIni();
		
		contentPane.add(scrollPane,BorderLayout.CENTER);
		contentPane.add(panelBotones, BorderLayout.NORTH);

		f.setTitle("Gestor De Olimpiadas - PAIS");
		f.setSize(600,300);
		f.setResizable(false);
		f.setVisible(true);	
	  
	}
	
	public JFrame getFrame() {
		
		return f;
		
	}
	
	class button1Listener implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				
				int rowAux = tabla.getSelectedRow();
				
				if( carga.IDdeportistaConPaisID( carga.paisID( tabla.getValueAt( rowAux , 1 ).toString() ) ) == 0 ) {
					
					System.out.println("EDITAR");
					
					f.setVisible(false);
					
					EditarPais Ed = new EditarPais(tabla.getValueAt( rowAux , 1 ).toString() ,Integer.parseInt(tabla.getValueAt( rowAux , 0 ).toString()));
					
					actualizarDatosPais();
				}
				else {
					throw new exception1();
				}
			}catch(exception1 e1) {
				Alerta pais = new Alerta("El pais tiene asociado a uno o mas deportistas y no podra ser editado.");
			}
		}}
	
	class button2Listener implements ActionListener {

        int lastElim = -1;

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            int resp = JOptionPane.showConfirmDialog(null, "Eliminar pais?");

            if (resp == JOptionPane.YES_OPTION) {
            	
                int rowElim = tabla.getSelectedRow();
                
                if (rowElim == -1 || rowElim >= tabla.getRowCount()) rowElim = lastElim;
                
                System.out.println("rowElim: "+rowElim);
                
                if( carga.IDdeportistaConPaisID( carga.paisID( tabla.getValueAt( rowElim , 1 ).toString() ) ) == 0 ) {
                	
                    carga.EliminarPais( tabla.getValueAt( rowElim , 1 ).toString() );

                    ((DefaultTableModel) tabla.getModel()).removeRow(rowElim);

                    lastElim = rowElim;

                    if (rowElim >= tabla.getRowCount()) lastElim--;

                    tabla.setEditingRow(lastElim);
                	
                }
                else {
                	@SuppressWarnings("unused")
					Alerta pais = new Alerta("El pais tiene asociado a uno o mas deportistas y no podra ser eliminado.");
                }
                }
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

	class NuevoListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		f.setVisible(false);
		@SuppressWarnings("unused")
		ventanaNuevoPAIS vNuevoPais = new ventanaNuevoPAIS();
		
		
		
		
	}}
	
	class Render extends DefaultTableCellRenderer{
	    @Override
	    public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected
	            ,boolean hasFocus,int row,int column) {
	        if(value instanceof JButton) {
	            JButton btn = (JButton)value;
	            return btn;
	        }
	        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    }

	}
  
	class BackListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			f.setVisible(false);
			VentanaPrincipal.getPrincipal().setVisible(true);
			
		}}

	public static ventanaPAIS getInstancia() {
		return ventanapais;
	}

	public void cargaDatosIni() {
	  
	  datos=carga.tablaDatosPais();
	  
	  tabla=setearTabla();
	  
  }
	
	

	public void actualizarDatosPais() {
		
		//cargaDatosIni();
		//f.setVisible(true);
	  datos=carga.tablaDatosPais();
	  tabla.setModel(new DefaultTableModel(datos,titulos){
          public boolean isCellEditable(int row, int column){
              if(column == 2 || column == 3) return true;
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
	  
	  tabla.getColumn("Paises").setResizable(false);
	  tabla.getColumn("").setResizable(false);
	  
	}
	
	
	public JTable setearTabla() {
		
		JTable TBL;
		
		model=new DefaultTableModel(datos,titulos) {
	          public boolean isCellEditable(int row, int column){
	        	  if (column == 2 || column == 3) {
	        		  return true;
	        	  }
	        	  else return false;
	          }
	         
		 };
		  TBL = new JTable();
		  TBL.setModel(model);
		  
		  TBL.getColumn("Accion 1").setCellRenderer(new ButtonRenderer1());
		  TBL.getColumn("Accion 1").setCellEditor(new ButtonEditor1(new JCheckBox()));
		  
		  TBL.getColumn("Accion 1").setResizable(false);
		  
		  TBL.getColumn("Accion 1").setHeaderValue(null);

		  TBL.getColumn("Accion 2").setCellRenderer(new ButtonRenderer2());
		  TBL.getColumn("Accion 2").setCellEditor(new ButtonEditor2(new JCheckBox()));
		  
		  TBL.getColumn("Accion 2").setResizable(false);
		  
		  TBL.getColumn("Accion 2").setHeaderValue(null);
		  
		  TBL.getTableHeader().setReorderingAllowed(false);
		  scrollPane = new JScrollPane(TBL);
		  
		  button1.addActionListener(new button1Listener());
		  button2.addActionListener(new button2Listener());
		  
		  TBL.getColumn("Paises").setResizable(false);
		  TBL.getColumn("").setResizable(false);
		
		return TBL;
	}
	
	
  }