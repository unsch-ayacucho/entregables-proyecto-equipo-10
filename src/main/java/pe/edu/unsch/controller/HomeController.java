package pe.edu.unsch.controller;

import java.util.List;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import pe.edu.unsch.hibernate.*;

@Controller
@RequestMapping("/")
public class HomeController {

  @RequestMapping(method = RequestMethod.GET)
  public String index(ModelMap model){
    String str = "";
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();

    List usuarios = session.createQuery("FROM Usuario").list();
    for (Iterator iterator = usuarios.iterator(); iterator.hasNext();){
      Usuario usuario = (Usuario) iterator.next();
      str = str + usuario.getNombre();
    }

    tx.commit();
    session.close();

    model.addAttribute("title", "Home");
    model.addAttribute("username", str);
    return "admin/home/index";
  }
}
