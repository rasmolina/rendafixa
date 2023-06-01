package br.edu.ifsp.rendafixa.domain.usescases.carteira;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListarAtivosCarteira {

    private CarteiraDAO carteiraDAO;

    public ListarAtivosCarteira(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public void listarAtivosNaCarteira(Integer idCarteira) {
        Carteira carteira = carteiraDAO.findOne(idCarteira)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado!"));

        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (Ativo ativo : ativos) {
                System.out.println("Ativo: " + ativo.getNome());
                System.out.println("Categoria: " + ativo.getCategoriaAtivo());
                LocalDate vencimento = ativo.getDataVencimento();
                String liquidez = ativo.isLiquidezDiaria() ? "Liquidez Diária" : vencimento.format(formatter);
                System.out.println("Data de vencimento: " + liquidez);

                List<LocalDate> datasCompra = ativo.getDataDaCompra();
                List<Double> valoresCompra = ativo.getValorTotalDaCompra();

                for (int i = 0; i < datasCompra.size(); i++) {
                    System.out.println("    Data da compra: " + datasCompra.get(i).format(formatter));
                    System.out.println("    Valor investido: " + valoresCompra.get(i));
                }
            }
        }
    }

}
