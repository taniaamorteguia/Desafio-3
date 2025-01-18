package med.voll.api.domain.topico;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAll(Pageable paginacion);

    @Query("SELECT t FROM Topico t WHERE t.id = :id")
    Topico findTopico(@Param("id") Long id);
}
