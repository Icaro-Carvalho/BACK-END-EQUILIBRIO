package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.EmpregadorEntity;
import br.com.ponto.equilibrio.api.vo.EmpregadorVO;
import br.com.ponto.equilibrio.core.repository.EmpregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpregadorService {

    @Autowired
    private EmpregadorRepository empregadorRepository;

    public EmpregadorVO cadastrar(EmpregadorVO empregadorVO) {
        EmpregadorEntity empregadorEntity = empregadorRepository.save(new EmpregadorEntity(empregadorVO));
        return new EmpregadorVO(empregadorEntity);
    }

    public EmpregadorVO atualizar(Long id, EmpregadorVO empregadorVO) {
        EmpregadorEntity empregadorEntity = empregadorRepository.getReferenceById(id);

        empregadorEntity.setRazaoSocial(empregadorVO.getRazaoSocial());
        empregadorEntity.setCnpjCpf(empregadorVO.getCnpjCpf());
        empregadorEntity.setCep(empregadorVO.getCep());
        empregadorEntity.setLogradouro(empregadorVO.getLogradouro());
        empregadorEntity.setNumero(empregadorVO.getNumero());
        empregadorEntity.setComplemento(empregadorVO.getComplemento());
        empregadorEntity.setEstado(empregadorVO.getEstado());
        empregadorEntity.setCidade(empregadorVO.getCidade());
        empregadorEntity.setDiaFechamentoPonto(empregadorVO.getDiaFechamentoPonto());

        empregadorRepository.save(empregadorEntity);

        return new EmpregadorVO(empregadorEntity);
    }

    public Optional<EmpregadorVO> findById(Long id) {
        EmpregadorEntity findById = empregadorRepository.getReferenceById(id);
        EmpregadorVO empregadorVO = new EmpregadorVO(findById);
        return Optional.of(empregadorVO);
    }

    public List<EmpregadorVO> findall() {
        List<EmpregadorEntity> empregadorEntities = empregadorRepository.findAll();
        return empregadorEntities.stream().map(EmpregadorVO::new).toList();
    }

    public void excluir(Long id) {
        EmpregadorEntity empregadorEntity = empregadorRepository.getReferenceById(id);
        empregadorRepository.delete(empregadorEntity);
    }
}
