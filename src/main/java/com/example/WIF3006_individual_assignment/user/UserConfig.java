//package com.example.WIF3006_individual_assignment.user;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//public class UserConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository repository) {
//        return args -> {
//            User mariam = new User(
//                    1L,
//                    "Mariam",
//                    "mariam@gmail.com",
//                    LocalDate.of(2000, Month.JANUARY, 5),
//                    "Stay Fit"
//            );
//
//            User alex = new User(
//                    1L,
//                    "Alex",
//                    "alex@gmail.com",
//                    LocalDate.of(2000, Month.JANUARY, 5),
//                    "Lose Weight"
//            );
//
//            repository.saveAll(
//                    List.of(mariam, alex)
//            );
//        };
//    }
//}
