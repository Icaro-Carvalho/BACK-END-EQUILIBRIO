package br.com.ponto.equilibrio.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ponto.equilibrio.api.model.Funcionario;
import br.com.ponto.equilibrio.api.model.Mensagem;
import br.com.ponto.equilibrio.api.model.MensagemSuporte;
import br.com.ponto.equilibrio.api.model.Rh;
import br.com.ponto.equilibrio.api.vo.MensagemVO;
import br.com.ponto.equilibrio.core.repository.FuncionarioRepository;
import br.com.ponto.equilibrio.core.repository.MensagemRepository;
import br.com.ponto.equilibrio.core.repository.MensagemSuporteRepository;
import br.com.ponto.equilibrio.core.repository.RhRepository;

@Service
public class MensagemService {

    @Autowired 
    private MensagemRepository mensagemRepository;
    @Autowired
    private RhRepository rhRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private MensagemSuporteRepository mensagemSuporteRepository;

    public MensagemVO criarMensagem(MensagemVO mensagemVO) {
        Mensagem mensagem = new Mensagem();

        mensagem.setTexto(mensagemVO.getTexto());
        mensagem.setDataCriacao(mensagemVO.getDataCriacao());
        
        Mensagem novaMensagem = mensagemRepository.save(mensagem);
        return new MensagemVO(novaMensagem);
    }

    public void enviarMensagem(Long rhId, Long funcionarioId, Long mensagemId) {
        Rh rh = rhRepository.findById(rhId).orElseThrow(() -> new RuntimeException("RH não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        Mensagem mensagem = mensagemRepository.findById(mensagemId).orElseThrow(() -> new RuntimeException("Mensagem não encontrada"));

        MensagemSuporte mensagemSuporte = new MensagemSuporte();
        mensagemSuporte.setRh(rh);
        mensagemSuporte.setFuncionario(funcionario);
        mensagemSuporte.setMensagem(mensagem);
        mensagemSuporteRepository.save(mensagemSuporte);

    }

    public List<MensagemVO> mostrarMensagens() {
        List<Mensagem> mensagems = mensagemRepository.findAll();
        return mensagems.stream().map(MensagemVO::new).toList();
    }
    
}
