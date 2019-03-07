<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Client Side jQuery DataTables</title>
    <!--引用css-->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" />
</head>
<body>

    
    <table id="myDataTalbe"  class="display"  >
        <thead>
            <!--必填-->

            <tr>
                <th>#</th>
                <th>MyTitle</th>
                <th>MyMoney</th>
                <th>ActionButton</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>Apple</td>
                <td>2000</td>
                <td>
                    <button type="button">Edit</button>
                    <button type="button">Delete</button>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>Banana</td>
                <td>3000</td>
                <td>
                    <button type="button">Edit</button>
                    <button type="button">Delete</button>
                </td>
            </tr>
            <tr>
                <td>3</td>
                <td>Cherry</td>
                <td>4000</td>
                <td>
                    <button type="button">Edit</button>
                    <button type="button">Delete</button>
                </td>
            </tr>
        </tbody>
    </table>
    <!--引用jQuery-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <!--引用dataTables.js-->
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
     
    <script type="text/javascript">
        $(function () {

            $("#myDataTalbe").DataTable({
                searching: false, //關閉filter功能
                columnDefs: [{
                    targets: [3],
                    orderable: false,
                }]
            });
        });
    </script>
</body>
</html>
