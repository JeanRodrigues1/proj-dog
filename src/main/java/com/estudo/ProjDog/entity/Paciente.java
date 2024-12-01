package com.estudo.ProjDog.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sexo;
    private Boolean alta;
    private String especie;    
    @ManyToOne
    @JoinColumn(name = "id_dono", nullable = false)
    @JsonBackReference 
    private Dono dono;

    // Construtor vazio
    public Paciente(){

    }
    
    // Construtor com todos os atributos
    public Paciente(Long id, String nome, String sexo, Boolean alta, String especie){
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.alta = alta;
        this.especie = especie;
    }

    //Getters and Setters :P
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

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Dono getDono() {
        return dono;
    }

    public void setDono(Dono dono) {
        this.dono = dono;
    }

    
 
    
}