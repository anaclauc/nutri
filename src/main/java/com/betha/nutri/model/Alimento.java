package com.betha.nutri.model;

import com.betha.nutri.utils.Utils;
import java.util.Map;
import java.util.Objects;

public class Alimento implements Parseable {

    private Long id;
    private String descricao;

    @Override
    public void parse(Map<String, String> values) {
        
        if(Utils.isNotEmpty(values.get("id"))) {
            this.id = Long.parseLong(values.get("id"));
        }
        
        this.descricao = values.get("descricao");
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.descricao);
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
        final Alimento other = (Alimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\", \"descricao\":\"%s\"}", id, descricao);

    }

}
