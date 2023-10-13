package plugin.gradle.git.service.impl

import plugin.gradle.git.core.PluginConstants
import plugin.gradle.git.service.api.IGitService
import plugin.gradle.git.service.api.IVersionCreationService
import plugin.gradle.git.util.TagParserUtil

class GitServiceImpl implements IGitService{

    private final IVersionCreationService versionCreationService

    GitServiceImpl(IVersionCreationService versionCreationService) {
        this.versionCreationService = versionCreationService
    }

    @Override
    boolean isGitInstalled() {
        def process = PluginConstants.GIT_COMMAND_IS_GIT_INSTALLED.execute()
        def exit = process.waitFor()

        return exit == 0
    }

    @Override
    boolean isAnyCommitExistInRepo() {
        def lastCommit = PluginConstants.GIT_COMMAND_DOES_ANY_COMMIT_EXIST.execute().text

        return !lastCommit.isEmpty()
    }

    /*
    Если у последнего коммита есть тег, то строка будет не пустая и метод вернет - !false = true
    Если у последнего коммита нет тега, то строка будет пустая и метод вернет - !true = false
    * */
    @Override
    boolean doesLastCommitHaveTag() {
        def answer = PluginConstants.GIT_COMMAND_FOR_CHECKING_TAG_IN_LAST_COMMIT.execute().text
        return !answer.isEmpty()
    }

    @Override
    String getLastVersion() {
        def lastVersion
        def tags = PluginConstants.GIT_COMMAND_FOR_CHECKING_TAGS.execute().text

        if (tags.length() == 0) {
            lastVersion = "v0.0"
        } else {
            def arr = tags.split("\n")
            lastVersion = Arrays.stream(arr)
                .sorted((a, b) -> {

                    def majVerA = TagParserUtil.parseMajorVersion(a)
                    def majVerB = TagParserUtil.parseMajorVersion(b)

                    if (majVerA < majVerB) {
                        return -1
                    } else if (majVerA > majVerB) {
                        return 1
                    } else {
                        def minorVerA = TagParserUtil.parseMinorVersion(a)
                        def minorVerB = TagParserUtil.parseMinorVersion(b)

                        if (minorVerA < minorVerB) {
                            return -1
                        } else {
                            return 1
                        }
                    }
                })
                .reduce((min, max) -> max)
                .get()
        }

            return lastVersion
    }

    /**
     * Метод проверяет наличие незафиксированных изменений в текущем каталоге
     * return - количество файлов (неотслеживаемых и отслеживаемых, но модифицированных),
     *          0, если незафиксированнные изменения отсутствуют
     * */
    @Override
    int checkUncommittedFiles() {
        def filesInfo = PluginConstants.GIT_COMMAND_FOR_CHECKING_UNCOMMITTED_FILES.execute().text

        if(filesInfo.isEmpty()) {
            return 0
        }

        def statusArr = filesInfo.split("\n")

        return statusArr.length
    }

    @Override
    String getCurrentBranch() {
        def branches = PluginConstants.GIT_COMMAND_FOR_CHECKING_BRANCH.execute().text

        if(branches.isEmpty()) {
            return PluginConstants.DEFAULT_MAIN_BRANCH
        }

        def arr = branches.split("\n")

        def currentBranch = Arrays.stream(arr)
                .filter {it -> it.contains("*")}
                .find() - "* "

        return currentBranch
    }

    @Override
    String getNextVersion(String curVersion, String curBranch) {
        String newVersion

        switch (curBranch) {
            case PluginConstants.DEV_BRANCH:
            case PluginConstants.QA_BRANCH:
                def minorVersion = TagParserUtil.parseMinorVersion(curVersion)
                def newMinorVersion = minorVersion + 1

                newVersion = versionCreationService.createNewVersionInDevOrQABranch(curVersion, newMinorVersion)

                break

            case PluginConstants.STAGE_BRANCH:
                newVersion = versionCreationService.createNewVersionWithPostfix(curVersion, "-rc")

                break

            case PluginConstants.MASTER_BRANCH:
                def majorVersion = TagParserUtil.parseMajorVersion(curVersion)
                def newMajorVersion = majorVersion + 1

                newVersion = versionCreationService.createNewVersionInMasterBranch(curVersion, newMajorVersion)

                break

            default:
                newVersion = versionCreationService.createNewVersionWithPostfix(curVersion,"-SNAPSHOT")
        }

        return newVersion
    }

    @Override
    boolean assignNewVersion(String nextVersion) {
        def process = String.format(PluginConstants.GIT_COMMAND_ASSIGN_NEW_TAG, nextVersion).execute()
        return process.waitFor() == 0
    }

    @Override
    boolean publishNewVersion(String newVersion) {
        def process = String.format(PluginConstants.GIT_COMMAND_PUBLISH_NEW_TAG_TO_REMOTE_REPO, PluginConstants.NAME_GIT_REMOTE_REPO, newVersion).execute()
        return process.waitFor() == 0
    }
}
