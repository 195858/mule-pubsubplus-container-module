package com.solace.connector.mulesoft.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.testcontainers.containers.GenericContainer;

import com.solace.connector.mulesoft.testcontainer.SolaceContainerPropertyHolder;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;

public class SolaceContainerProviderTestCase  extends AbstractSolaceArtifactFunctionalTestCase {

	/**
	 * Specifies the mule config xml with the flows that are going to be executed in
	 * the tests, this file lives in the test resources.
	 */
	@Override
	protected String getConfigFile() {
		return "test-mule-config.xml";
	}

	@Inject
	private TestObject testObject;

	@Test
	public void customPropertyProviderSuccessfullyConfigured() {
		long high = Long.MAX_VALUE;
		long low = 0;
		long port = Long.parseLong(testObject.getValueFromProperty());
		assertTrue("Error, port is too high", high >= port);
		assertTrue("Error, port is too low", low <= port);
	}

}
