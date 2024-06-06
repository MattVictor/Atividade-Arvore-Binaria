package org.projetoED;

import com.opencsv.CSVWriter;
import org.projetoED.BinarySearchTrees.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Esse código roda apenas um algoritmo por vez Para que os outros algoritmos
// não sofram pelo desempenho da máquina sendo afetado ao longo do tempo
public class Main {

    public static String arvoreDeBusca = "Binary"; //Escolher o Algoritmo (Para deixar registrado no CSV)
    public static String[] arquivosDados = {
            "1MCrescente",
            "1MDecrescente",
            "1MDesordenado",
            "1MDesordenado10Finais",
            "1MDesordenado10Iniciais",
            "2MCrescente",
            "2MDecrescente",
            "2MDesordenado",
            "2MDesordenado10Finais",
            "2MDesordenado10Iniciais",
            "3MCrescente",
            "3MDecrescente",
            "3MDesordenado",
            "3MDesordenado10Finais",
            "3MDesordenado10Iniciais"
    };
    public static String[] arquivosBusca = {
            "1MDistintos",
            "1MRepetidos"
    };


    public static void main(String[] args) {
        ArrayList <Double> tempoDeExecução = new ArrayList<Double>(); //Array dos tempos de Execução
        ArrayList <Integer> altura = new ArrayList<Integer>(); //Array da altura das árvores
        ArrayList <Integer> rotacoes = new ArrayList<Integer>(); //Array das rotações
        BinaryTree tree = new BinaryTree();

        for(String arquivo : arquivosDados){ //Para cada arquivo txt

            Integer[] array = ArraysTeste.Array(arquivo+".txt");

            long timeBefore = System.nanoTime();

            tree = createTree(array);//Rodando o Algortimo de ordenação

            long timeAfter = System.nanoTime();

            tempoDeExecução.add((timeAfter-timeBefore)/ 1000000000.0);
            altura.add(tree.maxDepth(tree.getRoot()));
            rotacoes.add(tree.getRotations());

//            ArrayList <Double> tempoDeBusca = new ArrayList<Double>(); //Array dos tempos de Execução
//
//            for(String busca : arquivosBusca){
//                for(int i = 0; i < array.length; i++){
//                    long timeBeforeSearch = System.nanoTime();
//                    tree.search(array[i]);
//                    long timeAfterSearch = System.nanoTime();
//                    tempoDeBusca.add((timeAfterSearch-timeBeforeSearch)/ 1000000000.0);
//                }
//                treeSearchDataRegister(arvoreDeBusca+"-"+arquivo + "-" + busca, array, tempoDeBusca);
//            }

            System.out.println(arquivo);
        }

        treeBuilderDataRegister("Resultados"+arvoreDeBusca+".csv", tempoDeExecução, altura, rotacoes);

    }

    private static BinaryTree createTree(Integer[] array) {
        BinaryTree tree = new BinaryTree();

        for(int i = 0; i < array.length; i++){
            tree.put(array[i]);
        }

        return tree;
    }


    public static void treeBuilderDataRegister(String filePath, ArrayList<Double> tempo,
                                    ArrayList<Integer> altura,
                                    ArrayList<Integer> rotacoes){
        //Registrando os resultados das criações das árvores em um arquivo .csv
        File file = new File(filePath);
        try {

            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = { "Arvore de Busca", "Arquivo", "Tempo De Execução", "Altura", "Quantidade de Rotações" };
            writer.writeNext(header);

            for(int i = 0; i < arquivosDados.length; i++){
                String[] data = {arvoreDeBusca,
                        arquivosDados[i],
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

    public static void treeSearchDataRegister(String filePath, Integer[] elementos, ArrayList<Double> tempos){
        //Registrando os resultados das bucas em um arquivo .csv
        File file = new File(filePath);
        try {

            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = { "Elemento","Tempo" };
            writer.writeNext(header);

            for(int i = 0; i < elementos.length; i++){
                String[] data = {elementos[i].toString(),
                        tempos.get(i).toString()};
                writer.writeNext(data);
            }

            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}