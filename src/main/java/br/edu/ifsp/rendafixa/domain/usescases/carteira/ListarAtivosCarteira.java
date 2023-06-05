package br.edu.ifsp.rendafixa.domain.usescases.carteira;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListarAtivosCarteira {

    private CarteiraDAO carteiraDAO;

    public ListarAtivosCarteira(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public void listarAtivosNaCarteira(Carteira carteira) {
        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            if(!ativos.isEmpty()){
                for (Ativo ativo : ativos) {
                    System.out.println("Ativo: " + ativo.getNome());
                    System.out.println("Categoria: " + ativo.getCategoriaAtivo());
                    LocalDate vencimento = ativo.getDataVencimento();
                    String liquidez = ativo.isLiquidezDiaria() ? "Liquidez Diária" : vencimento.format(formatter);
                    System.out.println("Data de vencimento: " + liquidez);

                    List<ItemAtivo> aplicacoes = ativo.getItensAtivo();
                    if(!aplicacoes.isEmpty()){
                        for (int i = 0; i < aplicacoes.size(); i++) {
                            System.out.println("    Data da aplicação: " + aplicacoes.get(i).getDataDaCompra().format(formatter));
                            System.out.println("    Valor aplicado: " + aplicacoes.get(i).getValorDaCompra());
                        }
                    }else{
                        System.out.print("Sem aplicações!");
                    }
                }
            }else
                System.out.println("Carteira sem ativos!");
        }
    }

}
