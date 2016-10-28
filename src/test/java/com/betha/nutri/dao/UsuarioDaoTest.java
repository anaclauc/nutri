package com.betha.nutri.dao;

import com.betha.nutri.model.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class UsuarioDaoTest {
    
    private UsuarioDao dao = new UsuarioDao();
    
    @Test
    public void usuarioComNomeNulo() {
        Usuario usuario = new Usuario();
        
        try {
            dao.validar(usuario);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("O nome do usuario nao pode ser nulo", ex.getMessage());
        }
        
    }
    
    @Test
    public void usuarioComEmailNulo() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Claudia");
        
        try {
            dao.validar(usuario);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("O email do usuario nao pode ser nulo", ex.getMessage());
        }
        
    }
    
    @Test
    public void usuarioComIdadeNula() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Claudia");
        usuario.setEmail("anaclaudiacpatricio@gmail.com");
        
        try {
            dao.validar(usuario);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("A idade deve ser superior a 0", ex.getMessage());
        }
        
    }
    
    @Test
    public void usuarioComIdadeZero() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Claudia");
        usuario.setEmail("anaclaudiacpatricio@gmail.com");
        usuario.setIdade(0);
        
        try {
            dao.validar(usuario);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("A idade deve ser superior a 0", ex.getMessage());
        }
        
    }
    
    @Test
    public void usuarioComPesoNulo() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Claudia");
        usuario.setEmail("anaclaudiacpatricio@gmail.com");
        usuario.setIdade(61);
        
        try {
            dao.validar(usuario);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("O peso deve ser superior a 0", ex.getMessage());
        }
        
    }
    
    @Test
    public void usuarioComPesoZero() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Claudia");
        usuario.setEmail("anaclaudiacpatricio@gmail.com");
        usuario.setIdade(26);
        usuario.setPeso(0d);
        
        try {
            dao.validar(usuario);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("O peso deve ser superior a 0", ex.getMessage());
        }
        
    }
    
    @Test
    public void usuarioComAlturaNula() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Claudia");
        usuario.setEmail("anaclaudiacpatricio@gmail.com");
        usuario.setIdade(26);
        usuario.setPeso(60d);
        
        try {
            dao.validar(usuario);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("A altura deve ser superior a 0", ex.getMessage());
        }
    }
    
    @Test
    public void usuarioComAlturaZero() {
        Usuario usuario = new Usuario();
        usuario.setNome("Ana Claudia");
        usuario.setEmail("anaclaudiacpatricio@gmail.com");
        usuario.setIdade(26);
        usuario.setPeso(60d);
        usuario.setAltura(0d);
        
        try {
            dao.validar(usuario);
        } catch (IllegalArgumentException ex) {
            Assert.assertEquals("A altura deve ser superior a 0", ex.getMessage());
        }
    }
}
