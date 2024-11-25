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
public class DonoService {
    
    @Autowired
    private DonoInterface donoInterface;

    @Autowired 
    private PacienteInterface pacienteInterface;

    public List<Dono> getAllDonos(){
        return donoInterface.findAll();
    }

    public Optional<Dono> getDonoById(Long id){
        return donoInterface.findById(id);
    }

    public List<Dono> getDonosByName(String nome){
        Optional<List<Dono>> donos = donoInterface.findByNameContainingIgnoreCase(nome);
        if (donos.isPresent()){
            return donos.get();
        } else {
            return Collections.emptyList();
        }
    }

    public Dono getDonoByPacienteId(Long id_paciente){
        try {
            Optional<Paciente> paciente = pacienteInterface.findById(id_paciente);
            if(paciente.isPresent()){
                return paciente.get().getDono();
            } else{
                throw new EntityNotFoundException("Paciente com id " + id_paciente + " não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar dono pelo id do paciente: " + id_paciente,e); 
        }
    }


    public Dono saveDono(Dono dono){
        return donoInterface.save(dono);
    }

    public Dono updateDonoById(Long id, Dono newDonoData){
        Optional<Dono> donoData = donoInterface.findById(id);
        if (donoData.isPresent()){
            Dono updateDonoData = donoData.get();
            updateDonoData.setNome(newDonoData.getNome());
            updateDonoData.setCpf(newDonoData.getCpf());
            if (newDonoData.getPacientes() != null){
                updateDonoData.setPacientes(newDonoData.getPacientes());
            }
            return donoInterface.save(updateDonoData);
        } else {
            return null;
        }
    }

    public void deleteDonoById(Long id){
        donoInterface.deleteById(id);
    }
}
