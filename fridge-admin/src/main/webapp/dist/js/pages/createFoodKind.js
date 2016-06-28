function validateFoodKindName(){
    var food_kind_name = $('#food_kind_name').val();
    $.post(
        "../validateFoodKindName.do",
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
    var food_kind_name = $('#food_kind_name').val();
    var food_kind_info = $('#food_kind_info').val();
    var simple_name = $('#simple_name').val();
    if(food_kind_name == null || food_kind_name == ''){
        alert("名称不能为空");
        return ;
    }
    if(food_kind_info == null || food_kind_info == ''){
        alert("描述不能为空");
        return ;
    }
    if(simple_name == null || simple_name == ''){
        alert("简称不能为空");
        return ;
    }
    $('#createFoodKindForm').submit();
}