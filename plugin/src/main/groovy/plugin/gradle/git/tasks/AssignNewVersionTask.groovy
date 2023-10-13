package plugin.gradle.git.tasks

import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class AssignNewVersionTask extends RootTask{
    Project project

    @Inject
    AssignNewVersionTask(Project project) {
        this.project = project
    }

    @TaskAction
    def assignNewVersion() {
        String newVersion = executeCalcTask()

        def res = gitService.assignNewVersion(newVersion)

        if(res) {
            logger.log(LogLevel.LIFECYCLE, "New version ${newVersion} is assigned")
        } else {
            logger.log(LogLevel.ERROR, "Something went wrong")
        }

        addResult(res)
    }

    private String executeCalcTask() {
        DefineNextVersionTask resTask = (DefineNextVersionTask) project.tasks.getByName('defineNextVersion')
        String nextVersion = (String) resTask.defineNextVersion()
        return nextVersion
    }


}
