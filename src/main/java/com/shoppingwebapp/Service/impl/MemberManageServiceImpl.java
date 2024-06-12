package com.shoppingwebapp.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoppingwebapp.Dao.MemberRepository;
import com.shoppingwebapp.Model.Member;
import com.shoppingwebapp.Service.MemberManageService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class MemberManageServiceImpl implements MemberManageService {

    @PersistenceContext
    private EntityManager entityManager;

    MemberRepository memberRepository;

    public MemberManageServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

// 	@Override
// 	public List<Member> findBySearch(String searchText) {
// 		System.out.println("findBySearch()");
// 		return MemberManageRepository.findBySearch(searchText);
// 	}

    @Override
    public List<Member> findAll() {
        return (List<Member>) memberRepository.findAll();
    }

    @Override
    public Member save(Member bean) {
        return memberRepository.save(bean);
    }

    @Override
    public Member update(Member bean) {
        return memberRepository.save(bean);
    }

    @Override
    public void deleteById(Integer id) {
        memberRepository.deleteById(id);
    }

// 	@Override
// 	public List<Member> findByPermission(boolean isAdmin) {
// 		return memberRepository.findByPermission(isAdmin);
// 	}

    @Override
    public Member findById(int id) {
        Optional<Member> result = memberRepository.findById(id);
        Member member = null;
        if(result.isPresent()) {
            member = result.get();
        }else{
            throw new RuntimeException("not found");
        }
        return member;
    }

   @Override
   public List<Member> findBySearch(String searchText) {
       // TODO Auto-generated method stub
       return null;
   }

   @Override
   public List<Member> findByPermission(boolean isAdmin) {
       // TODO Auto-generated method stub
       return null;
   }

}
