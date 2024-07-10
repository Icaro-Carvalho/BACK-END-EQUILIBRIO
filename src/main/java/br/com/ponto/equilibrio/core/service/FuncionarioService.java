package br.com.ponto.equilibrio.core.service;

import br.com.ponto.equilibrio.api.model.EmpregadorEntity;
import br.com.ponto.equilibrio.api.model.FuncionarioEntity;
import br.com.ponto.equilibrio.api.model.GestorEntity;
import br.com.ponto.equilibrio.api.model.UnidadeEntity;
import br.com.ponto.equilibrio.api.vo.EmpregadorVO;
import br.com.ponto.equilibrio.api.vo.FuncionarioVO;
import br.com.ponto.equilibrio.api.vo.GestorVO;
import br.com.ponto.equilibrio.core.repository.EmpregadorRepository;
import br.com.ponto.equilibrio.core.repository.FuncionarioRepository;
import br.com.ponto.equilibrio.core.repository.GestorRepository;
import br.com.ponto.equilibrio.core.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private UnidadeRepository unidadeRepository;

    @Autowired
    private EmpregadorRepository empregadorRepository;

    @Autowired
    private GestorRepository gestorRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioVO cadastrar(FuncionarioVO funcionarioVO) {

        Long unidadeId = funcionarioVO.getUnidade().getId();
        UnidadeEntity unidadeEntity = unidadeRepository.findById(unidadeId)
                .orElseThrow(() -> new IllegalArgumentException("Unidade com ID " + unidadeId + " não encontrada."));

        Long empregadorId = funcionarioVO.getEmpregador().getId();
        EmpregadorEntity empregadorEntity = empregadorRepository.findById(empregadorId)
                .orElseThrow(() -> new IllegalArgumentException("Empregador com ID " + empregadorId + " não encontrado."));

        List<GestorEntity> gestores = funcionarioVO.getGestor().stream()
                .map(gestorVO -> gestorRepository.findById(gestorVO.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Gestor com ID " + gestorVO.getId() + " não encontrado.")))
                .toList();

        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();

        funcionarioEntity.setUnidade(unidadeEntity);
        funcionarioEntity.setEmpregador(empregadorEntity);
        funcionarioEntity.setGestor(gestores);


        funcionarioEntity = funcionarioRepository.save(new FuncionarioEntity(funcionarioVO));
        return new FuncionarioVO(funcionarioEntity);
    }

    public FuncionarioVO atualizar(Long id, FuncionarioVO funcionarioVO) {
        FuncionarioEntity funcionarioEntity = funcionarioRepository.getReferenceById(id);

        funcionarioEntity.setNomeCompleto(funcionarioVO.getNomeCompleto());
        funcionarioEntity.setCpf(funcionarioVO.getCpf());
        funcionarioEntity.setDataNascimento(funcionarioVO.getDataNascimento());
        funcionarioEntity.setNisPis(funcionarioVO.getNisPis());
        funcionarioEntity.setCelular(funcionarioVO.getCelular());
        funcionarioEntity.setEmail(funcionarioVO.getEmail());
        funcionarioEntity.setEstado(funcionarioVO.getEstado());
        funcionarioEntity.setCidade(funcionarioVO.getCidade());
        funcionarioEntity.setDepartamento(funcionarioVO.getDepartamento());
        funcionarioEntity.setCargo(funcionarioVO.getCargo());
        funcionarioEntity.setCodigoMatricula(funcionarioVO.getCodigoMatricula());
        funcionarioEntity.setValorHora(funcionarioVO.getValorHora());
        funcionarioEntity.setDataAdmissao(funcionarioVO.getDataAdmissao());
        funcionarioEntity.setInicioRegistroPonto(funcionarioVO.getInicioRegistroPonto());
        funcionarioEntity.setEmpregador(new EmpregadorEntity(funcionarioVO.getEmpregador()));
        funcionarioEntity.setGestor(funcionarioVO.getGestor().stream().map(GestorEntity::new).collect(Collectors.toList()));
        funcionarioEntity.setUnidade(new UnidadeEntity(funcionarioVO.getUnidade()));

        funcionarioRepository.save(funcionarioEntity);

        return new FuncionarioVO(funcionarioEntity);

    }

    public Optional<FuncionarioVO> findById(Long id) {
        FuncionarioEntity findById = funcionarioRepository.getReferenceById(id);
        FuncionarioVO funcionarioId = new FuncionarioVO(findById);
        return Optional.of(funcionarioId);
    }

    public List<FuncionarioVO> findAll() {
        return funcionarioRepository.findAll().stream().map(FuncionarioVO::new).collect(Collectors.toList());
    }

    public boolean delete(Long id) {
            FuncionarioEntity findById = funcionarioRepository.getReferenceById(id);
            funcionarioRepository.delete(findById);
            return true;
    }

    public boolean existsByCpf(String cpf) {
        return funcionarioRepository.existsByCpf(cpf);
    }

}
