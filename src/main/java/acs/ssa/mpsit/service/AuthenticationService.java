package acs.ssa.mpsit.service;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import acs.ssa.mpsit.dto.User;
import acs.ssa.mpsit.dto.UserCredentials;
import acs.ssa.mpsit.model.UserEntity;
import acs.ssa.mpsit.repository.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private Mapper mapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	@Qualifier(value = "authenticationManager")
	private AuthenticationManager authManager;

	public User login(UserCredentials userCredentials) {
		if(StringUtils.isEmpty(userCredentials.getUsername()) || StringUtils.isEmpty(userCredentials.getPassword())) {
			throw new AuthorizationServiceException("Invalid credentials");
		}
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword());
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		notificationService.addNotification("User " + userCredentials.getUsername() + " logged in successfully ");

		return mapper.map(userRepository.findByUsername(userCredentials.getUsername()), User.class);
	}

	public User register(User user) {
		UserEntity userEntity = mapper.map(user, UserEntity.class);
		notificationService.addNotification("User " + user.getUsername() + " has been registered successfully ");
		return mapper.map(userRepository.save(userEntity), User.class);
	}
}
