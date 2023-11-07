package dtos;

import java.util.Collection;

public record Usuario(String usuario, String clave, Collection<String> roles) {
}
