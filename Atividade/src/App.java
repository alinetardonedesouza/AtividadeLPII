import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

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

        System.out.println("Digite o tamanho do produto a ser cadastrado");
        prod.setTamanho(Integer.parseInt(System.console().readLine().intern()));

        System.out.println("Digite a cor do produto a ser cadastrado");
        prod.setCor(Integer.parseInt(System.console().readLine().intern()));

        System.out.println("Digite o valor da etiqueta do produto a ser cadastrado");
        prod.setValorEtiqueta(Double.parseDouble(System.console().readLine().intern()));

        System.out.println("Digite o preço sugerido do produto a ser cadastrado");
        prod.setPrecoSugerido(Double.parseDouble(System.console().readLine().intern()));

        System.out.println("Digite o valor pago do produto a ser cadastrado");
        prod.setValorPago(Double.parseDouble(System.console().readLine().intern()));

        prod.setValorMargem(prod.getValorPago());
        prod.setCodigo(getUltimoCodigo());

        // Escrever no aquivo texto
        setProduto(prod);
    }

    public static void listaProdutos() throws IOException {
        listaProd();
    }

    public static void editarProduto() {

    }

    public static void deletarProduto() {

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
            if (ultimaLinha == null)
                return "0";
            return ultimaLinha.split("|")[0];
        } catch (Exception e) {
            return "0";
        }
    }

    public static void listaProd() throws IOException {
        String path = "Registros.txt";
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        while (true) {
            if (linha != null) {
                System.out.println(linha);
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
        limpaTela();;
    }

    public static void setProduto(Produto prod) throws IOException {
        var path = "Registros.txt";

        FileWriter fw = new FileWriter(path, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        // cria o arquivo txt separando as informações do produto por "|"
        String produtoString = Integer.toHexString(prod.getCodigo()) + "|" + prod.getDataEntrada() + "|"
                + prod.getLocal() + "|" + prod.getTipo() + "|" + prod.getMarca() + "|" + prod.getCaracteristicas() + "|"
                + prod.getTamanho() + "|" + prod.getCor() + "|" + Double.toString(prod.getValorEtiqueta()) + "|"
                + Double.toString(prod.getValorMargem()) + "|" + Double.toString(prod.getPrecoSugerido());

                out.println(produtoString);
                out.close();
    }
}
