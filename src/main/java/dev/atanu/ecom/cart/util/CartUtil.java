/**
 * 
 */
package dev.atanu.ecom.cart.util;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.atanu.ecom.cart.constant.CartConstant;
import dev.atanu.ecom.cart.constant.ErrorCode;
import dev.atanu.ecom.cart.exception.CartException;

/**
 * @author Atanu Bhowmick
 *
 */

public class CartUtil {

	private static final ObjectMapper mapper;
	private static final Logger logger = LoggerFactory.getLogger(CartUtil.class);

	static {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * Default Constructor
	 */
	private CartUtil() {
	}

	/**
	 * @param <T>
	 * @param jsonString
	 * @param clazz
	 * @return T
	 */
	public static <T> T toObject(String json, Class<T> clazz) {
		T t = null;
		try {
			t = mapper.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			throw new CartException(ErrorCode.CART_E006.name(), ErrorCode.CART_E006.getErrorMsg(), e);
		}
		return t;
	}

	/**
	 * 
	 * @param <T>
	 * @param jsonString
	 * @param typeReference
	 * @return T
	 */
	public static <T> T toObject(String json, TypeReference<T> typeReference) {
		T t = null;
		try {
			t = mapper.readValue(json, typeReference);
		} catch (JsonProcessingException e) {
			throw new CartException(ErrorCode.CART_E006.name(), ErrorCode.CART_E006.getErrorMsg(), e);
		}
		return t;
	}

	/**
	 * Convert object to string using jackson mapper
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		String str = CartConstant.EMPTY_STRING;
		try {
			str = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.debug("Error while converting object to string", e);
		}
		return str;
	}
	
	/**
	 * Convert double to decimal place
	 * 
	 * @param format
	 * @param value
	 * @return String
	 */
	public static String formatDecimal(String format, Double value) {
		DecimalFormat decimalFormat = new DecimalFormat(format);
		return decimalFormat.format(value);
	}

}
