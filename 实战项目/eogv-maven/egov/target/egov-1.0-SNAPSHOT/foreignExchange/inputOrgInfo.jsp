<%@page contentType="text/html;charset=GBK" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
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
</head>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/js/egov.js"></script>
<script type="text/javascript">
  $(function () {

    //auto计算注册基本总额/外资投资比例
    checkCty=function () {
      var regcapItems=document.getElementsByName("regcapItem");
      var ctys=document.getElementsByName("cty");
      var Wsum=0;
      var Zsum=0;
      for (var i=0;i<regcapItems.length;i++){
        Zsum+=parseInt(regcapItems[i].value.trim()==""? "0":regcapItems[i].value.trim());//此处在当表格数据为空时，通过parseInt()函数转换为NAN
        if (ctys[i].value!="中国"){
          Wsum+=parseInt(regcapItems[i].value.trim()==""? "0":regcapItems[i].value.trim());
        }
      }
      //总出资比列
      document.getElementById("regcap").value=Zsum;
      //外方出资
      document.getElementById("outregcap").value=Wsum;
      $("#tipScale").text((((Wsum*1.0)/Zsum)*100).toFixed(2)+"%")
    }
    //删除行
    DeleRow=function(tableRowId){
      var invListTable = document.getElementById ("invListTable");
      var tableRow = document.getElementById(tableRowId) ;
      invListTable.deleteRow(tableRow.rowIndex) ;

    }
    //添加表格行（使用Jquery尝试）
    Newline=function (invregnum,invname,cty) {
      //获取表格
      var invListTable=document.getElementById("invListTable");
      //获取表格现有的总行数
      var index=invListTable.rows.length;
      //在表格的末尾添加一行
      var tableRows=invListTable.insertRow();
      tableRows.style.background='#FFFFFF';
      //设置tableRow的ID
      tableRows.id=invregnum;
      //给新行添加5个单元格
      var tableCell0=tableRows.insertCell(0);
      var tableCell1=tableRows.insertCell(1);
      var tableCell2=tableRows.insertCell(2);
      var tableCell3=tableRows.insertCell(3);
      var tableCell4=tableRows.insertCell(4);
      //给每个单元格设置HTML
      tableCell0.innerHTML='<div align="center" style="padding:5px" class="STYLE2 STYLE1"><input type="hidden" name="invregnum" value="'+invregnum+'">'+invname+'</div>';
      tableCell1.innerHTML='<div align="center" style="padding:2px" class="STYLE2"><input type="hidden" name="cty" value="'+cty+'">'+cty+'</div>';
      tableCell2.innerHTML='<div align="center" style="padding:5px" class="STYLE2 STYLE1"><input type="text" name="regcapItem" id="regcapItem" onblur="checkCty()" style="width:90px; height:20px; border:solid 1px #035551; color:#000000"><font color="red">*</font></div>';
      tableCell3.innerHTML='<div align="center" style="padding:2px" class="STYLE2"><input type="text" name="scale" style="width:90px; height:20px; border:solid 1px #035551; color:#000000"><font color="red">*</font></div>';
      tableCell4.innerHTML='<div align="center" style="padding:2px" class="STYLE2"><img src="../images/delete.jpg" onclick="DeleRow('+invregnum+')"/></div>';
    }
    doSaveEnter=function () {
      var formItem1=new FormItem("外汇登记证号","regno");
      var formItem2=new FormItem("企业中文名称","cnname");
      var formItem3=new FormItem("注册币种","regcry");
      var formItemArray=[formItem1,formItem2,formItem3];
      var ok=eg.checkForms(formItemArray);
      if (ok){
        $("#Myform").submit();
      }
    }
  })


</script>
<body>
<form action="${pageContext.request.contextPath}/servlet/SaveEnterandAllinv" method="post" id="Myform">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16" height="16" /> <span class="STYLE4">新设外商企业登记-录入</span></td>
        <td width="281" background="../images/tab_05.gif"><table border="0" align="right" cellpadding="0" cellspacing="0">
        </table></td>
        <td width="14"><img src="../images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#0e6f68" >
          <tr>
            <td width="100" height="26" class="STYLE1" colspan="4"><div align="center" style="padding:5px" class="STYLE2 STYLE1"><font color="#FFFFFF"><B>企业基本信息</B></font></div></td>
          </tr>
          <tr>
            <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1"><div align="right" style="padding:5px" class="STYLE2 STYLE1">组织机构代码:</div></td>
            <td width="250" bgcolor="#FFFFFF" class="STYLE1"><div align="left" style="padding:2px" class="STYLE2"><%=request.getParameter("orgcode")%><input type="hidden" name="orgcode" value="<%=request.getParameter("orgcode")%>"/></div></td>
            <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1"><div align="right" style="padding:5px" class="STYLE2 STYLE1">外汇登记证号:</div></td>
            <td bgcolor="#FFFFFF" class="STYLE1"><div align="left" style="padding:2px" class="STYLE2"><input type="text" name="regno" id="regno" style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font color="red">*</font></div></td>
          </tr>
          <tr>
            <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1"><div align="right" style="padding:5px" class="STYLE2 STYLE1">企业中文名称:</div></td>
            <td width="250" bgcolor="#FFFFFF" class="STYLE1"><div align="left" style="padding:2px" class="STYLE2"><input type="text" name="cnname" id="cnname" style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font color="red">*</font></div></td>
            <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1"><div align="right" style="padding:5px" class="STYLE2 STYLE1">企业英文名称:</div></td>
            <td bgcolor="#FFFFFF" class="STYLE1"><div align="left" style="padding:2px" class="STYLE2"><input type="text" name="enname" style="width:150px; height:20px; border:solid 1px #035551; color:#000000"></div></td>
          </tr>
          <tr>
            <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1"><div align="right" style="padding:5px" class="STYLE2 STYLE1">联系人:</div></td>
            <td width="250" bgcolor="#FFFFFF" class="STYLE1"><div align="left" style="padding:2px" class="STYLE2"><input type="text" name="contactman" style="width:150px; height:20px; border:solid 1px #035551; color:#000000"></div></td>
            <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1"><div align="right" style="padding:5px" class="STYLE2 STYLE1">联系电话:</div></td>
            <td bgcolor="#FFFFFF" class="STYLE1"><div align="left" style="padding:2px" class="STYLE2"><input type="text" name="contacttel" style="width:150px; height:20px; border:solid 1px #035551; color:#000000"></div></td>
          </tr>
          <tr>
            <td width="100" height="26" class="STYLE1" colspan="4"><div align="center" width="100%" style="padding:5px" class="STYLE2 STYLE1"><font color="#FFFFFF"><B>企业资金情况信息</B></font></div></td>
          </tr>        
          <tr>
            <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1"><div align="right" style="padding:5px" class="STYLE2 STYLE1">注册资本:</div></td>
            <td width="250" bgcolor="#FFFFFF" class="STYLE1"><div align="left" style="padding:2px" class="STYLE2"><input type="text" name="regcap" id="regcap" readonly style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font color="red">*</font></div></td>
            <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1"><div align="right" style="padding:5px" class="STYLE2 STYLE1">注册币种:</div></td>
            <td bgcolor="#FFFFFF" class="STYLE1"><div align="left" style="padding:2px" class="STYLE2">
		      <select name="regcry" id="regcry" style="WIDTH:100px">
		        <option value="000">人民币</option>
		        <option value="001">美元</option>
		        <option value="002">日元</option>
		        <option value="003">英镑</option>
		      </select> <font color="red">*</font></div></td>
          </tr>
          <tr>
            <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1"><div align="right" style="padding:5px" class="STYLE2 STYLE1">外方注册资本:</div></td>
            <td width="250" bgcolor="#FFFFFF" class="STYLE1"><div align="left" style="padding:2px" class="STYLE2"><input type="text" name="outregcap" id="outregcap" readonly style="width:150px; height:20px; border:solid 1px #035551; color:#000000"><font color="red">*</font></div></td>
            <td width="100" bgcolor="#FFFFFF" height="26" class="STYLE1"><div align="right" style="padding:5px" class="STYLE2 STYLE1">外方出资比例:</div></td>
            <td bgcolor="#FFFFFF" class="STYLE1"><div align="left" style="padding:2px" class="STYLE2"><span id="tipScale">0%</span></div></td>
          </tr>
          <tr>
            <td width="100" height="26" class="STYLE1" colspan="4"><div align="center" style="padding:5px" class="STYLE2 STYLE1"><font color="#FFFFFF"><B>投资者资金及利润分配</B></font></div></td>
          </tr> 
          <tr>
            <td width="100%" bgcolor="#FFFFFF" class="STYLE1" colspan="4">
                <table  id="invListTable" border="0" width="100%" height="100%" cellpadding="0" cellspacing="1" bgcolor="#0e6f68">
		          <tr>
		            <td width="20%" bgcolor="#CCCCCC" height="20" class="STYLE1"><div align="center" style="padding:5px" class="STYLE2 STYLE1">投资者名称</div></td>
		            <td width="20%" bgcolor="#CCCCCC" class="STYLE1"><div align="center" style="padding:2px" class="STYLE2">国别</div></td>
		            <td width="20%" bgcolor="#CCCCCC" class="STYLE1"><div align="center" style="padding:5px" class="STYLE2 STYLE1">注册资本出资额</div></td>
		            <td width="20%" bgcolor="#CCCCCC" class="STYLE1"><div align="center" style="padding:2px" class="STYLE2">利润分配比例</div></td>
		            <td width="20%" bgcolor="#CCCCCC" class="STYLE1"><div align="center" style="padding:2px" class="STYLE2"><img src="../images/query.jpg" onclick="window.open('/egov/foreignExchange/orgcodeSelect.jsp', '选择组织机构代码', 'width=720, height=400, scrollbars=no')"/></div></td>
		          </tr>
                </table>
            </td>
          </tr> 
        </table></td>
        <td width="9" background="../images/tab_16.gif">&nbsp;</td>
        
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#0e6f68">
          <tr height="30"><td bgcolor="#FFFFFF" height="30" class="STYLE1" colspan="2" align="center"><img src="../images/ok.jpg" onclick="doSaveEnter()"/>&nbsp;&nbsp;<img src="../images/back.jpg" onclick="document.location='newInputOrg.html'"/></td></tr>
        </table></td>
        <td width="9" background="../images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="../images/tab_20.gif" width="15" height="29" /></td>
        <td background="../images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="75%" valign="top" class="STYLE1"><div align="left">
              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="62" height="22" valign="middle"></td>
                  <td width="50" height="22" valign="middle"></td>
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
</form>
</body>
</html>
