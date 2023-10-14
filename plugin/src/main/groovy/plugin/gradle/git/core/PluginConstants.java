package plugin.gradle.git.core;

public class PluginConstants {
    public final static String GIT_COMMAND_IS_GIT_INSTALLED = "git --version";
    public final static String GIT_COMMAND_FOR_CHECKING_REMOTE_REPO = "git remote";
    public final static String GIT_COMMAND_FOR_CHECKING_TAGS = "git tag";
    public final static String GIT_COMMAND_FOR_CHECKING_BRANCH = "git branch";
    public final static String GIT_COMMAND_ASSIGN_NEW_TAG = "git tag %s";
    public final static String GIT_COMMAND_PUBLISH_NEW_TAG_TO_REMOTE_REPO = "git push %s %s";
    public final static String GIT_COMMAND_DOES_ANY_COMMIT_EXIST = "git log";
    public final static String GIT_COMMAND_FOR_CHECKING_TAG_IN_LAST_COMMIT = "git describe --exact-match HEAD --tags";
    public final static String GIT_COMMAND_FOR_CHECKING_UNCOMMITTED_FILES = "git status --porcelain";
    public final static String NAME_GIT_REMOTE_REPO = "origin";
    public final static String RESULT_TASK = "res";
    public final static String TASK_GROUP = "git tags";
    public final static String LOG_MESSAGE_FOR_UNCOMMITTED_FILES = "%s-.uncommitted: You have %d uncommitted file(-s)";
    public final static String LOG_MESSAGE_FOR_UNCOMMITTED_FILES_WITHOUT_VERSION = "You have no tags but you have %d uncommitted file(-s)";
    public final static String DEV_BRANCH = "dev";
    public final static String QA_BRANCH = "qa";
    public final static String MASTER_BRANCH = "master";
    public final static String STAGE_BRANCH = "stage";
    public final static String DEFAULT_MAIN_BRANCH = "master";
    public final static String DEFAULT_START_VERSION = "v0.0";



}
