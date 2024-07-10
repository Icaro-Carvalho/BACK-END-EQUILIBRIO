package br.com.ponto.equilibrio.core.service;


import br.com.ponto.equilibrio.api.model.UnidadeEntity;
import br.com.ponto.equilibrio.api.vo.UnidadeVO;
import br.com.ponto.equilibrio.core.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public UnidadeVO cadastrar(UnidadeVO unidadeVO) {
        UnidadeEntity unidadeEntity = unidadeRepository.save(new UnidadeEntity(unidadeVO));
        return new UnidadeVO(unidadeEntity);
    }

    public UnidadeVO atualizar(Long id, UnidadeVO unidadeVO) {
        UnidadeEntity unidadeEntity = unidadeRepository.getReferenceById(id);

        unidadeEntity.setNome(unidadeVO.getNome());
        unidadeEntity.setFusoHorario(unidadeVO.getFusoHorario());
        unidadeEntity.setEndereco(unidadeVO.getEndereco());
        unidadeEntity.setLongitude(unidadeVO.getLongitude());
        unidadeEntity.setLatitude(unidadeVO.getLatitude());
        unidadeEntity.setRaio(unidadeVO.getRaio());

        unidadeRepository.save(unidadeEntity);

        return new UnidadeVO(unidadeEntity);
    }



    public Optional<UnidadeVO> findById(Long id) {
        UnidadeEntity findById = unidadeRepository.getReferenceById(id);
        UnidadeVO unidadeVO = new UnidadeVO(findById);

        return Optional.of(unidadeVO);
    }

    public List<UnidadeVO> findAll() {
        List<UnidadeEntity> unidadeEntities = unidadeRepository.findAll();
        return unidadeEntities.stream().map(UnidadeVO::new).toList();
    }

    public void excluir(Long id) {
        UnidadeEntity unidadeEntity = unidadeRepository.getReferenceById(id);
        unidadeRepository.delete(unidadeEntity);
    }
}
