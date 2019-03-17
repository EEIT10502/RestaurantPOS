<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table >
	<div id="all">
		<from>
		<div id="calContent">
			<tbody>
				<th colspan="4"><input type="text" id="showResBox" readonly value="" required="required"></th>
				<tr>
					<td rowspan="2"><input type="button" value="上班"  style="height: 140px" id="checkIn"></td>
					<td><input type="button" id="cal" value="7" onclick="getNum('7')"></td>
					<td><input type="button" id="cal" value="8"	onclick="getNum('8')"></td>
					<td><input type="button" id="cal" value="9" onclick="getNum('9')"></td>
				</tr>
				
				<tr>
					<td><input type="button" id="cal" value="4"	onclick="getNum('4')"></td>
					<td><input type="button" id="cal" value="5"	onclick="getNum('5')"></td>
					<td><input type="button" id="cal" value="6"	onclick="getNum('6')"></td>
				</tr>

				<tr>
					<td rowspan="2"><input type="button" value="下班"  style="height: 140px" id="checkOut"></td>
					<td><input type="button" id="cal" value="1"	onclick="getNum('1')"></td>
					<td><input type="button" id="cal" value="2"	onclick="getNum('2')"></td>
					<td><input type="button" id="cal" value="3" onclick="getNum('3')"></td>
				</tr>
				<tr>

					<td><input type="button" id="cal" value="--" onclick="del()"></td>
					<td><input type="button" id="cal" value="0" onclick="getNum('0')"></td>
					<td><input type="button" id="cal" value="C"	onclick="clearRes()"></td>
				</tr>
				
			</tbody>
		</div>
		</from>
		<div>
</table>
