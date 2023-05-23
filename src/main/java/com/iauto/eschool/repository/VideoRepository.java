package com.iauto.eschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.iauto.eschool.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> , JpaSpecificationExecutor<Video>{
	

}
