package com.didispace.repository;

import com.didispace.domain.PopupConfig;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: txc
 * @date: 18-7-28 下午9:45
 */
public interface PopupConfigRespository extends JpaRepository<PopupConfig, Long> {
    List<PopupConfig> findAllByMainConfigIdAndAndGroupType(Long mainConfigId, String groupType, Sort sort);

}
