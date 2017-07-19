angular.module('orderUtil', [])
.filter('orderStatusValue', function() {
  return function(input) {
    if(input === 1)
		return 'OPEN';
	else if(input === 2)
		return 'REFIL_INITIATED';
	else if(input === 3)
		return 'REFIL_COMPLETED';
	else if(input === 4)
		return 'RENT_INITIATED';
	else if(input === 5)
		return 'RENT_COMPLETED';
	else
		return '';
  };
});