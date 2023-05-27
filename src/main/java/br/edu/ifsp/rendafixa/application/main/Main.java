package br.edu.ifsp.rendafixa.application.main;

import br.edu.ifsp.rendafixa.application.repository.*;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.SiglaIndexador;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.*;
import br.edu.ifsp.rendafixa.domain.usescases.carteira.*;
import br.edu.ifsp.rendafixa.domain.usescases.emissora.AtualizarEntidadeEmissora;
import br.edu.ifsp.rendafixa.domain.usescases.emissora.CadastrarEntidadeEmissora;
import br.edu.ifsp.rendafixa.domain.usescases.emissora.EmissoraDAO;
import br.edu.ifsp.rendafixa.domain.usescases.emissora.RemoverEntidadeEmissora;
import br.edu.ifsp.rendafixa.domain.usescases.indexadores.AtualizarIndexador;
import br.edu.ifsp.rendafixa.domain.usescases.indexadores.ConsultarIndexador;
import br.edu.ifsp.rendafixa.domain.usescases.indexadores.IndexadoresDAO;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.AtualizarEntidadePortadora;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.CadastrarEntidadePortadora;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.PortadoraDAO;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.RemoverEntidadePortadora;

import java.time.LocalDate;

public class Main {
    //Ativo
    private static AtualizarAtivo atualizarAtivo;
    private static CadastrarAtivo cadastrarAtivo;
    private static ConsultarAtivo consultarAtivo;
    private static RemoverAtivo removerAtivo;

    //Emissora
    private static CadastrarEntidadeEmissora cadastrarEntidadeEmissora;
    private static AtualizarEntidadeEmissora atualizarEntidadeEmissora;
    private static RemoverEntidadeEmissora removerEntidadeEmissora;

    //Portadora
    private static CadastrarEntidadePortadora cadastrarEntidadePortadora;
    private static AtualizarEntidadePortadora atualizarEntidadePortadora;
    private static RemoverEntidadePortadora removerEntidadePortadora;

    //Indexadores
    private static ConsultarIndexador consultarIndexador;
    private static AtualizarIndexador atualizarIndexador;

    //Carteira
    private static CriarCarteira criarCarteira;
    private static ApagarCarteira apagarCarteira;
    private static ConsultarAtivoCarteira consultarAtivoCarteira;
    private static VisualizarTotalInvestido visualizarTotalInvestido;
    private static VisualizarComposicaoCarteira visualizarComposicaoCarteira;
    private static CalcularRendimentoAtivo calcularRendimentoAtivo;

    public static void main(String[] args) {
        configureInjection();
        LocalDate hoje = LocalDate.now();

        Emissora emissora1 = new Emissora(1,"Inter", "Banco Inter SA", "INTER");
        cadastrarEntidadeEmissora.insert(emissora1);
        Portadora portadora1 = new Portadora(1,"Inter", "Banco Inter SA", "INTER");
        cadastrarEntidadePortadora.insert(portadora1);
        Indexador ipca = new Indexador(1, SiglaIndexador.IPCA,"IPCA",0.61);
        Indexador cdi = new Indexador(2, SiglaIndexador.SELIC,"Selic",13.75);

        Ativo ativo1 = new Ativo(1,"CRI INTER URBA",false, LocalDate.parse("2023-03-31"), CategoriaAtivo.CDI,emissora1,portadora1, CategoriaRentabilidade.PRE_FIXADO,0.5);
        Ativo ativo2 = new Ativo(2,"CDB AGRO SAO JOSE",false, LocalDate.parse("2028-01-11"), CategoriaAtivo.CDB,emissora1,portadora1, cdi, CategoriaRentabilidade.POS_FIXADO,95.0,0.8);
        cadastrarAtivo.insert(ativo1);
        cadastrarAtivo.insert(ativo2);



    }

    private static void configureInjection(){
        AtivoDAO ativoDAO = new MemoriaAtivoDAO();
        cadastrarAtivo = new CadastrarAtivo(ativoDAO);
        atualizarAtivo = new AtualizarAtivo(ativoDAO);
        removerAtivo = new RemoverAtivo(ativoDAO);
        consultarAtivo = new ConsultarAtivo(ativoDAO);

        IndexadoresDAO indexadoresDAO = new MemoriaIndexadoresDAO();
        consultarIndexador = new ConsultarIndexador(indexadoresDAO);
        atualizarIndexador = new AtualizarIndexador(indexadoresDAO);

        EmissoraDAO emissoraDAO = new MemoriaEmissoraDAO();
        cadastrarEntidadeEmissora = new CadastrarEntidadeEmissora(emissoraDAO);
        atualizarEntidadeEmissora = new AtualizarEntidadeEmissora(emissoraDAO);
        removerEntidadeEmissora = new RemoverEntidadeEmissora(emissoraDAO);

        PortadoraDAO portadoraDAO = new MemoriaPortadoraDAO();
        cadastrarEntidadePortadora = new CadastrarEntidadePortadora(portadoraDAO);
        atualizarEntidadePortadora = new AtualizarEntidadePortadora(portadoraDAO);
        removerEntidadePortadora = new RemoverEntidadePortadora(portadoraDAO);

        CarteiraDAO carteiraDAO = new MemoriaCarteiraDAO();
        criarCarteira = new CriarCarteira(carteiraDAO);
        apagarCarteira = new ApagarCarteira(carteiraDAO);
       //consultarAtivoCarteira = new ConsultarAtivoCarteira(carteiraDAO);
        visualizarTotalInvestido = new VisualizarTotalInvestido();
        visualizarComposicaoCarteira = new VisualizarComposicaoCarteira();
        calcularRendimentoAtivo = new CalcularRendimentoAtivo();
    }
}
