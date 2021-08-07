import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.Scanner;

public class Cuenta {

    //atributos
    private double saldo;
    private Usuario usuario;
    private List<Gasto> gastos;
    private List<Ingreso> ingresos;

    //constructor
    public Cuenta(Usuario usuario) {
        this.usuario=usuario;
        this.saldo = 0.0;

        //inicializo las colecciones en el constructor
        this.ingresos = new ArrayList<>();
        this.gastos = new ArrayList<>();
    }

    //get/set
    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double addIngresos(String descripcion, double cantidad) {
        //agrego un ingreso
        ingresos.add(new Ingreso(cantidad, descripcion));
        //a saldo se le agrega la cantidad del ingreso
        this.saldo += cantidad;

        //mostrar el saldo
        return this.getSaldo();
    }

    public double addGastos(String descripcion, double cantidad) throws GastoException {

        boolean condicionGastoException = this.getSaldo()<cantidad;

        //comprobación si hay saldo suficiente antes de restar la cantidad, sino hay no se hace nada
        if(condicionGastoException) {
            throw new GastoException();
        }

        //agrego un gasto
        gastos.add(new Gasto(cantidad, descripcion));

        //a saldo le resto la cantidad del gasto
        this.saldo -= cantidad;
        return this.getSaldo();
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public List<Gasto> getGastos() {
        return gastos;
    }

    @Override
    public String toString() {
        return "Usuario: " + this.getUsuario() + ", saldo: " + this.getSaldo();
    }
}