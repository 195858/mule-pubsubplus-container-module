package com.solace.connector.mulesoft.testcontainer;

//import org.testcontainers.containers.GenericContainer;

public class SolaceContainerPropertyHolder {
//	public static final GenericContainer solace;

	private static int port = 55555;
	private static String host = "localhost";


	static {
//		solace = new GenericContainer<>("solace/solace-pubsub-standard:9.5.0.25").withExposedPorts(55555)
//				.withSharedMemorySize(1000000000L).withEnv("username_admin_globalaccesslevel", "admin")
//				.withEnv("username_admin_password", "admin").withEnv("system_scaling_maxconnectioncount", "100");
//		solace.start();
//		port = solace.getFirstMappedPort();
//		host = solace.getContainerIpAddress();
	}

	public static void setPort(int port) {
		SolaceContainerPropertyHolder.port = port;
	}

	public static void setHost(String host) {
		SolaceContainerPropertyHolder.host = host;
	}

	public static int getPort() {
		return port;
	}

	public static String getHost() {
		return host;
	}

}
