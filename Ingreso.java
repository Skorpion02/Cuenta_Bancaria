public class Ingreso extends Dinero{

    //constructor
    public Ingreso(double ingreso, String description) {
        this.setDinero(ingreso);
        super.setDescription(description);
    }

    //Resultado del programa
    @Override
    public String toString() {
        return "Ingreso: "+ super.getDescription() +  ", cantidad:" + this.getDinero() +"€";
    }

}