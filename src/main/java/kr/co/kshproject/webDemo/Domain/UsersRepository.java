package kr.co.kshproject.webDemo.Domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<Users,String> {
    //user 여러개 조회 (관리자 계정: 유저리스트 조회)

    List<Users> findAll();

    //user 특정 유저 조회 (관리자 계정: 유저리스트 조회
     /*1234567898910
    Users findAllBy(String id);

    // 유저 등록 (회원가입)
    Users save(Users user);
    //user 로그인
  Users login(Users user);

    //이메일 찾기
    Users findByEmail();

    //비밀번호 찾기
    Users findByPassword();

    */
}
