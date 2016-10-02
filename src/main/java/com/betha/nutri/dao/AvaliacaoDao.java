package com.betha.nutri.dao;

import com.betha.nutri.model.Avaliacao;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AvaliacaoDao {

    public Avaliacao inserir(Avaliacao avaliacao) throws Exception {
        try {
            PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.avaliacao(id, id_usuario, id_dieta, data, peso_atual, imc, taxa_basal)VALUES (?, ?, ?, ?, ?, ?, ?);");
            paramStm.setLong(1, avaliacao.getId_usuario());
            paramStm.setLong(2, avaliacao.getId_dieta());
            paramStm.setDate(3, avaliacao.getData());
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
            PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.avaliacao SET id=?, id_usuario=?, id_dieta=?, data=?, peso_atual=?, imc=?, taxa_basal=? WHERE id=?;");

            stm.setLong(1, avaliacao.getId_usuario());
            stm.setLong(2, avaliacao.getId_dieta());
            stm.setDate(3, avaliacao.getData());
            stm.setDouble(4, avaliacao.getPeso_atual());
            stm.setDouble(5, avaliacao.getImc());
            stm.setDouble(6, avaliacao.getTaxa_basal());
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

}
