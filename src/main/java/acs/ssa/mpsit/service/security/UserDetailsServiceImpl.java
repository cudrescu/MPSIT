package acs.ssa.mpsit.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import acs.ssa.mpsit.model.UserEntity;
import acs.ssa.mpsit.repository.UserRepository;

@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user == null) {
            throw new AuthorizationServiceException("The user with name " + username + " was not found");
        }

        return new User(user.getUsername(), user.getPassword(), new ManagedList<GrantedAuthority>());
    }
}
