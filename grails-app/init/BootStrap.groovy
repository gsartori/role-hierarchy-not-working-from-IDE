import test.*

class BootStrap {

	def TestService

	def init = { servletContext ->

		TestService.with {
			
			createRole('ROLE_TEST')
			
			createUser(
				username: 'test',
				password: 'test',
				roles: ['ROLE_TEST'],
			)
			
		}

	}

	def destroy = {}

}
