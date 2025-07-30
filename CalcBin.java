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
        if (a.length() != b.length()) return null;

        int dif = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                dif++;
                result.append('-');
            } else {
                result.append(a.charAt(i));
            }
        }

        if (dif == 1) {
            return result.toString();
        } else {
            return null;
        }
    }

    public static List<String> combinaGrupos(String[][] grupos) {
        List<String> combinados = new ArrayList<>();
        int numGrupos = grupos.length;

        for (int i = 0; i < numGrupos - 1; i++) {
            for (int j = 0; j < grupos[i].length; j++) {
                String termo1 = grupos[i][j];
                if (termo1 == null) continue;

                for (int k = 0; k < grupos[i + 1].length; k++) {
                    String termo2 = grupos[i + 1][k];
                    if (termo2 == null) continue;

                    String combinado = combina(termo1, termo2);
                    if (combinado != null && !combinados.contains(combinado)) {
                        combinados.add(combinado);
                    }
                }
            }
        }
        return combinados;
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
	

}

