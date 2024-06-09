package org.projetoED;

import org.projetoED.BinarySearchTrees.*;

import java.util.ArrayList;

//Esse código roda apenas um algoritmo por vez Para que os outros algoritmos
// não sofram pelo desempenho da máquina sendo afetado ao longo do tempo
public class TreeTeste{
    public SplayTree tree;
    public String arvoreDeBusca; //Escolher o Algoritmo (Para deixar registrado no CSV)
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

    public void creationTest() {
        ArrayList <Double> tempoDeExecução = new ArrayList<Double>(); //Array dos tempos de Execução
        ArrayList <Integer> altura = new ArrayList<Integer>(); //Array da altura das árvores
        ArrayList <Integer> rotacoes = new ArrayList<Integer>(); //Array das rotações

        tree = new SplayTree();

        arvoreDeBusca = tree.toString();

        for(String arquivo : arquivosDados){ //Para cada arquivo txt

            tree = new SplayTree();

            Integer[] array = FileManager.Array(arquivo+".txt");

            long timeBefore = System.nanoTime();

            createTree(array);//Rodando o Algortimo de ordenação

            long timeAfter = System.nanoTime();

            tempoDeExecução.add((timeAfter-timeBefore)/ 1000000000.0);
            altura.add(tree.treeHeight(tree.getRoot()));
            rotacoes.add(tree.getRotations());

            System.out.println(arquivo);
        }

        FileManager.treeBuilderDataRegister(arvoreDeBusca, tempoDeExecução, altura, rotacoes);
    }

    private void createTree(Integer[] array) {
        for(int i = 0; i < array.length; i++){
            tree.insert(array[i]);
        }
    }

    public void searchTest(){
        String arquivo = "3MDesordenado";

        tree = new SplayTree();

        arvoreDeBusca = tree.toString();
        Integer[] arrayTree = FileManager.Array(arquivo+".txt");

        createTree(arrayTree);//Rodando o Algortimo de ordenação

        ArrayList <Double> tempoDeBusca;

        for(String busca : arquivosBusca){
            Integer[] array = FileManager.Array(busca + ".txt");
            tempoDeBusca = new ArrayList<Double>();
            for(int i = 0; i < array.length; i++){
                long timeBeforeSearch = System.nanoTime();
                tree.search(array[i]);
                long timeAfterSearch = System.nanoTime();
                tempoDeBusca.add((timeAfterSearch-timeBeforeSearch)/ 1000000000.0);
            }
            FileManager.treeSearchDataRegister(arvoreDeBusca+"-3MDesordenado-" + busca, array, tempoDeBusca);
        }

        System.out.println(arquivo);
    }
}