package com.solace.connector.mulesoft.test;

import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;

import com.solace.connector.mulesoft.testcontainer.SolaceContainerPropertyHolder;

public abstract class AbstractSolaceArtifactFunctionalTestCase extends MuleArtifactFunctionalTestCase {
	public static final GenericContainer solace;
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSolaceArtifactFunctionalTestCase.class);

	static {
		solace = new GenericContainer<>("solace/solace-pubsub-standard:latest").withExposedPorts(55555)
				.withSharedMemorySize(1000000000L).withEnv("username_admin_globalaccesslevel", "admin")
				.withEnv("username_admin_password", "admin").withEnv("system_scaling_maxconnectioncount", "100");
		solace.start();
		SolaceContainerPropertyHolder.setHost(solace.getContainerIpAddress());
		SolaceContainerPropertyHolder.setPort(solace.getFirstMappedPort());
		LOGGER.info(String.format("Started Solace Docker Container, available on host [%s], port [%d]", solace.getContainerIpAddress(), solace.getFirstMappedPort()));
	}

}