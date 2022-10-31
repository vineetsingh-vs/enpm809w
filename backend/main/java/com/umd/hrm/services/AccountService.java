package com.umd.hrm.services;

import com.umd.hrm.models.Account;
import com.umd.hrm.repositories.AccountRepository;
import com.umd.hrm.security.CustomException;
import com.umd.hrm.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final static String USER_FOUND_MSG =
            "user with email %s already exists";

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;



    public ResponseEntity<Object> registerAccount(Account account)  throws UsernameNotFoundException {
       Optional<Account> validAccount = accountRepository.findByUserName(account.getUsername());
       if(validAccount.isPresent()) {
           return ResponseEntity.status(500).body("user already exists!");
       }
       account.setPassword(passwordEncoder.encode(account.getPassword()));
       accountRepository.save(account);
        return ResponseEntity.status(200).body("successfully created!");
    }

    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, "Admin");
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
