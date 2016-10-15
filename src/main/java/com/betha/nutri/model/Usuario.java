
package com.betha.nutri.model;

import com.betha.nutri.dao.UsuarioDao;
import com.betha.nutri.utils.Utils;
import java.util.Map;
import java.util.Objects;

public class Usuario implements Parseable{
    private Long id;
    private String nome;
    private String email;
    private String sexo;
    private int idade;
    private double peso;
    private double altura;
    
    
    @Override
    public void parse(Map<String, String> values) {
        this.id = Utils.parseLong(values.get("id"));
        this.nome = values.get("nome");
        this.email = values.get("email");
        this.altura = Utils.parseDouble(values.get("altura"));
        this.idade =  Utils.parseInt(values.get("idade"));
        this.sexo = values.get("sexo");
        this.peso = Utils.parseDouble(values.get("peso"));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.sexo);
        hash = 97 * hash + this.idade;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.peso) ^ (Double.doubleToLongBits(this.peso) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.altura) ^ (Double.doubleToLongBits(this.altura) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (this.idade != other.idade) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
        if (Double.doubleToLongBits(this.altura) != Double.doubleToLongBits(other.altura)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\", \"nome\":\"%s\", \"email\":\"%s\", \"idade\":\"%s\", \"altura\":\"%s\", \"peso\":\"%s\", \"sexo\":\"%s\" }", id, nome, email, idade, altura, peso, sexo);
    }
    
    public static void main(String[] args) throws Exception {
        
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Ana Claudia");
        usuario.setEmail("anclaudiccpatricio2@gmail.com");
        usuario.setAltura(1.67);
        usuario.setPeso(57);
        usuario.setIdade(26);
        usuario.setSexo("F");
        
        UsuarioDao dao = new UsuarioDao();
        dao.excluir(4L);
        
        System.out.println("USUARIO: " + dao.atualizar(usuario).toString());
        
    }
       
}
