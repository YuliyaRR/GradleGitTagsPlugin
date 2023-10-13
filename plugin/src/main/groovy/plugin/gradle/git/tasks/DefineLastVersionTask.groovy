package plugin.gradle.git.tasks

import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction

class DefineLastVersionTask extends RootTask{

    @TaskAction
    def checkLastVersion() {
        def lastVersion = gitService.getLastVersion()

        logger.log(LogLevel.LIFECYCLE, "Last version - ${lastVersion}")
    }

}
