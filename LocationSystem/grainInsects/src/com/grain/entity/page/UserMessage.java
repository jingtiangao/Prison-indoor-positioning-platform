package com.grain.entity.page;

import java.io.Serializable;

import com.grain.Message.Type;

@SuppressWarnings("serial")
public class UserMessage<T > implements Serializable{
	T t;
	Type type;
	String content;
	
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
