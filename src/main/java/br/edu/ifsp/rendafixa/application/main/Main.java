package br.edu.ifsp.rendafixa.application.main;

import br.edu.ifsp.rendafixa.application.repository.*;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
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
import br.edu.ifsp.rendafixa.domain.usescases.indexadores.*;
import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.ConsultarItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.InserirItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.ItemAtivoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.RemoverItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.AtualizarEntidadePortadora;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.CadastrarEntidadePortadora;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.PortadoraDAO;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.RemoverEntidadePortadora;
import br.edu.ifsp.rendafixa.domain.usescases.transacao.ConsultarTransacao;
import br.edu.ifsp.rendafixa.domain.usescases.transacao.RegistrarTransacao;
import br.edu.ifsp.rendafixa.domain.usescases.transacao.TransacaoDAO;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    //Ativo
    private static AtualizarAtivo atualizarAtivo;
    private static CadastrarAtivo cadastrarAtivo;
    private static ConsultarAtivo consultarAtivo;
    private static RemoverAtivo removerAtivo;

    //Item Ativo
    private static InserirItemAtivo inserirItemAtivo;
    private static RemoverItemAtivo removerItemAtivo;
    private static ConsultarItemAtivo consultarItemAtivo;

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
    private static CadastrarIndexador cadastrarIndexador;
    private static RemoverIndexador removerIndexador;

    //Carteira
    private static CriarCarteira criarCarteira;
    private static ApagarCarteira apagarCarteira;
    private static IncluirAtivoCarteira incluirAtivoCarteira;
    private static RemoverAtivoCarteira removerAtivoCarteira;
    private static ComprarAtivo comprarAtivo;
    private static ResgatarAtivo resgatarAtivo;
    private static ConsultarAtivoCarteira consultarAtivoCarteira;
    private static CalcularTotalInvestido calcularTotalInvestido;
    private static CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo;
    private static VisualizarComposicaoCarteira visualizarComposicaoCarteira;
    private static CalcularRendimentoAtivo calcularRendimentoAtivo;
    private static CalcularRendimentoMesAMes calcularRendimentoMesAMes;
    private static ListarAtivosCarteira listarAtivosCarteira;
    private static ResgatarAtivosVencidos resgatarAtivosVencidos;


    //Transacao
    private static RegistrarTransacao registrarTransacao;
    private static ConsultarTransacao consultarTransacao;

    public static void main(String[] args) {
        configureInjection();
        LocalDate data1 = LocalDate.now();
        LocalDate data2 = LocalDate.now().minusDays(1);
        LocalDate data3 = LocalDate.now().minusDays(2);

        Emissora emissoraInter = new Emissora(1,"Inter", "Banco Inter SA", "INTER");
        cadastrarEntidadeEmissora.insert(emissoraInter);
        Emissora emissoraXP = new Emissora(2,"XP", "XP Co.", "XP");
        cadastrarEntidadeEmissora.insert(emissoraXP);

        Portadora portadoraInter = new Portadora(1,"Inter", "Banco Inter SA", "INTER");
        cadastrarEntidadePortadora.insert(portadoraInter);
        Portadora portadoraB3 = new Portadora(2,"B3", "Bolsa do Brasil", "B3");
        cadastrarEntidadePortadora.insert(portadoraB3);

        Indexador ipca = new Indexador(1, SiglaIndexador.IPCA,"IPCA",0.61);
        cadastrarIndexador.insert(ipca);
        Indexador cdi = new Indexador(2, SiglaIndexador.SELIC,"Selic",13.75);
        cadastrarIndexador.insert(cdi);
        Indexador di = new Indexador(3,SiglaIndexador.DI,"DI",90.0);
        cadastrarIndexador.insert(di);

        //Ativo pré-fixado
        LocalDate vencimento1 = LocalDate.parse("2025-03-31");
        Ativo ativo1 = new Ativo(1,"CRI INTER URBA", vencimento1, CategoriaAtivo.CDI,emissoraInter,portadoraInter, CategoriaRentabilidade.PRE_FIXADO,0.5);
        cadastrarAtivo.insert(ativo1);
        //Ativo pós-fixado
        Ativo ativo2 = new Ativo(2,"CDB AGRO SAO JOSE", vencimento1, CategoriaAtivo.CDB,emissoraXP,portadoraB3, cdi, CategoriaRentabilidade.POS_FIXADO,95.0,0.8);
        cadastrarAtivo.insert(ativo2);
        //Ativo com liquidez diária
        Ativo ativo3 = new Ativo(3,"CDB LD",true,CategoriaAtivo.CDB,emissoraXP,portadoraB3,CategoriaRentabilidade.PRE_FIXADO,1.0);
        cadastrarAtivo.insert(ativo3);

        LocalDate dataIni = LocalDate.parse("2023-05-29");
        LocalDate dataFin = LocalDate.parse("2023-09-29");

        List<Ativo> ativos = new ArrayList<>();
        Carteira carteira1 = new Carteira(ativos);
        incluirAtivoCarteira.incluirAtivoCarteira(carteira1,ativo1);
        incluirAtivoCarteira.incluirAtivoCarteira(carteira1,ativo2);
        incluirAtivoCarteira.incluirAtivoCarteira(carteira1,ativo3);
        listarAtivosCarteira.listarAtivosNaCarteira(carteira1);
        comprarAtivo.comprarAtivo(carteira1,ativo1,1000.00,data2);
        comprarAtivo.comprarAtivo(carteira1,ativo1,5000.00,data2);
        comprarAtivo.comprarAtivo(carteira1,ativo2,7000.00,data3);
        comprarAtivo.comprarAtivo(carteira1,ativo1,8000.00,data3);

        listarAtivosCarteira.listarAtivosNaCarteira(carteira1);

        LocalDate dataFinal = LocalDate.parse("2023-09-05");
        incluirAtivoCarteira.incluirAtivoCarteira(carteira1,ativo1);
        removerAtivoCarteira.removerAtivoCarteira(carteira1,ativo3);
        removerAtivoCarteira.removerAtivoCarteira(carteira1,ativo1);

        double rendimentoAtivo1 = calcularRendimentoAtivo.calcularRendimentoAtivo(ativo1,dataFinal);
        System.out.print("Rendimento ativo 1: R$ "+ rendimentoAtivo1);

        double totalInvestidoAtivo = calcularTotalInvestidoPorAtivo.calcularTotalInvestidoPorAtivo(ativo1);
        System.out.println("Total investido ativo " + ativo1.getNome() + ": R$ " + totalInvestidoAtivo);

        double totalCarteira = calcularTotalInvestido.calcularTotalInvestido(carteira1);
        System.out.println("Total aplicado na carteira: R$ "+ totalCarteira);

        //resgatarAtivo.resgatarAtivo(carteira1,ativo1);
        double valorSaque = carteira1.getValorDisponivelSaque();
        System.out.print("Valor disponível para Saque - R$ "+ valorSaque);

        consultarAtivoCarteira.consultarAtivoNaCarteira(carteira1,ativo1);

        visualizarComposicaoCarteira.visualizarComposicaoCarteira(carteira1);



    }

    private static void configureInjection(){
        AtivoDAO ativoDAO = new MemoriaAtivoDAO();
        cadastrarAtivo = new CadastrarAtivo(ativoDAO);
        atualizarAtivo = new AtualizarAtivo(ativoDAO);
        removerAtivo = new RemoverAtivo(ativoDAO);
        consultarAtivo = new ConsultarAtivo(ativoDAO);

        ItemAtivoDAO itemAtivoDAO = new MemoryItemAtivoDAO();
        inserirItemAtivo = new InserirItemAtivo(itemAtivoDAO);
        removerItemAtivo = new RemoverItemAtivo(itemAtivoDAO);
        consultarItemAtivo = new ConsultarItemAtivo(itemAtivoDAO);

        IndexadoresDAO indexadoresDAO = new MemoriaIndexadoresDAO();
        cadastrarIndexador = new CadastrarIndexador(indexadoresDAO);
        consultarIndexador = new ConsultarIndexador(indexadoresDAO);
        atualizarIndexador = new AtualizarIndexador(indexadoresDAO);
        removerIndexador = new RemoverIndexador(indexadoresDAO);

        EmissoraDAO emissoraDAO = new MemoriaEmissoraDAO();
        cadastrarEntidadeEmissora = new CadastrarEntidadeEmissora(emissoraDAO);
        atualizarEntidadeEmissora = new AtualizarEntidadeEmissora(emissoraDAO);
        removerEntidadeEmissora = new RemoverEntidadeEmissora(emissoraDAO);

        PortadoraDAO portadoraDAO = new MemoriaPortadoraDAO();
        cadastrarEntidadePortadora = new CadastrarEntidadePortadora(portadoraDAO);
        atualizarEntidadePortadora = new AtualizarEntidadePortadora(portadoraDAO);
        removerEntidadePortadora = new RemoverEntidadePortadora(portadoraDAO);

        TransacaoDAO transacaoDAO = new MemoriaTransacaoDAO();
        registrarTransacao = new RegistrarTransacao(transacaoDAO);
        consultarTransacao = new ConsultarTransacao(transacaoDAO);

        CarteiraDAO carteiraDAO = new MemoriaCarteiraDAO();
        criarCarteira = new CriarCarteira(carteiraDAO);
        apagarCarteira = new ApagarCarteira(carteiraDAO);
        incluirAtivoCarteira = new IncluirAtivoCarteira(carteiraDAO,ativoDAO);
        removerAtivoCarteira = new RemoverAtivoCarteira(carteiraDAO,ativoDAO);
        listarAtivosCarteira = new ListarAtivosCarteira(carteiraDAO);
        comprarAtivo = new ComprarAtivo(carteiraDAO,ativoDAO,transacaoDAO,itemAtivoDAO);
        resgatarAtivo = new ResgatarAtivo(carteiraDAO,ativoDAO,transacaoDAO);
        consultarAtivoCarteira = new ConsultarAtivoCarteira(carteiraDAO, ativoDAO, calcularTotalInvestidoPorAtivo);

        calcularTotalInvestido = new CalcularTotalInvestido(carteiraDAO,ativoDAO, calcularTotalInvestidoPorAtivo);
        calcularTotalInvestidoPorAtivo = new CalcularTotalInvestidoPorAtivo(carteiraDAO,ativoDAO);
        calcularRendimentoAtivo = new CalcularRendimentoAtivo(carteiraDAO,ativoDAO);
        visualizarComposicaoCarteira = new VisualizarComposicaoCarteira(carteiraDAO,ativoDAO);
        calcularRendimentoMesAMes = new CalcularRendimentoMesAMes();

    }
}
