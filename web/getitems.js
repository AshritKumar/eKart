$("document").ready(function(){
	$("#catagory").change(function(){
		var id = $(this)[0].value;
		//Prepare an ajax call to set sub catagories
		var sessionID = $("#session").val();
		$.ajax({
			method: "GET",
			url: "displayItem;jsessionid="+sessionID,
			data : {"catID":id},
			success:function(data,status){
				var map = JSON.parse(data);
				
					//alert(data);
					var subList = $("#subCat");
					processOptions(subList,data, "Select SubCatagory");
			
				
				
			}
		});
	});
	
	//Items
	$("#subCat").change(function(){
		var id = $(this)[0].value;
		//Prepare an ajax call to items
		var sessionID = $("#session").val();
		$.ajax({
			method: "GET",
			url: "displayItem;jsessionid="+sessionID,
			data : {"subCatID":id},
			success:function(data,status){
				
					var itemList = $("#items");
					itemList.children().remove();
					itemList.append("<option>--Select Item--</option>");
					//alert(data);
					console.log(data);
					var map = JSON.parse(data);
					//processOptions(subList,data, "Select Item");
					$.each(map,function(k,v){
						itemList.append("<option value = "+v.itemID+">"+v.itemName+"</option>");
					});
					displayItems(map);
				
			
			}
		});
	});
	
	function processOptions(option,data, message){
		option.children().remove();
		var map = JSON.parse(data);
		option.append("<option>--"+message+"--</option>");
		$.each(map,function(k,v){
			option.append("<option value = "+k+">"+v+"</option>");
		});
	}
	
	function displayItems(map){
		//alert("hhhh");
		 $("#itemList").children().remove();
		var rows = Math.ceil(map.length/3);
		var columns = 3
		var row = $("<div />", {
		    class: 'row'
		});
		    //add columns to the the temp row object
		    for (var i = 0; i < map.length; i++) {
		    	var square = $("<div />", {
				    class: 'col-md-6 col-xs-12',
				    style: "background-color:lavenderblush",
				    id: "itemBox"+map[i].itemID
				});
		    	var iDetails = $("<p />", {
				});
		    	
		    	iDetails.append("<h4>"+map[i].itemName+"</h4><br>");
		    	//iDetails.append("<b>Description: </b>"+map[i].itemDesc+"<br>");
		    	iDetails.append("<b>price: </b>"+map[i].price+"<br>");
		    	iDetails.append("<b>Stock: </b>"+map[i].stock+"<br>");
		    	square.append(iDetails);
		    	//square.append("<p>Hiiiii</p>")
		    	square.append("<button type=\"button\" class=\"btn btn-success btn-md\" id=\"btnAddCart\"  data-toggle=\"modal\"  onclick=\"addCart("+map[i].itemID+") \">add to cart</button> &nbsp;");
		    	//square.append("<button type=\"button\" class=\"btn btn-warning btn-md\" onclick=\"viewDetails("+map[i].itemID+") id=\"viewDetails\" \">view details</button>");
		        row.append(square.clone());
		    }
		    //clone the temp row object with the columns to the wrapper
		    for (var i = 0; i < 1; i++) {
		        $("#itemList").append(row.clone());
		    }
		    
	}
	 
});



function addCart(itemID){
	//alert(itemID);
	$.ajax({
		method : "GET",
		url:"cartManager",
		data:{"addItemID":itemID},
/*		xhr: function(){
			var xhr = new XMLHttpRequest();
			xhr.addEventListener("progress", function(e){
				if (evt.lengthComputable) {
	                var percentComplete = evt.loaded / evt.total;
	                progressElem.html(Math.round(percentComplete * 100) + "%");
	            }
				
			},false);
		},*/
		beforeSend: function () {
	        //$("#addCart").show();
	    },
	    complete: function () {
	       // $("#addCart").hide();
	    },
	    success: function (data) {
	    	var dat = JSON.parse(data);
	    		if(dat.status){
	    			 var k = $("#itemBox"+itemID+" button");
	 	 	        //alert(k);
	 	 	        k.html('added to cart');
	 	 	        k.prop('disabled',true);
	 	 	        //alert(data);
	 	 	      var t = $("#addCartModel p");
	 	 	      t.html('Item added to cart');
	 	 	      $("#addCartModel").modal();
	 	 	       var btnData = $("#btnCart")
	 	 	        btnData.html('<span class="glyphicon glyphicon-shopping-cart" id="spnCart"></span>Cart '+dat.size);
	    		}
	    		else{
	    			var t = $("#addCartModel p")
	    			t.html('Item is already added to cart');
	    			 $("#addCartModel").modal();
	    		}
	    		
	    	
	       
	        
	    },
	    error: function (xhr, status, thrownError) {
	        alert("Some error occured . . .");
	        //alert(thrownError);
	    }
	});
}

