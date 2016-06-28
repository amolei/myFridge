function delById(food_kind_id) {
    $.post(
        "validateFoodKindById.do",
        {"kind_id":food_kind_id},
        function(data){
            if(data.flag == 1){
                alert("该分类下存在食物!");
            }else{
                window.location.href = "delFoodKindById.do?kind_id=" + food_kind_id;
            }
        }
    );
}

