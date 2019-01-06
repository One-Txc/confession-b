package com.didispace.repository;

import com.didispace.domain.AuthorizationCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: txc
 * @date: 19-1-6 下午7:35
 */
public interface AuthorizationCodeRepository extends JpaRepository<AuthorizationCode, String> {

}

