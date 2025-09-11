import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido al programa");
        String prop1 = "1.- A:= [(_ p _ _ q) _ (_ p _ s)] _ (s _ q)";
        String prop2 = "2.- B:= [(_ p _ _ q) _ _ r] _ [( _ r _ _ t) _ (q _ t)]";
        System.out.println(prop1);
        System.out.println(prop2);
        int opcion = validarEntrada("Selecciona la preposicion que desee analizar", 1,2);
        System.out.println(opcion);
        List<Boolean> valoresP;
        List<Boolean> valoresQ;
        List<Boolean> valoresS;
        List<Boolean> valorest;
        List<Boolean> valoresArg1;
        List<Boolean> valoresArg2;
        List<Boolean> valoresArg3;
        List<Boolean> valoresArg4;
        List<Boolean> valoresArg5;
        List<Boolean> valoresArg6;

        if (opcion == 1) {
            Primitiva p = new Primitiva(3, 1, "p");
            Primitiva q = new Primitiva(3, 2, "q");
            Primitiva s = new Primitiva(3,3, "s");
            boolean[] negable = {true, false, true, false, true, false, false, false};
            char[] res = new char[8];
            prop1 = solicitarOperadores(negable, res, prop1);
            System.out.println("Proposicion final: " + prop1);
            Compuesta arg2 = new Compuesta(s, false, q, false, res[7]);
            Compuesta arg4 = new Compuesta(p, res[0], q, res[2], res[1]);
            Compuesta arg5 = new Compuesta(p, res[4], s, ' ', res[5]);
            Compuesta arg3 = new Compuesta(arg4, false, arg5, false, res[3]);
            Compuesta arg1 = new Compuesta(arg3, false, arg2, false, res[6]);

            arg1.valoresDeVerdad();
            System.out.println("Tabla de verdad");
            System.out.printf("%-24s %-20s %-18s %-20s %-20s %-20s \n",' ', "a", "b", "c", "d", "A");
            System.out.printf("%-6s %-6s %-6s %-20s %-20s %-20s %-20s %-20s\n \n", "p", "q", "s",
                    String.format("(%sp %s %sq)", res[0], res[1], res[2]), // Arg 4
                    String.format("(%s p %s s)", res[4], res[5]), // Arg 5
                    String.format("a %s b", res[3]), // Arg 3
                    String.format("s %s q", res[7]), // Arg 2
                    String.format("c %s d", res[6])); // Arg 1
            valoresP = p.valoresDeVerdad();
            valoresQ = q.valoresDeVerdad();
            valoresS = s.valoresDeVerdad();
            valoresArg1 = arg1.getValoresVdd();
            valoresArg2 = arg2.getValoresVdd();
            valoresArg3 = arg3.getValoresVdd();
            valoresArg4 = arg4.getValoresVdd();
            valoresArg5 = arg5.getValoresVdd();
            for(int x = 0; x < 8; x++) {
                System.out.printf("%-6s %-6s %-8s %-20s %-19s %-19s %-19s %-19s\n",
                        valoresP.get(x),
                        valoresQ.get(x),
                        valoresS.get(x),
                        valoresArg4.get(x),
                        valoresArg5.get(x),
                        valoresArg3.get(x),
                        valoresArg2.get(x),
                        valoresArg1.get(x));
            }
        } else {
            Primitiva p = new Primitiva(4, 1, "p");
            Primitiva q = new Primitiva(4, 2, "q");
            Primitiva r = new Primitiva(4, 3, "r");
            Primitiva t = new Primitiva(4, 4, "t");

            boolean[] negable = {true, false, true, false, true, false, true, false, true, false, false};
            char[] res = new char[11];
            prop2 = solicitarOperadores(negable, res, prop2);
            System.out.println("Proposicion final: " + prop2);
            Compuesta arg5 = new Compuesta(q, false, t, false, res[10]);
            Compuesta arg4 = new Compuesta(r, res[6], t, res[8], res[7]);
            Compuesta arg3 = new Compuesta(p, res[0], q, res[2], res[1]);
            Compuesta arg2 = new Compuesta(arg3, false, r, res[4], res[3]);
            Compuesta arg6 = new Compuesta(arg4, false, arg5, false, res[9]);
            Compuesta arg1 = new Compuesta(arg2, false, arg6, false, res[5]);

            arg1.valoresDeVerdad();

            valoresP = p.valoresDeVerdad();
            valoresQ = q.valoresDeVerdad();
            valoresS = r.valoresDeVerdad();
            valorest = t.valoresDeVerdad();
            valoresArg1 = arg1.getValoresVdd();
            valoresArg2 = arg2.getValoresVdd();
            valoresArg3 = arg3.getValoresVdd();
            valoresArg4 = arg4.getValoresVdd();
            valoresArg5 = arg5.getValoresVdd();
            valoresArg6 = arg6.getValoresVdd();

            System.out.println("Tabla de verdad");
            System.out.printf("%-30s %-22s %-18s %-20s %-20s %-20s %s \n",' ', "a", "b", "c", "d", "e", "B");
            System.out.printf("%-6s %-6s %-6s %-6s %-20s %-20s %-20s %-20s %-20s %-20s \n \n", "p", "q", "r", "t",
                    String.format("(%sp %s %sq)", res[0], res[1], res[2]), // arg 3
                    String.format("(a %s %s r)", res[3], res[4]), // arg 2
                    String.format("(%s r %s %s t)", res[6], res[7], res[8]), // arg 4
                    String.format("q %s t", res[10]), // arg 5
                    String.format("c %s d", res[9]), // arg 6
                    String.format("b %s e", res[5])); // arg 1
            for(int x = 0; x < 16; x++) {
                System.out.printf("%-6s %-6s %-6s %-6s %-20s %-20s %-20s %-20s %-20s %-20s \n",
                        valoresP.get(x), valoresQ.get(x), valoresS.get(x), valorest.get(x),
                            valoresArg3.get(x), valoresArg2.get(x), valoresArg4.get(x), valoresArg5.get(x), valoresArg6.get(x), valoresArg1.get(x));
            }
        }
    }

    public static String solicitarOperadores(boolean[] negable, char[]res, String prop) {
        for(int x = 0; x < negable.length; x++) {
            System.out.println("Estado actual de la proposicion: " + prop);
            if(negable[x]) {
                int neg = validarEntrada("Desea ingresar negacion: \n 0: No \n 1: Si", 0, 1);
                res[x] = (neg == 1) ? '\u00AC' : ' ';

            } else {
                int opc = validarEntrada("Ingrese el operador deseado: \n 1: ^ (Conjuncion) " +
                        "\n 2: v (Disyuncion)" +
                        "\n 3: \u2192 (Condicional)" +
                        "\n 4: \u2194 (Bicondicional)", 1, 4);
                switch (opc) {
                    case 1:
                        res[x] = '^';
                        break;
                    case 2:
                        res[x] = 'v';
                        break;
                    case 3:
                        res[x] = '\u2192';
                        break;
                    case 4:
                        res[x] = '\u2194';
                        break;
                }
            }
            prop = String.format(prop.replaceFirst("_", "%s"), res[x]);
        }
        return prop;
    }

    public static int validarEntrada(String mensaje, int limInf, int limSup) {
        while(true) {
            try {
                System.out.println(mensaje);
                Scanner sc = new Scanner(System.in);
                int entrada = sc.nextInt();
                if(entrada < limInf || entrada > limSup) {
                    throw new Exception();
                }
                return entrada;
            } catch (Exception ex) {
                System.out.println("Entrada no valida");
            }
        }
    }


}
