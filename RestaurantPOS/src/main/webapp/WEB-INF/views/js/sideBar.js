//打開選單
	function openHRList() {
		var x = document.getElementById("demoHRList");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
			x.previousElementSibling.className += " my-barHover";
		} else {
			x.className = x.className.replace(" w3-show", "");
			x.previousElementSibling.className = x.previousElementSibling.className
					.replace(" my-barHover", "");
		}
	}
//打開商品選單
	function openGoodsList() {
		var x = document.getElementById("demoGoodsList");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
			x.previousElementSibling.className += " my-barHover";
		} else {
			x.className = x.className.replace(" w3-show", "");
			x.previousElementSibling.className = x.previousElementSibling.className
					.replace(" my-barHover", "");
		}
	}
	//打開排班選單
	function openScheduleList() {
		var x = document.getElementById("demoScheduleList");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
			x.previousElementSibling.className += " my-barHover";
		} else {
			x.className = x.className.replace(" w3-show", "");
			x.previousElementSibling.className = x.previousElementSibling.className
					.replace(" my-barHover", "");
		}
	}
//打開報表選單
	function openReportList() {
		var x = document.getElementById("demoReportList");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
			x.previousElementSibling.className += " my-barHover";
		} else {
			x.className = x.className.replace(" w3-show", "");
			x.previousElementSibling.className = x.previousElementSibling.className
					.replace(" my-barHover", "");
		}
	}