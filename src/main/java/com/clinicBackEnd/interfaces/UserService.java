package com.clinicBackEnd.interfaces;

import jakarta.validation.Valid;
import com.clinicBackEnd.entities.UserData;
import com.clinicBackEnd.exceptions.UserAlreadyExistException;

public interface UserService {

	void register(@Valid UserData userData)  throws UserAlreadyExistException ;

}
