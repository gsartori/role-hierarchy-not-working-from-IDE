package test

import grails.boot.*
import grails.boot.config.GrailsAutoConfiguration
import test.Application;

class Application extends GrailsAutoConfiguration implements GrailsPluginApplication {
	
	static void printClassPath(classLoader) {
		println "$classLoader"
		classLoader.getURLs().each {url->
		   println "- ${url.toString()}"
		}
		if (classLoader.parent) {
		   printClassPath(classLoader.parent)
		}
	  }
	
    static void main(String[] args) {
		
		printClassPath(Application.classLoader)
		
        GrailsApp.run(Application, args)
    }
}
