export default ($scope, $rootScope, qService, materialsRes, ToasterTool, resultsRes, BASE_URL) => {
	'ngInject';

	const isNull = (value) => {
    	return typeof(value) == undefined || value == null;
    };

	$scope.getAll = () => {
		$rootScope.loading = true;
		qService.httpGetWithToken(materialsRes.materialsAll, {}, {}).then((data) => {
	        if (data.success) {
	        	ToasterTool.success("查找成功");
	            $scope.items = data.data;
	        } else {
	        	$scope.items = null;
	        }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	$scope.items = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	};
	$scope.params = {};
	$scope.setCategory = (chName, enName) => {
		$scope.category = chName;
		$scope.params.column = enName;
	}
	$scope.setCategory('物料名称', 'name');

	$scope.getSome = () => {
		if (isNull($scope.params.value)) {
			ToasterTool.warning("输入不能为空");
			return;
		}
		$rootScope.loading = true;
		const params = {
			"column": $scope.params.column,
			"value": $scope.params.value,
		}
		qService.httpGetWithToken(materialsRes.materials, params, {}).then((data) => {
	        if (data.success) {
	            if (data.data == null) {
	            	ToasterTool.error("无结果");
	            	$scope.items = null;
	            } else {
	            	ToasterTool.success("查找成功");
	            	$scope.items = data.data;
	            }
	        } else {
	        	$scope.items = null;
	        }
	    }, (err) => {
	    	ToasterTool.error("网络错误");
	    	$scope.items = null;
	    }).finally(() => {
	        $rootScope.loading = false;
	    });
	}

	$("#importBtn").hide(0);
	let materialDrop = document.getElementById('materialDrop');
	let importTip = document.getElementById('importTip');
	function handleDrop(e) {
		e.stopPropagation();
		e.preventDefault();

		$scope.excelData = [];

		let files = e.dataTransfer.files;
		let f = files[0];
		{
			let reader = new FileReader();
			let name = f.name;

			importTip.innerHTML = name;

			reader.onload = function(e) {
				let data = e.target.result;
				let workbook = XLSX.read(data, {type: 'binary'});
				// do something here
				let first_sheet_name = workbook.SheetNames[0];
				let worksheet = workbook.Sheets[first_sheet_name];

				let rowIndex = 0;
				let rowCount = worksheet["!range"].e.r;
				for (let i = 1; i <= rowCount; i++) {
					$scope.excelData[rowIndex] = [];
					$scope.excelData[rowIndex].push(worksheet['A'+i] === undefined? "": worksheet['A'+i].v);
					$scope.excelData[rowIndex].push(worksheet['B'+i] === undefined? "": worksheet['B'+i].v);
					$scope.excelData[rowIndex].push(worksheet['C'+i] === undefined? "": worksheet['C'+i].v);
					$scope.excelData[rowIndex].push(worksheet['D'+i] === undefined? "": worksheet['D'+i].v);
					$scope.excelData[rowIndex].push(worksheet['E'+i] === undefined? "": worksheet['E'+i].v);
					$scope.excelData[rowIndex].push(worksheet['F'+i] === undefined? "": worksheet['F'+i].v);
					$scope.excelData[rowIndex].push(worksheet['G'+i] === undefined? "": worksheet['G'+i].v);
					$scope.excelData[rowIndex].push(worksheet['H'+i] === undefined? "": worksheet['H'+i].v);
					$scope.excelData[rowIndex].push(worksheet['I'+i] === undefined? "": worksheet['I'+i].v);
					rowIndex += 1;
				}
				// console.log($scope.excelData);
				$("#importBtn").show(500);
				$("#importBtn").removeAttr('disabled');
			};
			reader.readAsBinaryString(f);
		}
	};
	function handleDragover(e) {
		e.stopPropagation();
		e.preventDefault();
		e.dataTransfer.dropEffect = 'copy';
	}
	materialDrop.addEventListener('dragenter', handleDragover, false);
	materialDrop.addEventListener('dragover', handleDragover, false);
	materialDrop.addEventListener('drop', handleDrop, false);

    $scope.importMaterials = () => {
    	$("#importBtn").attr('disabled',"true");
    	// $scope.importLoading = true;
    	let testRow = $scope.excelData[0];
    	if ( testRow[0]!=="行号" || testRow[1]!=="物料编码" || testRow[2]!=="物料描述" || testRow[3]!=="大类" || testRow[4]!=="中类" || testRow[5]!=="明细类" || testRow[6]!=="生产公司" || testRow[7]!=="产品线" || testRow[8]!=="面价" ) {
    		ToasterTool.error("EXCEL格式校验失败！");
    		return;
    	}
    	// ToasterTool.success("EXCEL格式校验成功,开始导入数据");
    	ToasterTool.warning("导入功能占时关闭");
    	// qService.httpPostWithToken(materialsRes.materials, {}, {}, $scope.excelData).then((data) => {
    		
	    //     if (data.success) {
	    //    		ToasterTool.success("全部导入成功");
	    //     } else {
	    //     	ToasterTool.error(data.message);
	    //     }
	    // }, (err) => {
	    // 	console.log("网络错误");
	    // }).finally(() => {
	    // 	$scope.importLoading = false;
	    // });
    }

	$scope.changeDiscount = (item) => {
		swal({
			title: "修改折扣",
			text: "输入新的折扣",
			type: "input",
			showCancelButton: true,
			closeOnConfirm: false,
			showLoaderOnConfirm: true,
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			inputValue: item.discount,
			animation: "slide-from-top",
			inputPlaceholder: "输入小数, 例: 0.78"
		}, function(inputValue){
			if (inputValue === false) return false;
			if (inputValue === "") {
				swal.showInputError("输入不能为空");
				return false
			}
			if (isNaN(inputValue)) {
				swal.showInputError("请输入数字");
				return false
			}
			let discount_back = item.discount; // 留待还原item
			let discountprice_back = item.discountprice;
			// 修改折扣价格
			item.discount = inputValue;
			item.discountprice = inputValue * item.marketprice;
			qService.httpPutWithToken(materialsRes.materials, {}, {}, item).then((data) => {
		        if (data.success) {
		       		swal("修改成功！");
		        } else {
		        	ToasterTool.error("未知服务器错误");
		        	item.discount = discount_back;
		       		item.discountprice = discountprice_back;
		        }
		    }, (err) => {
		    	ToasterTool.error("网络错误");
		    	item.discount = discount_back;
	       		item.discountprice = discountprice_back;
		    });
		});
	}
	$scope.changeName = (item) => {
		swal({
			title: "修改物料名称",
			text: "输入新的物料名称",
			type: "input",
			showCancelButton: true,
			closeOnConfirm: false,
			showLoaderOnConfirm: true,
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			inputValue: item.name,
			animation: "slide-from-top",
			inputPlaceholder: "输入文字, 例: 名称"
		}, function(inputValue){
			if (inputValue === false) return false;
			if (inputValue === "") {
				swal.showInputError("输入不能为空");
				return false
			}
			let name_back = item.name;
			item.name = inputValue;
			qService.httpPutWithToken(materialsRes.materials, {}, {}, item).then((data) => {
		        if (data.success) {
		       		swal("修改成功！");
		        } else {
		        	ToasterTool.error("未知服务器错误");
		        	item.name = name_back;
		        }
		    }, (err) => {
		    	ToasterTool.error("网络错误");
		    	item.name = name_back;
		    });
		});
	}
	$scope.changeRemarks = (item) => {
		swal({
			title: "修改物料备注",
			text: "输入新的备注",
			type: "input",
			showCancelButton: true,
			closeOnConfirm: false,
			showLoaderOnConfirm: true,
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			inputValue: item.remarks,
			animation: "slide-from-top",
			inputPlaceholder: "输入文字, 例: 备注"
		}, function(inputValue){
			if (inputValue === false) return false;
			if (inputValue === "") {
				swal.showInputError("输入不能为空");
				return false
			}
			let remarks_back = item.remarks;
			item.remarks = inputValue;
			qService.httpPutWithToken(materialsRes.materials, {}, {}, item).then((data) => {
		        if (data.success) {
		       		swal("修改成功！");
		        } else {
		        	ToasterTool.error("未知服务器错误");
		        	item.remarks = remarks_back;
		        }
		    }, (err) => {
		    	ToasterTool.error("网络错误");
		    	item.remarks = remarks_back;
		    });
		});
	}
	$scope.addToResult = (item) => {
		swal({
			title: "确定添加" + (item.name === null ? item.description:item.name) + "?",
			text: "输入添加数量:",
			type: "input",
			showCancelButton: true,
			closeOnConfirm: false,
			showLoaderOnConfirm: true,
			confirmButtonText: "确定",
			cancelButtonText: "取消",
			animation: "slide-from-top",
			inputPlaceholder: "输入整数, 例: 3"
		},function(inputValue){
			if (inputValue === false) return false;
			if (inputValue === "") {
				swal.showInputError("输入不能为空");
				return false
			}
			if (isNaN(inputValue)) {
				swal.showInputError("请输入数字");
				return false
			}
			const results = {
				mid: item.id,
				count: inputValue
			};
			qService.httpPostWithToken(resultsRes.results, {}, {}, results).then((data) => {
		        if (data.success) {
		       		swal("成功!", (item.name === null ? item.description:item.name) + "已添加到结果集!", "success");
		        } else {
		        	ToasterTool.error("未知服务器错误, 添加失败");
		        }
		    }, (err) => {
		    	ToasterTool.error("网络错误");
		    });
		});
	}
};