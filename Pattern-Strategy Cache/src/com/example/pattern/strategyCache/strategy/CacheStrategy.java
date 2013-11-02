package com.example.pattern.strategyCache.strategy;

import com.example.pattern.strategyCache.Comment;

public interface CacheStrategy {

	public void storeComment(Comment comment);
	
	public Comment getComment(long commentId);
	
}
