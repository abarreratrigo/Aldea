import java.util.*;
import java.io.*;

public class Aldea {
    Map<Integer, Aldeano> registro;
    static final File ARCHIVO_BINARIO = new File ("registro.dat");

    public Map<Integer, Aldeano> getRegistro(){
        return registro;
    }

    public void setRegistro(Map<Integer, Aldeano> registro){
        this.registro = registro;
    }

    public Aldea (Map <Integer,Aldeano> registro){
        this.registro = registro;
    }

    /**
     *  Para añadir un aldeano al registro
     *  */

    public void agregarAldeano(Aldeano a){
        registro.put(registro.size() + 1,a);
        guardarDatos();
    }

    /**
     *  Para eliminar un aldeano
     *  */

    public void eliminarAldeano(String nombre){
        System.out.println("Vamos a intentar eliminar al aldeano " + nombre);
        Aldeano aEliminar = buscarAldeanoNombreEliminar(nombre);

        if (aEliminar == null){
            System.out.println("No hay ningún aldeano con ese nombre");
            return;
        }

        boolean encontrado = false;
        Integer idAldeanoEliminar = null;

        for(Map.Entry<Integer,Aldeano> a : registro.entrySet()){
            if (a.getValue().equals(aEliminar)){
                encontrado = true;
                idAldeanoEliminar = a.getKey();
            }
        }

        if (encontrado && idAldeanoEliminar != null){
            registro.remove(idAldeanoEliminar);
            System.out.println("Se ha eliminado el aldeano: " + buscarAldeanoNombreEliminar(nombre) + ", con ID: " + idAldeanoEliminar);
        }else {
            System.out.println("El aldeano no está registrado");
        }

        guardarDatos();
    }

    /**
     * Para ordenar a los aldeanos
     * 0 = Ordenar por clave
     * 1 = Ordenar por nombre
     * 2 = Ordenar por profesión
     * 3 = Ordenar por asesinatos
     */

    public void ordenarLista(int comoOrdenar){
        Comparator<Map.Entry<Integer, Aldeano>> comparador;

        switch (comoOrdenar){
            case 0 -> comparador = Map.Entry.comparingByKey();
            case 1 -> comparador = Comparator.comparing(a -> a.getValue().getNombre());
            case 2 -> comparador = Comparator.comparing(a -> a.getValue().getProfesion());
            case 3 -> comparador = Comparator.comparing(a -> a.getValue().getNumAsesinados());
            default -> {
                System.out.println("Criterio no válido");
                return;
            }
        }
        registro.entrySet().stream()
                .sorted(comparador)
                .forEach(a -> System.out.println(a.getKey() + " - " + a.getValue()));
    }

    /**
     * Buscar por nombre para saber qué aldeano quieres seleccionar
     */

    public void buscarAldeanoNombre (String nombre) {
        Scanner sc = new Scanner(System.in);
        int registros = 0;
        for (Map.Entry<Integer, Aldeano> a : registro.entrySet()) {
            if (a.getValue().getNombre().contains(nombre)) {
                registros++;
                System.out.println(a.getKey() + " - " + a.getValue());
            }
            else System.out.println("No existe ningún aldeano con ese nombre");
        }
    }

    public Aldeano buscarAldeanoNombreEliminar (String nombre) {
        Scanner sc = new Scanner(System.in);
        int registros = 0;
        for (Map.Entry<Integer, Aldeano> a : registro.entrySet()) {
            if (a.getValue().getNombre().contains(nombre)) {
                registros++;
                System.out.println(a.getKey() + " - " + a.getValue());
            }
        }

        if (registros >0){
            System.out.println("\n Clave del aldeano que quieres eliminar: ");
            int claveBuscada= sc.nextInt();
            return registro.get(claveBuscada);
        } else {
            System.out.println("No hay aldeanos con ese nombre");
            return null;
        }
    }

    /**
     * Busca por profesión y muestra que aldeano quieres elegir
     */

    public void buscarProfesion(String profesion) {
        Scanner sc = new Scanner(System.in);
        int registros = 0;
        for (Map.Entry<Integer, Aldeano> a : registro.entrySet()) {
            if (a.getValue().getProfesion().contains(profesion)) {
                registros++;
                System.out.println(a.getKey() + " - " + a.getValue());
            }
        }
    }

    /**
     * Con el toString() hecho, mostramos los aldeanos
     */

    public void mostrarAldeanos(){
        System.out.println("\n\t------------ALDEANOS------------");
        for (Aldeano a : registro.values()){
            System.out.println("\t" + a.toString());
        }
    }

    /**
     * Eliminamos una cantidad de aldeanos de forma aleatoria
     */

    public void expulsarAleatorios (int cantidad){
        if (cantidad <=0 || cantidad > registro.size()){
            System.out.println("Cantidad introducida inválida");
            return;
        }

        List <Integer> claves= new ArrayList<>(registro.keySet());
        Collections.shuffle(claves);

        for (int i = 0; i < cantidad; i++){
            Integer clave= claves.get(i);
            System.out.println("Eliminando a: " + registro.get(clave));
            registro.remove(clave);
        }

        Map <Integer, Aldeano> nuevoRegistro = new TreeMap<>();
        int nuevaClave = 1;

        for (Aldeano a : registro.values()){
            nuevoRegistro.put(nuevaClave++, a);
        }
        registro = nuevoRegistro;
    }

    /**
     * Guardamos los datos de los aldeanos en un fichero binario
     */

    public void guardarDatos(){
        try (ObjectOutputStream oos= new ObjectOutputStream( new FileOutputStream(ARCHIVO_BINARIO))){
            oos.writeObject(registro);
            System.out.println("Datos guardados correctamente");
        } catch (IOException e) {
            System.out.println("No se han podido guardar los datos");
        }
    }

    /**
     * Cargamos los datos almacenados
     */

    public void cargarDatos(){
        if (!ARCHIVO_BINARIO.exists()){
            System.out.println("El archivo con los datos no existe");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_BINARIO))){
            registro = (Map<Integer,Aldeano>) ois.readObject();
            System.out.println("Datos cargados correctamente");
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Error al cargar los datos");
        }

    }
}
