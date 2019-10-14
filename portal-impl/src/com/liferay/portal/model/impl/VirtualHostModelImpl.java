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
import com.liferay.portal.kernel.model.VirtualHost;
import com.liferay.portal.kernel.model.VirtualHostModel;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the VirtualHost service. Represents a row in the &quot;VirtualHost&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>VirtualHostModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VirtualHostImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VirtualHostImpl
 * @generated
 */
public class VirtualHostModelImpl
	extends BaseModelImpl<VirtualHost> implements VirtualHostModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a virtual host model instance should use the <code>VirtualHost</code> interface instead.
	 */
	public static final String TABLE_NAME = "VirtualHost";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"virtualHostId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"layoutSetId", Types.BIGINT},
		{"hostname", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("virtualHostId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("layoutSetId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("hostname", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table VirtualHost (mvccVersion LONG default 0 not null,virtualHostId LONG not null primary key,companyId LONG,layoutSetId LONG,hostname VARCHAR(200) null)";

	public static final String TABLE_SQL_DROP = "drop table VirtualHost";

	public static final String ORDER_BY_JPQL =
		" ORDER BY virtualHost.virtualHostId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY VirtualHost.virtualHostId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.VirtualHost"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.VirtualHost"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.VirtualHost"),
		true);

	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	public static final long HOSTNAME_COLUMN_BITMASK = 2L;

	public static final long LAYOUTSETID_COLUMN_BITMASK = 4L;

	public static final long VIRTUALHOSTID_COLUMN_BITMASK = 8L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.VirtualHost"));

	public VirtualHostModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _virtualHostId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setVirtualHostId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _virtualHostId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return VirtualHost.class;
	}

	@Override
	public String getModelClassName() {
		return VirtualHost.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<VirtualHost, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<VirtualHost, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VirtualHost, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((VirtualHost)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<VirtualHost, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<VirtualHost, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(VirtualHost)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<VirtualHost, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<VirtualHost, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, VirtualHost>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			VirtualHost.class.getClassLoader(), VirtualHost.class,
			ModelWrapper.class);

		try {
			Constructor<VirtualHost> constructor =
				(Constructor<VirtualHost>)proxyClass.getConstructor(
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

	private static final Map<String, Function<VirtualHost, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<VirtualHost, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<VirtualHost, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<VirtualHost, Object>>();
		Map<String, BiConsumer<VirtualHost, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<VirtualHost, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion",
			new Function<VirtualHost, Object>() {

				@Override
				public Object apply(VirtualHost virtualHost) {
					return virtualHost.getMvccVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"mvccVersion",
			new BiConsumer<VirtualHost, Object>() {

				@Override
				public void accept(
					VirtualHost virtualHost, Object mvccVersionObject) {

					virtualHost.setMvccVersion((Long)mvccVersionObject);
				}

			});
		attributeGetterFunctions.put(
			"virtualHostId",
			new Function<VirtualHost, Object>() {

				@Override
				public Object apply(VirtualHost virtualHost) {
					return virtualHost.getVirtualHostId();
				}

			});
		attributeSetterBiConsumers.put(
			"virtualHostId",
			new BiConsumer<VirtualHost, Object>() {

				@Override
				public void accept(
					VirtualHost virtualHost, Object virtualHostIdObject) {

					virtualHost.setVirtualHostId((Long)virtualHostIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<VirtualHost, Object>() {

				@Override
				public Object apply(VirtualHost virtualHost) {
					return virtualHost.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<VirtualHost, Object>() {

				@Override
				public void accept(
					VirtualHost virtualHost, Object companyIdObject) {

					virtualHost.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"layoutSetId",
			new Function<VirtualHost, Object>() {

				@Override
				public Object apply(VirtualHost virtualHost) {
					return virtualHost.getLayoutSetId();
				}

			});
		attributeSetterBiConsumers.put(
			"layoutSetId",
			new BiConsumer<VirtualHost, Object>() {

				@Override
				public void accept(
					VirtualHost virtualHost, Object layoutSetIdObject) {

					virtualHost.setLayoutSetId((Long)layoutSetIdObject);
				}

			});
		attributeGetterFunctions.put(
			"hostname",
			new Function<VirtualHost, Object>() {

				@Override
				public Object apply(VirtualHost virtualHost) {
					return virtualHost.getHostname();
				}

			});
		attributeSetterBiConsumers.put(
			"hostname",
			new BiConsumer<VirtualHost, Object>() {

				@Override
				public void accept(
					VirtualHost virtualHost, Object hostnameObject) {

					virtualHost.setHostname((String)hostnameObject);
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
	public long getVirtualHostId() {
		return _virtualHostId;
	}

	@Override
	public void setVirtualHostId(long virtualHostId) {
		_virtualHostId = virtualHostId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getLayoutSetId() {
		return _layoutSetId;
	}

	@Override
	public void setLayoutSetId(long layoutSetId) {
		_columnBitmask |= LAYOUTSETID_COLUMN_BITMASK;

		if (!_setOriginalLayoutSetId) {
			_setOriginalLayoutSetId = true;

			_originalLayoutSetId = _layoutSetId;
		}

		_layoutSetId = layoutSetId;
	}

	public long getOriginalLayoutSetId() {
		return _originalLayoutSetId;
	}

	@Override
	public String getHostname() {
		if (_hostname == null) {
			return "";
		}
		else {
			return _hostname;
		}
	}

	@Override
	public void setHostname(String hostname) {
		_columnBitmask |= HOSTNAME_COLUMN_BITMASK;

		if (_originalHostname == null) {
			_originalHostname = _hostname;
		}

		_hostname = hostname;
	}

	public String getOriginalHostname() {
		return GetterUtil.getString(_originalHostname);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), VirtualHost.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public VirtualHost toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, VirtualHost>
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
		VirtualHostImpl virtualHostImpl = new VirtualHostImpl();

		virtualHostImpl.setMvccVersion(getMvccVersion());
		virtualHostImpl.setVirtualHostId(getVirtualHostId());
		virtualHostImpl.setCompanyId(getCompanyId());
		virtualHostImpl.setLayoutSetId(getLayoutSetId());
		virtualHostImpl.setHostname(getHostname());

		virtualHostImpl.resetOriginalValues();

		return virtualHostImpl;
	}

	@Override
	public int compareTo(VirtualHost virtualHost) {
		long primaryKey = virtualHost.getPrimaryKey();

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

		if (!(obj instanceof VirtualHost)) {
			return false;
		}

		VirtualHost virtualHost = (VirtualHost)obj;

		long primaryKey = virtualHost.getPrimaryKey();

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
		VirtualHostModelImpl virtualHostModelImpl = this;

		virtualHostModelImpl._originalCompanyId =
			virtualHostModelImpl._companyId;

		virtualHostModelImpl._setOriginalCompanyId = false;

		virtualHostModelImpl._originalLayoutSetId =
			virtualHostModelImpl._layoutSetId;

		virtualHostModelImpl._setOriginalLayoutSetId = false;

		virtualHostModelImpl._originalHostname = virtualHostModelImpl._hostname;

		virtualHostModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<VirtualHost> toCacheModel() {
		VirtualHostCacheModel virtualHostCacheModel =
			new VirtualHostCacheModel();

		virtualHostCacheModel.mvccVersion = getMvccVersion();

		virtualHostCacheModel.virtualHostId = getVirtualHostId();

		virtualHostCacheModel.companyId = getCompanyId();

		virtualHostCacheModel.layoutSetId = getLayoutSetId();

		virtualHostCacheModel.hostname = getHostname();

		String hostname = virtualHostCacheModel.hostname;

		if ((hostname != null) && (hostname.length() == 0)) {
			virtualHostCacheModel.hostname = null;
		}

		return virtualHostCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<VirtualHost, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<VirtualHost, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VirtualHost, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((VirtualHost)this));
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
		Map<String, Function<VirtualHost, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<VirtualHost, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<VirtualHost, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((VirtualHost)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, VirtualHost>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _virtualHostId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _layoutSetId;
	private long _originalLayoutSetId;
	private boolean _setOriginalLayoutSetId;
	private String _hostname;
	private String _originalHostname;
	private long _columnBitmask;
	private VirtualHost _escapedModel;

}