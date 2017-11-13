$(function () {


});

/**
 * 根据搜索条件搜索学生
 * 1. 获取搜索数据并发起请求
 * 2. 将结果写入 DOM
 */
function searchStudent() {
    var data = {};
    data['dicGrade'] = $('#grade').val();
    data['academyId'] = $('#acade').val();
    data['majorId'] = $('#major').val();
    data['classId'] = $('#class').val();
    data['gender'] = $('#grender').val();
    data['dormStatus'] = '0';
    // 1.2
    $.ajax({
        type:'post',
        contentType:'application/json;charset=utf-8',
        url:getBasePath()+"/student/list/search",
        data:JSON.stringify(data),
        success:function (data) {
            // 写入之前先重置
            $('#stu-table tbody').empty().append("" +
                "<tr class='tr-h'>\n" +
                "    <th><label class='select-label'><input type='checkbox' onclick='clickAllStu()'>全选</label></th>\n" +
                "    <th>编号</th>\n" +
                "    <th>学号</th>\n" +
                "    <th>姓名</th>\n" +
                "    <th>性别</th>\n" +
                "</tr>"
            );
            // 循环写入 DOM
            var x = 1;
            for (var i in data){
                $('#stu-table tbody').append("" +
                    "<tr class='tr-d'>\n" +
                    "    <td>" +
                    "       <label class='select-label'>" +
                    "           <input type='checkbox' name='one' value='"+data[i]['id']+"'>" +
                    "       </label>" +
                    "    </td>\n" +
                    "    <td>"+x+"</td>\n" +
                    "    <td>"+data[i]['id']+"</td>\n" +
                    "    <td>"+data[i]['name']+"</td>\n" +
                    "    <td>"+getGenderStr(data[i]['gender'])+"</td>\n" +
                    "</tr>"
                );
                x++;
            }
        }
    });
}

/**
 * 点击全选--学生表
 */
function clickAllStu() {
    $("#stu-table tbody .tr-d td .select-label input[type='checkbox']").click();
}

/**
 * 根据公寓搜索宿舍
 */
function searchDorm() {
    var data = $('#apartId option:checked').val();
    $.ajax({
        type:'get',
        url:getBasePath()+"/distribute/getDorm/"+data,
        data:data,
        success:function (data) {
            var apart = data['apartDto'];
            $('#apart-introduce span').html('#'+apart['id']+"-"+apart['name']);
            $('#apart-type span').html(apart['apartmentTypeName']);
            $('#apart-all-bed span').html(apart['usedDorm']+' / '+apart['allBed']);
            $('#apart-single-bed span').html(apart['dormBedNum']);

            $('#dorm-table tbody').empty().append("" +
                "<tr class='tr-h'>\n" +
                "    <th class='select-label-th'><label class='select-label'><input type='checkbox' onclick='clickAllDorm()'>全选</label></th>\n" +
                "    <th class='sequence-h'>序号</th>\n" +
                "    <th class='dorm-id-h'>宿舍号</th>\n" +
                "    <th class='rest-th'>入住比</th>\n" +
                "</tr>"
            );

            var dorms = data['dormDtos'];
            for (var i in dorms){
                $('#dorm-table tbody').append("" +
                    "<tr class='tr-d'>\n" +
                    "    <td class='select-label-td'>" +
                    "       <label class='select-label'><input type='checkbox' value='"+dorms[i]['id']+"'></label>" +
                    "       <input type='hidden' class='u-dorm-id' value='"+dorms[i]['id']+"' name=''></td>\n" +
                    "    <td class='sequence-d'>"+(i+1)+"</td>\n" +
                    "    <td class='dorm-id-d'>#"+apart['id']+"-"+dorms[i]['dormId']+"</td>\n" +
                    "    <td class='rest-td'><span class='graph-wrap'><span class='distred-change'></span><span class='graph'><span class='exist-num'>"+dorms[i]['usedBed']+"</span>/"+dorms[i]['allBed']+"</span></span></td>\n" +
                    "</tr>"
                );
            }
            // 设置进度条
            var singleNum = parseInt($('#apart-single-bed span.value').html());
            var existNum = 0;
            var percentage = 0;
            setTimeout(function(){
                $('#dorm-table tbody .tr-d .rest-td .graph').each(function(){
                    existNum = parseInt($(this).children('span.exist-num').html());
                    percentage = existNum / singleNum * 100;
                    $(this).css('width',percentage+'%').css('background','#AEEEEE');
                });
            },50);
        }
    });
}

/**
 * 点击全选--宿舍表
 */
function clickAllDorm() {
    $("#dorm-table tbody .tr-d .select-label-td .select-label input[type='checkbox']").click();
}

/**
 * 按照入住比率比率进行排序
 */
function sortBypercentage(data){
    // 排序算法，根据入住情况排序
    return data;
}

/**
 * 测试分配
 * 1. 判断学生性别和宿舍类型是否匹配
 * 2. 计算选中要分配学生人数，计算选中宿舍剩余床位，for 循环依次减去单位宿舍剩余床铺数，直到宿舍用完或者人数耗尽
 * 2. 分配结果用进度条表现出来（画进度条之前先删除）
 * 2. 将 for 循环判断结果弹出告知用户（本次分配 ** 人，可分配床位 ** 个，涉及宿舍 ** 个，无法分配完全/可以分配完全）
 */
function doTest(){
    // 判断类型符合否 TODO iwangtosleepso

    var stuCheckBoxLists = $("#stu-table tbody .tr-d .select-label input[type='checkbox']:checked");
    var stuNum = stuCheckBoxLists.length;
    // 这里可以对宿舍进行某种排序，比如按照入住比
    var dormCheckBoxLists = $("#dorm-table tbody .tr-d .select-label input[type='checkbox']:checked");
    var dormNum = dormCheckBoxLists.length;

    // 重置 table
    $('#dorm-table tbody').empty().append("" +
        "<tr class='tr-h'>\n" +
        "    <th class='select-label-th'><label class='select-label'><input type='checkbox'>全选</label></th>\n" +
        "    <th class='sequence-h'>序号</th>\n" +
        "    <th class='dorm-id-h'>宿舍号</th>\n" +
        "    <th class='rest-th'>入住比</th>\n" +
        "</tr>"
    );
    // 重置 table -- 绑定全选事件
    doSelectAll('#dorm-table .tr-h .select-label-th .select-label input',
        '#dorm-table .tr-d .select-label-td .select-label input');

    // 将选取的宿舍投放到 table 中
    var trCheckBoxLists = dormCheckBoxLists.parent().parent().parent();
    var restNumLists = [], t = 0;
    trCheckBoxLists.find('.rest-td .graph-wrap .graph span.exist-num').each(function () {
        restNumLists[t] = parseInt($(this).html());
        t++;
    });
    var changeEleLists = trCheckBoxLists.find('.rest-td .graph-wrap .distred-change');
    var graphLists = trCheckBoxLists.find('.rest-td .graph-wrap .graph');
    // console.log(trCheckBoxLists.find('.rest-td .graph-wrap .distred-change'));
    $('#dorm-table tbody').append(trCheckBoxLists);

    // 开始分配
    var flag = false, i = 0, usedDorm=0;
    var oldNum = stuNum;
    var singleBed = parseInt($('#apart-single-bed span').html());
    var restNum = 0;
    var temp = stuNum;//临时变量用来保存总参与分配的学生个数。
    while (stuNum>0 && dormNum>0){
        restNum = singleBed - restNumLists[i];
        if (restNum <= 0){ // 如果当前宿舍已经住满，跳过分配
            i++;
            continue;
        }
        if (stuNum <= 0 || i>=dormNum){ // 学生分配完全/宿舍分配完全,分配结束,此时 i 为改变的宿舍个数，oldNum - stuNum 为完成分配的学生个数
            break;
        } else if (stuNum>=restNum){// 剩余的学生大于第 i 个宿舍的空余床铺数,分配学生，将学生填满宿舍。
            stuNum -= restNum;
            // 将宿舍 change-span 的颜色画出来
            changeEleLists.eq(i).css('width',graphLists.eq(i).css('width'));
            setTimeout(_setwidth(changeEleLists.eq(i),100),10);
            i++; // 换下一个宿舍
            usedDorm++;// 已分配宿舍 +1
        } else { // 剩余未分配的学生人数小于当前宿舍剩余床位，计算出改变量，此时分配完毕
            var cgPercentage = (restNumLists[i]+stuNum)/singleBed * 100;
            changeEleLists.eq(i).css('width',graphLists.eq(i).css('width'));
            setTimeout(_setwidth(changeEleLists.eq(i),cgPercentage),10);
            stuNum = 0;
            i++;
            usedDorm++;// 已分配宿舍 +1
        }
    }
    // 分配完毕，弹出测试结果
    $('#test-info .stu-num').html(oldNum);
    $('#test-info .dorm-num').html(dormNum);
    $('#test-info .distrbute-num').html(oldNum-stuNum);
    $('#test-info .dorm-num').html(usedDorm);
    if (oldNum-stuNum<temp){
        var xx = temp+stuNum-oldNum;
        $('#test-info .moreInfo').html("有"+xx+"名学生无法分配完全。");
    } else {
        $('#test-info .moreInfo').html("可完全分配！");
    }
    // 设置位置
    var divWidth = parseInt($('#test-info').css('width'));
    var allWidth = $(document).width;
    var widthPercentageHalf = divWidth/allWidth*50;
    $('#test-info').css('left',widthPercentageHalf+'%');
    // 展出 div
    $('#test-info').addClass('test-info-in');
}

//=========
function _setwidth(target,percentage) {
    return function () {
        setWidth(target,percentage);
    }
}
function setWidth(target,percentage){
    target.css('transition','all 0.3s ease-out');
    target.css('width',percentage+'%');
}
//=========



/**
 * 点击确定关闭，关闭弹出窗口
 */
function closeBox(){
    $('#test-info').removeClass('test-info-in');
}

/**
 * 点击确认分配
 * 1. 取值：获取学生，获取宿舍，将之发送到后台
 * 2. 如果分配成功，弹出提示，并将 graph 宽度变为 distrbutionchange 一样长
 * 3. 否则弹出失败信息
 */
function doConfirm(){
    // 1.2
    var stuList = $("#stu-table tbody .tr-d .select-label input[type='checkbox']:checked");
    var dormList = $("#dorm-table tbody .tr-d .select-label input[type='checkbox']:checked");
    var stuData = [],dormData=[];

    stuList.each(function(){
        stuData.push($(this).val());
    });

    dormList.each(function () {
        dormData.push($(this).parent().siblings('.u-dorm-id').val());
    });
    var data = {};
    data['stuIds'] = stuData.join(',');
    data['dormIds'] = dormData.join(',');
    // 后台查值
    $.ajax({
        type:'post',
        url:getBasePath()+"/distribute/batchDistribute",
        data:data,
        success:function (data) {
            // 分配成功的数量 TODO iwantsleepso。。。
            // 分配成功之后点击两个搜索按钮刷新页面。
            searchStudent();
            searchDorm();
            popupInfo('操作成功！');
        },
        error:function () {
            alert("发生异常");
        }
    });

}



/**
 * 选择学院之后，向后台获取该学院的专业数据
 * 1. 获取当前学院ID,
 * 1.1 判断当前选中的是不是全部，是，则清空子选项并终止不发起请求
 * 2. 发送数据至后台，
 * 3. 将新数据写入 Dom
 */
function doAcademyChange() {
    // 1.
    var data = {};
    data['academyId'] = $('#acade').val();
    // 1.1 点击全部时清空下一级菜单
    if (data['academyId']==null || data['academyId'].length == 0){
        $('#major').empty().append("<option value=''>全部</option>");
        $('#class').empty().append("<option value=''>全部</option>");
        $('#dorm').empty().append("<option value=''>全部</option>");
        return;
    }

    // 2.

    $.ajax({
        type:'get',
        url:getBasePath()+"/major/pid",
        data:data,
        success:function (data) {
            // 将新值写进 dom 之前要=将旧值删除
            $('#major').empty().append("<option value=''>全部</option>");
            $('#class').empty().append("<option value=''>全部</option>");
            for (var i in data){
                $('#major').append("" +
                    "<option value='"+data[i]['id']+"'>"+data[i]["name"]+"</option>"
                )
            }
        }
    })
}

/**
 * 选择专业之后，向后台获取该专业的班级数据
 * 1. 获取当前专业ID,
 * 1.1 ...
 * 2. 发送数据至后台，
 * 3. 将新数据写入 Dom
 */
function doMajorChange() {
    // 1.
    var data = {};
    data['majorId'] = $('#major').val();
    data['dicGrade'] = $('#grade').val();
    if (data['majorId'] == null || data['majorId'].length == 0) {
        $('#class').empty().append("<option value=''>全部</option>");
        return;
    }
    // 2.
    $.ajax({
        type: 'get',
        url: getBasePath() + "/class/pid",
        data: data,
        success: function (data) {
            $('#class').empty().append("<option value=''>全部</option>");
            $('#dorm').empty().append("<option value=''>全部</option>");
            for (var i in data) {
                $('#class').append("" +
                    "<option value='" + data[i]['id'] + "'>" + data[i]["classId"] + "</option>"
                )
            }
        }
    });

}










