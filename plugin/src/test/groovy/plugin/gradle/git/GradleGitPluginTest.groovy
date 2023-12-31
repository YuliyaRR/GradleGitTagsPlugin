/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package plugin.gradle.git

import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import spock.lang.Specification

/**
 * A simple unit test for the 'plugin_gradle_git.greeting' plugin.
 */
class GradleGitPluginTest extends Specification {
    def "plugin registers task"() {
        given:
        def project = ProjectBuilder.builder().build()

        when:
        project.plugins.apply("plugin_gradle_git.greeting")

        then:
        project.tasks.findByName("greeting") != null
    }
}
