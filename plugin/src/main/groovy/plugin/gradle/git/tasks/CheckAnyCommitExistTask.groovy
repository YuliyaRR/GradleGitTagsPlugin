package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction

class CheckAnyCommitExistTask extends RootTask {

    @TaskAction
    def doesAnyCommitExistInRepo() {
        def isCommitExistInRepo = gitService.doesAnyCommitExistInRepo()
        this.addResult(isCommitExistInRepo)

        if (!isCommitExistInRepo) {
            logger.log(LogLevel.WARN, "You have no commits so you can't define tag")
        }
    }
}
