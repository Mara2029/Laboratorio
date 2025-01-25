package zegel.edu.pe.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import zegel.edu.pe.models.Competiciones;
import zegel.edu.pe.models.Eventos;

public interface ICompeticionesDao extends JpaRepository<Competiciones, Integer> {

    Optional<Competiciones> findByEventos(Eventos evento);

    @Query("SELECT COUNT(u) FROM Competiciones c JOIN c.usuarios u WHERE c.id = :competicionId")
    long countUsuariosByCompeticionId(@Param("competicionId") Integer competicionId);
}
