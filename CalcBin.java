import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CalcBin{
	
	
	
	static public String [] transformaBin (int numVdd []){
	int tamLista = numVdd.length;
	String [] bin = new String [tamLista];
	for (int i=0; i <tamLista; i++){
		bin[i] = Integer.toBinaryString(numVdd[i]);
		
	}
	return bin;
}

	static public String[][] agrupaGrupos(String bin[]){
	int tamLista = bin.length;
	int tamBitsTotal = bin[tamLista-1].length();
	int grupoDetect, tamBits;
	String[][] grupoList = new String [tamLista] [tamBitsTotal+1];
	
	for (int i =0; i< tamLista; i++){
		grupoDetect=0;
		tamBits = bin[i].length();
		for (int j=0; j< tamBits; j++){
		if (bin[i].charAt(j) == '1'){
			grupoDetect++;
		}
		grupoList[i][grupoDetect] = bin[i];
		}	
	}	
		return grupoList;
	}

    public static String combina(String a, String b) {
        int dif = 0;
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                resultado.append('-');
                dif++;
            } else {
                resultado.append(a.charAt(i));
            }
        }

        if (dif == 1) {
            return resultado.toString();
        } else {
            return null;
        }
    }

    public static List<String> geraImplicantes(String[] mintermos) {
        List<String> implicantes = new ArrayList<>();
        List<String> termos = new ArrayList<>();
        Set<String> combinados = new HashSet<>();

        for (int m = 0; m < mintermos.length; m++) {
            termos.add(mintermos[m]);
        }

        for (int i = 0; i < termos.size(); i++) {
            boolean combinado = false;
            for (int j = i + 1; j < termos.size(); j++) {
                String comb = combina(termos.get(i), termos.get(j));
                if (comb != null) {
                    combinado = true;
                    combinados.add(comb);
                }
            }
            if (!combinado) {
                implicantes.add(termos.get(i));
            }
        }

        implicantes.addAll(combinados);
        return implicantes;
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

