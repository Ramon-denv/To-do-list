package br.com.ramonmendes.todolist.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
      var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user !=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");
        }

      var senhHashred = BCrypt.withDefaults().hashToString(12, userModel.getSenha().toCharArray());

      userModel.setSenha(senhHashred);

      var userCreated =  this.userRepository.save(userModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
