package com.clear.entity;


import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Document(indexName="index_entity", type="tstype")
public class Entity implements Serializable {

	private static final long serialVersionUID = -763638353551774166L;

	public static final String INDEX_NAME = "index_entity";

	public static final String TYPE = "tstype";

	private Long id;

	private String name;

}
