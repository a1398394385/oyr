//$(function () {}页面初始化执行
$(function () {
    //#.:
    $.ajax({
        type: "get",
        url: "/user/",
        //contentType: "application/json;charset=UTF-8",发送给后端数据的类型       
        // data: JSON.stringify($('form').serializeJSON()),发送给后端的数据
        dataType: "json",
        success: (response) => {
            if (response.status == "success") {
                response.data.forEach(member => {
                    var item =
                        `<tr>
                        <td>${member.id}</td>
                        <td>${member.username}</td>
                        <td>${member.telephone}</td>
                        <td>${member.address}</td>
                        <td>${member.createTime}</td>
                        <td><button onclick="deleteOrder${member.id}">删除</button>
                        <button onclick="updateOrder${member.id}">更新</button></td>
                    </tr>`;
                    //console.log(item);
                    $("#users").append(item);
                });
                $("#userList").DataTable({
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