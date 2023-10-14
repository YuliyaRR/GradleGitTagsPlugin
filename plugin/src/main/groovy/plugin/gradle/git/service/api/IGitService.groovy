package plugin.gradle.git.service.api

interface IGitService {
    boolean isGitInstalled()
    boolean doesRemoteRepoExist()
    boolean doesAnyCommitExistInRepo()
    boolean doesLastCommitHaveTag()
    String getLastVersion()
    int checkUncommittedFiles()
    String getCurrentBranch()
    String getNextVersion(String curVersion, String curBranch)
    boolean assignNewVersion(String nextVersion)
    boolean publishNewVersion(String newVersion)

}