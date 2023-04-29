package test;


public class Deportista {
	
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private Pais pais= new Pais();
	private Disciplina disciplina= new Disciplina();
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Pais getPais() {
		return pais;
	}
	public void setIdpais(Pais pais) {
		this.pais = pais;
	}

	
	
	
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	public void setDisciplina(String Disciplina) {
		this.disciplina.setNombre(Disciplina);
		this.disciplina.setId(Disciplina);//SACAR DE LA BD con el Nombre en disciplina
	}
	public void setPais(String pais) {
		this.pais.setNombre(pais);
		this.pais.setId(pais);//SACAR DE LA BD con el Nombre	
	}
}
