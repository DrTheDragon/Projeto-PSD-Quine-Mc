import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


public class CalcBin {

    public static String[] transformaBin(int[] numVdd) {
        int tamLista = numVdd.length;
        String[] bin = new String[tamLista];
        for (int i = 0; i < tamLista; i++) {
            bin[i] = Integer.toBinaryString(numVdd[i]);
        }
        return bin;
    }

 
    public static String[][] agrupaGrupos(String[] bin, int numVariaveis) {
        for (int i = 0; i < bin.length; i++) {
            while (bin[i].length() < numVariaveis) {
                bin[i] = "0" + bin[i];
            }
        }

        List<List<String>> grupos = new ArrayList<>();
        for (int i = 0; i <= numVariaveis; i++) {
            grupos.add(new ArrayList<>());
        }

        for (String b : bin) {
            int cont = b.replace("0", "").length();
            grupos.get(cont).add(b);
        }

        String[][] result = new String[grupos.size()][];
        for (int i = 0; i < grupos.size(); i++) {
            result[i] = grupos.get(i).toArray(new String[0]);
        }

        return result;
    }

    public static String combina(String a, String b) {
        int dif = 0;
        int pos = -1;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                dif++;
                pos = i;
            }
        }
        if (dif == 1) {
            return a.substring(0, pos) + '-' + a.substring(pos + 1);
        }
        return null;
    }

    public static Set<Integer> cobre(String implicante) {
        Set<String> lista = new HashSet<>();
        lista.add(implicante);
        for (int i = 0; i < implicante.length(); i++) {
            if (implicante.charAt(i) == '-') {
                Set<String> novaLista = new HashSet<>();
                for (String s : lista) {
                    StringBuilder sb0 = new StringBuilder(s);
                    sb0.setCharAt(i, '0');
                    novaLista.add(sb0.toString());

                    StringBuilder sb1 = new StringBuilder(s);
                    sb1.setCharAt(i, '1');
                    novaLista.add(sb1.toString());
                }
                lista = novaLista;
            }
        }
        Set<Integer> result = new HashSet<>();
        for (String s : lista) {
            result.add(Integer.parseInt(s, 2));
        }
        return result;
    }
    
    public static List<String> simplifica(int[] mintermos, int numVariaveis) {
        String[] binarios = transformaBin(mintermos);
        String[][] grupos = agrupaGrupos(binarios, numVariaveis);

        Set<String> usados = new HashSet<>();
        Set<String> novos = new HashSet<>();
        Set<String> todos = new HashSet<>();

        for (String[] grupo : grupos) {
            todos.addAll(Arrays.asList(grupo));
        }

        boolean podeCombinar = true;
        while (podeCombinar) {
            podeCombinar = false;
            Set<String> combinados = new HashSet<>();
            usados.clear();

            for (int i = 0; i < grupos.length - 1; i++) {
                for (String a : grupos[i]) {
                    for (String b : grupos[i + 1]) {
                        String combinacao = combina(a, b);
                        if (combinacao != null) {
                            combinados.add(combinacao);
                            usados.add(a);
                            usados.add(b);
                            podeCombinar = true;
                        }
                    }
                }
            }

            todos.addAll(combinados);

            Map<Integer, List<String>> novoAgrupamento = new HashMap<>();
            for (String s : combinados) {
                int qtd = s.replace("0", "").replace("-", "").length();
                novoAgrupamento.putIfAbsent(qtd, new ArrayList<>());
                novoAgrupamento.get(qtd).add(s);
            }
            

            grupos = new String[novoAgrupamento.size()][];
            int index = 0;
            for (List<String> lista : novoAgrupamento.values()) {
                grupos[index++] = lista.toArray(new String[0]);
            }
        }

        List<String> primos = new ArrayList<>();
        for (String t : todos) {
            if (!usados.contains(t)) {
                primos.add(t);
            }
        }

        return primos;
    }


	public static List<String> filtraEssenciais(List<String> primos, int[] mintermos) {
    Map<Integer, List<String>> cobertura = new HashMap<>();
    for (int m : mintermos) {
        cobertura.put(m, new ArrayList<>());
        for (String primo : primos) {
            if (cobre(primo).contains(m)) {
                cobertura.get(m).add(primo);
            }
        }
    }

    Set<String> essenciais = new HashSet<>();
    Set<Integer> cobertos = new HashSet<>();

    for (int m : mintermos) {
        List<String> cobridores = cobertura.get(m);
        if (cobridores.size() == 1) {
            String essencial = cobridores.get(0);
            if (!essenciais.contains(essencial)) {
                essenciais.add(essencial);
                cobertos.addAll(cobre(essencial));
            }
        }
    }

    while (cobertos.size() < mintermos.length) {
        String melhor = null;
        int maxCobertura = -1;

        for (String p : primos) {
            if (essenciais.contains(p)) continue;

            Set<Integer> cobreP = cobre(p);
            cobreP.removeAll(cobertos);
            if (cobreP.size() > maxCobertura) {
                maxCobertura = cobreP.size();
                melhor = p;
            }
        }

        if (melhor != null) {
            essenciais.add(melhor);
            cobertos.addAll(cobre(melhor));
        } else {
            break;
        }
    }

    return new ArrayList<>(essenciais);
}

	
   
    public static String geraExpressao(List<String> implicantes) {
        StringBuilder expressao = new StringBuilder();

        for (int i = 0; i < implicantes.size(); i++) {
            String implicante = implicantes.get(i);
            if (i > 0) {
                expressao.append(" + ");
            }

            for (int j = 0; j < implicante.length(); j++) {
                char bit = implicante.charAt(j);

                String variavel;
                if (j < 26) {
                    variavel = "" + (char) ('A' + j);
                } else {
                    variavel = "" + (char) ('A' + (j % 26)) + (j / 26);
                }

                if (bit == '1') {
                    expressao.append(variavel);
                } else if (bit == '0') {
                    expressao.append(variavel).append("'");
                }
            }
        }

        return expressao.toString();
    }
}

    return expressao.toString();
}
}

