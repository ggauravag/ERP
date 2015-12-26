/**
 * New node file
 */

var fbks;

function showFeedback(radio) {
	var i = Number(radio.id);
	var html = "";
	for (var k = 0; k < fbks[i].questions.length; k++) {
		html += "<h4>Q." + (k + 1) + " " + fbks[i].questions[k].text + "</h4>";
		if (fbks[i].questions[k].type == "TEXT") {
			html += "<h5>" + fbks[i].questions[k].response + "</h5>";
		} else {
			switch (fbks[i].questions[k].response) {
			case "5":
				html += "<h5>Completely Agree</h5>";
				break;
			case "4":
				html += "<h5>Somewhat Agree</h5>";
				break;
			case "3":
				html += "<h5>Neither Agree nor Disagree</h5>";
				break;
			case "2":
				html += "<h5>Somewhat Disagree</h5>";
				break;
			case "1":
				html += "<h5>Completely Disagree</h5>";
				break;
			default:
				html += "<h5>Unknown - " + fbks[i].questions[k].response+"</h5>";
			}
		}

	}

	$("#feedbackQues").html(html);

}

$("#searchFeedback")
		.click(
				function() {

					var orderID = $("#inputOrderID").val();
					var name = $("#inputName").val();
					var mobile = $("#inputMobile").val();
					var fromDate = $("#inputFromDate").val();
					var toDate = $("#inputToDate").val();

					// alert(orderID+":"+name+":"+mobile+":"+fromDate+":"+toDate);
					if (orderID == "" && name == "" && mobile == ""
							&& (fromDate == "" || toDate == "")) {
						swal("Please select atleast one criterion to search !");
						return;
					}
						$("#loaderImage").show();
					$
							.ajax({
								url : $("#basePath").val() + "/ajaxServlet.do",
								data : {
									orderId : orderID,
									custName : name,
									custMobile : mobile,
									fromdate : fromDate,
									todate : toDate,
									action : "getFeedback"
								},
								success : function(data) {
									$("#loaderImage").hide();
									if (data.status) {
										fbks = new Array();
										var html = "";
										for (var i = 0; i < data.feedbacks.length; i++) {
											fbks.push(data.feedbacks[i]);
											html += "<tr>";
											html += "<td><div class='radio m-b-15'>";
											html += "<label><input type='radio' name='feedback' id='"
													+ i
													+ "' value='"
													+ data.feedbacks[i].id
													+ "' onclick='showFeedback(this)'><i class='input-helper'></i></label></div></td>";
											html += "<td>"
													+ data.feedbacks[i].id
													+ "</td>"
											html += "<td>"
													+ data.feedbacks[i].orderID
													+ "</td>"
											html += "<td>"
													+ data.feedbacks[i].generateDate
													+ "</td>";
											html += "<td>"
													+ data.feedbacks[i].submitDate
													+ "</td>";
											if (data.feedbacks[i].submitDate == "")
												html += "<td>Not Submitted</td>";
											else
												html += "<td>Submitted</td>";
											html += "</tr>";
										}
										$("#fillFeedbackDetails").html(html);
									} else {
										swal("No Feedbacks found ! Please try again !");
									}

								},
								error : function(data) {
									$("#loaderImage").hide();
									alert("Error : "+data);
								},
								method : "post"
							});

				});