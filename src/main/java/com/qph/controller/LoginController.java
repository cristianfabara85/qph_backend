package com.qph.controller;

import java.net.URI;
import java.util.List;

import com.qph.entities.LoginResponse;
import com.qph.entities.Usuario;
import com.qph.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.OPTIONS,RequestMethod.GET,
    RequestMethod.POST,RequestMethod.DELETE})
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> login (@RequestBody Usuario usuario){
        LoginResponse obj = loginService.login(usuario);

        try {
            return ResponseEntity.created(new URI("/api/qph/save"+obj.getCode())).body(obj);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<LoginResponse> createUser (@RequestBody Usuario usuario){
        LoginResponse obj = loginService.createUser(usuario);

        try {
            return ResponseEntity.created(new URI("/api/qph/save"+obj.getCode())).body(obj);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

    @GetMapping("/findall")
    @ResponseBody
    public ResponseEntity<List<Usuario> > findUsers (){
        List<Usuario> obj = loginService.findUsers();

        try {
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    };

}
