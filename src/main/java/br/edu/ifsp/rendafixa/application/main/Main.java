package br.edu.ifsp.rendafixa.application.main;

import br.edu.ifsp.rendafixa.application.repository.inmemory.*;
import br.edu.ifsp.rendafixa.application.repository.sqlite.*;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.SiglaIndexador;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.entities.transacao.TipoTransacao;
import br.edu.ifsp.rendafixa.domain.entities.transacao.Transacao;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        setupDataBase();
        LocalDate data1 = LocalDate.now();
        LocalDate data2 = LocalDate.now().minusDays(1);
        LocalDate data3 = LocalDate.now().minusDays(2);



        EmissoraDAO emissoraDAO = new SqliteEmissoraDAO();
        Emissora emissora = new Emissora(3,"Emissora 1231231","Descricao emissora","EM");
        cadastrarEntidadeEmissora = new CadastrarEntidadeEmissora(emissoraDAO);
        //cadastrarEntidadeEmissora.insert(emissora);
        //emissoraDAO.create(emissora);
        PortadoraDAO portadoraDAO = new SqlitePortadoraDAO();
        Portadora portadora = new Portadora(2,"Portadora6321511","Portadora descricao","P1");
        cadastrarEntidadePortadora = new CadastrarEntidadePortadora(portadoraDAO);
        //cadastrarEntidadePortadora.insert(portadora);
        //portadoraDAO.create(portadora);
        IndexadoresDAO indexadoresDAO = new SqliteIndexadorDAO();
        Indexador indexador = new Indexador(1,SiglaIndexador.IPCA,"Indexador1gf",200.00);
        cadastrarIndexador = new CadastrarIndexador(indexadoresDAO);
        //cadastrarIndexador.insert(indexador);

        String data = "13-11-2024";

        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        LocalDate date = LocalDate.parse(data, parser);


        AtivoDAO ativoDAO = new SqliteAtivoDAO();
        Ativo ativo = new Ativo(1,"Ativo 1",false,date,CategoriaAtivo.DEB,emissora,portadora,indexador,CategoriaRentabilidade.POS_FIXADO,0.10,0.8);
        //ativoDAO.buscaPorNome("Ativo 1");
        //removerAtivo.remove(3);
        //portadoraDAO.deleteByKey(3);

        //ativoDAO.create(ativo);

        LocalDate dataTransacao = LocalDate.now();
        LocalDate dataCompra = LocalDate.now();

        TransacaoDAO transacaoDAO = new SqliteTransacaoDAO();
        Transacao transacao = new Transacao(dataTransacao,dataTransacao,ativo,2000.00, TipoTransacao.COMPRA);
        registrarTransacao = new RegistrarTransacao(transacaoDAO);
        //registrarTransacao.insert(transacao);

        ItemAtivoDAO itemAtivoDAO = new SqliteItemAtivoDAO();
        ItemAtivo itemAtivo = new ItemAtivo(ativo,date,2000.00);
        inserirItemAtivo = new InserirItemAtivo(itemAtivoDAO);
        //inserirItemAtivo.insert(itemAtivo);

        CarteiraDAO carteiraDAO = new SqliteCarteiraDAO();
        Carteira carteira = new Carteira(1);
        criarCarteira = new CriarCarteira(carteiraDAO);

        //carteiraDAO.incluirAtivoCarteira(carteira,ativo);

        double total = carteiraDAO.CalcularTotalInvestidoPorAtivo(ativo);
        System.out.println("O total investido é:" + total);
       

        //carteiraDAO.comprarAtivo(carteira,ativo,2000.00,dataCompra);
        //carteiraDAO.comprarAtivo(carteira,ativo,5000.00,dataCompra);
        //carteiraDAO.incluirAtivoCarteira(carteira,ativo);
        //carteiraDAO.comprarAtivo(carteira,ativo,2000.00,dataCompra);
        //criarCarteira.insert(carteira);

        //carteiraDAO.incluirAtivoCarteira(carteira,ativo);
        //visualizarComposicaoCarteira.visualizarComposicaoCarteira(carteira);
        //incluirAtivoCarteira.incluirAtivoCarteira(carteira,ativo);

        //carteiraDAO.incluirAtivoCarteira(carteira,ativo);
        //carteiraDAO.incluirAtivoCarteira();


        IncluirAtivoCarteira incluirAtivoCarteira = new IncluirAtivoCarteira(carteiraDAO, ativoDAO);


        //incluirAtivoCarteira.incluirAtivoCarteira(carteira, ativo);

        //removerAtivo.remove(ativo);

        //carteiraDAO.incluirAtivoCarteira(carteira,ativo);
        //carteiraDAO.incluirAtivoCarteira(carteira,ativo);
        //carteiraDAO.removerAtivoCarteira(carteira,ativo);
        //carteiraDAO.consultarAtivoNaCarteira(1,ativo);

        LocalDate data_compra = LocalDate.now();

        Ativo ativo2 = new Ativo(3,"Ativo 2",false,date,CategoriaAtivo.DEB,emissora,portadora,indexador,CategoriaRentabilidade.POS_FIXADO,0.10,0.8);
        //carteiraDAO.comprarAtivo(carteira,ativo2,2000.00,data_compra);



        //atualizarAtivo.update(ativo);
/*
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
*/
        //Ativo pré-fixado
        /*
        Carteira carteira = new Carteira();
        CarteiraDAO carteiraDAO = new SqliteCarteiraDAO();
        carteiraDAO.create(carteira)
        carteiraDAO.incluirAtivoCarteira(carteira,ativo1);

        /*
        //Ativo pós-fixado
        Ativo ativo2 = new Ativo(2,"CDB AGRO SAO JOSE", vencimento1, CategoriaAtivo.CDB,emissoraXP,portadoraB3, cdi, CategoriaRentabilidade.POS_FIXADO,95.0,0.8);
        cadastrarAtivo.insert(ativo2);
        //Ativo com liquidez diária
        Ativo ativo3 = new Ativo(3,"LCA LD INTER",true,CategoriaAtivo.LCA,emissoraXP,portadoraB3,CategoriaRentabilidade.PRE_FIXADO,1.0);
        cadastrarAtivo.insert(ativo3);

        /*
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

        incluirAtivoCarteira.incluirAtivoCarteira(carteira1, ativo3);

        comprarAtivo.comprarAtivo(carteira1,ativo3,500,data2);

        resgatarAtivo.resgatarAtivo(carteira1,ativo3);
        double valorSaque = carteira1.getValorDisponivelSaque();
        System.out.print("Valor disponível para Saque - R$ "+ valorSaque);

        System.out.println("\n=======================");
        totalCarteira = calcularTotalInvestido.calcularTotalInvestido(carteira1);
        System.out.println("Total aplicado na carteira: R$ "+ totalCarteira);

        totalInvestidoAtivo = calcularTotalInvestidoPorAtivo.calcularTotalInvestidoPorAtivo(ativo3);
        System.out.println("Total investido ativo " + ativo3.getNome() + ": R$ " + totalInvestidoAtivo);

        visualizarComposicaoCarteira.visualizarComposicaoCarteira(carteira1);

        System.out.println("\n=======================\n");
        consultarTransacao.findAll().stream().forEach(transacao -> System.out.println(transacao));
*/


    }

    private static void setupDataBase() {
        DatabaseBuilder databaseBuilder = new DatabaseBuilder();
        databaseBuilder.builderDataBaseIfMissing();
    }

    private static void configureInjection(){
        AtivoDAO ativoDAO = new SqliteAtivoDAO();
        cadastrarAtivo = new CadastrarAtivo(ativoDAO);
        atualizarAtivo = new AtualizarAtivo(ativoDAO);
        removerAtivo = new RemoverAtivo(ativoDAO);
        consultarAtivo = new ConsultarAtivo(ativoDAO);

        ItemAtivoDAO itemAtivoDAO = new SqliteItemAtivoDAO();
        inserirItemAtivo = new InserirItemAtivo(itemAtivoDAO);
        removerItemAtivo = new RemoverItemAtivo(itemAtivoDAO);
        consultarItemAtivo = new ConsultarItemAtivo(itemAtivoDAO);

        IndexadoresDAO indexadoresDAO = new SqliteIndexadorDAO();
        cadastrarIndexador = new CadastrarIndexador(indexadoresDAO);
        consultarIndexador = new ConsultarIndexador(indexadoresDAO);
        atualizarIndexador = new AtualizarIndexador(indexadoresDAO);
        removerIndexador = new RemoverIndexador(indexadoresDAO);

        EmissoraDAO emissoraDAO = new SqliteEmissoraDAO();
        cadastrarEntidadeEmissora = new CadastrarEntidadeEmissora(emissoraDAO);
        atualizarEntidadeEmissora = new AtualizarEntidadeEmissora(emissoraDAO);
        removerEntidadeEmissora = new RemoverEntidadeEmissora(emissoraDAO);

        PortadoraDAO portadoraDAO = new SqlitePortadoraDAO();
        cadastrarEntidadePortadora = new CadastrarEntidadePortadora(portadoraDAO);
        atualizarEntidadePortadora = new AtualizarEntidadePortadora(portadoraDAO);
        removerEntidadePortadora = new RemoverEntidadePortadora(portadoraDAO);

        TransacaoDAO transacaoDAO = new SqliteTransacaoDAO();
        registrarTransacao = new RegistrarTransacao(transacaoDAO);
        consultarTransacao = new ConsultarTransacao(transacaoDAO);

        CarteiraDAO carteiraDAO = new SqliteCarteiraDAO();
        criarCarteira = new CriarCarteira(carteiraDAO);
        apagarCarteira = new ApagarCarteira(carteiraDAO);
        incluirAtivoCarteira = new IncluirAtivoCarteira(carteiraDAO,ativoDAO);
        removerAtivoCarteira = new RemoverAtivoCarteira(carteiraDAO,ativoDAO);
        listarAtivosCarteira = new ListarAtivosCarteira(carteiraDAO);
        comprarAtivo = new ComprarAtivo(carteiraDAO,ativoDAO,transacaoDAO,itemAtivoDAO);
        resgatarAtivo = new ResgatarAtivo(carteiraDAO,ativoDAO,transacaoDAO,itemAtivoDAO);
        resgatarAtivosVencidos = new ResgatarAtivosVencidos(carteiraDAO);
        consultarAtivoCarteira = new ConsultarAtivoCarteira(carteiraDAO, ativoDAO, calcularTotalInvestidoPorAtivo);

        calcularTotalInvestido = new CalcularTotalInvestido(carteiraDAO,ativoDAO, calcularTotalInvestidoPorAtivo);
        calcularTotalInvestidoPorAtivo = new CalcularTotalInvestidoPorAtivo(carteiraDAO,ativoDAO);
        calcularRendimentoAtivo = new CalcularRendimentoAtivo(carteiraDAO,ativoDAO);
        visualizarComposicaoCarteira = new VisualizarComposicaoCarteira(carteiraDAO,ativoDAO);
        calcularRendimentoMesAMes = new CalcularRendimentoMesAMes();

    }
}
