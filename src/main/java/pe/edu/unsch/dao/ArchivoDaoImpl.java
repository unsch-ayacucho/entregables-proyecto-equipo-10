package pe.edu.unsch.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
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
	public void genSolicitud(String name) {
		try {
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\ga.pdf"));
			
			document.open();
			Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
			
			Font font2 = FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK);

			
			
			Chunk title1 = new Chunk("SOLICITA SER PROMOVIDO A LA CATEGORÍA DE", font);
			title1.setUnderline(0.5f, -2f);
			Chunk title2 = new Chunk("\nPROFESOR PRINCIPAL DE LA UNSCH", font);
			title2.setUnderline(0.5f, -2f);
			
			PdfContentByte cb = writer.getDirectContent();
			ColumnText ct = new ColumnText(cb);
			
			System.out.println(document.getPageSize().getWidth());
			
			System.out.println(document.getPageSize().getHeight());
			
			Phrase titlep1 = new Phrase(title1);
			ct.setSimpleColumn(titlep1, document.getPageSize().getWidth()/3.5f, document.getPageSize().getHeight()*0.94f, 580, 317, 15, Element.ALIGN_CENTER);
			Phrase titlep2 = new Phrase(title2);
			ct.setSimpleColumn(titlep2, document.getPageSize().getWidth()/3.5f, document.getPageSize().getHeight()*0.94f, 580, 317, 15, Element.ALIGN_CENTER);
			ct.go();
			
			Paragraph p1 = new Paragraph("SEÑOR RECTOR DE LA UNIVERSIDAD NACIONAL DE SAN CRISTÓBAL DE HUAMANGA", font2);
			p1.setAlignment(Element.ALIGN_JUSTIFIED);
			p1.setLeading(p1.getLeading() - 4);
			p1.setSpacingAfter(p1.getSpacingAfter() + 10);
			p1.setSpacingBefore(p1.getSpacingBefore() + 100);
			
			
			Paragraph p2 = new Paragraph("PALOMINO ROJAS, Víctor Gedeón, docente nombrado en la categoría de profesor asociado de la UNSCH, "
					+ "Facultad de Ciencias de la Educación, adscrito al Departamento Académico de Lenguas y Literatura", font2);
			
			p2.setAlignment(Element.ALIGN_JUSTIFIED);
			p2.setIndentationLeft(document.getPageSize().getWidth()/4.5f);
			p2.setLeading(p2.getLeading() - 4);
			
			document.add(p1);
			document.add(p2);
			
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

}
