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

package com.liferay.portal.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.model.ReleaseModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Release service. Represents a row in the &quot;Release_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>ReleaseModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ReleaseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseImpl
 * @generated
 */
public class ReleaseModelImpl
	extends BaseModelImpl<Release> implements ReleaseModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a release model instance should use the <code>Release</code> interface instead.
	 */
	public static final String TABLE_NAME = "Release_";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"releaseId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"servletContextName", Types.VARCHAR}, {"schemaVersion", Types.VARCHAR},
		{"buildNumber", Types.INTEGER}, {"buildDate", Types.TIMESTAMP},
		{"verified", Types.BOOLEAN}, {"state_", Types.INTEGER},
		{"testString", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("releaseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("servletContextName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("schemaVersion", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("buildNumber", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("buildDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("verified", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("state_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("testString", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table Release_ (mvccVersion LONG default 0 not null,releaseId LONG not null primary key,createDate DATE null,modifiedDate DATE null,servletContextName VARCHAR(75) null,schemaVersion VARCHAR(75) null,buildNumber INTEGER,buildDate DATE null,verified BOOLEAN,state_ INTEGER,testString VARCHAR(1024) null)";

	public static final String TABLE_SQL_DROP = "drop table Release_";

	public static final String ORDER_BY_JPQL =
		" ORDER BY release.releaseId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY Release_.releaseId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.Release"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.Release"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.Release"),
		true);

	public static final long SERVLETCONTEXTNAME_COLUMN_BITMASK = 1L;

	public static final long RELEASEID_COLUMN_BITMASK = 2L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.Release"));

	public ReleaseModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _releaseId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setReleaseId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _releaseId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Release.class;
	}

	@Override
	public String getModelClassName() {
		return Release.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Release, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Release, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Release, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Release)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Release, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Release, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Release)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Release, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Release, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, Release>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			Release.class.getClassLoader(), Release.class, ModelWrapper.class);

		try {
			Constructor<Release> constructor =
				(Constructor<Release>)proxyClass.getConstructor(
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

	private static final Map<String, Function<Release, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<Release, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<Release, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<Release, Object>>();
		Map<String, BiConsumer<Release, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<Release, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getMvccVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"mvccVersion",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(Release release, Object mvccVersionObject) {
					release.setMvccVersion((Long)mvccVersionObject);
				}

			});
		attributeGetterFunctions.put(
			"releaseId",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getReleaseId();
				}

			});
		attributeSetterBiConsumers.put(
			"releaseId",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(Release release, Object releaseIdObject) {
					release.setReleaseId((Long)releaseIdObject);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(Release release, Object createDateObject) {
					release.setCreateDate((Date)createDateObject);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(Release release, Object modifiedDateObject) {
					release.setModifiedDate((Date)modifiedDateObject);
				}

			});
		attributeGetterFunctions.put(
			"servletContextName",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getServletContextName();
				}

			});
		attributeSetterBiConsumers.put(
			"servletContextName",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(
					Release release, Object servletContextNameObject) {

					release.setServletContextName(
						(String)servletContextNameObject);
				}

			});
		attributeGetterFunctions.put(
			"schemaVersion",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getSchemaVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"schemaVersion",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(
					Release release, Object schemaVersionObject) {

					release.setSchemaVersion((String)schemaVersionObject);
				}

			});
		attributeGetterFunctions.put(
			"buildNumber",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getBuildNumber();
				}

			});
		attributeSetterBiConsumers.put(
			"buildNumber",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(Release release, Object buildNumberObject) {
					release.setBuildNumber((Integer)buildNumberObject);
				}

			});
		attributeGetterFunctions.put(
			"buildDate",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getBuildDate();
				}

			});
		attributeSetterBiConsumers.put(
			"buildDate",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(Release release, Object buildDateObject) {
					release.setBuildDate((Date)buildDateObject);
				}

			});
		attributeGetterFunctions.put(
			"verified",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getVerified();
				}

			});
		attributeSetterBiConsumers.put(
			"verified",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(Release release, Object verifiedObject) {
					release.setVerified((Boolean)verifiedObject);
				}

			});
		attributeGetterFunctions.put(
			"state",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getState();
				}

			});
		attributeSetterBiConsumers.put(
			"state",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(Release release, Object stateObject) {
					release.setState((Integer)stateObject);
				}

			});
		attributeGetterFunctions.put(
			"testString",
			new Function<Release, Object>() {

				@Override
				public Object apply(Release release) {
					return release.getTestString();
				}

			});
		attributeSetterBiConsumers.put(
			"testString",
			new BiConsumer<Release, Object>() {

				@Override
				public void accept(Release release, Object testStringObject) {
					release.setTestString((String)testStringObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@Override
	public long getReleaseId() {
		return _releaseId;
	}

	@Override
	public void setReleaseId(long releaseId) {
		_releaseId = releaseId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

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

	@Override
	public String getServletContextName() {
		if (_servletContextName == null) {
			return "";
		}
		else {
			return _servletContextName;
		}
	}

	@Override
	public void setServletContextName(String servletContextName) {
		_columnBitmask |= SERVLETCONTEXTNAME_COLUMN_BITMASK;

		if (_originalServletContextName == null) {
			_originalServletContextName = _servletContextName;
		}

		_servletContextName = servletContextName;
	}

	public String getOriginalServletContextName() {
		return GetterUtil.getString(_originalServletContextName);
	}

	@Override
	public String getSchemaVersion() {
		if (_schemaVersion == null) {
			return "";
		}
		else {
			return _schemaVersion;
		}
	}

	@Override
	public void setSchemaVersion(String schemaVersion) {
		_schemaVersion = schemaVersion;
	}

	@Override
	public int getBuildNumber() {
		return _buildNumber;
	}

	@Override
	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;
	}

	@Override
	public Date getBuildDate() {
		return _buildDate;
	}

	@Override
	public void setBuildDate(Date buildDate) {
		_buildDate = buildDate;
	}

	@Override
	public boolean getVerified() {
		return _verified;
	}

	@Override
	public boolean isVerified() {
		return _verified;
	}

	@Override
	public void setVerified(boolean verified) {
		_verified = verified;
	}

	@Override
	public int getState() {
		return _state;
	}

	@Override
	public void setState(int state) {
		_state = state;
	}

	@Override
	public String getTestString() {
		if (_testString == null) {
			return "";
		}
		else {
			return _testString;
		}
	}

	@Override
	public void setTestString(String testString) {
		_testString = testString;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			0, Release.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Release toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Release>
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
		ReleaseImpl releaseImpl = new ReleaseImpl();

		releaseImpl.setMvccVersion(getMvccVersion());
		releaseImpl.setReleaseId(getReleaseId());
		releaseImpl.setCreateDate(getCreateDate());
		releaseImpl.setModifiedDate(getModifiedDate());
		releaseImpl.setServletContextName(getServletContextName());
		releaseImpl.setSchemaVersion(getSchemaVersion());
		releaseImpl.setBuildNumber(getBuildNumber());
		releaseImpl.setBuildDate(getBuildDate());
		releaseImpl.setVerified(isVerified());
		releaseImpl.setState(getState());
		releaseImpl.setTestString(getTestString());

		releaseImpl.resetOriginalValues();

		return releaseImpl;
	}

	@Override
	public int compareTo(Release release) {
		long primaryKey = release.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Release)) {
			return false;
		}

		Release release = (Release)obj;

		long primaryKey = release.getPrimaryKey();

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
		ReleaseModelImpl releaseModelImpl = this;

		releaseModelImpl._setModifiedDate = false;

		releaseModelImpl._originalServletContextName =
			releaseModelImpl._servletContextName;

		releaseModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Release> toCacheModel() {
		ReleaseCacheModel releaseCacheModel = new ReleaseCacheModel();

		releaseCacheModel.mvccVersion = getMvccVersion();

		releaseCacheModel.releaseId = getReleaseId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			releaseCacheModel.createDate = createDate.getTime();
		}
		else {
			releaseCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			releaseCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			releaseCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		releaseCacheModel.servletContextName = getServletContextName();

		String servletContextName = releaseCacheModel.servletContextName;

		if ((servletContextName != null) &&
			(servletContextName.length() == 0)) {

			releaseCacheModel.servletContextName = null;
		}

		releaseCacheModel.schemaVersion = getSchemaVersion();

		String schemaVersion = releaseCacheModel.schemaVersion;

		if ((schemaVersion != null) && (schemaVersion.length() == 0)) {
			releaseCacheModel.schemaVersion = null;
		}

		releaseCacheModel.buildNumber = getBuildNumber();

		Date buildDate = getBuildDate();

		if (buildDate != null) {
			releaseCacheModel.buildDate = buildDate.getTime();
		}
		else {
			releaseCacheModel.buildDate = Long.MIN_VALUE;
		}

		releaseCacheModel.verified = isVerified();

		releaseCacheModel.state = getState();

		releaseCacheModel.testString = getTestString();

		String testString = releaseCacheModel.testString;

		if ((testString != null) && (testString.length() == 0)) {
			releaseCacheModel.testString = null;
		}

		return releaseCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Release, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Release, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Release, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Release)this));
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
		Map<String, Function<Release, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Release, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Release, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Release)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Release>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _releaseId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _servletContextName;
	private String _originalServletContextName;
	private String _schemaVersion;
	private int _buildNumber;
	private Date _buildDate;
	private boolean _verified;
	private int _state;
	private String _testString;
	private long _columnBitmask;
	private Release _escapedModel;

}