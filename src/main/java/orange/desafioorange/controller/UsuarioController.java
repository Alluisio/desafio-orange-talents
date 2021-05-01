package orange.desafioorange.controller;

import orange.desafioorange.dto.UsuarioDTO;
import orange.desafioorange.exceptionHandler.exception.EntidadeNaoEncontradaException;
import orange.desafioorange.exceptionHandler.exception.NegocioException;
import orange.desafioorange.model.Usuario;
import orange.desafioorange.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO findById(@PathVariable Long id) {

        if (usuarioRepository.findById(id).isEmpty())
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");

        return new UsuarioDTO(usuarioRepository.findById(id).get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO save(@Valid @RequestBody Usuario usuario) {

        if (usuarioRepository.findByEmail(usuario.getEmail()) != null)
            throw new NegocioException("Email já cadastrado");

        if (usuarioRepository.findByCpf(usuario.getCpf()) != null)
            throw new NegocioException("Cpf já cadastrado");

        return new UsuarioDTO(usuarioRepository.save(usuario));
    }
}
