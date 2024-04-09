package br.com.ekan.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ekan.teste.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);
}
