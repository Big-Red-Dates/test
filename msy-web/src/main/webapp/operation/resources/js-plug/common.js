//清空表单
function clearQueryForm(formId,text,hidden,select,textarea) {
	if (typeof $("#" + formId) == undefined) {
		return;
	}
	if(text){
		$("#" + formId + " input:text").each(function() {
			var isClear = $(this).attr("isClear");
			if (typeof isClear == 'undefined' || isClear == true) {
				$(this).val("");
			}
		});
	}
	if(hidden){
		$("#" + formId + " input:hidden").each(function() {
			$(this).val("");
		});
	}
	if(select){
		$("#" + formId + " select").each(function() {
			var isClear = $(this).attr("isClear");
			if (typeof isClear == 'undefined' || isClear == true) {
				$(this).get(0).selectedIndex = 0;
			}
		});
	}
	if(textarea){
		$("#" + formId + " textarea").each(function() {
			var isClear = $(this).attr("isClear");
			if (typeof isClear == 'undefined' || isClear == true) {
				$(this).html("");
			}
		});
	}
}