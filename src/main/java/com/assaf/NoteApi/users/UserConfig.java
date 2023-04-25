package com.assaf.NoteApi.users;//package com.assaf.SecondSpring.Users;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class UserConfig {
//
//    @Bean
//    @Autowired
//    CommandLineRunner commandLineRunner(UserRepository repository){
//        return args -> {
//            User user1 = new User(
//                    "Email1",
//                    "123"
//            );
//            User user2 = new User(
//                    "Email2",
//                    "123"
//            );
//
//            repository.saveAll(List.of(user1,user2));
//        };
//    }
//
//}
