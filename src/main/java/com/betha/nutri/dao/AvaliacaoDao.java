package com.betha.nutri.dao;

import com.betha.nutri.model.Avaliacao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDao {

    public Avaliacao inserir(Avaliacao avaliacao) throws Exception {
        try {
            
            avaliacao.calcularImc();
            avaliacao.calcularTaxaBasal();
            
            PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.avaliacao(id_usuario, id_dieta, data, peso_atual, imc, taxa_basal)VALUES (?, ?, ?, ?, ?, ?);");
            paramStm.setLong(1, avaliacao.getId_usuario());
            paramStm.setLong(2, avaliacao.getId_dieta());
            paramStm.setDate(3, new Date(avaliacao.getData().getTime()));
            paramStm.setDouble(4, avaliacao.getPeso_atual());
            paramStm.setDouble(5, avaliacao.getImc());
            paramStm.setDouble(6, avaliacao.getTaxa_basal());
            paramStm.execute();

            // Recuperar o usuario com o ID
            return avaliacao;
        } catch (SQLException ex) {
            throw new Exception("Falha ao inserir o registro", ex);
        }
    }

    public Avaliacao atualizar(Avaliacao avaliacao) throws Exception {
        try {
            
            avaliacao.calcularImc();
            avaliacao.calcularTaxaBasal();
            
            PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.avaliacao SET id_usuario=?, id_dieta=?, data=?, peso_atual=?, imc=?, taxa_basal=? WHERE id=?;");

            stm.setLong(1, avaliacao.getId_usuario());
            stm.setLong(2, avaliacao.getId_dieta());
            stm.setDate(3, new Date(avaliacao.getData().getTime()));
            stm.setDouble(4, avaliacao.getPeso_atual());
            stm.setDouble(5, avaliacao.getImc());
            stm.setDouble(6, avaliacao.getTaxa_basal());
            stm.setLong(7, avaliacao.getId());
            stm.execute();

            return avaliacao;
        } catch (SQLException ex) {
            throw new Exception("Falha ao alterar o registro", ex);
        }

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

    private Avaliacao lerRegistro(ResultSet rs) throws SQLException {
        Avaliacao a = new Avaliacao();
        a.setId(rs.getLong("id"));
        a.setId_usuario(rs.getLong("id_usuario"));
        a.setId_dieta(rs.getLong("id_dieta"));
        a.setData(rs.getDate("Data"));
        a.setPeso_atual(rs.getDouble("peso_atual"));
        a.setImc(rs.getDouble("imc"));
        a.setTaxa_basal(rs.getDouble("taxa_basal"));
        return a;
    }
}
