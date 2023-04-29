package test;

public class Disciplina {
	private int id;
	private String nombre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setId(String disciplina) {
		// ME LLEGA EL NOMBRE DE LA DISCIPLINA QUIERO EL ID QUE ESTA EN LA BD
		this.id= carga.disciplinaID(disciplina);
		//lo que dice la bd con ese string
		
	}
	
}
