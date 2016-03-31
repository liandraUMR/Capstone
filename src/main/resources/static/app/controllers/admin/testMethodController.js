'use strict';

angular.module('appController')

    .controller('AdminTestMethodOverviewController', function ($scope, TestMethodService, DeviceService,
                                                               ToastService, DialogService, GridRequestModel) {

        $scope.setActiveService(TestMethodService);

        $scope.data = {};
        $scope.data.message = 'Admin Test Method Overview Page';

        $scope.getGrid = function (options) {
	        options.ignoredColumns = ['id', 'deviceId'];
            return TestMethodService.getGrid(options);
        };

	    $scope.goToAddTestMethod = function ($event) {
		    $scope.testMethod = {};
		    $scope.dialogTitle = 'Add Test Method';
		    getDeviceOptions();
		    DialogService.showDialog($scope, $event, '/views/admin/testMethod/add.html');
	    };

	    $scope.goToEditTestMethod = function ($event) {

		    $scope.testMethod = {};
		    $scope.dialogTitle = 'Edit Test Method';

		    $scope.findOne($scope.options.selected[0].id)
			    .then(function(resp){
				    $scope.testMethod.id = resp.data.id;
				    $scope.testMethod.name = resp.data.name;
				    setDeviceSelection(resp.data.deviceId);
				    DialogService.showDialog($scope, $event, '/views/admin/testMethod/edit.html');
			    })
			    .catch(function (error) {
				    ToastService.error('Error Retrieving Test Method');
			    });
	    };

	    $scope.createTestMethod = function() {
		    var testMethod = new TestMethod();

		    testMethod.name = $scope.testMethod.name;
		    testMethod.deviceId = $scope.testMethod.device.value;
		    testMethod.deviceName = $scope.testMethod.device.display;

		    $scope.create(testMethod)
			    .then(function (resp) {
				    ToastService.success('Test Method Saved');
			    })
			    .catch(function (error) {
				    ToastService.error('Error Saving Test Method');
			    })
			    .finally( function() {
				    $scope.closeDialog();
				    var model = GridRequestModel.newGridRequestModel();
				    $scope.options.updateGrid(model);
			    });
	    };

        $scope.updateTestMethod = function () {
            var testMethod = new TestMethod();

            testMethod.id = $scope.testMethod.id;
            testMethod.name = $scope.testMethod.name;
	        testMethod.deviceId = $scope.testMethod.device.value;
	        testMethod.deviceName = $scope.testMethod.device.display;

            $scope.update(testMethod)
                .then(function(resp){
                    ToastService.success('Test Method Updated');
                })
                .catch(function(error){
                    ToastService.error('Error Updating Test Method');
                })
	            .finally( function() {
		            $scope.closeDialog();
		            var model = GridRequestModel.newGridRequestModel();
		            $scope.options.updateGrid(model);
	            });
        };

	    $scope.closeDialog = function () {
		    DialogService.close();
	    };

	    function getDeviceOptions() {
		    DeviceService.singleSelect().then(function (resp) {
			    $scope.deviceOptions = resp.data;
		    });
	    }

	    function setDeviceSelection(value) {
		    DeviceService.singleSelect().then(function (resp) {
			    $scope.deviceOptions = resp.data;
			    for (var i = 0; i < $scope.deviceOptions.length; i++) {
				    if ($scope.deviceOptions[i].value === value) {
					    $scope.testMethod.device = $scope.deviceOptions[i];
				    }
			    }
		    });
	    }
    });