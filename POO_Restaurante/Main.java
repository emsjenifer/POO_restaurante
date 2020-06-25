package POO_Restaurante;

public class Main {
    public static void main(String[] args) throws Exception {
        Cozinha cozinha = new Cozinha();

        //Criar receitas e adicionar os ingredientes
        Receita macarronada = new Receita ("Macarronada");
        try {
             macarronada.AdiconarIngrediente("Macarrão", 500.0f);
             macarronada.AdiconarIngrediente("Tomate", 4.0f);
        } catch (QuantidadeException ex) {
             System.out.println(ex.getMessage());
        }

        Receita doceDeLeite = new Receita("Doce de Leite");
        try {
             doceDeLeite.AdiconarIngrediente("Leite", 1000.0f);
             doceDeLeite.AdiconarIngrediente("Açucar", 500.0f);
        } catch (QuantidadeException ex) {
             System.out.println(ex.getMessage());
        }

        //Adicionar receitas na cozinha
        cozinha.AdicionarReceita(macarronada);
        cozinha.AdicionarReceita(doceDeLeite);

        //Adiconar ingredientes no estoque
        try {
             cozinha.AdicionarNoEstoque("Macarrão", 1000.0f);
             cozinha.AdicionarNoEstoque("Tomate", 15.0f);
             cozinha.AdicionarNoEstoque("Leite", 1000.0f);
             cozinha.AdicionarNoEstoque("Açucar", 2000.0f);
        } catch (QuantidadeException ex) {
            System.out.println(ex.getMessage());
        }

        //Preparação das receitas
        Receita pedidos[] = {macarronada, doceDeLeite, doceDeLeite, macarronada};
        for (Receita receita : pedidos) {
            try {
                 cozinha.prepararReceita(receita);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                cozinha.limparFogao();
            }
        }

    }
}