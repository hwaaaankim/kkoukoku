package com.dev.KKoukoku.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.KKoukoku.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Optional<Member> findByUsername(String username);
	
}
