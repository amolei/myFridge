function validateSellerName(){
    var seller_name = $('#seller_name').val();
    $.post(
        "validateSellerName.do",
        {
            "seller_name":seller_name
        },
        function(data){
            if(data.flag == 1){
                $('#seller_name').val("");
                alert("商家已存在!");
            }
        });
}

function submitForm(){
    var seller_name = $('#seller_name').val();
    var seller_address = $('#seller_address').val();
    var seller_mobile = $('#seller_mobile').val();
    if(seller_name == null || seller_name == ''){
        alert("名称不能为空");
        return ;
    }
    if(seller_address == null || seller_address == ''){
        alert("地址不能为空");
        return ;
    }
    if(seller_mobile == null || seller_mobile    == ''){
        alert("电话不能为空");
        return ;
    }
    $('#createFridgeSellerForm').submit();
}

function delFridgeSeller(seller_id){
    window.location.href = 'delFridgeSeller.do?seller_id=' + seller_id;
}

function openModifyModal(id) {
    $.ajax({
        type : "post",
        url : "getFridgeSellerById.do",
        dataType : "json",
        data : {
            "seller_id" : id
        },
        cache : false,
        success : function(data) {
            if (data != null) {
                var obj = data.fridgeSeller;
                $('input[name=seller_id]').val(obj.seller_id);
                $('input[name=modifyModal_seller_name]').val(obj.seller_name);
                $('input[name=modifyModal_seller_address]')
                    .val(obj.seller_address);
                var txt = "";
                var map = {1:"一星",2:"二星",3:"三星",4:"四星",5:"五星"};
                for(var i in map){
                    if(obj.seller_level == i){
                        txt = txt + "<option value=" + i + " selected='selected'>" + map[i] + "</option>";
                    }else{
                        txt = txt + "<option value=" + i + ">" + map[i] + "</option>";
                    }
                }
                $('#modifyModal_seller_level').html(txt);
                $('input[name=modifyModal_seller_mobile]')
                    .val(obj.seller_mobile);
                $('#modifyModal').modal('show');
            }
        },
        error : function(err) {
            var txt = "出错了";
            alert('error');
        }
    });
}

function submitModifyModalForm() {
    $('#modifyModalFridgeSellerForm').submit();
}

