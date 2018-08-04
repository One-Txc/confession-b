package com.didispace.repository;

import com.didispace.domain.MainConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: txc
 * @date: 18-7-28 下午6:17
 */
public interface MainConfigRepository extends JpaRepository<MainConfig, Long> {
    MainConfig findByTitle(String title);
}
