

    /**
     * 创建表单类
     * @param lable
     * @param id
     * @constructor
     */
    FormItem=function (label,id) {
        this.label=label;
        this.id=id;
    }



    EGOV=function () {
        /**
         * 进行非空验证
         */
    this.checkForms=function (formItemArray) {
            for (var i=0;i<formItemArray.length;i++){
                var ids=formItemArray[i].id;
                var idsObj=document.getElementById(ids);
                if ((idsObj.value).trim()==""){
                    alert(formItemArray[i].label+"不能为空！")
                    return false;
                }
            }
            return true;
        };

        /**
         * 验证密码与确认密码是否一致
         */
        this.SamePasd=function (formItem1,formItem2) {
            var id1=formItem1.id;
            var id2=formItem2.id;
            var id1Obj=document.getElementById(id1);
            var id2Obj=document.getElementById(id2);
            if (id1Obj.value!=id2Obj.value){
               id1Obj.value="";
               id1Obj.focus();
               id2Obj.value="";
                alert(formItem1.label+"和"+formItem2.label+"不一致请重新填写！")
                return false;
            }
            return true;
        }


    };
var eg=new EGOV();