package ubb.gpsw.arrauPropiedades.service;

import java.util.List;

import ubb.gpsw.arrauPropiedades.model.Propiedad;
import ubb.gpsw.arrauPropiedades.utilidades.Respuesta;


public interface PropiedadService extends Respuesta<Propiedad, Integer> {

	List<Propiedad> findAll();
}