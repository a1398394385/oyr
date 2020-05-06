//$(function () {}页面初始化执行
$(function () {
    //#.:
    $.ajax({
        type: "get",
        url: "/orders/",
        //contentType: "application/json;charset=UTF-8",发送给后端数据的类型       
        // data: JSON.stringify($('form').serializeJSON()),发送给后端的数据
        dataType: "json",
        success: (response) => {
            if (response.status == "success") {
                response.data.forEach(order => {
                    var item =
                        `<tr>
                        <td>${order.id}</td>
                        <td>${order.name}</td>
                        <td>${order.telephone}</td>
                        <td>${order.phoneId}</td>
                        <td>${order.price}</td>
                        <td>${order.userId}</td>
                        <td>顺丰速运</td>
                        <td>${order.address}</td>
                        <td>${order.state}</td>
                        <td>${order.createTime}</td>
                        <td><button>删除</button>
                        <button>更新</button></td>
                    </tr>`;
                    //console.log(item);
                    $("#orders").append(item);
                });
                $("#orderList").DataTable({
                    oLanguage: {
                        sProcessing: "处理中...",
                        sLengthMenu: "_MENU_ 记录/页",
                        sZeroRecords: "没有匹配的记录",
                        sInfo: "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
                        sInfoEmpty: "显示第 0 至 0 项记录，共 0 项",
                        sInfoFiltered: "(由 _MAX_ 项记录过滤)",
                        sInfoPostFix: "",
                        sSearch: "从当前数据中检索:",
                        sUrl: "",
                        oPaginate: {
                            sFirst: "首页",
                            sPrevious: "上页",
                            sNext: "下页",
                            sLast: "末页"
                        },
                        oAria: {
                            sSortAscending: ": 以升序排列此列",
                            sSortDescending: ": 以降序排列此列"
                        }
                    },
                    retrieve: true
                });
            } else {
                alert(response.message);
            }
        }
    });


});