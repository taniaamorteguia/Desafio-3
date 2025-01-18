package med.voll.api.domain.topico;

import lombok.Getter;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Enum status,
        String autor,
        String curso,
        int respuestas
) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String titulo() {
        return titulo;
    }

    @Override
    public String mensaje() {
        return mensaje;
    }

    @Override
    public LocalDateTime fechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public Enum status() {
        return status;
    }

    @Override
    public String autor() {
        return autor;
    }

    @Override
    public String curso() {
        return curso;
    }

    @Override
    public int respuestas() {
        return respuestas;
    }
}
