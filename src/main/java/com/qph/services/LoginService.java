package com.qph.services;

import java.util.List;

import com.qph.entities.Usuario;
import com.qph.entities.LoginResponse;
import com.qph.entities.UsuarioVO;
import com.qph.repository.UserRepository;
import com.qph.util.Constantes;
import com.qph.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    public UserRepository userRepository;


    public LoginResponse login(Usuario usuario){
        String resultado="";
        String token="";
        String respuesta="";
        LoginResponse response = new LoginResponse();

        UsuarioVO userData=userRepository.findUserById(usuario.getUsuario());
        if(userData!=null){
            resultado=EncryptionUtil.decrypt(userData.getPassword(), Constantes.SECRET_KEY);
            if(resultado.equals(usuario.getPassword())){
                response.setMessage(Constantes.LOGIN_OK);
                response.setData(userData);
                response.setCode(Constantes.CODE_OK);
            } else {
                response.setMessage(Constantes.LOGIN_ERROR);
                response.setCode(Constantes.CODE_ERROR_LOGIN);
            }
        }else{
            response.setCode(Constantes.CODE_LOGIN_UNKNOWN);
            response.setMessage(Constantes.LOGIN_UNKNOWN);
        }

        return response;
    }

    public LoginResponse createUser(Usuario usuario) {
            LoginResponse login=new LoginResponse();
            try {
                String password= encriptarPassword(usuario.getPassword());
                usuario.setPassword(password);
                Usuario response=userRepository.save(usuario);
                login.setMessage("usuario creado con exito");
                login.setCode(200);
                login.setData(response);
                return login;
            }catch (Exception e){
                login.setMessage("error al crear usuario");
                login.setCode(200);
                login.setData(new Object());
                return login;
            }
    }

    public String encriptarPassword(String password){
        String encriptado="";
        String llave=Constantes.SECRET_KEY;
        String originalString = password;
        String encryptedString = EncryptionUtil.encrypt(originalString, llave) ;
        encriptado=encryptedString;
        return encriptado;
    }

    public List<Usuario> findUsers(){
        return userRepository.findAll();
    }

}
