package com.springboot_portfolio.mapper;

import com.springboot_portfolio.vo.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author 이승환
 * @since 2019-11-27
 */
public interface MemberMapper extends JpaRepository<MemberEntity, String> {

    Optional<MemberEntity> findByEmail(String MEMBER_EMAIL);

}
