var prefix = "/system/order"
$(function () {
    load();
});

function load() {
    var roleId = $("#role_id").val();
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset,
                        orderNo: $('#searchOrderNo').val(),
                        merchantOrderNo: $('#searchMechantOrderNo').val(),
                        status: $('#status').val(),
                        createTime: $('#createTime').val(),
                        finishTime: $('#finishTime').val()
                    };
                },
                columns: [
                    {
                        checkbox: true
                    }, {
                        field: 'id',
                        title: '序号'
                    }, {
                        field: 'orderNo',
                        title: '平台订单No'
                    }, {
                        field: 'merchantOrderNo',
                        title: '商户订单No'
                    }, {
                        field: 'amount',
                        title: '订单额度(元)',
                        formatter: function (value, row, index) {
                            return value * 1.0 / 100;
                        }
                    }, {
                        field: 'reallyAmount',
                        title: '实际支付额度(元)',
                        formatter: function (value, row, index) {
                            return value * 1.0 / 100;
                        }
                    }, {
                        field: 'createTime',
                        title: '创建时间'
                    }, {
                        field: 'finishTime',
                        title: '支付完成'
                    }, {
                        field: 'status',
                        title: '状态',
                        formatter: function (value, row, index) {
                            if (value == "pre_pay") {
                                return "待支付";
                            } else if (value == "finished_pay") {
                                return "支付完成";
                            } else if (value == "callback_success") {
                                return "回调成功";
                            } else if (value == "callback_failed") {
                                return "回调失败";
                            } else if (value == "canceled") {
                                return "已取消";
                            }
                        }
                    }, {
                        field: 'payType',
                        title: '收款方式',
                        formatter: function (value, row, index) {
                            if (value == 'bank') {
                                return "银行卡";
                            } else if (value == "wechat") {
                                return "微信扫码";
                            } else if (value == "alipay") {
                                return "支付宝扫码";
                            }
                        }
                    },{
                        field: 'account',
                        title: '收款账号',
                        formatter: function (value, row, index) {
                            return row.payment.account;
                        }
                    },{
                        field: 'code',
                        title: '留言码',
                        formatter: function (value, row, index) {
                            if(row.paymentInfo){
                                var paymentInfoObj =JSON.parse(row.paymentInfo);
                                return paymentInfoObj.no;
                            } else {
                                return "--";
                            }
                        }
                    }, {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (roleId == 60) {
                                return "--";
                            }
                            var e = '';
                            if (row.status == 'pre_pay') {
                                e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="confirm(\''
                                    + row.id
                                    + '\')"><i class="fa fa-edit"></i>确认收款</a> ';
                            }

                            var f = '<a class="btn btn-success btn-sm" href="#" title="备用"  mce_href="#" onclick="callbackOrder(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i>发起回调</a> ';
                            return e != '' ? e + f : f;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function showInfo(acc, rmk) {
    var msgTxt = '账号：' + acc + '\n 名称：' + rmk;
    layer.msg(msgTxt);
}
function callbackOrder(id) {
    layer.confirm('请确认要重新发起回掉？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/callbackOrder",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    });
}
function confirm(id) {
    layer.confirm('请确认收到款？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/confirm",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
                reLoad();

            }
        });
    });

}

function add() {
    layer.open({
        type: 2,
        title: '增加',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/add' // iframe的url
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '编辑',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframe的url
    });
}

function remove(id) {
    layer.confirm('确定要删除选中的记录？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要删除的数据");
        return;
    }
    layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
        btn: ['确定', '取消']
        // 按钮
    }, function () {
        var ids = new Array();
        // 遍历所有选择的行数据，取每条数据对应的ID
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {

    });
}