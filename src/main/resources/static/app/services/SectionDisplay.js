'use strict';

angular.module('appService').factory('SectionDisplay', function ($location, $filter) {
    var current = '';
    var titles = [
        'Device',
        'Project',
        'Sample',
        'Admin Client',
        'Admin Device',
        'Admin Investigator',
        'Admin Project',
        'Admin Test Method',
        'Admin Unit',
        'Admin User'
    ];
    update();

    return {
        getCurrent: getCurrent,
        update: update
    };

    function update(clickedTitle) {
        if (current === '' || !clickedTitle) {
            var loc = ($filter('convertCamel')($location.path())).split('/');
            var title = '';
            loc.forEach(function (item) {
                if (/\d/.test(item)) {
                    // title += '- ' + item;
                } else if (item.trim() !== 'Overview') {
                    title += item + ' ';
                }
            });
            current = title.trim() || 'Project';
        } else {
            current = clickedTitle;
        }
    }

    function getCurrent() {
        return current;
    }
});
