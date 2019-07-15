/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.document.library.opener.google.drive.constants;

import com.liferay.document.library.opener.drive.constants.DLOpenerDriveMimeTypes;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.Map;

/**
 * Provides a set of constants and methods for working with the MIME types
 * supported by Liferay and Google Drive.
 *
 * See https://developers.google.com/drive/api/v3/manage-downloads.
 *
 * @author Adolfo Pérez
 * @review
 */
public class DLOpenerGoogleDriveMimeTypes {

	/**
	 * The MIME type for Google Docs.
	 */
	public static final String APPLICATION_VND_GOOGLE_APPS_DOCUMENT =
		"application/vnd.google-apps.document";

	/**
	 * The MIME type for Google Slides.
	 */
	public static final String APPLICATION_VND_GOOGLE_APPS_PRESENTATION =
		"application/vnd.google-apps.presentation";

	/**
	 * The MIME type for Google Spreadsheets.
	 */
	public static final String APPLICATION_VND_GOOGLE_APPS_SPREADSHEET =
		"application/vnd.google-apps.spreadsheet";

	/**
	 * Returns the Google Drive MIME type equivalent to the one received. For
	 * example, this method maps Microsoft Word documents and plain text files
	 * to Google Docs, Microsoft PowerPoint presentations to Google Slides, and
	 * and so on.
	 *
	 * <p>
	 * This method returns a valid Google document type only for MIME types for
	 * which {@link #isGoogleMimeTypeSupported(String)} returns {@code true}. An
	 * exception is thrown for any others.
	 * </p>
	 *
	 * @param  mimeType the MIME type to map to Google Drive
	 * @return the equivalent Google Drive MIME type
	 * @review
	 */
	public static String getGoogleDocsMimeType(String mimeType) {
		if (!isGoogleMimeTypeSupported(mimeType)) {
			throw new UnsupportedOperationException(
				StringBundler.concat(
					"Google Docs does not support edition of documents of ",
					"type ", mimeType));
		}

		return _googleDocsMimeTypes.get(mimeType);
	}

	/**
	 * Returns the canonical file extension associated with the MIME type. The
	 * canonical file extension is the most common one used for documents of
	 * this type (e.g., if the MIME type indicates this is a Microsoft docx
	 * document, the return value is {@code ".docx"}.
	 *
	 * <p>
	 * The return value always includes a dot ({@code .}) as the extension's
	 * first character.
	 * </p>
	 *
	 * <p>
	 * Only MIME types for which the {@link #isGoogleMimeTypeSupported(String)}
	 * method returns {@code true} return an extension. Any empty string is
	 * returned for any others.
	 * </p>
	 *
	 * @param  mimeType the MIME type
	 * @return the canonical extension, or an empty string if none could be
	 *         determined
	 * @review
	 */
	public static String getMimeTypeExtension(String mimeType) {
		return DLOpenerDriveMimeTypes.extensions.getOrDefault(
			mimeType, StringPool.BLANK);
	}

	/**
	 * Returns {@code true} if a MIME type is supported.
	 *
	 * @param  mimeType the MIME type
	 * @return {@code true} if the MIME type is supported; {@code false}
	 *         otherwise
	 * @review
	 */
	public static boolean isGoogleMimeTypeSupported(String mimeType) {
		return _googleDocsMimeTypes.containsKey(mimeType);
	}

	private static final Map<String, String> _googleDocsMimeTypes =
		MapUtil.fromArray(
			ContentTypes.APPLICATION_PDF, APPLICATION_VND_GOOGLE_APPS_DOCUMENT,
			DLOpenerDriveMimeTypes.APPLICATION_RTF,
			APPLICATION_VND_GOOGLE_APPS_DOCUMENT, ContentTypes.APPLICATION_TEXT,
			APPLICATION_VND_GOOGLE_APPS_DOCUMENT,
			DLOpenerDriveMimeTypes.APPLICATION_VND_DOCX,
			APPLICATION_VND_GOOGLE_APPS_DOCUMENT,
			DLOpenerDriveMimeTypes.APPLICATION_VND_ODP,
			APPLICATION_VND_GOOGLE_APPS_PRESENTATION,
			DLOpenerDriveMimeTypes.APPLICATION_VND_ODS,
			APPLICATION_VND_GOOGLE_APPS_SPREADSHEET,
			DLOpenerDriveMimeTypes.APPLICATION_VND_ODT,
			APPLICATION_VND_GOOGLE_APPS_DOCUMENT,
			DLOpenerDriveMimeTypes.APPLICATION_VND_PPTX,
			APPLICATION_VND_GOOGLE_APPS_PRESENTATION,
			DLOpenerDriveMimeTypes.APPLICATION_VND_XSLX,
			APPLICATION_VND_GOOGLE_APPS_SPREADSHEET, ContentTypes.TEXT,
			APPLICATION_VND_GOOGLE_APPS_DOCUMENT, ContentTypes.TEXT_CSV,
			APPLICATION_VND_GOOGLE_APPS_SPREADSHEET, ContentTypes.TEXT_HTML,
			APPLICATION_VND_GOOGLE_APPS_DOCUMENT, ContentTypes.TEXT_PLAIN,
			APPLICATION_VND_GOOGLE_APPS_DOCUMENT,
			DLOpenerDriveMimeTypes.TEXT_TAB_SEPARATED_VALUES,
			APPLICATION_VND_GOOGLE_APPS_SPREADSHEET);

}