package com.betha.nutri.model;

import java.util.Map;
import java.util.Objects;

public class DietaAlimentos implements Parseable {

    private Long id;
    private Long id_dieta;
    private Long id_alimento;
    private double quantidade;
    private int tipo;

    @Override
    public void parse(Map<String, String> values) {
        this.id = Long.parseLong(values.get("id_dieta"));
        this.id_dieta = Long.parseLong(values.get("id_dieta"));
        this.id_alimento = Long.parseLong("id_alimento");
        this.quantidade = Double.parseDouble("quantidade");
        this.tipo = Integer.parseInt("tipo");

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_dieta() {
        return id_dieta;
    }

    public void setId_dieta(Long id_dieta) {
        this.id_dieta = id_dieta;
    }

    public Long getId_alimento() {
        return id_alimento;
    }

    public void setId_alimento(Long id_alimento) {
        this.id_alimento = id_alimento;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.id_dieta);
        hash = 11 * hash + Objects.hashCode(this.id_alimento);
        hash = 11 * hash + (int) (Double.doubleToLongBits(this.quantidade) ^ (Double.doubleToLongBits(this.quantidade) >>> 32));
        hash = 11 * hash + this.tipo;
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
        final DietaAlimentos other = (DietaAlimentos) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.id_dieta, other.id_dieta)) {
            return false;
        }
        if (!Objects.equals(this.id_alimento, other.id_alimento)) {
            return false;
        }
        if (Double.doubleToLongBits(this.quantidade) != Double.doubleToLongBits(other.quantidade)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"id_dieta\":\"%s\", \"id_alimento\":\"%s\", \"quantidade\":\"%s\", \"tipo\" }", id, id_dieta, id_alimento, quantidade, tipo);
    }

}
