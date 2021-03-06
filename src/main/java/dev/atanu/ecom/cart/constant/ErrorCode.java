/**
 * 
 */
package dev.atanu.ecom.cart.constant;

/**
 * @author Atanu Bhowmick
 *
 */
public enum ErrorCode {

	CART_E001("Product not found"), 
	CART_E002("No Search result found"),
	CART_E003("Cart information not found"),
	CART_E004("Maximum %s products can be added in cart"),
	CART_E005("Product you have selected currently is not available"),
	CART_E006("Invalid Json"),
	
	CART_E500("Internal Server Error. Please try again later!");
	
	private String errorMsg;

	private ErrorCode(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
