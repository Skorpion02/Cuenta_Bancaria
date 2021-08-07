public abstract class Dinero {

    //atributos como lo ha pedido la pac de desarrollo
    protected double dinero;
    protected String description;

    //get/set como lo ha pedido la pac de desarrollo
    public double getDinero() {
        return dinero;
    }
    public void setDinero(double dinero) {
        this.dinero = dinero;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

}