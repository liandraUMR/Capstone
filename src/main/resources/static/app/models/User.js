'use strict';

/**
 * @ngdoc model
 * @memberof appModel
 * @name User
 *
 * @description The User class is a copy to the UserModel in the backend.
 * A user contains all the information about the a person who works for the Environmental
 * Technologies lab
 */
class User {
    constructor(id, firstName, lastName, email, status, password, roleType) {
        if (!arguments.length) {
            this.id = "00000000-0000-0000-0000-000000000000";
            this.firstName = null;
            this.lastName = null;
            this.email = null;
            this.status = null;
            this.password = null;
            this.roleType = null;
        }
        else {

            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.status = status;
            this.roleType = roleType;
        }
    }
}