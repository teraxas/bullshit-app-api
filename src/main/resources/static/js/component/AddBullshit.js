angular.module('bullshitApp').component('addBullshitDialog', {
//  templateUrl: '/path/to/myComponent.html'
template: `<div class="modal-body"><div>{{$ctrl.greeting}}</div>
<label>Name To Edit</label> <input ng-model="$ctrl.modalData.name"><br>
<label>Value To Edit</label> <input ng-model="$ctrl.modalData.value"><br>
<button class="btn btn-warning" type="button" ng-click="$ctrl.handleClose()">Close Modal</button>
<button class="btn btn-warning" type="button" ng-click="$ctrl.handleDismiss()">Dimiss Modal</button>
</div>`,
  bindings: {
    modalInstance: "<",
    resolve: "<"
  },
  controller: [function() {
    var $ctrl = this;
    $ctrl.modalData = $ctrl.resolve.modalData;
    $ctrl.handleClose = function() {
      console.info("in handle close");
      $ctrl.modalInstance.close($ctrl.modalData);
    };
    $ctrl.handleDismiss = function() {
      console.info("in handle dismiss");
      $ctrl.modalInstance.dismiss("cancel");
    };
  }]
});