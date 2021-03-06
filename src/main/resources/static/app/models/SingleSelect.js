'use strict';

/**
 * All the options that appear in the Single Select that are hard-coded.
 */
angular.module('appModel').factory('SingleSelect', ['Enum', function (Enum) {
    return {
        Status: [
            {display: Enum.Status.Active.display, value: Enum.Status.Active.value},
            {display: Enum.Status.Inactive.display, value: Enum.Status.Inactive.value}
        ],
        RoleType: [
            {display: Enum.RoleType.User.display, value: Enum.RoleType.User.value},
            {display: Enum.RoleType.Admin.display, value: Enum.RoleType.Admin.value}
        ],
        FilterType: [
            {display: Enum.FilterType.StartsWith.display, value: Enum.FilterType.StartsWith.value},
            {display: Enum.FilterType.EndsWith.display, value: Enum.FilterType.EndsWith.value},
            {display: Enum.FilterType.Contains.display, value: Enum.FilterType.Contains.value}
        ],
        GridStatus: [
            {display: Enum.Status.All.display, value: Enum.Status.All.value},
            {display: Enum.Status.Active.display, value: Enum.Status.Active.value},
            {display: Enum.Status.Inactive.display, value: Enum.Status.Inactive.value}
        ]
    }
}]);

