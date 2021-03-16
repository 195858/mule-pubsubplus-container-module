/*
 * (c) 2003-2018 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */
package com.solace.connector.mulesoft.testcontainer;

import static com.solace.connector.mulesoft.testcontainer.SolaceContainerConfigurationPropertiesExtensionLoadingDelegate.CONFIG_ELEMENT;
import static com.solace.connector.mulesoft.testcontainer.SolaceContainerConfigurationPropertiesExtensionLoadingDelegate.EXTENSION_NAME;
import static org.mule.runtime.api.component.ComponentIdentifier.builder;
import org.mule.runtime.api.component.ComponentIdentifier;
import org.mule.runtime.config.api.dsl.model.ConfigurationParameters;
import org.mule.runtime.config.api.dsl.model.ResourceProvider;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationPropertiesProvider;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationPropertiesProviderFactory;
import org.mule.runtime.config.api.dsl.model.properties.ConfigurationProperty;

import java.util.Optional;

/**
 * Builds the provider for a custom-properties-provider:config element.
 *
 * @since 1.0
 */
public class SolaceContainerConfigurationPropertiesProviderFactory implements ConfigurationPropertiesProviderFactory {

	private static final String SOLACE_PUB_SUB_PLUS_CONTAINER = "Solace PubSubPlus Container";
	public static final String EXTENSION_NAMESPACE = EXTENSION_NAME.toLowerCase().replace(" ", "-");
	private static final ComponentIdentifier CUSTOM_PROPERTIES_PROVIDER = builder().namespace(EXTENSION_NAMESPACE)
			.name(CONFIG_ELEMENT).build();
	private final static String CUSTOM_PROPERTIES_PREFIX = "pubsubplus-container::";

	@Override
	public ComponentIdentifier getSupportedComponentIdentifier() {
		return CUSTOM_PROPERTIES_PROVIDER;
	}

	@Override
	public ConfigurationPropertiesProvider createProvider(ConfigurationParameters parameters,
			ResourceProvider externalResourceProvider) {

		return new ConfigurationPropertiesProvider() {

			@Override
			public Optional<ConfigurationProperty> getConfigurationProperty(String configurationAttributeKey) {
				if (configurationAttributeKey.startsWith(CUSTOM_PROPERTIES_PREFIX)) {
					String effectiveKey = configurationAttributeKey.substring(CUSTOM_PROPERTIES_PREFIX.length());
					if (effectiveKey.equals("host")) {
						return Optional.of(new ConfigurationProperty() {

							@Override
							public Object getSource() {
								return SOLACE_PUB_SUB_PLUS_CONTAINER;
							}

							@Override
							public Object getRawValue() {
								return SolaceContainerPropertyHolder.getHost();
							}

							@Override
							public String getKey() {
								return effectiveKey;
							}
						});
					}
					if (effectiveKey.equals("port")) {
						return Optional.of(new ConfigurationProperty() {

							@Override
							public Object getSource() {
								return SOLACE_PUB_SUB_PLUS_CONTAINER;
							}

							@Override
							public Object getRawValue() {
								return Integer.toString(SolaceContainerPropertyHolder.getPort());
							}

							@Override
							public String getKey() {
								return effectiveKey;
							}
						});
					}
				}
				return Optional.empty();
			}

			@Override
			public String getDescription() {
				return "Solace Test Container properties provider";
			}
		};
	}

}
