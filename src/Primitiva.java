import java.util.ArrayList;
import java.util.List;

public class Primitiva implements Proposicion{

    private String id;
    private ArrayList<Boolean> valoresVdd = new ArrayList<>();

    public Primitiva(int tamanio, int orden, String id) {
        this.id = id;

        int cantidad = (int) Math.pow(2, tamanio);
        int rep = (int) (cantidad / Math.pow(2, orden));

        boolean valor = false;
        for(int x = 0; x < cantidad; x++) {
            if(x % rep == 0) {
                valor = !valor;
            }
            valoresVdd.add(valor);
        }
    }

    public void imprimirVerdades() {
        System.out.println(valoresVdd);
    }

    public String getId() {
        return id;
    }

    @Override
    public List<Boolean> valoresDeVerdad() {
        return valoresVdd;
    }
}
