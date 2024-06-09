package org.projetoED;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
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

    public static void treeBuilderDataRegister(String arvoreDeBusca, ArrayList<Double> tempo,
                                               ArrayList<Integer> altura,
                                               ArrayList<Integer> rotacoes){
        //Registrando os resultados das criações das árvores em um arquivo .csv
        File file = new File("Resultados"+arvoreDeBusca+".csv");
        try {

            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = { "Arvore de Busca", "Arquivo", "Tempo De Execução", "Altura", "Quantidade de Rotações" };
            writer.writeNext(header);

            for(int i = 0; i < TreeTeste.arquivosDados.length; i++){
                String[] data = {arvoreDeBusca,
                        TreeTeste.arquivosDados[i],
                        tempo.get(i).toString(),
                        altura.get(i).toString(),
                        rotacoes.get(i).toString()};
                writer.writeNext(data);
            }

            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void treeSearchDataRegister(String filePath, Integer[] elementos, ArrayList<Double> tempos) {
        //Registrando os resultados das bucas em um arquivo .csv
        File file = new File(filePath);
        try {

            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = {"Elemento", "Tempo"};
            writer.writeNext(header);

            for (int i = 0; i < elementos.length; i++) {
                String[] data = {elementos[i].toString(),
                        tempos.get(i).toString()};
                writer.writeNext(data);
            }

            Double tempoTotal = 0.0;
            for(int i = 0; i < tempos.size(); i++){
                tempoTotal += tempos.get(i);
            }

            tempoTotal /= tempos.size();

            String[] data = {"Média",
                    tempoTotal.toString()};
            writer.writeNext(data);


            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
