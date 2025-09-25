import java.util.Arrays;
import java.util.Scanner;

public class Inferencias {
    public static void main(String[] args) {
        System.out.println("Bienvenido al programa, en el momento que se llenen todos los valores se procedera a la validaciond el argumento");
        String[] props = {"1.- _ ( p _ q )", "2.- _ ( p _ r )", "3.- _ ( _ p _ _ s)", "4.- _ ( r _ t )","5.- _ p _ _ s"};
        String[] propsMod = props.clone();

        boolean[] neg1 = {true, false};
        boolean[] neg2 = {true, false};
        boolean[] neg3 = {true, true, false, true};
        boolean[] neg4 = {true, false};
        boolean[] negConc = {true, false, true};
        boolean[][] negaciones = {neg1, neg2, neg3, neg4, negConc};

        char[] val1 = new char[2];
        char[] val2 = new char[2];
        char[] val3 = new char[4];
        char[] val4 = new char[2];
        char[] valConc = new char[3];
        char[][] valores = {val1, val2, val3, val4, valConc};

        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido al programa");
        while (!estanLlenos(valores)) {
            System.out.println("Proposiciones:");
            Arrays.stream(propsMod).forEach(System.out::println);
            int valor = TablasVerdad.validarEntrada("Escoja la proposicion que desee modificar, sea la 5ta proposicion la conclusion", 1, 5);
            System.out.println("Modificando la premisa " + valor);
            propsMod[valor-1] = TablasVerdad.solicitarOperadores(negaciones[valor - 1], valores[valor - 1], props[valor - 1]);
        }

        System.out.println("Argumento final:");
        Arrays.stream(propsMod).forEach(System.out::println);

        Primitiva p = new Primitiva(5,1, "p");
        Primitiva q = new Primitiva(5,2, "q");
        Primitiva r = new Primitiva(5,3, "r");
        Primitiva s = new Primitiva(5,4, "s");
        Primitiva t = new Primitiva(5,5, "t");

        Compuesta p1 = new Compuesta(p, false, q, false, val1[1]);
        Compuesta p2 = new Compuesta(p, false, r, false, val2[1]);
        Compuesta p3 = new Compuesta(p, false, s, false, val2[1]);
        Compuesta p4 = new Compuesta(r, false, t, false, val4[1]);

        Compuesta conclusion = new Compuesta(p, valConc[0], s, valConc[2], valConc[1]);

        Compuesta comp1 = new Compuesta(p1, val1[0], p2, val2[0], '^');
        Compuesta comp2 = new Compuesta(p3, val3[0], p4, val4[0], '^');

        Compuesta premisas = new Compuesta(comp1, false, comp2, false, '^');
        Compuesta fin = new Compuesta(premisas,false, conclusion, false, '\u2192');

        System.out.println("Es un argumento " + ((fin.valoresDeVerdad().size() == fin.getValoresVdd().stream().filter(
                (valor) -> {return valor;}
        ).toList().size()) ? "valido" : "invalido"));
    }


    public static boolean estanLlenos(char[]... valores) {
        for(char[] arreglo : valores) {
            for(char c: arreglo) {
                if (c == '\u0000') return false;
            }
        }
        return true;
    }
}
