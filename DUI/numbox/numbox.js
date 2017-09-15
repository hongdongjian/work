var dui_numbox = {};
var $dui_minux = $(".dui-numbox .dui-numbox-minux");
var $dui_plus = $(".dui-numbox .dui-numbox-plus");
var $dui_input = $(".dui-numbox .dui-numbox-input");
dui_numbox.checkNumber = function (num) {
    var reg = /^[0-9]*$/;
    if (!reg.test(num)) {
        return false;
    }
    if (num === undefined || num === null || $.trim(num) === "") {
        return false;
    }
    return true;
};

dui_numbox.show = function ($input,num) {
    $input.val(num);
};

dui_numbox.plus = function(data) {
    var result = data.num + data.step;
    if (data.max !== null) {
        if (result > data.max) {
            data.num = data.max;
        } else {
            data.num = result;
        }
    } else {
        data.num = result;
    }

    return data;
};

dui_numbox.minux = function(data) {
    var result = data.num - data.step;
    if (data.min !== null) {
        if (result < data.min) {
            data.num = data.min;
        } else {
            data.num = result;
        }
    } else {
        data.num = result;
    }
    return data;
};

dui_numbox.getData = function ($parent,$input) {
    var data = {};
    var min = $parent.attr("min");
    if (dui_numbox.checkNumber(min)) {
        data.min = parseInt(min);
    } else {
        data.min = 0;
    }
    var max = $parent.attr("max");
    if (dui_numbox.checkNumber(max)) {
        data.max = parseInt(max);
    } else {
        data.max = null;
    }
    var step = $parent.attr("step");
    if (dui_numbox.checkNumber(step)) {
        data.step = parseInt(step);
    } else {
        data.step = 1;
    }
    var num = $input.val();
    if (dui_numbox.checkNumber(num)) {
        data.num = parseInt(num);
        data.flag = 1;
    } else {
        data.num = data.min;
        data.flag = 0;
    }
    return data;
};

$dui_minux.click(function () {
    var $parent = $(this).parent();
    var $input = $parent.find(".dui-numbox-input");
    var data = dui_numbox.getData($parent,$input);
    if (data.flag) {
        dui_numbox.minux(data);
    }
    dui_numbox.show($input,data.num);
});

$dui_plus.click(function () {
    var $parent = $(this).parent();
    var $input = $parent.find(".dui-numbox-input");
    var data = dui_numbox.getData($parent,$input);
    if (data.flag) {
        dui_numbox.plus(data);
    }
    dui_numbox.show($input,data.num);
});

$dui_input.bind("input propertychange",function () {
    var $parent = $(this).parent();
    var data = dui_numbox.getData($parent,$(this));
    var num = $(this).val();
    if (dui_numbox.checkNumber(num)) {
        num = parseInt(num);
        if (num > data.max) {
            num = data.max;
        }
        if (num < data.min) {
            num = data.min;
        }
        dui_numbox.show($(this),num);
    }
});

dui_numbox.init = function () {
    $(".dui-numbox").each(function (){
        var $input = $(this).find(".dui-numbox-input");
        var data = dui_numbox.getData($(this),$input);
        dui_numbox.show($input,data.num);
    })
};

dui_numbox.init();

