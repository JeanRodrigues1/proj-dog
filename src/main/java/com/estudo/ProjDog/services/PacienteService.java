package com.estudo.ProjDog.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.ProjDog.entity.Dono;
import com.estudo.ProjDog.entity.Paciente;
import com.estudo.ProjDog.repository.DonoInterface;
import com.estudo.ProjDog.repository.PacienteInterface;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteInterface pacienteInterface;

    @Autowired
    private DonoInterface donoInterface;

    public List<Paciente> getAllPacientes(){
        return pacienteInterface.findAll();
    }

    public Optional<Paciente> getPacienteById(Long id){
        return pacienteInterface.findById(id);
    }

    public List<Paciente> getPacientesByName(String nome){
        Optional<List<Paciente>> pacientes = pacienteInterface.findByNomeContainingIgnoreCase(nome);
        if (pacientes.isPresent()){
            return pacientes.get();
        } else {
            return Collections.emptyList();
        }
    }

    public List<Paciente> getPacientesByDonoId(Long id_dono){
       try {
            Optional<Dono> dono = donoInterface.findById(id_dono);
            if(dono.isPresent()){
                return dono.get().getPacientes();
            } else{
                throw new EntityNotFoundException("Dono com id " + id_dono + " não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar paciente pelo id do dono: " + id_dono,e);
        }
    }

    public Paciente savePaciente(Paciente paciente){
        Long donoId = paciente.getDono().getId();
        Optional<Dono> dono = donoInterface.findById(donoId);
        if (dono.isPresent()) {
            paciente.setDono(dono.get());
            return pacienteInterface.save(paciente);
        } else {
            throw new EntityNotFoundException("Dono não encontrado para o ID: " + donoId);
        }
    }
    
    

   public Paciente updatePacienteById(Long id, Paciente newPacienteData){
    Optional<Paciente> pacienteData = pacienteInterface.findById(id);
    if (pacienteData.isPresent()){
        Paciente updatePacienteData = pacienteData.get();
        updatePacienteData.setNome(newPacienteData.getNome());
        updatePacienteData.setEspecie(newPacienteData.getEspecie());
        updatePacienteData.setAlta(newPacienteData.getAlta());
        updatePacienteData.setSexo(newPacienteData.getSexo());
        if (newPacienteData.getDono() != null){
            updatePacienteData.setDono(newPacienteData.getDono());
        } 
        return pacienteInterface.save(updatePacienteData);
    } else {
        return null;
    }
   }

   public void deleteByPacienteId(Long id){
    pacienteInterface.deleteById(id);
   }
   
   
}
