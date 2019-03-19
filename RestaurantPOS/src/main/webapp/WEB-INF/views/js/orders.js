	var itemNo = 0;
	
	$(function() {
		// 選類別
		$("#oc1").click(function() {
			$("#cTable").hide();
			$("#pTable").show();
		});
		
		// 上一步回類別
		$("#oBPage").click(function() {
			$("#pTable").hide();
			$('#cTable').show();
		});
		
		// 炒飯點餐
		$("#op1").click(function() {
			$("#opItem1").html($("#op1").val());
			$("#opPrice1").html($("#opHidden1").val());
			$("#opSubtotal1").html($("#opHidden1").val() * $("#opQty1").val());
		});
		
		// 折扣修改
		$("#opQMod1").click(function() {
			$("#opSubtotal1").html($("#opHidden1").val() * $("#opQty1").val());
		});
		
		// 刪除單品
		$("#opDelete1").click(function() {
			$("#ol1").remove();
		});
		
		// 口味調整
		$("#opFlaver1").click(function() {
			$("#fDiv").popover("toggle");
		});
		
		$(".pt").click(function() {			
			var v = $(this).val();
			var total=0;
			var total1=0;
			var total1 = parseInt($("#oTotal").html());
			
			
			$.ajax({
			    url: '/RestaurantPOS/order/getPrice',
			    type: 'post',
			    data: {"product" : v},
			    error: function(xhr) {
			      	alert('Ajax request 發生錯誤');
			    },
			    success: function(response) {
			        var vPrice = response.price;
			        var vCategory = response.cate;
			        var vProductNo = response.productNo;
				    var subTotal = vPrice * 1
				
			        var row = '';
					row += '<tr>';
					row += '	<td id="opItem' + itemNo + '" name="opItem' + itemNo + '">' + v + '</td>';					
					row += '	<td id="opPrice' + itemNo + '" name="opPrice' + itemNo + '">' + vPrice + '</td>';					
					row += '	<td>';
					row += '		<input type="number" value="1" min="1" max="10" id="opQty' + itemNo + '" name="orderVos[' + itemNo + '].qty">';
					row += '		<input type="button" value="修改" id="opQMod' + itemNo + '" name="opQMod' + itemNo + '" onclick="modifyQty('+itemNo+',' + vPrice + ');"></td>';
					row += '	</td>';
					row += '	<td id="opSubtotal' + itemNo + '" name="opSubtotal' + itemNo + '">' + subTotal + '</td>';					
					row += '	<td id="" name=""></td>';
					row += '	<td>';
					row += '		<input type="button" value="口味" id="opFlaver' + itemNo + '" name="opFlaver' + itemNo + '">';
					row += '		<input type="button" value="刪除" id="opDelete' + itemNo + '" name="opDelete' + itemNo + '" onclick="delItem(this);">';
					row += '	</td>';
					
					row += '	<input type="hidden" id="hidOpItem' + itemNo + '" name="orderVos[' + itemNo + '].itemName" value="' + v + '" />';
					row += '	<input type="hidden" id="hidOpPrice' + itemNo + '" name="orderVos[' + itemNo + '].price" value="' + vPrice + '" />';
					row += '	<input type="hidden" id="hidSubtotal' + itemNo + '" name="orderVos[' + itemNo + '].subTotal" value="' + subTotal + '" />';
					row += '	<input type="hidden" id="hidCategory' + itemNo + '" name="orderVos[' + itemNo + '].category" value="' + vCategory + '" />';
					row += '	<input type="hidden" id="hidProductNo' + itemNo + '" name="orderVos[' + itemNo + '].productNo" value="' + vProductNo + '" />';
					row += '</tr>';
				
					$('#oL1').after(row);
					var subTotal1 = parseInt($("#opSubtotal" + itemNo).html());
					total= total1 + subTotal1; //總金額加總
					$("#oTotal").html(total);
					$('#hidoTotal').attr("value",total);
					itemNo++;
				
			    }
			});
			
		});
		//檢查來客數、叫號機號碼是否為空白
		$('#oNext').click(function() {
			var people = $('#oPeople').val();
			var call = $('#oCall').val();
			
			if (!people) {
				alert('「請輸入用餐人數」');
				return;
			}
			if(!call){
				alert('「請輸入叫號機號碼」');
				return;
			}
			
			if(call<1 || call >10){
				alert('「請輸入1-10」');
				return;
			}
			
	
			
			$('#dataForm').submit();
		});
	});
	
	function deltable(){
		$("#oTotal").html("0");
		$("#tablelist  tr:not(:first):not(:last)").empty("");
	}
	
	function delItem(obj) {
		$(obj).closest('tr').remove();
	}
	
	function modifyQty(itemNo, price){
		var qty = parseInt($("#opQty"+ itemNo).val());//修改單品數量，小計連動。
		var subTotal = qty*price;
		$('#opSubtotal' + itemNo).html(subTotal);
		$('#hidSubtotal' + itemNo).val(subTotal);
		
		var y = parseInt($("#oTotal").html()); //修改單品數量，總金額連動。
		var totalAmount=0;
		totalAmount = y + (qty-1)*price;
		$("#oTotal").html(totalAmount);
		$('#hidoTotal').attr("value",totalAmount);
		
	}
	
	function riceList() {
		var x = document.getElementById("riceList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function soupList() {
		var x = document.getElementById("soupList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function noodleList() {
		var x = document.getElementById("noodleList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	function vegetableList() {
		var x = document.getElementById("vegetableList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function sidedishList() {
		var x = document.getElementById("sidedishList");
		hiddenAllList();
		x.className = x.className.replace("hiddenList", "");
	}
	
	function hiddenAllList() {
		var rice = document.getElementById("riceList");
		var noodle = document.getElementById("noodleList");
		var soup = document.getElementById("soupList");
		var vegetable = document.getElementById("vegetableList");
		var sidedish = document.getElementById("sidedishList");
		
		// 		if (allProduct.className.indexOf("hiddenList") == -1) {
		// 			allProduct.className += "hiddenList";
		// 		} 
		if (rice.className.indexOf("hiddenList") == -1) {
			rice.className += "hiddenList";
		}
		
		if (soup.className.indexOf("hiddenList") == -1) {
			soup.className += "hiddenList";
		}
		
		if (noodle.className.indexOf("hiddenList") == -1) {
			noodle.className += "hiddenList";
		}
		
		if (vegetable.className.indexOf("hiddenList") == -1) {
			vegetable.className += "hiddenList";
		}
		
		if (sidedish.className.indexOf("hiddenList") == -1) {
			sidedish.className += "hiddenList";
		}
	}