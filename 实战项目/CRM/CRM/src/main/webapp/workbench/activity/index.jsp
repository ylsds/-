<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String basePath = request.getScheme() + "://"+
		request.getServerName() + ":" +request.getServerPort()
		+request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
<meta charset="UTF-8">

<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="jquery/bs_pagination/jquery.bs_pagination.min.js"></script>
	<script type="text/javascript" src="jquery/bs_pagination/en.js"></script>

<script type="text/javascript">

	$(function(){

		/**
		 * 页面加载完成后 自动获取活动列表
		 */
		pageList(1,3);

		/**
		 * 时间插件
		 */
		$(".time").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			todayBtn: true,
			pickerPosition: "bottom-left"
		});

		/**
		 * 获取所有--所有者--信息
		 */
		$("#addBtn").click(function () {
			//使用AXAJ获取user中的信息
			$.ajax({
				url:"activity/selectUsers",
				dataType:"json",
				type:"get",
				success:function (resp) {
					$("#create-marketActivityOwner").empty();
					$("#create-marketActivityOwner").html("<option value='${sessionScope.user.id}'>"+"${sessionScope.user.name}"
							+"</option>")
					$.each(resp ,function (i,n) {
						if (n.id!=${sessionScope.user.id}){
							$("#create-marketActivityOwner").html("<option value='"+n.id+"'>" +n.name+
									"</option>")
						}
					})
				}
			})
			$("#createActivityModal").modal("show");//弹出模态窗口
		})


		/**
		 * 添加活动信息
		 */
		$("#saveBtn").click(function () {
			$.ajax({
				url:"activity/save.do",
				data:{
					"owner":$.trim($("#create-marketActivityOwner").val()),
					"name":$.trim($("#create-marketActivityName").val()),
					"startDate":$.trim($("#create-startTime").val()),
					"endDate":$.trim($("#create-endTime").val()),
					"cost":$.trim($("#create-cost").val()),
					"description":$.trim($("#create-describe").val())
				},
				dataType:"json",
				type:"post",
				success:function (resp) {
					if (resp.success){
						//刷新市场活动列表

						alert("添加成功")
						$("#createActivityModal").modal("hide");
						// $("#activityAddForm")[0].reset();
						pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));

					}else{
						alert("添加失败！")
					}
				}
			})
		})

		/**
		 * 复选框的操作
		 */
		$("#sumCheckbox").click(function () {
			$("input[name=xz]").prop("checked",this.checked)
		})
		$("#addActivityTboby").on("click",$("input[name=xz]"),function () {
			$("#sumCheckbox").prop("checked",$("input[name=xz]").length==$("input[name=xz]:checked").length);
		})

		/**
		 * 批量删除活动信息
		 */
		$("#deleteBtn").click(function () {
			var $xz=$("input[name=xz]:checked");
			if ($xz.length==0){
				alert("请选择需要删除的记录");
			}else{
				var parameter="";
				for (var i=0;i<$xz.length;i++){
					parameter+="id=";
					parameter+=$xz[i].value;
					if (i!=$xz.length-1){
						parameter+="&";
					}
				}
				$.ajax({
					url:"activity/deleteActivity",
					data:parameter,
					dataType:"json",
					type:"post",
					success:function (data) {
						if (data){
							alert("删除成功！");

							//跳转到第一页，保持分页属性修改
							pageList(1,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
						}else{
							alert("删除失败");
						}
					},
					error:alert("删除失败！")
				})
			}
		})



		//点击修改进行相关操作
		$("#updateBtn").click(function () {

			var boxChecked=$("input[name=xz]:checked");
			if (boxChecked.length==0){
				alert("请选择需要修改的活动！")
			}else if (boxChecked.length>1){
				alert("单次只能修改一个活动信息！")
			}else{
				//使用AXAJ获取user中的信息
				$.ajax({
					url:"activity/selectUsers",
					dataType:"json",
					type:"get",
					success:function (resp) {//所有的user
						var html="";
						$("#edit-marketActivityOwner").empty();
						$.each(resp ,function (i,n) {
							html+="<option value='"+n.id+"'>" +n.name+"</option>";
						})
						$("#edit-marketActivityOwner").html(html);
					}
				})

				$.ajax({
					url:"activity/selectActivity",
					data:{
						"id":$("input[name=xz]:checked").val()
					},
					dataType:"json",
					type:"post",
					success:function (resp) {
						var selected='select>option[value='+resp.towner+']';
						$('selected').prop("selected",true);
						$("#edit-marketActivityName").val(resp.name);
						$("#edit-startTime").val(resp.startDate);
						$("#edit-endTime").val(resp.endDate);
						$("#edit-cost").val(resp.cost);
						$("#edit-describe").text(resp.description);
					}
				})
				$("#editActivityModal").modal("show");//弹出模态窗口
			}

		})


		//点击更新按钮
		$("#updataBtn").click(function () {
			var owner=$.trim($("#edit-marketActivityOwner").val());
			var name=$.trim($("#edit-marketActivityName").val());
			var startDate=$.trim($("#edit-startTime").val());
			var endDate=$.trim($("#edit-endTime").val());
			var cost=$.trim($("#edit-cost").val());
			var description=$.trim($("#edit-describe").val());

			$.ajax({
				url:"activity/updateActivity",
				data:{
					"id":$("input[name=xz]:checked").val(),
					"owner":owner,
					"name":name,
					"startDate":startDate,
					"endDate":endDate,
					"cost":cost,
					"description":description
				},
				dataType:"json",
				type:"post",
				success:function (resp) {
					if (resp.success){
						alert("修改成功！")
						pageList($("#activityPage").bs_pagination('getOption', 'currentPage')
								,$("#activityPage").bs_pagination('getOption', 'rowsPerPage'));
					}else{
						alert("修改失败！")
					}
				}
			})
		})


	});

	//动态sql查询活动
	$("#searchBtn").click(function () {
		$("#hidden-name").val($("#create-marketActivityName").val())
		$("#hidden-owner").val($.trim($("#create-marketActivityOwner").val()))
		$("#hidden-startDate").val($.trim($("#create-startTime").val()))
		$("#hidden-endDate").val($.trim($("#create-endTime").val()))
		pageList(1,3);
	})

	/**
	 * 分页查询
	 * @param pageno
	 * @param pagesize
	 */
	pageList=function (pageno,pagesize) {
		$("#sumCheckbox").prop("checked",false);
		//与上面是相反的操作
		$("#create-marketActivityName").val($("#hidden-name").val())
		$("#create-marketActivityOwner").val($.trim($("#hidden-owner").val()))
		$("#create-startTime").val($.trim($("#hidden-startDate").val()))
		$("#create-endTime").val($.trim($("#hidden-endDate").val()))

		$.ajax({
			url:"activity/queryActivity",
			data: {
				"pageno":pageno==""?1:pageno,
				"pagesize":pagesize==""?3:pagesize,
				"name":$.trim($("#search-name").val()),
				"owner":$.trim($("#search-owner").val()),
				"startDate":$.trim($("#startTime").val()),
				"endDate":$.trim($("#endTime").val())
			},
			type:"get",
			dataType:"json",
			success:function (resp) {
				var html='';
				 $("#addActivityTboby").empty();
				$.each(resp,function (i,n) {
					if (i<resp.length-1){
						html+="<tr class='active'>";
						html+="<td><input type=\"checkbox\" name='xz'value='" +n.id+"'></td>"
						html+="<td><a style='text-decoration: none; cursor: pointer;'onclick="+
								"window.location.href='${pageContext.request.contextPath}/activity/detail.do?id="+n.id+"'"+";>" +n.name+"</a></td>";
						html+="<td>" +n.createBy+"</td>";
						html+="<td>" +n.startDate+"</td>";
						html+="<td>" +n.endDate+"</td>";
						html+="</tr>";
						$("#addActivityTboby").html(html);
					}
				});

				//分页查询插件
				$("#activityPage").bs_pagination({
					currentPage: resp[resp.length-1].pageno, // 页码
					rowsPerPage: resp[resp.length-1].pagesize, // 每页显示的记录条数
					maxRowsPerPage: 20, // 每页最多显示的记录条数
					totalPages: resp[resp.length-1].pagecount, // 总页数
					totalRows: resp[resp.length-1].pagetotel, // 总记录条数

					visiblePageLinks: 3, // 显示几个卡片

					showGoToPage: true,
					showRowsPerPage: true,
					showRowsInfo: true,
					showRowsDefaultInfo: true,

					onChangePage:function(event, data){
						pageList(data.currentPage , data.rowsPerPage);
					}
				});
			}
		})
	}


</script>
</head>
<body>
<input type="hidden" id="hidden-name">
<input type="hidden" id="hidden-owner">
<input type="hidden" id="hidden-startDate">
<input type="hidden" id="hidden-endDate">
	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form id="activityAddForm" class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-marketActivityOwner">
							<%-- --%>
								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-marketActivityName">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-startTime" readonly>
							</div>
							<label for="create-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="create-endTime" readonly>
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveBtn">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form">
					
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">

								</select>
							</div>
                            <label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
                            </div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-startTime" value="2020-10-10" readonly>
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control time" id="edit-endTime" value="2020-10-20" readonly>
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>
						
						<div class="form-group">
							<label for="edit-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-describe">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="updataBtn" class="btn btn-primary" data-dismiss="modal">更新</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input class="form-control" type="text" id="search-name">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input class="form-control" type="text" id="search-owner">
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input class="form-control time" type="text" id="startTime" readonly/>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input class="form-control time" type="text" id="endTime" readonly>
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="searchBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="addBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="updateBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" id="deleteBtn" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
				</div>
				
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="sumCheckbox" /></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="addActivityTboby">
					</tbody>
				</table>
			</div>
			
			<div style="height: 50px; position: relative;top: 30px;">
				<div id="activityPage">

				</div>

			</div>
			
		</div>
		
	</div>
</body>
</html>