package test;

public class Pais {
	private String nombre;
	private int id;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setId(String pais) {

		// ME LLEGA EL NOMBRE DE LA DISCIPLINA QUIERO EL ID QUE ESTA EN LA BD
		this.id= carga.paisID(pais);
		//lo que dice la bd con ese string
		
	}
	
}
