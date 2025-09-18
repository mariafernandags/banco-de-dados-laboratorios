package com.example.academia;

import com.example.academia.DAO.*;
import com.example.academia.model.*;
import com.example.academia.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Criando o EntityManager para gerenciar as operações com o banco de dados
        EntityManager manager = JPAUtil.getEntityManager();

        // Instanciando os DAOs para acessar os dados
        AlunoDAO alunoDao = new AlunoDAO(manager);
        TreinoDAO treinoDao = new TreinoDAO(manager);
        AlunoTreinoDAO alunoTreinoDao = new AlunoTreinoDAO(manager);
        TreinadorDAO treinadorDao = new TreinadorDAO(manager);
        ControleSistemaDAO sistemaDao = new ControleSistemaDAO(manager);

        // Buscando um aluno pelo ID e exibindo seu nome
        Aluno aluno = alunoDao.buscarPorId(2);
        System.out.println("Aluno: " + aluno.getNome());

        // Buscando um treino pelo ID e exibindo seu identificador
        Treino treino = treinoDao.buscarPorId(2);
        System.out.println("Treino: " + treino.getId());

        // Buscando um treinador pelo ID e exibindo seu nome
        Treinador treinador = treinadorDao.buscarPorId(2);
        System.out.println("Treinador: " + treinador.getNome());

        // Buscando uma relação AlunoTreino pela chave composta (alunoId + treinoId)
        AlunoTreinoId chave = new AlunoTreinoId(aluno.getId(), treino.getId());
        AlunoTreino relacao = alunoTreinoDao.buscarPorId(chave);
        System.out.println("Aluno: " + relacao.getAluno().getNome() + ", Treina: " + relacao.getTreino().getDescricao());

        // Buscando informações do sistema, como o total de matrículas canceladas
        ControleSistema controle = sistemaDao.buscarPorId(1);
        System.out.println("Total de matrículas canceladas: " + controle.getTotal_matriculas_canceladas());

        // =============================
        // EXERCÍCIO 1: Listar treinos do aluno com ID 1 usando duas abordagens
        // =============================

        // Primeira abordagem: buscando uma lista de Object[] com informações específicas
        List<Object[]> listaTreinos = alunoTreinoDao.buscarTreinosPorAlunoId(1);
        for (Object[] item : listaTreinos) {
            System.out.println("Treino: " + item[0] + ", Descrição: " + item[1] + ", Treinador: " + item[2]);
        }

        // Segunda abordagem: buscando uma lista de objetos AlunoTreino completos
        List<AlunoTreino> relacoes = alunoTreinoDao.buscarTreinosPoralunoid(1);
        for (AlunoTreino at : relacoes) {
            System.out.println("Treino: " + at.getTreino().getId() + ", Descrição: " + at.getTreino().getDescricao() +
                    ", Treinador: " + at.getTreino().getTreinador().getNome());
        }

        // =============================
        // EXERCÍCIO 2: Listar alunos de uma cidade específica
        // =============================

        // Buscando todos os alunos da cidade "Belo Horizonte"
        List<Aluno> alunosCidade = alunoDao.buscarAlunosPorCidade("Belo Horizonte");
        for (Aluno a : alunosCidade) {
            System.out.println("ID: " + a.getId() + ", Nome: " + a.getNome());
        }

        // Encerrando o EntityManager
        manager.close();
    }
}
