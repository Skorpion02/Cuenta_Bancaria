public class GastoException extends Exception{

    //Nos obligan pasar a través del padre debido a quue no permite acabar en String
    public GastoException() {
        super("Saldo insuficiente o cero, verifique saldo primero ");
    }

}