var swiper_dj = {};
var $swiper = $(".swiper_dj");
var $swiper_ul =  $(".swiper_dj ul");
var $swiper_li =  $(".swiper_dj ul li");
var $swiperButton =  $(".swiper_dj .swiperButton");
var $swiper_span;
var $swiperSwitch = $(".swiper_dj .swiperSwitch");
var $swiper_prev = $(".swiper_dj .swiperSwitch .prev");
var $swiper_next = $(".swiper_dj .swiperSwitch .next");
swiper_dj.param = {
    width : $(window).width(), //轮播宽度
    height :  550, //轮播高度
    num : $swiper_li.length, //轮播图的数量
    span_width : 13, //轮播图小圆圈的直径
    span_space : 20, //轮播图小圆圈直接的间距
    span_top : 500, //轮播图小圆圈到轮播图顶部的距离
    switch_width : 44, //轮播图左右切换按钮的宽度
    switch_space : 40, //左右切换相对两边的间距
    time : 3000
};
swiper_dj.data = {
    index : 0, //当前的第几张图片，从0开始
    flag : false, //标志位，
    lock: false, //锁
    time:null //计时器
};
swiper_dj.css = function () {
    var data = swiper_dj.param;
    $swiper_ul.prepend("<li>" + $swiper_li.eq(data.num-1).html() + "</li>");
    $swiper_ul.append("<li>" + $swiper_li.eq(0).html() + "</li>");
    $swiper_li = $(".swiper_dj ul li");
    for (var i=0; i< data.num; i++) {
        $swiperButton.append("<span data-id='"+ i +"'></span>"); //添加小圆圈
    }
    $swiper_span = $(".swiper_dj .swiperButton span");
    $swiper_span.eq(0).addClass("active"); //第一个小圆圈为选中状态
    $swiper.css("width", data.width); //设置轮播图的宽
    $swiper.css("height", data.height); //设置轮播图的高
    $swiperButton.css("top",data.span_top); //设置小圆圈到顶部的距离
    $swiperButton.css("height",data.span_width);
    $swiperButton.css("line-height",data.span_width);
    $swiper_span.css("margin-left",data.span_space); //设置小圆圈之间的间距
    var buttons_width = (data.num - 1) * data.span_space + data.num * data.span_width; //计算小圆圈所占的长度
    var span_left = (data.width - buttons_width) / 2; //计算小圆圈居中后离左边的距离
    $swiper_span.eq(0).css("margin-left",span_left); //设置小圆圈离左边的距离，使它居中
    $swiper_span.css("height",data.span_width);
    $swiper_span.css("width",data.span_width);
    $swiper_ul.css("width", (data.num+2)*data.width); //设置ul宽度，但是大于width的被隐藏。
    $swiper_ul.css("height", data.height);
    $swiper_ul.css("left", -data.width); //展现二个li
    $swiper_li.css("width", data.width);
    $swiper_li.css("height", data.height);
    $swiperSwitch.css("top",(data.height - data.switch_width)/2); //设置左右切换离顶部的距离
    $swiperSwitch.css("height",data.switch_width);
    $swiperSwitch.css("width",data.width);
    $swiper_prev.css("width",data.switch_width);
    $swiper_prev.css("margin-left",data.switch_space);
    $swiper_prev.css("float","left");
    $swiper_next.css("width",data.switch_width);
    $swiper_next.css("margin-right",data.switch_space);
    $swiper_next.css("float","right");
};
swiper_dj.removePx = function (data) { //如果又px去掉px，返回数字
    if (data.substring(data.length-2,data.length) === "px") {
        return parseInt(data.substring(0,data.length-2));
    }
    return parseInt(data);
};
swiper_dj.changeSpanActive = function (index) { //改变小圆圈的选中状态
    $swiper_span.siblings(".active").removeClass("active");
    $swiper_span.eq(index).addClass("active");
};
swiper_dj.animate = function(offset,clickFlag) {
    console.log("swiper1");
    var param = swiper_dj.param;
    var data = swiper_dj.data;
    var newLeft = swiper_dj.removePx($swiper_ul.css("left")) + offset;
    if (swiper_dj.data.lock === true) {
        return;
    }
    swiper_dj.data.lock = true;
    if (clickFlag === true) {
        clearTimeout(swiper_dj.data.time);
    }
    $swiper_ul.animate({left:newLeft},500,function () {
        if (offset < 0)
            swiper_dj.data.index ++;
        else
            swiper_dj.data.index --;
        if (newLeft > -param.width) {
            swiper_dj.data.index = param.num - 1;
            $swiper_ul.css("left", -param.width*param.num)
        }
        if (newLeft < -(param.width*param.num)) {
            swiper_dj.data.index = 0;
            $swiper_ul.css("left", -param.width);
        }
        swiper_dj.changeSpanActive(swiper_dj.data.index);
        if (swiper_dj.data.lock === true) {
            swiper_dj.data.lock = false;
        }
        if (clickFlag === true) {
            swiper_dj.data.flag = false;
            swiper_dj.timeCount();
        }
    });
};
swiper_dj.timeCount = function(){
    if (swiper_dj.data.flag) {
        swiper_dj.animate(-swiper_dj.param.width);
    }
    swiper_dj.data.flag = true;
    swiper_dj.data.time = setTimeout(swiper_dj.timeCount,swiper_dj.param.time);
};
swiper_dj.init = function () {
    swiper_dj.css();
    swiper_dj.timeCount();
    $swiper_span.click(function () {
        var id = $(this).attr("data-id");
        clearTimeout(swiper_dj.data.time);
        $swiper_ul.css("left",-(parseInt(id)+1)*swiper_dj.param.width);
        swiper_dj.data.flag = 0;
        swiper_dj.data.index = id;
        swiper_dj.changeSpanActive(id);
        swiper_dj.timeCount();
    });

    $swiper_prev.click(function () {
        swiper_dj.animate(swiper_dj.param.width,true);
    });

    $swiper_next.click(function () {
        swiper_dj.animate(-swiper_dj.param.width,true);
    });
};
swiper_dj.init();