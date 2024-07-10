package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.GestorEntity;
import br.com.ponto.equilibrio.api.vo.GestorVO;
import br.com.ponto.equilibrio.core.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GestorService {

    @Autowired
    private GestorRepository gestorRepository;

    public GestorService(GestorRepository gestorRepository) {
        this.gestorRepository = gestorRepository;
    }

    public GestorVO cadastrar(GestorVO gestorVO) {
        GestorEntity gestorEntity = gestorRepository.save(new GestorEntity(gestorVO));
        return new GestorVO(gestorEntity);
    }

    public GestorVO atualizar(Long id, GestorVO gestorVO) {
        GestorEntity gestorEntity = gestorRepository.getReferenceById(id);

        gestorEntity.setNomeCompleto(gestorVO.getNomeCompleto());
        gestorEntity.setCelular(gestorVO.getCelular());
        gestorEntity.setEmail(gestorVO.getEmail());
        gestorEntity.setAtendimentoChat(gestorVO.getAtendimentoChat());
        gestorEntity.setPermissao(gestorVO.getPermissao());

        gestorRepository.save(gestorEntity);

        return new GestorVO(gestorEntity);
    }

    public Optional<GestorVO> findById(Long id) {
        GestorEntity findById = gestorRepository.getReferenceById(id);
        GestorVO gestorId = new GestorVO(findById);
        return Optional.of(gestorId);
    }

    public List<GestorVO> findAll() {
        List<GestorEntity> gestorEntity = gestorRepository.findAll();
        return gestorEntity.stream().map(GestorVO::new).toList();
    }

    public void excluir(Long id) {
        GestorEntity findById = gestorRepository.getReferenceById(id);
        gestorRepository.delete(findById);
    }

}
