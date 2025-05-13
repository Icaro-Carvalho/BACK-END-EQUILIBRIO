package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.Equipe;
import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.model.Gestor;
import br.com.ponto.equilibrio.api.vo.EquipeVO;
import br.com.ponto.equilibrio.core.repository.EquipeRepository;
import br.com.ponto.equilibrio.core.repository.FuncionarioRepository;
import br.com.ponto.equilibrio.core.repository.GestorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipe")
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private GestorRepository gestorRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public EquipeVO cadastrarEquipe(EquipeVO equipeVO) {
        Gestor gestor = gestorRepository.findById(equipeVO.getGestor().getId()).orElseThrow(() -> new RuntimeException("Gestor não encontrado"));
        Equipe equipe = new Equipe(equipeVO);
        equipe.setGestor(gestor);
        equipeRepository.save(equipe);
        return new EquipeVO(equipe);
    }

    public List<EquipeVO> listarEquipes() {
        List<Equipe> equipes = equipeRepository.findAll();
        return equipes.stream().map(EquipeVO::new).toList();
    }

    @Transactional
    public EquipeVO adicionarFuncionarios(Long equipeId, List<Long> funcionariosIds) {
        Equipe equipe = equipeRepository.findById(equipeId).orElseThrow(() -> new RuntimeException("Equipe não encontrada"));

        List<Funcionario> funcionarios = funcionarioRepository.findAllById(funcionariosIds);

        if (funcionarios.size() != funcionariosIds.size()) {
            throw new RuntimeException("Um ou mais funcionários não foram encontrados.");
        }

        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getEquipe() != null) {
                throw new RuntimeException("Funcionário " + funcionario.getId() + " já está vinculado a uma equipe.");
            }
        
            funcionario.setEquipe(equipe); 
            equipe.getFuncionarios().add(funcionario);
        }

        equipeRepository.save(equipe);

        return new EquipeVO(equipe);

    }

    @Transactional
    public EquipeVO adicionarGestor(Long equipeId, Long gestorId) {
        Equipe equipe = equipeRepository.findById(equipeId).orElseThrow(() -> new RuntimeException("Equipe Não Encontrada"));
        Gestor gestor = gestorRepository.findById(gestorId).orElseThrow(() -> new RuntimeException("Gestor nãoe ncontrado"));
        
        equipe.setGestor(gestor);
        equipeRepository.save(equipe);
        return new EquipeVO(equipe);
    }

    public void deletarEquipe(Long equipeId) {
        Equipe equipe = equipeRepository.findById(equipeId).orElseThrow(() -> new EntityNotFoundException("Equipe não encontrada"));

        boolean temFuncionarios = funcionarioRepository.existsByEquipeId(equipeId);
        boolean temGestor = equipe.getGestor() != null;

        if (temFuncionarios || temGestor) {
            throw new IllegalStateException("Não é possível excluir a equipe com funcionários ou gestor associados.");
        }

        equipeRepository.delete(equipe);
    }
}
