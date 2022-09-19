package es.gaia.controller;

import java.util.Collection;
import java.util.stream.Collectors;
import javax.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.libreria.gaia.service.GaiaServiceImpl;
import es.libreria.gaia.user.GaiaUserRepresentation;

/**
 * Este controlador se utiliza para acceder a la información del perfil del usuario, a los roles del
 * usuario que se autentica y a todos los roles de la aplicación.
 * 
 * @author ACING DIM XLIV PROYECTO ATLAS
 * @version v1.0.0
 * 
 */


@RestController
@RequestMapping("/gaia")
@RolesAllowed("user") // A nivel de clase se aplicara a todos los metodos.
@CrossOrigin
public class GaiaController {

  Collection<String> roles;

  public GaiaServiceImpl servicioGaia;

  public GaiaController(@Autowired GaiaServiceImpl servicioGaia) {
    this.servicioGaia = servicioGaia;
    roles = servicioGaia.getRolesRealm();
    System.out.println("--> Inyectado Bean GaiaServiceImpl en constructor GaiaController");
    System.out.println("--> Roles de la aplicacion: " + roles.toString());
    System.out.println("--> Usuarios con rol user: " + servicioGaia.getUsersWithRol("user").stream()
        .map(m -> m.getUsername()).collect(Collectors.toList()));
  }

  @GetMapping("/hello")
  public String hello() {
    return "HOLA!, has accedido al controlador Gaia";
  }

  @GetMapping("/roles-aplicacion")
  public ResponseEntity<Collection<String>> listOfRealmRoles() {
    System.out.println("--> Roles de la Aplicación: " + roles.toString());
    return new ResponseEntity<Collection<String>>(roles, HttpStatus.OK);
  }

  @GetMapping("/roles")
  public Collection<String> rolesOfCurrentUser() {
    System.out.println("--> Roles de " + servicioGaia.getCurrentUser().getUsername() + ": "
        + servicioGaia.getCurrentUserRoles());
    return servicioGaia.getCurrentUserRoles();
  }

  @GetMapping("/profile")
  public GaiaUserRepresentation profileOfCurrentUser() {
    System.out.println("--> Perfil de usuario " + servicioGaia.getCurrentUser().getUsername() + ": "
        + servicioGaia.getUserProfile().toString());
    return servicioGaia.getUserProfile();
  }
}
