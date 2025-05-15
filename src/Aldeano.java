import java.io.Serializable;
import java.util.Objects;

public class Aldeano implements Comparable<Aldeano>, Serializable {
    String nombre;
    String profesion;
    int numAsesinados;

    public Aldeano(String nombre, String profesion, int numAsesinados){
        this.nombre = nombre;
        this.profesion = profesion;
        this.numAsesinados = numAsesinados;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getNumAsesinados() {
        return numAsesinados;
    }
    public void setNumAsesinados(int numAsesinados) {
        this.numAsesinados = numAsesinados;
    }

    @Override
    public int compareTo(Aldeano a){
        return 0;
    }

    @Override
    public String toString(){
        return String.format("%-10s | %-10s | %-3d", nombre, profesion, numAsesinados);
    }
}
