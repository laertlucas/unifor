package com.unifor.crudspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity // @Entity anotação indica que a classe é uma classe Java persistente.
@Table(name = "Course") // @Tablea anotação fornece a tabela que mapeia esta entidade.
public class Course {
    
    @Id // @Ida anotação é para a chave primária.
    @GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValuea anotação é usada para definir a estratégia de geração da chave primária. GenerationType.AUTOsignifica campo de incremento automático.
    private Long id;

    private String name;

    private String category;

    private boolean published;

    public Course(){

    }

    public Course(String name, String category, boolean published) {
        this.name = name;
        this.category = category;
        this.published = published;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public String toString(){
        return "Course [id=" + id + ", name=" + name + ", category=" + category + ", published= " + published + "]";
    }
}
