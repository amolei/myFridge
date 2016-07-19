function openModifyModal(images) {
    var array = images.split(',');
    var txt;
    $.each(array, function (index,url) {
        url = 'http://139.196.171.209' + url;
        if(index == 0){
            txt = txt + "<div class='item active'>";
        }else{
            txt = txt + "<div class='item'>";
        }
        txt = txt + "<img src='" + url + "'/></div>";
    });
    $('#carousel-inner').html(txt);
    $('#modifyModal').modal('show');
}

