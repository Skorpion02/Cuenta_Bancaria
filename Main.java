import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    /*las ponemos final porque son constantes, para evitar tener literales por medio del código.
    así sí tenemos que modificar algo, lo hacemos aqui y no tenemos que buscarlo en el código*/
    private static final String MENSAJE_DESPEDIDA = "Fin del programa.\nGracias por utilizar la aplicación.";

    //Mensajes de errores
    private static final String ACCION_NO_VALIDA = "Accion no valida, elija una nueva acción";
    private static final String DNI_NO_VALIDO = "DNI introducido incorrecto";


    //mensajes para el menú
    private static final String MENU =
            "Realiza una nueva acción\n" +
                    "1 Introduce un nuevo gasto\n" +
                    "2 Introduce un nuevo ingreso\n" +
                    "3 Mostrar gastos\n" +
                    "4 Mostrar ingresos\n" +
                    "5 Mostrar saldo\n" +
                    "0 Salir\n" ;

    //mensajes para solicitar atributos clase Usuario
    private static final String SOLICITAR_NOMBRE = "Introduce el nombre de usuario ";
    private static final String SOLICITAR_EDAD = "Introduce la edad del usuario ";
    private static final String SOLICITAR_DNI = "Introduce el DNI del usuario ";
    private static final String USUARIO_CREADO = "Usuario creado correctamente ";

    //mensajes para gasto
    private static final String SOLICITAR_DESCRIPCION_GASTO = "Introduce la descripción ";
    private static final String SOLICITAR_NUEVO_GASTO = "Introduce la cantidad ";

    //mensajes para ingreso
    private static final String SOLICITAR_DESCRIPCION_INGRESO = "Introduce la descripción ";
    private static final String SOLICITAR_NUEVO_INGRESO = "Introduce la cantidad ";

    //Opciones numéricas para las distintas acciones del programa
    private static final int NUEVO_GASTO = 1;
    private static final int NUEVO_INGRESO = 2;
    private static final int MOSTRAR_GASTOS = 3;
    private static final int MOSTRAR_INGRESOS = 4;
    private static final int MOSTRAR_SALDO = 5;
    private static final int SALIR = 0;

    //mensaje saldo
    private static final String SALDO_RESTANTE = "Saldo restante: ";
    private static final String SALDO_ACTUAL = "El saldo actual de la cuenta es: ";

    //Variables de Objeto
    private static Cuenta NEWCuenta;

    public static void main(String[] args) {

        solicitarDatos();
		
		/*lo inicialio a falso para que el bloque do...while se repita, cambiará su estado dentro del
		propio bucle do..while cuando se elija salir*/
        boolean condicionSalir = true;
        int opcion;
		
		/*utilizo un bloque do...while() porque como mínimo se debe de ejecutar una vez
		se podia hacer con while sin problema pero puede que no se ejecute ni una vez.*/
        do {
            mostraMenu();
            opcion = elegirOpcion();
            condicionSalir = ejecutarOpcion(opcion);
        } while (condicionSalir);
    }

    //metodos del metodo main()
    private static void solicitarDatos() {

        //creo un usuario con new
        Usuario miUsuario = new Usuario();

        //condicion de salida del bucle do...while
        boolean DNICorrecto = false;

        String nombre = solicitarString(SOLICITAR_NOMBRE);
        //se comprueba que no pasen un nombre vacio, como no hace falta pues no continue con edad
        while(nombre.isEmpty()) {
            nombre = solicitarString(SOLICITAR_NOMBRE);
        }

        int edad = solicitarEntero(SOLICITAR_EDAD);
        miUsuario.setNombre(nombre);
        miUsuario.setEdad(edad);
			
		/*bucle para comprobar que se cumpla las condiciones
		 de un DNI valido segun el ejercicio la de PAC*/
        do {
            String DNI = solicitarString(SOLICITAR_DNI);
            miUsuario.setDNI(DNI);

            if(miUsuario.setDNI(DNI)) {
                DNICorrecto = true;
            }else {
                System.out.println(DNI_NO_VALIDO);
            }

        } while (!DNICorrecto );

        System.out.println(USUARIO_CREADO);

        //una vez creado el usuario, creo la cuenta y le paso el usuario valido
        NEWCuenta = new Cuenta(miUsuario);

    }

    private static void mostraMenu() {
        System.out.print(MENU);
    }

    private static int elegirOpcion() {
        return leerEntero();
    }

    private static boolean ejecutarOpcion(int opcion) {

        switch (opcion) {
            case NUEVO_GASTO:
                nuevoGasto();
                break;
            case NUEVO_INGRESO:
                nuevoIngreso();
                break;
            case MOSTRAR_GASTOS:
                mostrarGastos();
                break;
            case MOSTRAR_INGRESOS:
                mostrarIngresos();
                break;
            case MOSTRAR_SALDO:
                mostrarSaldo();
                break;
            case SALIR:
                System.out.println(MENSAJE_DESPEDIDA);
                return false;

            default:
                System.out.println(ACCION_NO_VALIDA);
                break;
        }
        return true;
    }

    //metodos del metodo ejecutarOpcion() del switch
    private static void nuevoGasto() {

        String descripcion = solicitarString(SOLICITAR_DESCRIPCION_GASTO);
        double cantidad = solicitarDouble(SOLICITAR_NUEVO_GASTO);

        //sino existe saldo suficiente lanza un error
        try {
            double saldo = NEWCuenta.addGastos(descripcion,cantidad);
            System.out.println(SALDO_RESTANTE + saldo);
        } catch (GastoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void nuevoIngreso() {

        /*Para simplificar el código paso a variables locales todas las llamadas a métodos*/
        String descripcion = solicitarString(SOLICITAR_DESCRIPCION_INGRESO);
        double cantidad = solicitarDouble(SOLICITAR_NUEVO_INGRESO);
        double saldo = NEWCuenta.addIngresos(descripcion,cantidad);

        System.out.println(SALDO_RESTANTE + saldo);
    }

    private static void mostrarIngresos() {

        //hago una copia de la lista de ingresos y trabajo siempre con copias
        List<Ingreso> listaIngresos = NEWCuenta.getIngresos();

        //podemos mostrarlo con un for los datos guardados
        for(Ingreso ingreso : listaIngresos) {
            System.out.println(ingreso);
        }
    }

    private static void mostrarGastos() {

        List<Gasto> listaGastos = NEWCuenta.getGastos();

        //con un for accedemos a List<Gasto>
        for (int i = 0; i < listaGastos.size(); i++) {
			/* tenemos que acceder a cada casilla de List<Gasto>, mediante el método get() de getGastos()
			 y mostrar por pantalla los gastos*/
            System.out.println(listaGastos.get(i));
        }
    }
    //método para solicitar los gastos
    private static void mostrarSaldo() {
        System.out.println(SALDO_ACTUAL + NEWCuenta.getSaldo()+"€");
    }

    //metodos para solicitar datos por consola
    private static String solicitarString(String mensaje) {

        System.out.println(mensaje);
        return leerString();
    }

    private static int solicitarEntero(String mensaje) {
        System.out.println(mensaje);
        return leerEntero();
    }

    private static double solicitarDouble(String mensaje) {
        System.out.println(mensaje);
        return leerDouble();
    }

    /*métodos para leer por consola, lo normal es crear una clase consola sólo con estos métodos
    como la corrección del ejercicio no lo permite*/
    private static String leerString() {
        return new Scanner(System.in).nextLine();
    }

    private static int leerEntero() {
		/*Se podria reutilizar el método leerString, convirtiendo su salida a int mediante la clase envolverte Integer
		con su método parseInt pero nos obligan a emplear Scanner
		sino le pasamos un numero da una NumberFormatException vamos a capturarlo
		esto en teoria no se pide en el enunciado habria que hacerlo con todos los numericos*/
        try {
            return new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Introduzca un número entero: ");
            return leerEntero();
			/*Una regla con la recursividad que dice, si invocamos recursivamente una función,
			un método que devuelva valor siempre aplicarle un “return” porque al final
			quien tiene que retornar algo es la primera invocación, tiene que retornar
			algo tanto con el TRY como por el CATCH*/
        }
    }

    private static double leerDouble() {
        return new Scanner(System.in).nextDouble();
		/*se podria reutilizar el método leerString, convirtiendo su
		salida a double mediante la clase envolverte Double
		con su método parseDouble
		return Integer.parseInt(leerString());
		pero no permiten hacerlo en el ejercicio*/
    }
}