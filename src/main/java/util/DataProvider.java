package util;

import com.github.javafaker.Faker;
import dtos.Usuario;
import dtos.UsuarioIncompleto;
import dtos.UsuarioLogin;

import java.util.List;

public class DataProvider {
    Faker faker= new Faker();

    public Usuario getAdmin(){
        return new Usuario("admin","admin", List.of("admin"));
    }

    public Usuario getUser(){
        return new Usuario(getUsername(),getPassword(),List.of("user"));
    }

    public Usuario getUserWithOutUsername(){
        return new Usuario(null,getPassword(),List.of("user"));
    }

    public UsuarioIncompleto getUsuarioIncompleto(){
        return new UsuarioIncompleto(getUsername());
    }

    public String getUsername(){
        return faker.name().username();
    }

    public String getPassword(){
        return faker.internet().password();
    }

    public UsuarioLogin getAdminCredential(){
        return new UsuarioLogin("admin","admin");
    }

    public UsuarioLogin getCredential(Usuario usuario) {
        if( usuario != null ) {
            return new UsuarioLogin(usuario.usuario(), usuario.clave());
        }
        return getCredential(getUser());
    }
}
