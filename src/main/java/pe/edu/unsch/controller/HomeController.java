package pe.edu.unsch.controller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.unsch.service.ArchivoService;
import pe.edu.unsch.service.CalendarioService;
import pe.edu.unsch.service.ExpedienteService;
import pe.edu.unsch.service.HistorialService;
import pe.edu.unsch.service.PromocionService;
import pe.edu.unsch.service.UsuarioService;
import pe.edu.unsch.entities.Archivo;
import pe.edu.unsch.entities.Usuario;

@Controller
@RequestMapping("/admin")
public class HomeController {
	@Autowired
	private ArchivoService archivoService;
	
	@Autowired
	private CalendarioService calendarioService;
	
	@Autowired
	private ExpedienteService expedienteService;
	
	@Autowired
	private HistorialService historialService;
	
	@Autowired
	private PromocionService promocionService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Dashboard");
		return "views/admin/home/index";
	}
	
	@GetMapping("/convocatoria")
	public String conv(Model model, @ModelAttribute("usuario") Usuario usuario) {
		long id_docente = usuario.getDocentes().stream().findFirst().get().getIddocente();
		model.addAttribute("title", "Convocatoria");
		model.addAttribute("sendable", this.expedienteService.isSendable(id_docente));
		model.addAttribute("calendarios", this.calendarioService.listarCalendario());
		model.addAttribute("expedientes", this.expedienteService.listarExpedientesHabiles(id_docente) );
		return "views/admin/home/convocatoria";
	}
	
	@GetMapping("/expediente")
	public String expediente(Model model, @ModelAttribute("usuario") Usuario usuario) {
		model.addAttribute("title", "Expedientes");
		model.addAttribute("expedientes", this.expedienteService.listarExpedientes(usuario.getDocentes().stream().findFirst().get().getIddocente()) );
		return "views/admin/home/expediente";
	}
	
	@GetMapping("/expediente-doc")
	public String expedienteDoc(Model model, @ModelAttribute("usuario") Usuario usuario) {
		model.addAttribute("title", "Subir documentos");
		model.addAttribute("expedientes", this.expedienteService.listarExpedientes(usuario.getDocentes().stream().findFirst().get().getIddocente()) );
		return "views/admin/home/doc_expedientes";
	}
	
	@GetMapping("/exp-doc/{idExpediente}")
	public String doc(Model model, @ModelAttribute("usuario") Usuario usuario, @PathVariable("idExpediente") long idExpediente) {
		boolean bool = this.expedienteService.isEditable(idExpediente);
		if(bool) {
			model.addAttribute("bool", bool);
		}
		model.addAttribute("title", "Documentos");
		model.addAttribute("idExpediente", idExpediente);
		model.addAttribute("docs", this.archivoService.listarDocumentos(idExpediente) );
		return "views/admin/home/documentos";
	}
	
	@GetMapping("/historial")
	public String historial(Model model, @ModelAttribute("usuario") Usuario usuario) {
		model.addAttribute("title", "Historial");
		model.addAttribute("historial", this.historialService.listarHistorial(usuario.getIdusuario()) );
		return "views/admin/home/historial";
	}
		
	@GetMapping("/usuario")
	public String user(Model model, @ModelAttribute("usuario") Usuario usuario) {
		model.addAttribute("title", "Usuario");
		model.addAttribute("user", this.usuarioService.datosUsuario(usuario.getIdusuario()) );
		return "views/admin/home/usuario";
	}
	
	@GetMapping("/solicitud")
	public String solicitud(Model model, @ModelAttribute("usuario") Usuario usuario) {
		model.addAttribute("title", "Solicitud");
		model.addAttribute("user", this.usuarioService.datosUsuario(usuario.getIdusuario()) );
		model.addAttribute("expedientes", this.expedienteService.listarExpedientesHabiles(usuario.getDocentes().stream().findFirst().get().getIddocente()) );
		return "views/admin/home/solicitud";
	}
	
	// CONVOCATORIA
	//
	//
	@PostMapping("/save-conv")
	public String save_conv(RedirectAttributes redir, @ModelAttribute("usuario") Usuario usuario, @RequestParam("optradio") long optradio) {
		this.promocionService.savePromocion(optradio, usuario.getDocentes().stream().findFirst().get().getIddocente());
		redir.addFlashAttribute("error", "Las claves nuevas no coinciden entre sí.");
		return "redirect:/admin/convocatoria";
	}
	
	// USER MANAGEMENT ROUTING
	//
	//
	
	@PostMapping("/change-pass")
	public String change_pass(HttpServletRequest request, RedirectAttributes redir, @ModelAttribute("usuario") Usuario usuario) {
		int resp = 0;
		
		try{
			if(request.getParameter("password_n1").equals(request.getParameter("password_n2"))) {
				resp = this.usuarioService.changePass(request.getParameter("password_old"), request.getParameter("password_n2"), usuario.getIdusuario());
				if(resp == 0) {
					redir.addFlashAttribute("error", "La clave actual no coincide con la nueva.");
					return "redirect:/admin/usuario";
				} else if (resp == 1) {
					redir.addFlashAttribute("acierto", "La clave se cambió con éxito.");
					return "redirect:/admin/usuario";
				} else {
					redir.addFlashAttribute("error", "Algo salió mal...");
					return "redirect:/admin/usuario";
				}
			} else {
				redir.addFlashAttribute("error", "Las claves nuevas no coinciden entre sí.");
				return "redirect:/admin/usuario";
			}
			
		}			
		catch(Exception err) {
			err.printStackTrace();
			redir.addFlashAttribute("error", "Algo salió mal...");
			return "redirect:/admin/usuario";
		}
	}

	@PostMapping("/change-param")
	public String change_param(HttpServletRequest request, RedirectAttributes redir, @ModelAttribute("usuario") Usuario usuario) {
		int resp = 0;
		
		try{
			resp = this.usuarioService.changeParam(request.getParameter("celular"), request.getParameter("domicilio"), usuario.getIdusuario());
			if(resp == 0) {
				redir.addFlashAttribute("error", "Algo salió mal...");
				return "redirect:/admin/usuario";
			} else {
				redir.addFlashAttribute("acierto", "Datos guardados con éxito.");
				return "redirect:/admin/usuario";
			}
			
		}			
		catch(Exception err) {
			err.printStackTrace();
			redir.addFlashAttribute("error", "Algo salió mal...");
			return "redirect:/admin/usuario";
		}
	}
	
	// EXPEDIENT ROUTING
	//
	//
	@PostMapping("/add-exp")
	public String addExp(RedirectAttributes redir, @RequestParam("name") String name, @ModelAttribute("usuario") Usuario usuario) {
		int resp = 0;
		
		try{
			resp = this.expedienteService.addExpediente(name, new Long(usuario.getIdusuario()) );
			if(resp == -1) {
				redir.addFlashAttribute("error", "Ya existe un expediente con ese nombre...");
				return "redirect:/admin/expediente";
			} else {
				redir.addFlashAttribute("acierto", "Expediente guardado con éxito.");
				return "redirect:/admin/expediente";
			}
			
		}			
		catch(Exception err) {
			err.printStackTrace();
			redir.addFlashAttribute("error", "Algo salió mal...");
			return "redirect:/admin/expediente";
		}
	}
	
	@RequestMapping("/remove-exp/{idExpediente}")
	public String removeExp(@PathVariable("idExpediente") long idExpediente) {
		this.expedienteService.removeExpediente(idExpediente);
		return "redirect:/admin/expediente/";
	}
	
	// DOCUMENT ROUTING
	//
	//
	
	@PostMapping("/save-doc")
	public String saveDoc(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,  @RequestParam("idExpediente") long idExpediente) {
		if(!name.trim().toLowerCase().equals("solicitud")) {
			this.archivoService.saveDocumento(file, name, idExpediente);
		}
		return "redirect:/admin/exp-doc/" + String.valueOf(idExpediente);
	}
	
	@RequestMapping("/download-doc/{flag}/{idArchivo}")
	public String downloadDoc(@PathVariable("idArchivo") long idArchivo, @PathVariable("flag") String flag, HttpServletResponse response) {
		Archivo arch = archivoService.downloadDocumento(idArchivo);
		String mode = flag.equals("p") ? "inline" : "attachment";
		try {
			response.setHeader("Content-Disposition", mode + ";filename=" + arch.getFullnombre());
			OutputStream out = response.getOutputStream();
			response.setContentType(arch.getTipo());
			IOUtils.copy(new ByteArrayInputStream(arch.getData()), out);
			out.flush();
			out.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		return null;
	}
	
	@RequestMapping("/remove-doc/{idExpediente}/{idArchivo}")
	public String removeDoc(@PathVariable("idArchivo") long idArchivo, @PathVariable("idExpediente") long idExpediente) {
		String str_aux = this.archivoService.getArchivo(idArchivo).getNombre().toLowerCase().trim();
		this.archivoService.removeDocumento(idArchivo);
		if(str_aux.equals("solicitud")) {
			this.expedienteService.removeSolicitud(idExpediente);
		}
		return "redirect:/admin/exp-doc/" + String.valueOf(idExpediente);
	}
	
	@RequestMapping(value="/zip-doc/{idExpediente}", produces="application/zip")
	public String zipFiles(HttpServletResponse response, @PathVariable("idExpediente") long idExpediente) throws IOException {
		
	    response.setStatus(HttpServletResponse.SC_OK);
	    response.addHeader("Content-Disposition", "attachment; filename=archivos.zip");
	
	    ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
	
	    List<Archivo> files = this.archivoService.listarDocumentos(idExpediente);
	
	    for (Archivo file : files) {
	    	String fileName = file.getFullnombre();
	    	try {
	    		 zipOutputStream.putNextEntry(new ZipEntry("[" + file.getNombre() + "]-" + fileName));
			} catch (ZipException e) {
				int count = 2;
				String[] ls_aux = fileName.split("\\.");
				String str_aux = "";
				for (int i = 1; i < ls_aux.length; i++) {
					str_aux = str_aux + "." + ls_aux[i];
				}
				System.out.println(str_aux);
				while(true) {
					try {
						zipOutputStream.putNextEntry(new ZipEntry("[" + file.getNombre() + "]-" + ls_aux[0] + "(" + count + ")" + str_aux));
						break;
					} catch (ZipException e2) {
						count++;
					}
				}
				
			}
	    	
	        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(file.getData());
	
	        IOUtils.copy(fileInputStream, zipOutputStream);
	
	        fileInputStream.close();
	        zipOutputStream.closeEntry();
	    }    
	
	    zipOutputStream.close();
	    
	    return null;
	}
	
	// APPLICATION ROUTING
	//
	//
	
	@PostMapping("/gen-solicitud")
	public String genSolicitud(@RequestParam("name") String name, @RequestParam("last_name") String last_name, @RequestParam("doc") String doc,
			@RequestParam("categoria_actual") String categoria_actual, @RequestParam("categoria_nueva") String categoria_nueva,
			@RequestParam("domicilio") String domicilio, HttpServletResponse response, @ModelAttribute("usuario") Usuario usuario) {
		
		InputStream input = this.archivoService.genSolicitud(name, last_name, doc, categoria_actual, categoria_nueva, domicilio, usuario.getIdusuario());
		
		try {
			response.setHeader("Content-Disposition", "attachment;filename=solicitud.pdf");
			OutputStream out = response.getOutputStream();
			response.setContentType("application/pdf");
			IOUtils.copy(input, out);
			out.flush();
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
		return null;
	}
	
	@PostMapping("/save-doc-gen")
	public String saveDocGen(@RequestParam("file") MultipartFile file, @RequestParam("id_expediente") long id_expediente, RedirectAttributes redir) {
		int result = this.archivoService.saveSolicitud(file, id_expediente);
		if(result == 1) {
			redir.addFlashAttribute("acierto2", "Se subió la solicitud correctamente.");
		} else if(result == 0) {
			redir.addFlashAttribute("acierto2", "Se subió la solicitud correctamente. Se ha sobreescrito el archivo.");
		} else {
			redir.addFlashAttribute("acierto2", "Error inesperado.");
		}
		
		return "redirect:/admin/solicitud";
	}
}
