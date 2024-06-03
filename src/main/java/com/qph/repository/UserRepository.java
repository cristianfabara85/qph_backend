package com.qph.repository;

import com.qph.entities.Usuario;
import com.qph.entities.UsuarioVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Usuario,Long> {

    @Query(value = "SELECT usuarioId,usuario, password, tipo FROM public.usuarios where usuario=:nombreUsuario", nativeQuery = true)
    UsuarioVO findUserById(String nombreUsuario);

}
