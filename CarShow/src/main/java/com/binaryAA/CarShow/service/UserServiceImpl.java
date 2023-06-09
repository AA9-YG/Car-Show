package com.binaryAA.CarShow.service;

import com.binaryAA.CarShow.entity.User;
import com.binaryAA.CarShow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);

        org.springframework.security.core.userdetails.User
                .UserBuilder userbuilder = null;

        if (user.isPresent()) {
            User currentUser = user.get();
            userbuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userbuilder.password(currentUser.getPassword());
            userbuilder.roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException(username);
        }

        return userbuilder.build();
    }
}
