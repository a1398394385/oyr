let app = new Vue({
    el: '#adminApp',
    data: {
        admins: null,
        admin: {
            id: null,
            name: null,
            password: null,
            authority: null,
            createTime: null,
            updateTime: null,
        },
        updating: false,
    },
    //页面之前的数据获取
    beforeMount: function () {
        axios.get("/admin/")
            .then(res => {
                if (res.data.status == "success") {
                    this.admins = res.data.data
                } else {
                    alert("数据错误，请稍后重试")
                    location.reload();
                }
            })
            .catch(err => {
                alert("您的网络异常，请刷新后重试")
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
        //删除按钮
        deleteAdmin: function (adminId) {
            axios.delete("/admin/" + adminId)
                .then(res => {
                    if (res.data.status == "success") {
                        location.reload();
                    } else {
                        alert("数据错误，请稍后重试")
                        location.reload();
                    }
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/manage/index";
                    console.error(err);
                })
        },
        updateAdmin: function (admin) {
            let authorities = ["无权限", "普通管理员", "超级管理员"];
            this.admin = JSON.parse(JSON.stringify(admin));
            this.admin.authority = authorities.indexOf(this.admin.authority);
            this.updating = true;
        },
        submitUpdate: function () {
            axios.put("/admin/" + this.admin.id, {
                name: this.admin.name,
                password: this.admin.password,
                authority: this.admin.authority
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
    },
    watch: {
        // 将权限等级数字替换为中文
        // authorities为权限数组，
        admins: function (newValue, oldValue) {
            let authorities = ["无权限", "普通管理员", "超级管理员"];
            for (var i in newValue) {
                newValue[i].authority = authorities[newValue[i].authority];
            }
        },
    }
});
