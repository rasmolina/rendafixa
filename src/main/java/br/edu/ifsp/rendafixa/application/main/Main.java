package br.edu.ifsp.rendafixa.application.main;

import br.edu.ifsp.rendafixa.application.repository.inmemory.*;
import br.edu.ifsp.rendafixa.application.repository.sqlite.*;
import br.edu.ifsp.rendafixa.application.view.WindowLoader;
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
import br.edu.ifsp.rendafixa.domain.usescases.emissora.*;
import br.edu.ifsp.rendafixa.domain.usescases.indexadores.*;
import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.ConsultarItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.InserirItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.ItemAtivoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.RemoverItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.*;
import br.edu.ifsp.rendafixa.domain.usescases.transacao.ConsultarTransacao;
import br.edu.ifsp.rendafixa.domain.usescases.transacao.RegistrarTransacao;
import br.edu.ifsp.rendafixa.domain.usescases.transacao.TransacaoDAO;
import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.SiglaIndexador;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    //Ativo
    public static AtualizarAtivo atualizarAtivo;
    public static CadastrarAtivo cadastrarAtivo;
    public static ConsultarAtivo consultarAtivo;
    public static RemoverAtivo removerAtivo;

    //Item Ativo
    public static InserirItemAtivo inserirItemAtivo;
    public static RemoverItemAtivo removerItemAtivo;
    public static ConsultarItemAtivo consultarItemAtivo;

    //Emissora
    public static CadastrarEntidadeEmissora cadastrarEntidadeEmissora;
    public static AtualizarEntidadeEmissora atualizarEntidadeEmissora;
    public static RemoverEntidadeEmissora removerEntidadeEmissora;

    public static ConsultarEmissora consultarEmissora;

    //Portadora
    public static CadastrarEntidadePortadora cadastrarEntidadePortadora;
    public static AtualizarEntidadePortadora atualizarEntidadePortadora;
    public static RemoverEntidadePortadora removerEntidadePortadora;

    public static ConsultarPortadora consultarPortadora;

    //Indexadores
    public static ConsultarIndexador consultarIndexador;
    public static AtualizarIndexador atualizarIndexador;
    public static CadastrarIndexador cadastrarIndexador;
    public static RemoverIndexador removerIndexador;

    //Carteira
    public static CriarCarteira criarCarteira;
    public static ApagarCarteira apagarCarteira;
    public static IncluirAtivoCarteira incluirAtivoCarteira;
    public static RemoverAtivoCarteira removerAtivoCarteira;
    public static ComprarAtivo comprarAtivo;
    public static ResgatarAtivo resgatarAtivo;
    public static ConsultarAtivoCarteira consultarAtivoCarteira;
    public static CalcularTotalInvestido calcularTotalInvestido;
    public static CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo;
    public static VisualizarComposicaoCarteira visualizarComposicaoCarteira;
    public static CalcularRendimentoAtivo calcularRendimentoAtivo;
    public static CalcularRendimentoMesAMes calcularRendimentoMesAMes;
    public static ListarAtivosCarteira listarAtivosCarteira;
    public static ResgatarAtivosVencidos resgatarAtivosVencidos;
    public static ConsultarCarteira consultarCarteira;


    //Transacao
    private static RegistrarTransacao registrarTransacao;
    private static ConsultarTransacao consultarTransacao;

    public static void main(String[] args) {
        configureInjection();
        setupDataBase();

        BDTests();

        prepareBaseMock();
        WindowLoader.main(args);
    }

    private static void prepareBaseMock() {
        Emissora emissora1 = new Emissora(3,"Emissora 1","Descricao emissora 1","EM");
        Emissora emissora2 = new Emissora(2,"Emissora 2","Descricao emissora 2","ES");
        Emissora emissora3 = new Emissora(1,"Emissora 3","Descricao emissora 3","EMS");
        cadastrarEntidadeEmissora.insert(emissora1);
        cadastrarEntidadeEmissora.insert(emissora2);
        cadastrarEntidadeEmissora.insert(emissora3);

        Portadora portadora = new Portadora(1,"Portadora 1","Portadora descricao 1","P1");
        Portadora portadora2 = new Portadora(2,"Portadora 2","Portadora descricao 2","P2");
        Portadora portadora3 = new Portadora(3,"Portadora 3","Portadora descricao 3","P3");
        cadastrarEntidadePortadora.insert(portadora);
        cadastrarEntidadePortadora.insert(portadora2);
        cadastrarEntidadePortadora.insert(portadora3);

        Indexador indexador = new Indexador(1,SiglaIndexador.DI,"Indexador DI",230.00);
        Indexador indexador2 = new Indexador(2,SiglaIndexador.SELIC,"Indexador SELIC",300.00);
        Indexador indexador3 = new Indexador(3,SiglaIndexador.IPCA,"Indexador IPCA",500.00);
        Indexador indexador5 = new Indexador(5,SiglaIndexador.IGPM,"Indexador IGPM",200.00);
        cadastrarIndexador.insert(indexador);
        cadastrarIndexador.insert(indexador2);
        cadastrarIndexador.insert(indexador3);
        cadastrarIndexador.insert(indexador5);

        Carteira carteira1 = new Carteira(1);
        criarCarteira.insert(carteira1);

        String data = "13-11-2024";
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        LocalDate date = LocalDate.parse(data, parser);

        LocalDate dataHoje = LocalDate.now();

        Ativo ativo = new Ativo(1,"Ativo 1",false,date,CategoriaAtivo.DEB,emissora1,portadora,indexador,CategoriaRentabilidade.POS_FIXADO,0.10,0.8);
        cadastrarAtivo.insert(ativo);
        incluirAtivoCarteira.incluirAtivoCarteira(carteira1, ativo);

        LocalDate dataMaio = LocalDate.parse("2023-05-14");
        comprarAtivo.comprarAtivo(carteira1,ativo,500,dataMaio);
        comprarAtivo.comprarAtivo(carteira1,ativo,7000,dataMaio);



        Ativo ativo2 = new Ativo(2,"CDB AGRO SAO JOSE",false, date, CategoriaAtivo.CDB,emissora1,portadora, indexador, CategoriaRentabilidade.POS_FIXADO,95.0,0.8);
        cadastrarAtivo.insert(ativo2);
        incluirAtivoCarteira.incluirAtivoCarteira(carteira1, ativo2);
        //Ativo com liquidez diária
        Ativo ativo3 = new Ativo(3,"LCA LD INTER",true,dataHoje,CategoriaAtivo.LCA,emissora2,portadora,indexador2,CategoriaRentabilidade.PRE_FIXADO,0.0,0.4);
        cadastrarAtivo.insert(ativo3);
        incluirAtivoCarteira.incluirAtivoCarteira(carteira1, ativo3);
        comprarAtivo.comprarAtivo(carteira1,ativo2,5000,dataHoje);

        visualizarComposicaoCarteira.visualizarComposicaoCarteira(carteira1);

        double investidoAtivo = calcularTotalInvestidoPorAtivo.calcularTotalInvestidoPorAtivo(ativo);
        System.out.println("Total investido no ativo " + ativo.getNome()+": R$ "+investidoAtivo);

        double investidoAtivo2 = calcularTotalInvestidoPorAtivo.calcularTotalInvestidoPorAtivo(ativo2);
        System.out.println("Total investido no ativo " + ativo2.getNome()+": R$ "+investidoAtivo2);


        double totalInvestido = calcularTotalInvestido.calcularTotalInvestido(carteira1);
        System.out.println("Total investido na carteira: R$ " + totalInvestido);

        LocalDate dataFin = LocalDate.parse("2023-09-14");
        double rendimentoAtivo = calcularRendimentoAtivo.calcularRendimentoAtivo(ativo2,dataFin);
        System.out.println("Rendimento calculado do ativo "+ativo.getNome()+" até "+dataFin+": R$ "+rendimentoAtivo);

        consultarAtivoCarteira.consultarAtivoNaCarteira(carteira1,ativo2);

        resgatarAtivo.resgatarAtivo(carteira1,ativo2);

    }

    private static void setupDataBase() {
        DatabaseBuilder databaseBuilder = new DatabaseBuilder();
        databaseBuilder.builderDataBaseIfMissing();
    }

    private static void BDTests() {
        // ******** IMPLEMENTAÇÃO DOS TESTES COM O BANCO DE DADOS ********
        //LocalDate dataVencimento = LocalDate.parse("2025-05-10");
        //LocalDate dataHoje = LocalDate.now();

        //Resgata a carteira do BD
        //CarteiraDAO carteiraDAO = new SqliteCarteiraDAO();
        //Carteira carteira1 = new Carteira(1);

        //Resgata do BD uma emissora, uma portadora e um indexador
        //Emissora emissora1 = new Emissora(1);
        //Portadora portadora1 = new Portadora(1);
        //Indexador indexador1 = new Indexador(1);

        //Resgata do BD os ativos 1, 2, 3 que já foram inseridos na carteira
        //AtivoDAO ativoDAO = new SqliteAtivoDAO();
        //Ativo ativo1 = ativoDAO.findOne(1)
        //      .orElseThrow(() -> new EntityNotFoundException("Ativo não encontrado"));

        //Ativo ativo2 = new Ativo(2);
        // ativo3 = new Ativo(3);

        //Resgata o ativo 4 cadastrado no BD mas que ainda não foi inserido na carteira
        //Ativo ativo4 = new Ativo(4);

        //Ativo que não terá aplicações
        //Ativo ativo5 = new Ativo(5,"LCI XP",false,dataVencimento,CategoriaAtivo.LCI,emissora1,portadora1,indexador1,CategoriaRentabilidade.PRE_FIXADO,0.0,0.4);
        //Integer id5 = ativoDAO.create(ativo5); //Cadastra ativo 5 no BD
        //if(id5 != null)
        //System.out.println("Ativo "+ ativo5.getNome() + " cadastrado com sucesso no BD!");

        //Inclui um ativo já existente na carteira
        //carteiraDAO.incluirAtivoCarteira(carteira1,ativo2);

        //Tenta remover um ativo contendo aplicações
        //carteiraDAO.removerAtivoCarteira(carteira1,ativo2);
        //Tenta remover um ativo que ainda não foi inserido na carteira
        //carteiraDAO.removerAtivoCarteira(carteira1,ativo4);

        //Insere e remove na carteira o ativo 5 que não contém aplicações
        //carteiraDAO.incluirAtivoCarteira(carteira1,ativo5);
        //carteiraDAO.removerAtivoCarteira(carteira1,ativo5);
        //Remove o ativo 5 do BD
        //ativoDAO.delete(ativo5);

        //double investidoAtivo1 = carteiraDAO.CalcularTotalInvestidoPorAtivo(ativo2);
        //System.out.println("Total investido no ativo " + ativo2.getNome() + ": R$ " + investidoAtivo1);




        /*PortadoraDAO portadoraDAO = new MemoriaPortadoraDAO();//new SqlitePortadoraDAO();
        Portadora portadora = new Portadora(2,"Portadora6321511","Portadora descricao","P1");
        portadoraDAO.create(portadora);
        LocalDate data1 = LocalDate.now();
        LocalDate data2 = LocalDate.now().minusDays(1);
        LocalDate data3 = LocalDate.now().minusDays(2);*/


        /*
        EmissoraDAO emissoraDAO = new SqliteEmissoraDAO();
        Emissora emissora = new Emissora(3,"Emissora 1231231","Descricao emissora","EM");
        //cadastrarEntidadeEmissora.insert(emissora);
        //emissoraDAO.create(emissora);
        PortadoraDAO portadoraDAO = new SqlitePortadoraDAO();
        Portadora portadora = new Portadora(2,"Portadora6321511","Portadora descricao","P1");
        //portadoraDAO.create(portadora);
        IndexadoresDAO indexadoresDAO = new SqliteIndexadorDAO();
        Indexador indexador = new Indexador(4,SiglaIndexador.DI,"Indexador novo",230.00);
        indexadoresDAO.create(indexador);
        String data = "13-11-2024";
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd-MM-uuuu");
        LocalDate date = LocalDate.parse(data, parser);
        Ativo ativo = new Ativo(1,"Ativo 1",false,date,CategoriaAtivo.DEB,emissora,portadora,indexador,CategoriaRentabilidade.POS_FIXADO,0.10,0.8);
        //ativoDAO.buscaPorNome("Ativo 1");
        //removerAtivo.remove(3);
        //portadoraDAO.deleteByKey(3);

        //ativoDAO.create(ativo);

        LocalDate dataTransacao = LocalDate.now();
        LocalDate dataCompra = LocalDate.now();

        Transacao transacao = new Transacao(dataTransacao,dataTransacao,ativo,2000.00, TipoTransacao.COMPRA);
        registrarTransacao.insert(transacao);

        ItemAtivoDAO itemAtivoDAO = new SqliteItemAtivoDAO();
        ItemAtivo itemAtivo = new ItemAtivo(ativo,date,2000.00);
        inserirItemAtivo = new InserirItemAtivo(itemAtivoDAO);
        //inserirItemAtivo.insert(itemAtivo);

        CarteiraDAO carteiraDAO = new SqliteCarteiraDAO();
        Carteira carteira = new Carteira(1);
        criarCarteira = new CriarCarteira(carteiraDAO);

        String nomePortadora = "Portadora";
        Optional<Portadora> portadoraOptional = portadoraDAO.buscaPorNomePortadora(nomePortadora);

        if (portadoraOptional.isPresent()) {
            Portadora portadora1 = portadoraOptional.get();
            System.out.println("Portadora encontrada: " + portadora1.getNome());
        } else {
            System.out.println("Nenhuma portadora encontrada com o nome: " + nomePortadora);
        }

        List<Portadora> portadoras = portadoraDAO.findAll();

        if (!portadoras.isEmpty()) {
            System.out.println("Portadoras encontradas:");
            for (Portadora p : portadoras) {
                System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome());
            }
        } else {
            System.out.println("Nenhuma portadora encontrada.");
        }
        List<Emissora> emissoras = emissoraDAO.findAll();
        if (!emissoras.isEmpty()) {
            System.out.println("Emissoras encontradas:");
            for (Emissora e : emissoras) {
                System.out.println("ID: " + e.getId() + ", Nome: " + e.getNome());
            }
        } else {
            System.out.println("Nenhuma portadora encontrada.");
        }
        portadoraDAO.create(portadora);
        //incluirAtivoCarteira.incluirAtivoCarteira(carteira, ativo);
        //removerAtivo.remove(ativo);
        //carteiraDAO.incluirAtivoCarteira(carteira,ativo);
        //carteiraDAO.incluirAtivoCarteira(carteira,ativo);
        //carteiraDAO.removerAtivoCarteira(carteira,ativo);
        //carteiraDAO.consultarAtivoNaCarteira(1,ativo);
        Localate data_compra = LocalDate.now();
        //Ativo ativo2 = new Ativo(3,"Ativo 2",false,date,CategoriaAtivo.DEB,emissora,portadora,indexador,CategoriaRentabilidade.POS_FIXADO,0.10,0.8);
        //carteiraDAO.comprarAtivo(carteira,ativo2,2000.00,data_compra);
        //ativoDAO.create(ativo2);
        //atualizarAtivo.update(ativo);
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
        removerEntidadeEmissora.remove(1);
        /*
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
        System.out.rintln("\n=======================");
        totalCarteira = calcularTotalInvestido.calcularTotalInvestido(carteira1);
        System.out.println("Total aplicado na carteira: R$ "+ totalCarteira);
        totalInvestidoAtivo = calcularTotalInvestidoPorAtivo.calcularTotalInvestidoPorAtivo(ativo3);
        System.out.println("Total investido ativo " + ativo3.getNome() + ": R$ " + totalInvestidoAtivo);
        visualizarComposicaoCarteira.visualizarComposicaoCarteira(carteira1);
        System.out.println("\n=======================\n");
        consultarTransacao.findAll().stream().forEach(transacao -> System.out.println(transacao));

        consultarTransacao.findAll().stream().forEach(transacao -> System.out.println(transacao)); */
    }

    private static void configureInjection(){
        AtivoDAO ativoDAO = new MemoriaAtivoDAO();//MemoriaAtivoDAO();
        cadastrarAtivo = new CadastrarAtivo(ativoDAO);
        atualizarAtivo = new AtualizarAtivo(ativoDAO);
        removerAtivo = new RemoverAtivo(ativoDAO);
        consultarAtivo = new ConsultarAtivo(ativoDAO);

        ItemAtivoDAO itemAtivoDAO = new MemoryItemAtivoDAO();//new SqliteItemAtivoDAO();
        inserirItemAtivo = new InserirItemAtivo(itemAtivoDAO);
        removerItemAtivo = new RemoverItemAtivo(itemAtivoDAO);
        consultarItemAtivo = new ConsultarItemAtivo(itemAtivoDAO);

        IndexadoresDAO indexadoresDAO = new MemoriaIndexadoresDAO();//new SqliteIndexadorDAO();
        cadastrarIndexador = new CadastrarIndexador(indexadoresDAO);
        consultarIndexador = new ConsultarIndexador(indexadoresDAO);
        atualizarIndexador = new AtualizarIndexador(indexadoresDAO);
        removerIndexador = new RemoverIndexador(indexadoresDAO);

        EmissoraDAO emissoraDAO = new MemoriaEmissoraDAO();//new SqliteEmissoraDAO();
        cadastrarEntidadeEmissora = new CadastrarEntidadeEmissora(emissoraDAO);
        atualizarEntidadeEmissora = new AtualizarEntidadeEmissora(emissoraDAO);
        removerEntidadeEmissora = new RemoverEntidadeEmissora(emissoraDAO);
        consultarEmissora = new ConsultarEmissora(emissoraDAO);

        PortadoraDAO portadoraDAO = new MemoriaPortadoraDAO();//new SqlitePortadoraDAO();
        cadastrarEntidadePortadora = new CadastrarEntidadePortadora(portadoraDAO);
        atualizarEntidadePortadora = new AtualizarEntidadePortadora(portadoraDAO);
        removerEntidadePortadora = new RemoverEntidadePortadora(portadoraDAO);
        consultarPortadora = new ConsultarPortadora(portadoraDAO);

        TransacaoDAO transacaoDAO = new MemoriaTransacaoDAO();//new SqliteTransacaoDAO();
        registrarTransacao = new RegistrarTransacao(transacaoDAO);
        consultarTransacao = new ConsultarTransacao(transacaoDAO);

        CarteiraDAO carteiraDAO = new MemoriaCarteiraDAO();//new SqliteCarteiraDAO();
        criarCarteira = new CriarCarteira(carteiraDAO);
        apagarCarteira = new ApagarCarteira(carteiraDAO);
        incluirAtivoCarteira = new IncluirAtivoCarteira(carteiraDAO,ativoDAO);
        removerAtivoCarteira = new RemoverAtivoCarteira(carteiraDAO,ativoDAO);
        listarAtivosCarteira = new ListarAtivosCarteira(carteiraDAO);
        comprarAtivo = new ComprarAtivo(carteiraDAO,ativoDAO,transacaoDAO,itemAtivoDAO);
        resgatarAtivo = new ResgatarAtivo(carteiraDAO,ativoDAO,transacaoDAO,itemAtivoDAO);
        resgatarAtivosVencidos = new ResgatarAtivosVencidos(carteiraDAO);
        consultarAtivoCarteira = new ConsultarAtivoCarteira(carteiraDAO, ativoDAO, calcularTotalInvestidoPorAtivo);
        consultarCarteira = new ConsultarCarteira(carteiraDAO);

        calcularTotalInvestido = new CalcularTotalInvestido(carteiraDAO,ativoDAO, calcularTotalInvestidoPorAtivo);
        calcularTotalInvestidoPorAtivo = new CalcularTotalInvestidoPorAtivo(carteiraDAO,ativoDAO);
        calcularRendimentoAtivo = new CalcularRendimentoAtivo(carteiraDAO,ativoDAO);
        visualizarComposicaoCarteira = new VisualizarComposicaoCarteira(carteiraDAO,ativoDAO);
        calcularRendimentoMesAMes = new CalcularRendimentoMesAMes();

    }
}
