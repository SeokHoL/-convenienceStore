package com.saehimit.shproject.repository;

import com.saehimit.shproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUserId(String userId);

    @Query("SELECT u FROM User u WHERE " +
            "(:userId IS NULL OR :userId = '' OR u.userId = :userId) AND " +
            "(:username IS NULL OR :username = '' OR u.username LIKE %:username%) AND " +
            "(:branch IS NULL OR :branch = '' OR u.branch = :branch)")
    List<User> findUsersByCriteria(@Param("userId") String userId,
                                   @Param("username") String username,
                                   @Param("branch") String branch);

    Optional<User> findByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber); // 핸드폰 번호 중복 여부 확인




}
