package com.buildbooster.customer.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

public enum ErrorCodes implements IErrorCode {
	// Catalog specific response error codes and error response.
	MANDATORY_ELEMENT_MISSING(1, HttpStatus.BAD_REQUEST, "Mandatory element {0} missing in the request"),;

	/** Begin of the error codes range. */
	public static final int ERROR_CODE_RANGE_BASE = 1000;
	/** End of the error codes range. */
	public static final int ERROR_CODE_RANGE_MAX = 1999;

	/** Error code. */
	private final Integer code;

	/** Error text message. */
	private final String message;

	private final HttpStatus httpCode;

	ErrorCodes(final Integer pCode, final HttpStatus pHttpCode, final String pMessage) {
		code = pCode + ERROR_CODE_RANGE_BASE;
		message = pMessage;
		httpCode = pHttpCode;
	}

	@Override
	public final Integer getCode() {
		return code;
	}

	public final String getMessage() {
		return message;
	}

	@Override
	public final HttpStatus getHttpCode() {
		return httpCode;
	}

	/**
	 * getter for message.
	 *
	 * @param pArguments the arguments
	 * @return the formatted message
	 */

	public final String getFormattedMessage(final Object... pArguments) {
		return MessageFormat.format(this.getMessage(), pArguments);
	}

	/**
	 * Catalog Exception.
	 *
	 * @return the Catalog exception
	 */

	public final CatalogServiceException createCatalogServiceException(final Object... pArguments) {
		return new CatalogServiceException(this, this.getFormattedMessage(pArguments));
	}

	/**
	 * Catalog Exception with variable arguments.
	 *
	 * @param pMessageArguments the message arguments
	 * @return the Catalog exception
	 */
	public final CatalogServiceException createCatalogServiceException(final Throwable pCause,
			final Object... pArguments) {
		return new CatalogServiceException(this, this.getFormattedMessage(pArguments), pCause);
	}

	/**
	 * Catalog Exception.
	 *
	 * @return the Catalog exception
	 */

	public final CatalogSystemException createCatalogSystemException(final Object... pArguments) {
		return new CatalogSystemException(this, this.getFormattedMessage(pArguments));
	}

	/**
	 * Catalog Exception with variable arguments.
	 *
	 * @param pMessageArguments the message arguments
	 * @return the Catalog exception
	 */
	public final CatalogSystemException createCatalogSystemException(final Throwable pCause,
			final Object... pArguments) {
		return new CatalogSystemException(this, this.getFormattedMessage(pArguments), pCause);
	}
}
