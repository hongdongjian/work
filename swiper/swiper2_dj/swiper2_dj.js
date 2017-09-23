var swiper2_dj = {};
var $swiper2 = $(".swiper2_dj");
var $swiper2_ul =  $(".swiper2_dj ul");
var $swiper2_li =  $(".swiper2_dj ul li");
var $swiper2Button =  $(".swiper2_dj .swiper2Button");
var $swiper2_span;
var $swiper2Switch = $(".swiper2_dj .swiper2Switch");
var $swiper2_prev = $(".swiper2_dj .swiper2Switch .prev");
var $swiper2_next = $(".swiper2_dj .swiper2Switch .next");
swiper2_dj.param = {
    width : $(window).width(), //轮播宽度
    min_width : 1336, // 轮播最小宽度
    height :  400, //轮播高度
    num : $swiper2_li.length, //轮播图的数量
    span_width : 13, //轮播图小圆圈的直径
    span_space : 20, //轮播图小圆圈直接的间距
    span_top : 350, //轮播图小圆圈到轮播图顶部的距离
    switch_width : 44, //轮播图左右切换按钮的宽度
    switch_space : $(window).width()*0.23, //左右切换相对两边的间距
    time : 3000, //轮播切换的时间间隔
    show_percent : 0.54, //突出的轮播图所占长度的比例
    show_space : 70 //突出的轮播图和两边的轮播图的间隔
};
swiper2_dj.data = {
    index : 3, //当前的第几张图片，从0开始
    flag : false, //标志位，
    lock: false, //锁
    time: null //计时器
};
//左右边轮播图的样式
swiper2_dj.lrCss = {
    height : swiper2_dj.param.height*0.9,
    marginTop : swiper2_dj.param.height*0.05,
    opacity : 0.7
};
//突出的轮播图的样式
swiper2_dj.midCss = {
    height : swiper2_dj.param.height,
    marginTop: 0,
    opacity : 1
};
//传入第几张图，得到这张图的偏移量
swiper2_dj.getLeft = function (index) {
    var left = index * (swiper2_dj.param.width * swiper2_dj.param.show_percent + swiper2_dj.param.show_space) + swiper2_dj.param.show_space;
    left = left - swiper2_dj.param.width*(1.0-swiper2_dj.param.show_percent)/2.0;
    return -left;
};
swiper2_dj.changeSpanActive = function (index) { //改变小圆圈的选中状态
    $swiper2_span.siblings(".active").removeClass("active");
    $swiper2_span.eq(index).addClass("active");
};
swiper2_dj.removePx = function (data) { //如果又px去掉px，返回数字
    if (data.substring(data.length-2,data.length) === "px") {
        return parseInt(data.substring(0,data.length-2));
    }
    return parseInt(data);
};
// 没有动画的改变样式，传进突出那张图的Index
swiper2_dj.changeCss = function (index) {
    $swiper2_li.eq(index-1).css(swiper2_dj.lrCss);
    $swiper2_li.eq(index).css(swiper2_dj.midCss);
    $swiper2_li.eq(index+1).css(swiper2_dj.lrCss);
    $swiper2_ul.css("left",swiper2_dj.getLeft(index));
};
// 首尾各添加连两个li
swiper2_dj.dom = function() {
    var data = swiper2_dj.param;
    $swiper2_ul.prepend("<li>" + $swiper2_li.eq(data.num-1).html() + "</li>");
    $swiper2_ul.append("<li>" + $swiper2_li.eq(0).html() + "</li>");
    $swiper2_ul.prepend("<li>" + $swiper2_li.eq(data.num-2).html() + "</li>");
    $swiper2_ul.append("<li>" + $swiper2_li.eq(1).html() + "</li>");
    for (var i=0; i< data.num; i++) {
        $swiper2Button.append("<span data-id='"+ i +"'></span>"); //添加小圆圈
    }
};

//初始化css样式
swiper2_dj.css = function () {
    var data = swiper2_dj.param;

    //当小于最小宽度时，使用最小宽度
    var winWidth = $(window).width();
    if(winWidth < data.min_width){
        data.width = data.min_width;
    }else{
        data.width = winWidth;
    }
    //data.switch_space = data.width * 0.23;
    data.switch_space = data.width * 0.14;

    var li_width = data.width * data.show_percent;

    $swiper2_li = $(".swiper2_dj ul li");
    $swiper2_span = $(".swiper2_dj .swiper2Button span");
    $swiper2_span.eq(0).addClass("active"); //第一个小圆圈为选中状态
    $swiper2.css("width", data.width); //设置轮播图的宽
    $swiper2.css("height", data.height); //设置轮播图的高
    $swiper2Button.css("top",data.span_top); //设置小圆圈到顶部的距离
    $swiper2Button.css("height",data.span_width);
    $swiper2Button.css("line-height",data.span_width);
    $swiper2_span.css("margin-left",data.span_space); //设置小圆圈之间的间距
    var buttons_width = (data.num - 1) * data.span_space + data.num * data.span_width; //计算小圆圈所占的长度
    var span_left = (data.width - buttons_width) / 2; //计算小圆圈居中后离左边的距离
    $swiper2_span.eq(0).css("margin-left",span_left); //设置小圆圈离左边的距离，使它居中
    $swiper2_span.css("height",data.span_width);
    $swiper2_span.css("width",data.span_width);
    var ul_width = (data.num+4)*li_width + (data.num + 4)*data.show_space;
    $swiper2_ul.css("width", ul_width); //设置ul宽度，但是大于width的被隐藏。
    $swiper2_ul.css("height", data.height);
    $swiper2_ul.css("left", swiper2_dj.getLeft(swiper2_dj.data.index)); //展现二个li
    $swiper2_li.css("width", li_width);
    $swiper2_li.css("height", data.height);
    $swiper2_li.css("margin-left",data.show_space);
    $swiper2Switch.css("top",(data.height - data.switch_width)/2); //设置左右切换离顶部的距离
    $swiper2Switch.css("height",data.switch_width);
    $swiper2Switch.css("width",data.width);
    $swiper2_prev.css("width",data.switch_width);
    $swiper2_prev.css("margin-left",data.switch_space);
    $swiper2_prev.css("float","left");
    $swiper2_next.css("float","right");
    $swiper2_next.css("width",data.switch_width);
    $swiper2_next.css("margin-right",data.switch_space);
    $swiper2_li.eq(swiper2_dj.data.index - 1).css(swiper2_dj.lrCss); //左右两张轮播图的样式
    $swiper2_li.eq(swiper2_dj.data.index + 1).css(swiper2_dj.lrCss);
    swiper2_dj.changeSpanActive(swiper2_dj.data.index - 2); //小圆圈的样式
};
// 动画 offset 负数，向左滑动，正数，向右滑动。clickflag true,点击时的切换，false，定时切换
swiper2_dj.animate = function(offset,clickflag) {
    if (swiper2_dj.data.lock === true) { //如果正在动画就直接返回，保证同一时间只有一个动画
        return;
    }
    if (clickflag) {
        clearTimeout(swiper2_dj.data.time); //如果是点击，去掉定时
    }
    swiper2_dj.data.lock = true;
    if (offset < 0) {
        swiper2_dj.data.index ++;
    } else {
        swiper2_dj.data.index --;
    }
    var newLeft = swiper2_dj.getLeft(swiper2_dj.data.index);
    $swiper2_li.eq(swiper2_dj.data.index - 1).animate(swiper2_dj.lrCss,300);
    $swiper2_li.eq(swiper2_dj.data.index).animate(swiper2_dj.midCss,300);
    $swiper2_li.eq(swiper2_dj.data.index + 1).animate(swiper2_dj.lrCss,300);
    $swiper2_ul.animate({left : newLeft},300,function () {
        var index = swiper2_dj.data.index;
        if (index === 1) {
            swiper2_dj.data.index = swiper2_dj.param.num + 1;
            swiper2_dj.changeCss(swiper2_dj.data.index);
        } else if (index === swiper2_dj.param.num + 2) {
            swiper2_dj.data.index = 2;
            swiper2_dj.changeCss(swiper2_dj.data.index);
        }
        swiper2_dj.changeSpanActive(swiper2_dj.data.index - 2);
        if (swiper2_dj.data.lock === true) {
            swiper2_dj.data.lock = false;
        }
        if (clickflag) {
            swiper2_dj.data.flag = false;
            swiper2_dj.timeCount();
        }
    });
};

swiper2_dj.timeCount = function(){ //定时轮播
    if (swiper2_dj.data.flag) {
        swiper2_dj.animate(-1);
    }
    swiper2_dj.data.flag = true;
    swiper2_dj.data.time = setTimeout(swiper2_dj.timeCount,swiper2_dj.param.time);
};
swiper2_dj.init = function () { //初始化
    swiper2_dj.dom();
    swiper2_dj.css();
    $swiper2_prev.click(function () {
        swiper2_dj.animate(1,true);
    });
    $swiper2_next.click(function () {
        swiper2_dj.animate(-1,true);
    });
    $swiper2_span.click(function () {
        if (swiper2_dj.data.lock)
            return;
        clearTimeout(swiper2_dj.data.time);
        var index = parseInt($(this).attr("data-id")) + 1;
        swiper2_dj.data.index = index;
        swiper2_dj.changeCss(swiper2_dj.data.index);
        swiper2_dj.changeSpanActive(swiper2_dj.data.index);
        swiper2_dj.flag = false;
        swiper2_dj.timeCount();
    });
    swiper2_dj.timeCount();
};
$(window).resize(function(){ //每当窗口发生变化时，重新渲染css
    swiper2_dj.css();
});
swiper2_dj.init();