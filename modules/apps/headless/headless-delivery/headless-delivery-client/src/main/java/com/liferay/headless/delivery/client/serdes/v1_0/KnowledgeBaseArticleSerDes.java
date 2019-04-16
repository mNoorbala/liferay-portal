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

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.KnowledgeBaseArticle;
import com.liferay.headless.delivery.client.dto.v1_0.TaxonomyCategory;
import com.liferay.headless.delivery.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class KnowledgeBaseArticleSerDes {

	public static KnowledgeBaseArticle toDTO(String json) {
		KnowledgeBaseArticleJSONParser knowledgeBaseArticleJSONParser =
			new KnowledgeBaseArticleJSONParser();

		return knowledgeBaseArticleJSONParser.parseToDTO(json);
	}

	public static KnowledgeBaseArticle[] toDTOs(String json) {
		KnowledgeBaseArticleJSONParser knowledgeBaseArticleJSONParser =
			new KnowledgeBaseArticleJSONParser();

		return knowledgeBaseArticleJSONParser.parseToDTOs(json);
	}

	public static String toJSON(KnowledgeBaseArticle knowledgeBaseArticle) {
		if (knowledgeBaseArticle == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		sb.append("\"aggregateRating\": ");

		sb.append(
			AggregateRatingSerDes.toJSON(
				knowledgeBaseArticle.getAggregateRating()));
		sb.append(", ");

		sb.append("\"articleBody\": ");

		if (knowledgeBaseArticle.getArticleBody() == null) {
			sb.append("null");
		}
		else {
			sb.append("\"");

			sb.append(knowledgeBaseArticle.getArticleBody());

			sb.append("\"");
		}

		sb.append(", ");

		sb.append("\"creator\": ");

		sb.append(CreatorSerDes.toJSON(knowledgeBaseArticle.getCreator()));
		sb.append(", ");

		sb.append("\"dateCreated\": ");

		if (knowledgeBaseArticle.getDateCreated() == null) {
			sb.append("null");
		}
		else {
			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					knowledgeBaseArticle.getDateCreated()));

			sb.append("\"");
		}

		sb.append(", ");

		sb.append("\"dateModified\": ");

		if (knowledgeBaseArticle.getDateModified() == null) {
			sb.append("null");
		}
		else {
			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					knowledgeBaseArticle.getDateModified()));

			sb.append("\"");
		}

		sb.append(", ");

		sb.append("\"description\": ");

		if (knowledgeBaseArticle.getDescription() == null) {
			sb.append("null");
		}
		else {
			sb.append("\"");

			sb.append(knowledgeBaseArticle.getDescription());

			sb.append("\"");
		}

		sb.append(", ");

		sb.append("\"encodingFormat\": ");

		if (knowledgeBaseArticle.getEncodingFormat() == null) {
			sb.append("null");
		}
		else {
			sb.append("\"");

			sb.append(knowledgeBaseArticle.getEncodingFormat());

			sb.append("\"");
		}

		sb.append(", ");

		sb.append("\"friendlyUrlPath\": ");

		if (knowledgeBaseArticle.getFriendlyUrlPath() == null) {
			sb.append("null");
		}
		else {
			sb.append("\"");

			sb.append(knowledgeBaseArticle.getFriendlyUrlPath());

			sb.append("\"");
		}

		sb.append(", ");

		sb.append("\"id\": ");

		if (knowledgeBaseArticle.getId() == null) {
			sb.append("null");
		}
		else {
			sb.append(knowledgeBaseArticle.getId());
		}

		sb.append(", ");

		sb.append("\"keywords\": ");

		if (knowledgeBaseArticle.getKeywords() == null) {
			sb.append("null");
		}
		else {
			sb.append("[");

			for (int i = 0; i < knowledgeBaseArticle.getKeywords().length;
				 i++) {

				sb.append("\"");

				sb.append(knowledgeBaseArticle.getKeywords()[i]);

				sb.append("\"");

				if ((i + 1) < knowledgeBaseArticle.getKeywords().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append(", ");

		sb.append("\"numberOfAttachments\": ");

		if (knowledgeBaseArticle.getNumberOfAttachments() == null) {
			sb.append("null");
		}
		else {
			sb.append(knowledgeBaseArticle.getNumberOfAttachments());
		}

		sb.append(", ");

		sb.append("\"numberOfKnowledgeBaseArticles\": ");

		if (knowledgeBaseArticle.getNumberOfKnowledgeBaseArticles() == null) {
			sb.append("null");
		}
		else {
			sb.append(knowledgeBaseArticle.getNumberOfKnowledgeBaseArticles());
		}

		sb.append(", ");

		sb.append("\"parentKnowledgeBaseFolder\": ");

		sb.append(
			ParentKnowledgeBaseFolderSerDes.toJSON(
				knowledgeBaseArticle.getParentKnowledgeBaseFolder()));
		sb.append(", ");

		sb.append("\"parentKnowledgeBaseFolderId\": ");

		if (knowledgeBaseArticle.getParentKnowledgeBaseFolderId() == null) {
			sb.append("null");
		}
		else {
			sb.append(knowledgeBaseArticle.getParentKnowledgeBaseFolderId());
		}

		sb.append(", ");

		sb.append("\"siteId\": ");

		if (knowledgeBaseArticle.getSiteId() == null) {
			sb.append("null");
		}
		else {
			sb.append(knowledgeBaseArticle.getSiteId());
		}

		sb.append(", ");

		sb.append("\"taxonomyCategories\": ");

		if (knowledgeBaseArticle.getTaxonomyCategories() == null) {
			sb.append("null");
		}
		else {
			sb.append("[");

			for (int i = 0;
				 i < knowledgeBaseArticle.getTaxonomyCategories().length; i++) {

				sb.append(
					TaxonomyCategorySerDes.toJSON(
						knowledgeBaseArticle.getTaxonomyCategories()[i]));

				if ((i + 1) <
						knowledgeBaseArticle.getTaxonomyCategories().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append(", ");

		sb.append("\"taxonomyCategoryIds\": ");

		if (knowledgeBaseArticle.getTaxonomyCategoryIds() == null) {
			sb.append("null");
		}
		else {
			sb.append("[");

			for (int i = 0;
				 i < knowledgeBaseArticle.getTaxonomyCategoryIds().length;
				 i++) {

				sb.append(knowledgeBaseArticle.getTaxonomyCategoryIds()[i]);

				if ((i + 1) <
						knowledgeBaseArticle.getTaxonomyCategoryIds().length) {

					sb.append(", ");
				}
			}

			sb.append("]");
		}

		sb.append(", ");

		sb.append("\"title\": ");

		if (knowledgeBaseArticle.getTitle() == null) {
			sb.append("null");
		}
		else {
			sb.append("\"");

			sb.append(knowledgeBaseArticle.getTitle());

			sb.append("\"");
		}

		sb.append(", ");

		sb.append("\"viewableBy\": ");

		if (knowledgeBaseArticle.getViewableBy() == null) {
			sb.append("null");
		}
		else {
			sb.append("\"");

			sb.append(knowledgeBaseArticle.getViewableBy());

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, String> toMap(
		KnowledgeBaseArticle knowledgeBaseArticle) {

		if (knowledgeBaseArticle == null) {
			return null;
		}

		Map<String, String> map = new HashMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (knowledgeBaseArticle.getAggregateRating() == null) {
			map.put("aggregateRating", null);
		}
		else {
			map.put(
				"aggregateRating",
				AggregateRatingSerDes.toJSON(
					knowledgeBaseArticle.getAggregateRating()));
		}

		if (knowledgeBaseArticle.getArticleBody() == null) {
			map.put("articleBody", null);
		}
		else {
			map.put(
				"articleBody",
				String.valueOf(knowledgeBaseArticle.getArticleBody()));
		}

		if (knowledgeBaseArticle.getCreator() == null) {
			map.put("creator", null);
		}
		else {
			map.put(
				"creator",
				CreatorSerDes.toJSON(knowledgeBaseArticle.getCreator()));
		}

		map.put(
			"dateCreated",
			liferayToJSONDateFormat.format(
				knowledgeBaseArticle.getDateCreated()));

		map.put(
			"dateModified",
			liferayToJSONDateFormat.format(
				knowledgeBaseArticle.getDateModified()));

		if (knowledgeBaseArticle.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description",
				String.valueOf(knowledgeBaseArticle.getDescription()));
		}

		if (knowledgeBaseArticle.getEncodingFormat() == null) {
			map.put("encodingFormat", null);
		}
		else {
			map.put(
				"encodingFormat",
				String.valueOf(knowledgeBaseArticle.getEncodingFormat()));
		}

		if (knowledgeBaseArticle.getFriendlyUrlPath() == null) {
			map.put("friendlyUrlPath", null);
		}
		else {
			map.put(
				"friendlyUrlPath",
				String.valueOf(knowledgeBaseArticle.getFriendlyUrlPath()));
		}

		if (knowledgeBaseArticle.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(knowledgeBaseArticle.getId()));
		}

		if (knowledgeBaseArticle.getKeywords() == null) {
			map.put("keywords", null);
		}
		else {
			map.put(
				"keywords", String.valueOf(knowledgeBaseArticle.getKeywords()));
		}

		if (knowledgeBaseArticle.getNumberOfAttachments() == null) {
			map.put("numberOfAttachments", null);
		}
		else {
			map.put(
				"numberOfAttachments",
				String.valueOf(knowledgeBaseArticle.getNumberOfAttachments()));
		}

		if (knowledgeBaseArticle.getNumberOfKnowledgeBaseArticles() == null) {
			map.put("numberOfKnowledgeBaseArticles", null);
		}
		else {
			map.put(
				"numberOfKnowledgeBaseArticles",
				String.valueOf(
					knowledgeBaseArticle.getNumberOfKnowledgeBaseArticles()));
		}

		if (knowledgeBaseArticle.getParentKnowledgeBaseFolder() == null) {
			map.put("parentKnowledgeBaseFolder", null);
		}
		else {
			map.put(
				"parentKnowledgeBaseFolder",
				ParentKnowledgeBaseFolderSerDes.toJSON(
					knowledgeBaseArticle.getParentKnowledgeBaseFolder()));
		}

		if (knowledgeBaseArticle.getParentKnowledgeBaseFolderId() == null) {
			map.put("parentKnowledgeBaseFolderId", null);
		}
		else {
			map.put(
				"parentKnowledgeBaseFolderId",
				String.valueOf(
					knowledgeBaseArticle.getParentKnowledgeBaseFolderId()));
		}

		if (knowledgeBaseArticle.getSiteId() == null) {
			map.put("siteId", null);
		}
		else {
			map.put("siteId", String.valueOf(knowledgeBaseArticle.getSiteId()));
		}

		if (knowledgeBaseArticle.getTaxonomyCategories() == null) {
			map.put("taxonomyCategories", null);
		}
		else {
			map.put(
				"taxonomyCategories",
				String.valueOf(knowledgeBaseArticle.getTaxonomyCategories()));
		}

		if (knowledgeBaseArticle.getTaxonomyCategoryIds() == null) {
			map.put("taxonomyCategoryIds", null);
		}
		else {
			map.put(
				"taxonomyCategoryIds",
				String.valueOf(knowledgeBaseArticle.getTaxonomyCategoryIds()));
		}

		if (knowledgeBaseArticle.getTitle() == null) {
			map.put("title", null);
		}
		else {
			map.put("title", String.valueOf(knowledgeBaseArticle.getTitle()));
		}

		if (knowledgeBaseArticle.getViewableBy() == null) {
			map.put("viewableBy", null);
		}
		else {
			map.put(
				"viewableBy",
				String.valueOf(knowledgeBaseArticle.getViewableBy()));
		}

		return map;
	}

	private static class KnowledgeBaseArticleJSONParser
		extends BaseJSONParser<KnowledgeBaseArticle> {

		@Override
		protected KnowledgeBaseArticle createDTO() {
			return new KnowledgeBaseArticle();
		}

		@Override
		protected KnowledgeBaseArticle[] createDTOArray(int size) {
			return new KnowledgeBaseArticle[size];
		}

		@Override
		protected void setField(
			KnowledgeBaseArticle knowledgeBaseArticle,
			String jsonParserFieldName, Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "aggregateRating")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setAggregateRating(
						AggregateRatingSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "articleBody")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setArticleBody(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "creator")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setCreator(
						CreatorSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateCreated")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setDateCreated(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "dateModified")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setDateModified(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setDescription(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "encodingFormat")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setEncodingFormat(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "friendlyUrlPath")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setFriendlyUrlPath(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "keywords")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setKeywords(
						toStrings((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "numberOfAttachments")) {

				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setNumberOfAttachments(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "numberOfKnowledgeBaseArticles")) {

				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setNumberOfKnowledgeBaseArticles(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "parentKnowledgeBaseFolder")) {

				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setParentKnowledgeBaseFolder(
						ParentKnowledgeBaseFolderSerDes.toDTO(
							(String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "parentKnowledgeBaseFolderId")) {

				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setParentKnowledgeBaseFolderId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "siteId")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setSiteId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "taxonomyCategories")) {

				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setTaxonomyCategories(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> TaxonomyCategorySerDes.toDTO(
								(String)object)
						).toArray(
							size -> new TaxonomyCategory[size]
						));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "taxonomyCategoryIds")) {

				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setTaxonomyCategoryIds(
						toLongs((Object[])jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setTitle((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "viewableBy")) {
				if (jsonParserFieldValue != null) {
					knowledgeBaseArticle.setViewableBy(
						KnowledgeBaseArticle.ViewableBy.create(
							(String)jsonParserFieldValue));
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

}