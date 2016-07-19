package test

class TestService {

	def springSecurityService

    Role createRole(String aRole) {

		def role = Role.findByAuthority(aRole)
        if (role)
            return role

        // Role does not exist we create it
        //
	    role = new Role(authority: aRole).save()

	    // Set ROLE_ADMIN in higher hierarchy so to avoid giving
	    // ADMIN permissions to each controller
        if (role.authority != 'ROLE_ADMIN') {
            new RoleHierarchyEntry(entry: 'ROLE_ADMIN > ' + role.authority).save()
            springSecurityService.reloadDBRoleHierarchy()
        }

        return role
    }
	
	User createUser(Map map) {

		def user = new User(
			username: map.username,
			password: map.password ?: 'test'
			).save()

		def roles = map.roles
		if (roles instanceof String) roles = [roles]

		roles.each {
			UserRole.create user, createRole(it)
		}

		UserRole.withSession {
			it.flush()
			it.clear()
		}

		return user
	}

}
