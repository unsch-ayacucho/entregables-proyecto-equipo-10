package pe.edu.unsch.controller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
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
	public String home(HttpSession session, Model model) {
		try {
			if(((Usuario) session.getAttribute("usuario")).getUsuario() == null || ((Usuario) session.getAttribute("usuario")).getUsuario().equals("")) {
				return "redirect:/login";
			} else {
				model.addAttribute("title", "Dashboard");
				return "views/admin/home/index";
			}
		} catch(NullPointerException npe) {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/doc")
	public String doc(HttpSession session, Model model) {
		try {
			if(((Usuario) session.getAttribute("usuario")).getUsuario() == null || ((Usuario) session.getAttribute("usuario")).getUsuario().equals("")) {
				return "redirect:/login";
			} else {
				model.addAttribute("title", "Documentos");
				model.addAttribute("docs",
				this.archivoService.listarDocumentos( ((Usuario) session.getAttribute("usuario")).getIdusuario() )  );
				return "views/admin/home/documentos";
				
			}
		} catch(NullPointerException npe) {
			return "redirect:/login";
		}
	}
	
	@GetMapping("/usuario")
	public String user(HttpSession session, Model model) {
		try {
			if(((Usuario) session.getAttribute("usuario")).getUsuario() == null || ((Usuario) session.getAttribute("usuario")).getUsuario().equals("")) {
				return "redirect:/login";
			} else {
				model.addAttribute("title", "Usuario");
				model.addAttribute("user", this.usuarioService.datosUsuario( (long) ((Usuario) session.getAttribute("usuario")).getIdusuario() )  );
				return "views/admin/home/usuario";
			}
		} catch(NullPointerException npe) {
			return "redirect:/login";
		}
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redir) {
		session.invalidate();
		redir.addFlashAttribute("error", "Ha cerrado sesión correctamente.");
		return "redirect:/login";
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
	
	@PostMapping("/save-doc")
	public String saveDoc(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("name") String name) {
		this.archivoService.saveDocumento(file, name);
		return "redirect:/admin/doc";
	}
	
	@RequestMapping("/remove-doc/{idArchivo}")
	public String removeDoc(@PathVariable("idArchivo") long idArchivo, HttpServletResponse response) {
		
		this.archivoService.removeDocumento(idArchivo);
		
		return "redirect:/admin/doc";
	}
	


	@RequestMapping(value="/zip-doc", produces="application/zip")
	public String zipFiles(HttpServletResponse response, HttpSession session) throws IOException {
	
	    //setting headers  
	    response.setStatus(HttpServletResponse.SC_OK);
	    response.addHeader("Content-Disposition", "attachment; filename=archivos.zip");
	
	    ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
	
	    // create a list to add files to be zipped
	    List<Archivo> files = this.archivoService.listarDocumentos( ((Usuario) session.getAttribute("usuario")).getIdusuario() );
	
	    // package files
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



}
