let app = new Vue({
    el: '#orderApp',
    data: {
        orders: null,
        currentOrder: {
            id: null,
            name: null,
            userId: null,
            phoneId: null,
            price: null,
            model: null,
            state: null,
            telephone: null,
            address: null,
            createTime: null,
        },
        updating: false,
    },
    //获取用户列表数据
    beforeMount: function () {
        axios.get("/orders/all")
            .then(res => {
                if (res.data.status == "success") {
                    this.orders = res.data.data
                    console.log(res.data.data);

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
        deleteOrder: function (orderId) {
            axios.delete("/orders/" + orderId)
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
        updateOrder: function (order) {
            let states = ["订单创立", "上门取货", "收到手机", "订单完成", "退货中", "退货完成"];
            this.currentOrder = JSON.parse(JSON.stringify(order));
            this.currentOrder.state = states.indexOf(this.currentOrder.state);
            this.updating = true;
        },
        submitUpdate: function () {
            axios.put("/orders/" + this.currentOrder.id, {
                name: this.currentOrder.name,
                telephone: this.currentOrder.telephone,
                name: this.currentOrder.name,
                price: this.currentOrder.price,
                address: this.currentOrder.address,
                state: this.currentOrder.state,
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
        // 将收货状态修改为中文
        orders: function (newValue, oldValue) {
            let states = ["订单创立", "上门取货", "收到手机", "订单完成", "退货中", "退货完成"];
            for (var i in newValue) {
                newValue[i].state = states[newValue[i].state];
            }
        },
    }
});