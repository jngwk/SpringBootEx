package com.conan.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.conan.demo.domain.Todo;
import com.conan.demo.dto.PageRequestDTO;
import com.conan.demo.dto.PageResponseDTO;
import com.conan.demo.dto.TodoDTO;
import com.conan.demo.repository.TodoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoService {
    // 자동 주입 대상은 final로 설정
    private final ModelMapper modelMapper;
    private final TodoRepository todoRepository;

    public Integer register(TodoDTO TodoDTO) {
        log.info("..............");
        Todo todo = modelMapper.map(TodoDTO, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo.getTno();
    }

    public TodoDTO get(Integer tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
        return dto;
    }

    public void modify(TodoDTO todoDTO) {
        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());
        Todo todo = result.orElseThrow();
        todo.setTitle(todoDTO.getTitle());
        todo.setDueDate(todoDTO.getDueDate());
        todo.setComplete(todoDTO.isComplete());
        todoRepository.save(todo);
    }

    public void remove(Integer tno) {
        todoRepository.deleteById(tno);
    }

    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1, // 1페이지가 0
                pageRequestDTO.getSize(),
                Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(pageable);
        List<TodoDTO> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
        long totalCount = result.getTotalElements();
        PageResponseDTO<TodoDTO> responseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();
        return responseDTO;
    }
}
