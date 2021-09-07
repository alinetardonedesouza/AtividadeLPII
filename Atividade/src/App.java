public class App {

    public static void limpaTela() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void cadastrarProduto(){

    }

    public static void listaProdutos(){
        
    }

    public static void editarProduto(){
        
    }

    public static void deletarProduto(){
        
    }

    public static int montaMenu() {

        try {
            
            System.out.println("O que deseja fazer?");
            System.out.println("Digite:" + 
                                "\n1) Cadastrar produto" + 
                                "\n2) Ver produto" + 
                                "\n3) Editar produto" + 
                                "\n4) Deletar produto" +
                                "\n5) Sair");
            
            String ler = System.console().readLine();
            
            return Integer.parseInt(ler.intern());

        } catch (Exception e) {

            System.out.println("┗( T﹏T )┛ Ocorreu um erro ao exibir o menu: " + e);
            System.out.println("Digite apenas números!");
            return 5;
        }

    }

    public static void selecionaOpcao(int opcao){

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

        } while (opcao <= 3);

        System.out.println("Bye o(*￣▽￣*)ブ ");
        
    }
}

