package zegel.edu.pe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import zegel.edu.pe.dao.ITurnosDao;
import zegel.edu.pe.models.Competiciones;
import zegel.edu.pe.models.Turnos;
import zegel.edu.pe.models.Usuarios;
import java.util.Random;

@Service
public class TurnosServices {
	@Autowired
	private ITurnosDao turDao;
	
	public List<Turnos> getListTurnos(){
		return turDao.findAll();
	}
	
	public Turnos getListTurnosId(Integer id){
		return turDao.findById(id).orElse(null);
	}
	
	public Turnos actualizarTurnos(Turnos tur) {
		return turDao.save(tur);	
	}
	
	public Turnos guardarTurnos(Turnos tur) {
		return turDao.save(tur);
	}
	
	public void eliminarTurnos(Integer id) {
		turDao.deleteById(id);
	}
	
	
    public Turnos crearEnfrentamiento(Competiciones competicion, Usuarios usuario1, Usuarios usuario2) {
    	Turnos enfrentamiento = new Turnos();
        enfrentamiento.setCompeticion(competicion);
        enfrentamiento.setUsuario1(usuario1);
        enfrentamiento.setUsuario2(usuario2);
        return turDao.save(enfrentamiento);
    }

    public Usuarios determinarGanadorAleatorio(Turnos enfrentamiento) {
        Random random = new Random();
        Usuarios ganador = random.nextBoolean() ? enfrentamiento.getUsuario1() : enfrentamiento.getUsuario2();
        enfrentamiento.setGanador(ganador);
        turDao.save(enfrentamiento);
        return ganador;
    }

    public List<Turnos> obtenerEnfrentamientosPorCompeticion(Competiciones competicion) {
        return turDao.findByCompeticionId(competicion.getId());
    }
    
    public Turnos obtenerEnfrentamientoPorId(Integer id) {
        Optional<Turnos> enfrentamiento = turDao.findById(id);
        if (enfrentamiento.isPresent()) {
            return enfrentamiento.get();
        } else {
            throw new EntityNotFoundException("Enfrentamiento con ID " + id + " no encontrado.");
        }
    }

}
