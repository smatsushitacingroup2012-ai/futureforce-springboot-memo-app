package com.lesson.memo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lesson.memo.model.Memo; 


public interface MemoRepository extends JpaRepository<Memo, Long> {
    
    //部分一致
	List<Memo> findByTitleContainingOrContentContainingOrderByPriorityAsc(String firstname, String secondname);
	//全件取得
	List<Memo> findAll();
	
	
}


