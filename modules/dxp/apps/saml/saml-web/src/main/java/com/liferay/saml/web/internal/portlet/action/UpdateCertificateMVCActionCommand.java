/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.saml.web.internal.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.saml.constants.SamlWebKeys;
import com.liferay.saml.runtime.certificate.CertificateEntityId;
import com.liferay.saml.runtime.certificate.CertificateTool;
import com.liferay.saml.runtime.configuration.SamlProviderConfigurationHelper;
import com.liferay.saml.runtime.exception.CertificateKeyPasswordException;
import com.liferay.saml.runtime.exception.UnsupportedBindingException;
import com.liferay.saml.runtime.metadata.LocalEntityManager;
import com.liferay.saml.util.PortletPropsKeys;
import com.liferay.saml.web.internal.constants.SamlAdminPortletKeys;
import com.liferay.saml.web.internal.util.SamlTempFileEntryUtil;

import java.io.IOException;

import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 * @author Stian Sigvartsen
 */
@Component(
	configurationPid = "com.liferay.saml.runtime.configuration.SamlKeyStoreManagerConfiguration",
	immediate = true,
	property = {
		"javax.portlet.name=" + SamlAdminPortletKeys.SAML_ADMIN,
		"mvc.command.name=/admin/updateCertificate"
	},
	service = MVCActionCommand.class
)
public class UpdateCertificateMVCActionCommand extends BaseMVCActionCommand {

	protected void authenticateCertificate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		LocalEntityManager.CertificateUsage certificateUsage =
			LocalEntityManager.CertificateUsage.valueOf(
				ParamUtil.getString(actionRequest, "certificateUsage"));

		UnicodeProperties unicodeProperties = PropertiesParamUtil.getProperties(
			actionRequest, "settings--");

		String keystoreCredentialPassword = unicodeProperties.getProperty(
			getCeritifcateUsagePropertyKey(certificateUsage));

		if (Validator.isNotNull(keystoreCredentialPassword)) {
			_samlProviderConfigurationHelper.updateProperties(
				unicodeProperties);
		}

		try {
			X509Certificate x509Certificate =
				_localEntityManager.getLocalEntityCertificate(certificateUsage);

			actionRequest.setAttribute(
				SamlWebKeys.SAML_X509_CERTIFICATE, x509Certificate);
		}
		catch (Exception exception) {
			SessionErrors.add(
				actionRequest, CertificateKeyPasswordException.class);
		}

		actionResponse.setRenderParameter(
			"mvcRenderCommandName", "/admin/updateCertificate");
	}

	protected void deleteCertificate(ActionRequest actionRequest)
		throws Exception {

		_localEntityManager.deleteLocalEntityCertificate(
			LocalEntityManager.CertificateUsage.valueOf(
				ParamUtil.getString(actionRequest, "certificateUsage")));
	}

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new PrincipalException();
		}

		String cmd = ParamUtil.get(actionRequest, "cmd", "auth");

		if (cmd.equals("auth")) {
			authenticateCertificate(actionRequest, actionResponse);
		}
		else if (cmd.equals("delete")) {
			deleteCertificate(actionRequest);
		}
		else if (cmd.equals("import")) {
			importCertificate(
				actionRequest, actionResponse, themeDisplay.getUser());
		}
		else if (cmd.equals("replace")) {
			replaceCertificate(actionRequest);
		}
	}

	protected String getCeritifcateUsagePropertyKey(
			LocalEntityManager.CertificateUsage certificateUsage)
		throws UnsupportedBindingException {

		if (certificateUsage == LocalEntityManager.CertificateUsage.SIGNING) {
			return PortletPropsKeys.SAML_KEYSTORE_CREDENTIAL_PASSWORD;
		}
		else if (certificateUsage ==
					LocalEntityManager.CertificateUsage.ENCRYPTION) {

			return PortletPropsKeys.
				SAML_KEYSTORE_ENCRYPTION_CREDENTIAL_PASSWORD;
		}
		else {
			throw new UnsupportedBindingException(
				"Unsupported certificate usage: " + certificateUsage.name());
		}
	}

	protected void importCertificate(
			ActionRequest actionRequest, ActionResponse actionResponse,
			User user)
		throws Exception {

		hideDefaultSuccessMessage(actionRequest);

		KeyStore keyStore = null;

		String selectUploadedFile = ParamUtil.getString(
			actionRequest, "selectUploadedFile");

		FileEntry fileEntry = SamlTempFileEntryUtil.getTempFileEntry(
			user, selectUploadedFile);

		String keyStorePassword = ParamUtil.getString(
			actionRequest, "keyStorePassword");

		char[] password = null;

		if (keyStorePassword != null) {
			password = keyStorePassword.toCharArray();
		}

		String selectKeyStoreAlias = actionRequest.getParameter(
			"selectKeyStoreAlias");
		KeyStore.PrivateKeyEntry privateKeyEntry;

		try {
			keyStore = KeyStore.getInstance("PKCS12");

			keyStore.load(fileEntry.getContentStream(), password);

			actionRequest.setAttribute(SamlWebKeys.SAML_KEYSTORE, keyStore);

			if (Validator.isBlank(selectKeyStoreAlias)) {
				return;
			}

			if (!keyStore.entryInstanceOf(
					selectKeyStoreAlias, KeyStore.PrivateKeyEntry.class)) {

				throw new IllegalArgumentException();
			}

			privateKeyEntry = (KeyStore.PrivateKeyEntry)keyStore.getEntry(
				selectKeyStoreAlias, new KeyStore.PasswordProtection(password));
		}
		catch (KeyStoreException | NoSuchAlgorithmException exception) {
			if (keyStore == null) {
				SessionErrors.add(
					actionRequest,
					"keyStoreIntegrityCheckingAlgorithmNotSupported");
			}
			else {
				SessionErrors.add(
					actionRequest, "keyEncryptionAlgorithmNotSupported");
			}

			return;
		}
		catch (IOException ioException) {
			if (ioException.getCause() instanceof UnrecoverableKeyException) {
				SessionErrors.add(actionRequest, "incorrectKeyStorePassword");

				return;
			}

			throw new PortalException(ioException);
		}
		catch (CertificateException certificateException) {
			SessionErrors.add(actionRequest, "certificateException");

			return;
		}
		catch (UnrecoverableEntryException unrecoverableEntryException) {
			SessionErrors.add(actionRequest, "incorrectKeyPassword");

			return;
		}

		X509Certificate x509Certificate =
			(X509Certificate)privateKeyEntry.getCertificate();
		LocalEntityManager.CertificateUsage certificateUsage =
			LocalEntityManager.CertificateUsage.valueOf(
				ParamUtil.getString(actionRequest, "certificateUsage"));

		_localEntityManager.storeLocalEntityCertificate(
			privateKeyEntry.getPrivateKey(), keyStorePassword, x509Certificate,
			certificateUsage);

		UnicodeProperties unicodeProperties = new UnicodeProperties();

		unicodeProperties.setProperty(
			getCeritifcateUsagePropertyKey(certificateUsage), keyStorePassword);

		_samlProviderConfigurationHelper.updateProperties(unicodeProperties);

		SamlTempFileEntryUtil.deleteTempFileEntry(user, selectUploadedFile);

		actionRequest.setAttribute(
			SamlWebKeys.SAML_X509_CERTIFICATE, x509Certificate);
	}

	protected void replaceCertificate(ActionRequest actionRequest)
		throws Exception {

		UnicodeProperties unicodeProperties = PropertiesParamUtil.getProperties(
			actionRequest, "settings--");

		LocalEntityManager.CertificateUsage certificateUsage =
			LocalEntityManager.CertificateUsage.valueOf(
				ParamUtil.getString(actionRequest, "certificateUsage"));

		String keystoreCredentialPassword = unicodeProperties.getProperty(
			getCeritifcateUsagePropertyKey(certificateUsage));

		if (Validator.isNull(keystoreCredentialPassword)) {
			throw new CertificateKeyPasswordException();
		}

		int validityDays = ParamUtil.getInteger(
			actionRequest, "certificateValidityDays");

		if (validityDays == 0) {
			SessionErrors.add(actionRequest, "certificateValidityDays");

			return;
		}

		Calendar startDate = Calendar.getInstance();

		Calendar endDate = (Calendar)startDate.clone();

		endDate.add(Calendar.DAY_OF_YEAR, validityDays);

		if (endDate.get(Calendar.YEAR) > 9999) {
			SessionErrors.add(actionRequest, "certificateValidityDays");

			return;
		}

		String keyAlgorithm = ParamUtil.getString(
			actionRequest, "certificateKeyAlgorithm");
		int keyLength = ParamUtil.getInteger(
			actionRequest, "certificateKeyLength");

		KeyPair keyPair = _certificateTool.generateKeyPair(
			keyAlgorithm, keyLength);

		String commonName = ParamUtil.getString(
			actionRequest, "certificateCommonName");
		String organization = ParamUtil.getString(
			actionRequest, "certificateOrganization");
		String organizationUnit = ParamUtil.getString(
			actionRequest, "certificateOrganizationUnit");
		String locality = ParamUtil.getString(
			actionRequest, "certificateLocality");
		String state = ParamUtil.getString(actionRequest, "certificateState");
		String country = ParamUtil.getString(
			actionRequest, "certificateCountry");

		CertificateEntityId subjectCertificateEntityId =
			new CertificateEntityId(
				commonName, organization, organizationUnit, locality, state,
				country);

		X509Certificate x509Certificate = _certificateTool.generateCertificate(
			keyPair, subjectCertificateEntityId, subjectCertificateEntityId,
			startDate.getTime(), endDate.getTime(),
			_SHA256_PREFIX + keyAlgorithm);

		_localEntityManager.storeLocalEntityCertificate(
			keyPair.getPrivate(), keystoreCredentialPassword, x509Certificate,
			certificateUsage);

		_samlProviderConfigurationHelper.updateProperties(unicodeProperties);

		actionRequest.setAttribute(
			SamlWebKeys.SAML_X509_CERTIFICATE, x509Certificate);
	}

	private static final String _SHA256_PREFIX = "SHA256with";

	@Reference
	private CertificateTool _certificateTool;

	@Reference
	private LocalEntityManager _localEntityManager;

	@Reference
	private SamlProviderConfigurationHelper _samlProviderConfigurationHelper;

}