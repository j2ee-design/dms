$(function () {
    doSelectAll('#stu-table .tr-h .select-label input',
        '#stu-table .tr-d .select-label');
    doSelectAll("#dorm-table .tr-h .select-label-th .select-label input",
        "#dorm-table .tr-d .select-label-td .select-label input[type='checkbox']");
    doSearch('#select-box .search-input','#select-box select','stu-table');
    doSearch('#dorm-input-button','#dorm-select-box','dorm-table');

});

/**
 * 点击查询按钮 -- 不怎么可复用
 * 1. 获取搜索条件
 * 2. 向后台发起查询
 * 3. 分情况将查询结果写入 dom
 * 4. 查询过程中的等待时期要有提示（转圈圈的那种）
 */
function doSearch(searchButtonElePath, selectListsPath,type){
    $(searchButtonElePath).click(function () {
        // 1
        var selectData = {};
        var testData = {};
        var selectLists = $(selectListsPath);
        selectLists.each(function () {
            testData[$(this).attr('name')] = $(this).children('option:selected').html(); // 获取选中的文本
            selectData[$(this).attr('name')] = $(this).val();
        });
        // 2
        var data;// 根据 if 条件不同请求不同的服务，得到不同的返回数据
        // 3
        if (type == 'stu-table'){
            // 写入之前先重置
            $('#stu-table tbody').empty().append("" +
                "<tr class='tr-h'>\n" +
                "    <th><label class='select-label'><input type='checkbox'>全选</label></th>\n" +
                "    <th>编号</th>\n" +
                "    <th>学号</th>\n" +
                "    <th>姓名</th>\n" +
                "    <th>性别</th>\n" +
                "</tr>"
            );
            // 重置 -- 绑定全选事件
            doSelectAll('#stu-table .tr-h .select-label input',
                '#stu-table .tr-d .select-label');

            $('#stu-table tbody').append("" +
                "<tr class='tr-d'>\n" +
                "    <td><label class='select-label'><input type='checkbox'><input type='hidden' value='000001' name='学生ID' class='stu-id'></label></td>\n" +
                "    <td>009</td>\n" +
                "    <td>1507094236</td>\n" +
                "    <td>江大北</td>\n" +
                "    <td>男</td>\n" +
                "</tr>"
            );

        }
        if (type == 'dorm-table'){
            // 写入宿舍楼信息
            $('#apart-introduce span').html('#14-呵呵楼');
            $('#apart-type span').html('男生公寓');
            $('#apart-all-bed span').html('900 / 1000');
            $('#apart-single-bed span').html('6');

            // 重置 table -- 内容添加
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
            //  根据后台返回 data 写入宿舍信息
            // for (var i=0; i<data.length; i++){
            //
            // }
            $('#dorm-table tbody').append("" +
                "<tr class='tr-d'>\n" +
                "    <td class='select-label-td'><label class='select-label'><input type='checkbox'></label><input type='hidden' class='u-dorm-id' value='00001' name='宿舍ID'></td>\n" +
                "    <td class='sequence-d'>006</td>\n" +
                "    <td class='dorm-id-d'>#13-0342</td>\n" +
                "    <td class='rest-td'><span class='graph-wrap'><span class='distred-change'></span><span class='graph'><span class='exist-num'>6</span>/6</span></span></td>\n" +
                "</tr>"+
                "<tr class='tr-d'>\n" +
                "    <td class='select-label-td'><label class='select-label'><input type='checkbox'></label></td>\n" +
                "    <td class='sequence-d'>006</td>\n" +
                "    <td class='dorm-id-d'>#13-0342</td>\n" +
                "    <td class='rest-td'><span class='graph-wrap'><span class='distred-change'></span><span class='graph'><span class='exist-num'>3</span>/6</span></span></td>\n" +
                "</tr>"
            );
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
 * 按照入住比率比率进行排序
 */
function sortBypercentage(data){
    // 排序算法，根据入住情况排序
    return data;
}

/**
 * 测试分配
 * 1. 计算选中要分配学生人数，计算选中宿舍剩余床位，for 循环依次减去单位宿舍剩余床铺数，直到宿舍用完或者人数耗尽
 * 2. 分配结果用进度条表现出来（画进度条之前先删除）
 * 2. 将 for 循环判断结果弹出告知用户（本次分配 ** 人，可分配床位 ** 个，涉及宿舍 ** 个，无法分配完全/可以分配完全）
 */
function doTest(){
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
    // 1
    var stuList = $("#stu-table tbody .tr-d .select-label input[type='checkbox']:checked");
    var dormList = $("#dorm-table tbody .tr-d .select-label input[type='checkbox']:checked");
    var stuData = {},dormData={};

    i = 0;
    stuList.each(function(){
        stuData[i] = $(this).siblings('.stu-id').val();
        i++;
    });

    i = 0;
    dormList.each(function () {
        dormData[i] = $(this).parent().siblings('.u-dorm-id').val();
    });

    // 2 改变长度以及弹出提示信息:后台传回修改过的宿舍的 ID 和 以住学生人数和单位总人数，计算宽度。

    popupInfo('分配成功！');
    console.log(stuData+' '+dormData);

}












