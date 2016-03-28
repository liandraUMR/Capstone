'use strict';

angular.module('appController')

	.controller('AdminUnitOverviewController', function ($scope, UnitService, ToastService, $mdDialog, GridRequestModel) {

		$scope.setActiveService(UnitService);

		$scope.data = {};
		$scope.data.message = "Admin Unit Overview Page";

		$scope.getGrid = function (options) {
			options.ignoredColumns = ['id'];
			return UnitService.getGrid(options);
		};

		$scope.goToAddUnit = function ($event) {
			$scope.dialogTitle = "Add Unit";

			$scope.unit = {};

			$mdDialog.show({
				scope: $scope,
				templateUrl: '/views/admin/unit/add.html',
				parent: angular.element(document.body),
				targetEvent: $event,
				fullscreen: false
			});
		};

		$scope.createUnit = function() {
			var unit = new Unit();

			unit.name = $scope.unit.name;

			$scope.create(unit)
				.then(function (resp) {
					ToastService.success('Saved Unit');
				})
				.catch(function (error) {
					ToastService.error('Cannot Save Unit');
				})
				.finally( function() {
					var model = GridRequestModel.newGridRequestModel();
					$scope.options.updateGrid(model);
				});

			$scope.closeDialog();
		};

		$scope.closeDialog = function () {
			$mdDialog.destroy();
		};
	});