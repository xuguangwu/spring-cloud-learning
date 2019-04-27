package com.clear.repository;

import com.clear.entity.Entity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EntityRepository extends ElasticsearchRepository<Entity, Long> {

}
