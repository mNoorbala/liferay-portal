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

package com.liferay.portal.service.base;

import com.liferay.counter.kernel.service.persistence.CounterPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.RecentLayoutSetBranch;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.RecentLayoutSetBranchLocalService;
import com.liferay.portal.kernel.service.persistence.LayoutSetBranchPersistence;
import com.liferay.portal.kernel.service.persistence.RecentLayoutSetBranchPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the recent layout set branch local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.RecentLayoutSetBranchLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.RecentLayoutSetBranchLocalServiceImpl
 * @generated
 */
public abstract class RecentLayoutSetBranchLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements IdentifiableOSGiService, RecentLayoutSetBranchLocalService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>RecentLayoutSetBranchLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.kernel.service.RecentLayoutSetBranchLocalServiceUtil</code>.
	 */

	/**
	 * Adds the recent layout set branch to the database. Also notifies the appropriate model listeners.
	 *
	 * @param recentLayoutSetBranch the recent layout set branch
	 * @return the recent layout set branch that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public RecentLayoutSetBranch addRecentLayoutSetBranch(
		RecentLayoutSetBranch recentLayoutSetBranch) {

		recentLayoutSetBranch.setNew(true);

		return recentLayoutSetBranchPersistence.update(recentLayoutSetBranch);
	}

	/**
	 * Creates a new recent layout set branch with the primary key. Does not add the recent layout set branch to the database.
	 *
	 * @param recentLayoutSetBranchId the primary key for the new recent layout set branch
	 * @return the new recent layout set branch
	 */
	@Override
	@Transactional(enabled = false)
	public RecentLayoutSetBranch createRecentLayoutSetBranch(
		long recentLayoutSetBranchId) {

		return recentLayoutSetBranchPersistence.create(recentLayoutSetBranchId);
	}

	/**
	 * Deletes the recent layout set branch with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param recentLayoutSetBranchId the primary key of the recent layout set branch
	 * @return the recent layout set branch that was removed
	 * @throws PortalException if a recent layout set branch with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public RecentLayoutSetBranch deleteRecentLayoutSetBranch(
			long recentLayoutSetBranchId)
		throws PortalException {

		return recentLayoutSetBranchPersistence.remove(recentLayoutSetBranchId);
	}

	/**
	 * Deletes the recent layout set branch from the database. Also notifies the appropriate model listeners.
	 *
	 * @param recentLayoutSetBranch the recent layout set branch
	 * @return the recent layout set branch that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public RecentLayoutSetBranch deleteRecentLayoutSetBranch(
		RecentLayoutSetBranch recentLayoutSetBranch) {

		return recentLayoutSetBranchPersistence.remove(recentLayoutSetBranch);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			RecentLayoutSetBranch.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return recentLayoutSetBranchPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.RecentLayoutSetBranchModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return recentLayoutSetBranchPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.RecentLayoutSetBranchModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return recentLayoutSetBranchPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return recentLayoutSetBranchPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return recentLayoutSetBranchPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public RecentLayoutSetBranch fetchRecentLayoutSetBranch(
		long recentLayoutSetBranchId) {

		return recentLayoutSetBranchPersistence.fetchByPrimaryKey(
			recentLayoutSetBranchId);
	}

	/**
	 * Returns the recent layout set branch with the primary key.
	 *
	 * @param recentLayoutSetBranchId the primary key of the recent layout set branch
	 * @return the recent layout set branch
	 * @throws PortalException if a recent layout set branch with the primary key could not be found
	 */
	@Override
	public RecentLayoutSetBranch getRecentLayoutSetBranch(
			long recentLayoutSetBranchId)
		throws PortalException {

		return recentLayoutSetBranchPersistence.findByPrimaryKey(
			recentLayoutSetBranchId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			recentLayoutSetBranchLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(RecentLayoutSetBranch.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"recentLayoutSetBranchId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			recentLayoutSetBranchLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			RecentLayoutSetBranch.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"recentLayoutSetBranchId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			recentLayoutSetBranchLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(RecentLayoutSetBranch.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"recentLayoutSetBranchId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return recentLayoutSetBranchLocalService.deleteRecentLayoutSetBranch(
			(RecentLayoutSetBranch)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return recentLayoutSetBranchPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the recent layout set branchs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.RecentLayoutSetBranchModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layout set branchs
	 * @param end the upper bound of the range of recent layout set branchs (not inclusive)
	 * @return the range of recent layout set branchs
	 */
	@Override
	public List<RecentLayoutSetBranch> getRecentLayoutSetBranchs(
		int start, int end) {

		return recentLayoutSetBranchPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of recent layout set branchs.
	 *
	 * @return the number of recent layout set branchs
	 */
	@Override
	public int getRecentLayoutSetBranchsCount() {
		return recentLayoutSetBranchPersistence.countAll();
	}

	/**
	 * Updates the recent layout set branch in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param recentLayoutSetBranch the recent layout set branch
	 * @return the recent layout set branch that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public RecentLayoutSetBranch updateRecentLayoutSetBranch(
		RecentLayoutSetBranch recentLayoutSetBranch) {

		return recentLayoutSetBranchPersistence.update(recentLayoutSetBranch);
	}

	/**
	 * Returns the recent layout set branch local service.
	 *
	 * @return the recent layout set branch local service
	 */
	public RecentLayoutSetBranchLocalService
		getRecentLayoutSetBranchLocalService() {

		return recentLayoutSetBranchLocalService;
	}

	/**
	 * Sets the recent layout set branch local service.
	 *
	 * @param recentLayoutSetBranchLocalService the recent layout set branch local service
	 */
	public void setRecentLayoutSetBranchLocalService(
		RecentLayoutSetBranchLocalService recentLayoutSetBranchLocalService) {

		this.recentLayoutSetBranchLocalService =
			recentLayoutSetBranchLocalService;
	}

	/**
	 * Returns the recent layout set branch persistence.
	 *
	 * @return the recent layout set branch persistence
	 */
	public RecentLayoutSetBranchPersistence
		getRecentLayoutSetBranchPersistence() {

		return recentLayoutSetBranchPersistence;
	}

	/**
	 * Sets the recent layout set branch persistence.
	 *
	 * @param recentLayoutSetBranchPersistence the recent layout set branch persistence
	 */
	public void setRecentLayoutSetBranchPersistence(
		RecentLayoutSetBranchPersistence recentLayoutSetBranchPersistence) {

		this.recentLayoutSetBranchPersistence =
			recentLayoutSetBranchPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the counter persistence.
	 *
	 * @return the counter persistence
	 */
	public CounterPersistence getCounterPersistence() {
		return counterPersistence;
	}

	/**
	 * Sets the counter persistence.
	 *
	 * @param counterPersistence the counter persistence
	 */
	public void setCounterPersistence(CounterPersistence counterPersistence) {
		this.counterPersistence = counterPersistence;
	}

	/**
	 * Returns the layout set branch local service.
	 *
	 * @return the layout set branch local service
	 */
	public com.liferay.portal.kernel.service.LayoutSetBranchLocalService
		getLayoutSetBranchLocalService() {

		return layoutSetBranchLocalService;
	}

	/**
	 * Sets the layout set branch local service.
	 *
	 * @param layoutSetBranchLocalService the layout set branch local service
	 */
	public void setLayoutSetBranchLocalService(
		com.liferay.portal.kernel.service.LayoutSetBranchLocalService
			layoutSetBranchLocalService) {

		this.layoutSetBranchLocalService = layoutSetBranchLocalService;
	}

	/**
	 * Returns the layout set branch persistence.
	 *
	 * @return the layout set branch persistence
	 */
	public LayoutSetBranchPersistence getLayoutSetBranchPersistence() {
		return layoutSetBranchPersistence;
	}

	/**
	 * Sets the layout set branch persistence.
	 *
	 * @param layoutSetBranchPersistence the layout set branch persistence
	 */
	public void setLayoutSetBranchPersistence(
		LayoutSetBranchPersistence layoutSetBranchPersistence) {

		this.layoutSetBranchPersistence = layoutSetBranchPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.kernel.model.RecentLayoutSetBranch",
			recentLayoutSetBranchLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.RecentLayoutSetBranch");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return RecentLayoutSetBranchLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return RecentLayoutSetBranch.class;
	}

	protected String getModelClassName() {
		return RecentLayoutSetBranch.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				recentLayoutSetBranchPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = RecentLayoutSetBranchLocalService.class)
	protected RecentLayoutSetBranchLocalService
		recentLayoutSetBranchLocalService;

	@BeanReference(type = RecentLayoutSetBranchPersistence.class)
	protected RecentLayoutSetBranchPersistence recentLayoutSetBranchPersistence;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(type = CounterPersistence.class)
	protected CounterPersistence counterPersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.LayoutSetBranchLocalService.class
	)
	protected com.liferay.portal.kernel.service.LayoutSetBranchLocalService
		layoutSetBranchLocalService;

	@BeanReference(type = LayoutSetBranchPersistence.class)
	protected LayoutSetBranchPersistence layoutSetBranchPersistence;

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}