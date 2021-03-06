package ubb.gpsw.arrauPropiedades.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ubb.gpsw.arrauPropiedades.model.Destinacion;
import ubb.gpsw.arrauPropiedades.model.Inmobiliaria;
import ubb.gpsw.arrauPropiedades.model.Propiedad;
import ubb.gpsw.arrauPropiedades.model.TopFive;
import ubb.gpsw.arrauPropiedades.service.DestinacionService;
import ubb.gpsw.arrauPropiedades.service.InmobiliariaService;
import ubb.gpsw.arrauPropiedades.service.PropiedadService;
import ubb.gpsw.arrauPropiedades.service.TopFiveService;

@Controller
public class PdfExcelController {
	// Servicio de clases que se implementan
	@Autowired
	private DestinacionService destService;

	@Autowired
	private PropiedadService propService;

	@Autowired
	private TopFiveService topService;

	@Autowired
	private InmobiliariaService inmoService;

	// Agregar para poder crear PDF
	@Autowired
	private ServletContext context;

	/*
	 * public String allDestinacion() { return null; }
	 */

	// API para crear topFive.pdf
	@GetMapping(value = "/pdfTopFive")
	public void pdfTopFive(HttpServletRequest request, HttpServletResponse response) {

		List<TopFive> topFive = topService.getAll();
		boolean isFlag = topService.pdfTopFive(topFive, context, request, response);
		if (isFlag) {
			// La direccion debe ser la misma definida en serviceImpl
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "topFive" + ".pdf");
			filedownload(fullPath, response, "topFive.pdf");
		}
	}

	// API para crear TopFive.xls
	@GetMapping(value = "/excelTopFive")
	public void excelTopFive(HttpServletRequest request, HttpServletResponse response) {
		List<TopFive> topFive = topService.getAll();
		boolean isFlag = topService.excelTopFive(topFive, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "topFive" + ".xls");
			filedownload(fullPath, response, "topFive.xls");

		}
	}

	// API para crear Inmobiliarias.pdf
	@GetMapping(value = "/pdfInmobiliarias")
	public void pdfInmobiliaria(HttpServletRequest request, HttpServletResponse response) {

		List<Inmobiliaria> inmobiliarias = inmoService.getAll();
		boolean isFlag = inmoService.pdfInmobiliaria(inmobiliarias, context, request, response);
		if (isFlag) {
			// La direccion debe ser la misma definida en serviceImpl
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "inmobiliarias" + ".pdf");
			filedownload(fullPath, response, "inmobiliarias.pdf");
		}
	}

	// API para crear inmobiliarias.xls
	@GetMapping(value = "/excelInmobiliarias")
	public void excelInmobiliaria(HttpServletRequest request, HttpServletResponse response) {
		List<Inmobiliaria> inmobiliarias = inmoService.getAll();
		boolean isFlag = inmoService.excelInmobiliaria(inmobiliarias, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "inmobiliarias" + ".xls");
			filedownload(fullPath, response, "inmobiliarias.xls");

		}
	}

	// API para crear propiedades.pdf
	@GetMapping(value = "/pdfPropiedades")
	public void pdfPropiedades(HttpServletRequest request, HttpServletResponse response) {

		List<Propiedad> propiedades = propService.getAll();
		boolean isFlag = propService.pdfPropiedades(propiedades, context, request, response);
		if (isFlag) {
			// La direccion debe ser la misma definida en serviceImpl
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "propiedades" + ".pdf"); // direccion
																														// debe
																														// ser
																														// la
																														// misma
																														// que
																														// en
																														// ServiceImpl
			filedownload(fullPath, response, "propiedades.pdf");
		}
	}

	// API para crear archivo Excel propiedades.xls
	@GetMapping(value = "/excelPropiedades")
	public void excelPropiedades(HttpServletRequest request, HttpServletResponse response) {
		List<Propiedad> propiedades = propService.getAll();
		boolean isFlag = propService.excelPropiedades(propiedades, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "propiedades" + ".xls");
			filedownload(fullPath, response, "propiedades.xls");

		}
	}

	// API para crear destinaciones.pdf
	@GetMapping(value = "/pdfDestinacion")
	public void pdfDestinacion(HttpServletRequest request, HttpServletResponse response) {

		List<Destinacion> destinaciones = destService.getAll();
		boolean isFlag = destService.pdfDestinacion(destinaciones, context, request, response);
		if (isFlag) {
			// La direccion debe ser la misma definida en serviceImpl
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "destinaciones" + ".pdf"); // direccion
																															// debe
																															// ser
																															// la
																															// misma
																															// que
																															// en
																															// ServiceImpl
			filedownload(fullPath, response, "destinaciones.pdf");
		}

	}

	// API para crear destinaciones.xls
	@GetMapping(value = "/excelDestinacion")
	public void createExcel(HttpServletRequest request, HttpServletResponse response) {
		List<Destinacion> destinaciones = destService.getAll();
		boolean isFlag = destService.createExcel(destinaciones, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "destinaciones" + ".xls"); // direccion
																															// debe
																															// ser
																															// la
																															// misma
																															// que
																															// en
																															// ServiceImpl
			filedownload(fullPath, response, "destinaciones.xls");

		}
	}

	// Descargar archivo
	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
