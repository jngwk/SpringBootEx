package com.conan.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conan.demo.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    // Todo Entity의 PK 타입 Integer를 인자로 전달
}
