/*
package test;

import java.util.Scanner;
import java.sql.*;

public class Testbd {
	private static DBHelper server;
	private static String query,query2;
	
	public static void main(String[] args) {
		
		Scanner scan= new Scanner(System.in);
		System.out.print("Contraseña de usuario root:");
		server= new DBHelper();
		server.setDbPassword(scan.nextLine());
		Connection conn= server.getConn();
		try {
			
			String guardar;
			System.out.print("ID deportista (0 Termina):");
			int idDeportista= scan.nextInt();
			Statement sent= conn.createStatement();
			//Statement sent1= conn.createStatement();
			while( idDeportista != 0) {
				query= carga.deportista(idDeportista);
				query2= carga.disciplina(idDeportista, sent);
				carga.imprimeDeportista();
				System.out.println ("Desea guardar este deportista? (Si/No):");
				scan.nextLine();
				guardar=scan.nextLine();
				
				if (guardar.compareToIgnoreCase("si") ==0) {
					sent.executeUpdate(query);
					sent.executeUpdate(query2);
				}
				
				System.out.print("ID deportista (0 Termina):");
				idDeportista= scan.nextInt();
				
			}
		sent.close();
		//sent1.close();
		scan.close();	
		} catch (SQLException e) {
			System.out.println ("¡Error de SQL!");
		}
		
	}

}
*/
package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
class TestJButton extends JFrame
{
  private JPanel topPanel;
  private JTable table;
  private JScrollPane scrollPane;
  private String[] columns = new String[3];
  private String[][] data = new String[3][3];
  JButton button = new JButton();

  public TestJButton()
  {
    setTitle("JButton in JTable");
    setSize(300,150);
    topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    getContentPane().add(topPanel);
    columns = new String[] {"Id", "Name", "Action"};

    data = new String[][]{
      {"1","Thomas"},
      {"2","Jean"},
      {"3","Yohan"}
    };

    DefaultTableModel model = new DefaultTableModel(data,columns);
    table = new JTable();
    table.setModel(model);
    table.getColumn("Action").setCellRenderer(new ButtonRenderer());
    table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));
    scrollPane = new JScrollPane(table);
    topPanel.add(scrollPane,BorderLayout.CENTER);  
    
    button.addActionListener(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent event)
        {
          JOptionPane.showMessageDialog(null,"Do you want to modify this line?");
        }
      }
    );
  }

  class ButtonRenderer extends JButton implements TableCellRenderer 
  {
    public ButtonRenderer() {
      setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {
      setText((value == null) ? "Modify" : value.toString());
      return this;
    }
  }

  class ButtonEditor extends DefaultCellEditor 
  {
    private String label;
    
    public ButtonEditor(JCheckBox checkBox)
    {
      super(checkBox);
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column) 
    {
      label = (value == null) ? "Modify" : value.toString();
      button.setText(label);
      return button;
    }

    public Object getCellEditorValue() 
    {
      return new String(label);
    }
  }

  public static void main(String args[])
  {
    TestJButton f = new TestJButton();
    f.setVisible(true);
  }
}