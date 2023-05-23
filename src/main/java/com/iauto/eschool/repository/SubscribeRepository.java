package com.iauto.eschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.iauto.eschool.entity.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long>, JpaSpecificationExecutor<Subscribe> {

}
