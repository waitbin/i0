package com.thoughtworks.i0.plugins.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.hamcrest.CoreMatchers
import org.junit.BeforeClass
import org.junit.Test

import static org.junit.Assert.assertThat
import org.gradle.api.artifacts.Configuration

import static org.junit.Assert.fail
import org.gradle.api.artifacts.DependencySet

class I0PluginTest {
    private static Project project;

    @BeforeClass
    public static void before() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: 'i0'
    }

    @Test
    public void should_add_repositories() {
        assertThat(project.repositories.size(), CoreMatchers.is(2))
    }

    @Test
    public void should_add_java_basic_compile_dependencies() {
        Configuration compile = project.configurations.getByName("compile")

        checkDependencies(compile.allDependencies, I0Plugin.JAVAX_DEPENDENCIES)
        checkDependencies(compile.allDependencies, I0Plugin.FUNCTIONAL_JAVA)
        checkDependencies(compile.allDependencies, ['com.thoughtworks.i0:i0-core:0.1.0'])
    }

    @Test
    public void should_add_java_basic_runtime_dependencies() {
        Configuration runtime = project.configurations.getByName("runtime")

        checkDependencies(runtime.allDependencies, ['com.thoughtworks.i0:i0-core:0.1.0', 'org.hibernate:hibernate-validator:4.3.0.Final'])
    }

    @Test
    public void should_add_persistence_compile_dependencies() {
        Configuration compile = project.configurations.getByName("compile")

        checkDependencies(compile.allDependencies, I0Plugin.PERSISTENCE_API)
    }

    @Test
    public void should_add_persistence_runtime_dependencies() {
        Configuration runtime = project.configurations.getByName("runtime")

        checkDependencies(runtime.allDependencies, I0Plugin.HIBERNATE)
    }

    private void checkDependencies(DependencySet dependencies, ArrayList<String> expected) {
        for (dependency in expected) {
            if (dependencies.findAll {"$it.group:$it.name:$it.version" == dependency}.empty)
                fail("'$dependency' should be add to dependencies")
        }
    }
}
