package orange.desafioorange.controller;

import orange.desafioorange.dto.EnderecoDTO;
import orange.desafioorange.exceptionHandler.exception.EntidadeNaoEncontradaException;
import orange.desafioorange.exceptionHandler.exception.NegocioException;
import orange.desafioorange.feignService.Cep;
import orange.desafioorange.feignService.CepService;
import orange.desafioorange.model.Endereco;
import orange.desafioorange.model.Usuario;
import orange.desafioorange.repository.EnderecoRepository;
import orange.desafioorange.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CepService cepService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoDTO save(@Valid @RequestBody Endereco endereco) {

        if (endereco.getCep().length() != 8)
            throw new NegocioException("Cep deve ter 8 dígitos");

        if (cepService.getCep(endereco.getCep()).getCep() == null)
            throw new EntidadeNaoEncontradaException("Cep inexistente");

        Cep cep = cepService.getCep(endereco.getCep());

        endereco.setBairro(cep.getBairro());
        endereco.setCidade(cep.getLocalidade());
        endereco.setCep(cep.getCep());
        endereco.setComplemento(cep.getComplemento());
        endereco.setEstado(cep.getUf());
        endereco.setLogradouro(cep.getLogradouro());

        if (usuarioRepository.findById(endereco.getUsuario().getId()).isEmpty())
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");

        Usuario usuario = usuarioRepository.findById(endereco.getUsuario().getId()).get();
        endereco.setUsuario(usuario);

        return new EnderecoDTO(enderecoRepository.save(endereco));
    }
}
