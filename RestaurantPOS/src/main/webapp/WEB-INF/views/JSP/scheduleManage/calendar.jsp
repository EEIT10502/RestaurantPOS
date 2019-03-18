<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<link
	href='${pageContext.request.contextPath }/assets/css/fullcalendar.min.css'
	rel='stylesheet' />
<link
	href='${pageContext.request.contextPath }/assets/css/fullcalendar.print.min.css'
	rel='stylesheet' media='print' />
<script
	src='${pageContext.request.contextPath }/assets/js/lib/moment.min.js'></script>
<script
	src='${pageContext.request.contextPath }/assets/js/lib/jquery.min.js'></script>
<script
	src='${pageContext.request.contextPath }/assets/js/lib/jquery-ui.min.js'></script>
<script
	src='${pageContext.request.contextPath }/assets/js/fullcalendar.min.js'></script>

<style>
body {
	margin-top: 40px;
	text-align: center;
	font-size: 14px;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
}

#wrap {
	width: 1100px;
	margin: 0 auto;
}

#external-events {
	float: left;
	width: 150px;
	padding: 0 10px;
	border: 1px solid #ccc;
	background: #eee;
	text-align: left;
}

#external-events h4 {
	font-size: 16px;
	margin-top: 0;
	padding-top: 1em;
}

#external-events .fc-event {
	margin: 10px 0;
	cursor: pointer;
}

#external-events p {
	margin: 1.5em 0;
	font-size: 11px;
	color: #666;
}

#external-events p input {
	margin: 0;
	vertical-align: middle;
}

#calendar {
	float: right;
	width: 900px;
}
</style>
</head>
<body>
	<div id='wrap'>

		<div id='external-events'>
			<h4>班表選擇</h4>
			<div class='fc-event'>早班</div>
			<div class='fc-event'>中班</div>
			<div class='fc-event'>晚班</div>
			<div class='fc-event'>兼A</div>
			<div class='fc-event'>兼B</div>
			<p>
				<input type='checkbox' id='drop-remove' /> <label for='drop-remove'>remove
					after drop</label>
			</p>
		</div>

		<div id='calendar'></div>

		<div style='clear: both'></div>

	</div>
</body>
<script>
	$(document).ready(
			function() {

				/* initialize the external events
				-----------------------------------------------------------------*/

				$('#external-events .fc-event').each(function() {

					// store data so the calendar knows to render an event upon drop
					$(this).data('event', {
						title : $.trim($(this).text()), // use the element's text as the event title
						stick : true
					// maintain when user navigates (see docs on the renderEvent method)
					});

					// make the event draggable using jQuery UI
					$(this).draggable({
						zIndex : 999,
						revert : true, // will cause the event to go back to its
						revertDuration : 0
					//  original position after the drag
					});

				});

				/* initialize the calendar
				-----------------------------------------------------------------*/

				$('#calendar').fullCalendar({
					header : {
						left : 'prev,next today',
						center : 'title',
						right : 'agendaWeek,month',
					},
					customButtons: {
				        promptResource: {
				          text: '+ room',
				          click: function() {
				            var title = prompt('Room name');
				            if (title) {
				              $('#calendar').fullCalendar(
				                'addResource',
				                { title: title },
				                true // scroll to the new resource?
				              );
				            }
				          }
				        }
				      },
		            
					editable : true,
					droppable : true, // this allows things to be dropped onto the calendar
					monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		            monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		            dayNames: ["日", "一", "二", "三", "四", "五", "六"],
		            dayNamesShort: ["日", "一", "二", "三", "四", "五", "六"],
					allDayText: '員工名稱',
// 					allDaySlot: false,	//allDay?域的文本?容
					timeFormat:'',
					
		            drop : function() {
						// is the "remove after drop" checkbox checked?
						if ($('#drop-remove').is(':checked')) {
							// if so, remove the element from the "Draggable Events" list
							$(this).remove();
						}
					}
					
				});
			});
</script>
</html>
