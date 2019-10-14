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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.shopping.model.ShoppingCoupon;
import com.liferay.shopping.model.ShoppingCouponModel;
import com.liferay.shopping.model.ShoppingCouponSoap;

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
 * The base model implementation for the ShoppingCoupon service. Represents a row in the &quot;ShoppingCoupon&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ShoppingCouponModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ShoppingCouponImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCouponImpl
 * @generated
 */
@JSON(strict = true)
public class ShoppingCouponModelImpl
	extends BaseModelImpl<ShoppingCoupon> implements ShoppingCouponModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a shopping coupon model instance should use the <code>ShoppingCoupon</code> interface instead.
	 */
	public static final String TABLE_NAME = "ShoppingCoupon";

	public static final Object[][] TABLE_COLUMNS = {
		{"couponId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP}, {"code_", Types.VARCHAR},
		{"name", Types.VARCHAR}, {"description", Types.VARCHAR},
		{"startDate", Types.TIMESTAMP}, {"endDate", Types.TIMESTAMP},
		{"active_", Types.BOOLEAN}, {"limitCategories", Types.VARCHAR},
		{"limitSkus", Types.VARCHAR}, {"minOrder", Types.DOUBLE},
		{"discount", Types.DOUBLE}, {"discountType", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("couponId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("code_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("startDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("endDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("active_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("limitCategories", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("limitSkus", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("minOrder", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("discount", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("discountType", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table ShoppingCoupon (couponId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,code_ VARCHAR(75) null,name VARCHAR(75) null,description STRING null,startDate DATE null,endDate DATE null,active_ BOOLEAN,limitCategories STRING null,limitSkus STRING null,minOrder DOUBLE,discount DOUBLE,discountType VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table ShoppingCoupon";

	public static final String ORDER_BY_JPQL =
		" ORDER BY shoppingCoupon.createDate ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY ShoppingCoupon.createDate ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.shopping.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.shopping.model.ShoppingCoupon"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.shopping.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.shopping.model.ShoppingCoupon"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.shopping.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.shopping.model.ShoppingCoupon"),
		true);

	public static final long CODE_COLUMN_BITMASK = 1L;

	public static final long GROUPID_COLUMN_BITMASK = 2L;

	public static final long CREATEDATE_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ShoppingCoupon toModel(ShoppingCouponSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ShoppingCoupon model = new ShoppingCouponImpl();

		model.setCouponId(soapModel.getCouponId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCode(soapModel.getCode());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setStartDate(soapModel.getStartDate());
		model.setEndDate(soapModel.getEndDate());
		model.setActive(soapModel.isActive());
		model.setLimitCategories(soapModel.getLimitCategories());
		model.setLimitSkus(soapModel.getLimitSkus());
		model.setMinOrder(soapModel.getMinOrder());
		model.setDiscount(soapModel.getDiscount());
		model.setDiscountType(soapModel.getDiscountType());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ShoppingCoupon> toModels(
		ShoppingCouponSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<ShoppingCoupon> models = new ArrayList<ShoppingCoupon>(
			soapModels.length);

		for (ShoppingCouponSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.shopping.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.shopping.model.ShoppingCoupon"));

	public ShoppingCouponModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _couponId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCouponId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _couponId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ShoppingCoupon.class;
	}

	@Override
	public String getModelClassName() {
		return ShoppingCoupon.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ShoppingCoupon, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ShoppingCoupon, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ShoppingCoupon, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((ShoppingCoupon)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ShoppingCoupon, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ShoppingCoupon, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(ShoppingCoupon)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<ShoppingCoupon, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ShoppingCoupon, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, ShoppingCoupon>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			ShoppingCoupon.class.getClassLoader(), ShoppingCoupon.class,
			ModelWrapper.class);

		try {
			Constructor<ShoppingCoupon> constructor =
				(Constructor<ShoppingCoupon>)proxyClass.getConstructor(
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

	private static final Map<String, Function<ShoppingCoupon, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<ShoppingCoupon, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<ShoppingCoupon, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ShoppingCoupon, Object>>();
		Map<String, BiConsumer<ShoppingCoupon, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ShoppingCoupon, ?>>();

		attributeGetterFunctions.put(
			"couponId",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getCouponId();
				}

			});
		attributeSetterBiConsumers.put(
			"couponId",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object couponIdObject) {

					shoppingCoupon.setCouponId((Long)couponIdObject);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object groupIdObject) {

					shoppingCoupon.setGroupId((Long)groupIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object companyIdObject) {

					shoppingCoupon.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object userIdObject) {

					shoppingCoupon.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object userNameObject) {

					shoppingCoupon.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object createDateObject) {

					shoppingCoupon.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object modifiedDateObject) {

					shoppingCoupon.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"code",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getCode();
				}

			});
		attributeSetterBiConsumers.put(
			"code",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object codeObject) {

					shoppingCoupon.setCode((String)codeObject);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object nameObject) {

					shoppingCoupon.setName((String)nameObject);
				}

			});
		attributeGetterFunctions.put(
			"description",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getDescription();
				}

			});
		attributeSetterBiConsumers.put(
			"description",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object descriptionObject) {

					shoppingCoupon.setDescription((String)descriptionObject);
				}

			});
		attributeGetterFunctions.put(
			"startDate",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getStartDate();
				}

			});
		attributeSetterBiConsumers.put(
			"startDate",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object startDateObject) {

					shoppingCoupon.setStartDate((Date)startDateObject);
				}

			});
		attributeGetterFunctions.put(
			"endDate",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getEndDate();
				}

			});
		attributeSetterBiConsumers.put(
			"endDate",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object endDateObject) {

					shoppingCoupon.setEndDate((Date)endDateObject);
				}

			});
		attributeGetterFunctions.put(
			"active",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getActive();
				}

			});
		attributeSetterBiConsumers.put(
			"active",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object activeObject) {

					shoppingCoupon.setActive((Boolean)activeObject);
				}

			});
		attributeGetterFunctions.put(
			"limitCategories",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getLimitCategories();
				}

			});
		attributeSetterBiConsumers.put(
			"limitCategories",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon,
					Object limitCategoriesObject) {

					shoppingCoupon.setLimitCategories(
						(String)limitCategoriesObject);
				}

			});
		attributeGetterFunctions.put(
			"limitSkus",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getLimitSkus();
				}

			});
		attributeSetterBiConsumers.put(
			"limitSkus",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object limitSkusObject) {

					shoppingCoupon.setLimitSkus((String)limitSkusObject);
				}

			});
		attributeGetterFunctions.put(
			"minOrder",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getMinOrder();
				}

			});
		attributeSetterBiConsumers.put(
			"minOrder",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object minOrderObject) {

					shoppingCoupon.setMinOrder((Double)minOrderObject);
				}

			});
		attributeGetterFunctions.put(
			"discount",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getDiscount();
				}

			});
		attributeSetterBiConsumers.put(
			"discount",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object discountObject) {

					shoppingCoupon.setDiscount((Double)discountObject);
				}

			});
		attributeGetterFunctions.put(
			"discountType",
			new Function<ShoppingCoupon, Object>() {

				@Override
				public Object apply(ShoppingCoupon shoppingCoupon) {
					return shoppingCoupon.getDiscountType();
				}

			});
		attributeSetterBiConsumers.put(
			"discountType",
			new BiConsumer<ShoppingCoupon, Object>() {

				@Override
				public void accept(
					ShoppingCoupon shoppingCoupon, Object discountTypeObject) {

					shoppingCoupon.setDiscountType((String)discountTypeObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getCouponId() {
		return _couponId;
	}

	@Override
	public void setCouponId(long couponId) {
		_couponId = couponId;
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
		_columnBitmask = -1L;

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
	public String getCode() {
		if (_code == null) {
			return "";
		}
		else {
			return _code;
		}
	}

	@Override
	public void setCode(String code) {
		_columnBitmask |= CODE_COLUMN_BITMASK;

		if (_originalCode == null) {
			_originalCode = _code;
		}

		_code = code;
	}

	public String getOriginalCode() {
		return GetterUtil.getString(_originalCode);
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
		_name = name;
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

	@JSON
	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	@JSON
	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	@JSON
	@Override
	public boolean getActive() {
		return _active;
	}

	@JSON
	@Override
	public boolean isActive() {
		return _active;
	}

	@Override
	public void setActive(boolean active) {
		_active = active;
	}

	@JSON
	@Override
	public String getLimitCategories() {
		if (_limitCategories == null) {
			return "";
		}
		else {
			return _limitCategories;
		}
	}

	@Override
	public void setLimitCategories(String limitCategories) {
		_limitCategories = limitCategories;
	}

	@JSON
	@Override
	public String getLimitSkus() {
		if (_limitSkus == null) {
			return "";
		}
		else {
			return _limitSkus;
		}
	}

	@Override
	public void setLimitSkus(String limitSkus) {
		_limitSkus = limitSkus;
	}

	@JSON
	@Override
	public double getMinOrder() {
		return _minOrder;
	}

	@Override
	public void setMinOrder(double minOrder) {
		_minOrder = minOrder;
	}

	@JSON
	@Override
	public double getDiscount() {
		return _discount;
	}

	@Override
	public void setDiscount(double discount) {
		_discount = discount;
	}

	@JSON
	@Override
	public String getDiscountType() {
		if (_discountType == null) {
			return "";
		}
		else {
			return _discountType;
		}
	}

	@Override
	public void setDiscountType(String discountType) {
		_discountType = discountType;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), ShoppingCoupon.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ShoppingCoupon toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, ShoppingCoupon>
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
		ShoppingCouponImpl shoppingCouponImpl = new ShoppingCouponImpl();

		shoppingCouponImpl.setCouponId(getCouponId());
		shoppingCouponImpl.setGroupId(getGroupId());
		shoppingCouponImpl.setCompanyId(getCompanyId());
		shoppingCouponImpl.setUserId(getUserId());
		shoppingCouponImpl.setUserName(getUserName());
		shoppingCouponImpl.setCreateDate(getCreateDate());
		shoppingCouponImpl.setModifiedDate(getModifiedDate());
		shoppingCouponImpl.setCode(getCode());
		shoppingCouponImpl.setName(getName());
		shoppingCouponImpl.setDescription(getDescription());
		shoppingCouponImpl.setStartDate(getStartDate());
		shoppingCouponImpl.setEndDate(getEndDate());
		shoppingCouponImpl.setActive(isActive());
		shoppingCouponImpl.setLimitCategories(getLimitCategories());
		shoppingCouponImpl.setLimitSkus(getLimitSkus());
		shoppingCouponImpl.setMinOrder(getMinOrder());
		shoppingCouponImpl.setDiscount(getDiscount());
		shoppingCouponImpl.setDiscountType(getDiscountType());

		shoppingCouponImpl.resetOriginalValues();

		return shoppingCouponImpl;
	}

	@Override
	public int compareTo(ShoppingCoupon shoppingCoupon) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), shoppingCoupon.getCreateDate());

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

		if (!(obj instanceof ShoppingCoupon)) {
			return false;
		}

		ShoppingCoupon shoppingCoupon = (ShoppingCoupon)obj;

		long primaryKey = shoppingCoupon.getPrimaryKey();

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
		ShoppingCouponModelImpl shoppingCouponModelImpl = this;

		shoppingCouponModelImpl._originalGroupId =
			shoppingCouponModelImpl._groupId;

		shoppingCouponModelImpl._setOriginalGroupId = false;

		shoppingCouponModelImpl._setModifiedDate = false;

		shoppingCouponModelImpl._originalCode = shoppingCouponModelImpl._code;

		shoppingCouponModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ShoppingCoupon> toCacheModel() {
		ShoppingCouponCacheModel shoppingCouponCacheModel =
			new ShoppingCouponCacheModel();

		shoppingCouponCacheModel.couponId = getCouponId();

		shoppingCouponCacheModel.groupId = getGroupId();

		shoppingCouponCacheModel.companyId = getCompanyId();

		shoppingCouponCacheModel.userId = getUserId();

		shoppingCouponCacheModel.userName = getUserName();

		String userName = shoppingCouponCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			shoppingCouponCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			shoppingCouponCacheModel.createDate = createDate.getTime();
		}
		else {
			shoppingCouponCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			shoppingCouponCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			shoppingCouponCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		shoppingCouponCacheModel.code = getCode();

		String code = shoppingCouponCacheModel.code;

		if ((code != null) && (code.length() == 0)) {
			shoppingCouponCacheModel.code = null;
		}

		shoppingCouponCacheModel.name = getName();

		String name = shoppingCouponCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			shoppingCouponCacheModel.name = null;
		}

		shoppingCouponCacheModel.description = getDescription();

		String description = shoppingCouponCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			shoppingCouponCacheModel.description = null;
		}

		Date startDate = getStartDate();

		if (startDate != null) {
			shoppingCouponCacheModel.startDate = startDate.getTime();
		}
		else {
			shoppingCouponCacheModel.startDate = Long.MIN_VALUE;
		}

		Date endDate = getEndDate();

		if (endDate != null) {
			shoppingCouponCacheModel.endDate = endDate.getTime();
		}
		else {
			shoppingCouponCacheModel.endDate = Long.MIN_VALUE;
		}

		shoppingCouponCacheModel.active = isActive();

		shoppingCouponCacheModel.limitCategories = getLimitCategories();

		String limitCategories = shoppingCouponCacheModel.limitCategories;

		if ((limitCategories != null) && (limitCategories.length() == 0)) {
			shoppingCouponCacheModel.limitCategories = null;
		}

		shoppingCouponCacheModel.limitSkus = getLimitSkus();

		String limitSkus = shoppingCouponCacheModel.limitSkus;

		if ((limitSkus != null) && (limitSkus.length() == 0)) {
			shoppingCouponCacheModel.limitSkus = null;
		}

		shoppingCouponCacheModel.minOrder = getMinOrder();

		shoppingCouponCacheModel.discount = getDiscount();

		shoppingCouponCacheModel.discountType = getDiscountType();

		String discountType = shoppingCouponCacheModel.discountType;

		if ((discountType != null) && (discountType.length() == 0)) {
			shoppingCouponCacheModel.discountType = null;
		}

		return shoppingCouponCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ShoppingCoupon, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<ShoppingCoupon, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ShoppingCoupon, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((ShoppingCoupon)this));
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
		Map<String, Function<ShoppingCoupon, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ShoppingCoupon, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<ShoppingCoupon, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((ShoppingCoupon)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, ShoppingCoupon>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _couponId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _code;
	private String _originalCode;
	private String _name;
	private String _description;
	private Date _startDate;
	private Date _endDate;
	private boolean _active;
	private String _limitCategories;
	private String _limitSkus;
	private double _minOrder;
	private double _discount;
	private String _discountType;
	private long _columnBitmask;
	private ShoppingCoupon _escapedModel;

}