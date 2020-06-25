package POO_Restaurante;

import java.util.HashMap;
import java.util.HashSet;

public class Cozinha {
    private HashSet<Receita> receitas;
    private HashMap<String, Float> estoque;

    public Cozinha() {
        receitas = new HashSet<Receita>();
        estoque = new HashMap<String, Float>();
    }

    public void AdicionarNoEstoque (String ingrediente, Float quantidade) throws QuantidadeException {
        if (quantidade <= 0) {
            throw new QuantidadeException("Só pode adicionar no estoque quantidades positivas");
        }
        if (estoque.containsKey(ingrediente)) {
            Float quantidadeAnterior = estoque.get(ingrediente);
            quantidade += quantidadeAnterior;
        }
        estoque.put(ingrediente, quantidade);
    }

    public void AdicionarReceita (Receita receita) throws Exception {
        if (receitas.contains(receita)) {
            throw new Exception("Receita já adicionada");
        }
        receitas.add(receita);
    }
    
    void prepararReceita (Receita receita) throws Exception {
        System.out.println(("Preparando "+ receita.getNome()));
        if ( ! receitas.contains(receita)) {
            throw new Exception("A cozinha não sabe preparar essa receita");
        }

        //Preparar receita
        for (var ingrediente : receita.getIngredientes().entrySet()) {
            String ingredienteNome = ingrediente.getKey();
            Float quantidadeParaReceita = ingrediente.getValue();

            if ( ! estoque.containsKey(ingredienteNome)) {
                throw new Exception(ingredienteNome + " não existe no estoque para " + receita.getNome());
            }

            Float ingredienteQuantidadeEstoque = estoque.get(ingredienteNome);
            if ( ingredienteQuantidadeEstoque < quantidadeParaReceita ) {
                throw new Exception(ingredienteNome + " com quantidade insuficiente para " + receita.getNome());
            }

            //Removendo ingrediente do estoque para o preparo
            System.out.println("\tUtilizando " + quantidadeParaReceita.toString() + " de " + ingredienteNome);
            Float novaQuantidadeNoEstoque = ingredienteQuantidadeEstoque - quantidadeParaReceita;
            estoque.put(ingredienteNome, novaQuantidadeNoEstoque);
        }
        System.out.println("\t" + receita.getNome() + " pronto");
    }

    void limparFogao() {
        System.out.println("Limpando o fogão");
    }

    public HashSet<Receita> getReceitas() {
        return receitas;
    }

    public HashMap<String, Float> getEstoque() {
        return estoque;
    }
}