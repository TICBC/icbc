export default () => {
  'ngInject';
    return {
        arraySub: (array1, array2) => {
        	let result = [];
            for (var i = array1.length - 1; i >= 0; i--) {
        		if (array2.indexOf(array1[i]) === -1) {
        			result.push(array1[i]);
        		}
        	}
        	return result;
        }
    }
}