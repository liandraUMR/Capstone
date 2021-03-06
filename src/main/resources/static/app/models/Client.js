'use strict';

/**
 * @ngdoc model
 * @memberof appModel
 * @name Client
 *
 * @description The Client class is a copy to the ClientModel in the backend.
 * A Client is a person who sponsors the Environmental Technologies projects.
 */
class Client {
    constructor(id, name, contact, phoneNumber, email, status, comment) {
        if (!arguments.length) {
            this.id = "00000000-0000-0000-0000-000000000000";
            this.name = null;
            this.phoneNumber = null;
            this.email = null;
            this.status = null;
            this.comment = null;
        }
        else {

            this.id = id;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.status = status;
            this.comment = comment;
        }
    }
}