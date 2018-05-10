package pl.coderslab.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.springsecurity.model.UserAuthoritory;
import pl.coderslab.springsecurity.model.UserName;
import pl.coderslab.springsecurity.repository.UserAuthoritoryRepository;
import pl.coderslab.springsecurity.repository.UserNameRepository;

import javax.validation.Valid;

@Controller
public class homeController {
    @Autowired
    UserAuthoritoryRepository userAuthoritoryRepository;
    @Autowired
    UserNameRepository userNameRepository;
    @GetMapping("/")
    public String getRequest(){
        return "user";
    }
    @GetMapping("/admin")
    @ResponseBody
    public String adminRequest(){
        return "BYYYYCH";
    }
    @GetMapping("/home")
    @ResponseBody
    public String getHome(){
        return "Allah Akbar";
    }
    @GetMapping("/registerUser")
    public  String registerUser(Model model){
        model.addAttribute("user", new UserName());
        return "userReg";
    }
    @PostMapping("/registerUser")
    public String doRegister(@Valid UserName userName, BindingResult result){
        userNameRepository.save(userName);
        UserAuthoritory userAuth = new UserAuthoritory();
        userAuth.setUsername(userName.getUsername());
        userAuth.setAuthority("ROLE_USER");
        userAuthoritoryRepository.save(userAuth);
        return "redirect:login";
    }
    @GetMapping("/registerAdmin")
    public String registerAdmin(Model model){
        model.addAttribute("user", new UserName());
        return "adminReg";
    }
    @PostMapping("/registerAdmin")
    public String doRegisterAdmin(@Valid UserName userName, BindingResult result){
     userNameRepository.save(userName);
        UserAuthoritory userAuth = new UserAuthoritory();
        userAuth.setUsername(userName.getUsername());
        userAuth.setAuthority("ROLE_ADMIN");
        userAuthoritoryRepository.save(userAuth);
     return "redirect:login";
    }
}
