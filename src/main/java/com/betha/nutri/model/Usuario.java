
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
    private Integer idade;
    private Double peso;
    private Double altura;
    
    
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
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
        if (!Objects.equals(this.idade, other.idade)) {
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
       
}
