var mainApp = angular.module("app", ['ui.bootstrap']);

mainApp.controller('TableFilterController', function($scope, $uibModal, $http, $log) {
    const contextPath = 'http://localhost:8081/';

    var parent = this;
    parent.fillTable = function () {
        $http.get(contextPath + "product")
            .then(function (resp) {
                $scope.Products = resp.data
            })
    }

    $scope.showProduct = function(p) {

        $uibModal.open({
            templateUrl: 'modalForm.html',
            backdrop: true,
            windowClass: 'modal',
            controller: function($scope, $uibModalInstance, $log) {
                $scope.selected = p;
                $scope.submit = function() {
                    saveProduct(p)
                    return

                }
                $scope.cancel = function() {
                    $uibModalInstance.close('Dismissed');
                };

                $scope.saveProduct = function (p) {
                    $http.post(contextPath + 'product', p)
                        .then($uibModalInstance.close('Запись добавлена!'))

                }
            },
            resolve: {
                object: function() {
                    return $scope.selected;
                }
            }

        }).result.then(function (some){
            alert("reload")
            parent.fillTable()
        });
    };

    $scope.isSelected = function(p) {
        return $scope.selected === p;
    }




    $scope.removeProduct = function (id) {
        $http.delete(contextPath + 'product/' + id)
            .then(function (resp) {
                parent.fillTable()
            })
    }

    $scope.filterProduct = function(filterModel){
        $http.post(contextPath + 'productFilter/', filterModel)
            .then(function (resp) {
                $scope.Products = resp.data
            })
    }

    $scope.Products = parent.fillTable()
})

