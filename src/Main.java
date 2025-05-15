import java.util.*;

/**
 * Vamos a hacer el lanzador de las clases Aldea y Aldeano
 */
public class Main {
    static Aldea aldea= new Aldea((new TreeMap<>()));

    /**
     * Comenzamos con el método
     */

    public static void main(String[] args) {
        int opcion;
        aldea.cargarDatos();
        Scanner sc = new Scanner(System.in);

        /*
          Menú de opciones
         */

        do{
            System.out.println("\n\t------------ALDEA------------");
            System.out.println("\t 1. Añadir aldeano");
            System.out.println("\t 2. Eliminar aldeano.");
            System.out.println("\t 3. Ordenar aldeanos.");
            System.out.println("\t 4. Buscar aldeano por su nombre.");
            System.out.println("\t 5. Buscar aldeano por su profesión.");
            System.out.println("\t 6. Eliminar aldeanos aleatorios.");
            System.out.println("\t 7. Mostrar aldeanos.");
            System.out.println("\t 0. Salir.");

            opcion= sc.nextInt();

            switch (opcion){
                case 1 -> {
                    System.out.print("Nombre del aldeano: ");
                    String nombre = new Scanner(System.in).nextLine();

                    System.out.print("Profesión del aldeano: ");
                    String profesion = new Scanner(System.in).nextLine();

                    System.out.print("Número de asesinatos: ");
                    int numAsesinados = new Scanner(System.in).nextInt();

                    aldea.agregarAldeano(new Aldeano(nombre, profesion, numAsesinados));
                }

                case 2 -> {
                    System.out.print("Nombre del aldeano a eliminar: ");
                    String nombreEliminar = new Scanner(System.in).nextLine();

                    aldea.eliminarAldeano(nombreEliminar);
                }

                case 3 -> {
                    System.out.println("Ordenar por: ");
                    System.out.println("\t 0. Clave");
                    System.out.println("\t 1. Nombre");
                    System.out.println("\t 2. Profesión");
                    System.out.println("\t 3. Asesinatos");

                    int comoOrdenar = new Scanner(System.in).nextInt();
                    aldea.ordenarLista(comoOrdenar);
                }

                case 4 -> {
                    System.out.print("Nombre del aldeano para buscar: ");
                    String nombreBuscar = new Scanner(System.in).nextLine();
                    aldea.buscarAldeanoNombre(nombreBuscar);
                }

                case 5 -> {
                    System.out.print("Profesión del aldeano que buscas: ");
                    String profesionBuscar = new Scanner(System.in).nextLine();
                    aldea.buscarProfesion(profesionBuscar);
                }

                case 6 -> {
                    System.out.println("La aldea tiene " + aldea.getRegistro().size() + " aldeanos");
                    System.out.print("Cantidad de aldeanos a eliminar: ");
                    int cantidad = new Scanner(System.in).nextInt();
                    aldea.expulsarAleatorios(cantidad);
                }

                case 7 -> aldea.mostrarAldeanos();

                case 0 -> aldea.guardarDatos();

                default -> System.out.println("Opción no válida");

            }
        } while ((opcion) != 0);
        System.out.println("Programa finalizado");



    }
}