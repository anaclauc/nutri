package com.betha.nutri.dao;

import com.betha.nutri.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    public Usuario inserir(Usuario usuario) throws Exception {
        try {
            PreparedStatement paramStm = Conexao.get().getParamStm("INSERT INTO public.usuarios(nome, email, sexo, idade, peso, altura) VALUES (?, ?, ?, ?, ?, ?);");
            paramStm.setString(1, usuario.getNome());
            paramStm.setString(2, usuario.getEmail());
            paramStm.setString(3, usuario.getSexo());
            paramStm.setInt(4, usuario.getIdade());
            paramStm.setDouble(5, usuario.getPeso());
            paramStm.setDouble(6, usuario.getAltura());
            paramStm.execute();

            // Recuperar o usuario com o ID
            return usuario;
        } catch (SQLException ex) {
            throw new Exception("Falha ao inserir o registro", ex);
        }
    }

    public Usuario atualizar(Usuario usuario) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("UPDATE public.usuarios SET nome=?, email=?, sexo=?, idade=?, peso=?, altura=? WHERE id=?;");

            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getEmail());
            stm.setString(3, usuario.getSexo());
            stm.setInt(4, usuario.getIdade());
            stm.setDouble(5, usuario.getPeso());
            stm.setDouble(6, usuario.getAltura());
            stm.setLong(7, usuario.getId());
            stm.execute();

            return usuario;
        } catch (SQLException ex) {
            throw new Exception("Falha ao alterar o registro", ex);
        }

    }

    public void excluir(Usuario usuario) throws Exception {
        excluir(usuario.getId());
    }

    public void excluir(Long id) throws Exception {
        try {
            PreparedStatement stm = Conexao.get().getParamStm("DELETE FROM public.alimentos WHERE id=?;");
            stm.setLong(1, id);
            stm.execute();
        } catch (SQLException ex) {
            throw new Exception("Falha ao excluir o registro", ex);
        }
    }

    public List<Usuario> listarTodos() throws Exception {
        List<Usuario> usuario = new ArrayList<>();

        try {
            PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM usuario");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                usuario.add(lerRegistro(rs));
            }
        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar o registro", ex);
        }

        return usuario;
    }

    public Usuario buscar(Long id) throws Exception {
        if (id == null) {
            return null;
        } else {
            try {
                PreparedStatement stm = Conexao.get().getParamStm("SELECT * FROM usuarios WHERE id = ?");
                stm.setLong(1, id);
                ResultSet rs = stm.executeQuery();
                rs.next();
                return lerRegistro(rs);
            } catch (SQLException ex) {
                throw new Exception("Erro ao buscar o registro", ex);
            }
        }
    }

    private Usuario lerRegistro(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setNome(rs.getString("nome"));
        u.setEmail(rs.getString("email"));
        u.setSexo(rs.getString("sexo"));
        u.setIdade(rs.getInt("idade"));
        u.setAltura(rs.getDouble("altura"));
        u.setPeso(rs.getDouble("peso"));
        return u;
    }
}
