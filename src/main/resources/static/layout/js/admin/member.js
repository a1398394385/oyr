let app = new Vue({
    el: '#memberApp',
    data: {
        users: null,
        user: {
            id: null,
            username: null,
            password: null,
            telephone: null,
            address: null,
            createTime: null,
        },
        updating: false
    },
    //获取用户列表数据
    beforeMount: function () {
        axios.get("/user/")
            .then(res => {
                if (res.data.status == "success") {
                    this.users = res.data.data
                } else {
                    alert("数据错误，稍后重试");
                    location.reload();
                }
            })
            .catch(err => {
                alert("网络故障");
                location.href = "/manage/index";
                console.error(err);
            })
    },
    updated: function () {
        $("table").DataTable({
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
    },
    methods: {
        deleteUser: function (userId) {
            axios.delete("/user/" + userId)
                .then(res => {
                    if (res.data.status == "success") {
                        location.reload();
                    } else {
                        alert("删除失败，稍后再试");
                        location.reload();
                    }
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/manage/index";
                    console.error(err);
                })
        },
        updateUser: function (user) {
            this.updating = true;
        },
        submitUpdate: function () {
            axios.put("/user/" + this.user.id, {
                username: this.user.username,
            }).then(res => {
                if (res.data.status == "success") {
                    location.reload();
                } else {
                    alert("数据错误，请稍后重试")
                    location.reload();
                }
            }).catch(err => {
                alert("您的网络异常，请刷新后重试")
                location.href = "/manage/index";
                console.error(err);
            })
        },
    }
});