package com.betha.nutri.dao;

import com.betha.nutri.model.Avaliacao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDao {

    public void validar(Avaliacao avaliacao) {
        if (avaliacao.getId_usuario() == null) {
            throw new IllegalArgumentException("O usuario nao pode ser nulo");
        } else if (avaliacao.getPeso_atual() == null) {
            throw new IllegalArgumentException("O peso nao pode ser nulo");
        }
    }

    public Avaliacao inserir(Avaliacao avaliacao) throws IllegalArgumentException, SQLException {
        validar(avaliacao);

        avaliacao.calcularImc();
        avaliacao.calcularTaxaBasal();

        PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.avaliacao(id_usuario, id_dieta, data, peso_atual, taxa_atividade, imc, taxa_basal)VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;");
        paramStm.setLong(1, avaliacao.getId_usuario());
        paramStm.setLong(2, avaliacao.getId_dieta());
        paramStm.setDate(3, new Date(avaliacao.getData().getTime()));
        paramStm.setDouble(4, avaliacao.getPeso_atual());
        paramStm.setDouble(5, avaliacao.getTaxa_atividade());
        paramStm.setDouble(6, avaliacao.getImc());
        paramStm.setDouble(7, avaliacao.getTaxa_basal());

        ResultSet rs = paramStm.executeQuery();

        if (rs != null) {
            rs.next();
            avaliacao.setId(rs.getLong("id"));
        }

        return avaliacao;
    }

    public Avaliacao atualizar(Avaliacao avaliacao) throws IllegalArgumentException, SQLException {
        validar(avaliacao);

        avaliacao.calcularImc();
        avaliacao.calcularTaxaBasal();

        PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.avaliacao SET id_usuario=?, id_dieta=?, data=?, peso_atual=?, taxa_atividade=?, imc=?, taxa_basal=? WHERE id=?;");

        stm.setLong(1, avaliacao.getId_usuario());
        stm.setLong(2, avaliacao.getId_dieta());
        stm.setDate(3, new Date(avaliacao.getData().getTime()));
        stm.setDouble(4, avaliacao.getPeso_atual());
        stm.setDouble(5, avaliacao.getTaxa_atividade());
        stm.setDouble(6, avaliacao.getImc());
        stm.setDouble(7, avaliacao.getTaxa_basal());
        stm.setLong(8, avaliacao.getId());
        stm.execute();

        return avaliacao;
    }

    public void excluir(Avaliacao avaliacao) throws Exception {
        excluir(avaliacao.getId());
    }

    public void excluir(Long id) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("DELETE FROM public.avaliacao WHERE id=?;");
            stm.setLong(1, id);
            stm.execute();
        } catch (SQLException ex) {
            throw new Exception("Falha ao excluir o registro", ex);
        }
    }

    public List<Avaliacao> listarTodos() throws Exception {
        List<Avaliacao> avaliacao = new ArrayList<>();

        try {
            PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM avaliacao");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                avaliacao.add(lerRegistro(rs));
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o registro", ex);
        }

        return avaliacao;
    }

    public Avaliacao buscar(Long id) throws Exception {
        if (id == null) {
            return null;
        } else {
            try {
                PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM avaliacao WHERE id = ?");
                stm.setLong(1, id);
                ResultSet rs = stm.executeQuery();
                rs.next();
                return lerRegistro(rs);
            } catch (SQLException ex) {
                throw new Exception("Erro ao buscar o registro", ex);
            }
        }
    }
    
    public List<Avaliacao> buscar(String nome) throws Exception {
        List<Avaliacao> avaliacoes = new ArrayList<>();

        try {
            PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM avaliacao JOIN usuarios ON (usuarios.id = avaliacao.id_usuario) WHERE usuarios.nome LIKE '%" + nome + "%'");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                avaliacoes.add(lerRegistro(rs));
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o registro", ex);
        }

        return avaliacoes;
    }

    private Avaliacao lerRegistro(ResultSet rs) throws SQLException {
        Avaliacao a = new Avaliacao();
        a.setId(rs.getLong("id"));
        a.setId_usuario(rs.getLong("id_usuario"));
        a.setId_dieta(rs.getLong("id_dieta"));
        a.setData(rs.getDate("Data"));
        a.setPeso_atual(rs.getDouble("peso_atual"));
        a.setTaxa_atividade(rs.getDouble("taxa_atividade"));
        a.setImc(rs.getDouble("imc"));
        a.setTaxa_basal(rs.getDouble("taxa_basal"));
        return a;
    }
}
