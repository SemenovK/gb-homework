var mainApp = angular.module("app", ['ui.bootstrap']);

mainApp.controller('TableFilterController', function($scope, $uibModal, $http, $log) {
    const contextPath = 'http://localhost:8081/';

    var parent = this;
    parent.fillTable = function () {
        $http.get(contextPath + "api/v1/products")
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

                $scope.cancel = function() {
                    $uibModalInstance.close('Dismissed');
                };

                $scope.saveProduct = function (p) {
                    if(p!== undefined && p!== null)
                    $http.post(contextPath + 'api/v1/products', p)
                        .then($uibModalInstance.close('Запись добавлена!')
                        )
                }
                $scope.getCompanies = function(){
                    $http.get(contextPath + 'api/v1/company')
                        .then(function (resp) {
                            $scope.Companies = resp.data
                        })
                }
                $scope.getCompanies()

            },
            resolve: {
                object: function() {
                    return $scope.selected;
                }
            }

        }).result.then(function (some){
            parent.fillTable()
        });
    };



    $scope.removeProduct = function (id) {
        $http.delete(contextPath + 'api/v1/products/' + id)
            .then(function (resp) {
                parent.fillTable()
            })
    }

    $scope.filterProduct = function(filterModel){
        $http.post(contextPath + 'api/v1/products/filter', filterModel)
            .then(function (resp) {
                $scope.Products = resp.data
            })
    }

    $scope.Products = parent.fillTable()
})

