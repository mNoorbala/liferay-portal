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

package com.liferay.social.privatemessaging.model.impl;

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
import com.liferay.social.privatemessaging.model.UserThread;
import com.liferay.social.privatemessaging.model.UserThreadModel;
import com.liferay.social.privatemessaging.model.UserThreadSoap;

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
 * The base model implementation for the UserThread service. Represents a row in the &quot;PM_UserThread&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>UserThreadModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserThreadImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadImpl
 * @generated
 */
@JSON(strict = true)
public class UserThreadModelImpl
	extends BaseModelImpl<UserThread> implements UserThreadModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user thread model instance should use the <code>UserThread</code> interface instead.
	 */
	public static final String TABLE_NAME = "PM_UserThread";

	public static final Object[][] TABLE_COLUMNS = {
		{"userThreadId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"mbThreadId", Types.BIGINT}, {"topMBMessageId", Types.BIGINT},
		{"read_", Types.BOOLEAN}, {"deleted", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("userThreadId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("mbThreadId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("topMBMessageId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("read_", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("deleted", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE =
		"create table PM_UserThread (userThreadId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,mbThreadId LONG,topMBMessageId LONG,read_ BOOLEAN,deleted BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table PM_UserThread";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userThread.modifiedDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY PM_UserThread.modifiedDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.social.privatemessaging.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.social.privatemessaging.model.UserThread"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.social.privatemessaging.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.social.privatemessaging.model.UserThread"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.social.privatemessaging.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.social.privatemessaging.model.UserThread"),
		true);

	public static final long DELETED_COLUMN_BITMASK = 1L;

	public static final long MBTHREADID_COLUMN_BITMASK = 2L;

	public static final long READ_COLUMN_BITMASK = 4L;

	public static final long USERID_COLUMN_BITMASK = 8L;

	public static final long MODIFIEDDATE_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static UserThread toModel(UserThreadSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		UserThread model = new UserThreadImpl();

		model.setUserThreadId(soapModel.getUserThreadId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setMbThreadId(soapModel.getMbThreadId());
		model.setTopMBMessageId(soapModel.getTopMBMessageId());
		model.setRead(soapModel.isRead());
		model.setDeleted(soapModel.isDeleted());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<UserThread> toModels(UserThreadSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<UserThread> models = new ArrayList<UserThread>(soapModels.length);

		for (UserThreadSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.social.privatemessaging.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.social.privatemessaging.model.UserThread"));

	public UserThreadModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _userThreadId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setUserThreadId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userThreadId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return UserThread.class;
	}

	@Override
	public String getModelClassName() {
		return UserThread.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserThread, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserThread, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserThread, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((UserThread)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserThread, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserThread, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserThread)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserThread, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserThread, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, UserThread>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			UserThread.class.getClassLoader(), UserThread.class,
			ModelWrapper.class);

		try {
			Constructor<UserThread> constructor =
				(Constructor<UserThread>)proxyClass.getConstructor(
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

	private static final Map<String, Function<UserThread, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserThread, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserThread, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<UserThread, Object>>();
		Map<String, BiConsumer<UserThread, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<UserThread, ?>>();

		attributeGetterFunctions.put(
			"userThreadId",
			new Function<UserThread, Object>() {

				@Override
				public Object apply(UserThread userThread) {
					return userThread.getUserThreadId();
				}

			});
		attributeSetterBiConsumers.put(
			"userThreadId",
			new BiConsumer<UserThread, Object>() {

				@Override
				public void accept(
					UserThread userThread, Object userThreadIdObject) {

					userThread.setUserThreadId((Long)userThreadIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<UserThread, Object>() {

				@Override
				public Object apply(UserThread userThread) {
					return userThread.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<UserThread, Object>() {

				@Override
				public void accept(
					UserThread userThread, Object companyIdObject) {

					userThread.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<UserThread, Object>() {

				@Override
				public Object apply(UserThread userThread) {
					return userThread.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<UserThread, Object>() {

				@Override
				public void accept(UserThread userThread, Object userIdObject) {
					userThread.setUserId((Long)userIdObject);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<UserThread, Object>() {

				@Override
				public Object apply(UserThread userThread) {
					return userThread.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<UserThread, Object>() {

				@Override
				public void accept(
					UserThread userThread, Object userNameObject) {

					userThread.setUserName((String)userNameObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<UserThread, Object>() {

				@Override
				public Object apply(UserThread userThread) {
					return userThread.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<UserThread, Object>() {

				@Override
				public void accept(
					UserThread userThread, Object createDateObject) {

					userThread.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<UserThread, Object>() {

				@Override
				public Object apply(UserThread userThread) {
					return userThread.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<UserThread, Object>() {

				@Override
				public void accept(
					UserThread userThread, Object modifiedDateObject) {

					userThread.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"mbThreadId",
			new Function<UserThread, Object>() {

				@Override
				public Object apply(UserThread userThread) {
					return userThread.getMbThreadId();
				}

			});
		attributeSetterBiConsumers.put(
			"mbThreadId",
			new BiConsumer<UserThread, Object>() {

				@Override
				public void accept(
					UserThread userThread, Object mbThreadIdObject) {

					userThread.setMbThreadId((Long)mbThreadIdObject);
				}

			});
		attributeGetterFunctions.put(
			"topMBMessageId",
			new Function<UserThread, Object>() {

				@Override
				public Object apply(UserThread userThread) {
					return userThread.getTopMBMessageId();
				}

			});
		attributeSetterBiConsumers.put(
			"topMBMessageId",
			new BiConsumer<UserThread, Object>() {

				@Override
				public void accept(
					UserThread userThread, Object topMBMessageIdObject) {

					userThread.setTopMBMessageId((Long)topMBMessageIdObject);
				}

			});
		attributeGetterFunctions.put(
			"read",
			new Function<UserThread, Object>() {

				@Override
				public Object apply(UserThread userThread) {
					return userThread.getRead();
				}

			});
		attributeSetterBiConsumers.put(
			"read",
			new BiConsumer<UserThread, Object>() {

				@Override
				public void accept(UserThread userThread, Object readObject) {
					userThread.setRead((Boolean)readObject);
				}

			});
		attributeGetterFunctions.put(
			"deleted",
			new Function<UserThread, Object>() {

				@Override
				public Object apply(UserThread userThread) {
					return userThread.getDeleted();
				}

			});
		attributeSetterBiConsumers.put(
			"deleted",
			new BiConsumer<UserThread, Object>() {

				@Override
				public void accept(
					UserThread userThread, Object deletedObject) {

					userThread.setDeleted((Boolean)deletedObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getUserThreadId() {
		return _userThreadId;
	}

	@Override
	public void setUserThreadId(long userThreadId) {
		_userThreadId = userThreadId;
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
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

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

	public long getOriginalUserId() {
		return _originalUserId;
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

		_columnBitmask = -1L;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getMbThreadId() {
		return _mbThreadId;
	}

	@Override
	public void setMbThreadId(long mbThreadId) {
		_columnBitmask |= MBTHREADID_COLUMN_BITMASK;

		if (!_setOriginalMbThreadId) {
			_setOriginalMbThreadId = true;

			_originalMbThreadId = _mbThreadId;
		}

		_mbThreadId = mbThreadId;
	}

	public long getOriginalMbThreadId() {
		return _originalMbThreadId;
	}

	@JSON
	@Override
	public long getTopMBMessageId() {
		return _topMBMessageId;
	}

	@Override
	public void setTopMBMessageId(long topMBMessageId) {
		_topMBMessageId = topMBMessageId;
	}

	@JSON
	@Override
	public boolean getRead() {
		return _read;
	}

	@JSON
	@Override
	public boolean isRead() {
		return _read;
	}

	@Override
	public void setRead(boolean read) {
		_columnBitmask |= READ_COLUMN_BITMASK;

		if (!_setOriginalRead) {
			_setOriginalRead = true;

			_originalRead = _read;
		}

		_read = read;
	}

	public boolean getOriginalRead() {
		return _originalRead;
	}

	@JSON
	@Override
	public boolean getDeleted() {
		return _deleted;
	}

	@JSON
	@Override
	public boolean isDeleted() {
		return _deleted;
	}

	@Override
	public void setDeleted(boolean deleted) {
		_columnBitmask |= DELETED_COLUMN_BITMASK;

		if (!_setOriginalDeleted) {
			_setOriginalDeleted = true;

			_originalDeleted = _deleted;
		}

		_deleted = deleted;
	}

	public boolean getOriginalDeleted() {
		return _originalDeleted;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), UserThread.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public UserThread toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserThread>
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
		UserThreadImpl userThreadImpl = new UserThreadImpl();

		userThreadImpl.setUserThreadId(getUserThreadId());
		userThreadImpl.setCompanyId(getCompanyId());
		userThreadImpl.setUserId(getUserId());
		userThreadImpl.setUserName(getUserName());
		userThreadImpl.setCreateDate(getCreateDate());
		userThreadImpl.setModifiedDate(getModifiedDate());
		userThreadImpl.setMbThreadId(getMbThreadId());
		userThreadImpl.setTopMBMessageId(getTopMBMessageId());
		userThreadImpl.setRead(isRead());
		userThreadImpl.setDeleted(isDeleted());

		userThreadImpl.resetOriginalValues();

		return userThreadImpl;
	}

	@Override
	public int compareTo(UserThread userThread) {
		int value = 0;

		value = DateUtil.compareTo(
			getModifiedDate(), userThread.getModifiedDate());

		value = value * -1;

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

		if (!(obj instanceof UserThread)) {
			return false;
		}

		UserThread userThread = (UserThread)obj;

		long primaryKey = userThread.getPrimaryKey();

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
		UserThreadModelImpl userThreadModelImpl = this;

		userThreadModelImpl._originalUserId = userThreadModelImpl._userId;

		userThreadModelImpl._setOriginalUserId = false;

		userThreadModelImpl._setModifiedDate = false;

		userThreadModelImpl._originalMbThreadId =
			userThreadModelImpl._mbThreadId;

		userThreadModelImpl._setOriginalMbThreadId = false;

		userThreadModelImpl._originalRead = userThreadModelImpl._read;

		userThreadModelImpl._setOriginalRead = false;

		userThreadModelImpl._originalDeleted = userThreadModelImpl._deleted;

		userThreadModelImpl._setOriginalDeleted = false;

		userThreadModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UserThread> toCacheModel() {
		UserThreadCacheModel userThreadCacheModel = new UserThreadCacheModel();

		userThreadCacheModel.userThreadId = getUserThreadId();

		userThreadCacheModel.companyId = getCompanyId();

		userThreadCacheModel.userId = getUserId();

		userThreadCacheModel.userName = getUserName();

		String userName = userThreadCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			userThreadCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			userThreadCacheModel.createDate = createDate.getTime();
		}
		else {
			userThreadCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			userThreadCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			userThreadCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		userThreadCacheModel.mbThreadId = getMbThreadId();

		userThreadCacheModel.topMBMessageId = getTopMBMessageId();

		userThreadCacheModel.read = isRead();

		userThreadCacheModel.deleted = isDeleted();

		return userThreadCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserThread, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserThread, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserThread, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((UserThread)this));
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
		Map<String, Function<UserThread, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<UserThread, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserThread, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((UserThread)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, UserThread>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _userThreadId;
	private long _companyId;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _mbThreadId;
	private long _originalMbThreadId;
	private boolean _setOriginalMbThreadId;
	private long _topMBMessageId;
	private boolean _read;
	private boolean _originalRead;
	private boolean _setOriginalRead;
	private boolean _deleted;
	private boolean _originalDeleted;
	private boolean _setOriginalDeleted;
	private long _columnBitmask;
	private UserThread _escapedModel;

}