package pe.edu.unsch.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import pe.edu.unsch.entities.Archivo;
import pe.edu.unsch.entities.Docente;

@Repository("archivoDao")
public class ArchivoDaoImpl implements ArchivoDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Archivo> listarDocumentos(long l) {
		return (List<Archivo>) entityManager
				.createQuery("select new Archivo(arc.idarchivo as idarchivo, arc.nombre as nombre, arc.fullnombre as fullnombre, arc.tipo as tipo, arc.data as data, arc.fecha as fecha) from Archivo arc inner join arc.docente doc inner join doc.usuario usu where usu.idusuario = :idUsuario", Archivo.class)
				.setParameter("idUsuario", l)
				.getResultList();
	}
	
	@Override
	public Archivo downloadDocumento(long l) {
		return (Archivo) entityManager.find(Archivo.class, l);
	}

	@Override
	public void saveDocumento(MultipartFile data, String nombre) {
		Docente doc = entityManager.getReference(Docente.class, (long) 1);
		
		System.out.println(data.getContentType());
		try {
			entityManager.persist(new Archivo(doc, nombre, data.getOriginalFilename(), data.getContentType(), data.getBytes(), new Date()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeDocumento(long l) {
		Archivo arch = entityManager.getReference(Archivo.class, (long) l);
		entityManager.remove(arch);
	}

	@Override
	public InputStream genSolicitud(String name, String last_name, String doc, String categoria_actual, String categoria_nueva, String domicilio) {
		try {
			Document document = new Document(PageSize.A4, 65, 65, 60, 60);
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			PdfWriter.getInstance(document, out);
			
			document.open();
			
			Font font_t = new Font(Font.getFamily("HELVETICA"), 12, Font.BOLD | Font.UNDERLINE);
			Font font_p = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
			Font font_u = new Font(Font.getFamily("HELVETICA"), 11, Font.UNDERLINE);
			Font font_a = new Font(Font.getFamily("HELVETICA"), 11, Font.BOLD);
			
			Paragraph pt = new Paragraph("SOLICITA SER PROMOVIDO A LA CATEGORÍA DE PROFESOR " + categoria_nueva.toUpperCase() + " DE LA UNSCH", font_t);
			pt.setAlignment(Element.ALIGN_CENTER);
			pt.setLeading(pt.getLeading() - 2);
			pt.setIndentationLeft(document.getPageSize().getWidth()/3.5f);
			
			Paragraph p1 = new Paragraph("SEÑOR RECTOR DE LA UNIVERSIDAD NACIONAL DE SAN CRISTÓBAL DE HUAMANGA", font_p);
			p1.setAlignment(Element.ALIGN_LEFT);
			p1.setLeading(p1.getLeading() - 4);
			p1.setSpacingAfter(p1.getSpacingAfter() + 10);
			p1.setSpacingBefore(p1.getSpacingBefore() + 12);
			
			String[] splitStr = name.trim().split("\\s+");
			String name_complete = "";
			
			for (int i = 0; i < splitStr.length; i++) {
				if(i < splitStr.length - 1) {
					name_complete = name_complete + StringUtils.capitalize(splitStr[i]) + " ";
				} else {
					name_complete = name_complete + StringUtils.capitalize(splitStr[i]);
				}
			}
			
			Paragraph p2 = new Paragraph(last_name.toUpperCase() + ", " + name_complete + ", docente nombrado en la categoría de profesor " + categoria_actual.toLowerCase() + " de la UNSCH, "
					+ "Facultad de Ciencias de la Educación, adscrito al Departamento Académico de Lenguas y Literatura"
					+ ", domiciliado en " + domicilio + " e identificado con DNI No. " + doc + ", ante Ud. con todo respeto me " +
					"prescrito y expando:", font_p);
			
			p2.setAlignment(Element.ALIGN_JUSTIFIED);
			p2.setIndentationLeft(document.getPageSize().getWidth()/6f);
			p2.setLeading(p2.getLeading() - 4);
			
			Paragraph p3 = new Paragraph("Que, al amparo del Art. 209 , inciso b) del Estatuto Reformado, y Art. 382', inciso b) del" + 
					" Reglamento General de la UNSCH tengo el derecho de ser promovido de acuerdo a Ley.", font_p);
			p3.setAlignment(Element.ALIGN_JUSTIFIED);
			p3.setLeading(p3.getLeading() - 4);
			p3.setSpacingBefore(p3.getSpacingBefore() + 10);
			
			Paragraph p4 = new Paragraph("Que, cumplo con los requisitos exigidos por el Art. 166 del Estatuto Reformado de la UNSCH y" + 
					" el Art. 252º del Reglamento General de la UNSCH para ser promovido; por lo que solicito a su" + 
					" digna autoridad me promueva a la categoría de profesor " + categoria_nueva.toLowerCase() + " en la Universidad" + 
					" Nacional de San Cristóbal de Huamanga; para lo cual adjunto el recibo de Tesorería, requisitos" + 
					" y otros documentos de acuerdo a la tabla de evaluación para promoción de docentes aprobado" + 
					" por Resolución del Consejo Universitario N' 152-2011- UNSCH-CU.", font_p);
			p4.setAlignment(Element.ALIGN_JUSTIFIED);
			p4.setLeading(p4.getLeading() - 4);
			p4.setSpacingBefore(p4.getSpacingBefore() + 10);
			
			Paragraph p5 = new Paragraph("POR LO EXPUESTO:", font_u);
			p5.setAlignment(Element.ALIGN_LEFT);
			p5.setSpacingAfter(p5.getSpacingAfter() + 8);
			p5.setSpacingBefore(p5.getSpacingBefore() + 12);
			
			Paragraph p6 = new Paragraph("Suplico a Ud., señor Rector, dar curso a mi pedido. Es justicia que espero alcanzar.", font_p);
			p6.setAlignment(Element.ALIGN_LEFT);
			p6.setSpacingAfter(p6.getSpacingAfter() + 32);
			
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            
            String formated_date = new SimpleDateFormat("dd 'de' MMMM 'de' YYYY", Locale.forLanguageTag("es-ES")).format(cal.getTime());
			
			Paragraph p7 = new Paragraph("Ayacucho, " +  formated_date, font_p);
			p7.setAlignment(Element.ALIGN_CENTER);
			p7.setSpacingAfter(p7.getSpacingAfter() + 48);
			
			String strAux = name + " " + last_name;
			
			String[] splitStr2 = strAux.trim().split("\\s+");
			String full_name = "";
			
			for (int i = 0; i < splitStr2.length; i++) {
				if(i < splitStr2.length - 1) {
					full_name = full_name + StringUtils.capitalize(splitStr2[i]) + " ";
				} else {
					full_name = full_name + StringUtils.capitalize(splitStr2[i]);
				}
			}
						
			Paragraph p8 = new Paragraph(full_name, font_p);
			p8.setAlignment(Element.ALIGN_CENTER);
			p8.setSpacingAfter(p8.getSpacingAfter() + 32);
			p8.setIndentationLeft(document.getPageSize().getWidth()/8f);
			
			Paragraph p9 = new Paragraph("ANEXOS:", font_a);
			p9.setAlignment(Element.ALIGN_LEFT);
			p9.setSpacingAfter(p9.getSpacingAfter() + 4);
			
			Paragraph p10 = new Paragraph("Anexo 1: Requisitos", font_p);
			p10.setAlignment(Element.ALIGN_LEFT);
			p10.setSpacingAfter(p10.getSpacingAfter() + 4);
			
			Paragraph p11 = new Paragraph("Anexo 2: Expediente para promoción", font_p);
			p11.setAlignment(Element.ALIGN_LEFT);
			
			document.add(pt);
			document.add(p1);
			document.add(p2);
			document.add(p3);
			document.add(p4);
			document.add(p5);
			document.add(p6);
			document.add(p7);
			document.add(p8);
			document.add(p9);
			document.add(p10);
			document.add(p11);
			
			document.close();
			
			InputStream input = new ByteArrayInputStream(out.toByteArray());
			
			return input;
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	}

}
