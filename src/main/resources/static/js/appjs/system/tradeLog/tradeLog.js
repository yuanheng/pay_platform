var prefix = "/system/tradeLog"
$(function () {
    load();
});

function load() {
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
                        tradeNo:$('#trade_no').val(),
                        mobile:$('#mobile').val(),
                        type:$('#type').val(),
                        status:$('#status').val()
                    };
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'id',
                        title: '主键'
                    },
                    {
                        field: 'tradeNo',
                        title: '流水单号'
                    },
                    {
                        field: 'amount',
                        title: '交易额度',
                        formatter: function (value, row, index) {
                            return (Number(value)/100).toFixed(2);
                        }
                    },
                    {
                        field: 'factAmount',
                        title: '真实到账',
                        formatter: function (value, row, index) {
                            return (Number(value)/100).toFixed(2);
                        }
                    },
                    {
                        field: 'type',
                        title: '交易类型'
                    },
                    {
                        field: 'createTime',
                        title: '交易时间'
                    },
                    {
                        field: 'finishedTime',
                        title: '完成时间'
                    },
                    {
                        field: 'tradeOutNo',
                        title: '外部的交易单号'
                    },
                    {
                        field: 'remark',
                        title: '备注'
                    },
                    {
                        field: 'mid',
                        title: '会员id'
                    },
                    {
                        field: 'status',
                        title: '状态'
                    },
                    {
                        field: 'mobile',
                        title: '当前用户手机号'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var temp = "";
                            if(row.type == 'invest_money'){
                                temp = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="上分"  mce_href="#" onclick="addMoney(\''
                                    + row.id+'\',\''+row.tradeNo
                                    + '\')"><i class="fa fa-add"></i>上分</a> ';
                            }

                            if(row.type == 'purchase'){
                                temp = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title=""  mce_href="#" onclick="purchase_back(\''
                                    + row.id+'\',\''+row.tradeNo
                                    + '\')"><i class="fa fa-add"></i>返现</a> ';
                            }
                            return temp;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
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


function purchase() {
    layer.confirm('确定要购买一次吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/purchase",
            type: "post",
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



function purchase_backAll() {
    layer.confirm('确定要所有购买返现吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/purchaseBackAll",
            type: "post",
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




function purchase_back(id,tradeNo) {
    layer.confirm('确定要给交易单号'+tradeNo+'返现吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/purchaseBack",
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

function addMoney(id,tradeNo){
    layer.confirm('确定要给交易单号'+tradeNo+'上分吗？', {
        btn: ['确定', '取消']
    }, function () {
        $.ajax({
            url: prefix + "/addMoney",
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