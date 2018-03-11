package com.dbsystel.tibco.plugins.common;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;


public class AbstractTibcoBwDeploymentMojo extends AbstractMojo {
	
	public static final String DEBUG_MESSAGE_NO_CREDS = "No username and password in settings.xml file - falling back to CLI entry";
    public static final String DEBUG_MESSAGE_NO_ID = "No <id> element was found in the POM - Getting credentials from CLI entry";
    public static final String DEBUG_MESSAGE_NO_SERVER_SECTION = "No <server> section was found for the specified id";
    public static final String DEBUG_MESSAGE_NO_SETTINGS_FILE = "No settings.xml file was found in this Mojo's execution context";
    public static final String DEBUG_MESSAGE_POM_HAS_CREDS = "Getting credentials from the POM";
    public static final String DEBUG_MESSAGE_SETTINGS_HAS_CREDS = "Found username and password in the settings.xml file";
    public static final String DEBUG_MESSAGE_SETTINGS_HAS_ID = "Found the server's id in the settings.xml file";
	
	@Parameter(defaultValue = "${project}", readonly = true, required = true)
    protected MavenProject project;
	
	/**
	 * Path to the TIBCO home directory.
	 */
	@Parameter ( property = PropertyNames.TIBCO_HOME, required = true )
	protected File tibcoHome;
	
	/**
	 * Path to the TIBCO home directory.
	 */
	@Parameter ( property = PropertyNames.TIBCO_VERSION )
	protected String tibcoVersion;
	
	/**
	 * Path to the TIBCO "AppManage" TRA configuration file.
	 */
	@Parameter ( property = PropertyNames.TIBCO_APPMANAGE_TRA )
	protected File tibcoAppManageTRAPath;
	
	/**
	 * Path to the TIBCO "buildear" TRA configuration file.
	 */
	@Parameter( property = PropertyNames.TIBCO_BUILDEAR_TRA )
	protected File tibcoBuildEARTRAPath;

	 /**
     * Specifies the username to use if prompted to authenticate by the server.
     * <p/>
     * If no username is specified and the server requests authentication the user
     * will be prompted to supply the username,
     */
    @Parameter(property = PropertyNames.USERNAME)
    private String username;

    /**
     * Specifies the password to use if prompted to authenticate by the server.
     * <p/>
     * If no password is specified and the server requests authentication the user
     * will be prompted to supply the password,
     */
    @Parameter(property = PropertyNames.PASSWORD)
    private String password;
    
    /**
     * Specifies the id of the server if the username and password is to be
     * retrieved from the settings.xml file
     */
    @Parameter(property = PropertyNames.ID)
    private String id;
    
    /**
     * Provides a reference to the settings file.
     */
    @Parameter(property = "settings", readonly = true, required = true, defaultValue = "${settings}")
    private Settings settings;
    
	/**
     * Indicates whether or not the goal should be skipped.
     */
	@Parameter(property = PropertyNames.SKIP, defaultValue = "false")
	protected boolean skip;
	
	/**
     * Indicates whether or not the goal should be skipped.
     *
     * @return {@code true} to skip the goal, otherwise {@code false}
     */
    protected boolean isSkip() {
        return skip;
    }

	public void execute() throws MojoExecutionException, MojoFailureException {
		if (isSkip()) {
            getLog().debug(String.format("Skipping deployment of %s:%s", project.getGroupId(), project.getArtifactId()));
            return;
        }
	}

}
