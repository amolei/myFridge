function validateFoodKindName(){
    var food_kind_name = $('#food_kind_name').val();
    $.post(
        "validateFoodKindName.do",
        {
            "food_kind_name":food_kind_name
        },
        function(data){
            if(data.flag == 1){
                $('#food_kind_name').val("");
                alert("分类已存在!");
            }
        });
}

function submitForm(){
    var fridge_name = $('#fridge_name').val();
    var fridge_mobile = $('#fridge_mobile').val();
    // var keep_info = $('#keep_info').val();
    // var keep_time = $('#keep_time').val();
    var fridge_pinyin = $('#fridge_pinyin').val();
    if(fridge_name == null || fridge_name == ''){
        alert("名称不能为空");
        return ;
    }
    if(fridge_mobile == null || fridge_mobile == ''){
        alert("售后电话不能为空");
        return ;
    }
    if(fridge_pinyin == null || fridge_pinyin    == ''){
        alert("拼音不能为空");
        return ;
    }
    $('#createFridgeBrandForm').submit();
}

function delFridgeBrand(brand_id){
    window.location.href = 'delFridgeBrand.do?brand_id=' + brand_id;
}

function openModifyModal(id) {
    $.ajax({
        type : "post",
        url : "getFridgeBrandById.do",
        dataType : "json",
        data : {
            "brand_id" : id
        },
        cache : false,
        success : function(data) {
            if (data != null) {
                var obj = data.fridgeBrand;
                $('input[name=brand_id]').val(obj.brand_id);
                $('input[name=modifyModal_fridge_name]').val(obj.fridge_name);
                $('input[name=modifyModal_fridge_mobile]')
                    .val(obj.fridge_mobile);
                $('input[name=modifyModal_keep_info]')
                    .val(obj.keep_info);
                $('input[name=modifyModal_keep_time]')
                    .val(obj.keep_time);
                $('input[name=modifyModal_fridge_pinyin]')
                    .val(obj.fridge_pinyin);
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
    $('#modifyModalFridgeBrandForm').submit();
}

