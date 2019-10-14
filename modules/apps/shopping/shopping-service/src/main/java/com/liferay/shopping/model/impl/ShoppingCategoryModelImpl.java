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

package com.liferay.shopping.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.shopping.model.ShoppingCategory;
import com.liferay.shopping.model.ShoppingCategoryModel;
import com.liferay.shopping.model.ShoppingCategorySoap;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ShoppingCategory service. Represents a row in the &quot;ShoppingCategory&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ShoppingCategoryModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ShoppingCategoryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCategoryImpl
 * @generated
 */
@JSON(strict = true)
public class ShoppingCategoryModelImpl
	extends BaseModelImpl<ShoppingCategory> implements ShoppingCategoryModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a shopping category model instance should use the <code>ShoppingCategory</code> interface instead.
	 */
	public static final String TABLE_NAME = "ShoppingCategory";

	public static final Object[][] TABLE_COLUMNS = {
		{"categoryId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"parentCategoryId", Types.BIGINT},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("categoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("parentCategoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ShoppingCategory (categoryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,parentCategoryId LONG,name VARCHAR(75) null,description STRING null)";

	public static final String TABLE_SQL_DROP = "drop table ShoppingCategory";

	public static final String ORDER_BY_JPQL =
		" ORDER BY shoppingCategory.parentCategoryId ASC, shoppingCategory.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ShoppingCategory.parentCategoryId ASC, ShoppingCategory.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.shopping.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.shopping.model.ShoppingCategory"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.shopping.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.shopping.model.ShoppingCategory"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.shopping.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.shopping.model.ShoppingCategory"),
		true);

	public static final long GROUPID_COLUMN_BITMASK = 1L;

	public static final long NAME_COLUMN_BITMASK = 2L;

	public static final long PARENTCATEGORYID_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ShoppingCategory toModel(ShoppingCategorySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ShoppingCategory model = new ShoppingCategoryImpl();

		model.setCategoryId(soapModel.getCategoryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setParentCategoryId(soapModel.getParentCategoryId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ShoppingCategory> toModels(
		ShoppingCategorySoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<ShoppingCategory> models = new ArrayList<ShoppingCategory>(
			soapModels.length);

		for (ShoppingCategorySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.shopping.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.shopping.model.ShoppingCategory"));

	public ShoppingCategoryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _categoryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCategoryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _categoryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ShoppingCategory.class;
	}

	@Override
	public String getModelClassName() {
		return ShoppingCategory.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ShoppingCategory, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ShoppingCategory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ShoppingCategory, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ShoppingCategory)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ShoppingCategory, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ShoppingCategory, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ShoppingCategory)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ShoppingCategory, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ShoppingCategory, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ShoppingCategory>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ShoppingCategory.class.getClassLoader(), ShoppingCategory.class,
			ModelWrapper.class);

		try {
			Constructor<ShoppingCategory> constructor =
				(Constructor<ShoppingCategory>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<ShoppingCategory, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ShoppingCategory, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ShoppingCategory, Object>>
			attributeGetterFunctions =
				new LinkedHashMap<String, Function<ShoppingCategory, Object>>();
		Map<String, BiConsumer<ShoppingCategory, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<ShoppingCategory, ?>>();

		attributeGetterFunctions.put(
			"categoryId",
			new Function<ShoppingCategory, Object>() {

				@Override
				public Object apply(ShoppingCategory shoppingCategory) {
					return shoppingCategory.getCategoryId();
				}

			});
		attributeSetterBiConsumers.put(
			"categoryId",
			new BiConsumer<ShoppingCategory, Object>() {

				@Override
				public void accept(
					ShoppingCategory shoppingCategory,
					Object categoryIdObject) {

					shoppingCategory.setCategoryId((Long)categoryIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<ShoppingCategory, Object>() {

				@Override
				public Object apply(ShoppingCategory shoppingCategory) {
					return shoppingCategory.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<ShoppingCategory, Object>() {

				@Override
				public void accept(
					ShoppingCategory shoppingCategory, Object groupIdObject) {

					shoppingCategory.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<ShoppingCategory, Object>() {

				@Override
				public Object apply(ShoppingCategory shoppingCategory) {
					return shoppingCategory.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<ShoppingCategory, Object>() {

				@Override
				public void accept(
					ShoppingCategory shoppingCategory, Object companyIdObject) {

					shoppingCategory.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<ShoppingCategory, Object>() {

				@Override
				public Object apply(ShoppingCategory shoppingCategory) {
					return shoppingCategory.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<ShoppingCategory, Object>() {

				@Override
				public void accept(
					ShoppingCategory shoppingCategory, Object userIdObject) {

					shoppingCategory.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<ShoppingCategory, Object>() {

				@Override
				public Object apply(ShoppingCategory shoppingCategory) {
					return shoppingCategory.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<ShoppingCategory, Object>() {

				@Override
				public void accept(
					ShoppingCategory shoppingCategory, Object userNameObject) {

					shoppingCategory.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<ShoppingCategory, Object>() {

				@Override
				public Object apply(ShoppingCategory shoppingCategory) {
					return shoppingCategory.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<ShoppingCategory, Object>() {

				@Override
				public void accept(
					ShoppingCategory shoppingCategory,
					Object createDateObject) {

					shoppingCategory.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<ShoppingCategory, Object>() {

				@Override
				public Object apply(ShoppingCategory shoppingCategory) {
					return shoppingCategory.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<ShoppingCategory, Object>() {

				@Override
				public void accept(
					ShoppingCategory shoppingCategory,
					Object modifiedDateObject) {

					shoppingCategory.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"parentCategoryId",
			new Function<ShoppingCategory, Object>() {

				@Override
				public Object apply(ShoppingCategory shoppingCategory) {
					return shoppingCategory.getParentCategoryId();
				}

			});
		attributeSetterBiConsumers.put(
			"parentCategoryId",
			new BiConsumer<ShoppingCategory, Object>() {

				@Override
				public void accept(
					ShoppingCategory shoppingCategory,
					Object parentCategoryIdObject) {

					shoppingCategory.setParentCategoryId(
						(Long)parentCategoryIdObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<ShoppingCategory, Object>() {

				@Override
				public Object apply(ShoppingCategory shoppingCategory) {
					return shoppingCategory.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<ShoppingCategory, Object>() {

				@Override
				public void accept(
					ShoppingCategory shoppingCategory, Object nameObject) {

					shoppingCategory.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"description",
			new Function<ShoppingCategory, Object>() {

				@Override
				public Object apply(ShoppingCategory shoppingCategory) {
					return shoppingCategory.getDescription();
				}

			});
		attributeSetterBiConsumers.put(
			"description",
			new BiConsumer<ShoppingCategory, Object>() {

				@Override
				public void accept(
					ShoppingCategory shoppingCategory,
					Object descriptionObject) {

					shoppingCategory.setDescription((String)descriptionObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCategoryId() {
		return _categoryId;
	}

	@Override
	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getParentCategoryId() {
		return _parentCategoryId;
	}

	@Override
	public void setParentCategoryId(long parentCategoryId) {
		_columnBitmask = -1L;

		if (!_setOriginalParentCategoryId) {
			_setOriginalParentCategoryId = true;

			_originalParentCategoryId = _parentCategoryId;
		}

		_parentCategoryId = parentCategoryId;
	}

	public long getOriginalParentCategoryId() {
		return _originalParentCategoryId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ShoppingCategory.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ShoppingCategory toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ShoppingCategory>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ShoppingCategoryImpl shoppingCategoryImpl = new ShoppingCategoryImpl();

		shoppingCategoryImpl.setCategoryId(getCategoryId());
		shoppingCategoryImpl.setGroupId(getGroupId());
		shoppingCategoryImpl.setCompanyId(getCompanyId());
		shoppingCategoryImpl.setUserId(getUserId());
		shoppingCategoryImpl.setUserName(getUserName());
		shoppingCategoryImpl.setCreateDate(getCreateDate());
		shoppingCategoryImpl.setModifiedDate(getModifiedDate());
		shoppingCategoryImpl.setParentCategoryId(getParentCategoryId());
		shoppingCategoryImpl.setName(getName());
		shoppingCategoryImpl.setDescription(getDescription());

		shoppingCategoryImpl.resetOriginalValues();

		return shoppingCategoryImpl;
	}

	@Override
	public int compareTo(ShoppingCategory shoppingCategory) {
		int value = 0;

		if (getParentCategoryId() < shoppingCategory.getParentCategoryId()) {
			value = -1;
		}
		else if (getParentCategoryId() >
					shoppingCategory.getParentCategoryId()) {

			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = getName().compareToIgnoreCase(shoppingCategory.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ShoppingCategory)) {
			return false;
		}

		ShoppingCategory shoppingCategory = (ShoppingCategory)obj;

		long primaryKey = shoppingCategory.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ShoppingCategoryModelImpl shoppingCategoryModelImpl = this;

		shoppingCategoryModelImpl._originalGroupId =
			shoppingCategoryModelImpl._groupId;

		shoppingCategoryModelImpl._setOriginalGroupId = false;

		shoppingCategoryModelImpl._setModifiedDate = false;

		shoppingCategoryModelImpl._originalParentCategoryId =
			shoppingCategoryModelImpl._parentCategoryId;

		shoppingCategoryModelImpl._setOriginalParentCategoryId = false;

		shoppingCategoryModelImpl._originalName =
			shoppingCategoryModelImpl._name;

		shoppingCategoryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ShoppingCategory> toCacheModel() {
		ShoppingCategoryCacheModel shoppingCategoryCacheModel =
			new ShoppingCategoryCacheModel();

		shoppingCategoryCacheModel.categoryId = getCategoryId();

		shoppingCategoryCacheModel.groupId = getGroupId();

		shoppingCategoryCacheModel.companyId = getCompanyId();

		shoppingCategoryCacheModel.userId = getUserId();

		shoppingCategoryCacheModel.userName = getUserName();

		String userName = shoppingCategoryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			shoppingCategoryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			shoppingCategoryCacheModel.createDate = createDate.getTime();
		}
		else {
			shoppingCategoryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			shoppingCategoryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			shoppingCategoryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		shoppingCategoryCacheModel.parentCategoryId = getParentCategoryId();

		shoppingCategoryCacheModel.name = getName();

		String name = shoppingCategoryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			shoppingCategoryCacheModel.name = null;
		}

		shoppingCategoryCacheModel.description = getDescription();

		String description = shoppingCategoryCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			shoppingCategoryCacheModel.description = null;
		}

		return shoppingCategoryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ShoppingCategory, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ShoppingCategory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ShoppingCategory, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ShoppingCategory)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<ShoppingCategory, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ShoppingCategory, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ShoppingCategory, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ShoppingCategory)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ShoppingCategory>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _categoryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _parentCategoryId;
	private long _originalParentCategoryId;
	private boolean _setOriginalParentCategoryId;
	private String _name;
	private String _originalName;
	private String _description;
	private long _columnBitmask;
	private ShoppingCategory _escapedModel;

}