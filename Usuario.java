import java.util.regex.Pattern;

public class Usuario {

    //Atributos de clase
    private String nombre;
    private int edad;
    private String DNI;

    //constructor
    public Usuario() {
    }

    //metodos get/set pedidos por la pac de desarrollo
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDNI() {
        return this.DNI;
    }

    public boolean setDNI(String DNI) {

        boolean salir = false;
		/*Si la condicion se cumple da valor al atributo de clase DNI y cambia el valor
		del booleano a true*/
        if (validarDNI(DNI)) {
            this.DNI = DNI;
            salir = true;
        }

        return salir;
    }

    @Override
    public String toString() {
        return " Usuario: " + getNombre() + ", edad: " + this.getEdad() + ", DNI: " + this.getDNI();
    }


    //Metodo para validar el DNI

    private boolean validarDNI(String DNI) {
		/*Creamos el patron para identificar el DNI, que sea hasta 8 y las mayúsculas
		entre A y la Z*/
        final String patron = "^\\d{8}-?[A-Z]$";

        return Pattern.matches(patron, DNI);
    }

}