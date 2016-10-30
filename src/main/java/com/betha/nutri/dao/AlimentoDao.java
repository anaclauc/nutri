package com.betha.nutri.dao;

import com.betha.nutri.model.Alimento;
import com.betha.nutri.model.Dieta;
import com.betha.nutri.utils.Utils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDao {

    public void validar(Alimento alimento) {
        if (Utils.isEmpty(alimento.getDescricao())) {
            throw new IllegalArgumentException("A descricao do alimento nao pode ser nula");
        }
    }

    public Alimento inserir(Alimento alimento) throws IllegalArgumentException, SQLException {
        validar(alimento);
        PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.alimentos(descricao) VALUES (?) RETURNING id;");
        paramStm.setString(1, alimento.getDescricao());
        ResultSet rs = paramStm.executeQuery();

        if (rs != null) {
            rs.next();
            Long id = rs.getLong("id");
            alimento.setId(id);
        }

        return alimento;
    }

    public Alimento atualizar(Alimento alimento) throws IllegalArgumentException, SQLException {
        validar(alimento);
        PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.alimentos SET descricao=? WHERE id=?;");

        stm.setString(1, alimento.getDescricao());
        stm.setLong(2, alimento.getId());
        stm.execute();

        return alimento;
    }

    public void excluir(Alimento alimento) throws Exception {
        excluir(alimento.getId());
    }

    public void excluir(Long id) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("DELETE FROM alimentos WHERE id=?;");
            stm.setLong(1, id);
            stm.execute();
        } catch (SQLException ex) {
            throw new Exception("Falha ao excluir o registro", ex);
        }
    }

    public List<Alimento> listarTodos() throws Exception {
        List<Alimento> alimentos = new ArrayList<>();

        try {
            PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM alimentos");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                alimentos.add(lerRegistro(rs));
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o registro", ex);
        }

        return alimentos;
    }

    public Alimento buscar(Long id) throws Exception {
        if (id == null) {
            return null;
        } else {
            try {
                PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM alimentos WHERE id = ?");
                stm.setLong(1, id);
                ResultSet rs = stm.executeQuery();
                rs.next();
                return lerRegistro(rs);
            } catch (SQLException ex) {
                throw new Exception("Erro ao buscar o registro", ex);
            }
        }
    }
    
    public List<Alimento> buscar(String descricao) throws Exception {
        List<Alimento> alimentos = new ArrayList<>();

        try {
            PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM alimentos WHERE descricao LIKE '%" + descricao + "%'");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                alimentos.add(lerRegistro(rs));
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o registro", ex);
        }

        return alimentos;
    }

    private Alimento lerRegistro(ResultSet rs) throws SQLException {
        Alimento a = new Alimento();
        a.setId(rs.getLong("id"));
        a.setDescricao(rs.getString("descricao"));
        return a;
    }

}
