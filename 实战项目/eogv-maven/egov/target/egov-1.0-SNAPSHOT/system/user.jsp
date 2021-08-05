<%@ page import="java.util.List" %>
<%@ page import="com.domain.User" %>
<%@ page import="com.Util.StringUtil" %>
<%@ page import="com.domain.Page" %>
<%@page contentType="text/html; charset=GBK" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>无标题文档</title>
  <%
    List<User> userList=(List<User>)request.getAttribute("userList");
  %>

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
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
<script type="text/javascript">
  $(function () {

    //check选择图片联动
    dynamicUDImg=function (){
      var count=0;
      var getImgLength=document.getElementsByName("usercode");
      for (var i=0;i<getImgLength.length;i++){
        if (getImgLength[i].checked){
          count++;
        }
        //选中0个（删除和修改都不能用)
        if (count==0){
          $("#update").attr("src","../images/update_disabled.jpg");
          $("#delete").attr("src","../images/delete_disabled.jpg")
          $("#update").attr("style","cursor:default")
          $("#delete").attr("style","cursor:default")
          $("#update").click(function () {
            javascript:void(0);
          })
          $("#delete").click(function () {
            javascript:void(0);
          })



        }
        // 选中1个(删除和修改都能用)
        if (count==1){
          $("#update").attr("src","../images/update.jpg");
          $("#update").attr("style","cursor:pointer")
          $("#delete").attr("src","../images/delete.jpg");
          $("#delete").attr("style","cursor:pointer");
          $("#update").click(function () {
            $("#MyForm").attr("action","${pageContext.request.contextPath}/servlet/SelectUser");
            $("#MyForm").submit();
          })
          $("#delete").click(function () {
            $("#MyForm").attr("action","${pageContext.request.contextPath}/servlet/DeleteUser");
            window.confirm("您确认是否删除所选用户！！");
            $("#MyForm").submit();
          })

        }
        //选中1+个(修改不能用,删除能用)
        if (count>1){
          $("#update").attr("src","../images/update_disabled.jpg");
          $("#update").attr("style","cursor:default")
          $("#delete").attr("src","../images/delete.jpg")
          $("#delete").attr("style","cursor:pointer");
          $("#update").click(function () {
            javascript:void(0);
          })
          $("#delete").click(function () {
            $("#MyForm").attr("action","${pageContext.request.contextPath}/servlet/DeleteUser");
            window.confirm("您确认是否删除所选用户！！");
            $("#MyForm").submit();
          })
        }
        if (count==getImgLength.length){
          $("#checkboxAll")[0].checked=true;
           }
        else{
          $("#checkboxAll")[0].checked=false;
        }
    }
    }
    dynamicUDImg();

    checkboxAllUser=function(){
      var getImgLength=document.getElementsByName("usercode");
      var checkboxAllObj=$("#checkboxAll")[0];
      if (checkboxAllObj.checked){
        for (var i=0;i<getImgLength.length;i++){
          getImgLength[i].checked=true;
        }
      }
      else{
        for (var i=0;i<getImgLength.length;i++){
          getImgLength[i].checked=false;
        }
      }
    }
    checkboxAllUser();
    //页码更改
    chagePageno=function (pageno){
      document.location='${pageContext.request.contextPath}/servlet/PageQueryUserWL?pageno='+pageno;
    }
  })
</script>
<body>
<form action="#" method="post" id="MyForm">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="../images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="../images/tab_05.gif"><img src="../images/311.gif" width="16" height="16" /> <span class="STYLE4">系统用户列表</span></td>
        <td width="281" background="../images/tab_05.gif"><table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="../images/add.jpg" style="cursor:pointer" onclick="document.location='${pageContext.request.contextPath}/system/userAdd.jsp'"/></div></td>
                  </tr>
              </table></td>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="../images/update_disabled.jpg" id="update" /></div></td>
                  </tr>
              </table></td>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><img src="../images/delete_disabled.jpg" id="delete" disabled  /></div></td>
                  </tr>
              </table></td>
            </tr>
        </table></td>
        <td width="14"><img src="../images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr id="AddUserList">
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="../images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#0e6f68" >
          <tr>
            <td width="6%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">
              <input type="checkbox" name="checkboxAll" id="checkboxAll" value="checkbox" onclick="checkboxAllUser()" /></div></td>
            <td width="8%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
            <td width="12%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">用户代码</div></td>
            <td width="24%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">用户姓名</div></td>
            <td width="38%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">机构类型</div></td>
          </tr>

          <%
            if (userList!=null){
            int index=0;
            for (int i=0;i<userList.size();i++){
              User user=userList.get(i);
              %>
          <tr>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
              <input name="usercode" id="usercode" type="checkbox" class="STYLE2" value="<%=user.getUsercode()%>" onclick="dynamicUDImg()"/>
            </div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><%=++index%></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=user.getUsercode()%></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><%=user.getUsername()%></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" ><a href="#"><%=StringUtil.getTextByCode(user.getOrgtype())%></a></div></td>
          </tr>
          <%
            }
            }
          %>

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

            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE1">共${requestScope.page.totalsize}条纪录，当前第${requestScope.page.pageno}/${requestScope
                    .page.pagecount}页，每页${requestScope.page.pagesize}条纪录</span></td>
            <td width="75%" valign="top" class="STYLE1"><div align="right">

              <%
                Page pageInf=(Page) request.getAttribute("page");
                if(pageInf!=null){
              %>
              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="30" height="22" valign="middle"><div align="right"><img src="../images/firstPage<%=pageInf.getPageno()==1?"Disabled":""%>.gif" <%=pageInf.getPageno()==1?"":"style=\"cursor:pointer\" onclick='chagePageno(1)'"%> /></div></td>
                  <td width="30" height="22" valign="middle"><div align="right"><img src="../images/prevPage<%=pageInf.getPageno()==1?"Disabled":""%>.gif"  <%=pageInf.getPageno()==1?"":"style=\"cursor:pointer\" onclick='chagePageno("+(pageInf.getPageno()-1)+")'"%>/></div></td>
                  <td width="30" height="22" valign="middle"><div align="right"><img src="../images/nextPage<%=pageInf.getPageno()==pageInf.getPagecount()?"Disabled":""%>.gif" <%=pageInf.getPageno()==pageInf.getPagecount()?"":"style=\"cursor:pointer\" onclick='chagePageno("+(pageInf.getPageno()+1)+")'"%>/></div></td>
                  <td width="30" height="22" valign="middle"><div align="right"><img src="../images/lastPage<%=pageInf.getPageno()==pageInf.getPagecount()?"Disabled":""%>.gif" <%=pageInf.getPageno()==pageInf.getPagecount()?"":"style=\"cursor:pointer\" onclick='chagePageno("+pageInf.getPagecount()+")'"%>/></div></td>
                  <td width="59" height="22" valign="middle"><div align="right">转到第</div></td>
                  <td width="25" height="22" valign="middle"><span class="STYLE7">
                    <input name="pageno" type="text" class="STYLE1" style="height:14px; width:25px;text-align:right" size="5" />
                  </span></td>
                  <td width="23" height="22" valign="middle">页</td>
                  <td width="30" height="22" valign="middle"><img src="../images/go.gif" width="37" height="15" id="goPageNo" style="cursor: pointer"/></td>
                </tr>
              </table>
              <%
                }
              %>


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
