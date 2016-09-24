
package com.betha.nutri.model;

import java.sql.Date;
import java.util.Map;
import java.util.Objects;

public class HistoricoPeso implements Parseable{
    private Long id;
    private Long idUsuario;
    private Date data;
    private Double peso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.idUsuario);
        hash = 19 * hash + Objects.hashCode(this.data);
        hash = 19 * hash + Objects.hashCode(this.peso);
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
        final HistoricoPeso other = (HistoricoPeso) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.peso, other.peso)) {
            return false;
        }
        return true;
    }

    @Override
    public void parse(Map<String, String> values) {
        this.id = Long.parseLong(values.get("id"));
        this.idUsuario = Long.parseLong("isUsuaio");
        this.peso = Double.parseDouble("peso");
        this.data = Date.valueOf("data");
    }

    @Override
    public String toString() {
         return String.format("{\"id\":\"%s\", \"idUsuario\":\"%s\", \"peso\":\"%s\", \"data\":\"%s\"}", id, idUsuario, peso, data);
    }
 
    
}

