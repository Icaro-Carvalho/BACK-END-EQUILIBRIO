package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.Gestor;
import br.com.ponto.equilibrio.api.vo.GestorVO;
import br.com.ponto.equilibrio.core.repository.GestorRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestorService {

    @Autowired
    private GestorRepository gestorRepository;

    public GestorVO cadastrarGestor(GestorVO gestorVO) {
        Gestor gestor = new Gestor(gestorVO);
        gestorRepository.save(gestor);
        return new GestorVO(gestor);
    }

    public List<GestorVO> listarGestores() {
        List<Gestor> gestores = gestorRepository.findAll();
        return gestores.stream().map(GestorVO::new).toList();
    }

    public void deletarGestor(Long gestorId) {
        if (!gestorRepository.existsById(gestorId)) {
            throw new EntityNotFoundException("Gestor não encontrado");
        }
        gestorRepository.deleteById(gestorId);
    }
}
