/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventario.ProyectoSENA.Controlador;

import com.inventario.ProyectoSENA.Implementacion.UsuarioImp;
import com.inventario.ProyectoSENA.Modelo.LoginUsuario;
import com.inventario.ProyectoSENA.Modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author johnny
 */

@RestController
@RequestMapping("usuario")

public class UsuarioController {

    @Autowired
    UsuarioImp usuarioImp;


    @GetMapping("/usuarios")
    public ResponseEntity<?> getUsuarios(){
        return ResponseEntity.ok(usuarioImp.getUsuarios());
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario){
        if(this.usuarioImp.encontrarUsuarioCorreo(usuario.getCorreo()))
            return ResponseEntity.ok("Usuario Ya se encuetra registrado con el mismo correo " + usuario);
        else{
            this.usuarioImp.guardarUsuario(usuario);
            return ResponseEntity.ok("Usuario registrado  " + usuario);
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuario) {
        if (this.usuarioImp.encontrarUsuario(usuario.getId())){
            this.usuarioImp.guardarUsuario(usuario);
            return ResponseEntity.ok("Usuario modificado");
        }else{
            return ResponseEntity.ok("Usuario no encontrado");
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUsuario loginUsuario){
        if(this.usuarioImp.loginUsuario(loginUsuario) != null)
            return new ResponseEntity("Logueado", HttpStatus.OK);
        return new ResponseEntity("No logueado", HttpStatus.NOT_ACCEPTABLE);
    }
}