package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.topico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
     public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                                                 UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(datos));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus(), topico.getAutor(), topico.getCurso(), topico.getRespuestas()
        );
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopico(@PageableDefault(size = 5)Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> consultarTopicoId(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(
                topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus(), topico.getAutor(), topico.getCurso(), topico.getRespuestas()
        );
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico,
                                           UriComponentsBuilder uriComponentsBuilder){
        Optional<Topico> topico = Optional.of(topicoRepository.getReferenceById(id));
        if (topico.isPresent()){
            topico.get().actualizarDatos(datosActualizarTopico);
            URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.get().getId()).toUri();
            return ResponseEntity.created(url).body(datosActualizarTopico);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
