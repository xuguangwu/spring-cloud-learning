package com.clear.service.impl;


import com.clear.entity.Entity;
import com.clear.repository.EntityRepository;
import com.clear.service.CityESService;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityESServiceImpl implements CityESService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CityESServiceImpl.class);

	//默认分页大小
	int pageSize = 10;

	//默认当前分页
	int pageNumber = 0;

	/**
	 * 由于无相关性的分值默认为1,设置权重分最小值为10
	 */
	Float MIN_SCORE = 10.0F;

	@Autowired
	EntityRepository entityRepository;

	/**
	 * 保存内容到ES
	 */
	@Override
	public Long saveEntity(Entity entity) {
		Entity entityResult = entityRepository.save(entity);
		System.out.println(entityResult.getId() + ": " + entityResult.getName());
		return entityResult.getId();
	}

	/**
	 * 在ES中搜索内容
	 */
	@Override
	public List<Entity> searchEntity(int pageNumber, int pageSize, String searchContent) {
		if (pageSize == 0) {
			pageSize = pageSize;
		}
		if (pageNumber < 0) {
			pageNumber = pageNumber;
		}

		SearchQuery searchQuery = getEntitySearchQuery(pageNumber, pageSize, searchContent);

		LOGGER.info("\n searchCity: searchContent [" + searchContent + "] \n DSL  = \n "
				+ searchQuery.getQuery().toString());


		Page<Entity> cityPage = entityRepository.search(searchQuery);
		return cityPage.getContent();
	}

	/**
	 * 组装搜索Query对象
	 *
	 * @param pageNumber
	 * @param pageSize
	 * @param searchContent
	 * @return
	 */
	private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {
		FunctionScoreQueryBuilder functionScoreQueryBuilder =
				QueryBuilders.functionScoreQuery(
						QueryBuilders.matchPhraseQuery("name", searchContent),
						ScoreFunctionBuilders.weightFactorFunction(1000))
						//.add(QueryBuilders.matchPhraseQuery("other", searchContent),
						//ScoreFunctionBuilders.weightFactorFunction(1000))
						.scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM).setMinScore(MIN_SCORE);
		//设置分页，否则只能按照ES默认的分页给
		Pageable pageable = new PageRequest(pageNumber, pageSize);
		return new NativeSearchQueryBuilder().withPageable(pageable).withQuery(functionScoreQueryBuilder).build();
	}
}