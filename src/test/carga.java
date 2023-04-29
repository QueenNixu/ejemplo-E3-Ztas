package test;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class carga {
		//private static String depor;
		private static String aBaseDatos;
		@SuppressWarnings("unused")
		private static Deportista deportista;
		
	
	public static void pais(String P) { //FUNCIONA BIEN
		try {
			
			DBHelper.getServer();
			Statement sent = DBHelper.getConn().createStatement();
			ResultSet busqueda = sent.executeQuery("SELECT max(id) FROM tokyo2021_e3.pais");
			int id=0;
			if (busqueda.next()){
				if(busqueda.getString("max(id)")!= null) {
				id=Integer.parseInt(busqueda.getString("max(id)"));
				}
			}
			id++;
			aBaseDatos= "INSERT INTO `pais` VALUES ("+Integer.toString(id)+",'"+P+"')";
			sent.executeUpdate(aBaseDatos);
			sent.close();
		}catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fue al catch");
		}
		
	}
	
	public static void deportista(Deportista depor) { //FUNCIONA BIEN
		try {
			DBHelper.getServer();
			Statement sent = DBHelper.getConn().createStatement();
			ResultSet busqueda = sent.executeQuery("SELECT max(id) FROM tokyo2021_e3.deportista;");
			int id=0;
			if (busqueda.next()){
				if(busqueda.getString("max(id)")!= null) {
				id=Integer.parseInt(busqueda.getString("max(id)"));
				}
			}
			id++;
			depor.setId(id);
			aBaseDatos= "INSERT INTO `deportista` VALUES ("
					+Integer.toString(depor.getId())+",'"
					+depor.getApellido()+"','"
					+depor.getNombre()+"','" 
					+depor.getEmail()+"','"
					+depor.getTelefono()+"',"
					+Integer.toString(depor.getPais().getId()) 
					+")";
			sent.executeUpdate(aBaseDatos);
			
			// id con disciplina
			aBaseDatos= ("INSERT INTO `deportista_en_disciplina` VALUES ("
					+Integer.toString(depor.getId())+","
					+Integer.toString(depor.getDisciplina().getId())+
					")");
			
			
			
			
			sent.executeUpdate(aBaseDatos);
			sent.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No se cargo");
		}
	}

	public static void imprimeDeportista(Deportista deportista) { // INNECESARIO
		System.out.println("Apellido:"+deportista.getApellido());
		System.out.println("Nombre:"+deportista.getNombre());
		System.out.println("Email:"+deportista.getEmail());
		System.out.println("Telefono:"+deportista.getTelefono());
		//System.out.println("Disciplinas:"+deportista.getDisciplinas());
		
	}
	
	public static int IDdeportistaConPaisID(int IDpais) {
		
		try {
			DBHelper.getServer();
			Statement sent = DBHelper.getConn().createStatement();
			ResultSet busqueda = sent.executeQuery("SELECT id FROM deportista where id_pais ='"+IDpais+"'");
			if(busqueda.next()) {
				int id=Integer.parseInt(busqueda.getString("id"));
				sent.close();
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}

	public static int disciplinaID(String disciplina) { //FUNCIONA con el nombre devuelve el num
		
		try {
			DBHelper.getServer();
			Statement sent = DBHelper.getConn().createStatement();
			ResultSet busqueda = sent.executeQuery("SELECT id FROM disciplina where nombre='"+disciplina+"'");
			if(busqueda.next()) {
				int id=Integer.parseInt(busqueda.getString("id"));
				sent.close();
				return id;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static int paisID(String pais) { //FUNCIONA con el nombre devuelve el num
		
		try {
			DBHelper.getServer();
			Statement sent = DBHelper.getConn().createStatement();
			ResultSet busqueda = sent.executeQuery("SELECT id FROM pais where nombre='"+pais+"'");
			if(busqueda.next()) {
				int id=Integer.parseInt(busqueda.getString("id"));
				sent.close();
				return id;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public static Object[][] tablaDatosPais() { //REVISAR

        try {
            DBHelper.getServer();
			Statement sent = DBHelper.getConn().createStatement();
            ResultSet busqueda = sent.executeQuery("SELECT * FROM pais ORDER BY nombre DESC;");
            String resultados = null;
            while(busqueda.next()) {
                resultados =(busqueda.getString(1)+ ","+ busqueda.getString(2)+ "-"+resultados);    
                }
            sent.close();
            Object[][] tabla;
            if (resultados != null) {
                String[] separados= resultados.split("-");
        
                tabla= new Object[separados.length-1][];
        
                for (int i=0;i<separados.length-1;i++) {
                    tabla[i]= separados[i].split(",");
                }
            }else tabla=null;
        return tabla;    
        
        } catch(SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }        
    }
	
	public static Object[][] tablaDatosDepor() { //REVISAR

        try {
            DBHelper.getServer();
            Statement sent = DBHelper.getConn().createStatement();
            ResultSet busqueda = sent.executeQuery("SELECT * FROM deportista ORDER BY nombres DESC;");
            String resultados = null;
            while(busqueda.next()) {
                resultados =(busqueda.getString("id")+","+busqueda.getString("nombres")+ " "+ busqueda.getString("apellidos")+","+busqueda.getString("id_pais")+","+busqueda.getString("id")+ "-"+resultados);
                }
            sent.close();
            Object[][] tabla;
            if (resultados != null) {
                String[] separados= resultados.split("-");
                tabla= new Object[separados.length-1][];

                for (int i=0;i<separados.length-1;i++) {
                    tabla[i]= separados[i].split(",");
                    tabla[i][2]= idPaisANombreP(Integer.parseInt((String) tabla[i][2])); //ID PAIS NECESITO NOMBRE
                    tabla[i][3]= idPADisciplina(Integer.parseInt((String) tabla[i][3])); //ID PERSONA NECESITO DISCIPLINA
                }
            }else tabla=null;
        return tabla;

        } catch(SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
	
	public static String idPaisANombreP(int idP) {//Hacer
        Pais pais= new Pais();
        pais.setId(idP);
        try {

            DBHelper.getServer();
			Statement sent = DBHelper.getConn().createStatement();
            ResultSet busqueda = sent.executeQuery("SELECT nombre FROM tokyo2021_e3.pais where id="+idP);
            busqueda.next();
            pais.setNombre(busqueda.getString("nombre"));
            sent.close();
        }catch(SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Fue al catch");

        }


        return pais.getNombre();
    }
	
	public static String idPADisciplina(int idD) { //hacer
        String aux= "pichichu";
    try {

        DBHelper.getServer();
		Statement sent = DBHelper.getConn().createStatement();
        ResultSet busqueda = sent.executeQuery("SELECT id_disciplina FROM tokyo2021_e3.deportista_en_disciplina where id_deportista="+ idD);
        busqueda.next();
        aux= busqueda.getString("id_disciplina");
        busqueda = sent.executeQuery("SELECT nombre FROM tokyo2021_e3.disciplina where id="+aux);
        busqueda.next();
        aux=busqueda.getString("nombre");
        sent.close();
    }catch(SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        System.out.println("Fue al catch");

    }
    return aux;
    }
	/*
	public static void EliminarTablaPais() {
		
		try {

	        Statement sent = DBHelper.getServer().getConn().createStatement();
	        
	        String query = "DELETE FROM pais";
	        sent.executeUpdate(query);
	        
	        sent.close();
	    }catch(SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        }
		
	}
	
//	public static void EliminarDeportista(String depor) {
//			
//			try {
//	
//		        DBHelper.getServer();
//				Statement sent = DBHelper.getConn().createStatement();
//		        
//		        String query = "DELETE FROM pais WHERE nombre = '"+Pais+"'";
//		        if(sent.executeUpdate(query) > 0) System.out.println("se borro");
//		        
//		        sent.close();
//		    }catch(SQLException e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		        } 
//	}
 * */
	public static void EliminarPais(String Pais) {
		
		try {

	        DBHelper.getServer();
			Statement sent = DBHelper.getConn().createStatement();
	        
	        String query = "DELETE FROM pais WHERE nombre = '"+Pais+"'";
	        if(sent.executeUpdate(query) > 0) System.out.println("se borro");
	        
	        sent.close();
	    }catch(SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        }
		
	}
	
	public static void EditarPais( String PaisElim, int id , String PaisAgreg ) {
		
		try {

	        DBHelper.getServer();
			Statement sent = DBHelper.getConn().createStatement();
	        
	        EliminarPais(PaisElim);
	        
	        String query = "INSERT INTO `pais` VALUES ("+Integer.toString(id)+",'"+PaisAgreg+"')";
	        sent.executeUpdate(query);
	        
	        sent.close();
	    }catch(SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        }
		
	}

	@SuppressWarnings("unused")
	public static void exportarCSV() {
		String linea="Nombre Completo,Pais,Disciplina\n";
		try {
			Writer arch = new FileWriter("Deportistas.csv");
			arch.write(linea);
			Object[][] datos= tablaDatosDepor();
			for (int i=0; i<datos.length;i++) {
				linea= (datos[i][0] + ","+ datos[i][1]+ "," + datos[i][2] + "\n");
				System.out.println(linea);
				arch.write(linea);
			}
			arch.close();
		} catch (IOException e) {Alerta csvErr = new Alerta("No se pudo generar el CSV");}
		
	}

	public static void eliminarDeportista(String idDep) {
			
		DBHelper.getServer();
		try {
			Statement sent = DBHelper.getConn().createStatement();
			String query = ("DELETE FROM deportista_en_disciplina WHERE id_deportista = "+idDep);
	        if(sent.executeUpdate(query) > 0) System.out.println("se borro la primera");
	        query = ("DELETE FROM deportista WHERE id= "+idDep);
	        if(sent.executeUpdate(query) > 0) System.out.println("se borro la segunda");
	        sent.close();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public static void editarDeportista(Deportista dep) {
		DBHelper.getServer();
		try {
			Statement sent = DBHelper.getConn().createStatement();
			System.out.println("Estoy en Carga.EDITAR DEPORTISTA");
			String linea =("UPDATE deportista "
					+ "SET nombres ='" +dep.getNombre()
					+"',apellidos = '"+dep.getApellido()
					+"',email= '"+dep.getEmail()
					+"',telefono= "+dep.getTelefono()
					+",id_pais= "+ dep.getPais().getId()
					+" WHERE id="+dep.getId());
			System.out.println(linea);
			sent.executeUpdate(linea);
			linea =("UPDATE deportista_en_disciplina "
					+"SET id_disciplina= "+dep.getDisciplina().getId()
					+" Where id_deportista="+dep.getId());
			System.out.println(linea);
			sent.executeUpdate(linea);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Deportista traerDeportista(String id) {
		// TODO Auto-generated method stub
		
		try{

			Deportista dep = new Deportista();
			
			DBHelper.getServer();
			Statement sent = DBHelper.getConn().createStatement();
			ResultSet busqueda = sent.executeQuery("SELECT * FROM deportista where id ='"+id+"'");

			if(busqueda.next()){
				dep.setId(Integer.parseInt(busqueda.getString("id")));
				dep.setApellido(busqueda.getString("apellidos"));
				dep.setNombre(busqueda.getString("nombres"));
				dep.setEmail(busqueda.getString("email"));
				dep.setTelefono(busqueda.getString("telefono"));
				Pais P = new Pais();
				P.setId(carga.idPaisANombreP( Integer.parseInt(busqueda.getString( "id_pais" ).toString( ) ) ) );
				dep.setIdpais(P);
				Disciplina D = new Disciplina();
				D.setId(carga.idPADisciplina( Integer.parseInt(busqueda.getString("id").toString() ) ) );
				dep.setDisciplina(D);
				return dep;
				}
				else return null;
		}catch (SQLException e) {e.printStackTrace();}
		return null;



	}	
}	

