public class Gasto extends Dinero {

    public Gasto(double gasto, String description) {
        this.dinero=gasto;
        this.setDescription(description);
    }

    @Override
    public String toString() {
        return "Gasto: "+ super.getDescription() +  ", cantidad:" + this.getDinero() +"€";
    }
}