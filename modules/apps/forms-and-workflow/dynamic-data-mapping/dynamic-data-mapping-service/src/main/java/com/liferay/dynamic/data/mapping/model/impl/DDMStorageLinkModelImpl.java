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

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMStorageLink;
import com.liferay.dynamic.data.mapping.model.DDMStorageLinkModel;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the DDMStorageLink service. Represents a row in the &quot;DDMStorageLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>DDMStorageLinkModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DDMStorageLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStorageLinkImpl
 * @generated
 */
public class DDMStorageLinkModelImpl
	extends BaseModelImpl<DDMStorageLink> implements DDMStorageLinkModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ddm storage link model instance should use the <code>DDMStorageLink</code> interface instead.
	 */
	public static final String TABLE_NAME = "DDMStorageLink";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"storageLinkId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"structureId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("storageLinkId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("structureId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table DDMStorageLink (uuid_ VARCHAR(75) null,storageLinkId LONG not null primary key,companyId LONG,classNameId LONG,classPK LONG,structureId LONG)";

	public static final String TABLE_SQL_DROP = "drop table DDMStorageLink";

	public static final String ORDER_BY_JPQL =
		" ORDER BY ddmStorageLink.storageLinkId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY DDMStorageLink.storageLinkId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.dynamic.data.mapping.model.DDMStorageLink"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.dynamic.data.mapping.model.DDMStorageLink"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.dynamic.data.mapping.model.DDMStorageLink"),
		true);

	public static final long CLASSPK_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long STRUCTUREID_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long STORAGELINKID_COLUMN_BITMASK = 16L;

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.dynamic.data.mapping.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.dynamic.data.mapping.model.DDMStorageLink"));

	public DDMStorageLinkModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _storageLinkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStorageLinkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _storageLinkId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DDMStorageLink.class;
	}

	@Override
	public String getModelClassName() {
		return DDMStorageLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<DDMStorageLink, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<DDMStorageLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMStorageLink, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((DDMStorageLink)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<DDMStorageLink, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<DDMStorageLink, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(DDMStorageLink)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<DDMStorageLink, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<DDMStorageLink, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, DDMStorageLink>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			DDMStorageLink.class.getClassLoader(), DDMStorageLink.class,
			ModelWrapper.class);

		try {
			Constructor<DDMStorageLink> constructor =
				(Constructor<DDMStorageLink>)proxyClass.getConstructor(
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

	private static final Map<String, Function<DDMStorageLink, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<DDMStorageLink, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<DDMStorageLink, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<DDMStorageLink, Object>>();
		Map<String, BiConsumer<DDMStorageLink, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<DDMStorageLink, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<DDMStorageLink, Object>() {

				@Override
				public Object apply(DDMStorageLink ddmStorageLink) {
					return ddmStorageLink.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<DDMStorageLink, Object>() {

				@Override
				public void accept(
					DDMStorageLink ddmStorageLink, Object uuidObject) {

					ddmStorageLink.setUuid((String)uuidObject);
				}

			});
		attributeGetterFunctions.put(
			"storageLinkId",
			new Function<DDMStorageLink, Object>() {

				@Override
				public Object apply(DDMStorageLink ddmStorageLink) {
					return ddmStorageLink.getStorageLinkId();
				}

			});
		attributeSetterBiConsumers.put(
			"storageLinkId",
			new BiConsumer<DDMStorageLink, Object>() {

				@Override
				public void accept(
					DDMStorageLink ddmStorageLink, Object storageLinkIdObject) {

					ddmStorageLink.setStorageLinkId((Long)storageLinkIdObject);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<DDMStorageLink, Object>() {

				@Override
				public Object apply(DDMStorageLink ddmStorageLink) {
					return ddmStorageLink.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<DDMStorageLink, Object>() {

				@Override
				public void accept(
					DDMStorageLink ddmStorageLink, Object companyIdObject) {

					ddmStorageLink.setCompanyId((Long)companyIdObject);
				}

			});
		attributeGetterFunctions.put(
			"classNameId",
			new Function<DDMStorageLink, Object>() {

				@Override
				public Object apply(DDMStorageLink ddmStorageLink) {
					return ddmStorageLink.getClassNameId();
				}

			});
		attributeSetterBiConsumers.put(
			"classNameId",
			new BiConsumer<DDMStorageLink, Object>() {

				@Override
				public void accept(
					DDMStorageLink ddmStorageLink, Object classNameIdObject) {

					ddmStorageLink.setClassNameId((Long)classNameIdObject);
				}

			});
		attributeGetterFunctions.put(
			"classPK",
			new Function<DDMStorageLink, Object>() {

				@Override
				public Object apply(DDMStorageLink ddmStorageLink) {
					return ddmStorageLink.getClassPK();
				}

			});
		attributeSetterBiConsumers.put(
			"classPK",
			new BiConsumer<DDMStorageLink, Object>() {

				@Override
				public void accept(
					DDMStorageLink ddmStorageLink, Object classPKObject) {

					ddmStorageLink.setClassPK((Long)classPKObject);
				}

			});
		attributeGetterFunctions.put(
			"structureId",
			new Function<DDMStorageLink, Object>() {

				@Override
				public Object apply(DDMStorageLink ddmStorageLink) {
					return ddmStorageLink.getStructureId();
				}

			});
		attributeSetterBiConsumers.put(
			"structureId",
			new BiConsumer<DDMStorageLink, Object>() {

				@Override
				public void accept(
					DDMStorageLink ddmStorageLink, Object structureIdObject) {

					ddmStorageLink.setStructureId((Long)structureIdObject);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getStorageLinkId() {
		return _storageLinkId;
	}

	@Override
	public void setStorageLinkId(long storageLinkId) {
		_storageLinkId = storageLinkId;
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
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public long getStructureId() {
		return _structureId;
	}

	@Override
	public void setStructureId(long structureId) {
		_columnBitmask |= STRUCTUREID_COLUMN_BITMASK;

		if (!_setOriginalStructureId) {
			_setOriginalStructureId = true;

			_originalStructureId = _structureId;
		}

		_structureId = structureId;
	}

	public long getOriginalStructureId() {
		return _originalStructureId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), DDMStorageLink.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DDMStorageLink toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, DDMStorageLink>
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
		DDMStorageLinkImpl ddmStorageLinkImpl = new DDMStorageLinkImpl();

		ddmStorageLinkImpl.setUuid(getUuid());
		ddmStorageLinkImpl.setStorageLinkId(getStorageLinkId());
		ddmStorageLinkImpl.setCompanyId(getCompanyId());
		ddmStorageLinkImpl.setClassNameId(getClassNameId());
		ddmStorageLinkImpl.setClassPK(getClassPK());
		ddmStorageLinkImpl.setStructureId(getStructureId());

		ddmStorageLinkImpl.resetOriginalValues();

		return ddmStorageLinkImpl;
	}

	@Override
	public int compareTo(DDMStorageLink ddmStorageLink) {
		long primaryKey = ddmStorageLink.getPrimaryKey();

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

		if (!(obj instanceof DDMStorageLink)) {
			return false;
		}

		DDMStorageLink ddmStorageLink = (DDMStorageLink)obj;

		long primaryKey = ddmStorageLink.getPrimaryKey();

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
		DDMStorageLinkModelImpl ddmStorageLinkModelImpl = this;

		ddmStorageLinkModelImpl._originalUuid = ddmStorageLinkModelImpl._uuid;

		ddmStorageLinkModelImpl._originalCompanyId =
			ddmStorageLinkModelImpl._companyId;

		ddmStorageLinkModelImpl._setOriginalCompanyId = false;

		ddmStorageLinkModelImpl._originalClassPK =
			ddmStorageLinkModelImpl._classPK;

		ddmStorageLinkModelImpl._setOriginalClassPK = false;

		ddmStorageLinkModelImpl._originalStructureId =
			ddmStorageLinkModelImpl._structureId;

		ddmStorageLinkModelImpl._setOriginalStructureId = false;

		ddmStorageLinkModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DDMStorageLink> toCacheModel() {
		DDMStorageLinkCacheModel ddmStorageLinkCacheModel =
			new DDMStorageLinkCacheModel();

		ddmStorageLinkCacheModel.uuid = getUuid();

		String uuid = ddmStorageLinkCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			ddmStorageLinkCacheModel.uuid = null;
		}

		ddmStorageLinkCacheModel.storageLinkId = getStorageLinkId();

		ddmStorageLinkCacheModel.companyId = getCompanyId();

		ddmStorageLinkCacheModel.classNameId = getClassNameId();

		ddmStorageLinkCacheModel.classPK = getClassPK();

		ddmStorageLinkCacheModel.structureId = getStructureId();

		return ddmStorageLinkCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<DDMStorageLink, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<DDMStorageLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMStorageLink, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((DDMStorageLink)this));
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
		Map<String, Function<DDMStorageLink, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<DDMStorageLink, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<DDMStorageLink, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((DDMStorageLink)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, DDMStorageLink>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private long _storageLinkId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _classNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private long _structureId;
	private long _originalStructureId;
	private boolean _setOriginalStructureId;
	private long _columnBitmask;
	private DDMStorageLink _escapedModel;

}