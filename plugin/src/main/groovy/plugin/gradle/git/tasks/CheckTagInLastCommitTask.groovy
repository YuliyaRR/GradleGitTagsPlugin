package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction

class CheckTagInLastCommitTask extends RootTask{

    @TaskAction
    def doesLastCommitHaveTag() {
        def res = gitService.doesLastCommitHaveTag()
        this.addResult(res)

        if (res) {
            logger.log(LogLevel.WARN, "You have tag in the last commit, so you can't assign another tag")
        }
    }
}
