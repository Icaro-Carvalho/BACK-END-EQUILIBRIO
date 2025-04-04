package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.Equipe;
import br.com.ponto.equilibrio.api.model.Gestor;
import br.com.ponto.equilibrio.api.vo.EquipeVO;
import br.com.ponto.equilibrio.core.repository.EquipeRepository;
import br.com.ponto.equilibrio.core.repository.GestorRepository;
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


    public EquipeVO cadastrarEquipe(EquipeVO equipeVO) {
        Gestor gestor = gestorRepository.findById(equipeVO.getGestor().getId()).orElseThrow(() -> new RuntimeException("Gestor não encontrado"));
        Equipe equipe = new Equipe(equipeVO);
        equipe.setGestor(gestor);
        equipeRepository.save(equipe);
        return new EquipeVO(equipe);
    }
}
