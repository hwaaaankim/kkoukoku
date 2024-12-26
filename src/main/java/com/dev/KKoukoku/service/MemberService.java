package com.dev.KKoukoku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.KKoukoku.model.Member;
import com.dev.KKoukoku.repository.MemberRepository;

@Service
public class MemberService {

	private final PasswordEncoder passwordEncoder;

	public MemberService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	private MemberRepository memberRepository;

	public Member save(Member member) {
		String encodedPassword = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodedPassword);
		member.setRole("ROLE_ADMIN");
		return memberRepository.save(member);

	}
}
