package Views;

import java.io.IOException;
import static Controllers.Controller.listaProd;

public class View {
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
    public static void listaProdutos() throws IOException {
        listaProd();
    }
}
