package com.estudo.ProjDog.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "dono")
public class Dono {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nome;
   private String cpf;

   @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL)
   @JsonBackReference 
   private List<Paciente> pacientes;

   //Constructor vazio 
   public Dono(){

   }

   //Constructor com os atributos
   public Dono(Long id, String nome, String cpf){
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    
   

}
