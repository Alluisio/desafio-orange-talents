package orange.desafioorange.controller;

import orange.desafioorange.dto.EnderecoDTO;
import orange.desafioorange.feignService.Cep;
import orange.desafioorange.feignService.CepService;
import orange.desafioorange.model.Endereco;
import orange.desafioorange.model.Usuario;
import orange.desafioorange.repository.EnderecoRepository;
import orange.desafioorange.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public EnderecoDTO save(@RequestBody Endereco endereco) {
        Cep cep = cepService.getCep(endereco.getCep());

        endereco.setBairro(cep.getBairro());
        endereco.setCidade(cep.getLocalidade());
        endereco.setCep(cep.getCep());
        endereco.setComplemento(cep.getComplemento());
        endereco.setEstado(cep.getUf());
        endereco.setLogradouro(cep.getLogradouro());

        Usuario usuario = usuarioRepository.findById(endereco.getUsuario().getId()).get();
        endereco.setUsuario(usuario);

        return new EnderecoDTO(enderecoRepository.save(endereco));
    }
}
