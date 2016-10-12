package com.betha.nutri.dao;

import com.betha.nutri.model.Alimento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDao {

    public Alimento inserir(Alimento alimento) throws Exception {
        try {
            PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.alimentos(descricao) VALUES (?);");
            paramStm.setString(1, alimento.getDescricao());
            paramStm.execute();
            return alimento;
        } catch (SQLException ex) {
            throw new Exception("Falha ao inserir o registro", ex);
        }
    }

    public Alimento atualizar(Alimento alimento) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.alimentos SET descricao=? WHERE id=?;");

            stm.setString(1, alimento.getDescricao());
            stm.setLong(2, alimento.getId());
            stm.execute();

            return alimento;
        } catch (SQLException ex) {
            throw new Exception("Falha ao alterar o registro", ex);
        }

    }

    public void excluir(Alimento alimento) throws Exception {
        excluir(alimento.getId());
    }

    public void excluir(Long id) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("DELETE FROM public.usuarios WHERE id=?;");
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

    private Alimento lerRegistro(ResultSet rs) throws SQLException {
        Alimento a = new Alimento();
        a.setId(rs.getLong("id"));
        a.setDescricao(rs.getString("descricao"));
        return a;
    }
}
