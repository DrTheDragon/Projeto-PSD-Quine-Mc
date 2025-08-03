import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String nomeArquivo = sc.nextLine();

		int rows = FuncoesPSD.contaLinha(nomeArquivo);
		String[] linha = FuncoesPSD.lerArquivo(nomeArquivo, rows);
		int[] inOut = FuncoesPSD.inOutArquivo(linha); 
		
		for (int i =0; i < inOut[1]; i++){
		int[] valVdd = FuncoesPSD.funcValoresVdd(linha, inOut[0], i , rows); 
		int numVariaveis = inOut[0];

		List<String> primos = CalcBin.simplifica(valVdd, numVariaveis);
		List<String> essenciais = CalcBin.filtraEssenciais(primos, valVdd);
		String expressao = CalcBin.geraExpressao(essenciais);

		System.out.println("Expressão booleana simplificada "+ i +":");
		System.out.println(expressao);
		System.out.println("\n");
	}
		sc.close();
	}
   
   
   
}

