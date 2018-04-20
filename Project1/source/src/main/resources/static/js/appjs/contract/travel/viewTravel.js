var prefix="/contract/travel"
$().ready(function() {
	 $('#travelDepartureDate').datetimepicker({
         format: 'YYYY-MM-DD',
         locale: moment.locale('zh-cn')
     });
	 $('#travelReturnDate').datetimepicker({
         format: 'YYYY-MM-DD',
         locale: moment.locale('zh-cn')
     });
});
