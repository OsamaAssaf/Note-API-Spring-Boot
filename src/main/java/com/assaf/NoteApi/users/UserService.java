package com.assaf.NoteApi.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(UserModel userModel) {
        User user = new User();

        String email = userModel.getEmail().toLowerCase().trim();
        user.setEmail(email);
        user.setUsername(userModel.getUsername());

        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        user.setPassword(encodedPassword);

        Optional<User> userExists = userRepository.findUserByEmail(user.getEmail());
        if (userExists.isPresent()) throw new IllegalStateException("Email already in use.");

        return userRepository.save(user);

    }

    public User login(UserModel userModel) {
        String email = userModel.getEmail().toLowerCase().trim();

        Optional<User> user = userRepository.findUserByEmail(email);
        if(user.isEmpty()) throw new IllegalStateException("Couldn't find the user.");

        boolean isMatches = passwordEncoder.matches(userModel.getPassword(),user.get().getPassword());
        if(!isMatches) throw new IllegalStateException("Incorrect password.");
        return user.get();

    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
