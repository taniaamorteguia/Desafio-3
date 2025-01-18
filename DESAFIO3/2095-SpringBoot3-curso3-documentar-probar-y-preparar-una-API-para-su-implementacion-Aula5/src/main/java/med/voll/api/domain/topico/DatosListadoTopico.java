package med.voll.api.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion,
                                 Status status, String autor, String curso, int respuestas) {
    public DatosListadoTopico(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getRespuestas()
        );
    }
}
