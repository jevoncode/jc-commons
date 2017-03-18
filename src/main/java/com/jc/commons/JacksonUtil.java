package com.jc.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonUtil {
	private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
		SimpleModule module = new SimpleModule();
		objectMapper.registerModule(module);
	}

	public static String toJson(Object o) {
		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			logger.error("序列化失败", e);
			return "";
		}
	}

}
