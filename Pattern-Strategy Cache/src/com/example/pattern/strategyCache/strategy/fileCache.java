package com.example.pattern.strategyCache.strategy;

import java.util.HashMap;
import java.util.Map;

import com.example.pattern.strategyCache.Comment;

public class fileCache implements CacheStrategy {
	
	private Map commentInMemoryCache = new HashMap<Long,Comment>();
	
	@Override
	public void storeComment(Comment comment) {
		//algorithm to store in file
	}

	@Override
	public Comment getComment(long commentId) {
		//algorithm to get from file
		return null;
	}
}
