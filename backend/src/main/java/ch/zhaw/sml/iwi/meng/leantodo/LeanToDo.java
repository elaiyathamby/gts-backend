package ch.zhaw.sml.iwi.meng.leantodo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ch.zhaw.sml.iwi.meng.leantodo.entity.CategoryEnum;
import ch.zhaw.sml.iwi.meng.leantodo.entity.Role;
import ch.zhaw.sml.iwi.meng.leantodo.entity.RoleRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.StatusEnum;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDo;
import ch.zhaw.sml.iwi.meng.leantodo.entity.ToDoRepository;
import ch.zhaw.sml.iwi.meng.leantodo.entity.User;
import ch.zhaw.sml.iwi.meng.leantodo.entity.UserRepository;

@SpringBootApplication
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class LeanToDo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LeanToDo.class, args);

    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ToDoRepository toDoRepository;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // This is only really relevant for development, where we have different servers
                        // for frontend and backend
                        .allowedOrigins("http://localhost:8100").allowedMethods("GET", "PUT", "POST", "DELETE")
                        // AllowCredentials is necessary, as it sets 'Access-Control-Allow-Credentials'.
                        // Otherwise Angular's HttpClient will not pass the Cookie back.
                        .allowCredentials(true);
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        User u = new User();
        u.setLoginName("user");
        u.setPasswordHash(new BCryptPasswordEncoder().encode("user"));
        Role r = new Role();
        r.setRoleName("ROLE_USER");
        roleRepository.save(r);
        u.getRoles().add(r);
        userRepository.save(u);

        ToDo toDo = new ToDo();

        toDo.setTitle("note TodayFinish This app");
        toDo.setOwner("user");
        toDo.setCategory(CategoryEnum.PRIVATE);
        toDo.setStatus(StatusEnum.OPEN);
        toDo.setDescription("bibibu");
        toDo.setDue(new Date((new Date()).getTime()+2*1000*24*3600));
        toDo.setOpen(new Date((new Date()).getTime()-5*1000*24*3600));
        toDoRepository.save(toDo);

        toDo = new ToDo(); 
        toDo.setTitle("yesterdayReply to student");
        toDo.setOwner("user");
        toDo.setCategory(CategoryEnum.PRIVATE);
        toDo.setStatus(StatusEnum.OPEN);
        toDo.setDescription("bibibu");
        toDo.setOpen(new Date((new Date()).getTime()-5*1000*24*3600));
        toDo.setDue(new Date((new Date()).getTime()-1000*24*3600));
        toDoRepository.save(toDo);
        
        CategoryEnum category[] = {CategoryEnum.PRIVATE,CategoryEnum.BUSINESS}; 
        StatusEnum status[] = {StatusEnum.OPEN,StatusEnum.INPROGRESS}; 

        for(int i = 1;i<=10;i++){
            toDo = new ToDo(); 
            toDo.setTitle("Finish This app "+i);
            toDo.setOwner("user");
            toDo.setCategory(category[i%2]);
            toDo.setStatus(status[i%2]);
            toDo.setDescription("bibibu");
            toDo.setDue(new Date((new Date()).getTime()));
            toDo.setOpen(new Date((new Date()).getTime()-i*1000*24*3600));
            toDoRepository.save(toDo);
        }
    }
}
