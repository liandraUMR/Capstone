'use strict';

angular.module('appController').controller('GridController', function($scope, SingleSelect, GridDetails){

    /**
     * Selected item in the grid
     */
    $scope.selectedRow = {};

    /**
     * The number of pages to show in the pagination for the grid.
     * Must be in an Array format for pagination to work properly.
     * @type {Array}
     */
    $scope.gridPages = new Array(3);

    /**
     * Deselect a row in the grid.
     */
    $scope.deselect = function(){};

    /**
     * Options to display in the single-select for number of items per page in the grid.
     * @type {Array}
     */
    $scope.perPageList = SingleSelect.GridSize;

    /**
     * The currently selected value in the dropdown box directly above.
     */
    $scope.perPage = SingleSelect.GridSize[0];

    /**
     * Total number of items in entire search size.
     * Used to calculate pagination numbers.
     * @type {number}
     */
    $scope.totalGridItems = 0;

    /**
     * Current pagination page that is active/being viewed
     * @type {number}
     */
    $scope.activeGridPage = 1;

    /**
     * All filters currently being applied.
     * @type {Array}
     */
    $scope.gridFilters = [];

    /**
     * Apply a new filter to the grid
     */
    $scope.applyFilter = function(){};

    /**
     * Remove a filter from the grid.
     */
    $scope.removeFilter = function() {};

    /**
     * Sort a field in ascending or descending order. Use {@link Enum.SortOrder}
     * @param $event
     */
    $scope.sortBy = function($event){};

    $scope.rowClick = function($event){
        var elem = $event.currentTarget;
        console.log(elem);
    };

    /**
     * This function will call the server for a new grid based on settings defined byt the user.
     */
    $scope.updateGrid = function() {
        var options = GridDetails.newGridDetails();
        options.pageSize = $scope.perPage.value;
        options.currentPage = $scope.activeGridPage;
        options.totalNumberOfItems = $scope.totalGridItems;
        options.filters = {};
        options.sortBy = {};
        options.serviceType = {};
    };
});
