package pe.edu.unsch.controller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import pe.edu.unsch.service.UsuarioService;
import pe.edu.unsch.entities.Archivo;
import pe.edu.unsch.entities.Usuario;

@Controller
@RequestMapping("/admin")
public class HomeController {
	@Autowired
	private ArchivoService archivoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Dashboard");
		return "views/admin/home/index";
	}
	
	@GetMapping("/convocatoria")
	public String conv(HttpSession session, Model model) {
		model.addAttribute("title", "Convocatoria");
		return "views/admin/home/convocatoria";
	}
	
	@GetMapping("/doc")
	public String doc(Model model, @ModelAttribute("usuario") Usuario usuario) {
		model.addAttribute("title", "Documentos");
		model.addAttribute("docs", this.archivoService.listarDocumentos( usuario.getIdusuario()) );
		return "views/admin/home/documentos";
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
		return "views/admin/home/solicitud";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redir) {
		session.invalidate();
		redir.addFlashAttribute("error", "Ha cerrado sesión correctamente.");
		return "redirect:/login";
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
	
	// DOCUMENT ROUTING
	//
	//
	
	@PostMapping("/save-doc")
	public String saveDoc(@RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
		if(!name.trim().toLowerCase().equals("solicitud")) {
			this.archivoService.saveDocumento(file, name);
		}
		return "redirect:/admin/doc";
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
	
	@RequestMapping("/remove-doc/{idArchivo}")
	public String removeDoc(@PathVariable("idArchivo") long idArchivo) {
		this.archivoService.removeDocumento(idArchivo);
		return "redirect:/admin/doc";
	}
	
	@RequestMapping(value="/zip-doc", produces="application/zip")
	public String zipFiles(HttpServletResponse response, @ModelAttribute("usuario") Usuario usuario) throws IOException {
		
	    response.setStatus(HttpServletResponse.SC_OK);
	    response.addHeader("Content-Disposition", "attachment; filename=archivos.zip");
	
	    ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
	
	    List<Archivo> files = this.archivoService.listarDocumentos(usuario.getIdusuario());
	
	    for (Archivo file : files) {
	    	
	        zipOutputStream.putNextEntry(new ZipEntry(file.getFullnombre()));
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
			@RequestParam("domicilio") String domicilio, HttpServletResponse response) {
		
		InputStream input = this.archivoService.genSolicitud(name, last_name, doc, categoria_actual, categoria_nueva, domicilio);
		
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
	public String saveDocGen(@RequestParam("file") MultipartFile file, @RequestParam("name") String name, RedirectAttributes redir) {
		this.archivoService.saveDocumento(file, name);
		redir.addFlashAttribute("acierto2", "Se subió la solicitud correctamente.");
		return "redirect:/admin/solicitud";
	}
}
