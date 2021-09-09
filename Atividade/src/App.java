import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

public class App {

    public static void limpaTela() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void cadastrarProduto() throws IOException {
        Produto prod = new Produto();

        System.out.println("Digite o nome do produto a ser cadastrado");
        prod.setLocal(System.console().readLine().intern());

        System.out.println("Digite o tipo do produto a ser cadastrado");
        prod.setTipo(System.console().readLine().intern());

        System.out.println("Digite a marca do produto a ser cadastrado");
        prod.setMarca(System.console().readLine().intern());

        System.out.println("Digite a caracteristica do produto a ser cadastrado");
        prod.setCaracteristicas(System.console().readLine().intern());

        String numero;
        do{
        System.out.println("Digite o tamanho do produto a ser cadastrado");
        numero = System.console().readLine().intern();
        }while(!numero.matches("[0-9]*"));
        prod.setTamanho(Integer.parseInt(numero));

        do{
        System.out.println("Digite a cor do produto a ser cadastrado");
        numero = System.console().readLine().intern();
        }while(!numero.matches("[0-9]*"));
        prod.setCor(Integer.parseInt(numero));

        do{
        System.out.println("Digite o valor da etiqueta do produto a ser cadastrado");
        numero = System.console().readLine().intern();
        }while(!numero.matches("[0-9]*"));
        prod.setValorEtiqueta(Double.parseDouble(numero));

        do{
        System.out.println("Digite o preço sugerido do produto a ser cadastrado");
        numero = System.console().readLine().intern();
        }while(!numero.matches("[0-9]*"));
        prod.setPrecoSugerido(Double.parseDouble(numero));

        do{
        System.out.println("Digite o valor pago do produto a ser cadastrado");
        numero = System.console().readLine().intern();
        }while(!numero.matches("[0-9]*"));
        prod.setValorPago(Double.parseDouble(numero));

        prod.setValorMargem(prod.getValorPago());
        prod.setCodigo(getUltimoCodigo());
        prod.setDataEntrada();

        // Escrever no aquivo texto
        setProduto(prod);
    }

    public static void listaProdutos() throws IOException {
        listaProd();
    }

    public static void editarProduto() throws IOException {
        listaProd();
    }

    public static void deletarProduto() throws IOException {
        listaProd();
        System.out.println("");
        System.out.println("Digite o codigo do produto a ser deletado");
        String ler = System.console().readLine();
        deleteProd(ler);
    }

    public static int montaMenu() {

        try {

            System.out.println("O que deseja fazer?");
            System.out.println("Digite:" + "\n1) Cadastrar produto" + "\n2) Ver produto" + "\n3) Editar produto"
                    + "\n4) Deletar produto" + "\n5) Sair");

            String ler = System.console().readLine();

            return Integer.parseInt(ler.intern());

        } catch (Exception e) {

            System.out.println("┗( T﹏T )┛ Ocorreu um erro ao exibir o menu: " + e);
            System.out.println("Digite apenas números!");
            return 5;
        }

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
                System.out.println("Digite apenas valores validos");
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
                if (linha != null) {
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
        while (true) {
            linha = buffRead.readLine();
            if (linha != null) {
                var informações = linha.split(";");
                var registro = "Cod : "+ informações[0]+ " Data de reg  : " + informações[1]+ ", Local : "+ informações[2]+ ", Tipo : " + informações[3]+
                ", Marca : "+ informações[4]+ ", Caracteristica : " + informações[5]+ ", Tamanho : "+ informações[6]+ ", Cor : " + informações[7] + 
                ", Valor Etiq : "+ informações[8]+ ", valor Margem : " + informações[9]+ ", Preço sugerido : "+ informações[10];
                System.out.println(registro);
            } 
            else
                break;
        }
        buffRead.close();
    }

    public static void setProduto(Produto prod) throws IOException {
        var path = "Registros.txt";

        FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        // cria o arquivo txt separando as informações do produto por "|"
        String produtoString = Integer.toHexString(prod.getCodigo()) + ";" + prod.getDataEntrada() + ";"
                + prod.getLocal() + ";" + prod.getTipo() + ";" + prod.getMarca() + ";" + prod.getCaracteristicas() + ";"
                + prod.getTamanho() + ";" + prod.getCor() + ";" + Double.toString(prod.getValorEtiqueta()) + ";"
                + Double.toString(prod.getValorMargem()) + ";" + Double.toString(prod.getPrecoSugerido());

                out.println(produtoString);
                out.close();
    }

    public static void deleteProd(String codigo) throws IOException {
        String path = "Registros.txt";
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        String resto="";
        while (true) {
            linha = buffRead.readLine();
            if (linha != null) {
                var informações = linha.split(";");
                if(informações[0].equals(codigo.intern()))
                    continue;
                else
                    resto += linha;
                if(!informações[0].equals(getUltimoCodigo()))
                    resto += "\n";
            } 
            else
                break;
        }
        FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.println(resto);
        out.close();
        buffRead.close();
        limpaTela();
    }
}
