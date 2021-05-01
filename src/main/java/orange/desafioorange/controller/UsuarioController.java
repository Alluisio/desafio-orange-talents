package orange.desafioorange.controller;

import orange.desafioorange.dto.UsuarioDTO;
import orange.desafioorange.model.Usuario;
import orange.desafioorange.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO findById(@PathVariable Long id) {
        return new UsuarioDTO(usuarioRepository.findById(id).get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO save(@RequestBody Usuario usuario) {
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }
}
