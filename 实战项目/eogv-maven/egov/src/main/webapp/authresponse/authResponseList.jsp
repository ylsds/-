<%@page contentType="text/html;charset=GBK" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<script type="text/javascript" src="../clander/date.js"></script>
<script type="text/javascript" src="../clander/setday.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
  $(function () {
    selectAuth=function () {
    if ($("#authno").val()==""){
      alert("核准件编号不能为空！")
    }else{
      $.ajax({
        url:"${pageContext.request.contextPath}/servlet/SelectAuth",
        data:{
          "-":new Date().getTime(),
          "authno":$("#authno").val()
        },
        type:"post",
        dataType:"html",
        success:function (result) {
          $("#MyDiv").append(result);
        }
      })
    }
    }
  })
</script>
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.STYLE7 {font-size: 12}

-->
</style>

<script>
</script>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16" height="16" /> <span class="STYLE4">银行核准类信息列表</span></td>
        <td width="281" background="../images/tab_05.gif"><table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"></td>
                  </tr>
              </table></td>
            </tr>
        </table></td>
        <td width="14"><img src="../images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="5">
          <td width="9" background="../images/tab_12.gif">&nbsp;</td>
          <td bgcolor="#f3ffe3">&nbsp;</td>
          <td width="9" background="../images/tab_16.gif">&nbsp;</td>
      </tr>
      <tr>
        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3">
        	<table width="99%" border="0" align="center" cellpadding="0" cellspacing="1">
        	    <tr>
        	       <td width="90"  nowrap class="STYLE1" align="right">核准件编号:</td>
				    <td class="class1_td alignleft" nowrap><input type="text" name="authno" id="authno" style="width:100px; height:20px; border:solid 1px #035551; color:#000000"></td>
        	    </tr>
        	    <tr>
        	        <td class="STYLE1" colspan="5" align="left">&nbsp;&nbsp;</td>
        	        <td nowrap class="STYLE1" align="right"><button type="button" style="width:68px;height:27px" onclick="selectAuth()" ><img src="../images/query.jpg" /></button>&nbsp;&nbsp;<button type="submit" style="width:68px;height:27px"><img src="../images/clear.jpg" /></button>&nbsp;&nbsp;</td>
        	    </tr>
        	</table>
        </td>
        <td width="9" background="../images/tab_16.gif">&nbsp;</td>
      </tr>
      <tr height="10">
          <td width="9" background="../images/tab_12.gif">&nbsp;</td>
          <td bgcolor="#f3ffe3">&nbsp;</td>
          <td width="9" background="../images/tab_16.gif">&nbsp;</td>
      </tr>
  </table>
  </tr>
  <tr>
    <td></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="../images/tab_20.gif" width="15" height="29" /></td>
        <td background="../images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="25%" height="29" nowrap="nowrap"></td>
            <td width="75%" valign="top" class="STYLE1">
              <div align="right">
              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="30" height="22" valign="middle"><div align="right"></div></td>
                  <td width="30" height="22" valign="middle"><div align="right"></div></td>
                  <td width="30" height="22" valign="middle"><div align="right"></div></td>
                  <td width="30" height="22" valign="middle"><div align="right"></div></td>
                  <td width="59" height="22" valign="middle"><div align="right" class="STYLE2 STYLE1"></div></td>
                  <td width="25" height="22" valign="middle"></td>
                  <td width="23" height="22" valign="middle" class="STYLE2 STYLE1"></td>
                  <td width="30" height="22" valign="middle"></td>
                </tr>
              </table>
            </div></td>
          </tr>
        </table></td>
        <td width="14"><img src="../images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
<div id="MyDiv" align="center" style="border: 0.5px white solid"></div>
</body>
</html>
