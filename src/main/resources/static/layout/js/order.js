let app = new Vue({
    el: '#app',
    data: {
        model: "小米 10 Pro（5G）.png",
        image: "/image/具体机型/小米/小米 10 Pro（5G）.png",
        price: "3219",
        user: {
            name: "",
            phone: "",
        }
    },
    beforeMount: function () {
        // axios.get("/phone/brand/1")
        //     .then(res => {
        //         if (res.data.status == "success") {
        //             this.phones = res.data.data
        //         } else {
        //             alert("数据错误，请稍后重试")
        //             location.href = "/home";
        //         }
        //     })
        //     .catch(err => {
        //         alert("您的网络异常，请刷新后重试")
        //         location.href = "/home";
        //         console.error(err);
        //     })
    },
    methods: {
        logout: function () {
            axios.post("/logout")
                .then(res => {
                    alert(res.data.data)
                    location.reload()
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/home";
                    console.error(err);
                })
        }
    }
});