package Controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Models.EnumCor;
import Models.EnumTamanho;
import Models.Produto;

import static Views.View.montaMenu;
import static Views.View.listaProdutos;

public class Controller {

    public static void limpaTela() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void cadastrarProduto() throws IOException {
        Produto prod = new Produto();

        System.out.println("Digite o local de registro do produto: ");
        prod.setLocal(System.console().readLine().intern());

        System.out.println("Digite o tipo do produto (vestido, blusa, calça, etc.): ");
        prod.setTipo(System.console().readLine().intern());

        System.out.println("Digite a marca do produto:");
        prod.setMarca(System.console().readLine().intern());

        System.out.println("Digite a caracteristica do produto (descrição detalhada da peça):");
        prod.setCaracteristicas(System.console().readLine().intern());

        String numero;
        do {
            System.out.println("Digite o tamanho do produto:");
            listaTamanho();
            numero = System.console().readLine().intern();
        } while (!numero.matches("[0-6]"));
        prod.setTamanho(Integer.parseInt(numero));

        do {
            System.out.println("Digite a cor do produto:");
            listaCores();
            numero = System.console().readLine().intern();
        } while (!numero.matches("[0-9]"));
        prod.setCor(Integer.parseInt(numero));

        do {
            System.out.println("Digite o valor da etiqueta do produto:");
            numero = System.console().readLine().intern();
        } while (!numero.matches("[0-9]*"));
        prod.setValorEtiqueta(Double.parseDouble(numero));

        do {
            System.out.println("Digite o preço sugerido do produto:");
            numero = System.console().readLine().intern();
        } while (!numero.matches("[0-9]*"));
        prod.setPrecoSugerido(Double.parseDouble(numero));

        do {
            System.out.println("Digite o valor pago do produto:");
            numero = System.console().readLine().intern();
        } while (!numero.matches("[0-9]*"));
        prod.setValorPago(Double.parseDouble(numero));

        prod.setValorMargem(prod.getValorPago());
        prod.setCodigo(getUltimoCodigo());
        prod.setDataEntrada();

        // Escrever no aquivo texto
        setProduto(prod);
    }

    public static void editarProduto() throws IOException {
        listaProd();
        System.out.println("");
        System.out.println("Digite o codigo do produto a ser editado");
        String ler = System.console().readLine();
        EditaProd(ler);
    }

    public static void deletarProduto() throws IOException {
        listaProd();
        System.out.println("");
        System.out.println("Digite o codigo do produto a ser deletado");
        String ler = System.console().readLine();
        deleteProd(ler);
    }


    public static void selecionaOpcao(int opcao) throws IOException {

        switch (opcao) {
            case 1:

                cadastrarProduto();
                limpaTela();
                break;

            case 2:

                limpaTela();
                listaProdutos();
                break;

            case 3:

                limpaTela();
                editarProduto();
                break;

            case 4:

                limpaTela();
                deletarProduto();
                break;

            default:
                System.out.println("Digite apenas valores válidos");
        }
    }

    public static void main(String[] args) throws Exception {
        int opcao;

        do {

            opcao = montaMenu();
            selecionaOpcao(opcao);

        } while (opcao <= 4);

        System.out.println("Bye o(*￣▽￣*)ブ ");

    }

    public static String getUltimoCodigo() throws IOException {
        String path = "Registros.txt";
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader(path));
            String linha = "";
            String ultimaLinha = "";
            while (true) {
                if (linha != null && linha !="") {
                    ultimaLinha = linha;

                } else
                    break;
                linha = buffRead.readLine();
            }
            buffRead.close();
            if (ultimaLinha == "")
                return "0";
            return ultimaLinha.split(";")[0];
        } catch (Exception e) {
            return "0";
        }
    }

    public static void listaProd() throws IOException {
        String path = "Registros.txt";
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";

        try {

            while (true) {
                linha = buffRead.readLine();
                if (linha != null && linha !="") {
                    var informações = linha.split(";");
                    var registro = "Cod : " + informações[0] + " Data de reg  : " + informações[1] + ", Local : "
                            + informações[2] + ", Tipo : " + informações[3] + ", Marca : " + informações[4]
                            + ", Caracteristica : " + informações[5] + ", Tamanho : " + informações[6] + ", Cor : "
                            + informações[7] + ", Valor Etiq : " + informações[8] + ", valor Margem : " + informações[9]
                            + ", Preço sugerido : " + informações[10] + ", Quantidade : " + informações[11];
                    System.out.println(registro);
                } else
                    break;
            }
            buffRead.close();

        } catch (Exception e) {

            System.out.println("┗( T﹏T )┛ Ocorreu um erro ao listar os produtos: " + e);
            System.out.println("Digite apenas o código numérico do produto!");
        }
    }

    public static void setProduto(Produto prod) throws IOException {
        var path = "Registros.txt";

        FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        // cria o arquivo txt separando as informações do produto por "|"
        String produtoString = Integer.toString(prod.getCodigo()) + ";" + prod.getDataEntrada() + ";" + prod.getLocal()
                + ";" + prod.getTipo() + ";" + prod.getMarca() + ";" + prod.getCaracteristicas() + ";"
                + prod.getTamanho() + ";" + prod.getCor() + ";" + Double.toString(prod.getValorEtiqueta()) + ";"
                + Double.toString(prod.getValorMargem()) + ";" + Double.toString(prod.getPrecoSugerido()) + ";1";

        out.println(produtoString);
        out.close();
    }

    public static void deleteProd(String codigo) throws IOException {
        String path = "Registros.txt";
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        var linhas =0;
        String resto = "";
        while (true) {
            linha = buffRead.readLine();
            if (linha != null && linha !="") {
                var informações = linha.split(";");
                if (informações[0].equals(codigo.intern()))
                    continue;

                else
                {
                    if(linhas != 0)
                        resto += "\n";
                    resto +=  linha ;
                }
                    linhas++;
            }
            else 
                break; 
        }

        String resposta = "";
        FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        do {
            System.out.println("Deseja mesmo excluir esse registro? y/n");
            resposta = System.console().readLine();

            if (resposta.intern() == "y") {
                out.println(resto);
            }
        } while (resposta.intern() != "y" && resposta.intern() != "n");
        
        out.close();
        buffRead.close();
        limpaTela();
    }

    public static void EditaProd(String codigo) throws IOException {
        String path = "Registros.txt";
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        String registros = "";
        String resposta = "";
        String novoValor ="";
        while (true) {
            linha = buffRead.readLine();
            if (linha != null) {
                var informações = linha.split(";");
                if (informações[0].equals(codigo.intern()))
                    {
                        do{
                        System.out.println("Digite o numero da caracteristica que deseja editar:" 
                        + "\n1) local" 
                        + "\n2) tipo" 
                        + "\n3) marca"
                        + "\n4) caracteristicas" 
                        + "\n5) tamanho"
                        + "\n6) cor" 
                        + "\n7) valor Etiqueta"
                        + "\n8) quantidade em estoque");
                        resposta = System.console().readLine();
                        limpaTela();
                        } while (!resposta.matches("[0-8]"));

                        var propiedade = Integer.parseInt(resposta.intern()) + 1;

                        do{
                        System.out.println("Digite o novo valor para propiedade");
                        novoValor = System.console().readLine();
                        limpaTela();
                        } while ((!novoValor.matches("[0-6]") && propiedade==6) || (!novoValor.matches("[0-9]") && propiedade==7) || (!novoValor.matches("[0-9]*") && propiedade==8) || (!novoValor.matches("[0-9]*") && propiedade==9));

                        if(propiedade ==9)
                            informações[11] = novoValor;
                        else
                            informações[propiedade] = novoValor;
                        
                        String alterado = "";
                        for(String valor : informações)
                        {
                            alterado+=valor + ";";
                        }
                        registros+=alterado;
                    }
                else
                    registros += linha;
                if (!informações[0].equals(getUltimoCodigo()))
                    registros += "\n";
                else
                    break;
            } else
                break;
        }

        FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.println(registros);
        out.close();
        buffRead.close();
        limpaTela();
    }

    public static void listaCores() throws IOException {

        int cont = 1;

        for (EnumCor cores : EnumCor.values()) {
            System.out.println(Integer.toString(cont) + "-" + cores);
            cont++;
        }
    }

    public static void listaTamanho() throws IOException {

        int cont = 1;

        for (EnumTamanho tamanhos : EnumTamanho.values()) {
            System.out.println(Integer.toString(cont) + "-" + tamanhos);
            cont++;
        }
    }
}