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
	

}

