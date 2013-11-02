package com.example.pattern.strategyCache.strategy;

import java.util.HashMap;
import java.util.Map;

import com.example.pattern.strategyCache.Comment;

public class inMemoryCache implements CacheStrategy {
	

	private Map commentInMemoryCache = new HashMap<Long,Comment>();
	
	@Override
	public void storeComment(Comment comment) {
		commentInMemoryCache.put(comment.getId(),comment);
	}

	@Override
	public Comment getComment(long commentId) {
		return (Comment) commentInMemoryCache.get(commentId);
	}

}
