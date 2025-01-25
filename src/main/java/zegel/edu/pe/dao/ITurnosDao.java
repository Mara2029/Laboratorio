package zegel.edu.pe.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import zegel.edu.pe.models.Turnos;

public interface ITurnosDao extends JpaRepository<Turnos, Integer>{
	
	List<Turnos> findByCompeticionId(Integer competicionId);

}
