package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction

class CheckGitAndLocalRepoExistTask extends RootTask{

    @TaskAction
    def checkGitIsInstalledAndLocalRepoExist() {
        def installed = gitService.isGitInstalled()

        if(!installed) {
            this.addResult(installed)
            logger.log(LogLevel.WARN, "Git isn't installed in your system. You have to install it to continue working")
        } else {
            def doesLocalRepoExist = this.getProject()
                    .getRootDir()
                    .toPath()
                    .resolve(".git")
                    .toFile()
                    .exists()

            if(!doesLocalRepoExist) {
                logger.log(LogLevel.WARN, "You don't have a local repo for your project. Use \'git init\' to create it")
            }

            this.addResult(doesLocalRepoExist)
        }
    }


}
