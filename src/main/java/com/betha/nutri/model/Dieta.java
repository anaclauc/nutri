package com.betha.nutri.model;

import java.util.Map;
import java.util.Objects;

public class Dieta implements Parseable {

    private Long id;
    private String nome;
    private String descricao;

    @Override
    public void parse(Map<String, String> values) {
        this.id = Long.parseLong(values.get("id"));
        this.nome = values.get("nome");
        this.descricao = values.get("descricao");
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.descricao);
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
        final Dieta other = (Dieta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\", \"nome\":\"%s\", \"descricao\":\"%s\" }", id, nome, descricao);
    }
}
