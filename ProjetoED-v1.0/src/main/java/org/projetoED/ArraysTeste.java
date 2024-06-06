package org.projetoED;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArraysTeste {
    public static Integer[] Array(String fileName) {
        try {
            File arquivo = new File("ProjetoED-v1.0/src/main/java/org/projetoED/data/" + fileName);
            Scanner scanner = new Scanner(arquivo);
            int tamanho = 0;
            while (scanner.hasNextLine()) {
                tamanho++;
                scanner.nextLine();
            }
            scanner.close();

            Integer[] numeros = new Integer[tamanho];
            scanner = new Scanner(arquivo);

            for (int i = 0; i < tamanho; i++) {
                numeros[i] = (int)Double.parseDouble(scanner.nextLine());
            }
            scanner.close();

            return numeros;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
            return null;
        }
    }
}
