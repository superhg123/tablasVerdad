import java.util.ArrayList;
import java.util.List;

public class Compuesta implements Proposicion{
    private Proposicion elemento1;
    private boolean negacion1;
    private Proposicion elemento2;
    private boolean negacion2;
    private char operador;
    private ArrayList<Boolean> valoresVdd = new ArrayList<>();

    public Compuesta(Proposicion elemento1, boolean negacion1, Proposicion elemento2, boolean negacion2, char operador) {
        this.elemento1 = elemento1;
        this.negacion1 = negacion1;
        this.elemento2 = elemento2;
        this.negacion2 = negacion2;
        this.operador = operador;
    }

    public Compuesta(Proposicion elemento1, char negacion1, Proposicion elemento2, char negacion2, char operador) {
        this.elemento1 = elemento1;
        this.negacion1 = (negacion1 == '\u00AC');
        this.elemento2 = elemento2;
        this.negacion2 = (negacion2 == '\u00AC');
        this.operador = operador;
    }

    @Override
    public List<Boolean> valoresDeVerdad() {
        List<Boolean> valores1 = elemento1.valoresDeVerdad();
        if(negacion1) valores1 = valores1.stream().map(valor -> {return !valor;}).toList();
        List<Boolean> valores2 = elemento2.valoresDeVerdad();
        if(negacion2) valores2 = valores2.stream().map(valor -> {return !valor;}).toList();
        for(int x = 0; x < valores1.size(); x++) {
            switch ((int) operador) {
                case 94:
                    valoresVdd.add(valores1.get(x) && valores2.get(x));
                    break;
                case 118:
                    valoresVdd.add(valores1.get(x) || valores2.get(x));
                    break;
                case 8594:
                    valoresVdd.add((!valores1.get(x) || valores2.get(x)));
                    break;
                case 8596:
                    valoresVdd.add((!valores1.get(x) && !valores2.get(x)) || (valores1.get(x) && valores2.get(x)));
                    break;
                default:
                    System.out.println("No existe puesto que el caracter es: " + (int) operador);
                    break;
            }
        }
        return valoresVdd;
    }

    public ArrayList<Boolean> getValoresVdd() {
        return valoresVdd;
    }
}
