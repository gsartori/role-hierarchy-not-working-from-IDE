package test

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_TEST'])
class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'test')
    }
}
